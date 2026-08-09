package andrews.pandoras_creatures.client.renderer;

import andrews.pandoras_creatures.client.model.HellhoundModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.client.renderer.layer.HellhoundEyesLayer;
import andrews.pandoras_creatures.client.renderer.state.HellhoundRenderState;
import andrews.pandoras_creatures.entities.HellhoundEntity;
import andrews.pandoras_creatures.entities.hellhound.HellhoundVariantCatalog;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HellhoundRenderer extends MobRenderer<HellhoundEntity, HellhoundRenderState, HellhoundModel<HellhoundRenderState>> {

    public HellhoundRenderer(EntityRendererProvider.Context context) {
        super(context, new HellhoundModel<>(context.bakeLayer(PCModelLayers.HELLHOUND)), 0.6F);
        this.addLayer(new HellhoundEyesLayer<>(this));
    }

    @Override
    public HellhoundRenderState createRenderState() {
        return new HellhoundRenderState();
    }

    @Override
    public void extractRenderState(HellhoundEntity entity, HellhoundRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.isEntityMoving = entity.isEntityMoving();
        state.isCharging = entity.getIsCharging();
        state.tickCount = entity.tickCount;
        state.hellhoundType = entity.getHellhoundType();
    }

    @Override
    public ResourceLocation getTextureLocation(HellhoundRenderState state) {
        return HellhoundVariantCatalog.texturePath(state.hellhoundType);
    }
}
