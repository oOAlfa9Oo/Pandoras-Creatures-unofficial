package andrews.pandoras_creatures.client.bootstrap;

import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.client.model.tile.EndTrollBoxModel;
import andrews.pandoras_creatures.client.renderer.tile.EndTrollBoxBlockEntityRenderer;
import andrews.pandoras_creatures.registry.PCFabricBlockEntities;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;

public final class PCFabricClientBlockRegistry {
    private static boolean initialized;

    private PCFabricClientBlockRegistry() {
    }

    public static void registerAll() {
        if (initialized) {
            return;
        }
        initialized = true;

        ModelLayerRegistry.registerModelLayer(PCModelLayers.END_TROLL_BOX, EndTrollBoxModel::createBodyLayer);
        BlockEntityRendererRegistry.register(PCFabricBlockEntities.END_TROLL_BOX, EndTrollBoxBlockEntityRenderer::new);
    }
}
