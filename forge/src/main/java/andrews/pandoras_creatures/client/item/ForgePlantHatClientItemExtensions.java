package andrews.pandoras_creatures.forge.client.item;

import andrews.pandoras_creatures.client.model.PlantHatModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public final class ForgePlantHatClientItemExtensions implements IClientItemExtensions {
    public static final ForgePlantHatClientItemExtensions INSTANCE = new ForgePlantHatClientItemExtensions();
    private PlantHatModel model;

    private ForgePlantHatClientItemExtensions() {
    }

    @Override
    public Model getGenericArmorModel(HumanoidRenderState state, ItemStack stack,
                                      net.minecraft.world.entity.EquipmentSlot slot, HumanoidModel<?> original) {
        if (model == null) {
            model = new PlantHatModel(Minecraft.getInstance().getEntityModels().bakeLayer(PCModelLayers.PLANT_HAT));
        }
        return model;
    }
}
