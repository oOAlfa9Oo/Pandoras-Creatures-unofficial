package andrews.pandoras_creatures.client.item;

import andrews.pandoras_creatures.client.model.PlantHatModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.Model;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public final class PlantHatClientItemExtensions implements IClientItemExtensions {
    public static final PlantHatClientItemExtensions INSTANCE = new PlantHatClientItemExtensions();
    private PlantHatModel model;

    private PlantHatClientItemExtensions() {
    }

    @Override
    public Model getHumanoidArmorModel(ItemStack stack, EquipmentClientInfo.LayerType layerType, Model original) {
        if (model == null) {
            model = new PlantHatModel(Minecraft.getInstance().getEntityModels().bakeLayer(PCModelLayers.PLANT_HAT));
        }
        return model;
    }
}
