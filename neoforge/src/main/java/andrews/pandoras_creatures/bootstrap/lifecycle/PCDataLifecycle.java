package andrews.pandoras_creatures.bootstrap.lifecycle;

import andrews.pandoras_creatures.datagen.PCDataGenerators;
import net.neoforged.bus.api.IEventBus;

public final class PCDataLifecycle {
    private PCDataLifecycle() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(PCDataGenerators::gatherServerData);
        modEventBus.addListener(PCDataGenerators::gatherClientData);
    }
}
