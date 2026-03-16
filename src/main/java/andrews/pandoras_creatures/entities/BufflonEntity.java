package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.entities.bases.AnimatedCreatureEntity;
import andrews.pandoras_creatures.entities.goals.bufflon.*;
import andrews.pandoras_creatures.registry.PCEntities;
import andrews.pandoras_creatures.registry.PCItems;
import andrews.pandoras_creatures.registry.PCSounds;
import andrews.pandoras_creatures.util.NetworkUtil;
import andrews.pandoras_creatures.util.animation.Animation;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerListener;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.Saddleable;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.PlayerTeam;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

public class BufflonEntity extends AnimatedCreatureEntity implements ContainerListener, Saddleable {
    // Stores the Bufflon type
    private static final EntityDataAccessor<Integer> BUFFLON_TYPE = SynchedEntityData.defineId(BufflonEntity.class, EntityDataSerializers.INT);
    // Stores whether or not the Bufflon is tamed and the UUID of the owner
    private static final EntityDataAccessor<Byte> TAMED = SynchedEntityData.defineId(BufflonEntity.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Optional<UUID>> OWNER_UNIQUE_ID = SynchedEntityData.defineId(BufflonEntity.class, EntityDataSerializers.OPTIONAL_UUID);
    // Store the information of the attachments
    private static final EntityDataAccessor<Boolean> IS_SADDLED = SynchedEntityData.defineId(BufflonEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> BACK_ATTACHMENT_TYPE = SynchedEntityData.defineId(BufflonEntity.class, EntityDataSerializers.INT);
    // Stores whether or not the Bufflon is sitting
    private static final EntityDataAccessor<Boolean> IS_SITTING = SynchedEntityData.defineId(BufflonEntity.class, EntityDataSerializers.BOOLEAN);
    // Stores whether or not the Bufflon is following
    private static final EntityDataAccessor<Boolean> IS_FOLLOWING = SynchedEntityData.defineId(BufflonEntity.class, EntityDataSerializers.BOOLEAN);
    // Stores whether or not the Bufflon is in combat mode
    private static final EntityDataAccessor<Boolean> COMBAT_MODE = SynchedEntityData.defineId(BufflonEntity.class, EntityDataSerializers.BOOLEAN);

    // The Items that can be used in given slots
    public static final Item SADDLE_ITEM = PCItems.BUFFLON_SADDLE.get();
    public static final Item[] VALID_BACK_ATTACHMENTS = {PCItems.BUFFLON_PLAYER_SEATS.get(), PCItems.BUFFLON_SMALL_STORAGE.get(), PCItems.BUFFLON_LARGE_STORAGE.get()};

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
        this(PCEntities.BUFFLON.get(), level);
        this.moveTo(posX, posY, posZ);
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
        builder.define(OWNER_UNIQUE_ID, Optional.empty());
        builder.define(IS_SADDLED, false);
        builder.define(BACK_ATTACHMENT_TYPE, 0);
        builder.define(IS_SITTING, false);
        builder.define(IS_FOLLOWING, false);
        builder.define(COMBAT_MODE, false);
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(PCItems.BUFFLON_SPAWN_EGG.get());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        // The Bufflon Variant
        compound.putInt("BufflonType", this.getBufflonType());
        // If this entity is saddled
        compound.putBoolean("IsSaddled", this.isSaddled());
        // If this entity is sitting
        compound.putBoolean("IsSitting", this.isSitting());
        // If this entity is following
        compound.putBoolean("IsFollowing", this.isFollowingOwner());
        // If this entity is in combat mode
        compound.putBoolean("IsInCombatMode", this.isInCombatMode());
        // The back attachment type this Entity has
        compound.putInt("BackAttachmentType", this.getBackAttachmentType());
        // The Owner of the Bufflon
        if (this.getOwnerId() != null) {
            compound.putUUID("OwnerUUID", this.getOwnerId());
        }

        // Storing the Items inside the Bufflons Inventory
        if (this.isTamed()) {
            ListTag listtag = new ListTag();
            for (int i = 0; i < this.bufflonStorage.getContainerSize(); ++i) {
                ItemStack itemstack = this.bufflonStorage.getItem(i);
                if (!itemstack.isEmpty()) {
                    CompoundTag compoundtag = new CompoundTag();
                    compoundtag.putByte("Slot", (byte) i);
                    listtag.add(itemstack.save(this.registryAccess(), compoundtag));
                }
            }
            compound.put("Items", listtag);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        // The Bufflon Variant
        this.setBufflonType(compound.getInt("BufflonType"));
        // If this entity is saddled
        this.setSaddled(compound.getBoolean("IsSaddled"));
        // If this entity is sitting
        if (this.bufflonSitGoal != null) {
            this.bufflonSitGoal.setSitting(compound.getBoolean("IsSitting"));
        }
        this.setSitting(compound.getBoolean("IsSitting"));
        // If this entity is following
        this.setFollowingOwner(compound.getBoolean("IsFollowing"));
        // If this entity is in combat mode
        this.setIsInCombatMode(compound.getBoolean("IsInCombatMode"));
        // The back attachment type this Entity has
        this.setBackAttachment(compound.getInt("BackAttachmentType"));
        // The Owner of the Bufflon
        UUID uuid = null;
        if (compound.hasUUID("OwnerUUID")) {
            uuid = compound.getUUID("OwnerUUID");
        }
        // Attempts to set the Bufflon as tamed
        if (uuid != null) {
            try {
                this.setOwnerId(uuid);
                this.setTamed(true);
            } catch (Throwable throwable) {
                this.setTamed(false);
            }
        }

        // Loading the stored Items inside the Bufflons Inventory
        if (this.isTamed()) {
            ListTag listtag = compound.getList("Items", Tag.TAG_COMPOUND);
            this.initBufflonStorage();
            for (int i = 0; i < listtag.size(); ++i) {
                CompoundTag compoundtag = listtag.getCompound(i);
                int j = compoundtag.getByte("Slot") & 255;
                if (j >= 0 && j < this.bufflonStorage.getContainerSize()) {
                    this.bufflonStorage.setItem(j, ItemStack.parse(this.registryAccess(), compoundtag).orElse(ItemStack.EMPTY));
                }
            }
        }

        this.updateBufflonSlots();
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData) {
        spawnData = super.finalizeSpawn(level, difficulty, reason, spawnData);
        RandomSource rand = level.getRandom();
        int type = rand.nextInt(7) + 1;
        this.setBufflonType(type);
        return spawnData;
    }

    @Override
    public boolean canBeLeashed() {
        return false;
    }

    @Override
    public int getBaseExperienceReward() {
        return 1 + this.level().random.nextInt(3);
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.is(PCItems.HERB_BUNDLE.get())) {
            // Is Not Tamed
            if (!this.level().isClientSide() && !this.isTamed()) {
                if (feedingCooldown <= 0) {
                    feedingCooldown = 10;
                    this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.HORSE_EAT, this.getSoundSource(), 1.0F, 1.0F);
                    if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }

                    if (this.random.nextInt(3) == 0) {
                        mountTo(player);
                    } else {
                        this.playTameEffect(false);
                        this.level().broadcastEntityEvent(this, (byte) 6);
                    }
                }
            }
            // Is Tamed
            else if (!this.level().isClientSide() && this.isTamed()) {
                if (this.getHealth() < this.getMaxHealth()) {
                    this.heal(2.0F);
                    this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.HORSE_EAT, this.getSoundSource(), 1.0F, 1.0F);
                    if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                } else {
                    if (player.isSecondaryUseActive()) {
                        this.openGUI(player);
                    } else {
                        mountTo(player);
                    }
                }
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide());
        } else if (this.isTamed() && !this.isSaddled() && itemstack.is(SADDLE_ITEM)) {
            this.openGUI(player);
            return InteractionResult.sidedSuccess(this.level().isClientSide());
        } else if (this.isTamed() && !this.hasBackAttachment() && Arrays.asList(VALID_BACK_ATTACHMENTS).contains(itemstack.getItem())) {
            this.openGUI(player);
            return InteractionResult.sidedSuccess(this.level().isClientSide());
        } else if (itemstack.isEmpty()) {
            if (this.isTamed() && player.isSecondaryUseActive()) {
                this.openGUI(player);
            } else if (this.isTamed() && !player.isSecondaryUseActive()) {
                mountTo(player);
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide());
        } else {
            return super.mobInteract(player, hand);
        }
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        boolean flag = target.hurt(this.damageSources().mobAttack(this), (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE));
        // The attack sound
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(), PCSounds.BUFFLON_ATTACK.get(), this.getSoundSource(), 0.6F, 0.8F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
        // The attack animation
        if (this.isAnimationPlaying(BLANK_ANIMATION) && !this.level().isClientSide()) {
            NetworkUtil.sendAnimationPacket(this, ATTACK_HEAD_ANIMATION);
        }

        if (flag) {
            target.setDeltaMovement(target.getDeltaMovement().add(this.position().subtract(target.position()).multiply(-0.15D, 0.0D, -0.15D)));
            target.hurtMarked = true;
            // Enchantment damage effects are now applied automatically
        }
        return flag;
    }

    /**
     * Used in Goals to avoid fighting of tamed entities
     */
    public boolean shouldAttackEntity(LivingEntity target, LivingEntity owner) {
        if (!(target instanceof Creeper) && !(target instanceof Ghast)) {
            // Protects Tamed Dog Entities
            if (target instanceof Wolf wolf) {
                if (wolf.isTame() && wolf.getOwner() == owner) {
                    return false;
                }
            }

            // Protects Tamed Bufflon Entities
            if (target instanceof BufflonEntity bufflon) {
                if (bufflon.isTamed() && bufflon.getOwner() == owner) {
                    return false;
                }
            }

            // Protects none attackable Players
            if (target instanceof Player && owner instanceof Player && !((Player) owner).canHarmPlayer((Player) target)) {
                return false;
            }
            // Protects tamed Horses
            else if (target instanceof AbstractHorse horse && horse.isTamed()) {
                return false;
            }
            // Protects tamed Cats
            else {
                return !(target instanceof Cat cat) || !cat.isTame();
            }
        } else {
            return false;
        }
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            Entity entity = source.getEntity();
            if (this.bufflonSitGoal != null && this.isInCombatMode()) {
                this.bufflonSitGoal.setSitting(false);
            }

            if (entity != null && !(entity instanceof Player) && !(entity instanceof AbstractArrow)) {
                amount = (amount + 1.0F) / 2.0F;
            }

            return super.hurt(source, amount);
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();

        // Makes it so the Bufflon gains health slowly "regeneration"
        if (!this.level().isClientSide() && this.isAlive()) {
            if (this.random.nextInt(900) == 0 && this.deathTime == 0) {
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
                if (this.random.nextInt(4) == 0) {
                    this.setTamedBy((Player) this.getPassengers().get(0));
                    this.navigation.stop();
                    this.setTarget(null);
                    this.playTameEffect(true);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                } else {
                    this.level().playSound(null, this.getX(), this.getY(), this.getZ(), PCSounds.BUFFLON_ATTACK.get(), this.getSoundSource(), 0.6F, 0.8F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
                    // Removes the Passengers
                    for (int i = this.getPassengers().size() - 1; i >= 0; --i) {
                        Entity entity = this.getPassengers().get(i);
                        entity.stopRiding();
                        entity.setDeltaMovement(entity.getDeltaMovement().add((random.nextInt(3) - 1) * 0.3D, 1.2D, (random.nextInt(3) - 1) * 0.3D));
                        entity.hurtMarked = true;
                        NetworkUtil.sendAnimationPacket(this, THROW_ANIMATION);
                    }
                    this.playTameEffect(false);
                    this.level().broadcastEntityEvent(this, (byte) 6);
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
        return PCSounds.BUFFLON_AMBIENT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return PCSounds.BUFFLON_DEATH.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return PCSounds.BUFFLON_HURT.get();
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
            float offsetX = 0F;
            float offsetY = 0F;
            if (!this.getPassengers().isEmpty()) {
                int i = this.getPassengers().indexOf(passenger);
                if (i == 0) { // The first passenger
                    if (!this.isSaddled()) {
                        offsetY = -0.08F;
                    }
                    offsetX = 0.95F;
                    offsetY += 2.3F - getPassengerMovement();
                } else if (i == 1) { // The second passenger
                    offsetX = -0.9F;
                    if (this.isMoving()) {
                        offsetY += 2.1F - (getPassengerMovement() * 1.4F);
                    } else {
                        offsetY += 2.1F - getPassengerMovement();
                    }
                    // Locks the player head rotation
                    passenger.setYHeadRot(passenger.getYHeadRot());
                    this.applyYawToEntity(passenger);
                } else { // The third passenger
                    offsetX = -1.59F;
                    if (this.isMoving()) {
                        offsetY += 2.0F - (getPassengerMovement() * 1.4F);
                    } else {
                        offsetY += 2.0F - getPassengerMovement();
                    }
                    passenger.setYHeadRot(passenger.getYHeadRot());
                    this.applyYawToEntity(passenger);
                }
            }

            Vec3 vec3 = (new Vec3(offsetX, 0.0D, 0.0D)).yRot(-this.getYRot() * ((float) Math.PI / 180F) - ((float) Math.PI / 2F));
            callback.accept(passenger, this.getX() + vec3.x, this.getY() + offsetY, this.getZ() + vec3.z);
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
        this.bufflonStorage = new SimpleContainer(this.getInventorySize());
        if (inventory != null) {
            inventory.removeListener(this);
            int i = Math.min(inventory.getContainerSize(), this.bufflonStorage.getContainerSize());

            for (int j = 0; j < i; ++j) {
                ItemStack itemstack = inventory.getItem(j);
                if (!itemstack.isEmpty()) {
                    this.bufflonStorage.setItem(j, itemstack.copy());
                }
            }
        }
        this.bufflonStorage.addListener(this);
        this.updateBufflonSlots();
    }

    /**
     * @return - The size of the inventory this Entity has
     */
    public int getInventorySize() {
        return 56;
    }

    /**
     * Updates the items in the slots of the Bufflon's inventory.
     */
    protected void updateBufflonSlots() {
        if (!this.level().isClientSide()) {
            // Sets the Bufflon to being Saddled
            this.setSaddled(!this.bufflonStorage.getItem(0).isEmpty());

            // Sets the Bufflon back attachments
            if (!this.bufflonStorage.getItem(1).isEmpty()) {
                Item itemInSlot = this.bufflonStorage.getItem(1).getItem();
                if (Arrays.asList(VALID_BACK_ATTACHMENTS).contains(itemInSlot)) {
                    this.setBackAttachment(this.getItemBackAttachmentType(itemInSlot));
                }
            } else {
                if (this.getBackAttachmentType() != 0) {
                    this.setBackAttachment(0);
                }
            }
        }
    }

    /**
     * Called by Container listener.
     */
    @Override
    public void containerChanged(Container container) {
        boolean flagIsSaddled = this.isSaddled();
        boolean flagHasBackAttachment = this.hasBackAttachment();

        this.updateBufflonSlots();

        // The Bufflon Saddle Sound
        if (this.tickCount > 20 && !flagIsSaddled && this.isSaddled()) {
            this.playSound(SoundEvents.HORSE_SADDLE, 0.5F, 1.0F);
        }
        // The Bufflon Back Attachments Sound
        if (this.tickCount > 20 && !flagHasBackAttachment && this.hasBackAttachment()) {
            this.playSound(SoundEvents.HORSE_SADDLE, 0.5F, 1.0F);
        }
    }

    @Override
    protected void dropEquipment() {
        super.dropEquipment();
        if (this.bufflonStorage != null) {
            for (int i = 0; i < this.bufflonStorage.getContainerSize(); ++i) {
                ItemStack itemstack = this.bufflonStorage.getItem(i);
                if (!itemstack.isEmpty()) {
                    this.spawnAtLocation(itemstack);
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

                    if (this.isControlledByLocalInstance() && this.isSaddled()) {
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
            if (this.hasBackAttachment() && this.getBackAttachmentType() == 1) {
                return this.getPassengers().size() < 3;
            } else if (this.hasBackAttachment() && this.getBackAttachmentType() == 2) {
                return this.getPassengers().size() < 2;
            } else {
                return this.getPassengers().size() < 1;
            }
        } else {
            return false;
        }
    }

    //======================================================================================================================================================

    public float getPassengerMovement() {
        float height;
        float bounce;
        if (!isMoving()) {
            height = 0.03F;
            float speed = 0.24F;
            bounce = (float) (Math.sin(this.tickCount * speed) * height - height);
        } else {
            height = 0.05F;
            float speed = 0.45F;
            bounce = (float) (Math.sin(this.walkAnimation.position() * speed - 0.04F) * this.walkAnimation.speed() * height - this.walkAnimation.speed() * height);
        }

        return bounce + 0.08F;
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
    @OnlyIn(Dist.CLIENT)
    @Override
    public void handleEntityEvent(byte id) {
        if (id == 7) {
            this.playTameEffect(true);
        } else if (id == 6) {
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
            this.thinkTime = 40;
        }
    }

    public boolean isInCombatMode() {
        return this.entityData.get(COMBAT_MODE);
    }

    public void setIsInCombatMode(boolean value) {
        this.entityData.set(COMBAT_MODE, value);
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

    @Override
    public boolean isSaddled() {
        return this.entityData.get(IS_SADDLED);
    }

    public void setSaddled(boolean value) {
        this.entityData.set(IS_SADDLED, value);
    }

    @Override
    public boolean isSaddleable() {
        return this.isAlive() && !this.isBaby() && this.isTamed();
    }

    @Override
    public void equipSaddle(ItemStack saddle, @Nullable net.minecraft.sounds.SoundSource source) {
        this.bufflonStorage.setItem(0, saddle.copyWithCount(1));
        if (source != null) {
            this.level().playSound(null, this, SoundEvents.HORSE_SADDLE, source, 0.5F, 1.0F);
        }
    }

    public boolean hasBackAttachment() {
        return (this.entityData.get(BACK_ATTACHMENT_TYPE) != 0);
    }

    public int getItemBackAttachmentType(Item item) {
        if (!Arrays.asList(VALID_BACK_ATTACHMENTS).contains(item)) {
            return 0;
        } else {
            if (item == PCItems.BUFFLON_PLAYER_SEATS.get()) {
                return 1;
            } else if (item == PCItems.BUFFLON_SMALL_STORAGE.get()) {
                return 2;
            } else if (item == PCItems.BUFFLON_LARGE_STORAGE.get()) {
                return 3;
            } else {
                return 0;
            }
        }
    }

    public int getBackAttachmentType() {
        return this.entityData.get(BACK_ATTACHMENT_TYPE);
    }

    public void setBackAttachment(int backAttachmentType) {
        this.entityData.set(BACK_ATTACHMENT_TYPE, backAttachmentType);
    }

    public void openGUI(Player player) {
        if (!this.level().isClientSide() && (!this.isVehicle() || this.hasPassenger(player)) && this.isTamed()) {
            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.openMenu(new net.minecraft.world.SimpleMenuProvider(
                        (windowId, playerInv, p) -> new andrews.pandoras_creatures.menu.BufflonMenu(windowId, playerInv, this.getId()),
                        this.getDisplayName()
                ), buf -> buf.writeInt(this.getId()));
            }
        }
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
        return this.entityData.get(OWNER_UNIQUE_ID).orElse(null);
    }

    public void setOwnerId(@Nullable UUID uuid) {
        this.entityData.set(OWNER_UNIQUE_ID, Optional.ofNullable(uuid));
    }

    public void setTamedBy(Player player) {
        this.setTamed(true);
        this.setOwnerId(player.getUUID());
    }

    @Nullable
    public LivingEntity getOwner() {
        try {
            UUID uuid = this.getOwnerId();
            return uuid == null ? null : this.level().getPlayerByUUID(uuid);
        } catch (IllegalArgumentException var2) {
            return null;
        }
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        return !this.isOwner(target) && super.canAttack(target);
    }

    public boolean isOwner(LivingEntity entity) {
        return entity == this.getOwner();
    }

    public boolean isOwnedBy(Player player) {
        UUID ownerId = this.getOwnerId();
        return player != null && ownerId != null && ownerId.equals(player.getUUID());
    }

    @Override
    public PlayerTeam getTeam() {
        if (this.isTamed()) {
            LivingEntity livingentity = this.getOwner();
            if (livingentity != null) {
                return livingentity.getTeam();
            }
        }
        return super.getTeam();
    }

    @Override
    public boolean isAlliedTo(Entity entity) {
        if (this.isTamed()) {
            LivingEntity livingentity = this.getOwner();
            if (entity == livingentity) {
                return true;
            }

            if (livingentity != null) {
                return livingentity.isAlliedTo(entity);
            }
        }
        return super.isAlliedTo(entity);
    }

    @Override
    public void die(DamageSource cause) {
        if (!this.level().isClientSide() && this.level().getGameRules().getBoolean(GameRules.RULE_SHOWDEATHMESSAGES) && this.getOwner() instanceof ServerPlayer) {
            this.getOwner().sendSystemMessage(this.getCombatTracker().getDeathMessage());
        }
        super.die(cause);
    }

    public int getBufflonType() {
        if (this.entityData.get(BUFFLON_TYPE) == 0) {
            RandomSource rand = this.random;
            this.entityData.set(BUFFLON_TYPE, rand.nextInt(7) + 1);
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
}
