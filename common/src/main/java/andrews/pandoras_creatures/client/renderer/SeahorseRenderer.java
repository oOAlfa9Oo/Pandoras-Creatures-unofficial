package andrews.pandoras_creatures.client.renderer;

import andrews.pandoras_creatures.client.model.SeahorseModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.entities.SeahorseEntity;
import andrews.pandoras_creatures.entities.seahorse.SeahorseVisualRules;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SeahorseRenderer extends MobRenderer<SeahorseEntity, SeahorseModel<SeahorseEntity>> {
    public SeahorseRenderer(EntityRendererProvider.Context context) {
        super(context, new SeahorseModel<>(context.bakeLayer(PCModelLayers.SEAHORSE)), 0.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(SeahorseEntity entity) {
        return Reference.id(
                SeahorseVisualRules.texturePath(entity.getSeahorseType(), entity.getName().getString())
        );
    }
}

