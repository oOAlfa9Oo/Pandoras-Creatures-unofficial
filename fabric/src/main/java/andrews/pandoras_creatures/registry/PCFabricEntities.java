package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.entities.AcidicArchvineEntity;
import andrews.pandoras_creatures.entities.ArachnonEntity;
import andrews.pandoras_creatures.entities.BufflonEntity;
import andrews.pandoras_creatures.entities.CrabEntity;
import andrews.pandoras_creatures.entities.EndTrollEntity;
import andrews.pandoras_creatures.entities.HellhoundEntity;
import andrews.pandoras_creatures.entities.SeahorseEntity;
import andrews.pandoras_creatures.entities.projectiles.EndTrollBulletDamageEntity;
import andrews.pandoras_creatures.entities.projectiles.EndTrollBulletPoisonEntity;
import andrews.pandoras_creatures.entities.projectiles.EndTrollBulletWitherEntity;
import andrews.pandoras_creatures.registry.entity.PCEntityBootstrap;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.registry.entity.PCEntitySpawnRules;
import andrews.pandoras_creatures.registry.entity.PCEntityTypeFactory;
import andrews.pandoras_creatures.world.biome.PCBiomeSpawnCatalog;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.List;
import java.util.function.Predicate;

public final class PCFabricEntities {
    public static final EntityType<AcidicArchvineEntity> ACIDIC_ARCHVINE = register(PCEntityIds.ACIDIC_ARCHVINE, PCEntityBootstrap.acidicArchvineType());
    public static final EntityType<ArachnonEntity> ARACHNON = register(PCEntityIds.ARACHNON, PCEntityBootstrap.arachnonType());
    public static final EntityType<BufflonEntity> BUFFLON = register(PCEntityIds.BUFFLON, PCEntityBootstrap.bufflonType());
    public static final EntityType<CrabEntity> CRAB = register(PCEntityIds.CRAB, PCEntityBootstrap.crabType());
    public static final EntityType<EndTrollEntity> END_TROLL = register(PCEntityIds.END_TROLL, PCEntityBootstrap.endTrollType());
    public static final EntityType<EndTrollBulletDamageEntity> END_TROLL_BULLET_DAMAGE = register(PCEntityIds.END_TROLL_BULLET_DAMAGE, PCEntityBootstrap.endTrollBulletDamageType());
    public static final EntityType<EndTrollBulletPoisonEntity> END_TROLL_BULLET_POISON = register(PCEntityIds.END_TROLL_BULLET_POISON, PCEntityBootstrap.endTrollBulletPoisonType());
    public static final EntityType<EndTrollBulletWitherEntity> END_TROLL_BULLET_WITHER = register(PCEntityIds.END_TROLL_BULLET_WITHER, PCEntityBootstrap.endTrollBulletWitherType());
    public static final EntityType<HellhoundEntity> HELLHOUND = register(PCEntityIds.HELLHOUND, PCEntityBootstrap.hellhoundType());
    public static final EntityType<SeahorseEntity> SEAHORSE = register(PCEntityIds.SEAHORSE, PCEntityBootstrap.seahorseType());

    private static boolean initialized;

    private PCFabricEntities() {
    }

    public static void register() {
        if (initialized) {
            return;
        }
        initialized = true;

        FabricDefaultAttributeRegistry.register(ACIDIC_ARCHVINE, PCEntityBootstrap.acidicArchvineAttributes());
        FabricDefaultAttributeRegistry.register(ARACHNON, PCEntityBootstrap.arachnonAttributes());
        FabricDefaultAttributeRegistry.register(BUFFLON, PCEntityBootstrap.bufflonAttributes());
        FabricDefaultAttributeRegistry.register(CRAB, PCEntityBootstrap.crabAttributes());
        FabricDefaultAttributeRegistry.register(END_TROLL, PCEntityBootstrap.endTrollAttributes());
        FabricDefaultAttributeRegistry.register(HELLHOUND, PCEntityBootstrap.hellhoundAttributes());
        FabricDefaultAttributeRegistry.register(SEAHORSE, PCEntityBootstrap.seahorseAttributes());

        SpawnPlacements.register(
                ACIDIC_ARCHVINE,
                SpawnPlacementTypes.NO_RESTRICTIONS,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) -> PCEntitySpawnRules.canSpawnAcidicArchvine(
                        level.getDifficulty() != Difficulty.PEACEFUL,
                        PCEntitySpawnRules.isJungleArchvineBiome(level, pos),
                        PCEntitySpawnRules.isNetherArchvineBiome(level, pos),
                        pos.getY(),
                        level.getBlockState(pos).isAir(),
                        level.getBlockState(pos.above()).isAir(),
                        PCEntitySpawnRules.hasValidAcidicArchvineCeiling(level, pos),
                        PCEntitySpawnRules.hasConsecutiveAirBelow(level, pos, PCEntitySpawnRules.acidicArchvineRequiredAirDepth())
                )
        );

        SpawnPlacements.register(
                ARACHNON,
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) -> PCEntitySpawnRules.canSpawnArachnon(level.getDifficulty() != Difficulty.PEACEFUL, level.getRawBrightness(pos, 0))
        );

        SpawnPlacements.register(
                HELLHOUND,
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) -> PCEntitySpawnRules.canSpawnHostileGroundMob(level.getDifficulty() != Difficulty.PEACEFUL)
        );

        SpawnPlacements.register(
                SEAHORSE,
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) -> PCEntitySpawnRules.canSpawnSeahorse(level.getFluidState(pos).is(FluidTags.WATER))
        );

        SpawnPlacements.register(
                CRAB,
                SpawnPlacementTypes.NO_RESTRICTIONS,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) -> PCEntitySpawnRules.canSpawnCrab(
                        isBiome(level, pos, Biomes.BEACH),
                        isBiome(level, pos, Biomes.WARM_OCEAN),
                        pos.getY(),
                        level.getBlockState(pos.below()).is(Blocks.SAND),
                        level.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK)
                )
        );

        registerBiomeSpawns();
    }

    private static boolean isBiome(LevelAccessor level, BlockPos pos, net.minecraft.resources.ResourceKey<net.minecraft.world.level.biome.Biome> biome) {
        return level.getBiome(pos).is(biome);
    }

    private static void registerBiomeSpawns() {
        for (PCBiomeSpawnCatalog.SpawnDefinition definition : PCBiomeSpawnCatalog.definitions()) {
            EntityType<?> entityType = BuiltInRegistries.ENTITY_TYPE.getOptional(Identifier.parse(definition.entityTypeId()))
                    .orElseThrow(() -> new IllegalStateException("Unknown Fabric entity id for biome spawn: " + definition.entityTypeId()));
            BiomeModifications.addSpawn(
                    toBiomeSelector(definition.biomes()),
                    entityType.getCategory(),
                    entityType,
                    definition.weight(),
                    definition.minCount(),
                    definition.maxCount()
            );
        }
    }

    private static Predicate<BiomeSelectionContext> toBiomeSelector(List<String> biomeSelectors) {
        if (biomeSelectors.size() == 1 && biomeSelectors.getFirst().startsWith("#")) {
            String selector = biomeSelectors.getFirst().substring(1);
            TagKey<Biome> tag = TagKey.create(Registries.BIOME, Identifier.parse(selector));
            return BiomeSelectors.tag(tag);
        }

        List<ResourceKey<Biome>> biomeKeys = biomeSelectors.stream()
                .map(Identifier::parse)
                .map(id -> ResourceKey.create(Registries.BIOME, id))
                .toList();
        return BiomeSelectors.includeByKey(biomeKeys);
    }

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder) {
        return Registry.register(
                BuiltInRegistries.ENTITY_TYPE,
                PCEntityTypeFactory.entityIdentifier(id),
                builder.build(PCEntityTypeFactory.entityKey(id))
        );
    }
}
