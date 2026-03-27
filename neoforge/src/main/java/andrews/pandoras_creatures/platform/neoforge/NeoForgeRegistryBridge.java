package andrews.pandoras_creatures.platform.neoforge;

import andrews.pandoras_creatures.platform.RegistryBridge;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

final class NeoForgeRegistryBridge implements RegistryBridge {
    static final NeoForgeRegistryBridge INSTANCE = new NeoForgeRegistryBridge();

    private NeoForgeRegistryBridge() {
    }

    @Override
    public String namespace() {
        return Reference.MODID;
    }

    @Override
    public Item item(String path) {
        return BuiltInRegistries.ITEM.get(id(path));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Entity> EntityType<T> entityType(String path) {
        return (EntityType<T>) BuiltInRegistries.ENTITY_TYPE.get(id(path));
    }

    @Override
    public SoundEvent sound(String path) {
        return BuiltInRegistries.SOUND_EVENT.get(id(path));
    }
}
