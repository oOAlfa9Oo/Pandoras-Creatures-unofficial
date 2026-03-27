package andrews.pandoras_creatures.registry.bootstrap;

import andrews.pandoras_creatures.registry.PCEntities;
import net.neoforged.bus.api.IEventBus;

public final class PCEntityRegisterGroup {
    private PCEntityRegisterGroup() {
    }

    public static void register(IEventBus modEventBus) {
        PCEntities.ENTITY_TYPES.register(modEventBus);
    }
}
