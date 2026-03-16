package andrews.pandoras_creatures.client;

import andrews.pandoras_creatures.client.model.*;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.client.model.tile.EndTrollBoxModel;
import andrews.pandoras_creatures.client.renderer.*;
import andrews.pandoras_creatures.client.renderer.tile.EndTrollBoxBlockEntityRenderer;
import andrews.pandoras_creatures.client.renderer.tile.PCItemRenderer;
import andrews.pandoras_creatures.client.screen.BufflonScreen;
import andrews.pandoras_creatures.client.screen.EndTrollBoxScreen;
import andrews.pandoras_creatures.registry.PCBlockEntities;
import andrews.pandoras_creatures.registry.PCBlocks;
import andrews.pandoras_creatures.registry.PCEntities;
import andrews.pandoras_creatures.registry.PCItems;
import andrews.pandoras_creatures.registry.PCMenuTypes;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

/**
 * Client-side setup for Pandoras Creatures mod.
 * Handles registration of entity renderers, model layers, and screens.
 */
@EventBusSubscriber(modid = Reference.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class PCClientSetup {

    /**
     * Register entity renderers
     */
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // Living Entity Renderers
        event.registerEntityRenderer(PCEntities.CRAB.get(), CrabRenderer::new);
        event.registerEntityRenderer(PCEntities.SEAHORSE.get(), SeahorseRenderer::new);
        event.registerEntityRenderer(PCEntities.HELLHOUND.get(), HellhoundRenderer::new);
        event.registerEntityRenderer(PCEntities.ARACHNON.get(), ArachnonRenderer::new);
        event.registerEntityRenderer(PCEntities.ACIDIC_ARCHVINE.get(), AcidicArchvineRenderer::new);
        event.registerEntityRenderer(PCEntities.BUFFLON.get(), BufflonRenderer::new);
        event.registerEntityRenderer(PCEntities.END_TROLL.get(), EndTrollRenderer::new);

        // Projectile Renderers
        event.registerEntityRenderer(PCEntities.END_TROLL_BULLET_DAMAGE.get(), EndTrollBulletDamageRenderer::new);
        event.registerEntityRenderer(PCEntities.END_TROLL_BULLET_POISON.get(), EndTrollBulletPoisonRenderer::new);
        event.registerEntityRenderer(PCEntities.END_TROLL_BULLET_WITHER.get(), EndTrollBulletWitherRenderer::new);

        // Block Entity Renderers
        event.registerBlockEntityRenderer(PCBlockEntities.END_TROLL_BOX.get(), EndTrollBoxBlockEntityRenderer::new);
        // TODO: Add Pandoric Shard renderer when implemented
    }

    /**
     * Register model layer definitions
     */
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        // Entity model layers
        event.registerLayerDefinition(PCModelLayers.CRAB, CrabModel::createBodyLayer);
        event.registerLayerDefinition(PCModelLayers.SEAHORSE, SeahorseModel::createBodyLayer);
        event.registerLayerDefinition(PCModelLayers.HELLHOUND, HellhoundModel::createBodyLayer);
        event.registerLayerDefinition(PCModelLayers.ARACHNON, ArachnonModel::createBodyLayer);
        event.registerLayerDefinition(PCModelLayers.ACIDIC_ARCHVINE, AcidicArchvineModel::createBodyLayer);
        event.registerLayerDefinition(PCModelLayers.BUFFLON, BufflonModel::createBodyLayer);
        event.registerLayerDefinition(PCModelLayers.END_TROLL, EndTrollModel::createBodyLayer);
        event.registerLayerDefinition(PCModelLayers.END_TROLL_BULLET, EndTrollBulletModel::createBodyLayer);

        // Block entity model layers
        event.registerLayerDefinition(PCItemRenderer.END_TROLL_BOX_LAYER, EndTrollBoxModel::createBodyLayer);
        // TODO: Add Pandoric Shard when implemented
        // event.registerLayerDefinition(PCModelLayers.PANDORIC_SHARD, PandoricShardModel::createBodyLayer);
    }

    /**
     * Register menu screens
     */
    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(PCMenuTypes.END_TROLL_BOX.get(), EndTrollBoxScreen::new);
        event.register(PCMenuTypes.BUFFLON.get(), BufflonScreen::new);
    }

    /**
     * Register item colors for spawn eggs.
     * SpawnEggItem.getColor() returns RGB (24-bit) without alpha channel.
     * We must OR with 0xFF000000 to set alpha=255, otherwise items appear fully transparent.
     */
    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        PCItems.SPAWN_EGGS.forEach(holder -> {
            event.register(
                    (stack, tintIndex) -> 0xFF000000 | ((SpawnEggItem) stack.getItem()).getColor(tintIndex),
                    holder.get()
            );
        });
    }

    /**
     * Register custom BEWLR for EndTrollBox items.
     * Replaces the deprecated Item.initializeClient() approach.
     */
    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        Item[] endTrollBoxItems = PCBlocks.END_TROLL_BOX_BLOCKS.stream()
                .map(holder -> holder.get().asItem())
                .toArray(Item[]::new);
        event.registerItem(PCClientItemExtensions.INSTANCE, endTrollBoxItems);

        // Custom 3D armor model for the Plant Hat
        event.registerItem(new IClientItemExtensions() {
            private PlantHatModel model;

            @Override
            @SuppressWarnings("unchecked")
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack,
                                                          EquipmentSlot slot, HumanoidModel<?> original) {
                if (model == null) {
                    model = new PlantHatModel(PlantHatModel.createBodyLayer().bakeRoot());
                }
                model.prepareForRender(entity, original);
                return model;
            }
        }, PCItems.PLANT_HAT.get());
    }
}
