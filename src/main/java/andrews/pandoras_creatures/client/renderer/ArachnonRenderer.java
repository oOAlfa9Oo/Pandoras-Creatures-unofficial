package andrews.pandoras_creatures.client.renderer;

import andrews.pandoras_creatures.client.model.ArachnonModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.client.renderer.layer.ArachnonEyeLayer;
import andrews.pandoras_creatures.entities.ArachnonEntity;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ArachnonRenderer extends MobRenderer<ArachnonEntity, ArachnonModel<ArachnonEntity>> {

    public ArachnonRenderer(EntityRendererProvider.Context context) {
        super(context, new ArachnonModel<>(context.bakeLayer(PCModelLayers.ARACHNON)), 1.5F);
        this.addLayer(new ArachnonEyeLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(ArachnonEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MODID, "textures/entity/arachnon/arachnon.png");
    }
}
