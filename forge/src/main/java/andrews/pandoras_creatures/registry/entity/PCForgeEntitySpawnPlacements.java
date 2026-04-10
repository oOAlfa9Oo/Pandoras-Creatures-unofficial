package andrews.pandoras_creatures.forge.registry.entity;

import andrews.pandoras_creatures.forge.registry.PCForgeEntities;
import andrews.pandoras_creatures.registry.entity.PCEntitySpawnRules;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public final class PCForgeEntitySpawnPlacements {
    private PCForgeEntitySpawnPlacements() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(PCForgeEntitySpawnPlacements::registerAll);
    }

    public static void registerAll(SpawnPlacementRegisterEvent event) {
        event.register(
                PCForgeEntities.seahorse(),
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) ->
                        PCEntitySpawnRules.canSpawnSeahorse(level.getFluidState(pos).is(FluidTags.WATER)),
                SpawnPlacementRegisterEvent.Operation.REPLACE
        );

        event.register(
                PCForgeEntities.crab(),
                SpawnPlacementTypes.NO_RESTRICTIONS,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) ->
                        PCEntitySpawnRules.canSpawnCrab(
                                level.getBiome(pos).is(net.minecraft.world.level.biome.Biomes.BEACH),
                                level.getBiome(pos).is(net.minecraft.world.level.biome.Biomes.WARM_OCEAN),
                                pos.getY(),
                                level.getBlockState(pos.below()).is(Blocks.SAND),
                                level.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK)
                        ),
                SpawnPlacementRegisterEvent.Operation.REPLACE
        );

        event.register(
                PCForgeEntities.hellhound(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) ->
                        PCEntitySpawnRules.canSpawnHostileGroundMob(level.getDifficulty() != Difficulty.PEACEFUL),
                SpawnPlacementRegisterEvent.Operation.REPLACE
        );

        event.register(
                PCForgeEntities.arachnon(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) ->
                        PCEntitySpawnRules.canSpawnArachnon(level.getDifficulty() != Difficulty.PEACEFUL, level.getRawBrightness(pos, 0)),
                SpawnPlacementRegisterEvent.Operation.REPLACE
        );

        event.register(
                PCForgeEntities.acidicArchvine(),
                SpawnPlacementTypes.NO_RESTRICTIONS,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) ->
                        PCEntitySpawnRules.canSpawnAcidicArchvine(
                                level.getDifficulty() != Difficulty.PEACEFUL,
                                PCEntitySpawnRules.isJungleArchvineBiome(level, pos),
                                PCEntitySpawnRules.isNetherArchvineBiome(level, pos),
                                pos.getY(),
                                level.getBlockState(pos).isAir(),
                                level.getBlockState(pos.above()).isAir(),
                                PCEntitySpawnRules.hasValidAcidicArchvineCeiling(level, pos),
                                PCEntitySpawnRules.hasConsecutiveAirBelow(level, pos, PCEntitySpawnRules.acidicArchvineRequiredAirDepth())
                        ),
                SpawnPlacementRegisterEvent.Operation.REPLACE
        );

        event.register(
                PCForgeEntities.bufflon(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) ->
                        PCEntitySpawnRules.canSpawnBufflon(level.getRawBrightness(pos, 0),
                                level.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK)),
                SpawnPlacementRegisterEvent.Operation.REPLACE
        );

        event.register(
                PCForgeEntities.endTroll(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) -> true,
                SpawnPlacementRegisterEvent.Operation.REPLACE
        );
    }
}
