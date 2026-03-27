package andrews.pandoras_creatures.client.bootstrap;

import andrews.pandoras_creatures.client.model.*;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.client.model.tile.EndTrollBoxModel;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public final class PCClientModelLayerRegistry {
    private PCClientModelLayerRegistry() {
    }

    public static void registerAll(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PCModelLayers.CRAB, CrabModel::createBodyLayer);
        event.registerLayerDefinition(PCModelLayers.SEAHORSE, SeahorseModel::createBodyLayer);
        event.registerLayerDefinition(PCModelLayers.HELLHOUND, HellhoundModel::createBodyLayer);
        event.registerLayerDefinition(PCModelLayers.ARACHNON, ArachnonModel::createBodyLayer);
        event.registerLayerDefinition(PCModelLayers.ACIDIC_ARCHVINE, AcidicArchvineModel::createBodyLayer);
        event.registerLayerDefinition(PCModelLayers.BUFFLON, BufflonModel::createBodyLayer);
        event.registerLayerDefinition(PCModelLayers.END_TROLL, EndTrollModel::createBodyLayer);
        event.registerLayerDefinition(PCModelLayers.END_TROLL_BULLET, EndTrollBulletModel::createBodyLayer);
        event.registerLayerDefinition(PCModelLayers.END_TROLL_BOX, EndTrollBoxModel::createBodyLayer);
    }
}
