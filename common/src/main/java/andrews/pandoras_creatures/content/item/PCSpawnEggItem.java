package andrews.pandoras_creatures.content.item;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
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

    public PCSpawnEggItem(Supplier<? extends EntityType<?>> entityType, Item.Properties properties) {
        super(null, properties);
        this.entityTypeSupplier = entityType;
    }

    protected EntityType<?> getDefaultType() {
        return this.entityTypeSupplier.get();
    }

    @Override
    public EntityType<?> getType(HolderLookup.Provider provider, ItemStack stack) {
        CustomData customData = stack.getOrDefault(DataComponents.ENTITY_DATA, CustomData.EMPTY);
        if (!customData.isEmpty()) {
            EntityType<?> entityType = customData.parseEntityType(provider, Registries.ENTITY_TYPE);
            if (entityType != null) {
                return entityType;
            }
        }
        return this.getDefaultType();
    }

    @Override
    public FeatureFlagSet requiredFeatures() {
        return this.getDefaultType().requiredFeatures();
    }
}
