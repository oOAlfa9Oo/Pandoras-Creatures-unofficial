package andrews.pandoras_creatures.gametest;

import andrews.pandoras_creatures.entities.EndTrollEntity;
import andrews.pandoras_creatures.registry.structure.PCStructureIds;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.Optional;

/**
 * Valida en un servidor real que la estructura End Prison (la unica del mod)
 * se carga desde datapack y puede generarse de verdad (jigsaw + piezas).
 */
public final class EndPrisonStructureGameTests {
    private static final String STRUCTURE_BATCH = "end_prison_structure";
    private static final String SHARED_TEMPLATE = "gametest/bufflon_arena";

    private EndPrisonStructureGameTests() {
    }

    public static void structureAndStructureSetAreLoadedFromDatapack(GameTestHelper helper) {
        ServerLevel level = helper.getLevel();

        Registry<Structure> structures = level.registryAccess().registryOrThrow(Registries.STRUCTURE);
        Structure endPrison = structures.get(Reference.id(PCStructureIds.END_PRISON));
        helper.assertTrue(endPrison != null, "End Prison structure JSON should load into the structure registry");

        Registry<StructureSet> structureSets = level.registryAccess().registryOrThrow(Registries.STRUCTURE_SET);
        StructureSet endPrisonSet = structureSets.get(Reference.id(PCStructureIds.END_PRISON));
        helper.assertTrue(endPrisonSet != null, "End Prison structure_set JSON should load into the structure_set registry");
        helper.assertTrue(endPrisonSet != null && !endPrisonSet.structures().isEmpty(),
                "End Prison structure_set should reference at least one structure");
        helper.succeed();
    }

    public static void structureTemplatesLoadWithRealSize(GameTestHelper helper) {
        ServerLevel level = helper.getLevel();

        Optional<StructureTemplate> body = level.getStructureManager().get(Reference.id(PCStructureIds.END_PRISON_BODY_TEMPLATE));
        Optional<StructureTemplate> ship = level.getStructureManager().get(net.minecraft.resources.ResourceLocation.tryParse(andrews.pandoras_creatures.world.structures.end_prison.EndPrisonBehaviorRules.VANILLA_SHIP_TEMPLATE));

        helper.assertTrue(body.isPresent(), "End Prison body template NBT should load");
        helper.assertTrue(ship.isPresent(), "Vanilla End City ship template NBT should load");
        helper.assertTrue(body.isPresent() && body.get().getSize().getX() > 0 && body.get().getSize().getY() > 0,
                "End Prison body template should have a real size");
        helper.assertTrue(ship.isPresent() && ship.get().getSize().getX() > 0 && ship.get().getSize().getY() > 0,
                "End Prison ship template should have a real size");
        helper.succeed();
    }

    public static void structureGeneratesValidStartWithPieces(GameTestHelper helper) {
        ServerLevel level = helper.getLevel();
        Registry<Structure> structures = level.registryAccess().registryOrThrow(Registries.STRUCTURE);
        Structure endPrison = structures.get(Reference.id(PCStructureIds.END_PRISON));
        helper.assertTrue(endPrison != null, "End Prison structure must exist to test generation");

        ChunkGenerator generator = level.getChunkSource().getGenerator();
        StructureStart start = endPrison.generate(
                level.registryAccess(),
                generator,
                generator.getBiomeSource(),
                level.getChunkSource().randomState(),
                level.getStructureManager(),
                level.getSeed(),
                new ChunkPos(helper.absolutePos(BlockPos.ZERO)),
                0,
                level,
                biome -> true);

        helper.assertTrue(start.isValid(), "End Prison should produce a valid structure start (jigsaw + pieces)");
        helper.assertTrue(!start.getPieces().isEmpty(), "End Prison structure start should contain generated pieces");
        helper.succeed();
    }

    public static void naturallyPlacedEndPrisonContainsEndTroll(GameTestHelper helper) {
        ServerLevel end = helper.getLevel().getServer().getLevel(Level.END);
        helper.assertTrue(end != null, "The End dimension must be loaded for natural structure validation");
        if (end == null) {
            return;
        }

        Registry<Structure> structures = end.registryAccess().registryOrThrow(Registries.STRUCTURE);
        Structure endPrison = structures.get(Reference.id(PCStructureIds.END_PRISON));
        helper.assertTrue(endPrison != null, "End Prison must be present in the runtime structure registry");
        if (endPrison == null) {
            return;
        }

        Registry<StructureSet> structureSets = end.registryAccess().registryOrThrow(Registries.STRUCTURE_SET);
        StructureSet endPrisonSet = structureSets.get(Reference.id(PCStructureIds.END_PRISON));
        helper.assertTrue(endPrisonSet != null && endPrisonSet.placement() instanceof RandomSpreadStructurePlacement,
                "End Prison must use its random-spread placement contract");
        if (endPrisonSet == null || !(endPrisonSet.placement() instanceof RandomSpreadStructurePlacement placement)) {
            return;
        }

        Holder.Reference<Structure> endPrisonHolder = structures.getHolder(
                net.minecraft.resources.ResourceKey.create(Registries.STRUCTURE, Reference.id(PCStructureIds.END_PRISON)))
                .orElse(null);
        helper.assertTrue(endPrisonHolder != null
                        && !end.getChunkSource().getGeneratorState().getPlacementsForStructure(endPrisonHolder).isEmpty(),
                "End Prison placement must be connected to The End chunk generator state");
        if (endPrisonHolder == null) {
            return;
        }

        ChunkGenerator generator = end.getChunkSource().getGenerator();
        StructureStart naturalStart = null;
        ChunkPos naturalStartChunk = null;
        int eligibleCandidates = 0;
        int allowedBiomeCandidates = 0;
        for (int regionX = 3; regionX <= 12 && naturalStart == null; regionX++) {
            for (int regionZ = -4; regionZ <= 4; regionZ++) {
                ChunkPos candidate = placement.getPotentialStructureChunk(
                        end.getSeed(),
                        regionX * placement.spacing(),
                        regionZ * placement.spacing());
                if (!placement.isStructureChunk(end.getChunkSource().getGeneratorState(), candidate.x, candidate.z)) {
                    continue;
                }
                eligibleCandidates++;

                BlockPos biomePos = new BlockPos(candidate.getMiddleBlockX(), 120, candidate.getMiddleBlockZ());
                if (!endPrison.biomes().contains(end.getBiome(biomePos))) {
                    continue;
                }
                allowedBiomeCandidates++;

                StructureStart candidateStart = endPrison.generate(
                        end.registryAccess(),
                        generator,
                        generator.getBiomeSource(),
                        end.getChunkSource().randomState(),
                        end.getStructureManager(),
                        end.getSeed(),
                        candidate,
                        0,
                        end,
                        endPrison.biomes()::contains);
                if (candidateStart.isValid()) {
                    naturalStart = candidateStart;
                    naturalStartChunk = candidate;
                    break;
                }
            }
        }

        helper.assertTrue(naturalStart != null,
                "End Prison should generate from a natural outer-End candidate; eligible=" + eligibleCandidates
                        + ", allowed-biome=" + allowedBiomeCandidates);
        if (naturalStart == null) {
            return;
        }
        helper.assertTrue(naturalStartChunk != null && Math.abs(naturalStartChunk.x) > 64,
                "Natural End Prison candidate should be outside the central End island");

        StructureStart generatedStart = naturalStart;
        helper.assertTrue(generatedStart.getPieces().get(0) instanceof PoolElementStructurePiece,
                "Natural End Prison start must begin with its jigsaw body piece");
        if (!(generatedStart.getPieces().get(0) instanceof PoolElementStructurePiece)) {
            return;
        }

        PoolElementStructurePiece bodyPiece = (PoolElementStructurePiece) generatedStart.getPieces().get(0);
        net.minecraft.world.phys.Vec3 relativeTrollPosition = StructureTemplate.transform(
                new net.minecraft.world.phys.Vec3(
                        andrews.pandoras_creatures.world.structures.end_prison.EndPrisonBehaviorRules.END_TROLL_X,
                        andrews.pandoras_creatures.world.structures.end_prison.EndPrisonBehaviorRules.END_TROLL_Y,
                        andrews.pandoras_creatures.world.structures.end_prison.EndPrisonBehaviorRules.END_TROLL_Z),
                net.minecraft.world.level.block.Mirror.NONE,
                bodyPiece.getRotation(),
                BlockPos.ZERO);
        net.minecraft.world.phys.Vec3 expectedTrollPosition = relativeTrollPosition.add(
                bodyPiece.getPosition().getX(),
                bodyPiece.getPosition().getY(),
                bodyPiece.getPosition().getZ());
        net.minecraft.world.level.levelgen.structure.BoundingBox box = generatedStart.getBoundingBox();
        for (int chunkX = box.minX() >> 4; chunkX <= box.maxX() >> 4; chunkX++) {
            for (int chunkZ = box.minZ() >> 4; chunkZ <= box.maxZ() >> 4; chunkZ++) {
                end.setChunkForced(chunkX, chunkZ, true);
                end.getChunkSource().getChunk(chunkX, chunkZ, ChunkStatus.FULL, true);
            }
        }

        helper.runAfterDelay(20L, () -> {
            end.getServer().setDifficulty(net.minecraft.world.Difficulty.NORMAL, true);
            end.getEntities(
                    EntityTypeTest.forClass(EndTrollEntity.class),
                    entity -> box.isInside(entity.blockPosition())).forEach(EndTrollEntity::discard);
            for (int chunkX = box.minX() >> 4; chunkX <= box.maxX() >> 4; chunkX++) {
                for (int chunkZ = box.minZ() >> 4; chunkZ <= box.maxZ() >> 4; chunkZ++) {
                    ChunkPos chunkPos = new ChunkPos(chunkX, chunkZ);
                    net.minecraft.world.level.levelgen.structure.BoundingBox chunkBox =
                            new net.minecraft.world.level.levelgen.structure.BoundingBox(
                                    chunkPos.getMinBlockX(),
                                    end.getMinBuildHeight(),
                                    chunkPos.getMinBlockZ(),
                                    chunkPos.getMaxBlockX(),
                                    end.getMaxBuildHeight() - 1,
                                    chunkPos.getMaxBlockZ());
                    generatedStart.placeInChunk(
                            end,
                            end.structureManager(),
                            generator,
                            end.getRandom(),
                            chunkBox,
                            chunkPos);
                }
            }

            helper.runAfterDelay(1L, () -> {
                java.util.List<? extends EndTrollEntity> structuralEndTrolls = end.getEntities(
                        EntityTypeTest.forClass(EndTrollEntity.class),
                        entity -> box.isInside(entity.blockPosition()));
                int allEndTrolls = end.getEntities(
                        EntityTypeTest.forClass(EndTrollEntity.class),
                        entity -> true).size();
                helper.assertTrue(structuralEndTrolls.size() == 1 && allEndTrolls == 1,
                        "End Prison should create exactly one structural End Troll; in-structure=" + structuralEndTrolls.size()
                                + ", all-end=" + allEndTrolls);
                if (structuralEndTrolls.size() == 1) {
                    EndTrollEntity endTroll = structuralEndTrolls.get(0);
                    helper.assertTrue(endTroll.position().distanceToSqr(expectedTrollPosition) < 0.01D,
                            "Structural End Troll must retain the official template position");
                    helper.assertTrue(endTroll.getHealth() == andrews.pandoras_creatures.world.structures.end_prison.EndPrisonBehaviorRules.END_TROLL_HEALTH,
                            "Structural End Troll must retain the official 200 health");
                    helper.assertTrue(endTroll.isPersistenceRequired(),
                            "Structural End Troll must remain persistent");
                    helper.assertTrue(!endTroll.isEntityStanding() && !endTroll.hasScreamed(),
                            "Structural End Troll must begin seated and without having screamed");
                }
                for (int chunkX = box.minX() >> 4; chunkX <= box.maxX() >> 4; chunkX++) {
                    for (int chunkZ = box.minZ() >> 4; chunkZ <= box.maxZ() >> 4; chunkZ++) {
                        end.setChunkForced(chunkX, chunkZ, false);
                    }
                }
                helper.succeed();
            });
        });
    }
}
