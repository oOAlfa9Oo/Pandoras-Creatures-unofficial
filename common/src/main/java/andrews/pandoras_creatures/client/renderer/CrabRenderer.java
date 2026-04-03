package andrews.pandoras_creatures.client.renderer;

import andrews.pandoras_creatures.client.model.CrabModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.entities.CrabEntity;
import andrews.pandoras_creatures.entities.crab.CrabVariantCatalog;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CrabRenderer extends MobRenderer<CrabEntity, CrabModel<CrabEntity>> {
    public CrabRenderer(EntityRendererProvider.Context context) {
        super(context, new CrabModel<>(context.bakeLayer(PCModelLayers.CRAB)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(CrabEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MODID, CrabVariantCatalog.texturePath(entity.getCrabType()));
    }
}
