package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.entities.bases.BucketableMobEntity;
import andrews.pandoras_creatures.entities.bucket.BucketEntityDataKeys;
import andrews.pandoras_creatures.entities.seahorse.SeahorseDataKeys;
import andrews.pandoras_creatures.entities.seahorse.SeahorseVariantCatalog;
import andrews.pandoras_creatures.entities.seahorse.SeahorseVisualRules;
import andrews.pandoras_creatures.util.animation.Animation;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import javax.annotation.Nullable;

public class SeahorseEntity extends BucketableMobEntity {
    private static final String SEAHORSE_ENTITY_ID = "seahorse";
    private static final String SEAHORSE_SPAWN_EGG_ID = "seahorse_spawn_egg";
    private static final String SEAHORSE_BUCKET_ID = "seahorse_bucket";
    private static final EntityDataAccessor<Integer> SEAHORSE_TYPE = SynchedEntityData.defineId(SeahorseEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SEAHORSE_SIZE = SynchedEntityData.defineId(SeahorseEntity.class, EntityDataSerializers.INT);

    public SeahorseEntity(EntityType<? extends SeahorseEntity> type, Level level) {
        super(type, level);
        this.moveControl = new SeahorseMoveControl(this);
    }

    public SeahorseEntity(Level level, double posX, double posY, double posZ) {
        this(PandorasCreaturesCommon.platform().registry().entityType(SEAHORSE_ENTITY_ID), level);
        this.moveTo(posX, posY, posZ);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D));
        this.goalSelector.addGoal(3, new SeahorseSwimGoal(this));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(SEAHORSE_TYPE, SeahorseVariantCatalog.DEFAULT_TYPE);
        builder.define(SEAHORSE_SIZE, SeahorseVariantCatalog.DEFAULT_SIZE);
    }

    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(PandorasCreaturesCommon.platform().registry().item(SEAHORSE_SPAWN_EGG_ID));
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[0];
    }

    @Override
    public void travel(Vec3 vector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.01F, vector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(vector);
        }
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 8;
    }

    @Override
    public void die(net.minecraft.world.damagesource.DamageSource cause) {
        super.die(cause);
        if (SeahorseVisualRules.isSpecialNamed(this.getName().getString())) {
            if (this.level().isClientSide()) {
                for (int i = 0; i < 40; i++) {
                    RandomSource rand = this.random;
                    // Rainbow particles
                    this.level().addParticle(new DustParticleOptions(new Vector3f(148/255f, 0, 211/255f), 1.0F), this.getX() + ((rand.nextInt(31) - 15) / 5.0), this.getY() + ((rand.nextInt(31) - 15) / 5.0), this.getZ() + ((rand.nextInt(31) - 15) / 5.0), 0, 0, 0);
                    this.level().addParticle(new DustParticleOptions(new Vector3f(75/255f, 0, 130/255f), 1.0F), this.getX() + ((rand.nextInt(31) - 15) / 5.0), this.getY() + ((rand.nextInt(31) - 15) / 5.0), this.getZ() + ((rand.nextInt(31) - 15) / 5.0), 0, 0, 0);
                    this.level().addParticle(new DustParticleOptions(new Vector3f(0, 0, 1), 1.0F), this.getX() + ((rand.nextInt(31) - 15) / 5.0), this.getY() + ((rand.nextInt(31) - 15) / 5.0), this.getZ() + ((rand.nextInt(31) - 15) / 5.0), 0, 0, 0);
                    this.level().addParticle(new DustParticleOptions(new Vector3f(0, 1, 0), 1.0F), this.getX() + ((rand.nextInt(31) - 15) / 5.0), this.getY() + ((rand.nextInt(31) - 15) / 5.0), this.getZ() + ((rand.nextInt(31) - 15) / 5.0), 0, 0, 0);
                    this.level().addParticle(new DustParticleOptions(new Vector3f(1, 1, 0), 1.0F), this.getX() + ((rand.nextInt(31) - 15) / 5.0), this.getY() + ((rand.nextInt(31) - 15) / 5.0), this.getZ() + ((rand.nextInt(31) - 15) / 5.0), 0, 0, 0);
                    this.level().addParticle(new DustParticleOptions(new Vector3f(1, 127/255f, 0), 1.0F), this.getX() + ((rand.nextInt(31) - 15) / 5.0), this.getY() + ((rand.nextInt(31) - 15) / 5.0), this.getZ() + ((rand.nextInt(31) - 15) / 5.0), 0, 0, 0);
                    this.level().addParticle(new DustParticleOptions(new Vector3f(1, 0, 0), 1.0F), this.getX() + ((rand.nextInt(31) - 15) / 5.0), this.getY() + ((rand.nextInt(31) - 15) / 5.0), this.getZ() + ((rand.nextInt(31) - 15) / 5.0), 0, 0, 0);
                }
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (SeahorseVisualRules.isSpecialNamed(this.getName().getString())) {
            if (this.level().isClientSide()) {
                if ((this.tickCount % 8) == 0) {
                    RandomSource rand = this.random;
                    float unit = SeahorseVisualRules.rainbowParticleUnit(this.getBbHeight(), this.getSeahorseSize());

                    this.level().addParticle(new DustParticleOptions(new Vector3f(148/255f, 0, 211/255f), 1.0F), this.getX() + (0.1F * (rand.nextInt(5) - 3)), this.getY() + unit * 7, this.getZ() + (0.1F * (rand.nextInt(5) - 3)), 0, 0, 0);
                    this.level().addParticle(new DustParticleOptions(new Vector3f(75/255f, 0, 130/255f), 1.0F), this.getX() + (0.1F * (rand.nextInt(5) - 3)), this.getY() + unit * 6, this.getZ() + (0.1F * (rand.nextInt(5) - 3)), 0, 0, 0);
                    this.level().addParticle(new DustParticleOptions(new Vector3f(0, 0, 1), 1.0F), this.getX() + (0.1F * (rand.nextInt(5) - 3)), this.getY() + unit * 5, this.getZ() + (0.1F * (rand.nextInt(5) - 3)), 0, 0, 0);
                    this.level().addParticle(new DustParticleOptions(new Vector3f(0, 1, 0), 1.0F), this.getX() + (0.1F * (rand.nextInt(5) - 3)), this.getY() + unit * 4, this.getZ() + (0.1F * (rand.nextInt(5) - 3)), 0, 0, 0);
                    this.level().addParticle(new DustParticleOptions(new Vector3f(1, 1, 0), 1.0F), this.getX() + (0.1F * (rand.nextInt(5) - 3)), this.getY() + unit * 3, this.getZ() + (0.1F * (rand.nextInt(5) - 3)), 0, 0, 0);
                    this.level().addParticle(new DustParticleOptions(new Vector3f(1, 127/255f, 0), 1.0F), this.getX() + (0.1F * (rand.nextInt(5) - 3)), this.getY() + unit * 2, this.getZ() + (0.1F * (rand.nextInt(5) - 3)), 0, 0, 0);
                    this.level().addParticle(new DustParticleOptions(new Vector3f(1, 0, 0), 1.0F), this.getX() + (0.1F * (rand.nextInt(5) - 3)), this.getY() + unit, this.getZ() + (0.1F * (rand.nextInt(5) - 3)), 0, 0, 0);
                }
            }
        }
    }

    @Override
    public void aiStep() {
        if (!this.isInWater() && this.onGround() && this.verticalCollision) {
            this.setDeltaMovement(this.getDeltaMovement().add((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F, 0.4D, (this.random.nextFloat() * 2.0F - 1.0F) * 0.05F));
            this.setOnGround(false);
            this.hasImpulse = true;
            this.playSound(SoundEvents.COD_FLOP, this.getSoundVolume(), 1.2F);
        }

        super.aiStep();
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.FISH_SWIM;
    }

    @Override
    protected void setBucketData(ItemStack bucket) {
        super.setBucketData(bucket);
        CompoundTag compoundtag = new CompoundTag();
        compoundtag.putInt(BucketEntityDataKeys.BUCKET_VARIANT_TAG, this.getSeahorseType());
        compoundtag.putInt(BucketEntityDataKeys.BUCKET_SIZE_TAG, this.getSeahorseSize());
        bucket.set(net.minecraft.core.component.DataComponents.BUCKET_ENTITY_DATA, net.minecraft.world.item.component.CustomData.of(compoundtag));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt(SeahorseDataKeys.SEAHORSE_TYPE, this.getSeahorseType());
        compound.putInt(SeahorseDataKeys.SEAHORSE_SIZE, this.getSeahorseSize());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setSeahorseType(compound.getInt(SeahorseDataKeys.SEAHORSE_TYPE));
        this.setSeahorseSize(compound.getInt(SeahorseDataKeys.SEAHORSE_SIZE));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData) {
        spawnData = super.finalizeSpawn(level, difficulty, reason, spawnData);
        RandomSource rand = level.getRandom();
        int type = SeahorseVariantCatalog.randomTypeId(rand.nextInt(SeahorseVariantCatalog.MAX_TYPE));
        int size = SeahorseVariantCatalog.randomSizeId(rand.nextInt(SeahorseVariantCatalog.MAX_SIZE));
        this.setSeahorseType(type);
        this.setSeahorseSize(size);
        return spawnData;
    }

    @Override
    public void loadFromBucketTag(CompoundTag tag) {
        super.loadFromBucketTag(tag);
        if (tag.contains(BucketEntityDataKeys.BUCKET_VARIANT_TAG, Tag.TAG_INT)) {
            this.setSeahorseType(tag.getInt(BucketEntityDataKeys.BUCKET_VARIANT_TAG));
        }
        if (tag.contains(BucketEntityDataKeys.BUCKET_SIZE_TAG, Tag.TAG_INT)) {
            this.setSeahorseSize(tag.getInt(BucketEntityDataKeys.BUCKET_SIZE_TAG));
        }
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(PandorasCreaturesCommon.platform().registry().item(SEAHORSE_BUCKET_ID));
    }

    protected boolean canRandomSwim() {
        return true;
    }

    public static String getNameById(int id) {
        return SeahorseVariantCatalog.variantTooltipKey(id);
    }

    public static String getSizeById(int id) {
        return SeahorseVariantCatalog.sizeTooltipKey(id);
    }

    public int getSeahorseType() {
        return SeahorseVariantCatalog.normalizeType(this.entityData.get(SEAHORSE_TYPE));
    }

    public int getSeahorseSize() {
        return SeahorseVariantCatalog.normalizeSize(this.entityData.get(SEAHORSE_SIZE));
    }

    public void setSeahorseType(int typeId) {
        this.entityData.set(SEAHORSE_TYPE, SeahorseVariantCatalog.normalizeType(typeId));
    }

    public void setSeahorseSize(int typeId) {
        this.entityData.set(SEAHORSE_SIZE, SeahorseVariantCatalog.normalizeSize(typeId));
    }

    /**
     * The Movement Controller.
     */
    static class SeahorseMoveControl extends MoveControl {
        private final SeahorseEntity seahorse;

        SeahorseMoveControl(SeahorseEntity seahorse) {
            super(seahorse);
            this.seahorse = seahorse;
        }

        @Override
        public void tick() {
            if (this.seahorse.isEyeInFluid(FluidTags.WATER)) {
                this.seahorse.setDeltaMovement(this.seahorse.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

            if (this.operation == MoveControl.Operation.MOVE_TO && !this.seahorse.getNavigation().isDone()) {
                double d0 = this.wantedX - this.seahorse.getX();
                double d1 = this.wantedY - this.seahorse.getY();
                double d2 = this.wantedZ - this.seahorse.getZ();
                double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float) (Mth.atan2(d2, d0) * (180F / (float) Math.PI)) - 90.0F;
                this.seahorse.setYRot(this.rotlerp(this.seahorse.getYRot(), f, 90.0F));
                this.seahorse.yBodyRot = this.seahorse.getYRot();
                float f1 = (float) (this.speedModifier * this.seahorse.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.seahorse.setSpeed(Mth.lerp(0.125F, this.seahorse.getSpeed(), f1));
                this.seahorse.setDeltaMovement(this.seahorse.getDeltaMovement().add(0.0D, (double) this.seahorse.getSpeed() * d1 * 0.1D, 0.0D));
            } else {
                this.seahorse.setSpeed(0.0F);
            }
        }
    }

    /**
     * The Swim Goal.
     */
    static class SeahorseSwimGoal extends RandomSwimmingGoal {
        private final SeahorseEntity seahorse;

        public SeahorseSwimGoal(SeahorseEntity seahorse) {
            super(seahorse, 1.25D, 40);
            this.seahorse = seahorse;
        }

        @Override
        public boolean canUse() {
            return this.seahorse.canRandomSwim() && super.canUse();
        }
    }
}
