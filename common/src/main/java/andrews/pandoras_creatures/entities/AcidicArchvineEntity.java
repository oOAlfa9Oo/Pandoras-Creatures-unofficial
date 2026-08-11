package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.entities.acidic_archvine.AcidicArchvineAttackState;
import andrews.pandoras_creatures.entities.acidic_archvine.AcidicArchvineDataKeys;
import andrews.pandoras_creatures.entities.acidic_archvine.AcidicArchvinePlacementRules;
import andrews.pandoras_creatures.entities.acidic_archvine.AcidicArchvineSpawnRules;
import andrews.pandoras_creatures.entities.bases.AnimatedMonsterEntity;
import andrews.pandoras_creatures.entities.goals.acidic_archvine.TargetUnderneathGoal;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.registry.item.PCItemIds;
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
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class AcidicArchvineEntity extends AnimatedMonsterEntity {
    private static final EntityDataAccessor<Integer> ARCHVINE_TYPE = SynchedEntityData.defineId(AcidicArchvineEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TARGET_ENTITY = SynchedEntityData.defineId(AcidicArchvineEntity.class, EntityDataSerializers.INT);
    private LivingEntity targetedEntity;
    private AcidicArchvineAttackState attackState = AcidicArchvineAttackState.IDLE;

    public AcidicArchvineEntity(EntityType<? extends AcidicArchvineEntity> type, Level level) {
        super(type, level);
    }

    public AcidicArchvineEntity(Level level, double posX, double posY, double posZ) {
        this(PandorasCreaturesCommon.platform().registry().entityType(PCEntityIds.ACIDIC_ARCHVINE), level);
        this.snapTo(posX, posY, posZ);
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(1, new TargetUnderneathGoal<>(this, Player.class, true));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(TARGET_ENTITY, 0);
        builder.define(ARCHVINE_TYPE, AcidicArchvinePlacementRules.DEFAULT_ARCHVINE_TYPE);
    }

    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(PandorasCreaturesCommon.platform().registry().item(PCItemIds.ACIDIC_ARCHVINE_SPAWN_EGG));
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[0];
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt(AcidicArchvineDataKeys.ARCHVINE_TYPE, this.getArchvineType());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setArchvineType(compound.getIntOr(AcidicArchvineDataKeys.ARCHVINE_TYPE, 0));
    }

    public void setTargetedEntity(int entityId) {
        this.entityData.set(TARGET_ENTITY, entityId);
    }

    public void clearTargetedEntity() {
        this.setTargetedEntity(0);
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
    public boolean doHurtTarget(ServerLevel level, Entity target) {
        float damage = (float) (4 + this.random.nextInt(3));
        return target.hurtServer(level, this.damageSources().mobAttack(this), damage);
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
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason reason, @Nullable SpawnGroupData spawnData) {
        spawnData = super.finalizeSpawn(level, difficulty, reason, spawnData);
        this.setArchvineType(resolveArchvineType(level));

        BlockPos pos = this.blockPosition();
        boolean immediateCeiling = isSupportedCeiling(level.getBlockState(pos.above()));
        boolean upperCeiling = isSupportedCeiling(level.getBlockState(pos.above(2)));
        double yOffset = AcidicArchvinePlacementRules.resolveSpawnYOffset(immediateCeiling, upperCeiling);
        if (Double.isNaN(yOffset)) {
            if (this.level() instanceof ServerLevel serverLevel) {
                this.hurtServer(serverLevel, this.damageSources().cramming(), Float.MAX_VALUE);
            }
        } else if (yOffset != 0.0D) {
            this.setPos(this.getX(), this.getY() + yOffset, this.getZ());
        }

        if (spawnData instanceof AcidicArchvineSpawnData archvineSpawnData && archvineSpawnData.allowCompanionSpawn()) {
            this.trySpawnCompanion(level, difficulty, reason);
            return archvineSpawnData.withCompanionSpawnDisabled();
        }

        if (spawnData == null) {
            AcidicArchvineSpawnData newSpawnData = new AcidicArchvineSpawnData(true);
            this.trySpawnCompanion(level, difficulty, reason);
            return newSpawnData.withCompanionSpawnDisabled();
        }

        return spawnData;
    }

    private void trySpawnCompanion(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason reason) {
        if (!AcidicArchvineSpawnRules.shouldAttemptCompanion(reason)) {
            return;
        }
        if (this.random.nextFloat() > AcidicArchvineSpawnRules.companionChance(difficulty.getDifficulty())) {
            return;
        }

        for (int attempt = 0; attempt < AcidicArchvineSpawnRules.companionAttempts(difficulty.getDifficulty()); attempt++) {
            BlockPos candidatePos = AcidicArchvineSpawnRules.randomNearbyPosition(this.blockPosition(), this.random);
            if (!AcidicArchvineSpawnRules.isValidCompanionSpawn(level, candidatePos)) {
                continue;
            }

            AcidicArchvineEntity companion = new AcidicArchvineEntity(level.getLevel(), candidatePos.getX() + 0.5D, candidatePos.getY(), candidatePos.getZ() + 0.5D);
            companion.finalizeSpawn(level, level.getCurrentDifficultyAt(candidatePos), reason, AcidicArchvineSpawnData.noCompanionSpawn());
            if (!level.addFreshEntity(companion)) {
                continue;
            }
            return;
        }
    }

    private int resolveArchvineType(ServerLevelAccessor level) {
        var biome = level.getBiome(this.blockPosition());
        String biomeName = biome.unwrapKey().map(key -> key.location().toString()).orElse("");
        return AcidicArchvinePlacementRules.resolveBiomeType(biomeName);
    }

    private boolean isSupportedCeiling(BlockState blockState) {
        return blockState.is(Blocks.JUNGLE_LEAVES) || blockState.is(Blocks.NETHERRACK);
    }

    @Override
    public void tick() {
        super.tick();
        BlockPos pos = this.blockPosition();
        if (AcidicArchvinePlacementRules.shouldLockVerticalMotion(isSupportedCeiling(this.level().getBlockState(pos.above(2))))) {
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

    @Override
    public void handleEntityEvent(byte id) {
        AcidicArchvineAttackState updatedState = AcidicArchvineAttackState.fromEventId(id);
        if (updatedState == null) {
            super.handleEntityEvent(id);
            return;
        }

        this.attackState = updatedState;
    }

    public AcidicArchvineAttackState getAttackState() {
        return this.attackState;
    }

    public int getArchvineType() {
        return this.entityData.get(ARCHVINE_TYPE);
    }

    public void setArchvineType(int typeId) {
        this.entityData.set(ARCHVINE_TYPE, typeId);
    }

    public void setAttackState(AcidicArchvineAttackState state) {
        this.attackState = state;
    }

    private record AcidicArchvineSpawnData(boolean allowCompanionSpawn) implements SpawnGroupData {
        private static AcidicArchvineSpawnData noCompanionSpawn() {
            return new AcidicArchvineSpawnData(false);
        }

        private AcidicArchvineSpawnData withCompanionSpawnDisabled() {
            return noCompanionSpawn();
        }
    }
}
