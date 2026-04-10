package andrews.pandoras_creatures.registry.entity;

import andrews.pandoras_creatures.entities.ArachnonEntity;
import andrews.pandoras_creatures.entities.AcidicArchvineEntity;
import andrews.pandoras_creatures.entities.CrabEntity;
import andrews.pandoras_creatures.entities.EndTrollEntity;
import andrews.pandoras_creatures.entities.HellhoundEntity;
import andrews.pandoras_creatures.entities.SeahorseEntity;
import andrews.pandoras_creatures.entities.BufflonEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.WaterAnimal;

/**
 * Shared entity definitions for the first multiloader-ready entity batch.
 */
public final class PCEntityBootstrap {
    private PCEntityBootstrap() {
    }

    public static EntityType.Builder<ArachnonEntity> arachnonType() {
        return PCEntityTypeFactory.living(ArachnonEntity::new, MobCategory.MONSTER, 2.8F, 1.8F);
    }

    public static EntityType.Builder<AcidicArchvineEntity> acidicArchvineType() {
        return PCEntityTypeFactory.living(AcidicArchvineEntity::new, MobCategory.MONSTER, 1.0F, 1.5F);
    }

    public static EntityType.Builder<andrews.pandoras_creatures.entities.projectiles.EndTrollBulletDamageEntity> endTrollBulletDamageType() {
        return PCEntityTypeFactory.projectile(andrews.pandoras_creatures.entities.projectiles.EndTrollBulletDamageEntity::new, MobCategory.MISC, 0.3125F, 0.3125F);
    }

    public static EntityType.Builder<andrews.pandoras_creatures.entities.projectiles.EndTrollBulletPoisonEntity> endTrollBulletPoisonType() {
        return PCEntityTypeFactory.projectile(andrews.pandoras_creatures.entities.projectiles.EndTrollBulletPoisonEntity::new, MobCategory.MISC, 0.3125F, 0.3125F);
    }

    public static EntityType.Builder<andrews.pandoras_creatures.entities.projectiles.EndTrollBulletWitherEntity> endTrollBulletWitherType() {
        return PCEntityTypeFactory.projectile(andrews.pandoras_creatures.entities.projectiles.EndTrollBulletWitherEntity::new, MobCategory.MISC, 0.3125F, 0.3125F);
    }

    public static EntityType.Builder<EndTrollEntity> endTrollType() {
        return PCEntityTypeFactory.living(EndTrollEntity::new, MobCategory.MONSTER, 3.0F, 3.6F);
    }

    public static EntityType.Builder<CrabEntity> crabType() {
        return PCEntityTypeFactory.living(CrabEntity::new, MobCategory.AMBIENT, 0.8F, 0.3F);
    }

    public static EntityType.Builder<HellhoundEntity> hellhoundType() {
        return PCEntityTypeFactory.fireImmuneLiving(HellhoundEntity::new, MobCategory.MONSTER, 1.0F, 1.2F);
    }

    public static EntityType.Builder<SeahorseEntity> seahorseType() {
        return PCEntityTypeFactory.living(SeahorseEntity::new, MobCategory.AMBIENT, 0.4F, 0.8F);
    }

    public static EntityType.Builder<BufflonEntity> bufflonType() {
        return PCEntityTypeFactory.living(BufflonEntity::new, MobCategory.CREATURE, 2.4F, 3.0F);
    }

    public static AttributeSupplier.Builder arachnonAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 80.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.2D);
    }

    public static AttributeSupplier.Builder acidicArchvineAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.FOLLOW_RANGE, 50.0D);
    }

    public static AttributeSupplier.Builder crabAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0D);
    }

    public static AttributeSupplier.Builder hellhoundAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 14.0D);
    }

    public static AttributeSupplier.Builder bufflonAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 60.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.55D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D);
    }

    public static AttributeSupplier.Builder endTrollAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 200.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.FOLLOW_RANGE, 30.0D);
    }

    public static AttributeSupplier.Builder seahorseAttributes() {
        return WaterAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 3.0D);
    }
}
