package andrews.pandoras_creatures.registry.bootstrap;

import andrews.pandoras_creatures.registry.PCStructures;
import net.neoforged.bus.api.IEventBus;

public final class PCWorldRegisterGroup {
    private PCWorldRegisterGroup() {
    }

    public static void register(IEventBus modEventBus) {
        PCStructures.STRUCTURE_TYPES.register(modEventBus);
        PCStructures.STRUCTURE_PIECE_TYPES.register(modEventBus);
    }
}
