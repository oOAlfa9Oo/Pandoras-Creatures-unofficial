package andrews.pandoras_creatures.client.renderer.layer;

import andrews.pandoras_creatures.entities.HellhoundEntity;
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

public class HellhoundEyesLayer<E extends HellhoundEntity, M extends EntityModel<E>> extends RenderLayer<E, M> {
    private static final ResourceLocation HELLHOUND_EYES_LAYER = ResourceLocation.fromNamespaceAndPath(Reference.MODID, "textures/entity/hellhound/hellhound_eyes.png");

    public HellhoundEyesLayer(RenderLayerParent<E, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, E entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (HellhoundVisualRules.showsEyesLayer(entity.getHellhoundType())) {
            VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.eyes(HELLHOUND_EYES_LAYER));
            this.getParentModel().renderToBuffer(poseStack, vertexConsumer, 15728640, OverlayTexture.NO_OVERLAY, -1);
        }
    }
}
