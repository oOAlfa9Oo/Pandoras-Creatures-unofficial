package andrews.pandoras_creatures.client.renderer;

import andrews.pandoras_creatures.client.model.HellhoundModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.client.renderer.layer.HellhoundEyesLayer;
import andrews.pandoras_creatures.entities.HellhoundEntity;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HellhoundRenderer extends MobRenderer<HellhoundEntity, HellhoundModel<HellhoundEntity>> {

    public HellhoundRenderer(EntityRendererProvider.Context context) {
        super(context, new HellhoundModel<>(context.bakeLayer(PCModelLayers.HELLHOUND)), 0.6F);
        this.addLayer(new HellhoundEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(HellhoundEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MODID, "textures/entity/hellhound/hellhound_" + entity.getHellhoundType() + ".png");
    }
}
