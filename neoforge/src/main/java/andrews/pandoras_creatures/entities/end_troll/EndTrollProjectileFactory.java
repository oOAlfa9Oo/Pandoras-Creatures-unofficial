package andrews.pandoras_creatures.entities.end_troll;

import andrews.pandoras_creatures.entities.EndTrollEntity;
import andrews.pandoras_creatures.entities.projectiles.AbstractEndTrollBulletEntity;
import andrews.pandoras_creatures.entities.projectiles.EndTrollBulletDamageEntity;
import andrews.pandoras_creatures.entities.projectiles.EndTrollBulletPoisonEntity;
import andrews.pandoras_creatures.entities.projectiles.EndTrollBulletWitherEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public final class EndTrollProjectileFactory {
    private EndTrollProjectileFactory() {
    }

    public static AbstractEndTrollBulletEntity createProjectile(Level level,
            EndTrollEntity owner,
            LivingEntity target,
            Direction.Axis directionAxis,
            EndTrollProjectileKind projectileKind,
            EndTrollProjectileLaunch launch) {
        AbstractEndTrollBulletEntity projectile = switch (projectileKind) {
            case POISON -> new EndTrollBulletPoisonEntity(level, owner, target, directionAxis);
            case WITHER -> new EndTrollBulletWitherEntity(level, owner, target, directionAxis);
            case DAMAGE -> new EndTrollBulletDamageEntity(level, owner, target, directionAxis);
        };
        projectile.push(launch.x(), launch.y(), launch.z());
        return projectile;
    }
}
