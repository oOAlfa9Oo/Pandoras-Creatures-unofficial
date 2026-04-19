package andrews.pandoras_creatures.client.renderer;

import andrews.pandoras_creatures.client.model.CrabModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.client.renderer.base.PCMobRenderer;
import andrews.pandoras_creatures.entities.CrabEntity;
import andrews.pandoras_creatures.entities.crab.CrabVariantCatalog;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class CrabRenderer extends PCMobRenderer<CrabEntity, CrabModel<CrabEntity>> {
    public CrabRenderer(EntityRendererProvider.Context context) {
        super(context, new CrabModel<>(context.bakeLayer(PCModelLayers.CRAB)), 0.3F);
    }

    @Override
    protected Identifier getTextureLocation(CrabEntity entity) {
        return Identifier.fromNamespaceAndPath(Reference.MODID, CrabVariantCatalog.texturePath(entity.getCrabType()));
    }
}
