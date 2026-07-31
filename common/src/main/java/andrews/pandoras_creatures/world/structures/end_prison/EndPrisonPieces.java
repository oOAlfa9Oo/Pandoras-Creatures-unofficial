package andrews.pandoras_creatures.world.structures.end_prison;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.registry.structure.PCStructureIds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.chunk.ChunkGenerator;

public class EndPrisonPieces {

    private static final ResourceLocation SHIP_TEMPLATE =
            ResourceLocation.tryParse(EndPrisonBehaviorRules.VANILLA_SHIP_TEMPLATE);

    public static void addPieces(StructureTemplateManager templateManager, BlockPos pos, Rotation rotation, StructurePiecesBuilder builder, RandomSource random) {
        if (EndPrisonBehaviorRules.shouldAddShip(random.nextInt(EndPrisonBehaviorRules.SHIP_CHANCE_BOUND))) {
            BlockPos shipPos = getShipBlockPos(pos.getX(), pos.getZ(), rotation);
            builder.addPiece(new EndPrisonPieces.Piece(templateManager, shipPos, getShipRotation(rotation)));
        }
    }

    private static BlockPos getShipBlockPos(int x, int z, Rotation rot) {
        return switch (rot) {
            case CLOCKWISE_90 -> new BlockPos(x + 20, 123, z + 33);
            case COUNTERCLOCKWISE_90 -> new BlockPos(x - 20, 123, z - 33);
            case CLOCKWISE_180 -> new BlockPos(x - 5, 123, z + 8);
            default -> new BlockPos(x + 5, 123, z - 8);
        };
    }

    private static Rotation getShipRotation(Rotation rot) {
        return switch (rot) {
            case CLOCKWISE_90 -> Rotation.CLOCKWISE_180;
            case COUNTERCLOCKWISE_90 -> Rotation.NONE;
            case CLOCKWISE_180 -> Rotation.CLOCKWISE_90;
            default -> Rotation.COUNTERCLOCKWISE_90;
        };
    }

    public static class Piece extends TemplateStructurePiece {

        public Piece(StructureTemplateManager templateManager, BlockPos position, Rotation rotation) {
            super(
                    PandorasCreaturesCommon.platform().registry().structurePieceType(PCStructureIds.END_PRISON_PIECE),
                    0,
                    templateManager,
                    SHIP_TEMPLATE,
                    SHIP_TEMPLATE.toString(),
                    makeSettings(rotation),
                    position
            );
        }

        public Piece(StructureTemplateManager templateManager, CompoundTag tag) {
            super(
                    PandorasCreaturesCommon.platform().registry().structurePieceType(PCStructureIds.END_PRISON_PIECE),
                    tag,
                    templateManager,
                    rl -> makeSettings(Rotation.valueOf(tag.getString("Rot")))
            );
        }

        private static StructurePlaceSettings makeSettings(Rotation rotation) {
            return new StructurePlaceSettings()
                    .setIgnoreEntities(true)
                    .setRotation(rotation)
                    .setMirror(Mirror.NONE)
                    .addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
        }

        @Override
        protected void addAdditionalSaveData(StructurePieceSerializationContext context, CompoundTag tag) {
            super.addAdditionalSaveData(context, tag);
            tag.putString("Rot", this.placeSettings.getRotation().name());
        }

        @Override
        protected void handleDataMarker(String name, BlockPos pos, ServerLevelAccessor level, RandomSource random, BoundingBox box) {
            if (name.startsWith("Chest")) {
                BlockPos chestPos = pos.below();
                if (box.isInside(chestPos) && level.getBlockEntity(chestPos) instanceof RandomizableContainerBlockEntity container) {
                    container.setLootTable(BuiltInLootTables.END_CITY_TREASURE);
                    container.setLootTableSeed(random.nextLong());
                }
            } else if (box.isInside(pos) && Level.isInSpawnableBounds(pos)) {
                if (name.startsWith("Sentry")) {
                    Shulker shulker = EntityType.SHULKER.create(level.getLevel());
                    if (shulker != null) {
                        shulker.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                        level.addFreshEntity(shulker);
                    }
                } else if (name.startsWith("Elytra")) {
                    ItemFrame itemFrame = new ItemFrame(level.getLevel(), pos, this.placeSettings.getRotation().rotate(Direction.SOUTH));
                    itemFrame.setItem(new ItemStack(Items.ELYTRA), false);
                    level.addFreshEntity(itemFrame);
                }
            }
        }

        @Override
        public void postProcess(WorldGenLevel level, StructureManager structureManager, ChunkGenerator generator, RandomSource random, BoundingBox box, ChunkPos chunkPos, BlockPos pos) {
            this.templatePosition = new BlockPos(this.templatePosition.getX(), 123, this.templatePosition.getZ());
            super.postProcess(level, structureManager, generator, random, box, chunkPos, pos);

            for (int x = box.minX(); x <= box.maxX(); x++) {
                for (int y = box.minY(); y <= box.maxY(); y++) {
                    for (int z = box.minZ(); z <= box.maxZ(); z++) {
                        BlockPos containerPos = new BlockPos(x, y, z);
                        if (level.getBlockEntity(containerPos) instanceof RandomizableContainerBlockEntity container) {
                            container.setLootTable(BuiltInLootTables.END_CITY_TREASURE);
                            container.setLootTableSeed(random.nextLong());
                        }
                    }
                }
            }
        }
    }
}
