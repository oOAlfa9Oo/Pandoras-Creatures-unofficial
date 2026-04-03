package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.entities.bases.AnimatedMonsterEntity;
import andrews.pandoras_creatures.entities.goals.hellhound.HellHoundAttack;
import andrews.pandoras_creatures.entities.hellhound.HellhoundChargeState;
import andrews.pandoras_creatures.entities.hellhound.HellhoundCombatRules;
import andrews.pandoras_creatures.entities.hellhound.HellhoundDataKeys;
import andrews.pandoras_creatures.entities.hellhound.HellhoundVariantCatalog;
import andrews.pandoras_creatures.entities.hellhound.HellhoundVisualRules;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.registry.item.PCItemIds;
import andrews.pandoras_creatures.registry.sound.PCSoundCatalog;
import andrews.pandoras_creatures.util.animation.Animation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nullable;

public class HellhoundEntity extends AnimatedMonsterEntity {
    private static final EntityDataAccessor<Integer> HELLHOUND_TYPE = SynchedEntityData.defineId(HellhoundEntity.class, EntityDataSerializers.INT);
    private int isCharging;

    public HellhoundEntity(EntityType<? extends HellhoundEntity> type, Level level) {
        super(type, level);
    }

    public HellhoundEntity(Level level, double posX, double posY, double posZ) {
        this(PandorasCreaturesCommon.platform().registry().entityType(PCEntityIds.HELLHOUND), level);
        this.moveTo(posX, posY, posZ);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new HellHoundAttack(this, 0.7D, false));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.4D, 0.01F));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(HELLHOUND_TYPE, HellhoundVariantCatalog.DEFAULT_TYPE);
    }

    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(PandorasCreaturesCommon.platform().registry().item(PCItemIds.HELLHOUND_SPAWN_EGG));
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[0];
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt(HellhoundDataKeys.TYPE_TAG, this.getHellhoundType());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setHellhoundType(compound.getInt(HellhoundDataKeys.TYPE_TAG));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData) {
        spawnData = super.finalizeSpawn(level, difficulty, reason, spawnData);
        this.setHellhoundType(HellhoundVariantCatalog.randomTypeId(level.getRandom()));
        return spawnData;
    }

    @Override
    protected void dropCustomDeathLoot(ServerLevel level, DamageSource source, boolean recentlyHit) {
        super.dropCustomDeathLoot(level, source, recentlyHit);
        int coalDropCount = HellhoundCombatRules.coalDropCount(this.getHellhoundType(), this.random);
        if (coalDropCount > 0) {
            this.spawnAtLocation(new ItemStack(Items.COAL, coalDropCount));
        }
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == HellhoundChargeState.START_EVENT_ID) {
            this.setIsCharging(HellhoundChargeState.CHARGING);
        } else if (id == HellhoundChargeState.STOP_EVENT_ID) {
            this.setIsCharging(HellhoundChargeState.IDLE);
        } else {
            super.handleEntityEvent(id);
        }
    }

    public int getIsCharging() {
        return this.isCharging;
    }

    public void setIsCharging(int value) {
        this.isCharging = HellhoundChargeState.normalize(value);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            RandomSource rand = this.random;
            if ((this.tickCount % 5) == 0) {
                int hellhoundType = this.getHellhoundType();
                double particleY = HellhoundVisualRules.particleY(this.getY(), hellhoundType);
                this.level().addParticle(HellhoundVisualRules.usesSoulFire(hellhoundType) ? ParticleTypes.SOUL_FIRE_FLAME : ParticleTypes.FLAME, this.getX(), particleY, this.getZ(), (rand.nextDouble() - 0.5D) / 10, (rand.nextDouble() - 0.5D) / 10, (rand.nextDouble() - 0.5D) / 10);
                this.level().addParticle(ParticleTypes.SMOKE, this.getX(), particleY, this.getZ(), (rand.nextDouble() - 0.5D) / 10, (rand.nextDouble() - 0.5D) / 10, (rand.nextDouble() - 0.5D) / 10);
            }
        }
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        int hellhoundType = this.getHellhoundType();
        boolean flag = target.hurt(this.damageSources().mobAttack(this), (float) HellhoundCombatRules.attackDamage(hellhoundType, this.random));
        if (flag && HellhoundCombatRules.appliesWither(hellhoundType) && target instanceof LivingEntity living) {
            int witherDuration = HellhoundCombatRules.witherDurationTicks(hellhoundType);
            if (witherDuration > 0) {
                living.addEffect(new MobEffectInstance(MobEffects.WITHER, witherDuration));
            }
        }
        return flag;
    }

    @Override
    public int getBaseExperienceReward() {
        this.xpReward = (int) ((float) this.xpReward * 2.0F);
        return super.getBaseExperienceReward();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return PandorasCreaturesCommon.platform().registry().sound(PCSoundCatalog.HELLHOUND_AMBIENT);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return PandorasCreaturesCommon.platform().registry().sound(PCSoundCatalog.HELLHOUND_HURT);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return PandorasCreaturesCommon.platform().registry().sound(PCSoundCatalog.HELLHOUND_DEATH);
    }

    @Override
    protected float getSoundVolume() {
        return 0.8F;
    }

    public int getHellhoundType() {
        int hellhoundType = HellhoundVariantCatalog.normalizeType(this.entityData.get(HELLHOUND_TYPE));
        if (this.entityData.get(HELLHOUND_TYPE) != hellhoundType) {
            this.entityData.set(HELLHOUND_TYPE, hellhoundType);
        }
        return hellhoundType;
    }

    public void setHellhoundType(int typeId) {
        this.entityData.set(HELLHOUND_TYPE, HellhoundVariantCatalog.normalizeType(typeId));
    }
}
