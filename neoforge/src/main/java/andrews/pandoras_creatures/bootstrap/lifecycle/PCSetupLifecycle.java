package andrews.pandoras_creatures.bootstrap.lifecycle;

import andrews.pandoras_creatures.PandorasCreatures;
import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.platform.neoforge.NeoForgePlatformServices;
import andrews.pandoras_creatures.registry.PCItems;
import andrews.pandoras_creatures.registry.util.PCDispenserBehaviors;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

public final class PCSetupLifecycle {
    private PCSetupLifecycle() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(PCSetupLifecycle::commonSetup);
        modEventBus.addListener(PCSetupLifecycle::clientSetup);
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
}
