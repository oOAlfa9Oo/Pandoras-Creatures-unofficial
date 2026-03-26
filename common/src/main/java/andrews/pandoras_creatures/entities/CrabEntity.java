package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.entities.bases.BucketableMobEntity;
import andrews.pandoras_creatures.entities.bucket.BucketEntityDataKeys;
import andrews.pandoras_creatures.entities.crab.CrabBehaviorRules;
import andrews.pandoras_creatures.entities.crab.CrabDataKeys;
import andrews.pandoras_creatures.entities.crab.CrabVariantCatalog;
import andrews.pandoras_creatures.registry.sound.PCSoundCatalog;
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

import javax.annotation.Nullable;

public class CrabEntity extends BucketableMobEntity {
    private static final String CRAB_ENTITY_ID = "crab";
    private static final String CRAB_SPAWN_EGG_ID = "crab_spawn_egg";
    private static final String CRAB_BUCKET_ID = "crab_bucket";
    private static final EntityDataAccessor<Integer> CRAB_TYPE = SynchedEntityData.defineId(CrabEntity.class, EntityDataSerializers.INT);
    private boolean partyCrab;
    private boolean underWater = false;
    private BlockPos jukeboxPosition;

    public CrabEntity(EntityType<? extends CrabEntity> type, Level level) {
        super(type, level);
    }

    public CrabEntity(Level level, double posX, double posY, double posZ) {
        this(PandorasCreaturesCommon.platform().registry().entityType(CRAB_ENTITY_ID), level);
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
        builder.define(CRAB_TYPE, CrabVariantCatalog.DEFAULT_TYPE);
    }

    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(PandorasCreaturesCommon.platform().registry().item(CRAB_SPAWN_EGG_ID));
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[0];
    }

    @Override
    protected void setBucketData(ItemStack bucket) {
        super.setBucketData(bucket);
        CompoundTag compoundtag = new CompoundTag();
        compoundtag.putInt(BucketEntityDataKeys.BUCKET_VARIANT_TAG, this.getCrabType());
        bucket.set(net.minecraft.core.component.DataComponents.BUCKET_ENTITY_DATA, net.minecraft.world.item.component.CustomData.of(compoundtag));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt(CrabDataKeys.CRAB_TYPE, this.getCrabType());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setCrabType(compound.getInt(CrabDataKeys.CRAB_TYPE));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData) {
        spawnData = super.finalizeSpawn(level, difficulty, reason, spawnData);
        RandomSource rand = level.getRandom();
        int type = CrabVariantCatalog.randomTypeId(rand.nextInt(CrabVariantCatalog.MAX_TYPE));
        this.setCrabType(type);
        return spawnData;
    }

    @Override
    public void loadFromBucketTag(CompoundTag tag) {
        super.loadFromBucketTag(tag);
        if (tag.contains(BucketEntityDataKeys.BUCKET_VARIANT_TAG)) {
            this.setCrabType(tag.getInt(BucketEntityDataKeys.BUCKET_VARIANT_TAG));
        }
    }

    @Override
    protected void updateAir(int air) {
        // Crabs don't need air management - they can survive both in and out of water
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(PandorasCreaturesCommon.platform().registry().item(CRAB_BUCKET_ID));
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
            boolean shouldBeUnderwater = CrabBehaviorRules.shouldBeUnderwater(this.level().getBlockState(this.blockPosition()).is(Blocks.WATER));
            if (this.underWater != shouldBeUnderwater) {
                this.underWater = shouldBeUnderwater;
            }
        }

        boolean keepPartying = CrabBehaviorRules.shouldKeepPartying(
                this.jukeboxPosition != null,
                this.jukeboxPosition != null && this.jukeboxPosition.closerToCenterThan(this.position(), CrabBehaviorRules.PARTY_JUKEBOX_RANGE),
                this.jukeboxPosition != null && this.level().getBlockState(this.jukeboxPosition).is(Blocks.JUKEBOX)
        );
        if (!keepPartying) {
            this.partyCrab = false;
            this.jukeboxPosition = null;
        }
        super.aiStep();
    }

    /**
     * Called when a record starts or stops playing. Used to make crabs start or stop partying.
     */
    public void setPartying(BlockPos pos, boolean isPartying) {
        this.jukeboxPosition = pos;
        this.partyCrab = isPartying;
    }

    public boolean isPartying() {
        return this.partyCrab;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return PandorasCreaturesCommon.platform().registry().sound(PCSoundCatalog.CRAB_HURT);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return PandorasCreaturesCommon.platform().registry().sound(PCSoundCatalog.CRAB_DEATH);
    }

    public static String getNameById(int id) {
        return CrabVariantCatalog.tooltipKey(id);
    }

    public int getCrabType() {
        return CrabVariantCatalog.normalizeType(this.entityData.get(CRAB_TYPE));
    }

    public void setCrabType(int typeId) {
        this.entityData.set(CRAB_TYPE, CrabVariantCatalog.normalizeType(typeId));
    }
}
