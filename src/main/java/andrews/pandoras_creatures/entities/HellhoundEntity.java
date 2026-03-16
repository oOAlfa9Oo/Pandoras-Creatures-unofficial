package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.entities.bases.AnimatedMonsterEntity;
import andrews.pandoras_creatures.entities.goals.hellhound.HellHoundAttack;
import andrews.pandoras_creatures.registry.PCEntities;
import andrews.pandoras_creatures.registry.PCItems;
import andrews.pandoras_creatures.registry.PCSounds;
import andrews.pandoras_creatures.util.animation.Animation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
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
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.HitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class HellhoundEntity extends AnimatedMonsterEntity {
    private static final EntityDataAccessor<Integer> HELLHOUND_TYPE = SynchedEntityData.defineId(HellhoundEntity.class, EntityDataSerializers.INT);
    private int isCharging;

    public HellhoundEntity(EntityType<? extends HellhoundEntity> type, Level level) {
        super(type, level);
    }

    public HellhoundEntity(Level level, double posX, double posY, double posZ) {
        this(PCEntities.HELLHOUND.get(), level);
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
        builder.define(HELLHOUND_TYPE, 0);
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(PCItems.HELLHOUND_SPAWN_EGG.get());
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[0];
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("HellhoundType", this.getHellhoundType());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setHellhoundType(compound.getInt("HellhoundType"));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData) {
        spawnData = super.finalizeSpawn(level, difficulty, reason, spawnData);
        RandomSource rand = level.getRandom();
        int type = 1;
        if ((rand.nextInt(12) + 1) == 12) {
            type = 2;
        }
        this.setHellhoundType(type);
        return spawnData;
    }

    /**
     * Used to drop additional items on the entities death
     */
    @Override
    protected void dropCustomDeathLoot(ServerLevel level, DamageSource source, boolean recentlyHit) {
        super.dropCustomDeathLoot(level, source, recentlyHit);
        if (this.getHellhoundType() == 2) {
            this.spawnAtLocation(new ItemStack(Items.COAL, this.random.nextInt(4) + 1));
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void handleEntityEvent(byte id) {
        if (id == 4) {
            this.isCharging = 1;
        } else if (id == 5) {
            this.isCharging = 0;
        } else {
            super.handleEntityEvent(id);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public int getIsCharging() {
        return this.isCharging;
    }

    public void setIsCharging(int value) {
        this.isCharging = value;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            RandomSource rand = this.random;

            if ((this.tickCount % 5) == 0) {
                double particleY = this.getY() + 0.8D;
                if (this.getHellhoundType() == 2) {
                    particleY += 0.2D;
                    this.level().addParticle(ParticleTypes.SOUL_FIRE_FLAME, this.getX(), particleY, this.getZ(), (rand.nextDouble() - 0.5D) / 10, (rand.nextDouble() - 0.5D) / 10, (rand.nextDouble() - 0.5D) / 10);
                } else {
                    this.level().addParticle(ParticleTypes.FLAME, this.getX(), particleY, this.getZ(), (rand.nextDouble() - 0.5D) / 10, (rand.nextDouble() - 0.5D) / 10, (rand.nextDouble() - 0.5D) / 10);
                }
                this.level().addParticle(ParticleTypes.SMOKE, this.getX(), particleY, this.getZ(), (rand.nextDouble() - 0.5D) / 10, (rand.nextDouble() - 0.5D) / 10, (rand.nextDouble() - 0.5D) / 10);
            }
        }
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        boolean flag;
        if (this.getHellhoundType() == 2) {
            flag = target.hurt(this.damageSources().mobAttack(this), (float) (4 + this.random.nextInt(5)));
            if (target instanceof LivingEntity living) {
                living.addEffect(new MobEffectInstance(MobEffects.WITHER, 60));
            }
        } else {
            flag = target.hurt(this.damageSources().mobAttack(this), (float) (2 + this.random.nextInt(3)));
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
        return PCSounds.HELLHOUND_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return PCSounds.HELLHOUND_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return PCSounds.HELLHOUND_DEATH.get();
    }

    @Override
    protected float getSoundVolume() {
        return 0.8F;
    }

    /**
     * Used to get the current Hellhound type, if there is none a random type will be generated
     * @return - The Hellhound type Id
     */
    public int getHellhoundType() {
        if (this.entityData.get(HELLHOUND_TYPE) == 0) {
            RandomSource rand = this.random;
            if ((rand.nextInt(12) + 1) == 12) {
                this.entityData.set(HELLHOUND_TYPE, 2);
            } else {
                this.entityData.set(HELLHOUND_TYPE, 1);
            }
            return this.entityData.get(HELLHOUND_TYPE);
        } else {
            return this.entityData.get(HELLHOUND_TYPE);
        }
    }

    /**
     * Used to set the Hellhound type, different hellhound types use different textures
     * @param typeId - the Hellhound type the entity should become
     */
    public void setHellhoundType(int typeId) {
        this.entityData.set(HELLHOUND_TYPE, typeId);
    }
}
