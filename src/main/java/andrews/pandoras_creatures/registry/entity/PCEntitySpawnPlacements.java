package andrews.pandoras_creatures.registry.entity;

import andrews.pandoras_creatures.registry.PCEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

public final class PCEntitySpawnPlacements {
    private static final int ACIDIC_ARCHVINE_REQUIRED_AIR_DEPTH = 8;

    private PCEntitySpawnPlacements() {
    }

    public static void registerAll(RegisterSpawnPlacementsEvent event) {
        event.register(PCEntities.SEAHORSE.get(),
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) ->
                        PCEntitySpawnRules.canSpawnSeahorse(level.getFluidState(pos).is(FluidTags.WATER)),
                RegisterSpawnPlacementsEvent.Operation.REPLACE);

        event.register(PCEntities.CRAB.get(),
                SpawnPlacementTypes.NO_RESTRICTIONS,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) ->
                        PCEntitySpawnRules.canSpawnCrab(
                                level.getBiome(pos).is(Biomes.BEACH),
                                level.getBiome(pos).is(Biomes.WARM_OCEAN),
                                pos.getY(),
                                level.getBlockState(pos.below()).is(Blocks.SAND),
                                level.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK)
                        ),
                RegisterSpawnPlacementsEvent.Operation.REPLACE);

        event.register(PCEntities.HELLHOUND.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) ->
                        PCEntitySpawnRules.canSpawnHostileGroundMob(level.getDifficulty() != Difficulty.PEACEFUL),
                RegisterSpawnPlacementsEvent.Operation.REPLACE);

        event.register(PCEntities.ARACHNON.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) ->
                        PCEntitySpawnRules.canSpawnArachnon(level.getDifficulty() != Difficulty.PEACEFUL, level.getRawBrightness(pos, 0)),
                RegisterSpawnPlacementsEvent.Operation.REPLACE);

        event.register(PCEntities.ACIDIC_ARCHVINE.get(),
                SpawnPlacementTypes.NO_RESTRICTIONS,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) ->
                        PCEntitySpawnRules.canSpawnAcidicArchvine(
                                level.getDifficulty() != Difficulty.PEACEFUL,
                                isJungleArchvineBiome(level, pos),
                                isNetherArchvineBiome(level, pos),
                                pos.getY(),
                                level.getBlockState(pos).isAir(),
                                level.getBlockState(pos.above()).isAir(),
                                hasValidAcidicArchvineCeiling(level, pos),
                                hasConsecutiveAirBelow(level, pos, ACIDIC_ARCHVINE_REQUIRED_AIR_DEPTH)
                        ),
                RegisterSpawnPlacementsEvent.Operation.REPLACE);

        event.register(PCEntities.BUFFLON.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) ->
                        PCEntitySpawnRules.canSpawnBufflon(level.getRawBrightness(pos, 0),
                                level.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK)),
                RegisterSpawnPlacementsEvent.Operation.REPLACE);

        event.register(PCEntities.END_TROLL.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) -> true,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    private static boolean isJungleArchvineBiome(LevelAccessor level, BlockPos pos) {
        return level.getBiome(pos).is(Biomes.JUNGLE)
                || level.getBiome(pos).is(Biomes.BAMBOO_JUNGLE)
                || level.getBiome(pos).is(Biomes.SPARSE_JUNGLE);
    }

    private static boolean isNetherArchvineBiome(LevelAccessor level, BlockPos pos) {
        return level.getBiome(pos).is(Biomes.NETHER_WASTES)
                || level.getBiome(pos).is(Biomes.CRIMSON_FOREST);
    }

    private static boolean hasValidAcidicArchvineCeiling(LevelAccessor level, BlockPos pos) {
        return level.getBlockState(pos.above(2)).is(Blocks.JUNGLE_LEAVES)
                || level.getBlockState(pos.above(2)).is(Blocks.NETHERRACK);
    }

    private static boolean hasConsecutiveAirBelow(LevelAccessor level, BlockPos pos, int depth) {
        for (int i = 0; i < depth; i++) {
            if (!level.getBlockState(pos.below(i)).isAir()) {
                return false;
            }
        }
        return true;
    }
}
