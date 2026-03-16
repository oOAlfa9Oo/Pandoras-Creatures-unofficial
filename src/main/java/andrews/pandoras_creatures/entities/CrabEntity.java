package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.entities.bases.BucketableMobEntity;
import andrews.pandoras_creatures.registry.PCEntities;
import andrews.pandoras_creatures.registry.PCItems;
import andrews.pandoras_creatures.registry.PCSounds;
import andrews.pandoras_creatures.util.animation.Animation;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class CrabEntity extends BucketableMobEntity {
    private static final EntityDataAccessor<Integer> CRAB_TYPE = SynchedEntityData.defineId(CrabEntity.class, EntityDataSerializers.INT);
    private boolean partyCrab;
    private boolean underWater = false;
    private BlockPos jukeboxPosition;

    public CrabEntity(EntityType<? extends CrabEntity> type, Level level) {
        super(type, level);
    }

    public CrabEntity(Level level, double posX, double posY, double posZ) {
        this(PCEntities.CRAB.get(), level);
        this.moveTo(posX, posY, posZ);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 0.3D));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 0.3D, 240));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(CRAB_TYPE, 0);
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(PCItems.CRAB_SPAWN_EGG.get());
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[0];
    }

    @Override
    protected void setBucketData(ItemStack bucket) {
        super.setBucketData(bucket);
        CompoundTag compoundtag = new CompoundTag();
        compoundtag.putInt("BucketVariantTag", this.getCrabType());
        bucket.set(net.minecraft.core.component.DataComponents.BUCKET_ENTITY_DATA, net.minecraft.world.item.component.CustomData.of(compoundtag));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("CrabType", this.getCrabType());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setCrabType(compound.getInt("CrabType"));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData) {
        spawnData = super.finalizeSpawn(level, difficulty, reason, spawnData);
        RandomSource rand = level.getRandom();
        int type = rand.nextInt(2) + 1;
        this.setCrabType(type);
        return spawnData;
    }

    @Override
    public void loadFromBucketTag(CompoundTag tag) {
        super.loadFromBucketTag(tag);
        if (tag.contains("BucketVariantTag")) {
            this.setCrabType(tag.getInt("BucketVariantTag"));
        }
    }

    @Override
    protected void updateAir(int air) {
        // Crabs don't need air management - they can survive both in and out of water
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(PCItems.CRAB_BUCKET.get());
    }

    @Override
    public void baseTick() {
        super.baseTick();

        if (this.underWater) {
            this.fallDistance = 0.0F;
            this.clearFire();
        }
    }

    @Override
    public void aiStep() {
        if (!this.level().isClientSide()) {
            if (this.level().getBlockState(this.blockPosition()).is(Blocks.WATER) && !this.underWater) {
                this.underWater = true;
            } else if (!this.level().getBlockState(this.blockPosition()).is(Blocks.WATER) && this.underWater) {
                this.underWater = false;
            }
        }

        if (this.jukeboxPosition == null || !this.jukeboxPosition.closerToCenterThan(this.position(), 4 * 3.46D) || !this.level().getBlockState(this.jukeboxPosition).is(Blocks.JUKEBOX)) {
            this.partyCrab = false;
            this.jukeboxPosition = null;
        }
        super.aiStep();
    }

    /**
     * Called when a record starts or stops playing. Used to make crabs start or stop partying.
     */
    @OnlyIn(Dist.CLIENT)
    public void setPartying(BlockPos pos, boolean isPartying) {
        this.jukeboxPosition = pos;
        this.partyCrab = isPartying;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isPartying() {
        return this.partyCrab;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return PCSounds.CRAB_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return PCSounds.CRAB_DEATH.get();
    }

    public static String getNameById(int id) {
        return switch (id) {
            case 1 -> "chat.pandoras_creatures.crabBucketTooltip.sea";
            case 2 -> "chat.pandoras_creatures.crabBucketTooltip.tropical";
            default -> "";
        };
    }

    public int getCrabType() {
        if (this.entityData.get(CRAB_TYPE) == 0) {
            RandomSource rand = this.random;
            this.entityData.set(CRAB_TYPE, rand.nextInt(2) + 1);
            return this.entityData.get(CRAB_TYPE);
        } else {
            return this.entityData.get(CRAB_TYPE);
        }
    }

    public void setCrabType(int typeId) {
        this.entityData.set(CRAB_TYPE, typeId);
    }
}
