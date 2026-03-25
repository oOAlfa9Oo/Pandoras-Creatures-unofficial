package andrews.pandoras_creatures.entities.goals.end_troll;

import andrews.pandoras_creatures.entities.EndTrollEntity;
import andrews.pandoras_creatures.entities.end_troll.EndTrollBehaviorRules;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.Path;

import java.util.EnumSet;

public class EndTrollAttackGoal extends Goal {
    protected final EndTrollEntity attacker;
    protected int attackTick;
    private final double speedTowardsTarget;
    private final boolean longMemory;
    private Path path;
    private int delayCounter;
    private double targetX;
    private double targetY;
    private double targetZ;
    protected final int attackInterval = 20;
    private long lastCanUseCheck;
    private int failedPathFindingPenalty = 0;
    private boolean canPenalize = false;
    private boolean hasPerformedAttackLogic = false;
    private final RandomSource rand;

    public EndTrollAttackGoal(EndTrollEntity creature, double speedIn, boolean useLongMemory) {
        this.attacker = creature;
        this.speedTowardsTarget = speedIn;
        this.longMemory = useLongMemory;
        this.rand = creature.getRandom();
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (attacker.level().getDifficulty() == Difficulty.PEACEFUL) {
            return false;
        }
        long i = this.attacker.level().getGameTime();
        if (i - this.lastCanUseCheck < 20L) {
            return false;
        } else {
            this.lastCanUseCheck = i;
            LivingEntity livingentity = this.attacker.getTarget();
            if (!EndTrollBehaviorRules.isValidCombatTarget(livingentity)) {
                return false;
            } else {
                if (canPenalize) {
                    if (--this.delayCounter <= 0) {
                        this.path = this.attacker.getNavigation().createPath(livingentity, 0);
                        this.delayCounter = 4 + this.attacker.getRandom().nextInt(7);
                        return this.path != null;
                    } else {
                        return true;
                    }
                }
                this.path = this.attacker.getNavigation().createPath(livingentity, 0);
                if (this.path != null) {
                    return true;
                } else {
                    return this.getAttackReachSqr(livingentity) >= this.attacker.distanceToSqr(livingentity.getX(), livingentity.getBoundingBox().minY, livingentity.getZ());
                }
            }
        }
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity livingentity = this.attacker.getTarget();
        boolean hasValidTarget = EndTrollBehaviorRules.isValidCombatTarget(livingentity);
        boolean withinRestriction = hasValidTarget && this.attacker.isWithinRestriction(livingentity.blockPosition());
        boolean withinAttackReach = hasValidTarget
                && this.attacker.distanceToSqr(livingentity.getX(), livingentity.getBoundingBox().minY, livingentity.getZ()) <= this.getAttackReachSqr(livingentity);
        return EndTrollBehaviorRules.shouldContinueMeleeAttack(
                hasValidTarget,
                this.longMemory,
                this.attacker.getNavigation().isDone(),
                withinRestriction,
                this.attacker.isAnyPunchAnimationPlaying(),
                withinAttackReach
        );
    }

    @Override
    public void start() {
        this.attacker.getNavigation().moveTo(this.path, this.speedTowardsTarget);
        this.attacker.setAggressive(true);
        this.delayCounter = 0;
        this.attackTick = 0;
    }

    @Override
    public void stop() {
        LivingEntity livingentity = this.attacker.getTarget();
        if (!EndTrollBehaviorRules.isValidCombatTarget(livingentity)) {
            this.attacker.setTarget(null);
            if (this.attacker.isAnyPunchAnimationPlaying()) {
                this.attacker.resetAnimation();
            }
        }

        this.attacker.setAggressive(false);
        this.attacker.getNavigation().stop();
        this.path = null;
        this.delayCounter = 0;
        this.targetX = 0.0D;
        this.targetY = 0.0D;
        this.targetZ = 0.0D;
        this.attackTick = 0;
        this.failedPathFindingPenalty = 0;
        this.hasPerformedAttackLogic = false;
    }

    @Override
    public void tick() {
        if (!this.attacker.isAnyPunchAnimationPlaying()) {

            if (this.hasPerformedAttackLogic) {
                this.hasPerformedAttackLogic = false;
            }

            LivingEntity livingentity = this.attacker.getTarget();
            if (!EndTrollBehaviorRules.isValidCombatTarget(livingentity)) return;

            this.attacker.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
            double d0 = this.attacker.distanceToSqr(livingentity.getX(), livingentity.getBoundingBox().minY, livingentity.getZ());
            --this.delayCounter;
            if ((this.longMemory || this.attacker.getSensing().hasLineOfSight(livingentity)) && this.delayCounter <= 0 &&
                (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D ||
                 livingentity.distanceToSqr(this.targetX, this.targetY, this.targetZ) >= 1.0D ||
                 this.attacker.getRandom().nextFloat() < 0.05F)) {

                this.targetX = livingentity.getX();
                this.targetY = livingentity.getBoundingBox().minY;
                this.targetZ = livingentity.getZ();
                this.delayCounter = 4 + this.attacker.getRandom().nextInt(7);
                if (this.canPenalize) {
                    this.delayCounter += failedPathFindingPenalty;
                    if (this.attacker.getNavigation().getPath() != null) {
                        Node finalPathPoint = this.attacker.getNavigation().getPath().getEndNode();
                        if (finalPathPoint != null && livingentity.distanceToSqr(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
                            failedPathFindingPenalty = 0;
                        else
                            failedPathFindingPenalty += 10;
                    } else {
                        failedPathFindingPenalty += 10;
                    }
                }
                if (d0 > 1024.0D) {
                    this.delayCounter += 10;
                } else if (d0 > 256.0D) {
                    this.delayCounter += 5;
                }

                if (!this.attacker.getNavigation().moveTo(livingentity, this.speedTowardsTarget)) {
                    this.delayCounter += 15;
                }
            }

            this.attackTick = Math.max(this.attackTick - 1, 0);
            this.checkAndPlayAnimation(livingentity, d0);
        } else {
            if (attacker.isAnyPunchAnimationPlaying() &&
                EndTrollBehaviorRules.shouldResolvePunchDamage(attacker.getAnimationTick(), this.hasPerformedAttackLogic)) {

                LivingEntity livingentity = this.attacker.getTarget();
                if (EndTrollBehaviorRules.isValidCombatTarget(livingentity)) {
                    this.attackTick = 20;
                    if (attacker.isAnimationPlaying(EndTrollEntity.DOUBLE_PUNCH_ANIMATION)) {
                        this.attacker.performPunchAttack(livingentity, true);
                    } else {
                        this.attacker.performPunchAttack(livingentity, false);
                    }
                    this.hasPerformedAttackLogic = true;
                }
            }
        }
    }

    protected void checkAndPlayAnimation(LivingEntity enemy, double distToEnemySqr) {
        double reachDistance = this.getAttackReachSqr(enemy);
        if (distToEnemySqr <= reachDistance && this.attackTick <= 0) {
            attacker.playPunchAnimation(EndTrollBehaviorRules.selectPunchAnimation(rand.nextInt(3)));
        }
    }

    protected double getAttackReachSqr(LivingEntity attackTarget) {
        return (double) (this.attacker.getBbWidth() * 2.0F * this.attacker.getBbWidth() * 2.0F + attackTarget.getBbWidth());
    }
}
