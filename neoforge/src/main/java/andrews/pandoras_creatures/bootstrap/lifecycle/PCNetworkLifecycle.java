package andrews.pandoras_creatures.bootstrap.lifecycle;

import andrews.pandoras_creatures.network.PCNetwork;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public final class PCNetworkLifecycle {
    private PCNetworkLifecycle() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(PCNetworkLifecycle::registerPayloadHandlers);
    }

    private static void registerPayloadHandlers(RegisterPayloadHandlersEvent event) {
        PCNetwork.register(event);
    }
}
