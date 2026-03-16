package andrews.pandoras_creatures.entities.projectiles;

import andrews.pandoras_creatures.entities.EndTrollEntity;
import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public abstract class AbstractEndTrollBulletEntity extends Entity {
    protected LivingEntity owner;
    protected Entity target;
    @Nullable
    protected Direction direction;
    protected int steps;
    protected double targetDeltaX;
    protected double targetDeltaY;
    protected double targetDeltaZ;
    @Nullable
    protected UUID ownerUniqueId;
    protected BlockPos ownerBlockPos;
    @Nullable
    protected UUID targetUniqueId;
    protected BlockPos targetBlockPos;

    public AbstractEndTrollBulletEntity(EntityType<? extends AbstractEndTrollBulletEntity> type, Level level) {
        super(type, level);
        this.noPhysics = true;
    }

    public AbstractEndTrollBulletEntity(EntityType<? extends AbstractEndTrollBulletEntity> type, Level level, LivingEntity owner, Entity target, Direction.Axis directionAxis) {
        this(type, level);
        this.owner = owner;
        BlockPos blockpos = owner.blockPosition();
        double posX = (double) blockpos.getX() + 0.5D;
        double posY = owner.blockPosition().getY() + owner.getEyeHeight() + 0.7D;
        double posZ = (double) blockpos.getZ() + 0.5D;
        this.moveTo(posX, posY, posZ, this.getYRot(), this.getXRot());
        this.target = target;
        this.direction = Direction.UP;
        this.selectNextMoveDirection(directionAxis);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        if (this.owner != null) {
            BlockPos blockpos = owner.blockPosition();
            CompoundTag compoundtag = new CompoundTag();
            compoundtag.putUUID("OwnerId", this.owner.getUUID());
            compoundtag.putInt("X", blockpos.getX());
            compoundtag.putInt("Y", blockpos.getY());
            compoundtag.putInt("Z", blockpos.getZ());
            compound.put("Owner", compoundtag);
        }

        if (this.target != null) {
            BlockPos blockpos1 = target.blockPosition();
            CompoundTag compoundtag1 = new CompoundTag();
            compoundtag1.putUUID("TargetId", this.target.getUUID());
            compoundtag1.putInt("X", blockpos1.getX());
            compoundtag1.putInt("Y", blockpos1.getY());
            compoundtag1.putInt("Z", blockpos1.getZ());
            compound.put("Target", compoundtag1);
        }

        if (this.direction != null) {
            compound.putInt("Dir", this.direction.get3DDataValue());
        }

        compound.putInt("Steps", this.steps);
        compound.putDouble("TXD", this.targetDeltaX);
        compound.putDouble("TYD", this.targetDeltaY);
        compound.putDouble("TZD", this.targetDeltaZ);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        this.steps = compound.getInt("Steps");
        this.targetDeltaX = compound.getDouble("TXD");
        this.targetDeltaY = compound.getDouble("TYD");
        this.targetDeltaZ = compound.getDouble("TZD");

        if (compound.contains("Dir", Tag.TAG_ANY_NUMERIC)) {
            this.direction = Direction.from3DDataValue(compound.getInt("Dir"));
        }

        if (compound.contains("Owner", Tag.TAG_COMPOUND)) {
            CompoundTag compoundtag = compound.getCompound("Owner");
            this.ownerUniqueId = compoundtag.getUUID("OwnerId");
            this.ownerBlockPos = new BlockPos(compoundtag.getInt("X"), compoundtag.getInt("Y"), compoundtag.getInt("Z"));
        }

        if (compound.contains("Target", Tag.TAG_COMPOUND)) {
            CompoundTag compoundtag1 = compound.getCompound("Target");
            this.targetUniqueId = compoundtag1.getUUID("TargetId");
            this.targetBlockPos = new BlockPos(compoundtag1.getInt("X"), compoundtag1.getInt("Y"), compoundtag1.getInt("Z"));
        }
    }

    @Override
    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide()) {
            if (this.target == null && this.targetUniqueId != null) {
                for (LivingEntity livingentity : this.level().getEntitiesOfClass(LivingEntity.class,
                        new net.minecraft.world.phys.AABB(
                            this.targetBlockPos.getX() - 2, this.targetBlockPos.getY() - 2, this.targetBlockPos.getZ() - 2,
                            this.targetBlockPos.getX() + 2, this.targetBlockPos.getY() + 2, this.targetBlockPos.getZ() + 2))) {
                    if (livingentity.getUUID().equals(this.targetUniqueId)) {
                        this.target = livingentity;
                        break;
                    }
                }
                this.targetUniqueId = null;
            }

            if (this.owner == null && this.ownerUniqueId != null) {
                for (LivingEntity livingentity1 : this.level().getEntitiesOfClass(LivingEntity.class,
                        new net.minecraft.world.phys.AABB(
                            this.ownerBlockPos.getX() - 2, this.ownerBlockPos.getY() - 2, this.ownerBlockPos.getZ() - 2,
                            this.ownerBlockPos.getX() + 2, this.ownerBlockPos.getY() + 2, this.ownerBlockPos.getZ() + 2))) {
                    if (livingentity1.getUUID().equals(this.ownerUniqueId)) {
                        this.owner = livingentity1;
                        break;
                    }
                }
                this.ownerUniqueId = null;
            }

            if (this.target == null || !this.target.isAlive() || this.target instanceof Player player && player.isSpectator()) {
                if (!this.isNoGravity()) {
                    this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
                }
            } else {
                this.targetDeltaX = Mth.clamp(this.targetDeltaX * 1.025D, -1.0D, 1.0D);
                this.targetDeltaY = Mth.clamp(this.targetDeltaY * 1.025D, -1.0D, 1.0D);
                this.targetDeltaZ = Mth.clamp(this.targetDeltaZ * 1.025D, -1.0D, 1.0D);
                Vec3 vec3 = this.getDeltaMovement();
                this.setDeltaMovement(vec3.add((this.targetDeltaX - vec3.x) * 0.2D, (this.targetDeltaY - vec3.y) * 0.2D, (this.targetDeltaZ - vec3.z) * 0.2D));
            }

            HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
            if (hitresult.getType() != HitResult.Type.MISS) {
                this.bulletHit(hitresult);
            }
        }

        Vec3 vec3d1 = this.getDeltaMovement();
        this.setPos(this.getX() + vec3d1.x, this.getY() + vec3d1.y, this.getZ() + vec3d1.z);
        ProjectileUtil.rotateTowardsMovement(this, 0.5F);

        if (this.level().isClientSide()) {
            this.level().addParticle(getParticleType(), this.getX() - vec3d1.x, this.getY() - vec3d1.y + 0.15D, this.getZ() - vec3d1.z, 0.0D, 0.0D, 0.0D);
        } else if (this.target != null && this.target.isAlive()) {
            if (this.steps > 0) {
                --this.steps;
                if (this.steps == 0) {
                    this.selectNextMoveDirection(this.direction == null ? null : this.direction.getAxis());
                }
            }

            if (this.direction != null) {
                BlockPos blockpos1 = this.blockPosition();
                Direction.Axis axis = this.direction.getAxis();
                if (this.level().loadedAndEntityCanStandOn(blockpos1.relative(this.direction), this)) {
                    this.selectNextMoveDirection(axis);
                } else {
                    BlockPos blockpos = target.blockPosition();
                    if (axis == Direction.Axis.X && blockpos1.getX() == blockpos.getX() ||
                        axis == Direction.Axis.Z && blockpos1.getZ() == blockpos.getZ() ||
                        axis == Direction.Axis.Y && blockpos1.getY() == blockpos.getY()) {
                        this.selectNextMoveDirection(axis);
                    }
                }
            }
        }
    }

    protected boolean canHitEntity(Entity entity) {
        return entity != null && !(entity instanceof EndTrollEntity) && !entity.isSpectator() && entity.isAlive() && entity.isPickable() && !entity.noPhysics;
    }

    protected abstract ParticleOptions getParticleType();

    protected abstract void onEntityHit(LivingEntity target);

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public float getLightLevelDependentMagicValue() {
        return 1.0F;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean shouldRenderAtSqrDistance(double distance) {
        return distance < 16384.0D;
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (!this.level().isClientSide()) {
            this.playSound(SoundEvents.SHULKER_BULLET_HURT, 1.0F, 1.0F);
            ((ServerLevel) this.level()).sendParticles(ParticleTypes.CRIT, this.getX(), this.getY(), this.getZ(), 15, 0.2D, 0.2D, 0.2D, 0.0D);
            this.discard();
        }
        return true;
    }

    @Override
    public SoundSource getSoundSource() {
        return SoundSource.HOSTILE;
    }

    private void setDirection(@Nullable Direction direction) {
        this.direction = direction;
    }

    private void selectNextMoveDirection(@Nullable Direction.Axis directionAxis) {
        double heightModifier = 0.5D;
        BlockPos blockpos;
        if (this.target == null) {
            blockpos = this.blockPosition().below();
        } else {
            heightModifier = (double) this.target.getBbHeight() * 0.5D;
            blockpos = BlockPos.containing(this.target.getX(), this.target.getY() + heightModifier, this.target.getZ());
        }

        double posX = (double) blockpos.getX() + 0.5D;
        double posY = (double) blockpos.getY() + heightModifier;
        double posZ = (double) blockpos.getZ() + 0.5D;
        Direction direction = null;

        if (!blockpos.closerToCenterThan(this.position(), 2.0D)) {
            BlockPos blockpos1 = this.blockPosition();
            List<Direction> list = Lists.newArrayList();
            if (directionAxis != Direction.Axis.X) {
                if (blockpos1.getX() < blockpos.getX() && this.level().isEmptyBlock(blockpos1.east())) {
                    list.add(Direction.EAST);
                } else if (blockpos1.getX() > blockpos.getX() && this.level().isEmptyBlock(blockpos1.west())) {
                    list.add(Direction.WEST);
                }
            }

            if (directionAxis != Direction.Axis.Y) {
                if (blockpos1.getY() < blockpos.getY() && this.level().isEmptyBlock(blockpos1.above())) {
                    list.add(Direction.UP);
                } else if (blockpos1.getY() > blockpos.getY() && this.level().isEmptyBlock(blockpos1.below())) {
                    list.add(Direction.DOWN);
                }
            }

            if (directionAxis != Direction.Axis.Z) {
                if (blockpos1.getZ() < blockpos.getZ() && this.level().isEmptyBlock(blockpos1.south())) {
                    list.add(Direction.SOUTH);
                } else if (blockpos1.getZ() > blockpos.getZ() && this.level().isEmptyBlock(blockpos1.north())) {
                    list.add(Direction.NORTH);
                }
            }

            direction = Direction.getRandom(this.random);
            if (list.isEmpty()) {
                for (int i = 5; !this.level().isEmptyBlock(blockpos1.relative(direction)) && i > 0; --i) {
                    direction = Direction.getRandom(this.random);
                }
            } else {
                direction = list.get(this.random.nextInt(list.size()));
            }

            posX = this.getX() + (double) direction.getStepX();
            posY = this.getY() + (double) direction.getStepY();
            posZ = this.getZ() + (double) direction.getStepZ();
        }

        this.setDirection(direction);
        double totalPosX = posX - this.getX();
        double totalPosY = posY - this.getY();
        double totalPosZ = posZ - this.getZ();
        double deltaTotal = Math.sqrt(totalPosX * totalPosX + totalPosY * totalPosY + totalPosZ * totalPosZ);
        if (deltaTotal == 0.0D) {
            this.targetDeltaX = 0.0D;
            this.targetDeltaY = 0.0D;
            this.targetDeltaZ = 0.0D;
        } else {
            this.targetDeltaX = totalPosX / deltaTotal * 0.15D;
            this.targetDeltaY = totalPosY / deltaTotal * 0.15D;
            this.targetDeltaZ = totalPosZ / deltaTotal * 0.15D;
        }

        this.hasImpulse = true;
        this.steps = 10 + this.random.nextInt(5) * 10;
    }

    protected void bulletHit(HitResult result) {
        if (result.getType() == HitResult.Type.ENTITY) {
            Entity entity = ((EntityHitResult) result).getEntity();
            DamageSource damageSource = this.damageSources().mobProjectile(this, this.owner);
            boolean flag = entity.hurt(damageSource, 4.0F);
            if (flag && entity instanceof LivingEntity living) {
                this.onEntityHit(living);
            }
        } else {
            ((ServerLevel) this.level()).sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(), 2, 0.2D, 0.2D, 0.2D, 0.0D);
            this.playSound(SoundEvents.SHULKER_BULLET_HIT, 1.0F, 1.0F);
        }

        this.discard();
    }
}
