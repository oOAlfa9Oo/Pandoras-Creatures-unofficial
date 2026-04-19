package andrews.pandoras_creatures.client.renderer.tile;

import andrews.pandoras_creatures.block_entities.EndTrollBoxBlockEntity;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.client.model.tile.EndTrollBoxModel;
import andrews.pandoras_creatures.content.block.EndTrollBoxBlock;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxPalette;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class EndTrollBoxBlockEntityRenderer implements BlockEntityRenderer<EndTrollBoxBlockEntity, EndTrollBoxBlockEntityRenderer.State> {
    private final EndTrollBoxModel blockModel;

    public static final Identifier DEFAULT_END_TROLL_BOX_TEXTURE = PCEndTrollBoxPalette.textureId(null);
    public static final List<Identifier> END_TROLL_BOX_TEXTURES = PCEndTrollBoxPalette.orderedColors().stream()
            .map(PCEndTrollBoxPalette::textureId)
            .collect(ImmutableList.toImmutableList());

    public EndTrollBoxBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.blockModel = new EndTrollBoxModel(context.bakeLayer(PCModelLayers.END_TROLL_BOX));
    }

    @Override
    public State createRenderState() {
        return new State();
    }

    @Override
    public void extractRenderState(EndTrollBoxBlockEntity blockEntity, State state, float partialTick, Vec3 cameraPos, ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        BlockEntityRenderState.extractBase(blockEntity, state, crumblingOverlay);
        state.direction = Direction.UP;

        if (blockEntity.hasLevel()) {
            BlockState blockState = blockEntity.getLevel().getBlockState(blockEntity.getBlockPos());
            if (blockState.getBlock() instanceof EndTrollBoxBlock) {
                state.direction = blockState.getValue(EndTrollBoxBlock.FACING);
            }
        }

        DyeColor dyeColor = blockEntity.getColor();
        state.texture = dyeColor == null ? DEFAULT_END_TROLL_BOX_TEXTURE : END_TROLL_BOX_TEXTURES.get(dyeColor.getId());
        state.progress = blockEntity.getProgress(partialTick);
    }

    @Override
    public void submit(State state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        RenderType renderType = RenderTypes.entityCutout(state.texture);
        int packedLight = state.lightCoords;
        int packedOverlay = OverlayTexture.NO_OVERLAY;
        float pixelSize = 0.0625F;
        float progress = state.progress;

        poseStack.pushPose();
        poseStack.translate(0.5D, 1.5D, 0.5D);
        poseStack.scale(1.0F, -1.0F, -1.0F);

        switch (state.direction) {
            case DOWN -> {
                poseStack.translate(0.0F, 2.0F, 0.0F);
                poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
            }
            case NORTH -> {
                poseStack.translate(0.0F, 1.0F, 1.0F);
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F));
            }
            case SOUTH -> {
                poseStack.translate(0.0F, 1.0F, -1.0F);
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
            }
            case WEST -> {
                poseStack.translate(-1.0F, 1.0F, 0.0F);
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                poseStack.mulPose(Axis.ZN.rotationDegrees(90.0F));
            }
            case EAST -> {
                poseStack.translate(1.0F, 1.0F, 0.0F);
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
            }
            default -> {
            }
        }

        poseStack.pushPose();
        poseStack.translate(progress * (pixelSize * 1.5F), 0.0F, -progress * (pixelSize * 1.5F));
        submitPart(submitNodeCollector, poseStack, renderType, this.blockModel.bottom_front_left, packedLight, packedOverlay, state.breakProgress);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.translate(-progress * (pixelSize * 1.5F), 0.0F, -progress * (pixelSize * 1.5F));
        submitPart(submitNodeCollector, poseStack, renderType, this.blockModel.bottom_front_right, packedLight, packedOverlay, state.breakProgress);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.translate(progress * (pixelSize * 1.5F), 0.0F, progress * (pixelSize * 1.5F));
        submitPart(submitNodeCollector, poseStack, renderType, this.blockModel.bottom_back_left, packedLight, packedOverlay, state.breakProgress);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.translate(-progress * (pixelSize * 1.5F), 0.0F, progress * (pixelSize * 1.5F));
        submitPart(submitNodeCollector, poseStack, renderType, this.blockModel.bottom_back_right, packedLight, packedOverlay, state.breakProgress);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.translate(0.0F, -progress * (pixelSize * 1.5F), 0.0F);
        submitPart(submitNodeCollector, poseStack, renderType, this.blockModel.base, packedLight, packedOverlay, state.breakProgress);
        submitPart(submitNodeCollector, poseStack, renderType, this.blockModel.decoration_front_left, packedLight, packedOverlay, state.breakProgress);
        submitPart(submitNodeCollector, poseStack, renderType, this.blockModel.decoration_front_right, packedLight, packedOverlay, state.breakProgress);
        submitPart(submitNodeCollector, poseStack, renderType, this.blockModel.decoration_back_left, packedLight, packedOverlay, state.breakProgress);
        submitPart(submitNodeCollector, poseStack, renderType, this.blockModel.decoration_back_right, packedLight, packedOverlay, state.breakProgress);

        poseStack.pushPose();
        poseStack.translate(0.0F, (pixelSize * 13.5F), (pixelSize * -6.0F));
        poseStack.mulPose(Axis.XP.rotationDegrees(30.0F * progress));
        poseStack.translate(-0.0F, -(pixelSize * 13.5F), -(pixelSize * -6.0F));
        submitPart(submitNodeCollector, poseStack, renderType, this.blockModel.lid_front, packedLight, packedOverlay, state.breakProgress);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.translate(0.0F, (pixelSize * 13.5F), (pixelSize * 6.0F));
        poseStack.mulPose(Axis.XN.rotationDegrees(30.0F * progress));
        poseStack.translate(-0.0F, -(pixelSize * 13.5F), -(pixelSize * 6.0F));
        submitPart(submitNodeCollector, poseStack, renderType, this.blockModel.lid_back, packedLight, packedOverlay, state.breakProgress);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.translate((pixelSize * 6.0F), (pixelSize * 13.5F), 0.0F);
        poseStack.mulPose(Axis.ZP.rotationDegrees(10.0F * progress));
        poseStack.translate(-(pixelSize * 6.0F), -(pixelSize * 13.5F), 0.0F);
        submitPart(submitNodeCollector, poseStack, renderType, this.blockModel.lid_left, packedLight, packedOverlay, state.breakProgress);
        poseStack.pushPose();
        poseStack.translate((pixelSize * 6.0F), (pixelSize * 11.5F), 0.0F);
        poseStack.mulPose(Axis.ZN.rotationDegrees(65.0F * progress));
        poseStack.translate(-(pixelSize * 6.0F), -(pixelSize * 11.5F), 0.0F);
        submitPart(submitNodeCollector, poseStack, renderType, this.blockModel.lid_top_left, packedLight, packedOverlay, state.breakProgress);
        poseStack.popPose();
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.translate((pixelSize * -6.0F), (pixelSize * 13.5F), 0.0F);
        poseStack.mulPose(Axis.ZN.rotationDegrees(10.0F * progress));
        poseStack.translate(-(pixelSize * -6.0F), -(pixelSize * 13.5F), 0.0F);
        submitPart(submitNodeCollector, poseStack, renderType, this.blockModel.lid_right, packedLight, packedOverlay, state.breakProgress);
        poseStack.pushPose();
        poseStack.translate((pixelSize * -6.0F), (pixelSize * 11.5F), 0.0F);
        poseStack.mulPose(Axis.ZP.rotationDegrees(65.0F * progress));
        poseStack.translate(-(pixelSize * -6.0F), -(pixelSize * 11.5F), 0.0F);
        submitPart(submitNodeCollector, poseStack, renderType, this.blockModel.lid_top_right, packedLight, packedOverlay, state.breakProgress);
        poseStack.popPose();
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.translate(0.0F, -progress * (pixelSize * 1.5F), 0.0F);
        poseStack.pushPose();
        poseStack.translate(progress * (pixelSize * 1.5F), 0.0F, -progress * (pixelSize * 1.5F));
        submitPart(submitNodeCollector, poseStack, renderType, this.blockModel.top_front_left_1, packedLight, packedOverlay, state.breakProgress);
        poseStack.popPose();
        poseStack.pushPose();
        poseStack.translate(-progress * (pixelSize * 1.5F), 0.0F, -progress * (pixelSize * 1.5F));
        submitPart(submitNodeCollector, poseStack, renderType, this.blockModel.top_front_left, packedLight, packedOverlay, state.breakProgress);
        poseStack.popPose();
        poseStack.pushPose();
        poseStack.translate(progress * (pixelSize * 1.5F), 0.0F, progress * (pixelSize * 1.5F));
        submitPart(submitNodeCollector, poseStack, renderType, this.blockModel.top_back_left, packedLight, packedOverlay, state.breakProgress);
        poseStack.popPose();
        poseStack.pushPose();
        poseStack.translate(-progress * (pixelSize * 1.5F), 0.0F, progress * (pixelSize * 1.5F));
        submitPart(submitNodeCollector, poseStack, renderType, this.blockModel.top_back_right, packedLight, packedOverlay, state.breakProgress);
        poseStack.popPose();
        poseStack.popPose();

        poseStack.popPose();
        poseStack.popPose();
    }

    private static void submitPart(SubmitNodeCollector submitNodeCollector, PoseStack poseStack, RenderType renderType, ModelPart part, int packedLight, int packedOverlay, ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        submitNodeCollector.submitModelPart(part, poseStack, renderType, packedLight, packedOverlay, null, false, false, -1, crumblingOverlay, 0);
    }

    public static class State extends BlockEntityRenderState {
        private Direction direction = Direction.UP;
        private Identifier texture = DEFAULT_END_TROLL_BOX_TEXTURE;
        private float progress;
    }
}
