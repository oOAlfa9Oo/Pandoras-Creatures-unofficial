package andrews.pandoras_creatures.client.renderer;

import andrews.pandoras_creatures.client.model.BufflonModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.client.renderer.base.PCMobRenderer;
import andrews.pandoras_creatures.entities.BufflonEntity;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class BufflonRenderer extends PCMobRenderer<BufflonEntity, BufflonModel<BufflonEntity>> {

    public BufflonRenderer(EntityRendererProvider.Context context) {
        super(context, new BufflonModel<>(context.bakeLayer(PCModelLayers.BUFFLON)), 1.8F);
    }

    @Override
    protected Identifier getTextureLocation(BufflonEntity entity) {
        return Identifier.fromNamespaceAndPath(Reference.MODID, "textures/entity/bufflon/bufflon_" + entity.getBufflonType() + ".png");
    }
}
