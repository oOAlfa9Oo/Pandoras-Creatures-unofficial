package andrews.pandoras_creatures.client.bootstrap;

import andrews.pandoras_creatures.client.screen.BufflonScreen;
import andrews.pandoras_creatures.client.screen.EndTrollBoxScreen;
import andrews.pandoras_creatures.registry.PCFabricMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;

public final class PCFabricClientScreenRegistry {
    private PCFabricClientScreenRegistry() {
    }

    public static void registerAll() {
        MenuScreens.register(PCFabricMenuTypes.END_TROLL_BOX, EndTrollBoxScreen::new);
        MenuScreens.register(PCFabricMenuTypes.BUFFLON, BufflonScreen::new);
    }
}
