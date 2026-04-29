package andrews.pandoras_creatures.client.renderer;

import andrews.pandoras_creatures.client.model.BufflonModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.entities.BufflonEntity;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BufflonRenderer extends MobRenderer<BufflonEntity, BufflonModel<BufflonEntity>> {

    public BufflonRenderer(EntityRendererProvider.Context context) {
        super(context, new BufflonModel<>(context.bakeLayer(PCModelLayers.BUFFLON)), 1.8F);
    }

    @Override
    public ResourceLocation getTextureLocation(BufflonEntity entity) {
        return new ResourceLocation(Reference.MODID, "textures/entity/bufflon/bufflon_" + entity.getBufflonType() + ".png");
    }
}

