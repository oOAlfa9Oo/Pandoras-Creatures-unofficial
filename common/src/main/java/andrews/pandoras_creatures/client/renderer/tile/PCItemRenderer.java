package andrews.pandoras_creatures.client.renderer.tile;

import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.client.model.tile.EndTrollBoxModel;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxBootstrap;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxPalette;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class PCItemRenderer extends BlockEntityWithoutLevelRenderer {
    private static PCItemRenderer instance;
    private EndTrollBoxModel endTrollBoxModel;

    public static final Identifier DEFAULT_END_TROLL_BOX_TEXTURE = PCEndTrollBoxPalette.textureId(null);
    public static final List<Identifier> END_TROLL_BOX_TEXTURES = PCEndTrollBoxPalette.orderedColors().stream()
            .map(PCEndTrollBoxPalette::textureId)
            .collect(ImmutableList.toImmutableList());

    public PCItemRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet modelSet) {
        super(dispatcher, modelSet);
        this.endTrollBoxModel = new EndTrollBoxModel(modelSet.bakeLayer(PCModelLayers.END_TROLL_BOX));
    }

    public static PCItemRenderer getInstance() {
        if (instance == null) {
            Minecraft mc = Minecraft.getInstance();
            instance = new PCItemRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());
        }
        return instance;
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack,
                             MultiBufferSource buffer, int packedLight, int packedOverlay) {
        Item item = stack.getItem();

        // Check if this is an End Troll Box
        if (isEndTrollBox(item)) {
            renderEndTrollBox(stack, poseStack, buffer, packedLight, packedOverlay);
        }
        // TODO: Add Pandoric Shard rendering here when implemented
    }

    private boolean isEndTrollBox(Item item) {
        String path = BuiltInRegistries.ITEM.getKey(item).getPath();
        return path.equals(PCEndTrollBoxBootstrap.blockId(null))
                || PCEndTrollBoxPalette.orderedColors().stream()
                .map(PCEndTrollBoxBootstrap::blockId)
                .anyMatch(path::equals);
    }

    private void renderEndTrollBox(ItemStack stack, PoseStack poseStack, MultiBufferSource buffer,
                                   int packedLight, int packedOverlay) {
        Item item = stack.getItem();
        DyeColor color = getColorFromItem(item);
        Identifier texture = color == null ? DEFAULT_END_TROLL_BOX_TEXTURE : END_TROLL_BOX_TEXTURES.get(color.getId());

        poseStack.pushPose();

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(texture));

        poseStack.translate(0.5D, 1.5D, 0.5D);
        poseStack.scale(1.0F, -1.0F, -1.0F);

        // Render the closed box (no animation for item form)
        endTrollBoxModel.base.render(poseStack, vertexConsumer, packedLight, packedOverlay);

        // Bottom corners
        endTrollBoxModel.bottom_front_left.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        endTrollBoxModel.bottom_front_right.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        endTrollBoxModel.bottom_back_left.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        endTrollBoxModel.bottom_back_right.render(poseStack, vertexConsumer, packedLight, packedOverlay);

        // Decorations
        endTrollBoxModel.decoration_front_left.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        endTrollBoxModel.decoration_front_right.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        endTrollBoxModel.decoration_back_left.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        endTrollBoxModel.decoration_back_right.render(poseStack, vertexConsumer, packedLight, packedOverlay);

        // Lids (closed position)
        endTrollBoxModel.lid_front.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        endTrollBoxModel.lid_back.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        endTrollBoxModel.lid_left.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        endTrollBoxModel.lid_right.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        endTrollBoxModel.lid_top_left.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        endTrollBoxModel.lid_top_right.render(poseStack, vertexConsumer, packedLight, packedOverlay);

        // Top corners
        endTrollBoxModel.top_front_left.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        endTrollBoxModel.top_front_left_1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        endTrollBoxModel.top_back_left.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        endTrollBoxModel.top_back_right.render(poseStack, vertexConsumer, packedLight, packedOverlay);

        poseStack.popPose();
    }

    private DyeColor getColorFromItem(Item item) {
        String path = BuiltInRegistries.ITEM.getKey(item).getPath();
        if (path.equals(PCEndTrollBoxBootstrap.blockId(null))) {
            return null;
        }

        for (DyeColor color : PCEndTrollBoxPalette.orderedColors()) {
            if (path.equals(PCEndTrollBoxBootstrap.blockId(color))) {
                return color;
            }
        }

        return null;
    }
}
