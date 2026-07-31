package andrews.pandoras_creatures.gametest;

import andrews.pandoras_creatures.registry.structure.PCStructureIds;
import andrews.pandoras_creatures.world.structures.end_prison.EndPrisonPieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.Direction;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Places the official vanilla End City ship through the End Prison piece code.
 */
public final class EndPrisonShipGameTests {
    private EndPrisonShipGameTests() {
    }

    public static void vanillaShipMarkersCreateOfficialContentsInEveryRotation(GameTestHelper helper) {
        ServerLevel level = helper.getLevel().getServer().getLevel(net.minecraft.world.level.Level.END);
        helper.assertTrue(level != null, "The End dimension must be loaded for official ship validation");
        if (level == null) {
            return;
        }
        BlockPos testOrigin = new BlockPos(0, 123, 0);
        List<PlacedShip> ships = new ArrayList<>();

        Rotation[] rotations = {
                Rotation.NONE,
                Rotation.CLOCKWISE_90,
                Rotation.CLOCKWISE_180,
                Rotation.COUNTERCLOCKWISE_90
        };
        for (int index = 0; index < rotations.length; index++) {
            BlockPos shipOrigin = new BlockPos(testOrigin.getX() + index * 96, 123, testOrigin.getZ());
            ships.add(prepareShip(level, shipOrigin, rotations[index]));
        }

        helper.runAfterDelay(10L, () -> {
            for (PlacedShip ship : ships) {
                placeShip(level, ship);
            }
            helper.runAfterDelay(1L, () -> {
                try {
                    for (PlacedShip ship : ships) {
                        assertOfficialContents(helper, level, ship);
                    }
                    helper.succeed();
                } finally {
                    for (PlacedShip ship : ships) {
                        setChunksForced(level, ship.box(), false);
                    }
                }
            });
        });
    }

    private static PlacedShip prepareShip(ServerLevel level, BlockPos origin, Rotation rotation) {
        StructureTemplateManager templates = level.getStructureManager();
        EndPrisonPieces.Piece piece = new EndPrisonPieces.Piece(templates, origin, rotation);
        BoundingBox box = piece.getBoundingBox();
        Structure structure = level.registryAccess().lookupOrThrow(Registries.STRUCTURE)
                .getValue(PCStructureIds.id(PCStructureIds.END_PRISON));
        StructureStart start = new StructureStart(
                structure,
                new ChunkPos(origin.getX() >> 4, origin.getZ() >> 4),
                0,
                new PiecesContainer(List.of(piece)));
        setChunksForced(level, box, true);

        for (int chunkX = box.minX() >> 4; chunkX <= box.maxX() >> 4; chunkX++) {
            for (int chunkZ = box.minZ() >> 4; chunkZ <= box.maxZ() >> 4; chunkZ++) {
                level.getChunkSource().getChunk(chunkX, chunkZ, ChunkStatus.FULL, true);
            }
        }
        return new PlacedShip(rotation, box, start);
    }

    private static void placeShip(ServerLevel level, PlacedShip ship) {
        BoundingBox box = ship.box();
        for (int chunkX = box.minX() >> 4; chunkX <= box.maxX() >> 4; chunkX++) {
            for (int chunkZ = box.minZ() >> 4; chunkZ <= box.maxZ() >> 4; chunkZ++) {
                ChunkPos chunkPos = new ChunkPos(chunkX, chunkZ);
                BoundingBox chunkBox = new BoundingBox(
                        chunkPos.getMinBlockX(),
                        level.getMinY(),
                        chunkPos.getMinBlockZ(),
                        chunkPos.getMaxBlockX(),
                        level.getMaxY(),
                        chunkPos.getMaxBlockZ());
                ship.start().placeInChunk(
                        level,
                        level.structureManager(),
                        level.getChunkSource().getGenerator(),
                        level.getRandom(),
                        chunkBox,
                        chunkPos);
            }
        }
    }

    private static void assertOfficialContents(
            GameTestHelper helper,
            ServerLevel level,
            PlacedShip ship) {
        BoundingBox box = ship.box();
        net.minecraft.world.phys.AABB entityBounds = net.minecraft.world.phys.AABB.of(box).inflate(32.0D);
        int shulkers = level.getEntitiesOfClass(
                Shulker.class,
                entityBounds).size();
        List<ItemFrame> frames = level.getEntitiesOfClass(
                ItemFrame.class,
                entityBounds);

        int chests = 0;
        for (int x = box.minX(); x <= box.maxX(); x++) {
            for (int y = box.minY(); y <= box.maxY(); y++) {
                for (int z = box.minZ(); z <= box.maxZ(); z++) {
                    if (level.getBlockState(new BlockPos(x, y, z)).is(Blocks.CHEST)) {
                        chests++;
                    }
                }
            }
        }

        helper.assertTrue(shulkers == 3,
                "Official ship must create 3 Shulkers for rotation " + ship.rotation() + "; found=" + shulkers
                        + ", chests=" + chests + ", frames=" + frames.size() + ", box=" + box);
        helper.assertTrue(chests == 2,
                "Official ship must contain 2 treasure chests for rotation " + ship.rotation() + "; found=" + chests);
        helper.assertTrue(frames.size() == 1,
                "Official ship must create 1 Item Frame for rotation " + ship.rotation() + "; found=" + frames.size());
        if (frames.size() == 1) {
            ItemFrame frame = frames.get(0);
            Direction expectedDirection = ship.rotation().rotate(Direction.SOUTH);
            helper.assertTrue(frame.getItem().is(Items.ELYTRA),
                    "Official ship Item Frame must contain an Elytra for rotation " + ship.rotation());
            helper.assertTrue(frame.getDirection() == expectedDirection,
                    "Official ship Item Frame direction must rotate with the ship; expected="
                            + expectedDirection + ", actual=" + frame.getDirection());
        }
    }

    private static void setChunksForced(ServerLevel level, BoundingBox box, boolean forced) {
        for (int chunkX = box.minX() >> 4; chunkX <= box.maxX() >> 4; chunkX++) {
            for (int chunkZ = box.minZ() >> 4; chunkZ <= box.maxZ() >> 4; chunkZ++) {
                level.setChunkForced(chunkX, chunkZ, forced);
            }
        }
    }

    private record PlacedShip(Rotation rotation, BoundingBox box, StructureStart start) {
    }
}
