package andrews.pandoras_creatures.client.item;

import andrews.pandoras_creatures.client.model.PlantHatModel;
import net.minecraft.client.model.HumanoidModel;
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
    @SuppressWarnings("unchecked")
    public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack,
                                                  EquipmentSlot slot, HumanoidModel<?> original) {
        if (model == null) {
            model = new PlantHatModel(PlantHatModel.createBodyLayer().bakeRoot());
        }
        model.prepareForRender(entity, original);
        return model;
    }
}
