package andrews.pandoras_creatures.entities.goals.end_troll;

import andrews.pandoras_creatures.entities.EndTrollEntity;
import andrews.pandoras_creatures.entities.projectiles.EndTrollBulletDamageEntity;
import andrews.pandoras_creatures.entities.projectiles.EndTrollBulletPoisonEntity;
import andrews.pandoras_creatures.entities.projectiles.EndTrollBulletWitherEntity;
import andrews.pandoras_creatures.util.NetworkUtil;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;

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
        if (goalOwner.level().getDifficulty() == Difficulty.PEACEFUL) {
            return false;
        }
        LivingEntity livingentity = goalOwner.getTarget();
        if (livingentity != null && livingentity.isAlive() &&
            !goalOwner.isAnimationPlaying(EndTrollEntity.SCREAM_ANIMATION) &&
            !goalOwner.isAnimationPlaying(EndTrollEntity.DOUBLE_PUNCH_ANIMATION) &&
            !goalOwner.isAnimationPlaying(EndTrollEntity.RIGHT_PUNCH_ANIMATION) &&
            !goalOwner.isAnimationPlaying(EndTrollEntity.LEFT_PUNCH_ANIMATION) &&
            !goalOwner.isWorldRemote()) {
            return goalOwner.shootCooldown == 0;
        }
        return false;
    }

    @Override
    public void tick() {
        LivingEntity livingentity = goalOwner.getTarget();
        if (livingentity == null) return;

        goalOwner.getLookControl().setLookAt(livingentity, 180.0F, 180.0F);
        double d0 = goalOwner.distanceToSqr(livingentity);
        if (d0 < 400.0D) {
            if (goalOwner.isAnimationPlaying(EndTrollEntity.BLANK_ANIMATION) && !goalOwner.level().isClientSide()) {
                NetworkUtil.sendAnimationPacket(goalOwner, EndTrollEntity.SHOOT_ANIMATION);
            }

            if (goalOwner.isAnimationPlaying(EndTrollEntity.SHOOT_ANIMATION) && goalOwner.getAnimationTick() == 9) {
                for (int i = 0; i < 5; i++) {
                    goalOwner.level().addFreshEntity(getRandomEndTrollBullet(goalOwner.level(), goalOwner, livingentity, getAxis()));
                }
                goalOwner.playSound(SoundEvents.SHULKER_SHOOT, 2.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                goalOwner.shootCooldown = 300;
            }
        }
        super.tick();
    }

    private Entity getRandomEndTrollBullet(Level level, EndTrollEntity owner, LivingEntity targetEntity, Direction.Axis directionAxis) {
        float xMotion = -Mth.sin(owner.yHeadRotO * ((float) Math.PI / 180F)) * Mth.cos(owner.getXRot() * ((float) Math.PI / 180F));
        float yMotion = -Mth.sin(owner.getXRot() * ((float) Math.PI / 180F));
        float zMotion = Mth.cos(owner.getYHeadRot() * ((float) Math.PI / 180F)) * Mth.cos(owner.getXRot() * ((float) Math.PI / 180F));

        switch (rand.nextInt(3) + 1) {
            default:
            case 1:
                EndTrollBulletPoisonEntity bulletEntity0 = new EndTrollBulletPoisonEntity(level, owner, targetEntity, directionAxis);
                bulletEntity0.push(xMotion, yMotion, zMotion);
                return bulletEntity0;
            case 2:
                EndTrollBulletWitherEntity bulletEntity1 = new EndTrollBulletWitherEntity(level, owner, targetEntity, directionAxis);
                bulletEntity1.push(xMotion, yMotion, zMotion);
                return bulletEntity1;
            case 3:
                EndTrollBulletDamageEntity bulletEntity2 = new EndTrollBulletDamageEntity(level, owner, targetEntity, directionAxis);
                bulletEntity2.push(xMotion, yMotion, zMotion);
                return bulletEntity2;
        }
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
