package andrews.pandoras_creatures.client.renderer;

import andrews.pandoras_creatures.client.model.SeahorseModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.client.renderer.state.SeahorseRenderState;
import andrews.pandoras_creatures.entities.SeahorseEntity;
import andrews.pandoras_creatures.entities.seahorse.SeahorseVisualRules;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SeahorseRenderer extends MobRenderer<SeahorseEntity, SeahorseRenderState, SeahorseModel<SeahorseRenderState>> {
    public SeahorseRenderer(EntityRendererProvider.Context context) {
        super(context, new SeahorseModel<>(context.bakeLayer(PCModelLayers.SEAHORSE)), 0.2F);
    }

    @Override
    public SeahorseRenderState createRenderState() {
        return new SeahorseRenderState();
    }

    @Override
    public void extractRenderState(SeahorseEntity entity, SeahorseRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.seahorseType = entity.getSeahorseType();
        state.seahorseSize = entity.getSeahorseSize();
        state.customNameString = entity.getName().getString();
        state.isSpecialNamed = SeahorseVisualRules.isSpecialNamed(state.customNameString);
        state.isEntityMovingHorizontally = entity.isEntityMovingHorizontally();
        state.tickCount = entity.tickCount;
        state.isAlive = entity.isAlive();
    }

    @Override
    public ResourceLocation getTextureLocation(SeahorseRenderState state) {
        return ResourceLocation.fromNamespaceAndPath(
                Reference.MODID,
                SeahorseVisualRules.texturePath(state.seahorseType, state.customNameString)
        );
    }
}
