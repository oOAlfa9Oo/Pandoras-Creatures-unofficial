package andrews.pandoras_creatures.forge.client.item;

import andrews.pandoras_creatures.client.model.PlantHatModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public final class ForgePlantHatClientItemExtensions implements IClientItemExtensions {
    public static final ForgePlantHatClientItemExtensions INSTANCE = new ForgePlantHatClientItemExtensions();
    private PlantHatModel model;

    private ForgePlantHatClientItemExtensions() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack,
                                                  EquipmentSlot slot, HumanoidModel<?> original) {
        if (model == null) {
            model = new PlantHatModel(Minecraft.getInstance().getEntityModels().bakeLayer(PCModelLayers.PLANT_HAT));
        }
        model.prepareForRender(entity, original);
        return model;
    }
}
