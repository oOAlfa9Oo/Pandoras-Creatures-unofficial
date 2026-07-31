package andrews.pandoras_creatures.forge.test;

import andrews.pandoras_creatures.test.PCGameTestCatalog;
import net.minecraftforge.event.RegisterGameTestsEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public final class PCForgeGameTests {
    private PCForgeGameTests() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(PCForgeGameTests::registerGameTests);
    }

    private static void registerGameTests(RegisterGameTestsEvent event) {
        event.register(PCGameTestCatalog.class);
    }
}