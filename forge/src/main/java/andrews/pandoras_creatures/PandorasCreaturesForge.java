package andrews.pandoras_creatures.forge;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.forge.network.PCForgeNetwork;
import andrews.pandoras_creatures.forge.test.PCForgeGameTests;
import andrews.pandoras_creatures.forge.platform.ForgePlatformServices;
import andrews.pandoras_creatures.forge.registry.PCForgeEntities;
import andrews.pandoras_creatures.forge.registry.PCForgeBlocks;
import andrews.pandoras_creatures.forge.registry.PCForgeBlockEntities;
import andrews.pandoras_creatures.forge.registry.PCForgeCreativeTabs;
import andrews.pandoras_creatures.forge.registry.PCForgeItems;
import andrews.pandoras_creatures.forge.registry.PCForgeMenuTypes;
import andrews.pandoras_creatures.forge.registry.PCForgeRecipeSerializers;
import andrews.pandoras_creatures.forge.registry.PCForgeSounds;
import andrews.pandoras_creatures.forge.registry.PCForgeStructures;
import andrews.pandoras_creatures.forge.registry.entity.PCForgeEntitySpawnPlacements;
import andrews.pandoras_creatures.util.Reference;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(Reference.MODID)
public final class PandorasCreaturesForge {
    public static final Logger LOGGER = LoggerFactory.getLogger(Reference.MODID);

    public PandorasCreaturesForge(FMLJavaModLoadingContext context) {
        BusGroup modEventBus = context.getModBusGroup();

        PandorasCreaturesCommon.initialize(ForgePlatformServices.INSTANCE);
        PCForgeGameTests.register(modEventBus);
        PCForgeNetwork.initialize();
        PCForgeSounds.register(modEventBus);
        PCForgeEntities.register(modEventBus);
        PCForgeEntitySpawnPlacements.register(modEventBus);
        PCForgeBlocks.register(modEventBus);
        PCForgeBlockEntities.register(modEventBus);
        PCForgeItems.register(modEventBus);
        PCForgeMenuTypes.register(modEventBus);
        PCForgeRecipeSerializers.register(modEventBus);
        PCForgeCreativeTabs.register(modEventBus);
        PCForgeStructures.register(modEventBus);
        if (FMLEnvironment.dist == Dist.CLIENT) {
            registerClient(modEventBus);
        }
        LOGGER.info("Pandoras Creatures Forge bootstrap initialized");
    }

    private static void registerClient(BusGroup modEventBus) {
        andrews.pandoras_creatures.forge.client.bootstrap.PCForgeClientBlockRegistry.register(modEventBus);
        andrews.pandoras_creatures.forge.client.bootstrap.PCForgeClientEntityRegistry.register(modEventBus);
        andrews.pandoras_creatures.forge.client.bootstrap.PCForgeClientItemColorRegistry.register(modEventBus);
        andrews.pandoras_creatures.forge.client.bootstrap.PCForgeClientScreenRegistry.register(modEventBus);
        andrews.pandoras_creatures.forge.client.bootstrap.PCForgeSpecialModelRegistry.registerAll();
    }
}
