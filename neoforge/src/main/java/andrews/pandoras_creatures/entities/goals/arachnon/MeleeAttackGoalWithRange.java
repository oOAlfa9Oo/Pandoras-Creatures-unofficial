package andrews.pandoras_creatures.entities.goals.arachnon;

import andrews.pandoras_creatures.entities.goals.bases.PCMeleeAttackGoal;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;

public class MeleeAttackGoalWithRange extends PCMeleeAttackGoal {
    private final double attackRange;

    public MeleeAttackGoalWithRange(PathfinderMob creature, double speedIn, boolean useLongMemory, double attackRange) {
        super(creature, speedIn, useLongMemory);
        this.attackRange = attackRange;
    }

    @Override
    protected double getAttackReachSqr(LivingEntity attackTarget) {
        return attackRange + attackTarget.getBbWidth();
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
        double d0 = this.getAttackReachSqr(enemy);
        if (distToEnemySqr <= d0 && this.attackTick <= 0) {
            this.attackTick = 40;
            this.attacker.doHurtTarget(enemy);
        }
    }
}
