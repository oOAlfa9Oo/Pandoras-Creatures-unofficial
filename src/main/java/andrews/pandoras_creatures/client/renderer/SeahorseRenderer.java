package andrews.pandoras_creatures.client.renderer;

import andrews.pandoras_creatures.client.model.SeahorseModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.entities.SeahorseEntity;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SeahorseRenderer extends MobRenderer<SeahorseEntity, SeahorseModel<SeahorseEntity>> {

    public SeahorseRenderer(EntityRendererProvider.Context context) {
        super(context, new SeahorseModel<>(context.bakeLayer(PCModelLayers.SEAHORSE)), 0.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(SeahorseEntity entity) {
        if (entity.getName().getString().equals("Mr.Sparkles")) {
            return ResourceLocation.fromNamespaceAndPath(Reference.MODID, "textures/entity/seahorse/unicorn.png");
        } else {
            return ResourceLocation.fromNamespaceAndPath(Reference.MODID, "textures/entity/seahorse/seahorse_" + entity.getSeahorseType() + ".png");
        }
    }
}
