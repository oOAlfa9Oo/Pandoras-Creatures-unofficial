package andrews.pandoras_creatures.registry.sound;

import andrews.pandoras_creatures.registry.bootstrap.SharedRegistryRegistrar;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Shared sound registration bootstrap consumed by each loader adapter.
 */
public final class PCSoundBootstrap {
    private PCSoundBootstrap() {
    }

    public static <H extends Supplier<? extends SoundEvent>> Map<String, H> registerAll(SharedRegistryRegistrar<SoundEvent, H> registrar) {
        LinkedHashMap<String, H> registeredSounds = new LinkedHashMap<>();

        for (String soundId : PCSoundCatalog.allSoundIds()) {
            ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Reference.MODID, soundId);
            H handle = registrar.register(soundId, () -> SoundEvent.createVariableRangeEvent(id));
            registeredSounds.put(soundId, handle);
        }

        return Collections.unmodifiableMap(registeredSounds);
    }
}
