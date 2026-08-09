package andrews.pandoras_creatures.client.renderer.layer;

import andrews.pandoras_creatures.client.renderer.state.HellhoundRenderState;
import andrews.pandoras_creatures.entities.hellhound.HellhoundVisualRules;
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

public class HellhoundEyesLayer<S extends HellhoundRenderState, M extends EntityModel<? super S>> extends RenderLayer<S, M> {
    private static final ResourceLocation HELLHOUND_EYES_LAYER = ResourceLocation.fromNamespaceAndPath(Reference.MODID, "textures/entity/hellhound/hellhound_eyes.png");

    public HellhoundEyesLayer(RenderLayerParent<S, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, S state, float yRot, float xRot) {
        if (HellhoundVisualRules.showsEyesLayer(state.hellhoundType)) {
            VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.eyes(HELLHOUND_EYES_LAYER));
            this.getParentModel().renderToBuffer(poseStack, vertexConsumer, 15728640, OverlayTexture.NO_OVERLAY, -1);
        }
    }
}
