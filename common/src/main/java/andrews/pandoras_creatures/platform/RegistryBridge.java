package andrews.pandoras_creatures.platform;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

/**
 * Initial registry-facing bridge for shared code.
 */
public interface RegistryBridge {
    String namespace();

    default ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(namespace(), path);
    }

    Item item(String path);

    <T extends Entity> EntityType<T> entityType(String path);

    SoundEvent sound(String path);
}
