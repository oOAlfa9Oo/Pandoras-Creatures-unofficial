package andrews.pandoras_creatures.entities.end_troll;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public final class EndTrollBehaviorRules {
    public static final int DEFAULT_SHOOT_COOLDOWN = 300;
    public static final int DEFAULT_SCREAM_COOLDOWN = 400;
    public static final int PROJECTILES_PER_VOLLEY = 5;
    public static final int PUNCH_SOUND_TICK = 4;
    public static final int PUNCH_DAMAGE_TICK = 10;
    public static final int SCREAM_SOUND_TICK = 7;
    public static final int SCREAM_PARTICLE_TICK = 12;
    public static final int SCREAM_IMPACT_TICK = 16;

    private static final int CHORUS_BREAK_INTERVAL = 10;
    private static final double TRANSFORM_RANGE_SQR = 150.0D;
    private static final double SCREAM_RANGE = 10.0D;
    private static final int SINGLE_PUNCH_BASE_DAMAGE = 12;
    private static final int DOUBLE_PUNCH_BASE_DAMAGE = 14;

    private EndTrollBehaviorRules() {
    }

    public static boolean shouldTryTransform(boolean isStanding, boolean hasTarget, double distanceToTargetSqr) {
        return !isStanding && hasTarget && distanceToTargetSqr < TRANSFORM_RANGE_SQR;
    }

    public static boolean hasValidCombatTarget(boolean hasTarget, boolean targetAlive, boolean creativeOrSpectator) {
        return hasTarget && targetAlive && !creativeOrSpectator;
    }

    public static boolean isValidCombatTarget(LivingEntity target) {
        boolean creativeOrSpectator = target instanceof Player player && (player.isCreative() || player.isSpectator());
        return hasValidCombatTarget(target != null, target != null && target.isAlive(), creativeOrSpectator);
    }

    public static boolean shouldContinueMeleeAttack(boolean hasValidTarget,
            boolean longMemory,
            boolean navigationDone,
            boolean withinRestriction,
            boolean punchAnimationPlaying,
            boolean withinAttackReach) {
        if (!hasValidTarget) {
            return false;
        }
        if (punchAnimationPlaying || withinAttackReach) {
            return true;
        }
        if (!longMemory) {
            return !navigationDone;
        }
        return withinRestriction;
    }

    public static boolean shouldTryScream(boolean hostileDifficulty,
            boolean hasAliveTarget,
            boolean blankAnimation,
            boolean serverSide,
            boolean navigationDone,
            double distanceToTarget,
            int screamCooldown) {
        return hostileDifficulty
                && hasAliveTarget
                && blankAnimation
                && serverSide
                && !navigationDone
                && distanceToTarget < SCREAM_RANGE
                && screamCooldown == 0;
    }

    public static boolean shouldTryShoot(boolean hostileDifficulty,
            boolean hasAliveTarget,
            boolean blockedByAnimation,
            boolean serverSide,
            int shootCooldown) {
        return hostileDifficulty
                && hasAliveTarget
                && !blockedByAnimation
                && serverSide
                && shootCooldown == 0;
    }

    public static int tickCooldown(int cooldown) {
        return Math.max(cooldown - 1, 0);
    }

    public static int getPunchDamage(boolean doublePunch, int randomBonus) {
        return (doublePunch ? DOUBLE_PUNCH_BASE_DAMAGE : SINGLE_PUNCH_BASE_DAMAGE) + randomBonus;
    }

    public static EndTrollPunchAnimation selectPunchAnimation(int randomIndex) {
        return switch (Math.floorMod(randomIndex, 3)) {
            case 0 -> EndTrollPunchAnimation.RIGHT;
            case 1 -> EndTrollPunchAnimation.LEFT;
            default -> EndTrollPunchAnimation.DOUBLE;
        };
    }

    public static boolean shouldBreakChorusThisTick(int tickCount) {
        return tickCount % CHORUS_BREAK_INTERVAL == 0;
    }

    public static boolean shouldSpawnScreamExplosion(int animationTick) {
        return animationTick == SCREAM_PARTICLE_TICK;
    }

    public static boolean shouldApplyScreamImpact(int animationTick) {
        return animationTick == SCREAM_IMPACT_TICK;
    }

    public static boolean shouldPlayScreamSound(int animationTick) {
        return animationTick == SCREAM_SOUND_TICK;
    }

    public static boolean shouldPlayPunchSound(int animationTick) {
        return animationTick == PUNCH_SOUND_TICK;
    }

    public static boolean shouldResolvePunchDamage(int animationTick, boolean hasPerformedAttackLogic) {
        return !hasPerformedAttackLogic && animationTick >= PUNCH_DAMAGE_TICK;
    }

    public static boolean shouldLaunchFallingBlock(boolean hasBlockEntity, int randomRollOneToFour) {
        return !hasBlockEntity && randomRollOneToFour == 4;
    }

    public static boolean shouldDropDestroyedBlock(boolean hasBlockEntity, int randomRollOneToThree) {
        return hasBlockEntity || randomRollOneToThree == 3;
    }
}
