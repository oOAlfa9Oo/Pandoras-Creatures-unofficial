package andrews.pandoras_creatures.client.renderer;

import andrews.pandoras_creatures.client.model.ArachnonModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.client.renderer.layer.ArachnonEyeLayer;
import andrews.pandoras_creatures.client.renderer.state.ArachnonRenderState;
import andrews.pandoras_creatures.entities.ArachnonEntity;
import andrews.pandoras_creatures.entities.arachnon.ArachnonVisualRules;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ArachnonRenderer extends MobRenderer<ArachnonEntity, ArachnonRenderState, ArachnonModel<ArachnonRenderState>> {

    public ArachnonRenderer(EntityRendererProvider.Context context) {
        super(context, new ArachnonModel<>(context.bakeLayer(PCModelLayers.ARACHNON)), 1.5F);
        this.addLayer(new ArachnonEyeLayer<>(this));
    }

    @Override
    public ArachnonRenderState createRenderState() {
        return new ArachnonRenderState();
    }

    @Override
    public void extractRenderState(ArachnonEntity entity, ArachnonRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.isEntityMoving = entity.isEntityMoving();
        state.attackTimer = entity.getAttackTimer();
        state.tickCount = entity.tickCount;
    }

    @Override
    public ResourceLocation getTextureLocation(ArachnonRenderState state) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MODID, ArachnonVisualRules.texturePathString());
    }
}
