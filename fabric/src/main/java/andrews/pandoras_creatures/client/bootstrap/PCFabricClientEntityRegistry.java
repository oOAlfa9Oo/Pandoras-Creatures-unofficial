package andrews.pandoras_creatures.client.bootstrap;

import andrews.pandoras_creatures.client.model.AcidicArchvineModel;
import andrews.pandoras_creatures.client.model.ArachnonModel;
import andrews.pandoras_creatures.client.model.BufflonModel;
import andrews.pandoras_creatures.client.model.CrabModel;
import andrews.pandoras_creatures.client.model.EndTrollModel;
import andrews.pandoras_creatures.client.model.EndTrollBulletModel;
import andrews.pandoras_creatures.client.model.HellhoundModel;
import andrews.pandoras_creatures.client.model.PlantHatModel;
import andrews.pandoras_creatures.client.model.SeahorseModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.client.renderer.AcidicArchvineRenderer;
import andrews.pandoras_creatures.client.renderer.ArachnonRenderer;
import andrews.pandoras_creatures.client.renderer.BufflonRenderer;
import andrews.pandoras_creatures.client.renderer.CrabRenderer;
import andrews.pandoras_creatures.client.renderer.EndTrollRenderer;
import andrews.pandoras_creatures.client.renderer.EndTrollBulletDamageRenderer;
import andrews.pandoras_creatures.client.renderer.EndTrollBulletPoisonRenderer;
import andrews.pandoras_creatures.client.renderer.EndTrollBulletWitherRenderer;
import andrews.pandoras_creatures.client.renderer.HellhoundRenderer;
import andrews.pandoras_creatures.client.renderer.SeahorseRenderer;
import andrews.pandoras_creatures.registry.PCFabricEntities;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public final class PCFabricClientEntityRegistry {
    private static boolean initialized;

    private PCFabricClientEntityRegistry() {
    }

    public static void registerAll() {
        if (initialized) {
            return;
        }
        initialized = true;

        ModelLayerRegistry.registerModelLayer(PCModelLayers.ACIDIC_ARCHVINE, AcidicArchvineModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(PCModelLayers.ARACHNON, ArachnonModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(PCModelLayers.BUFFLON, BufflonModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(PCModelLayers.CRAB, CrabModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(PCModelLayers.END_TROLL, EndTrollModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(PCModelLayers.END_TROLL_BULLET, EndTrollBulletModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(PCModelLayers.HELLHOUND, HellhoundModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(PCModelLayers.PLANT_HAT, PlantHatModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(PCModelLayers.SEAHORSE, SeahorseModel::createBodyLayer);

        EntityRendererRegistry.register(PCFabricEntities.ACIDIC_ARCHVINE, AcidicArchvineRenderer::new);
        EntityRendererRegistry.register(PCFabricEntities.ARACHNON, ArachnonRenderer::new);
        EntityRendererRegistry.register(PCFabricEntities.BUFFLON, BufflonRenderer::new);
        EntityRendererRegistry.register(PCFabricEntities.CRAB, CrabRenderer::new);
        EntityRendererRegistry.register(PCFabricEntities.END_TROLL, EndTrollRenderer::new);
        EntityRendererRegistry.register(PCFabricEntities.END_TROLL_BULLET_DAMAGE, EndTrollBulletDamageRenderer::new);
        EntityRendererRegistry.register(PCFabricEntities.END_TROLL_BULLET_POISON, EndTrollBulletPoisonRenderer::new);
        EntityRendererRegistry.register(PCFabricEntities.END_TROLL_BULLET_WITHER, EndTrollBulletWitherRenderer::new);
        EntityRendererRegistry.register(PCFabricEntities.HELLHOUND, HellhoundRenderer::new);
        EntityRendererRegistry.register(PCFabricEntities.SEAHORSE, SeahorseRenderer::new);
    }
}
