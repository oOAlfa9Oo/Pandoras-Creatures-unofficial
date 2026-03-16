package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.entities.bases.AnimatedCreatureEntity;
import andrews.pandoras_creatures.entities.goals.end_troll.EndTrollAttackGoal;
import andrews.pandoras_creatures.entities.goals.end_troll.EndTrollBulletAttackGoal;
import andrews.pandoras_creatures.entities.goals.end_troll.EndTrollScreamGoal;
import andrews.pandoras_creatures.entities.goals.end_troll.EndTrollTransformGoal;
import andrews.pandoras_creatures.registry.PCEntities;
import andrews.pandoras_creatures.registry.PCItems;
import andrews.pandoras_creatures.registry.PCSounds;
import andrews.pandoras_creatures.util.NetworkUtil;
import andrews.pandoras_creatures.util.animation.Animation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.event.EventHooks;

import javax.annotation.Nullable;

public class EndTrollEntity extends AnimatedCreatureEntity {
    private static final EntityDataAccessor<Boolean> IS_STANDING = SynchedEntityData.defineId(EndTrollEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HAS_SCREAMED = SynchedEntityData.defineId(EndTrollEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDimensions STANDING_SIZE = EntityDimensions.fixed(3.0F, 5.0F);

    public static final Animation TRANSFORM_ANIMATION = new Animation(20);
    public static final Animation SCREAM_ANIMATION = new Animation(32);
    public static final Animation SHOOT_ANIMATION = new Animation(20);
    public static final Animation RIGHT_PUNCH_ANIMATION = new Animation(28);
    public static final Animation LEFT_PUNCH_ANIMATION = new Animation(28);
    public static final Animation DOUBLE_PUNCH_ANIMATION = new Animation(28);
    public static final Animation DEATH_ANIMATION = new Animation(50);

    public int shootCooldown = 300;
    public int screamCooldown = 400;

    public EndTrollEntity(EntityType<? extends EndTrollEntity> type, Level level) {
        super(type, level);
    }

    public EndTrollEntity(Level level, double posX, double posY, double posZ) {
        this(PCEntities.END_TROLL.get(), level);
        // Step height is now handled via Attributes.STEP_HEIGHT
        this.moveTo(posX, posY, posZ);
    }

    @Override
    protected void registerGoals() {
        // AI Goals
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new EndTrollTransformGoal(this));
        this.goalSelector.addGoal(3, new EndTrollScreamGoal(this));
        this.goalSelector.addGoal(4, new EndTrollBulletAttackGoal(this));
        this.goalSelector.addGoal(5, new EndTrollAttackGoal(this, 0.3D, false));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.3D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 10.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        // Target Selector
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(IS_STANDING, false);
        builder.define(HAS_SCREAMED, false);
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(PCItems.END_TROLL_SPAWN_EGG.get());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("IsStanding", this.isEntityStanding());
        compound.putBoolean("HasScreamed", this.hasScreamed());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setEntityStanding(compound.getBoolean("IsStanding"));
        this.setHasScreamed(compound.getBoolean("HasScreamed"));
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);
        if (IS_STANDING.equals(key)) {
            this.refreshDimensions();
        }
    }

    @Override
    public EntityDimensions getDefaultDimensions(Pose pose) {
        return this.isEntityStanding() ? STANDING_SIZE : super.getDefaultDimensions(pose);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        // Plays the Scream Animation
        if (!this.hasScreamed()) {
            if (this.isEntityStanding()) {
                if (this.isAnimationPlaying(BLANK_ANIMATION) && !this.level().isClientSide()) {
                    NetworkUtil.sendAnimationPacket(this, SCREAM_ANIMATION);
                }
            }
        }

        if (this.isEntityStanding()) {
            // Breaks the Blocks during the Scream
            if (this.isAnimationPlaying(SCREAM_ANIMATION)) {
                if (this.getAnimationTick() == 16) {
                    screamBlockBreaking(this.getBoundingBox().inflate(5, 2, 5).move(0, 2, 0), this.level());
                    screamEntityKnockBack(this.getBoundingBox().inflate(5, 2, 5).move(0, 2, 0));
                } else if (this.getAnimationTick() == 12) {
                    this.level().addParticle(ParticleTypes.EXPLOSION_EMITTER, this.blockPosition().getX(), this.getY() + this.getEyeHeight() / 2, this.blockPosition().getZ(), 0, 0, 0);
                } else if (this.getAnimationTick() == 7) {
                    this.level().playLocalSound(this.blockPosition().getX(), this.getY() + this.getEyeHeight(), this.blockPosition().getZ(), PCSounds.END_TROLL_SCREAM.get(), SoundSource.HOSTILE, 2.0F, 1.0F, false);
                }
            } else if (this.isAnimationPlaying(RIGHT_PUNCH_ANIMATION) || this.isAnimationPlaying(LEFT_PUNCH_ANIMATION) || this.isAnimationPlaying(DOUBLE_PUNCH_ANIMATION)) {
                if (this.getAnimationTick() == 4) {
                    this.level().playLocalSound(this.blockPosition().getX(), this.getY() + this.getEyeHeight(), this.blockPosition().getZ(), PCSounds.END_TROLL_ATTACK.get(), SoundSource.HOSTILE, 2.0F, 1.0F, false);
                }
            }

            if (!this.isWorldRemote() && this.isEntityStanding()) {
                if (shootCooldown > 0) {
                    shootCooldown--;
                }
                if (screamCooldown > 0) {
                    screamCooldown--;
                }
            }
        }

        // Breaks Chorus Plants
        if ((this.tickCount % 10) == 0) {
            if (!this.level().isClientSide()) {
                if (EventHooks.canEntityGrief(this.level(), this)) {
                    breakChorusBlocks(this.getBoundingBox().inflate(2, 0, 2), this.level());
                }
            }
        }
    }

    /**
     * Used by the EndTroll to break Chorus Plants and Flowers that are around it
     */
    private void breakChorusBlocks(AABB aabb, Level level) {
        for (int x = Mth.floor(aabb.minX); x < Mth.floor(aabb.maxX); ++x) {
            for (int y = Mth.ceil(aabb.minY); y < Mth.floor(aabb.maxY); ++y) {
                for (int z = Mth.floor(aabb.minZ); z < Mth.floor(aabb.maxZ); ++z) {
                    BlockPos pos = new BlockPos(x, y, z);
                    Block block = level.getBlockState(pos).getBlock();
                    if (block == Blocks.CHORUS_PLANT || block == Blocks.CHORUS_FLOWER) {
                        level.destroyBlock(pos, true);
                    }
                }
            }
        }
    }

    @Override
    protected SoundEvent getDeathSound() {
        return PCSounds.END_TROLL_DEATH.get();
    }

    @Override
    protected float getSoundVolume() {
        return 2.0F;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData) {
        spawnData = super.finalizeSpawn(level, difficulty, reason, spawnData);
        return spawnData;
    }

    @Override
    @Nullable
    public Animation getDeathAnimation() {
        return this.isEntityStanding() ? DEATH_ANIMATION : super.getDeathAnimation();
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation == TRANSFORM_ANIMATION) {
            this.setEntityStanding(true);
            refreshDimensions();
        }

        if (animation == SCREAM_ANIMATION) {
            this.setHasScreamed(true);
        }
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[]{TRANSFORM_ANIMATION, SCREAM_ANIMATION, SHOOT_ANIMATION, RIGHT_PUNCH_ANIMATION, LEFT_PUNCH_ANIMATION, DOUBLE_PUNCH_ANIMATION, DEATH_ANIMATION};
    }

    @Override
    public int getBaseExperienceReward() {
        this.xpReward = 100;
        return super.getBaseExperienceReward();
    }

    @Override
    public boolean canBeLeashed() {
        return false;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        Entity entity = source.getDirectEntity();
        if (entity instanceof AbstractArrow) {
            return false;
        }
        return super.hurt(source, amount);
    }

    /**
     * Used to handle the EndTroll Attacks
     */
    public boolean attackEntityAsMob(Entity target, boolean doublePunch) {
        boolean flag;
        if (doublePunch) {
            flag = target.hurt(this.damageSources().mobAttack(this), (float) (14 + this.random.nextInt(7)));
        } else {
            flag = target.hurt(this.damageSources().mobAttack(this), (float) (12 + this.random.nextInt(4)));
        }
        return flag;
    }

    /**
     * Used by the EndTroll to break the Blocks around it when screaming
     */
    private void screamBlockBreaking(AABB aabb, Level level) {
        if (!this.level().isClientSide() && EventHooks.canEntityGrief(level, this)) {
            for (int x = Mth.floor(aabb.minX); x < Mth.floor(aabb.maxX); ++x) {
                for (int y = Mth.ceil(aabb.minY); y < Mth.floor(aabb.maxY); ++y) {
                    for (int z = Mth.floor(aabb.minZ); z < Mth.floor(aabb.maxZ); ++z) {
                        BlockPos pos = new BlockPos(x, y, z);
                        Block block = level.getBlockState(pos).getBlock();
                        BlockEntity blockEntity = level.getBlockEntity(pos);

                        if (!level.getBlockState(pos).isAir() && !level.getBlockState(pos).is(BlockTags.WITHER_IMMUNE)) {
                            if (blockEntity == null && random.nextInt(4) + 1 == 4) {
                                FallingBlockEntity fallingBlock = FallingBlockEntity.fall(level, pos, block.defaultBlockState());
                                fallingBlock.setDeltaMovement(fallingBlock.getDeltaMovement().add(
                                        this.position().subtract(fallingBlock.position()).multiply(
                                                (-1.2D + random.nextDouble()) / 3,
                                                (-1.1D + random.nextDouble()) / 3,
                                                (-1.2D + random.nextDouble()) / 3
                                        )
                                ));
                            } else {
                                level.destroyBlock(pos, shouldDropItem(blockEntity));
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean shouldDropItem(BlockEntity blockEntity) {
        if (blockEntity == null) {
            return random.nextInt(3) + 1 == 3;
        }
        return true;
    }

    /**
     * Used by the EndTroll to throw back Entities when he screams
     */
    private void screamEntityKnockBack(AABB aabb) {
        if (!this.level().isClientSide()) {
            for (Entity entity : level().getEntities(null, aabb)) {
                double throwPower = 0.8D;

                if (entity instanceof Player player) {
                    if (!player.isSpectator() && !player.isCreative()) {
                        entity.setDeltaMovement(entity.getDeltaMovement().add(this.position().subtract(entity.position()).multiply(-throwPower, -throwPower, -throwPower)));
                        entity.hurtMarked = true;
                    }
                } else if (entity instanceof LivingEntity) {
                    entity.setDeltaMovement(entity.getDeltaMovement().add(this.position().subtract(entity.position()).multiply(-throwPower, -throwPower, -throwPower)));
                    entity.hurtMarked = true;
                }
            }
        }
    }

    /**
     * @return - Whether or not this Entity is standing
     */
    public boolean isEntityStanding() {
        return this.entityData.get(IS_STANDING);
    }

    /**
     * Sets whether or not the Entity is standing
     */
    public void setEntityStanding(boolean value) {
        this.entityData.set(IS_STANDING, value);
    }

    /**
     * @return - Whether or not this Entity has screamed
     */
    public boolean hasScreamed() {
        return this.entityData.get(HAS_SCREAMED);
    }

    /**
     * Sets whether or not the Entity has screamed
     */
    public void setHasScreamed(boolean value) {
        this.entityData.set(HAS_SCREAMED, value);
    }
}
