package andrews.pandoras_creatures.client.renderer;

import andrews.pandoras_creatures.client.model.AcidicArchvineModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.client.renderer.state.AcidicArchvineRenderState;
import andrews.pandoras_creatures.entities.AcidicArchvineEntity;
import andrews.pandoras_creatures.util.Reference;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

public class AcidicArchvineRenderer extends MobRenderer<AcidicArchvineEntity, AcidicArchvineRenderState, AcidicArchvineModel<AcidicArchvineRenderState>> {
    private static final ResourceLocation TONGUE_1 = ResourceLocation.fromNamespaceAndPath(Reference.MODID, "textures/entity/acidic_archvine/acidic_archvine_tongue_1.png");
    private static final ResourceLocation TONGUE_2 = ResourceLocation.fromNamespaceAndPath(Reference.MODID, "textures/entity/acidic_archvine/acidic_archvine_tongue_2.png");
    private static final ResourceLocation TONGUE_3 = ResourceLocation.fromNamespaceAndPath(Reference.MODID, "textures/entity/acidic_archvine/acidic_archvine_tongue_3.png");
    private final RenderType TONGUE_RENDER_TYPE_1 = RenderType.entityCutoutNoCull(TONGUE_1);
    private final RenderType TONGUE_RENDER_TYPE_2 = RenderType.entityCutoutNoCull(TONGUE_2);
    private final RenderType TONGUE_RENDER_TYPE_3 = RenderType.entityCutoutNoCull(TONGUE_3);

    public AcidicArchvineRenderer(EntityRendererProvider.Context context) {
        super(context, new AcidicArchvineModel<>(context.bakeLayer(PCModelLayers.ACIDIC_ARCHVINE)), 0.5F);
    }

    @Override
    public AcidicArchvineRenderState createRenderState() {
        return new AcidicArchvineRenderState();
    }

    @Override
    public void extractRenderState(AcidicArchvineEntity entity, AcidicArchvineRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.archvineType = entity.getArchvineType();
        state.tickCount = entity.tickCount;
        state.gameTimeWithPartialTick = (float) entity.level().getGameTime() + partialTick;
        state.hasTargetedEntity = entity.hasTargetedEntity();
        state.attackState = entity.getAttackState();
        state.entityRotation = (float) Math.toRadians(state.yRot)
                - (float) Math.toRadians(Mth.rotLerp(partialTick, entity.yBodyRotO, entity.yBodyRot));

        LivingEntity target = entity.getTargetedEntity();
        state.hasTarget = target != null;
        if (target != null) {
            state.targetPosition = getPosition(target, (double) target.getBbHeight() * 0.5D, partialTick);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(AcidicArchvineRenderState state) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MODID, "textures/entity/acidic_archvine/acidic_archvine_" + state.archvineType + ".png");
    }

    private RenderType getEntityTongueRenderType(int archvineType) {
        return switch (archvineType) {
            case 2 -> TONGUE_RENDER_TYPE_2;
            case 3 -> TONGUE_RENDER_TYPE_3;
            default -> TONGUE_RENDER_TYPE_1;
        };
    }

    private static Vec3 getPosition(LivingEntity entity, double heightOffset, float partialTicks) {
        double x = Mth.lerp(partialTicks, entity.xOld, entity.getX());
        double y = Mth.lerp(partialTicks, entity.yOld, entity.getY()) + heightOffset;
        double z = Mth.lerp(partialTicks, entity.zOld, entity.getZ());
        return new Vec3(x, y, z);
    }

    @Override
    public void render(AcidicArchvineRenderState state, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        super.render(state, poseStack, buffer, packedLight);

        if (state.hasTarget) {
            float f = 1.0F;
            float f1 = state.gameTimeWithPartialTick;
            float f2 = f1 * 0.5F % 1.0F;
            float f3 = state.eyeHeight;

            poseStack.pushPose();
            poseStack.translate(0.0D, (double) f3, 0.0D);

            Vec3 vec3d = state.targetPosition;
            Vec3 vec3d1 = new Vec3(state.x, state.y + f3, state.z);
            Vec3 vec3d2 = vec3d.subtract(vec3d1);
            float f4 = (float) (vec3d2.length() + 1.0D);
            vec3d2 = vec3d2.normalize();
            float f5 = (float) Math.acos(vec3d2.y);
            float f6 = (float) Math.atan2(vec3d2.z, vec3d2.x);

            poseStack.mulPose(Axis.YP.rotationDegrees((((float) Math.PI / 2F) - f6) * (180F / (float) Math.PI)));
            poseStack.mulPose(Axis.XP.rotationDegrees(f5 * (180F / (float) Math.PI)));

            float f7 = f1 * 0.05F * -1.5F;
            float f8 = f * f;
            int j = 64 + (int) (f8 * 191.0F);
            int k = 32 + (int) (f8 * 191.0F);
            int l = 128 - (int) (f8 * 64.0F);

            float f11 = Mth.cos(f7 + 2.3561945F) * 0.282F;
            float f12 = Mth.sin(f7 + 2.3561945F) * 0.282F;
            float f13 = Mth.cos(f7 + ((float) Math.PI / 4F)) * 0.282F;
            float f14 = Mth.sin(f7 + ((float) Math.PI / 4F)) * 0.282F;
            float f15 = Mth.cos(f7 + 3.926991F) * 0.282F;
            float f16 = Mth.sin(f7 + 3.926991F) * 0.282F;
            float f17 = Mth.cos(f7 + 5.4977875F) * 0.282F;
            float f18 = Mth.sin(f7 + 5.4977875F) * 0.282F;
            float f19 = Mth.cos(f7 + (float) Math.PI) * 0.2F;
            float f20 = Mth.sin(f7 + (float) Math.PI) * 0.2F;
            float f21 = Mth.cos(f7 + 0.0F) * 0.2F;
            float f22 = Mth.sin(f7 + 0.0F) * 0.2F;
            float f23 = Mth.cos(f7 + ((float) Math.PI / 2F)) * 0.2F;
            float f24 = Mth.sin(f7 + ((float) Math.PI / 2F)) * 0.2F;
            float f25 = Mth.cos(f7 + ((float) Math.PI * 1.5F)) * 0.2F;
            float f26 = Mth.sin(f7 + ((float) Math.PI * 1.5F)) * 0.2F;
            float f29 = -1.0F + f2;
            float f30 = f4 * 2.5F + f29;

            VertexConsumer vertexConsumer = buffer.getBuffer(getEntityTongueRenderType(state.archvineType));
            PoseStack.Pose pose = poseStack.last();
            Matrix4f matrix4f = pose.pose();

            vertexThingy(vertexConsumer, matrix4f, pose, f19, f4, f20, j, k, l, 0.4999F, f30);
            vertexThingy(vertexConsumer, matrix4f, pose, f19, 0.0F, f20, j, k, l, 0.4999F, f29);
            vertexThingy(vertexConsumer, matrix4f, pose, f21, 0.0F, f22, j, k, l, 0.0F, f29);
            vertexThingy(vertexConsumer, matrix4f, pose, f21, f4, f22, j, k, l, 0.0F, f30);
            vertexThingy(vertexConsumer, matrix4f, pose, f23, f4, f24, j, k, l, 0.4999F, f30);
            vertexThingy(vertexConsumer, matrix4f, pose, f23, 0.0F, f24, j, k, l, 0.4999F, f29);
            vertexThingy(vertexConsumer, matrix4f, pose, f25, 0.0F, f26, j, k, l, 0.0F, f29);
            vertexThingy(vertexConsumer, matrix4f, pose, f25, f4, f26, j, k, l, 0.0F, f30);

            float f31 = 0.0F;
            if (state.tickCount % 2 == 0) {
                f31 = 0.5F;
            }

            vertexThingy(vertexConsumer, matrix4f, pose, f11, f4, f12, j, k, l, 0.5F, f31 + 0.5F);
            vertexThingy(vertexConsumer, matrix4f, pose, f13, f4, f14, j, k, l, 1.0F, f31 + 0.5F);
            vertexThingy(vertexConsumer, matrix4f, pose, f17, f4, f18, j, k, l, 1.0F, f31);
            vertexThingy(vertexConsumer, matrix4f, pose, f15, f4, f16, j, k, l, 0.5F, f31);

            poseStack.popPose();
        }
    }

    private static void vertexThingy(VertexConsumer vertexConsumer, Matrix4f matrix4, PoseStack.Pose pose, float x, float y, float z, int r, int g, int b, float u, float v) {
        vertexConsumer.addVertex(matrix4, x, y, z)
                .setColor(r, g, b, 255)
                .setUv(u, v)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(15728880)
                .setNormal(pose, 0.0F, 1.0F, 0.0F);
    }
}
