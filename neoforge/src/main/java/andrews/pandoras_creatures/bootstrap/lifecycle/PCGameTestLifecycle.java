package andrews.pandoras_creatures.bootstrap.lifecycle;

import andrews.pandoras_creatures.test.PCNeoForgeGameTests;
import andrews.pandoras_creatures.util.Reference;
import net.neoforged.bus.api.IEventBus;

import java.util.Arrays;

public final class PCGameTestLifecycle {
    private static final String GAME_TEST_NAMESPACES = "neoforge.enabledGameTestNamespaces";

    private PCGameTestLifecycle() {
    }

    public static void register(IEventBus modEventBus) {
        if (isPandorasGameTestNamespaceEnabled()) {
            modEventBus.addListener(PCNeoForgeGameTests::register);
        }
    }

    private static boolean isPandorasGameTestNamespaceEnabled() {
        String namespaces = System.getProperty(GAME_TEST_NAMESPACES, "");
        return Arrays.stream(namespaces.split(","))
                .map(String::trim)
                .anyMatch(Reference.MODID::equals);
    }
}
