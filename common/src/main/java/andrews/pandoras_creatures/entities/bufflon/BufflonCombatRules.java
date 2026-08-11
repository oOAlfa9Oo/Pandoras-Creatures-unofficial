package andrews.pandoras_creatures.entities.bufflon;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.phys.Vec3;

/**
 * Central combat rules for Bufflon combat state, damage and ally-safe target selection.
 */
public final class BufflonCombatRules {
    private BufflonCombatRules() {
    }

    public static boolean canProtectOwner(boolean isTamed, boolean isSitting, boolean inCombatMode) {
        return BufflonCombatPolicy.canProtectOwner(isTamed, isSitting, inCombatMode);
    }

    public static boolean shouldInterruptSitOnDamage(boolean inCombatMode) {
        return BufflonCombatPolicy.shouldInterruptSitOnDamage(inCombatMode);
    }

    public static float getAdjustedIncomingDamage(Entity attacker, float amount) {
        return BufflonCombatPolicy.getAdjustedIncomingDamage(attacker != null, attacker instanceof Player, attacker instanceof AbstractArrow, amount);
    }

    public static float getAdjustedIncomingDamage(boolean hasAttacker, boolean attackerIsPlayer, boolean attackerIsArrow, float amount) {
        return BufflonCombatPolicy.getAdjustedIncomingDamage(hasAttacker, attackerIsPlayer, attackerIsArrow, amount);
    }

    public static Vec3 getAttackKnockback(Vec3 attackerPosition, Vec3 targetPosition, double strength) {
        BufflonCombatKnockback knockback = BufflonCombatPolicy.getAttackKnockback(
                attackerPosition.x, attackerPosition.y, attackerPosition.z,
                targetPosition.x, targetPosition.y, targetPosition.z,
                strength
        );
        return new Vec3(knockback.x(), knockback.y(), knockback.z());
    }

    public static boolean shouldAttackTarget(BufflonCombatTargetProfile profile) {
        return BufflonCombatPolicy.shouldAttackTarget(profile);
    }

    public static BufflonCombatTargetProfile describeTarget(LivingEntity target, LivingEntity owner, boolean alliedTamedBufflon) {
        return new BufflonCombatTargetProfile(
                target instanceof Creeper || target instanceof Ghast,
                target instanceof Wolf wolf && wolf.isTame() && wolf.getOwner() == owner,
                alliedTamedBufflon,
                target instanceof Player targetPlayer && owner instanceof Player ownerPlayer && !ownerPlayer.canHarmPlayer(targetPlayer),
                target instanceof AbstractHorse horse && horse.isTamed(),
                target instanceof Cat cat && cat.isTame()
        );
    }
}
