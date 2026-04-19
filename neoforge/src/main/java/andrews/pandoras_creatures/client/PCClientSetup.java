package andrews.pandoras_creatures.client;

import andrews.pandoras_creatures.client.bootstrap.PCClientItemExtensionsRegistry;
import andrews.pandoras_creatures.client.bootstrap.PCClientModelLayerRegistry;
import andrews.pandoras_creatures.client.bootstrap.PCClientRendererRegistry;
import andrews.pandoras_creatures.client.bootstrap.PCClientScreenRegistry;
import andrews.pandoras_creatures.client.bootstrap.PCClientSpecialModelRegistry;
import andrews.pandoras_creatures.util.Reference;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

/**
 * Client-side setup for Pandoras Creatures mod.
 * Handles registration of entity renderers, model layers, and screens.
 */
@EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT)
public class PCClientSetup {

    /**
     * Register entity renderers
     */
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        PCClientRendererRegistry.registerAll(event);
    }

    /**
     * Register model layer definitions
     */
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        PCClientModelLayerRegistry.registerAll(event);
    }

    /**
     * Register menu screens
     */
    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        PCClientScreenRegistry.registerAll(event);
    }

    /**
     * Register custom BEWLR for EndTrollBox items.
     * Replaces the deprecated Item.initializeClient() approach.
     */
    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        PCClientItemExtensionsRegistry.registerAll(event);
    }

    @SubscribeEvent
    public static void registerSpecialModels(RegisterSpecialModelRendererEvent event) {
        PCClientSpecialModelRegistry.registerAll(event);
    }
}
