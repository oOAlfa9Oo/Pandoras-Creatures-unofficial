package andrews.pandoras_creatures.content.item;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

import java.util.function.Supplier;

/**
 * Custom spawn egg implementation that handles deferred entity type resolution.
 * This is necessary to avoid calling .get() on DeferredHolders during registration.
 */
public class PCSpawnEggItem extends SpawnEggItem {
    private final Supplier<? extends EntityType<?>> entityTypeSupplier;

    public PCSpawnEggItem(Supplier<? extends EntityType<?>> entityType, int primaryColor, int secondaryColor, Item.Properties properties) {
        super(properties.delayedComponent(net.minecraft.core.component.DataComponents.ENTITY_DATA, components -> net.minecraft.world.item.component.TypedEntityData.of(entityType.get(), new net.minecraft.nbt.CompoundTag())));
        this.entityTypeSupplier = entityType;
    }

    protected EntityType<?> getDefaultType() {
        return this.entityTypeSupplier.get();
    }

}
