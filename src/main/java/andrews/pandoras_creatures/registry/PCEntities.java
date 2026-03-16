package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.entities.*;
import andrews.pandoras_creatures.entities.projectiles.*;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PCEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, Reference.MODID);

    // Living Entities
    public static final DeferredHolder<EntityType<?>, EntityType<ArachnonEntity>> ARACHNON =
            ENTITY_TYPES.register("arachnon", () -> EntityType.Builder.<ArachnonEntity>of(ArachnonEntity::new, MobCategory.MONSTER)
                    .sized(2.8F, 1.8F)
                    .clientTrackingRange(64)
                    .updateInterval(3)
                    .build(ResourceLocation.fromNamespaceAndPath(Reference.MODID, "arachnon").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<HellhoundEntity>> HELLHOUND =
            ENTITY_TYPES.register("hellhound", () -> EntityType.Builder.<HellhoundEntity>of(HellhoundEntity::new, MobCategory.MONSTER)
                    .sized(1.0F, 1.2F)
                    .fireImmune()
                    .clientTrackingRange(64)
                    .updateInterval(3)
                    .build(ResourceLocation.fromNamespaceAndPath(Reference.MODID, "hellhound").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<CrabEntity>> CRAB =
            ENTITY_TYPES.register("crab", () -> EntityType.Builder.<CrabEntity>of(CrabEntity::new, MobCategory.AMBIENT)
                    .sized(0.8F, 0.3F)
                    .clientTrackingRange(64)
                    .updateInterval(3)
                    .build(ResourceLocation.fromNamespaceAndPath(Reference.MODID, "crab").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<SeahorseEntity>> SEAHORSE =
            ENTITY_TYPES.register("seahorse", () -> EntityType.Builder.<SeahorseEntity>of(SeahorseEntity::new, MobCategory.AMBIENT)
                    .sized(0.4F, 0.8F)
                    .clientTrackingRange(64)
                    .updateInterval(3)
                    .build(ResourceLocation.fromNamespaceAndPath(Reference.MODID, "seahorse").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<AcidicArchvineEntity>> ACIDIC_ARCHVINE =
            ENTITY_TYPES.register("acidic_archvine", () -> EntityType.Builder.<AcidicArchvineEntity>of(AcidicArchvineEntity::new, MobCategory.MONSTER)
                    .sized(1.0F, 1.5F)
                    .clientTrackingRange(64)
                    .updateInterval(3)
                    .build(ResourceLocation.fromNamespaceAndPath(Reference.MODID, "acidic_archvine").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<BufflonEntity>> BUFFLON =
            ENTITY_TYPES.register("bufflon", () -> EntityType.Builder.<BufflonEntity>of(BufflonEntity::new, MobCategory.CREATURE)
                    .sized(2.4F, 3.0F)
                    .clientTrackingRange(64)
                    .updateInterval(3)
                    .build(ResourceLocation.fromNamespaceAndPath(Reference.MODID, "bufflon").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<EndTrollEntity>> END_TROLL =
            ENTITY_TYPES.register("end_troll", () -> EntityType.Builder.<EndTrollEntity>of(EndTrollEntity::new, MobCategory.MONSTER)
                    .sized(3.0F, 3.6F)
                    .clientTrackingRange(64)
                    .updateInterval(3)
                    .build(ResourceLocation.fromNamespaceAndPath(Reference.MODID, "end_troll").toString()));

    // Projectile Entities
    public static final DeferredHolder<EntityType<?>, EntityType<EndTrollBulletDamageEntity>> END_TROLL_BULLET_DAMAGE =
            ENTITY_TYPES.register("end_troll_bullet_damage", () -> EntityType.Builder.<EndTrollBulletDamageEntity>of(EndTrollBulletDamageEntity::new, MobCategory.MISC)
                    .sized(0.3125F, 0.3125F)
                    .clientTrackingRange(64)
                    .updateInterval(1)
                    .build(ResourceLocation.fromNamespaceAndPath(Reference.MODID, "end_troll_bullet_damage").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<EndTrollBulletPoisonEntity>> END_TROLL_BULLET_POISON =
            ENTITY_TYPES.register("end_troll_bullet_poison", () -> EntityType.Builder.<EndTrollBulletPoisonEntity>of(EndTrollBulletPoisonEntity::new, MobCategory.MISC)
                    .sized(0.3125F, 0.3125F)
                    .clientTrackingRange(64)
                    .updateInterval(1)
                    .build(ResourceLocation.fromNamespaceAndPath(Reference.MODID, "end_troll_bullet_poison").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<EndTrollBulletWitherEntity>> END_TROLL_BULLET_WITHER =
            ENTITY_TYPES.register("end_troll_bullet_wither", () -> EntityType.Builder.<EndTrollBulletWitherEntity>of(EndTrollBulletWitherEntity::new, MobCategory.MISC)
                    .sized(0.3125F, 0.3125F)
                    .clientTrackingRange(64)
                    .updateInterval(1)
                    .build(ResourceLocation.fromNamespaceAndPath(Reference.MODID, "end_troll_bullet_wither").toString()));

    /**
     * Register entity attributes - called from EntityAttributeCreationEvent
     * Matches original 1.16.5 attributes exactly
     */
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        // Arachnon - Boss-like spider creature (original: HP=80, KNOCKBACK=0.2)
        event.put(ARACHNON.get(), createAttributes()
                .add(Attributes.MAX_HEALTH, 80.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.2D)
                .build());

        // Hellhound - Nether creature (original: HP=14)
        event.put(HELLHOUND.get(), createAttributes()
                .add(Attributes.MAX_HEALTH, 14.0D)
                .build());

        // Crab - Passive ambient mob (original: HP=6)
        event.put(CRAB.get(), createAttributes()
                .add(Attributes.MAX_HEALTH, 6.0D)
                .build());

        // Seahorse - Water ambient mob (original: HP=3)
        event.put(SEAHORSE.get(), createAttributes()
                .add(Attributes.MAX_HEALTH, 3.0D)
                .build());

        // Acidic Archvine - Stationary monster (original: HP=20, KNOCKBACK=1.0, FOLLOW=30; adjusted to 36 for 1.21.1 test)
        event.put(ACIDIC_ARCHVINE.get(), createAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.FOLLOW_RANGE, 36.0D)
                .build());

        // Bufflon - Tameable mount (original: HP=60, SPEED=0.55, ATTACK=10)
        event.put(BUFFLON.get(), createAttributes()
                .add(Attributes.MAX_HEALTH, 60.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.55D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .build());

        // End Troll - Boss creature (original: HP=200, KNOCKBACK=1.0, FOLLOW=30)
        event.put(END_TROLL.get(), createAttributes()
                .add(Attributes.MAX_HEALTH, 200.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.FOLLOW_RANGE, 30.0D)
                .build());
    }

    private static AttributeSupplier.Builder createAttributes() {
        // Mob.createMobAttributes() is required - it includes FOLLOW_RANGE (16.0) and ATTACK_KNOCKBACK (0.0).
        // Using LivingEntity.createLivingAttributes() alone is missing FOLLOW_RANGE, which causes
        // NearestAttackableTargetGoal to throw IllegalArgumentException during entity construction,
        // preventing the spawn egg from spawning the entity.
        return Mob.createMobAttributes();
    }

    /**
     * Register spawn placements - called from RegisterSpawnPlacementsEvent
     */
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        // Water creatures
        event.register(SEAHORSE.get(),
                net.minecraft.world.entity.SpawnPlacementTypes.IN_WATER,
                net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) ->
                    level.getFluidState(pos).is(net.minecraft.tags.FluidTags.WATER),
                RegisterSpawnPlacementsEvent.Operation.REPLACE);

        // Crab: NO_RESTRICTIONS - spawns on sand (beach/warm ocean) or grass, not strictly in water
        event.register(CRAB.get(),
                net.minecraft.world.entity.SpawnPlacementTypes.NO_RESTRICTIONS,
                net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) -> {
                    if (level.getBiome(pos).is(Biomes.BEACH) && (pos.getY() > 70 || pos.getY() < 56)) {
                        return false;
                    }
                    if (level.getBiome(pos).is(Biomes.WARM_OCEAN) && (pos.getY() > 60 || pos.getY() < 30)) {
                        return false;
                    }
                    return level.getBlockState(pos.below()).is(Blocks.SAND) ||
                            level.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK);
                },
                RegisterSpawnPlacementsEvent.Operation.REPLACE);

        // Land creatures - monsters
        // Hellhound: spawns in Nether (nether_wastes, soul_sand_valley) - only difficulty check
        event.register(HELLHOUND.get(),
                net.minecraft.world.entity.SpawnPlacementTypes.ON_GROUND,
                net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) ->
                    level.getDifficulty() != Difficulty.PEACEFUL,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);

        event.register(ARACHNON.get(),
                net.minecraft.world.entity.SpawnPlacementTypes.ON_GROUND,
                net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) ->
                    level.getDifficulty() != Difficulty.PEACEFUL &&
                    level.getRawBrightness(pos, 0) <= 7,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);

        // AcidicArchvine: NO_RESTRICTIONS - must hang from jungle leaves or netherrack ceiling
        // with at least 8 air blocks below (vertical shaft)
        event.register(ACIDIC_ARCHVINE.get(),
                net.minecraft.world.entity.SpawnPlacementTypes.NO_RESTRICTIONS,
                net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) -> {
                    if (level.getDifficulty() == Difficulty.PEACEFUL) return false;
                    if ((level.getBiome(pos).is(Biomes.JUNGLE)
                            || level.getBiome(pos).is(Biomes.BAMBOO_JUNGLE)
                            || level.getBiome(pos).is(Biomes.SPARSE_JUNGLE))
                            && pos.getY() < 62) {
                        return false;
                    }
                    if ((level.getBiome(pos).is(Biomes.NETHER_WASTES)
                            || level.getBiome(pos).is(Biomes.CRIMSON_FOREST))
                            && pos.getY() < 40) {
                        return false;
                    }
                    if (!level.getBlockState(pos).isAir()) return false;
                    if (!level.getBlockState(pos.above()).isAir()) return false;
                    net.minecraft.world.level.block.state.BlockState ceiling = level.getBlockState(pos.above(2));
                    if (!ceiling.is(Blocks.JUNGLE_LEAVES) && !ceiling.is(Blocks.NETHERRACK)) return false;
                    // Require 8 consecutive air blocks below
                    for (int i = 0; i < 8; i++) {
                        if (!level.getBlockState(pos.below(i)).isAir()) return false;
                    }
                    return true;
                },
                RegisterSpawnPlacementsEvent.Operation.REPLACE);

        // Land creatures - peaceful
        // Bufflon: spawns in snowy biomes, needs light and grass block
        event.register(BUFFLON.get(),
                net.minecraft.world.entity.SpawnPlacementTypes.ON_GROUND,
                net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) ->
                    level.getRawBrightness(pos, 0) > 8 &&
                    level.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK),
                RegisterSpawnPlacementsEvent.Operation.REPLACE);

        event.register(END_TROLL.get(),
                net.minecraft.world.entity.SpawnPlacementTypes.ON_GROUND,
                net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) -> true,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }
}
