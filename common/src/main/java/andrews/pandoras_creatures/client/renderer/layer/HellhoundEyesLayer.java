package andrews.pandoras_creatures.client.renderer.layer;

import andrews.pandoras_creatures.client.model.base.PCEntityRenderState;
import andrews.pandoras_creatures.entities.HellhoundEntity;
import andrews.pandoras_creatures.entities.hellhound.HellhoundVisualRules;
import andrews.pandoras_creatures.util.Reference;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class HellhoundEyesLayer<E extends HellhoundEntity, M extends EntityModel<? super PCEntityRenderState<E>>> extends RenderLayer<PCEntityRenderState<E>, M> {
    private static final Identifier HELLHOUND_EYES_LAYER = Identifier.fromNamespaceAndPath(Reference.MODID, "textures/entity/hellhound/hellhound_eyes.png");

    public HellhoundEyesLayer(RenderLayerParent<PCEntityRenderState<E>, M> renderer) {
        super(renderer);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, PCEntityRenderState<E> state, float limbSwing, float limbSwingAmount) {
        if (state.entity != null && HellhoundVisualRules.showsEyesLayer(state.entity.getHellhoundType())) {
            renderColoredCutoutModel(this.getParentModel(), HELLHOUND_EYES_LAYER, poseStack, submitNodeCollector, 15728640, state, OverlayTexture.NO_OVERLAY, -1);
        }
    }
}
