package andrews.pandoras_creatures.bootstrap.lifecycle;

import andrews.pandoras_creatures.registry.entity.PCEntityAttributes;
import andrews.pandoras_creatures.registry.entity.PCEntitySpawnPlacements;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

public final class PCEntityLifecycle {
    private PCEntityLifecycle() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(PCEntityLifecycle::registerEntityAttributes);
        modEventBus.addListener(PCEntityLifecycle::registerSpawnPlacements);
    }

    private static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        PCEntityAttributes.registerAll(event);
    }

    private static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        PCEntitySpawnPlacements.registerAll(event);
    }
}
