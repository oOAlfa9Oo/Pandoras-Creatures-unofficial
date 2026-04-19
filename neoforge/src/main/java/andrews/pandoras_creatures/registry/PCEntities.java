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
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class PCEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, Reference.MODID);

    // Living Entities
    public static final DeferredHolder<EntityType<?>, EntityType<ArachnonEntity>> ARACHNON =
            register(PCEntityIds.ARACHNON, PCEntityBootstrap::arachnonType);

    public static final DeferredHolder<EntityType<?>, EntityType<HellhoundEntity>> HELLHOUND =
            register(PCEntityIds.HELLHOUND, PCEntityBootstrap::hellhoundType);

    public static final DeferredHolder<EntityType<?>, EntityType<CrabEntity>> CRAB =
            register(PCEntityIds.CRAB, PCEntityBootstrap::crabType);

    public static final DeferredHolder<EntityType<?>, EntityType<SeahorseEntity>> SEAHORSE =
            register(PCEntityIds.SEAHORSE, PCEntityBootstrap::seahorseType);

    public static final DeferredHolder<EntityType<?>, EntityType<AcidicArchvineEntity>> ACIDIC_ARCHVINE =
            register(PCEntityIds.ACIDIC_ARCHVINE, PCEntityBootstrap::acidicArchvineType);

    public static final DeferredHolder<EntityType<?>, EntityType<BufflonEntity>> BUFFLON =
            register(PCEntityIds.BUFFLON, PCEntityBootstrap::bufflonType);

    public static final DeferredHolder<EntityType<?>, EntityType<EndTrollEntity>> END_TROLL =
            register(PCEntityIds.END_TROLL, PCEntityBootstrap::endTrollType);

    // Projectile Entities
    public static final DeferredHolder<EntityType<?>, EntityType<EndTrollBulletDamageEntity>> END_TROLL_BULLET_DAMAGE =
            register(PCEntityIds.END_TROLL_BULLET_DAMAGE, PCEntityBootstrap::endTrollBulletDamageType);

    public static final DeferredHolder<EntityType<?>, EntityType<EndTrollBulletPoisonEntity>> END_TROLL_BULLET_POISON =
            register(PCEntityIds.END_TROLL_BULLET_POISON, PCEntityBootstrap::endTrollBulletPoisonType);

    public static final DeferredHolder<EntityType<?>, EntityType<EndTrollBulletWitherEntity>> END_TROLL_BULLET_WITHER =
            register(PCEntityIds.END_TROLL_BULLET_WITHER, PCEntityBootstrap::endTrollBulletWitherType);

    private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String name, Supplier<EntityType.Builder<T>> builderSupplier) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Reference.MODID, name));
        return ENTITY_TYPES.register(name, () -> builderSupplier.get().build(key));
    }
}
