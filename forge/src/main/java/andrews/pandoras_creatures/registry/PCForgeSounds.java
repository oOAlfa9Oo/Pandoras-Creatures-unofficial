package andrews.pandoras_creatures.forge.registry;

import andrews.pandoras_creatures.registry.sound.PCSoundCatalog;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

public final class PCForgeSounds {
    private static boolean registered;

    private PCForgeSounds() {
    }

    public static void register(BusGroup modEventBus) {
        RegisterEvent.getBus(modEventBus).addListener(PCForgeSounds::registerSounds);
    }

    private static void registerSounds(RegisterEvent event) {
        if (registered || !event.getRegistryKey().equals(ForgeRegistries.Keys.SOUND_EVENTS)) {
            return;
        }

        registered = true;
        event.register(ForgeRegistries.Keys.SOUND_EVENTS, helper -> {
            for (String soundId : PCSoundCatalog.allSoundIds()) {
                Identifier id = Identifier.fromNamespaceAndPath(Reference.MODID, soundId);
                helper.register(id, SoundEvent.createVariableRangeEvent(id));
            }
        });
    }
}
