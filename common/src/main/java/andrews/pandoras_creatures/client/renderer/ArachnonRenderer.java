package andrews.pandoras_creatures.client.renderer;

import andrews.pandoras_creatures.client.model.ArachnonModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.client.renderer.base.PCMobRenderer;
import andrews.pandoras_creatures.client.renderer.layer.ArachnonEyeLayer;
import andrews.pandoras_creatures.entities.ArachnonEntity;
import andrews.pandoras_creatures.entities.arachnon.ArachnonVisualRules;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class ArachnonRenderer extends PCMobRenderer<ArachnonEntity, ArachnonModel<ArachnonEntity>> {

    public ArachnonRenderer(EntityRendererProvider.Context context) {
        super(context, new ArachnonModel<>(context.bakeLayer(PCModelLayers.ARACHNON)), 1.5F);
        this.addLayer(new ArachnonEyeLayer<>(this));
    }

    @Override
    protected Identifier getTextureLocation(ArachnonEntity entity) {
        return Identifier.fromNamespaceAndPath(Reference.MODID, ArachnonVisualRules.texturePathString());
    }
}
