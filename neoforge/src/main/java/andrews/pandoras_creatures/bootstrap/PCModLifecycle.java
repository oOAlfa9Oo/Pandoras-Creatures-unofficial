package andrews.pandoras_creatures.bootstrap;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.PandorasCreatures;
import andrews.pandoras_creatures.datagen.PCDataGenerators;
import andrews.pandoras_creatures.network.PCNetwork;
import andrews.pandoras_creatures.platform.neoforge.NeoForgePlatformServices;
import andrews.pandoras_creatures.registry.PCItems;
import andrews.pandoras_creatures.registry.entity.PCEntityAttributes;
import andrews.pandoras_creatures.registry.entity.PCEntitySpawnPlacements;
import andrews.pandoras_creatures.registry.util.PCDispenserBehaviors;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public final class PCModLifecycle {
    private PCModLifecycle() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(PCModLifecycle::commonSetup);
        modEventBus.addListener(PCModLifecycle::clientSetup);
        modEventBus.addListener(PCModLifecycle::registerEntityAttributes);
        modEventBus.addListener(PCModLifecycle::registerSpawnPlacements);
        modEventBus.addListener(PCModLifecycle::registerPayloadHandlers);
        modEventBus.addListener(PCDataGenerators::gatherData);
    }

    private static void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            PandorasCreaturesCommon.initialize(NeoForgePlatformServices.INSTANCE);
            PCDispenserBehaviors.registerAll();
            // PCFeatures.registerConfiguredFeatures();
            // PCStructures.registerStructureFeaturesAndSeparation();
        });
        PandorasCreatures.LOGGER.info("Pandoras Creatures common setup complete!");
    }

    private static void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(PCItems::setupItemProperties);
        PandorasCreatures.LOGGER.info("Pandoras Creatures client setup complete!");
    }

    private static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        PCEntityAttributes.registerAll(event);
    }

    private static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        PCEntitySpawnPlacements.registerAll(event);
    }

    private static void registerPayloadHandlers(RegisterPayloadHandlersEvent event) {
        PCNetwork.register(event);
    }
}
