package andrews.pandoras_creatures.client.item;

import andrews.pandoras_creatures.client.model.PlantHatModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.Model;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

/**
 * NeoForge 21.3.97 aun no llama a {@code setupModelAnimations} desde HumanoidArmorLayer
 * (ver su propio TODO en IClientItemExtensions), asi que la sincronizacion de animacion
 * por frame de PlantHatModel (rotacion del hat_base con la cabeza, offset en armor stand)
 * no tiene hook disponible en este loader/version todavia; solo se puede devolver el modelo.
 */
public final class PlantHatClientItemExtensions implements IClientItemExtensions {
    public static final PlantHatClientItemExtensions INSTANCE = new PlantHatClientItemExtensions();
    private PlantHatModel model;

    private PlantHatClientItemExtensions() {
    }

    @Override
    public Model getHumanoidArmorModel(ItemStack itemStack, EquipmentClientInfo.LayerType layerType, Model original) {
        if (model == null) {
            model = new PlantHatModel(Minecraft.getInstance().getEntityModels().bakeLayer(PCModelLayers.PLANT_HAT));
        }
        return model;
    }
}
