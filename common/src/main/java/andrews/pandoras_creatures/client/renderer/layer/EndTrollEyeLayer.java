package andrews.pandoras_creatures.client.renderer.layer;

import andrews.pandoras_creatures.client.model.base.PCEntityRenderState;
import andrews.pandoras_creatures.entities.EndTrollEntity;
import andrews.pandoras_creatures.util.Reference;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
public class EndTrollEyeLayer<E extends EndTrollEntity, M extends EntityModel<? super PCEntityRenderState<E>>> extends RenderLayer<PCEntityRenderState<E>, M> {
    private static final Identifier END_TROLL_EYE_LAYER = Identifier.fromNamespaceAndPath(Reference.MODID, "textures/entity/end_troll/end_troll_eye_1.png");

    public EndTrollEyeLayer(RenderLayerParent<PCEntityRenderState<E>, M> renderer) {
        super(renderer);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, PCEntityRenderState<E> state, float limbSwing, float limbSwingAmount) {
        renderColoredCutoutModel(this.getParentModel(), END_TROLL_EYE_LAYER, poseStack, submitNodeCollector, 15728880, state, OverlayTexture.NO_OVERLAY, -1);
    }
}
