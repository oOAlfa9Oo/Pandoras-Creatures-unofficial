package andrews.pandoras_creatures;

import andrews.pandoras_creatures.network.PCNetwork;
import andrews.pandoras_creatures.registry.*;
import andrews.pandoras_creatures.registry.util.PCDispenserBehaviors;
import andrews.pandoras_creatures.util.Reference;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(Reference.MODID)
public class PandorasCreatures {
    public static final Logger LOGGER = LoggerFactory.getLogger(Reference.MODID);
    public static PandorasCreatures instance;

    public PandorasCreatures(IEventBus modEventBus, ModContainer modContainer) {
        instance = this;

        // Register all DeferredRegisters to the mod event bus
        PCItems.ITEMS.register(modEventBus);
        PCBlocks.BLOCKS.register(modEventBus);
        PCSounds.SOUNDS.register(modEventBus);
        PCBlockEntities.BLOCK_ENTITY_TYPES.register(modEventBus);
        PCMenuTypes.MENU_TYPES.register(modEventBus);
        PCEntities.ENTITY_TYPES.register(modEventBus);
        PCCreativeTabs.CREATIVE_TABS.register(modEventBus);
        PCRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
        // PCFeatures.FEATURES.register(modEventBus);
        PCStructures.STRUCTURE_TYPES.register(modEventBus);
        PCStructures.STRUCTURE_PIECE_TYPES.register(modEventBus);

        // Register event listeners
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::registerEntityAttributes);
        modEventBus.addListener(this::registerSpawnPlacements);
        modEventBus.addListener(this::registerPayloadHandlers);
        // Item colors are registered in PCClientSetup via @SubscribeEvent

        // Note: We don't need to register to NeoForge.EVENT_BUS here because all our listeners
        // are registered above using modEventBus.addListener()
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            PCDispenserBehaviors.registerAll();
            // PCFeatures.registerConfiguredFeatures();
            // PCStructures.registerStructureFeaturesAndSeparation();
        });
        LOGGER.info("Pandoras Creatures common setup complete!");
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // Client-side setup that needs to be thread-safe
            PCItems.setupItemProperties();
        });
        LOGGER.info("Pandoras Creatures client setup complete!");
    }

    private void registerEntityAttributes(EntityAttributeCreationEvent event) {
        PCEntities.registerEntityAttributes(event);
    }

    private void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        PCEntities.registerSpawnPlacements(event);
    }

    private void registerPayloadHandlers(RegisterPayloadHandlersEvent event) {
        PCNetwork.register(event);
    }
}
