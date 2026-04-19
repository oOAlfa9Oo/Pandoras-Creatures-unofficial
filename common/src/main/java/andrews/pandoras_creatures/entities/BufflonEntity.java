package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.entities.bases.AnimatedCreatureEntity;
import andrews.pandoras_creatures.entities.bufflon.BufflonBackAttachmentItems;
import andrews.pandoras_creatures.entities.bufflon.BufflonBackAttachmentType;
import andrews.pandoras_creatures.entities.bufflon.BufflonAccess;
import andrews.pandoras_creatures.entities.bufflon.BufflonCombatRules;
import andrews.pandoras_creatures.entities.bufflon.BufflonDataKeys;
import andrews.pandoras_creatures.entities.bufflon.BufflonInteractionPolicy;
import andrews.pandoras_creatures.entities.bufflon.BufflonInventoryLayout;
import andrews.pandoras_creatures.entities.bufflon.BufflonHandle;
import andrews.pandoras_creatures.entities.bufflon.BufflonOwnership;
import andrews.pandoras_creatures.entities.bufflon.BufflonPassengerLayout;
import andrews.pandoras_creatures.entities.bufflon.BufflonPassengerMotion;
import andrews.pandoras_creatures.entities.bufflon.BufflonPassengerOffset;
import andrews.pandoras_creatures.entities.goals.bufflon.*;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.registry.item.PCItemIds;
import andrews.pandoras_creatures.registry.sound.PCSoundCatalog;
import andrews.pandoras_creatures.util.animation.Animation;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.PlayerTeam;

import javax.annotation.Nullable;
import java.util.UUID;

public class BufflonEntity extends AnimatedCreatureEntity implements BufflonAccess {
    private static final int FEEDING_COOLDOWN_TICKS = 10;
    private static final int TAMING_THINK_TIME_TICKS = 40;
    private static final int NATURAL_REGEN_CHANCE = 900;
    private static final int TAMING_SUCCESS_CHANCE = 4;
    private static final int HERB_TAMING_CHANCE = 3;
    private static final int BUFFLON_VARIANT_COUNT = 7;
    private static final byte FAILED_TAME_EVENT = 6;
    private static final byte SUCCESSFUL_TAME_EVENT = 7;
    private static final double TARGET_KNOCKBACK_STRENGTH = 0.15D;
    private static final double PASSENGER_THROW_HORIZONTAL_SPEED = 0.3D;
    private static final double PASSENGER_THROW_VERTICAL_SPEED = 1.2D;

    // Stores the Bufflon type
    private static final EntityDataAccessor<Integer> BUFFLON_TYPE = SynchedEntityData.defineId(BufflonEntity.class, EntityDataSerializers.INT);
    // Stores whether or not the Bufflon is tamed and the UUID of the owner
    private static final EntityDataAccessor<Byte> TAMED = SynchedEntityData.defineId(BufflonEntity.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<String> OWNER_UNIQUE_ID = SynchedEntityData.defineId(BufflonEntity.class, EntityDataSerializers.STRING);
    // Store the information of the attachments
    private static final EntityDataAccessor<Boolean> IS_SADDLED = SynchedEntityData.defineId(BufflonEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> BACK_ATTACHMENT_TYPE = SynchedEntityData.defineId(BufflonEntity.class, EntityDataSerializers.INT);
    // Stores whether or not the Bufflon is sitting
    private static final EntityDataAccessor<Boolean> IS_SITTING = SynchedEntityData.defineId(BufflonEntity.class, EntityDataSerializers.BOOLEAN);
    // Stores whether or not the Bufflon is following
    private static final EntityDataAccessor<Boolean> IS_FOLLOWING = SynchedEntityData.defineId(BufflonEntity.class, EntityDataSerializers.BOOLEAN);
    // Stores whether or not the Bufflon is in combat mode
    private static final EntityDataAccessor<Boolean> COMBAT_MODE = SynchedEntityData.defineId(BufflonEntity.class, EntityDataSerializers.BOOLEAN);

    // The item that can be used in the saddle slot
    public static final String BUFFLON_SADDLE_ITEM_ID = PCItemIds.BUFFLON_SADDLE;

    private int thinkTime;
    private int feedingCooldown;
    public SimpleContainer bufflonStorage;
    public BufflonSitGoal bufflonSitGoal;

    public static final Animation THROW_ANIMATION = new Animation(12);
    public static final Animation ATTACK_HEAD_ANIMATION = new Animation(12);

    public BufflonEntity(EntityType<? extends BufflonEntity> type, Level level) {
        super(type, level);
        // Step height is now handled via Attributes.STEP_HEIGHT in entity attributes
        this.initBufflonStorage();
    }

    public BufflonEntity(Level level, double posX, double posY, double posZ) {
        this(PandorasCreaturesCommon.platform().registry().entityType(PCEntityIds.BUFFLON), level);
        this.setPos(posX, posY, posZ);
    }

    @Override
    protected void registerGoals() {
        // Goal Selector
        this.bufflonSitGoal = new BufflonSitGoal(this);
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, this.bufflonSitGoal);
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.5D));
        this.goalSelector.addGoal(4, new BufflonMeleeAttackGoal(this, 0.55D, true));
        this.goalSelector.addGoal(5, new BufflonFollowOwnerGoal(this, 0.55D, 10.0F, 2.0F));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        // Target Selector
        this.targetSelector.addGoal(1, new BufflonOwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new BufflonOwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(4, new BufflonNonTamedTargetGoal<>(this, Player.class, false));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(BUFFLON_TYPE, 0);
        builder.define(TAMED, (byte) 0);
        builder.define(OWNER_UNIQUE_ID, "");
        builder.define(IS_SADDLED, false);
        builder.define(BACK_ATTACHMENT_TYPE, 0);
        builder.define(IS_SITTING, false);
        builder.define(IS_FOLLOWING, false);
        builder.define(COMBAT_MODE, false);
    }

    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(PandorasCreaturesCommon.platform().registry().item(PCItemIds.BUFFLON_SPAWN_EGG));
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        savePersistentState(output);
        saveInventory(output);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        loadPersistentState(input);
        loadInventory(input);
        this.updateBufflonSlots();
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason reason, @Nullable SpawnGroupData spawnData) {
        spawnData = super.finalizeSpawn(level, difficulty, reason, spawnData);
        RandomSource rand = level.getRandom();
        int type = rand.nextInt(BUFFLON_VARIANT_COUNT) + 1;
        this.setBufflonType(type);
        return spawnData;
    }

    @Override
    public boolean canBeLeashed() {
        return false;
    }

    @Override
    protected int getBaseExperienceReward(ServerLevel serverLevel) {
        return 1 + this.random.nextInt(3);
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        BufflonInteractionPolicy.MobInteractAction action = BufflonInteractionPolicy.resolveMobInteractAction(
                this.isTamed(),
                itemstack.is(PandorasCreaturesCommon.platform().registry().item(PCItemIds.HERB_BUNDLE)),
                itemstack.isEmpty(),
                this.isSaddled(),
                this.hasBackAttachment(),
                itemstack.is(PandorasCreaturesCommon.platform().registry().item(BUFFLON_SADDLE_ITEM_ID)),
                BufflonBackAttachmentItems.isSupported(itemstack)
        );

        switch (action) {
            case HANDLE_HERB_BUNDLE -> {
                handleHerbBundleInteraction(player, itemstack);
                return this.level().isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
            }
            case OPEN_EQUIPMENT_MENU -> {
                this.openGUI(player);
                return this.level().isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
            }
            case HANDLE_EMPTY_HAND -> {
                handleEmptyHandInteraction(player);
                return this.level().isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
            }
            case PASS_TO_SUPER -> {
                return super.mobInteract(player, hand);
            }
        }

        return super.mobInteract(player, hand);
    }

    @Override
    public boolean doHurtTarget(ServerLevel serverLevel, Entity target) {
        boolean flag = target.hurtOrSimulate(this.damageSources().mobAttack(this), (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE));
        // The attack sound
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                PandorasCreaturesCommon.platform().registry().sound(PCSoundCatalog.BUFFLON_ATTACK),
                this.getSoundSource(), 0.6F, 0.8F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
        // The attack animation
        if (this.isAnimationPlaying(BLANK_ANIMATION) && !this.level().isClientSide()) {
            PandorasCreaturesCommon.platform().entities().syncAnimation(this, ATTACK_HEAD_ANIMATION);
        }

        if (flag) {
            target.setDeltaMovement(target.getDeltaMovement().add(
                    BufflonCombatRules.getAttackKnockback(this.position(), target.position(), TARGET_KNOCKBACK_STRENGTH)
            ));
            target.hurtMarked = true;
            // Enchantment damage effects are now applied automatically
        }
        return flag;
    }

    /**
     * Used in Goals to avoid fighting of tamed entities
     */
    public boolean shouldAttackEntity(LivingEntity target, LivingEntity owner) {
        boolean alliedTamedBufflon = target instanceof BufflonEntity bufflon && bufflon.isTamed() && bufflon.getOwner() == owner;
        return BufflonCombatRules.shouldAttackTarget(BufflonCombatRules.describeTarget(target, owner, alliedTamedBufflon));
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean hurtServer(ServerLevel serverLevel, DamageSource source, float amount) {
        if (this.isInvulnerableTo(serverLevel, source)) {
            return false;
        } else {
            Entity entity = source.getEntity();
            if (this.bufflonSitGoal != null && BufflonCombatRules.shouldInterruptSitOnDamage(this.isInCombatMode())) {
                this.bufflonSitGoal.setSitting(false);
            }

            amount = BufflonCombatRules.getAdjustedIncomingDamage(entity, amount);

            return super.hurtServer(serverLevel, source, amount);
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();

        // Makes it so the Bufflon gains health slowly "regeneration"
        if (!this.level().isClientSide() && this.isAlive()) {
            if (this.random.nextInt(NATURAL_REGEN_CHANCE) == 0 && this.deathTime == 0) {
                this.heal(1.0F);
            }
        }

        if (feedingCooldown > 0) {
            feedingCooldown--;
        }

        if (!level().isClientSide() && !this.isTamed() && this.isVehicle()) {
            if (thinkTime > 0) {
                thinkTime--;
            } else {
                if (this.random.nextInt(TAMING_SUCCESS_CHANCE) == 0) {
                    this.setTamedBy((Player) this.getPassengers().get(0));
                    this.navigation.stop();
                    this.setTarget(null);
                    this.broadcastTameResult(true);
                } else {
                    this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                            PandorasCreaturesCommon.platform().registry().sound(PCSoundCatalog.BUFFLON_ATTACK),
                            this.getSoundSource(), 0.6F, 0.8F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
                    // Removes the Passengers
                    for (int i = this.getPassengers().size() - 1; i >= 0; --i) {
                        Entity entity = this.getPassengers().get(i);
                        entity.stopRiding();
                        entity.setDeltaMovement(entity.getDeltaMovement().add((random.nextInt(3) - 1) * PASSENGER_THROW_HORIZONTAL_SPEED, PASSENGER_THROW_VERTICAL_SPEED, (random.nextInt(3) - 1) * PASSENGER_THROW_HORIZONTAL_SPEED));
                        entity.hurtMarked = true;
                        PandorasCreaturesCommon.platform().entities().syncAnimation(this, THROW_ANIMATION);
                    }
                    this.broadcastTameResult(false);
                }
            }
        }
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[]{THROW_ANIMATION, ATTACK_HEAD_ANIMATION};
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return PandorasCreaturesCommon.platform().registry().sound(PCSoundCatalog.BUFFLON_AMBIENT);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return PandorasCreaturesCommon.platform().registry().sound(PCSoundCatalog.BUFFLON_DEATH);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return PandorasCreaturesCommon.platform().registry().sound(PCSoundCatalog.BUFFLON_HURT);
    }

    @Override
    protected float getSoundVolume() {
        return 0.6F;
    }

    /**
     * Gets called when passengers get updated
     */
    @Override
    protected void positionRider(Entity passenger, MoveFunction callback) {
        super.positionRider(passenger, callback);
        if (this.hasPassenger(passenger)) {
            BufflonPassengerOffset offset = BufflonPassengerLayout.getOffset(
                    this.getPassengers().indexOf(passenger),
                    this.isSaddled(),
                    this.isMoving(),
                    this.getPassengerMovement()
            );
            if (offset.lockYaw()) {
                passenger.setYHeadRot(passenger.getYHeadRot());
                this.applyYawToEntity(passenger);
            }

            Vec3 vec3 = (new Vec3(offset.x(), 0.0D, 0.0D)).yRot(-this.getYRot() * ((float) Math.PI / 180F) - ((float) Math.PI / 2F));
            callback.accept(passenger, this.getX() + vec3.x, this.getY() + offset.y(), this.getZ() + vec3.z);
        }
    }

    /**
     * Applies this Bufflon's yaw to the given entity.
     */
    private void applyYawToEntity(Entity entityToUpdate) {
        entityToUpdate.setYBodyRot(this.getYRot());
        float f = Mth.wrapDegrees(entityToUpdate.getYRot() - this.getYRot());
        float f1 = Mth.clamp(f, -105.0F, 105.0F);
        entityToUpdate.yRotO += f1 - f;
        entityToUpdate.setYRot(entityToUpdate.getYRot() + f1 - f);
        entityToUpdate.setYHeadRot(entityToUpdate.getYRot());
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public boolean requiresCustomPersistence() {
        return this.isTamed();
    }

    //======================================================================================================================================================

    protected void initBufflonStorage() {
        SimpleContainer inventory = this.bufflonStorage;
        this.bufflonStorage = new SimpleContainer(this.getInventorySize()) {
            @Override
            public void setChanged() {
                super.setChanged();
                BufflonEntity.this.onBufflonInventoryChanged();
            }
        };
        if (inventory != null) {
            int i = Math.min(inventory.getContainerSize(), this.bufflonStorage.getContainerSize());

            for (int j = 0; j < i; ++j) {
                ItemStack itemstack = inventory.getItem(j);
                if (!itemstack.isEmpty()) {
                    this.bufflonStorage.setItem(j, itemstack.copy());
                }
            }
        }
        this.updateBufflonSlots();
    }

    /**
     * @return - The size of the inventory this Entity has
     */
    public int getInventorySize() {
        return BufflonInventoryLayout.getTotalSlotCount();
    }

    public int getOccupiedStorageSlotCount() {
        int occupiedSlots = 0;
        if (this.bufflonStorage == null) {
            return 0;
        }

        for (int i = BufflonInventoryLayout.FIRST_STORAGE_SLOT; i < this.bufflonStorage.getContainerSize(); ++i) {
            if (!this.bufflonStorage.getItem(i).isEmpty()) {
                occupiedSlots++;
            }
        }
        return occupiedSlots;
    }

    /**
     * Updates the items in the slots of the Bufflon's inventory.
     */
    protected void updateBufflonSlots() {
        if (!this.level().isClientSide()) {
            // Sets the Bufflon to being Saddled
            this.setSaddled(!this.bufflonStorage.getItem(BufflonInventoryLayout.SADDLE_SLOT).isEmpty());
            updateBackAttachmentFromInventorySlot();
        }
    }

    private void onBufflonInventoryChanged() {
        boolean flagIsSaddled = this.isSaddled();
        boolean flagHasBackAttachment = this.hasBackAttachment();

        this.updateBufflonSlots();

        // The Bufflon Saddle Sound
        if (this.tickCount > 20 && !flagIsSaddled && this.isSaddled()) {
            this.playSound(SoundEvents.HORSE_SADDLE.value(), 0.5F, 1.0F);
        }
        // The Bufflon Back Attachments Sound
        if (this.tickCount > 20 && !flagHasBackAttachment && this.hasBackAttachment()) {
            this.playSound(SoundEvents.HORSE_SADDLE.value(), 0.5F, 1.0F);
        }
    }

    @Override
    protected void dropEquipment(ServerLevel serverLevel) {
        super.dropEquipment(serverLevel);
        if (this.bufflonStorage != null) {
            for (int i = 0; i < this.bufflonStorage.getContainerSize(); ++i) {
                ItemStack itemstack = this.bufflonStorage.getItem(i);
                if (!itemstack.isEmpty()) {
                    this.spawnAtLocation(serverLevel, itemstack);
                }
            }
        }
    }

    //======================================================================================================================================================

    @Override
    public void travel(Vec3 vector) {
        if (this.isAlive()) {
            if (this.isVehicle()) {
                LivingEntity livingentity = (LivingEntity) this.getControllingPassenger();
                if (livingentity != null) {
                    this.setYRot(livingentity.getYRot());
                    this.yRotO = this.getYRot();
                    this.setXRot(livingentity.getXRot() * 0.5F);
                    this.setRot(this.getYRot(), this.getXRot());
                    this.yBodyRot = this.getYRot();
                    this.yHeadRot = this.yBodyRot;

                    if (this.isEffectiveAi() && this.isSaddled()) {
                        float f = livingentity.xxa * 0.5F;
                        float f1 = livingentity.zza;
                        if (f1 <= 0.0F) {
                            f1 *= 0.25F;
                        }

                        this.setSpeed(0.2F);
                        super.travel(new Vec3(f, vector.y, f1));
                    } else if (livingentity instanceof Player) {
                        this.setDeltaMovement(Vec3.ZERO);
                    }

                    // Walk animation sync
                    this.calculateEntityAnimation(false);
                }
            } else {
                super.travel(vector);
            }
        }
    }

    @Override
    @Nullable
    public LivingEntity getControllingPassenger() {
        if (this.isSaddled()) {
            Entity entity = this.getFirstPassenger();
            if (entity instanceof LivingEntity living) {
                return living;
            }
        }
        return null;
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        if (!this.isEyeInFluid(FluidTags.WATER)) {
            return this.getPassengers().size() < this.getBackAttachment().getMaxPassengers();
        } else {
            return false;
        }
    }

    //======================================================================================================================================================

    public float getPassengerMovement() {
        return BufflonPassengerMotion.getVerticalOffset(
                this.isMoving(),
                this.tickCount,
                this.walkAnimation.position(),
                this.walkAnimation.speed()
        );
    }

    public boolean isMoving() {
        return this.getX() != xOld || this.getZ() != this.zOld;
    }

    /**
     * Play the taming effect, will either be hearts or smoke depending on status
     */
    protected void playTameEffect(boolean play) {
        ParticleOptions particleOptions = ParticleTypes.HEART;
        if (!play) {
            particleOptions = ParticleTypes.SMOKE;
        }

        for (int i = 0; i < 7; ++i) {
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            this.level().addParticle(particleOptions,
                    this.getX() + (double) (this.random.nextFloat() * this.getBbWidth() * 2.0F) - (double) this.getBbWidth(),
                    this.getY() + 0.5D + (double) (this.random.nextFloat() * this.getBbHeight()),
                    this.getZ() + (double) (this.random.nextFloat() * this.getBbWidth() * 2.0F) - (double) this.getBbWidth(),
                    d0, d1, d2);
        }
    }

    /**
     * Handler for level().broadcastEntityEvent
     */
    @Override
    public void handleEntityEvent(byte id) {
        if (id == SUCCESSFUL_TAME_EVENT) {
            this.playTameEffect(true);
        } else if (id == FAILED_TAME_EVENT) {
            this.playTameEffect(false);
        } else {
            super.handleEntityEvent(id);
        }
    }

    /**
     * Mounts the given player to this entity
     */
    private void mountTo(Player player) {
        if (!this.level().isClientSide()) {
            player.setYRot(this.getYRot());
            player.setXRot(this.getXRot());
            player.startRiding(this);
            this.thinkTime = TAMING_THINK_TIME_TICKS;
        }
    }

    public boolean isInCombatMode() {
        return this.entityData.get(COMBAT_MODE);
    }

    public void setIsInCombatMode(boolean value) {
        this.entityData.set(COMBAT_MODE, value);
    }

    public boolean canProtectOwner() {
        return BufflonCombatRules.canProtectOwner(this.isTamed(), this.isSitting(), this.isInCombatMode());
    }

    public boolean isFollowingOwner() {
        return this.entityData.get(IS_FOLLOWING);
    }

    public void setFollowingOwner(boolean value) {
        this.entityData.set(IS_FOLLOWING, value);
    }

    public boolean isSitting() {
        return this.entityData.get(IS_SITTING);
    }

    public void setSitting(boolean value) {
        this.entityData.set(IS_SITTING, value);
    }

    public void setOrderedToSit(boolean value) {
        if (this.bufflonSitGoal != null) {
            this.bufflonSitGoal.setSitting(value);
        }
        this.setSitting(value);
        if (value) {
            this.getNavigation().stop();
        }
    }

    public boolean isSaddled() {
        return this.entityData.get(IS_SADDLED);
    }

    public void setSaddled(boolean value) {
        this.entityData.set(IS_SADDLED, value);
    }

    public boolean isSaddleable() {
        return this.isAlive() && !this.isBaby() && this.isTamed();
    }

    public void equipSaddle(ItemStack saddle, @Nullable net.minecraft.sounds.SoundSource source) {
        this.bufflonStorage.setItem(BufflonInventoryLayout.SADDLE_SLOT, saddle.copyWithCount(1));
        if (source != null) {
            this.level().playSound(null, this, SoundEvents.HORSE_SADDLE.value(), source, 0.5F, 1.0F);
        }
    }

    public boolean hasBackAttachment() {
        return this.getBackAttachment() != BufflonBackAttachmentType.NONE;
    }

    public int getItemBackAttachmentType(Item item) {
        return BufflonBackAttachmentItems.getType(item).getId();
    }

    public int getBackAttachmentType() {
        return this.entityData.get(BACK_ATTACHMENT_TYPE);
    }

    public BufflonBackAttachmentType getBackAttachment() {
        return BufflonBackAttachmentType.fromId(this.getBackAttachmentType());
    }

    public void setBackAttachment(int backAttachmentType) {
        this.entityData.set(BACK_ATTACHMENT_TYPE, backAttachmentType);
    }

    public void openGUI(Player player) {
        if (!this.level().isClientSide() && (!this.isVehicle() || this.hasPassenger(player)) && this.isTamed()) {
            if (player instanceof ServerPlayer serverPlayer) {
                PandorasCreaturesCommon.platform().menus().openBufflonMenu(serverPlayer, this.getId(), this.getDisplayName());
            }
        }
    }

    @Override
    public void openBufflonMenu(ServerPlayer player) {
        this.openGUI(player);
    }

    public boolean isTamed() {
        return (this.entityData.get(TAMED) & 4) != 0;
    }

    public void setTamed(boolean tamed) {
        byte b0 = this.entityData.get(TAMED);
        if (tamed) {
            this.entityData.set(TAMED, (byte) (b0 | 4));
        } else {
            this.entityData.set(TAMED, (byte) (b0 & -5));
        }
    }

    @Nullable
    public UUID getOwnerId() {
        String ownerId = this.entityData.get(OWNER_UNIQUE_ID);
        if (ownerId == null || ownerId.isBlank()) {
            return null;
        }
        try {
            return UUID.fromString(ownerId);
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }

    public void setOwnerId(@Nullable UUID uuid) {
        this.entityData.set(OWNER_UNIQUE_ID, uuid == null ? "" : uuid.toString());
    }

    public void setTamedBy(Player player) {
        this.setTamed(true);
        this.setOwnerId(player.getUUID());
    }

    @Nullable
    public LivingEntity getOwner() {
        return BufflonOwnership.resolveOwner(this.level(), this.getOwnerId());
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        return !this.isOwner(target) && super.canAttack(target);
    }

    public boolean isOwner(LivingEntity entity) {
        return BufflonOwnership.isOwner(entity, this.getOwner());
    }

    public boolean isOwnedBy(Player player) {
        return BufflonOwnership.isOwnedBy(this.getOwnerId(), player);
    }

    @Override
    public boolean isBufflonTamed() {
        return this.isTamed();
    }

    @Override
    public boolean isBufflonOwnedBy(ServerPlayer player) {
        return this.isOwnedBy(player);
    }

    @Override
    public void setBufflonOrderedToSit(boolean shouldSit) {
        this.setOrderedToSit(shouldSit);
    }

    @Override
    public void setBufflonFollowingOwner(boolean shouldFollow) {
        this.setFollowingOwner(shouldFollow);
    }

    @Override
    public void setBufflonCombatMode(boolean combatMode) {
        this.setIsInCombatMode(combatMode);
    }

    @Override
    public int getBufflonId() {
        return this.getId();
    }

    @Override
    public LivingEntity getBufflonLivingEntity() {
        return this;
    }

    @Override
    public Container getBufflonContainer() {
        return this.bufflonStorage;
    }

    @Override
    public BufflonBackAttachmentType getBufflonBackAttachment() {
        return this.getBackAttachment();
    }

    @Override
    public boolean isBufflonVehicle() {
        return this.isVehicle();
    }

    @Override
    public int getBufflonPassengerCount() {
        return this.getPassengers().size();
    }

    @Override
    public boolean isBufflonSaddled() {
        return this.isSaddled();
    }

    @Override
    public boolean hasBufflonBackAttachment() {
        return this.hasBackAttachment();
    }

    @Override
    public boolean isBufflonSitting() {
        return this.isSitting();
    }

    @Override
    public boolean isBufflonFollowingOwner() {
        return this.isFollowingOwner();
    }

    @Override
    public boolean isBufflonInCombatMode() {
        return this.isInCombatMode();
    }

    @Override
    public boolean isBufflonOwnedBy(Player player) {
        return this.isOwnedBy(player);
    }

    @Override
    public int getBufflonTickCount() {
        return this.tickCount;
    }

    @Override
    public PlayerTeam getTeam() {
        PlayerTeam inheritedTeam = BufflonOwnership.getInheritedTeam(this.isTamed(), this.getOwner());
        if (inheritedTeam != null) {
            return inheritedTeam;
        }
        return super.getTeam();
    }

    @Override
    protected boolean considersEntityAsAlly(Entity entity) {
        if (BufflonOwnership.isAlliedTo(this.isTamed(), this.getOwner(), entity)) {
            return true;
        }
        return super.considersEntityAsAlly(entity);
    }

    @Override
    public void die(DamageSource cause) {
        ServerPlayer deathRecipient = BufflonOwnership.getDeathMessageRecipient(
                !this.level().isClientSide(),
                this.level() instanceof ServerLevel serverLevel && serverLevel.getGameRules().get(GameRules.SHOW_DEATH_MESSAGES),
                this.getOwner()
        );
        if (deathRecipient != null) {
            deathRecipient.sendSystemMessage(this.getCombatTracker().getDeathMessage());
        }
        super.die(cause);
    }

    public int getBufflonType() {
        if (this.entityData.get(BUFFLON_TYPE) == 0) {
            RandomSource rand = this.random;
            this.entityData.set(BUFFLON_TYPE, rand.nextInt(BUFFLON_VARIANT_COUNT) + 1);
            return this.entityData.get(BUFFLON_TYPE);
        } else {
            return this.entityData.get(BUFFLON_TYPE);
        }
    }

    public void setBufflonType(int typeId) {
        this.entityData.set(BUFFLON_TYPE, typeId);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 1;
    }

    private void handleHerbBundleInteraction(Player player, ItemStack itemstack) {
        if (this.level().isClientSide()) {
            return;
        }

        if (!this.isTamed()) {
            handleUntamedHerbBundleInteraction(player, itemstack);
        } else {
            handleTamedHerbBundleInteraction(player, itemstack);
        }
    }

    private void handleUntamedHerbBundleInteraction(Player player, ItemStack itemstack) {
        if (feedingCooldown > 0) {
            return;
        }

        feedingCooldown = FEEDING_COOLDOWN_TICKS;
        consumeHerbBundle(player, itemstack);

        if (this.random.nextInt(HERB_TAMING_CHANCE) == 0) {
            mountTo(player);
        } else {
            broadcastTameResult(false);
        }
    }

    private void handleTamedHerbBundleInteraction(Player player, ItemStack itemstack) {
        if (this.getHealth() < this.getMaxHealth()) {
            this.heal(2.0F);
            consumeHerbBundle(player, itemstack);
            return;
        }

        performOwnedInteraction(player);
    }

    private void handleEmptyHandInteraction(Player player) {
        performOwnedInteraction(player);
    }

    private void consumeHerbBundle(Player player, ItemStack itemstack) {
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.HORSE_EAT, this.getSoundSource(), 1.0F, 1.0F);
        if (!player.getAbilities().instabuild) {
            itemstack.shrink(1);
        }
    }

    private void broadcastTameResult(boolean success) {
        this.playTameEffect(success);
        this.level().broadcastEntityEvent(this, success ? SUCCESSFUL_TAME_EVENT : FAILED_TAME_EVENT);
    }

    private void updateBackAttachmentFromInventorySlot() {
        ItemStack attachmentStack = this.bufflonStorage.getItem(BufflonInventoryLayout.BACK_ATTACHMENT_SLOT);
        this.setBackAttachment(BufflonBackAttachmentItems.getType(attachmentStack).getId());
    }

    private void savePersistentState(ValueOutput output) {
        output.putInt(BufflonDataKeys.BUFFLON_TYPE, this.getBufflonType());
        output.putBoolean(BufflonDataKeys.IS_SADDLED, this.isSaddled());
        output.putBoolean(BufflonDataKeys.IS_SITTING, this.isSitting());
        output.putBoolean(BufflonDataKeys.IS_FOLLOWING, this.isFollowingOwner());
        output.putBoolean(BufflonDataKeys.IS_IN_COMBAT_MODE, this.isInCombatMode());
        output.putInt(BufflonDataKeys.BACK_ATTACHMENT_TYPE, this.getBackAttachmentType());

        if (this.getOwnerId() != null) {
            output.store(BufflonDataKeys.OWNER_UUID, UUIDUtil.CODEC, this.getOwnerId());
        }
    }

    private void saveInventory(ValueOutput output) {
        if (!this.isTamed()) {
            return;
        }

        ValueOutput.ValueOutputList items = output.childrenList(BufflonDataKeys.ITEMS);
        for (int slotIndex = 0; slotIndex < this.bufflonStorage.getContainerSize(); ++slotIndex) {
            ItemStack itemStack = this.bufflonStorage.getItem(slotIndex);
            if (!itemStack.isEmpty()) {
                ValueOutput itemOutput = items.addChild();
                itemOutput.putByte(BufflonDataKeys.SLOT, (byte) slotIndex);
                itemOutput.store("Item", ItemStack.CODEC, itemStack);
            }
        }
    }

    private void loadPersistentState(ValueInput input) {
        this.setBufflonType(input.getIntOr(BufflonDataKeys.BUFFLON_TYPE, 0));
        this.setSaddled(input.getBooleanOr(BufflonDataKeys.IS_SADDLED, false));
        setOrderedToSitFromTag(input.getBooleanOr(BufflonDataKeys.IS_SITTING, false));
        this.setFollowingOwner(input.getBooleanOr(BufflonDataKeys.IS_FOLLOWING, false));
        this.setIsInCombatMode(input.getBooleanOr(BufflonDataKeys.IS_IN_COMBAT_MODE, false));
        this.setBackAttachment(input.getIntOr(BufflonDataKeys.BACK_ATTACHMENT_TYPE, 0));
        restoreOwner(input);
    }

    private void loadInventory(ValueInput input) {
        if (!this.isTamed()) {
            return;
        }

        this.initBufflonStorage();
        for (ValueInput itemInput : input.childrenListOrEmpty(BufflonDataKeys.ITEMS)) {
            int slot = itemInput.getByteOr(BufflonDataKeys.SLOT, (byte) 0) & 255;
            if (slot >= 0 && slot < this.bufflonStorage.getContainerSize()) {
                this.bufflonStorage.setItem(slot, itemInput.read("Item", ItemStack.CODEC).orElse(ItemStack.EMPTY));
            }
        }
    }

    private void setOrderedToSitFromTag(boolean shouldSit) {
        if (this.bufflonSitGoal != null) {
            this.bufflonSitGoal.setSitting(shouldSit);
        }
        this.setSitting(shouldSit);
    }

    private void restoreOwner(ValueInput input) {
        input.read(BufflonDataKeys.OWNER_UUID, UUIDUtil.CODEC).ifPresentOrElse(uuid -> {
            try {
                this.setOwnerId(uuid);
                this.setTamed(true);
            } catch (Throwable throwable) {
                this.setTamed(false);
            }
        }, () -> {
        });
    }

    private void performOwnedInteraction(Player player) {
        BufflonInteractionPolicy.OwnedInteractionAction action = BufflonInteractionPolicy.resolveOwnedInteractionAction(
                this.isTamed(),
                player.isSecondaryUseActive()
        );

        if (action == BufflonInteractionPolicy.OwnedInteractionAction.OPEN_MENU) {
            this.openGUI(player);
        } else if (action == BufflonInteractionPolicy.OwnedInteractionAction.MOUNT) {
            mountTo(player);
        }
    }
}
