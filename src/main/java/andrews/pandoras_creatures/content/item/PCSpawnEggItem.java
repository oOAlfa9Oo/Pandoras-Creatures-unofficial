package andrews.pandoras_creatures.content.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.CustomData;
import org.jetbrains.annotations.Nullable;

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

    @Override
    protected EntityType<?> getDefaultType() {
        return this.entityTypeSupplier.get();
    }

    @Override
    public EntityType<?> getType(@Nullable ItemStack stack) {
        if (stack != null) {
            CustomData customData = stack.get(DataComponents.ENTITY_DATA);
            if (customData != null && !customData.isEmpty()) {
                CompoundTag entityTag = customData.copyTag();
                if (entityTag.contains("id", 8)) { // 8 = TAG_STRING
                    return EntityType.byString(entityTag.getString("id")).orElse(this.getDefaultType());
                }
            }
        }
        return this.getDefaultType();
    }
}
