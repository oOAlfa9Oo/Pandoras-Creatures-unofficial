package andrews.pandoras_creatures.client.item;

import andrews.pandoras_creatures.client.model.PlantHatModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.registry.PCFabricItems;
import andrews.pandoras_creatures.registry.item.PCItemIds;
import andrews.pandoras_creatures.util.Reference;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;

public final class FabricPlantHatArmorRenderer {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Reference.MODID, "textures/models/armor/plant_hat.png");
    private static PlantHatModel model;
    private static boolean initialized;

    private FabricPlantHatArmorRenderer() {
    }

    public static void register() {
        if (initialized || !PCFabricItems.hasItem(PCItemIds.PLANT_HAT)) {
            return;
        }
        initialized = true;

        ArmorRenderer.register((matrices, vertexConsumers, stack, entity, slot, light, contextModel) -> {
            if (slot != EquipmentSlot.HEAD) {
                return;
            }

            PlantHatModel armorModel = getModel();
            armorModel.prepareForRender(entity, contextModel);
            ArmorRenderer.submitTransformCopyingModel(
                    contextModel,
                    entity,
                    armorModel,
                    entity,
                    true,
                    vertexConsumers,
                    matrices,
                    RenderTypes.armorCutoutNoCull(TEXTURE),
                    light,
                    OverlayTexture.NO_OVERLAY,
                    -1,
                    null
            );
        }, PCFabricItems.getItem(PCItemIds.PLANT_HAT));
    }

    private static PlantHatModel getModel() {
        if (model == null) {
            model = new PlantHatModel(Minecraft.getInstance().getEntityModels().bakeLayer(PCModelLayers.PLANT_HAT));
        }
        return model;
    }
}
