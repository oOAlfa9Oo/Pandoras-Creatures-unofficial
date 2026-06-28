package andrews.pandoras_creatures.client.renderer.layer;

import andrews.pandoras_creatures.entities.EndTrollEntity;
import andrews.pandoras_creatures.util.Reference;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
public class EndTrollEyeLayer<E extends EndTrollEntity, M extends EntityModel<E>> extends RenderLayer<E, M> {
    private static final ResourceLocation END_TROLL_EYE_LAYER = Reference.id("textures/entity/end_troll/end_troll_eye_1.png");

    public EndTrollEyeLayer(RenderLayerParent<E, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, E entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.eyes(END_TROLL_EYE_LAYER));
        this.getParentModel().renderToBuffer(poseStack, vertexConsumer, 15728880, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}

