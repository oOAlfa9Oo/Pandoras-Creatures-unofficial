package andrews.pandoras_creatures.client.bootstrap;

import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.client.model.tile.EndTrollBoxModel;
import andrews.pandoras_creatures.client.renderer.tile.EndTrollBoxBlockEntityRenderer;
import andrews.pandoras_creatures.client.renderer.tile.PCItemRenderer;
import andrews.pandoras_creatures.registry.PCFabricBlockEntities;
import andrews.pandoras_creatures.registry.PCFabricBlocks;
import andrews.pandoras_creatures.registry.PCFabricItems;
import andrews.pandoras_creatures.registry.block.PCBlockRenderLayers;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.Item;

public final class PCFabricClientBlockRegistry {
    private static boolean initialized;

    private PCFabricClientBlockRegistry() {
    }

    public static void registerAll() {
        if (initialized) {
            return;
        }
        initialized = true;

        for (String blockId : PCBlockRenderLayers.cutoutBlockIds()) {
            BlockRenderLayerMap.INSTANCE.putBlock(PCFabricBlocks.getSimpleBlock(blockId), RenderType.cutout());
        }

        EntityModelLayerRegistry.registerModelLayer(PCModelLayers.END_TROLL_BOX, EndTrollBoxModel::createBodyLayer);
        BlockEntityRenderers.register(PCFabricBlockEntities.END_TROLL_BOX, EndTrollBoxBlockEntityRenderer::new);

        for (Item item : PCFabricItems.getEndTrollBoxItems()) {
            BuiltinItemRendererRegistry.INSTANCE.register(item, (stack, displayContext, poseStack, buffer, packedLight, packedOverlay) ->
                    PCItemRenderer.getInstance().renderByItem(stack, displayContext, poseStack, buffer, packedLight, packedOverlay));
        }
    }
}
