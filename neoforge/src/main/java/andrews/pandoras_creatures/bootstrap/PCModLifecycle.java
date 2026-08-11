package andrews.pandoras_creatures.bootstrap;

import andrews.pandoras_creatures.bootstrap.lifecycle.PCDataLifecycle;
import andrews.pandoras_creatures.bootstrap.lifecycle.PCEntityLifecycle;
import andrews.pandoras_creatures.bootstrap.lifecycle.PCGameTestLifecycle;
import andrews.pandoras_creatures.bootstrap.lifecycle.PCNetworkLifecycle;
import andrews.pandoras_creatures.bootstrap.lifecycle.PCSetupLifecycle;
import net.neoforged.bus.api.IEventBus;

public final class PCModLifecycle {
    private PCModLifecycle() {
    }

    public static void register(IEventBus modEventBus) {
        PCSetupLifecycle.register(modEventBus);
        PCEntityLifecycle.register(modEventBus);
        PCNetworkLifecycle.register(modEventBus);
        PCDataLifecycle.register(modEventBus);
        PCGameTestLifecycle.register(modEventBus);
    }
}
