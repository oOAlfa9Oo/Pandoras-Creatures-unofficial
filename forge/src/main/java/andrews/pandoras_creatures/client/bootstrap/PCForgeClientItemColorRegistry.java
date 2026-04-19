package andrews.pandoras_creatures.forge.client.bootstrap;

import net.minecraftforge.eventbus.api.bus.BusGroup;

public final class PCForgeClientItemColorRegistry {
    private PCForgeClientItemColorRegistry() {
    }

    public static void register(BusGroup modEventBus) {
        // Spawn eggs use generated 26.1 item assets instead of the removed item color handler event.
    }
}
