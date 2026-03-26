package andrews.pandoras_creatures.entities.goals.end_troll;

import andrews.pandoras_creatures.entities.EndTrollEntity;
import andrews.pandoras_creatures.entities.end_troll.EndTrollBehaviorRules;
import andrews.pandoras_creatures.entities.end_troll.EndTrollProjectileFactory;
import andrews.pandoras_creatures.entities.end_troll.EndTrollProjectileLaunch;
import andrews.pandoras_creatures.entities.end_troll.EndTrollProjectileRules;
import andrews.pandoras_creatures.util.NetworkUtil;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class EndTrollBulletAttackGoal extends Goal {
    private final EndTrollEntity goalOwner;
    private final RandomSource rand;

    public EndTrollBulletAttackGoal(EndTrollEntity goalOwner) {
        this.goalOwner = goalOwner;
        this.rand = goalOwner.getRandom();
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity livingentity = goalOwner.getTarget();
        return EndTrollBehaviorRules.shouldTryShoot(
                goalOwner.level().getDifficulty() != Difficulty.PEACEFUL,
                EndTrollBehaviorRules.isValidCombatTarget(livingentity),
                goalOwner.blocksRangedAttackGoal(),
                !goalOwner.isWorldRemote(),
                goalOwner.getShootCooldown()
        );
    }

    @Override
    public void tick() {
        LivingEntity livingentity = goalOwner.getTarget();
        if (!EndTrollBehaviorRules.isValidCombatTarget(livingentity)) return;

        goalOwner.getLookControl().setLookAt(livingentity, 180.0F, 180.0F);
        double d0 = goalOwner.distanceToSqr(livingentity);
        if (d0 < 400.0D) {
            if (goalOwner.isAnimationPlaying(EndTrollEntity.BLANK_ANIMATION) && !goalOwner.level().isClientSide()) {
                NetworkUtil.sendAnimationPacket(goalOwner, EndTrollEntity.SHOOT_ANIMATION);
            }

            if (goalOwner.isAnimationPlaying(EndTrollEntity.SHOOT_ANIMATION) && goalOwner.getAnimationTick() == 9) {
                EndTrollProjectileLaunch launch = EndTrollProjectileRules.createLaunchVector(goalOwner.yHeadRotO, goalOwner.getXRot());
                for (int i = 0; i < EndTrollBehaviorRules.PROJECTILES_PER_VOLLEY; i++) {
                    goalOwner.level().addFreshEntity(EndTrollProjectileFactory.createProjectile(
                            goalOwner.level(),
                            goalOwner,
                            livingentity,
                            getAxis(),
                            EndTrollProjectileRules.selectProjectileKind(rand.nextInt(3)),
                            launch
                    ));
                }
                goalOwner.playSound(SoundEvents.SHULKER_SHOOT, 2.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                goalOwner.resetShootCooldown();
            }
        }
        super.tick();
    }

    private Direction.Axis getAxis() {
        if (rand.nextInt(3) + 1 == 1) {
            return Direction.Axis.X;
        }
        if (rand.nextInt(3) + 1 == 2) {
            return Direction.Axis.Y;
        }
        if (rand.nextInt(3) + 1 == 3) {
            return Direction.Axis.Z;
        }
        return Direction.Axis.X;
    }
}
