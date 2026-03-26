package andrews.pandoras_creatures.client.renderer;

import andrews.pandoras_creatures.client.model.EndTrollBulletModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.entities.projectiles.EndTrollBulletPoisonEntity;
import andrews.pandoras_creatures.util.Reference;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EndTrollBulletPoisonRenderer extends EntityRenderer<EndTrollBulletPoisonEntity> {
    private static final ResourceLocation END_TROLL_BULLET_TEXTURE = ResourceLocation.fromNamespaceAndPath(Reference.MODID, "textures/entity/end_troll/bullets/end_troll_bullet_2.png");
    private static final RenderType END_TROLL_BULLET_RENDER_TYPE = RenderType.entityTranslucent(END_TROLL_BULLET_TEXTURE);
    private final EndTrollBulletModel<EndTrollBulletPoisonEntity> model;

    public EndTrollBulletPoisonRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new EndTrollBulletModel<>(context.bakeLayer(PCModelLayers.END_TROLL_BULLET));
    }

    @Override
    protected int getBlockLightLevel(EndTrollBulletPoisonEntity entity, BlockPos pos) {
        return 15;
    }

    @Override
    public void render(EndTrollBulletPoisonEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        float f = rotLerp(entity.yRotO, entity.getYRot(), partialTicks);
        float f1 = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());
        float f2 = (float) entity.tickCount + partialTicks;
        poseStack.translate(0.0D, 0.15D, 0.0D);
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.sin(f2 * 0.1F) * 180.0F));
        poseStack.mulPose(Axis.XP.rotationDegrees(Mth.cos(f2 * 0.1F) * 180.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.sin(f2 * 0.15F) * 360.0F));
        poseStack.scale(-0.5F, -0.5F, 0.5F);
        this.model.setupAnim(entity, 0.0F, 0.0F, 0.0F, f, f1);
        VertexConsumer vertexConsumer = buffer.getBuffer(this.model.renderType(END_TROLL_BULLET_TEXTURE));
        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, -1);
        poseStack.scale(1.5F, 1.5F, 1.5F);
        VertexConsumer vertexConsumer1 = buffer.getBuffer(END_TROLL_BULLET_RENDER_TYPE);
        this.model.renderToBuffer(poseStack, vertexConsumer1, packedLight, OverlayTexture.NO_OVERLAY, 0x26FFFFFF);
        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(EndTrollBulletPoisonEntity entity) {
        return END_TROLL_BULLET_TEXTURE;
    }

    private float rotLerp(float prevRotation, float rotation, float partialTicks) {
        float f;
        for (f = rotation - prevRotation; f < -180.0F; f += 360.0F) {
        }
        while (f >= 180.0F) {
            f -= 360.0F;
        }
        return prevRotation + partialTicks * f;
    }
}
