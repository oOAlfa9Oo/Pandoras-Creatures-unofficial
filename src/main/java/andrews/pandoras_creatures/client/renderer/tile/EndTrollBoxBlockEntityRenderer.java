package andrews.pandoras_creatures.client.renderer.tile;

import andrews.pandoras_creatures.block_entities.EndTrollBoxBlockEntity;
import andrews.pandoras_creatures.client.model.tile.EndTrollBoxModel;
import andrews.pandoras_creatures.objects.blocks.EndTrollBoxBlock;
import andrews.pandoras_creatures.util.Reference;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;
import java.util.stream.Stream;

@OnlyIn(Dist.CLIENT)
public class EndTrollBoxBlockEntityRenderer implements BlockEntityRenderer<EndTrollBoxBlockEntity> {
    private final EndTrollBoxModel blockModel;

    public static final ResourceLocation DEFAULT_END_TROLL_BOX_TEXTURE = ResourceLocation.fromNamespaceAndPath(Reference.MODID, "textures/tile/end_troll_box.png");
    public static final List<ResourceLocation> END_TROLL_BOX_TEXTURES = Stream.of(
            "white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray",
            "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black"
    ).map(color -> ResourceLocation.fromNamespaceAndPath(Reference.MODID, "textures/tile/" + color + "_end_troll_box.png"))
            .collect(ImmutableList.toImmutableList());

    public EndTrollBoxBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.blockModel = new EndTrollBoxModel(context.bakeLayer(PCItemRenderer.END_TROLL_BOX_LAYER));
    }

    @Override
    public void render(EndTrollBoxBlockEntity blockEntity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Direction direction = Direction.UP;
        float pixelSize = 0.0625F;

        if (blockEntity.hasLevel()) {
            BlockState blockState = blockEntity.getLevel().getBlockState(blockEntity.getBlockPos());
            if (blockState.getBlock() instanceof EndTrollBoxBlock) {
                direction = blockState.getValue(EndTrollBoxBlock.FACING);
            }
        }

        DyeColor dyeColor = blockEntity.getColor();
        ResourceLocation texture;
        if (dyeColor == null) {
            texture = DEFAULT_END_TROLL_BOX_TEXTURE;
        } else {
            texture = END_TROLL_BOX_TEXTURES.get(dyeColor.getId());
        }

        poseStack.pushPose();

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(texture));

        poseStack.translate(0.5D, 1.5D, 0.5D);
        poseStack.scale(1.0F, -1.0F, -1.0F);

        switch (direction) {
            case DOWN:
                poseStack.translate(0.0F, 2.0F, 0.0F);
                poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
                break;
            case UP:
            default:
                break;
            case NORTH:
                poseStack.translate(0.0F, 1.0F, 1.0F);
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F));
                break;
            case SOUTH:
                poseStack.translate(0.0F, 1.0F, -1.0F);
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                break;
            case WEST:
                poseStack.translate(-1.0F, 1.0F, 0.0F);
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                poseStack.mulPose(Axis.ZN.rotationDegrees(90.0F));
                break;
            case EAST:
                poseStack.translate(1.0F, 1.0F, 0.0F);
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
        }

        // Get the opening progress
        float progress = blockEntity.getProgress(partialTick);

        // Bottom Front Left
        poseStack.pushPose();
        poseStack.translate(progress * (pixelSize * 1.5F), 0.0F, -progress * (pixelSize * 1.5F));
        this.blockModel.bottom_front_left.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        poseStack.popPose();

        // Bottom Front Right
        poseStack.pushPose();
        poseStack.translate(-progress * (pixelSize * 1.5F), 0.0F, -progress * (pixelSize * 1.5F));
        this.blockModel.bottom_front_right.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        poseStack.popPose();

        // Bottom Back Left
        poseStack.pushPose();
        poseStack.translate(progress * (pixelSize * 1.5F), 0.0F, progress * (pixelSize * 1.5F));
        this.blockModel.bottom_back_left.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        poseStack.popPose();

        // Bottom Back Right
        poseStack.pushPose();
        poseStack.translate(-progress * (pixelSize * 1.5F), 0.0F, progress * (pixelSize * 1.5F));
        this.blockModel.bottom_back_right.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.translate(0.0F, -progress * (pixelSize * 1.5F), 0.0F);
        this.blockModel.base.render(poseStack, vertexConsumer, packedLight, packedOverlay);

        // Decorations
        this.blockModel.decoration_front_left.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        this.blockModel.decoration_front_right.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        this.blockModel.decoration_back_left.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        this.blockModel.decoration_back_right.render(poseStack, vertexConsumer, packedLight, packedOverlay);

        // Lid Front
        poseStack.pushPose();
        poseStack.translate(0.0F, (pixelSize * 13.5F), (pixelSize * -6.0F));
        poseStack.mulPose(Axis.XP.rotationDegrees(30.0F * progress));
        poseStack.translate(-0.0F, -(pixelSize * 13.5F), -(pixelSize * -6.0F));
        this.blockModel.lid_front.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        poseStack.popPose();

        // Lid Back
        poseStack.pushPose();
        poseStack.translate(0.0F, (pixelSize * 13.5F), (pixelSize * 6.0F));
        poseStack.mulPose(Axis.XN.rotationDegrees(30.0F * progress));
        poseStack.translate(-0.0F, -(pixelSize * 13.5F), -(pixelSize * 6.0F));
        this.blockModel.lid_back.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        poseStack.popPose();

        // Lid Left
        poseStack.pushPose();
        poseStack.translate((pixelSize * 6.0F), (pixelSize * 13.5F), 0.0F);
        poseStack.mulPose(Axis.ZP.rotationDegrees(10.0F * progress));
        poseStack.translate(-(pixelSize * 6.0F), -(pixelSize * 13.5F), 0.0F);
        this.blockModel.lid_left.render(poseStack, vertexConsumer, packedLight, packedOverlay);

        poseStack.pushPose();
        poseStack.translate((pixelSize * 6.0F), (pixelSize * 11.5F), 0.0F);
        poseStack.mulPose(Axis.ZN.rotationDegrees(65.0F * progress));
        poseStack.translate(-(pixelSize * 6.0F), -(pixelSize * 11.5F), 0.0F);
        this.blockModel.lid_top_left.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        poseStack.popPose();
        poseStack.popPose();

        // Lid Right
        poseStack.pushPose();
        poseStack.translate((pixelSize * -6.0F), (pixelSize * 13.5F), 0.0F);
        poseStack.mulPose(Axis.ZN.rotationDegrees(10.0F * progress));
        poseStack.translate(-(pixelSize * -6.0F), -(pixelSize * 13.5F), 0.0F);
        this.blockModel.lid_right.render(poseStack, vertexConsumer, packedLight, packedOverlay);

        poseStack.pushPose();
        poseStack.translate((pixelSize * -6.0F), (pixelSize * 11.5F), 0.0F);
        poseStack.mulPose(Axis.ZP.rotationDegrees(65.0F * progress));
        poseStack.translate(-(pixelSize * -6.0F), -(pixelSize * 11.5F), 0.0F);
        this.blockModel.lid_top_right.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        poseStack.popPose();
        poseStack.popPose();

        // Top Cubes
        poseStack.pushPose();
        poseStack.translate(0.0F, -progress * (pixelSize * 1.5F), 0.0F);

        // Top Front Left
        poseStack.pushPose();
        poseStack.translate(progress * (pixelSize * 1.5F), 0.0F, -progress * (pixelSize * 1.5F));
        this.blockModel.top_front_left_1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        poseStack.popPose();

        // Top Front Right
        poseStack.pushPose();
        poseStack.translate(-progress * (pixelSize * 1.5F), 0.0F, -progress * (pixelSize * 1.5F));
        this.blockModel.top_front_left.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        poseStack.popPose();

        // Top Back Left
        poseStack.pushPose();
        poseStack.translate(progress * (pixelSize * 1.5F), 0.0F, progress * (pixelSize * 1.5F));
        this.blockModel.top_back_left.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        poseStack.popPose();

        // Top Back Right
        poseStack.pushPose();
        poseStack.translate(-progress * (pixelSize * 1.5F), 0.0F, progress * (pixelSize * 1.5F));
        this.blockModel.top_back_right.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        poseStack.popPose();

        poseStack.popPose();
        poseStack.popPose();
        poseStack.popPose();
    }
}
