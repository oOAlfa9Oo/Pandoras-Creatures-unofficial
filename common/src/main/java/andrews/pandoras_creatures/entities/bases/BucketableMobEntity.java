package andrews.pandoras_creatures.entities.bases;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

/**
 * Base class for water mobs that can be captured in buckets.
 * Uses Minecraft's built-in Bucketable interface for 1.21+
 */
public abstract class BucketableMobEntity extends AnimatedWaterMobEntity implements Bucketable {
    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(BucketableMobEntity.class, EntityDataSerializers.BOOLEAN);

    public BucketableMobEntity(EntityType<? extends BucketableMobEntity> type, Level level) {
        super(type, level);
    }

    /**
     * Registers the data of the DataManager
     */
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(FROM_BUCKET, false);
    }

    /**
     * Sets the name of the bucket to the mobs name, if it had a custom one
     * @param bucket - The ItemBucket used
     */
    protected void setBucketData(ItemStack bucket) {
        if (this.hasCustomName()) {
            bucket.set(net.minecraft.core.component.DataComponents.CUSTOM_NAME, this.getCustomName());
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("FromBucket", this.fromBucket());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setFromBucket(compound.getBoolean("FromBucket"));
    }

    /**
     * @return - Returns whether or not this entity is from a bucket
     */
    @Override
    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    /**
     * Sets if this entity should come from a bucket
     * @param value - True = Yes | False = No
     */
    @Override
    public void setFromBucket(boolean value) {
        this.entityData.set(FROM_BUCKET, value);
    }

    @Override
    public void saveToBucketTag(ItemStack bucket) {
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        this.setBucketData(bucket);
    }

    @Override
    public void loadFromBucketTag(CompoundTag tag) {
        Bucketable.loadDefaultDataFromBucketTag(this, tag);
    }

    @Override
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }

    /**
     * Used to process all things that happen when this entity is interacted with
     * @param player - The player that interacted with this entity
     * @param hand - The players hand that got used to interact
     */
    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.is(Items.WATER_BUCKET) && this.isAlive()) {
            this.playSound(this.getPickupSound(), 1.0F, 1.0F);
            ItemStack bucket = this.getBucketItemStack();
            this.saveToBucketTag(bucket);

            ItemStack result = ItemUtils.createFilledResult(itemstack, player, bucket);
            player.setItemInHand(hand, result);

            if (!this.level().isClientSide()) {
                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, bucket);
            }

            this.discard();
            return this.level().isClientSide() ? InteractionResult.CONSUME : InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(player, hand);
        }
    }

    /**
     * Checks whether or not this entity can despawn
     * @param distanceToClosestPlayer - The distance to the closest player
     * @return - Returns if this entity can despawn
     */
    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return !this.fromBucket() && !this.hasCustomName();
    }

    /**
     * Prevents this entity from despawning
     * @return - True if this entity is prevented from despawning
     */
    @Override
    public boolean requiresCustomPersistence() {
        return this.fromBucket();
    }
}
