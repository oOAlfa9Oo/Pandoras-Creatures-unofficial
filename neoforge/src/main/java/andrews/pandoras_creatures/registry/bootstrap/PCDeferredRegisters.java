package andrews.pandoras_creatures.registry.bootstrap;

import net.neoforged.bus.api.IEventBus;

public final class PCDeferredRegisters {
    private PCDeferredRegisters() {
    }

    public static void registerAll(IEventBus modEventBus) {
        PCContentRegisterGroup.register(modEventBus);
        PCGameplayRegisterGroup.register(modEventBus);
        PCEntityRegisterGroup.register(modEventBus);
        PCWorldRegisterGroup.register(modEventBus);
    }
}
