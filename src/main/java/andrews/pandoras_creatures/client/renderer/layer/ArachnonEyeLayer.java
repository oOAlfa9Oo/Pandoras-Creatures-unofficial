package andrews.pandoras_creatures.client.renderer.layer;

import andrews.pandoras_creatures.client.renderer.util.PCRenderTypes;
import andrews.pandoras_creatures.entities.ArachnonEntity;
import andrews.pandoras_creatures.entities.arachnon.ArachnonVisualRules;
import andrews.pandoras_creatures.util.Reference;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ArachnonEyeLayer<E extends ArachnonEntity, M extends EntityModel<E>> extends RenderLayer<E, M> {
    private static final ResourceLocation ARACHNON_EYE_LAYER = ResourceLocation.fromNamespaceAndPath(Reference.MODID, ArachnonVisualRules.eyeTexturePathString());

    public ArachnonEyeLayer(RenderLayerParent<E, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, E entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer vertexConsumer = buffer.getBuffer(PCRenderTypes.getEmissiveEntity(ARACHNON_EYE_LAYER));
        this.getParentModel().renderToBuffer(poseStack, vertexConsumer, 15728880, OverlayTexture.NO_OVERLAY, -1);
    }
}
