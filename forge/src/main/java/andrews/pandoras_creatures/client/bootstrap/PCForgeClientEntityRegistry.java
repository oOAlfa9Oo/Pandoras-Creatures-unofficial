package andrews.pandoras_creatures.forge.client.bootstrap;

import andrews.pandoras_creatures.client.model.AcidicArchvineModel;
import andrews.pandoras_creatures.client.model.ArachnonModel;
import andrews.pandoras_creatures.client.model.BufflonModel;
import andrews.pandoras_creatures.client.model.CrabModel;
import andrews.pandoras_creatures.client.model.EndTrollBulletModel;
import andrews.pandoras_creatures.client.model.EndTrollModel;
import andrews.pandoras_creatures.client.model.HellhoundModel;
import andrews.pandoras_creatures.client.model.PlantHatModel;
import andrews.pandoras_creatures.client.model.SeahorseModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.client.model.tile.EndTrollBoxModel;
import andrews.pandoras_creatures.client.renderer.AcidicArchvineRenderer;
import andrews.pandoras_creatures.client.renderer.ArachnonRenderer;
import andrews.pandoras_creatures.client.renderer.BufflonRenderer;
import andrews.pandoras_creatures.client.renderer.CrabRenderer;
import andrews.pandoras_creatures.client.renderer.EndTrollBulletDamageRenderer;
import andrews.pandoras_creatures.client.renderer.EndTrollBulletPoisonRenderer;
import andrews.pandoras_creatures.client.renderer.EndTrollBulletWitherRenderer;
import andrews.pandoras_creatures.client.renderer.EndTrollRenderer;
import andrews.pandoras_creatures.client.renderer.HellhoundRenderer;
import andrews.pandoras_creatures.client.renderer.SeahorseRenderer;
import andrews.pandoras_creatures.client.renderer.tile.EndTrollBoxBlockEntityRenderer;
import andrews.pandoras_creatures.forge.registry.PCForgeBlockEntities;
import andrews.pandoras_creatures.forge.registry.PCForgeEntities;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public final class PCForgeClientEntityRegistry {
    private PCForgeClientEntityRegistry() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(PCForgeClientEntityRegistry::registerLayerDefinitions);
        modEventBus.addListener(PCForgeClientEntityRegistry::registerRenderers);
    }

    private static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PCModelLayers.ACIDIC_ARCHVINE, AcidicArchvineModel::createBodyLayer);
        event.registerLayerDefinition(PCModelLayers.ARACHNON, ArachnonModel::createBodyLayer);
        event.registerLayerDefinition(PCModelLayers.BUFFLON, BufflonModel::createBodyLayer);
        event.registerLayerDefinition(PCModelLayers.CRAB, CrabModel::createBodyLayer);
        event.registerLayerDefinition(PCModelLayers.END_TROLL, EndTrollModel::createBodyLayer);
        event.registerLayerDefinition(PCModelLayers.END_TROLL_BULLET, EndTrollBulletModel::createBodyLayer);
        event.registerLayerDefinition(PCModelLayers.END_TROLL_BOX, EndTrollBoxModel::createBodyLayer);
        event.registerLayerDefinition(PCModelLayers.SEAHORSE, SeahorseModel::createBodyLayer);
        event.registerLayerDefinition(PCModelLayers.HELLHOUND, HellhoundModel::createBodyLayer);
        event.registerLayerDefinition(PCModelLayers.PLANT_HAT, PlantHatModel::createBodyLayer);
    }

    private static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(PCForgeEntities.acidicArchvine(), AcidicArchvineRenderer::new);
        event.registerEntityRenderer(PCForgeEntities.arachnon(), ArachnonRenderer::new);
        event.registerEntityRenderer(PCForgeEntities.bufflon(), BufflonRenderer::new);
        event.registerEntityRenderer(PCForgeEntities.crab(), CrabRenderer::new);
        event.registerEntityRenderer(PCForgeEntities.endTroll(), EndTrollRenderer::new);
        event.registerEntityRenderer(PCForgeEntities.endTrollBulletDamage(), EndTrollBulletDamageRenderer::new);
        event.registerEntityRenderer(PCForgeEntities.endTrollBulletPoison(), EndTrollBulletPoisonRenderer::new);
        event.registerEntityRenderer(PCForgeEntities.endTrollBulletWither(), EndTrollBulletWitherRenderer::new);
        event.registerEntityRenderer(PCForgeEntities.seahorse(), SeahorseRenderer::new);
        event.registerEntityRenderer(PCForgeEntities.hellhound(), HellhoundRenderer::new);
        event.registerBlockEntityRenderer(PCForgeBlockEntities.endTrollBox(), EndTrollBoxBlockEntityRenderer::new);
    }
}
