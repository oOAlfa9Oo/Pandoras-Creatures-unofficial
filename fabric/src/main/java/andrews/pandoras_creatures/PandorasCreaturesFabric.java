package andrews.pandoras_creatures;

import andrews.pandoras_creatures.network.FabricPayloadRegistrar;
import andrews.pandoras_creatures.network.FabricBufflonPayloadHandlers;
import andrews.pandoras_creatures.platform.fabric.FabricPlatformServices;
import andrews.pandoras_creatures.registry.PCFabricBlockEntities;
import andrews.pandoras_creatures.registry.PCFabricBlocks;
import andrews.pandoras_creatures.registry.PCFabricCreativeTabs;
import andrews.pandoras_creatures.registry.PCFabricEntities;
import andrews.pandoras_creatures.registry.PCFabricItems;
import andrews.pandoras_creatures.registry.PCFabricMenuTypes;
import andrews.pandoras_creatures.registry.PCFabricSounds;
import andrews.pandoras_creatures.registry.PCFabricStructures;
import andrews.pandoras_creatures.util.Reference;
import andrews.pandoras_creatures.world.PCFabricWorldGeneration;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PandorasCreaturesFabric implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger(Reference.MODID);

    @Override
    public void onInitialize() {
        PandorasCreaturesCommon.initialize(FabricPlatformServices.INSTANCE);
        FabricPayloadRegistrar.registerCommonPayloadTypes();
        FabricBufflonPayloadHandlers.registerReceivers();
        PCFabricBlocks.register();
        PCFabricSounds.register();
        PCFabricEntities.register();
        PCFabricItems.register();
        PCFabricBlockEntities.register();
        PCFabricMenuTypes.register();
        PCFabricCreativeTabs.register();
        PCFabricStructures.register();
        PCFabricWorldGeneration.register();
        LOGGER.info("Pandoras Creatures Fabric bootstrap initialized");
    }
}
