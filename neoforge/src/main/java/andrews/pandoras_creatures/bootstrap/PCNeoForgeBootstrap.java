package andrews.pandoras_creatures.bootstrap;

import andrews.pandoras_creatures.registry.bootstrap.PCDeferredRegisters;
import net.neoforged.bus.api.IEventBus;

/**
 * Top-level bootstrap entrypoint for the NeoForge host.
 */
public final class PCNeoForgeBootstrap {
    private PCNeoForgeBootstrap() {
    }

    public static void register(IEventBus modEventBus) {
        PCDeferredRegisters.registerAll(modEventBus);
        PCModLifecycle.register(modEventBus);
    }
}
