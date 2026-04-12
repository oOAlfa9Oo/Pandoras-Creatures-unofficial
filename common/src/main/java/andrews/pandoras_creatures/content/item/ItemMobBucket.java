package andrews.pandoras_creatures.content.item;

import andrews.pandoras_creatures.entities.bases.BucketableMobEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.material.Fluid;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ItemMobBucket extends BucketItem {
    private final Supplier<EntityType<? extends BucketableMobEntity>> entityType;

    public ItemMobBucket(Supplier<EntityType<? extends BucketableMobEntity>> entityType, Supplier<? extends Fluid> supplier, Properties properties) {
        super(supplier.get(), properties);
        this.entityType = entityType;
    }

    /**
     * Called when the buckets content gets placed
     */
    @Override
    public void checkExtraContent(@Nullable Player player, Level level, ItemStack stack, BlockPos pos) {
        if (!level.isClientSide()) {
            this.placeEntity((ServerLevel) level, stack, pos);
        }
    }

    /**
     * The sound the item plays when using it
     */
    @Override
    protected void playEmptySound(@Nullable Player player, LevelAccessor level, BlockPos pos) {
        level.playSound(player, pos, SoundEvents.BUCKET_EMPTY_FISH, SoundSource.NEUTRAL, 1.0F, 1.0F);
    }

    /**
     * Places the entity in the world, if it isn't null
     */
    private void placeEntity(ServerLevel level, ItemStack stack, BlockPos pos) {
        Entity entity = this.entityType.get().spawn(level, stack, null, pos, EntitySpawnReason.BUCKET, true, false);

        if (entity instanceof BucketableMobEntity bucketable) {
            bucketable.setFromBucket(true);
        }
    }
}
