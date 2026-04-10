package andrews.pandoras_creatures.forge.client.bootstrap;

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
        event.enqueueWork(() -> {
            for (String blockId : PCBlockRenderLayers.cutoutBlockIds()) {
                ItemBlockRenderTypes.setRenderLayer(PCForgeBlocks.getSimpleBlock(blockId), RenderType.cutout());
            }
        });
    }
}
