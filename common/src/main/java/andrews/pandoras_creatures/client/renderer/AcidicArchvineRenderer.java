package andrews.pandoras_creatures.client.renderer;

import andrews.pandoras_creatures.client.model.AcidicArchvineModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.client.model.base.PCEntityRenderState;
import andrews.pandoras_creatures.client.renderer.base.PCMobRenderer;
import andrews.pandoras_creatures.entities.AcidicArchvineEntity;
import andrews.pandoras_creatures.util.Reference;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class AcidicArchvineRenderer extends PCMobRenderer<AcidicArchvineEntity, AcidicArchvineModel<AcidicArchvineEntity>> {
    private static final Identifier TONGUE_1 = Identifier.fromNamespaceAndPath(Reference.MODID, "textures/entity/acidic_archvine/acidic_archvine_tongue_1.png");
    private static final Identifier TONGUE_2 = Identifier.fromNamespaceAndPath(Reference.MODID, "textures/entity/acidic_archvine/acidic_archvine_tongue_2.png");
    private static final RenderType TONGUE_RENDER_TYPE_1 = RenderTypes.entityCutout(TONGUE_1);
    private static final RenderType TONGUE_RENDER_TYPE_2 = RenderTypes.entityCutout(TONGUE_2);

    public AcidicArchvineRenderer(EntityRendererProvider.Context context) {
        super(context, new AcidicArchvineModel<>(context.bakeLayer(PCModelLayers.ACIDIC_ARCHVINE)), 0.5F);
    }

    @Override
    protected Identifier getTextureLocation(AcidicArchvineEntity entity) {
        return Identifier.fromNamespaceAndPath(Reference.MODID, "textures/entity/acidic_archvine/acidic_archvine_" + entity.getArchvineType() + ".png");
    }

    @Override
    protected void setupRotations(PCEntityRenderState<AcidicArchvineEntity> state, PoseStack poseStack, float bodyRot, float entityScale) {
        super.setupRotations(state, poseStack, bodyRot, entityScale);
        // Acidic Archvines attach to ceilings; flip the legacy upright model so it hangs downward.
        poseStack.translate(0.0F, (state.boundingBoxHeight + 0.1F) / entityScale, 0.0F);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F));
    }

    @Override
    public void submit(PCEntityRenderState<AcidicArchvineEntity> state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        super.submit(state, poseStack, submitNodeCollector, cameraRenderState);

        AcidicArchvineEntity entity = state.entity;
        LivingEntity target = entity == null ? null : entity.getTargetedEntity();
        if (entity == null || target == null) {
            return;
        }

        float partialTick = state.partialTick;
        float animationTime = entity.level().getGameTime() + partialTick;
        float textureOffset = animationTime * 0.5F % 1.0F;
        float eyeHeight = entity.getEyeHeight();

        poseStack.pushPose();
        poseStack.translate(0.0D, eyeHeight, 0.0D);

        Vec3 targetPosition = getPosition(target, target.getBbHeight() * 0.5D, partialTick);
        Vec3 sourcePosition = getPosition(entity, eyeHeight, partialTick);
        Vec3 tongueVector = targetPosition.subtract(sourcePosition);
        float tongueLength = (float) (tongueVector.length() + 1.0D);
        Vec3 normalizedTongueVector = tongueVector.normalize();

        float pitch = (float) Math.acos(normalizedTongueVector.y);
        float yaw = (float) Math.atan2(normalizedTongueVector.z, normalizedTongueVector.x);
        poseStack.mulPose(Axis.YP.rotationDegrees(((float) Math.PI / 2.0F - yaw) * Mth.RAD_TO_DEG));
        poseStack.mulPose(Axis.XP.rotationDegrees(pitch * Mth.RAD_TO_DEG));

        float rotation = animationTime * 0.05F * -1.5F;
        int red = 255;
        int green = 223;
        int blue = 192;

        float sideX1 = Mth.cos(rotation + (float) Math.PI) * 0.2F;
        float sideZ1 = Mth.sin(rotation + (float) Math.PI) * 0.2F;
        float sideX2 = Mth.cos(rotation) * 0.2F;
        float sideZ2 = Mth.sin(rotation) * 0.2F;
        float sideX3 = Mth.cos(rotation + (float) Math.PI / 2.0F) * 0.2F;
        float sideZ3 = Mth.sin(rotation + (float) Math.PI / 2.0F) * 0.2F;
        float sideX4 = Mth.cos(rotation + (float) Math.PI * 1.5F) * 0.2F;
        float sideZ4 = Mth.sin(rotation + (float) Math.PI * 1.5F) * 0.2F;

        float tipX1 = Mth.cos(rotation + 2.3561945F) * 0.282F;
        float tipZ1 = Mth.sin(rotation + 2.3561945F) * 0.282F;
        float tipX2 = Mth.cos(rotation + (float) Math.PI / 4.0F) * 0.282F;
        float tipZ2 = Mth.sin(rotation + (float) Math.PI / 4.0F) * 0.282F;
        float tipX3 = Mth.cos(rotation + 3.926991F) * 0.282F;
        float tipZ3 = Mth.sin(rotation + 3.926991F) * 0.282F;
        float tipX4 = Mth.cos(rotation + 5.4977875F) * 0.282F;
        float tipZ4 = Mth.sin(rotation + 5.4977875F) * 0.282F;

        float textureStart = -1.0F + textureOffset;
        float textureEnd = tongueLength * 2.5F + textureStart;
        float tipVOffset = entity.tickCount % 2 == 0 ? 0.5F : 0.0F;
        RenderType tongueRenderType = getEntityTongueRenderType(entity);

        submitNodeCollector.submitCustomGeometry(poseStack, tongueRenderType, (pose, vertexConsumer) -> {
            vertexThingy(vertexConsumer, pose, sideX1, tongueLength, sideZ1, red, green, blue, 0.4999F, textureEnd);
            vertexThingy(vertexConsumer, pose, sideX1, 0.0F, sideZ1, red, green, blue, 0.4999F, textureStart);
            vertexThingy(vertexConsumer, pose, sideX2, 0.0F, sideZ2, red, green, blue, 0.0F, textureStart);
            vertexThingy(vertexConsumer, pose, sideX2, tongueLength, sideZ2, red, green, blue, 0.0F, textureEnd);
            vertexThingy(vertexConsumer, pose, sideX3, tongueLength, sideZ3, red, green, blue, 0.4999F, textureEnd);
            vertexThingy(vertexConsumer, pose, sideX3, 0.0F, sideZ3, red, green, blue, 0.4999F, textureStart);
            vertexThingy(vertexConsumer, pose, sideX4, 0.0F, sideZ4, red, green, blue, 0.0F, textureStart);
            vertexThingy(vertexConsumer, pose, sideX4, tongueLength, sideZ4, red, green, blue, 0.0F, textureEnd);

            vertexThingy(vertexConsumer, pose, tipX1, tongueLength, tipZ1, red, green, blue, 0.5F, tipVOffset + 0.5F);
            vertexThingy(vertexConsumer, pose, tipX2, tongueLength, tipZ2, red, green, blue, 1.0F, tipVOffset + 0.5F);
            vertexThingy(vertexConsumer, pose, tipX4, tongueLength, tipZ4, red, green, blue, 1.0F, tipVOffset);
            vertexThingy(vertexConsumer, pose, tipX3, tongueLength, tipZ3, red, green, blue, 0.5F, tipVOffset);
        });

        poseStack.popPose();
    }

    private static Vec3 getPosition(LivingEntity entity, double yOffset, float partialTick) {
        double x = Mth.lerp(partialTick, entity.xo, entity.getX());
        double y = Mth.lerp(partialTick, entity.yo, entity.getY()) + yOffset;
        double z = Mth.lerp(partialTick, entity.zo, entity.getZ());
        return new Vec3(x, y, z);
    }

    private static RenderType getEntityTongueRenderType(AcidicArchvineEntity entity) {
        return entity.getArchvineType() == 1 ? TONGUE_RENDER_TYPE_1 : TONGUE_RENDER_TYPE_2;
    }

    private static void vertexThingy(VertexConsumer vertexConsumer, PoseStack.Pose pose, float x, float y, float z, int red, int green, int blue, float u, float v) {
        vertexConsumer.addVertex(pose, x, y, z)
                .setColor(red, green, blue, 255)
                .setUv(u, v)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(15728880)
                .setNormal(pose, 0.0F, 1.0F, 0.0F);
    }
}
