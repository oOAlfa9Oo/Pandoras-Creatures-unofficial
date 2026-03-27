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
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.registry.entity.PCEntityTypeFactory;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class PCEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, Reference.MODID);

    // Living Entities
    public static final DeferredHolder<EntityType<?>, EntityType<ArachnonEntity>> ARACHNON =
            register(PCEntityIds.ARACHNON, () -> PCEntityTypeFactory.living(ArachnonEntity::new, MobCategory.MONSTER, 2.8F, 1.8F));

    public static final DeferredHolder<EntityType<?>, EntityType<HellhoundEntity>> HELLHOUND =
            register(PCEntityIds.HELLHOUND, () -> PCEntityTypeFactory.fireImmuneLiving(HellhoundEntity::new, MobCategory.MONSTER, 1.0F, 1.2F));

    public static final DeferredHolder<EntityType<?>, EntityType<CrabEntity>> CRAB =
            register(PCEntityIds.CRAB, () -> PCEntityTypeFactory.living(CrabEntity::new, MobCategory.AMBIENT, 0.8F, 0.3F));

    public static final DeferredHolder<EntityType<?>, EntityType<SeahorseEntity>> SEAHORSE =
            register(PCEntityIds.SEAHORSE, () -> PCEntityTypeFactory.living(SeahorseEntity::new, MobCategory.AMBIENT, 0.4F, 0.8F));

    public static final DeferredHolder<EntityType<?>, EntityType<AcidicArchvineEntity>> ACIDIC_ARCHVINE =
            register(PCEntityIds.ACIDIC_ARCHVINE, () -> PCEntityTypeFactory.living(AcidicArchvineEntity::new, MobCategory.MONSTER, 1.0F, 1.5F));

    public static final DeferredHolder<EntityType<?>, EntityType<BufflonEntity>> BUFFLON =
            register(PCEntityIds.BUFFLON, () -> PCEntityTypeFactory.living(BufflonEntity::new, MobCategory.CREATURE, 2.4F, 3.0F));

    public static final DeferredHolder<EntityType<?>, EntityType<EndTrollEntity>> END_TROLL =
            register(PCEntityIds.END_TROLL, () -> PCEntityTypeFactory.living(EndTrollEntity::new, MobCategory.MONSTER, 3.0F, 3.6F));

    // Projectile Entities
    public static final DeferredHolder<EntityType<?>, EntityType<EndTrollBulletDamageEntity>> END_TROLL_BULLET_DAMAGE =
            register(PCEntityIds.END_TROLL_BULLET_DAMAGE, () -> PCEntityTypeFactory.projectile(EndTrollBulletDamageEntity::new, MobCategory.MISC, 0.3125F, 0.3125F));

    public static final DeferredHolder<EntityType<?>, EntityType<EndTrollBulletPoisonEntity>> END_TROLL_BULLET_POISON =
            register(PCEntityIds.END_TROLL_BULLET_POISON, () -> PCEntityTypeFactory.projectile(EndTrollBulletPoisonEntity::new, MobCategory.MISC, 0.3125F, 0.3125F));

    public static final DeferredHolder<EntityType<?>, EntityType<EndTrollBulletWitherEntity>> END_TROLL_BULLET_WITHER =
            register(PCEntityIds.END_TROLL_BULLET_WITHER, () -> PCEntityTypeFactory.projectile(EndTrollBulletWitherEntity::new, MobCategory.MISC, 0.3125F, 0.3125F));

    private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String name, Supplier<EntityType.Builder<T>> builderSupplier) {
        return ENTITY_TYPES.register(name, () -> builderSupplier.get().build(PCEntityTypeFactory.entityId(name)));
    }
}
