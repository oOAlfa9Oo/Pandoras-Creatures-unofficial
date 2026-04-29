package andrews.pandoras_creatures.client.renderer;

import andrews.pandoras_creatures.client.model.EndTrollModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.client.renderer.layer.EndTrollEyeLayer;
import andrews.pandoras_creatures.entities.EndTrollEntity;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
public class EndTrollRenderer extends MobRenderer<EndTrollEntity, EndTrollModel<EndTrollEntity>> {

    public EndTrollRenderer(EntityRendererProvider.Context context) {
        super(context, new EndTrollModel<>(context.bakeLayer(PCModelLayers.END_TROLL)), 2.0F);
        this.addLayer(new EndTrollEyeLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(EndTrollEntity entity) {
        return new ResourceLocation(Reference.MODID, "textures/entity/end_troll/end_troll_1.png");
    }
}

