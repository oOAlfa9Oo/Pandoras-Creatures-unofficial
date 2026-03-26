package andrews.pandoras_creatures.client.bootstrap;

import andrews.pandoras_creatures.client.screen.BufflonScreen;
import andrews.pandoras_creatures.client.screen.EndTrollBoxScreen;
import andrews.pandoras_creatures.registry.PCMenuTypes;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

public final class PCClientScreenRegistry {
    private PCClientScreenRegistry() {
    }

    public static void registerAll(RegisterMenuScreensEvent event) {
        event.register(PCMenuTypes.END_TROLL_BOX.get(), EndTrollBoxScreen::new);
        event.register(PCMenuTypes.BUFFLON.get(), BufflonScreen::new);
    }
}
