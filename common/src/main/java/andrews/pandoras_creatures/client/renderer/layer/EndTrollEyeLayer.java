package andrews.pandoras_creatures.client.renderer.layer;

import andrews.pandoras_creatures.client.renderer.state.EndTrollRenderState;
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
public class EndTrollEyeLayer<S extends EndTrollRenderState, M extends EntityModel<? super S>> extends RenderLayer<S, M> {
    private static final ResourceLocation END_TROLL_EYE_LAYER = ResourceLocation.fromNamespaceAndPath(Reference.MODID, "textures/entity/end_troll/end_troll_eye_1.png");

    public EndTrollEyeLayer(RenderLayerParent<S, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, S state, float yRot, float xRot) {
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.eyes(END_TROLL_EYE_LAYER));
        this.getParentModel().renderToBuffer(poseStack, vertexConsumer, 15728880, OverlayTexture.NO_OVERLAY, -1);
    }
}
