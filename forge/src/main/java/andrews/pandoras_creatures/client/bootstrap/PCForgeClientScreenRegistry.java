package andrews.pandoras_creatures.forge.client.bootstrap;

import andrews.pandoras_creatures.client.screen.BufflonScreen;
import andrews.pandoras_creatures.client.screen.EndTrollBoxScreen;
import andrews.pandoras_creatures.forge.registry.PCForgeMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public final class PCForgeClientScreenRegistry {
    private PCForgeClientScreenRegistry() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(PCForgeClientScreenRegistry::onClientSetup);
    }

    private static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(PCForgeMenuTypes.bufflon(), BufflonScreen::new);
            MenuScreens.register(PCForgeMenuTypes.endTrollBox(), EndTrollBoxScreen::new);
        });
    }
}
