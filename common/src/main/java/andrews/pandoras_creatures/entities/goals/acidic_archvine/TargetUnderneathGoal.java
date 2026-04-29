package andrews.pandoras_creatures.entities.goals.acidic_archvine;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.entities.AcidicArchvineEntity;
import andrews.pandoras_creatures.entities.acidic_archvine.AcidicArchvineAttackState;
import andrews.pandoras_creatures.entities.acidic_archvine.AcidicArchvineTargetingRules;
import andrews.pandoras_creatures.registry.item.PCItemIds;
import andrews.pandoras_creatures.registry.sound.PCSoundCatalog;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import org.jetbrains.annotations.Nullable;
import java.util.EnumSet;
import java.util.function.Predicate;

public class TargetUnderneathGoal<T extends LivingEntity> extends TargetGoal {
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
            return this.hasValidNearestTarget();
        }
    }

    protected AABB getTargetableArea(double targetDistance) {
        return AcidicArchvineTargetingRules.createTargetableArea(this.mob.getBoundingBox(), targetDistance);
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
            this.mob.level().broadcastEntityEvent(this.mob, AcidicArchvineAttackState.GRABBING.entityEventId());
            this.acidicArchvine.setAttackState(AcidicArchvineAttackState.GRABBING);
        }
        super.start();
    }

    @Override
    public void stop() {
        this.biteCooldown = 0;
        super.stop();
        this.acidicArchvine.clearTargetedEntity();
        this.mob.level().broadcastEntityEvent(this.mob, AcidicArchvineAttackState.IDLE.entityEventId());
        this.acidicArchvine.setAttackState(AcidicArchvineAttackState.IDLE);
        if (this.nearestTarget instanceof ServerPlayer serverPlayer && !serverPlayer.isCreative()) {
            serverPlayer.getAbilities().mayfly = false;
            serverPlayer.onUpdateAbilities();
        }
    }

    @Override
    public boolean canContinueToUse() {
        if (this.hasValidNearestTarget()) {
            double followDistance = this.getFollowDistance();
            if (!AcidicArchvineTargetingRules.isWithinFollowDistance(this.mob.distanceToSqr(this.nearestTarget), followDistance)) {
                return false;
            }

            if (!this.getTargetableArea(followDistance).intersects(this.nearestTarget.getBoundingBox())) {
                return false;
            }

            if (AcidicArchvineTargetingRules.canHoldTarget(this.mob.position().distanceTo(this.nearestTarget.position()))) {
                this.mob.setTarget(this.nearestTarget);
                return true;
            }
        }
        return super.canContinueToUse();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.hasValidNearestTarget()) {
            if (this.biteCooldown > 0) {
                this.biteCooldown--;
            }

            double dist = this.mob.position().distanceTo(this.nearestTarget.position());

            if (AcidicArchvineTargetingRules.shouldPullTarget(dist)) {
                Vec3 pull = AcidicArchvineTargetingRules.createPullVector(this.mob.position(), this.nearestTarget.position());
                this.nearestTarget.setDeltaMovement(this.nearestTarget.getDeltaMovement().add(pull));
                this.nearestTarget.hasImpulse = true;
            } else {
                if (AcidicArchvineTargetingRules.shouldTeleportTarget(dist)) {
                    this.mob.level().broadcastEntityEvent(this.mob, AcidicArchvineAttackState.CHEWING.entityEventId());
                    this.acidicArchvine.setAttackState(AcidicArchvineAttackState.CHEWING);
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
                    this.mob.playSound(PandorasCreaturesCommon.platform().registry().sound(PCSoundCatalog.ACIDIC_ARCHVINE_ATTACK), 1.0F, 1.0F);
                    this.biteCooldown = AcidicArchvineTargetingRules.BITE_COOLDOWN_TICKS;
                }
            }
            this.nearestTarget.hurtMarked = true;
        }
    }

    private boolean hasValidNearestTarget() {
        return AcidicArchvineTargetingRules.isValidTarget(this.nearestTarget, isProtectedByPlantHat(this.nearestTarget));
    }

    private boolean isProtectedByPlantHat(@Nullable LivingEntity target) {
        return target instanceof Player player
                && AcidicArchvineTargetingRules.isProtectedByPlantHat(player.getInventory().armor.get(3).is(PandorasCreaturesCommon.platform().registry().item(PCItemIds.PLANT_HAT)));
    }
}

