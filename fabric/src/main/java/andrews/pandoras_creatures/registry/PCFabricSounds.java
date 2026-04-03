package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.registry.sound.PCSoundBootstrap;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.Map;
import java.util.function.Supplier;

public final class PCFabricSounds {
    private static Map<String, Supplier<? extends SoundEvent>> registeredSounds = Map.of();

    private PCFabricSounds() {
    }

    public static void register() {
        if (!registeredSounds.isEmpty()) {
            return;
        }

        registeredSounds = PCSoundBootstrap.registerAll((id, factory) -> {
            SoundEvent soundEvent = factory.get();
            Registry.register(BuiltInRegistries.SOUND_EVENT, ResourceLocation.fromNamespaceAndPath(Reference.MODID, id), soundEvent);
            return () -> soundEvent;
        });
    }
}
