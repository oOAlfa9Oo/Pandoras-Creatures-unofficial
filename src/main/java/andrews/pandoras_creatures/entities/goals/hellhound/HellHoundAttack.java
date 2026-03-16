package andrews.pandoras_creatures.entities.goals.hellhound;

import andrews.pandoras_creatures.entities.HellhoundEntity;
import andrews.pandoras_creatures.entities.goals.bases.PCMeleeAttackGoal;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;

public class HellHoundAttack extends PCMeleeAttackGoal {
    public HellHoundAttack(PathfinderMob creature, double speedIn, boolean useLongMemory) {
        super(creature, speedIn, useLongMemory);
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
        super.checkAndPerformAttack(enemy, distToEnemySqr);

        double d0 = this.getAttackReachSqr(enemy);
        if (distToEnemySqr <= d0 && this.attackTick <= 0) {
            this.attackTick = 10;
            this.attacker.doHurtTarget(enemy);
        }
    }

    @Override
    public void start() {
        super.start();
        attacker.level().broadcastEntityEvent(attacker, (byte) 4);
        ((HellhoundEntity) this.attacker).setIsCharging(1);
    }

    @Override
    public void stop() {
        super.stop();
        ((HellhoundEntity) this.attacker).setIsCharging(0);
        attacker.level().broadcastEntityEvent(attacker, (byte) 5);
    }
}
