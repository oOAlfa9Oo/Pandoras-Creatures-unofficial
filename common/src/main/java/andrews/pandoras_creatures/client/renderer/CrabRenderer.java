package andrews.pandoras_creatures.client.renderer;

import andrews.pandoras_creatures.client.model.CrabModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.client.renderer.state.CrabRenderState;
import andrews.pandoras_creatures.entities.CrabEntity;
import andrews.pandoras_creatures.entities.crab.CrabBehaviorRules;
import andrews.pandoras_creatures.entities.crab.CrabVariantCatalog;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CrabRenderer extends MobRenderer<CrabEntity, CrabRenderState, CrabModel<CrabRenderState>> {
    public CrabRenderer(EntityRendererProvider.Context context) {
        super(context, new CrabModel<>(context.bakeLayer(PCModelLayers.CRAB)), 0.3F);
    }

    @Override
    public CrabRenderState createRenderState() {
        return new CrabRenderState();
    }

    @Override
    public void extractRenderState(CrabEntity entity, CrabRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.crabType = entity.getCrabType();
        state.isEntityMoving = entity.isEntityMoving();
        state.isPartying = entity.isPartying();
        state.showsHat = CrabBehaviorRules.showsHat(entity.getName().getString());
        state.tickCount = entity.tickCount;
    }

    @Override
    public ResourceLocation getTextureLocation(CrabRenderState state) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MODID, CrabVariantCatalog.texturePath(state.crabType));
    }
}
