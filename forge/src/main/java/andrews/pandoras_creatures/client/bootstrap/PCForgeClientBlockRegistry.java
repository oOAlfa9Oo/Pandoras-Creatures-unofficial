package andrews.pandoras_creatures.forge.client.bootstrap;

import andrews.pandoras_creatures.client.renderer.special.EndTrollBoxSpecialRenderer;
import andrews.pandoras_creatures.client.renderer.special.PCSpecialModelRendererRegistrar;
import andrews.pandoras_creatures.forge.registry.PCForgeBlocks;
import andrews.pandoras_creatures.registry.block.PCBlockRenderLayers;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public final class PCForgeClientBlockRegistry {
    private PCForgeClientBlockRegistry() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(PCForgeClientBlockRegistry::onClientSetup);
    }

    private static void onClientSetup(FMLClientSetupEvent event) {
        // Forge 54.1.14 no expone un evento de moddeo para registrar tipos de
        // SpecialModelRenderer de items (solo parchea el de bloques), asi que se usa la misma
        // reflection sobre SpecialModelRenderers.ID_MAPPER que fabric (ver
        // PCSpecialModelRendererRegistrar). No requiere el render thread, corre en el hilo de
        // FML antes de que se hagan los bakes de modelos.
        PCSpecialModelRendererRegistrar.register(EndTrollBoxSpecialRenderer.Unbaked.ID, EndTrollBoxSpecialRenderer.Unbaked.MAP_CODEC);

        event.enqueueWork(() -> {
            for (String blockId : PCBlockRenderLayers.cutoutBlockIds()) {
                ItemBlockRenderTypes.setRenderLayer(PCForgeBlocks.getSimpleBlock(blockId), RenderType.cutout());
            }
        });
    }
}
