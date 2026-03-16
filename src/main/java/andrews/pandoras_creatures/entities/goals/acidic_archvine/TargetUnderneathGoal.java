package andrews.pandoras_creatures.entities.goals.acidic_archvine;

import andrews.pandoras_creatures.entities.AcidicArchvineEntity;
import andrews.pandoras_creatures.registry.PCItems;
import andrews.pandoras_creatures.registry.PCSounds;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.function.Predicate;

public class TargetUnderneathGoal<T extends LivingEntity> extends TargetGoal {
    private static final double PULL_SPEED = 0.3D;
    private static final double HORIZONTAL_TARGET_RANGE = 10.0D;
    private static final double CLOSE_CAPTURE_DISTANCE = 1.5D;
    private static final int BITE_COOLDOWN_TICKS = 20;
    private final AcidicArchvineEntity acidicArchvine;
    protected final Class<T> targetClass;
    protected final int targetChance;
    protected LivingEntity nearestTarget;
    protected TargetingConditions targetEntitySelector;
    private int biteCooldown;

    public TargetUnderneathGoal(Mob entity, Class<T> target, boolean mustSee) {
        this(entity, target, mustSee, false);
    }

    public TargetUnderneathGoal(Mob entity, Class<T> target, boolean mustSee, boolean mustReach) {
        this(entity, target, 10, mustSee, mustReach, null);
    }

    public TargetUnderneathGoal(Mob entity, Class<T> target, int chance, boolean mustSee, boolean mustReach, @Nullable Predicate<LivingEntity> predicate) {
        super(entity, mustSee, mustReach);
        this.acidicArchvine = (AcidicArchvineEntity) entity;
        this.targetClass = target;
        this.targetChance = chance;
        this.setFlags(EnumSet.of(Goal.Flag.TARGET));
        this.targetEntitySelector = TargetingConditions.forCombat().range(this.getFollowDistance()).selector(predicate);
    }

    @Override
    public boolean canUse() {
        if (this.targetChance > 0 && this.mob.getRandom().nextInt(this.targetChance) != 0) {
            return false;
        } else {
            this.findNearestTarget();

            if (this.nearestTarget instanceof Player player && player.getInventory().armor.get(3).is(PCItems.PLANT_HAT.get())) {
                return false;
            }

            return this.nearestTarget != null;
        }
    }

    protected AABB getTargetableArea(double targetDistance) {
        return this.mob.getBoundingBox().inflate(HORIZONTAL_TARGET_RANGE, targetDistance, HORIZONTAL_TARGET_RANGE).move(0, -targetDistance, 0);
    }

    protected void findNearestTarget() {
        this.nearestTarget = this.mob.level().getNearestEntity(
                this.mob.level().getEntitiesOfClass(this.targetClass, this.getTargetableArea(this.getFollowDistance()), e -> true),
                this.targetEntitySelector,
                this.mob,
                this.mob.getX(),
                this.mob.getEyeY(),
                this.mob.getZ()
        );
    }

    @Override
    public void start() {
        this.biteCooldown = 0;
        this.mob.setTarget(this.nearestTarget);
        if (this.acidicArchvine.getTarget() != null) {
            this.acidicArchvine.setTargetedEntity(this.acidicArchvine.getTarget().getId());
            this.mob.level().broadcastEntityEvent(this.mob, (byte) 5);
            this.acidicArchvine.setAttackState(1);
        }
        super.start();
    }

    @Override
    public void stop() {
        this.biteCooldown = 0;
        super.stop();
        this.acidicArchvine.setTargetedEntity(0);
        this.mob.level().broadcastEntityEvent(this.mob, (byte) 4);
        this.acidicArchvine.setAttackState(0);
        if (this.nearestTarget instanceof ServerPlayer serverPlayer && !serverPlayer.isCreative()) {
            serverPlayer.getAbilities().mayfly = false;
            serverPlayer.onUpdateAbilities();
        }
    }

    @Override
    public boolean canContinueToUse() {
        if (this.nearestTarget != null && this.nearestTarget.isAlive()) {
            if (this.nearestTarget instanceof Player player && player.getInventory().armor.get(3).is(PCItems.PLANT_HAT.get())) {
                return false;
            }

            double followDistance = this.getFollowDistance();
            if (this.mob.distanceToSqr(this.nearestTarget) > followDistance * followDistance) {
                return false;
            }

            if (!this.getTargetableArea(followDistance).intersects(this.nearestTarget.getBoundingBox())) {
                return false;
            }

            if (this.mob.position().distanceTo(this.nearestTarget.position()) <= CLOSE_CAPTURE_DISTANCE) {
                this.mob.setTarget(this.nearestTarget);
                return true;
            }
        }
        return super.canContinueToUse();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.nearestTarget != null) {
            if (this.biteCooldown > 0) {
                this.biteCooldown--;
            }

            double dist = this.mob.position().distanceTo(this.nearestTarget.position());
            double mult = PULL_SPEED / (dist + 1);

            if (dist > 1.0) {
                Vec3 pull = this.mob.position().subtract(this.nearestTarget.position()).multiply(mult / 2, mult, mult / 2);
                this.nearestTarget.setDeltaMovement(this.nearestTarget.getDeltaMovement().add(pull));
                this.nearestTarget.hasImpulse = true;
            } else {
                if (dist > 0.55) {
                    this.mob.level().broadcastEntityEvent(this.mob, (byte) 6);
                    this.acidicArchvine.setAttackState(2);
                    if (this.nearestTarget instanceof ServerPlayer serverPlayer) {
                        serverPlayer.teleportTo(
                                serverPlayer.serverLevel(),
                                this.mob.getX(),
                                this.mob.getY() - 0.5,
                                this.mob.getZ(),
                                serverPlayer.getYRot(),
                                serverPlayer.getXRot()
                        );
                    } else {
                        this.nearestTarget.teleportTo(this.mob.getX(), this.mob.getY() - 0.5, this.mob.getZ());
                    }
                    this.nearestTarget.setDeltaMovement(0, 0, 0);
                    this.nearestTarget.hasImpulse = true;
                    if (this.nearestTarget instanceof ServerPlayer serverPlayer) {
                        serverPlayer.getAbilities().mayfly = true;
                        serverPlayer.onUpdateAbilities();
                    }
                }

                if (this.biteCooldown <= 0) {
                    this.mob.doHurtTarget(this.nearestTarget);
                    this.mob.playSound(PCSounds.ACIDIC_ARCHVINE_ATTACK.get(), 1.0F, 1.0F);
                    this.biteCooldown = BITE_COOLDOWN_TICKS;
                }
            }
            this.nearestTarget.hurtMarked = true;
        }
    }
}
