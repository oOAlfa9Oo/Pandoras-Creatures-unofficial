package andrews.pandoras_creatures.client.bootstrap;

import andrews.pandoras_creatures.client.renderer.*;
import andrews.pandoras_creatures.client.renderer.tile.EndTrollBoxBlockEntityRenderer;
import andrews.pandoras_creatures.registry.PCBlockEntities;
import andrews.pandoras_creatures.registry.PCEntities;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public final class PCClientRendererRegistry {
    private PCClientRendererRegistry() {
    }

    public static void registerAll(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(PCEntities.CRAB.get(), CrabRenderer::new);
        event.registerEntityRenderer(PCEntities.SEAHORSE.get(), SeahorseRenderer::new);
        event.registerEntityRenderer(PCEntities.HELLHOUND.get(), HellhoundRenderer::new);
        event.registerEntityRenderer(PCEntities.ARACHNON.get(), ArachnonRenderer::new);
        event.registerEntityRenderer(PCEntities.ACIDIC_ARCHVINE.get(), AcidicArchvineRenderer::new);
        event.registerEntityRenderer(PCEntities.BUFFLON.get(), BufflonRenderer::new);
        event.registerEntityRenderer(PCEntities.END_TROLL.get(), EndTrollRenderer::new);

        event.registerEntityRenderer(PCEntities.END_TROLL_BULLET_DAMAGE.get(), EndTrollBulletDamageRenderer::new);
        event.registerEntityRenderer(PCEntities.END_TROLL_BULLET_POISON.get(), EndTrollBulletPoisonRenderer::new);
        event.registerEntityRenderer(PCEntities.END_TROLL_BULLET_WITHER.get(), EndTrollBulletWitherRenderer::new);

        event.registerBlockEntityRenderer(PCBlockEntities.END_TROLL_BOX.get(), EndTrollBoxBlockEntityRenderer::new);
    }
}
