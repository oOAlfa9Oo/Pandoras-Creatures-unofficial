package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.entities.bases.AnimatedMonsterEntity;
import andrews.pandoras_creatures.entities.goals.acidic_archvine.TargetUnderneathGoal;
import andrews.pandoras_creatures.registry.PCEntities;
import andrews.pandoras_creatures.registry.PCItems;
import andrews.pandoras_creatures.util.animation.Animation;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class AcidicArchvineEntity extends AnimatedMonsterEntity {
    private static final EntityDataAccessor<Integer> ARCHVINE_TYPE = SynchedEntityData.defineId(AcidicArchvineEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TARGET_ENTITY = SynchedEntityData.defineId(AcidicArchvineEntity.class, EntityDataSerializers.INT);
    private LivingEntity targetedEntity;
    private int attackState;

    public AcidicArchvineEntity(EntityType<? extends AcidicArchvineEntity> type, Level level) {
        super(type, level);
    }

    public AcidicArchvineEntity(Level level, double posX, double posY, double posZ) {
        this(PCEntities.ACIDIC_ARCHVINE.get(), level);
        this.moveTo(posX, posY, posZ);
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(1, new TargetUnderneathGoal<>(this, Player.class, true));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(TARGET_ENTITY, 0);
        builder.define(ARCHVINE_TYPE, 0);
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(PCItems.ACIDIC_ARCHVINE_SPAWN_EGG.get());
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[0];
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("ArchvineType", this.getArchvineType());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setArchvineType(compound.getInt("ArchvineType"));
    }

    public void setTargetedEntity(int entityId) {
        this.entityData.set(TARGET_ENTITY, entityId);
    }

    public boolean hasTargetedEntity() {
        return this.entityData.get(TARGET_ENTITY) != 0;
    }

    @Nullable
    public LivingEntity getTargetedEntity() {
        if (!this.hasTargetedEntity()) {
            return null;
        } else if (this.level().isClientSide()) {
            if (this.targetedEntity != null) {
                return this.targetedEntity;
            } else {
                Entity entity = this.level().getEntity(this.entityData.get(TARGET_ENTITY));
                if (entity instanceof LivingEntity living) {
                    this.targetedEntity = living;
                    return this.targetedEntity;
                } else {
                    return null;
                }
            }
        } else {
            return this.getTarget();
        }
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        float damage = (float) (4 + this.random.nextInt(3));
        boolean flag = target.hurt(this.damageSources().mobAttack(this), damage);
        return flag;
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);
        if (TARGET_ENTITY.equals(key)) {
            this.targetedEntity = null;
        }
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData) {
        spawnData = super.finalizeSpawn(level, difficulty, reason, spawnData);
        int type = this.getTypeForBiome(level);
        this.setArchvineType(type);

        BlockPos pos = this.blockPosition();
        // Moves the plant up or down by half a Block, depending on the position it got placed at
        if (!level.getBlockState(pos.above(2)).is(Blocks.JUNGLE_LEAVES) && !level.getBlockState(pos.above(2)).is(Blocks.NETHERRACK) &&
            !level.getBlockState(pos.above()).is(Blocks.JUNGLE_LEAVES) && !level.getBlockState(pos.above()).is(Blocks.NETHERRACK)) {
            this.hurt(this.damageSources().cramming(), Float.MAX_VALUE);
        } else if (level.getBlockState(pos.above()).is(Blocks.JUNGLE_LEAVES) || level.getBlockState(pos.above()).is(Blocks.NETHERRACK)) {
            this.setPos(this.getX(), this.getY() - 0.5, this.getZ());
        } else if (level.getBlockState(pos.above()).isAir() &&
                  (level.getBlockState(pos.above(2)).is(Blocks.JUNGLE_LEAVES) || level.getBlockState(pos.above(2)).is(Blocks.NETHERRACK))) {
            this.setPos(this.getX(), this.getY() + 0.5, this.getZ());
        }

        return spawnData;
    }

    private int getTypeForBiome(ServerLevelAccessor level) {
        // Check biome for nether types
        var biome = level.getBiome(this.blockPosition());
        String biomeName = biome.unwrapKey().map(key -> key.location().toString()).orElse("");

        if (biomeName.contains("nether_wastes") || biomeName.contains("soul_sand_valley") ||
            biomeName.contains("warped_forest") || biomeName.contains("basalt_deltas")) {
            return 2;
        } else if (biomeName.contains("crimson_forest")) {
            return 3;
        }
        return 1;
    }

    @Override
    public void tick() {
        super.tick();
        BlockPos pos = this.blockPosition();
        if (this.level().getBlockState(pos.above(2)).is(Blocks.JUNGLE_LEAVES) ||
            this.level().getBlockState(pos.above(2)).is(Blocks.NETHERRACK)) {
            this.setDeltaMovement(Vec3.ZERO);
        }
    }

    @Override
    public void die(DamageSource cause) {
        super.die(cause);
        if (this.getTarget() != null) {
            this.getTarget().setNoGravity(false);
            if (this.getTarget() instanceof ServerPlayer serverPlayer && !serverPlayer.isCreative()) {
                serverPlayer.getAbilities().mayfly = false;
                serverPlayer.onUpdateAbilities();
            }
        }
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    protected void doPush(Entity entity) {
    }

    @Override
    protected void pushEntities() {
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 1;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void handleEntityEvent(byte id) {
        if (id == 4) {
            this.attackState = 0;
        } else if (id == 5) {
            this.attackState = 1;
        } else if (id == 6) {
            this.attackState = 2;
        } else {
            super.handleEntityEvent(id);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public int getAttackState() {
        return this.attackState;
    }

    public int getArchvineType() {
        if (this.entityData.get(ARCHVINE_TYPE) == 0) {
            this.entityData.set(ARCHVINE_TYPE, 1);
            return this.entityData.get(ARCHVINE_TYPE);
        } else {
            return this.entityData.get(ARCHVINE_TYPE);
        }
    }

    public void setArchvineType(int typeId) {
        this.entityData.set(ARCHVINE_TYPE, typeId);
    }

    public void setAttackState(int value) {
        this.attackState = value;
    }
}
