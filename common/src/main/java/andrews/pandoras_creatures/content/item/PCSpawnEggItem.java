package andrews.pandoras_creatures.content.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.CustomData;

import java.util.function.Supplier;

/**
 * Custom spawn egg implementation that handles deferred entity type resolution.
 * This is necessary to avoid calling .get() on DeferredHolders during registration.
 */
public class PCSpawnEggItem extends SpawnEggItem {
    private final Supplier<? extends EntityType<?>> entityTypeSupplier;

    public PCSpawnEggItem(Supplier<? extends EntityType<?>> entityType, int primaryColor, int secondaryColor, Item.Properties properties) {
        super(null, primaryColor, secondaryColor, properties);
        this.entityTypeSupplier = entityType;
    }

    protected EntityType<?> getDefaultType() {
        return this.entityTypeSupplier.get();
    }

    @Override
    public EntityType<?> getType(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.ENTITY_DATA);
        if (customData != null) {
            CompoundTag entityTag = customData.copyTag();
            if (entityTag.contains("id", 8)) {
                return EntityType.byString(entityTag.getString("id")).orElse(this.getDefaultType());
            }
        }
        return this.getDefaultType();
    }

    @Override
    public FeatureFlagSet requiredFeatures() {
        return this.getDefaultType().requiredFeatures();
    }
}
