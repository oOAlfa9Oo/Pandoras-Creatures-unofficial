package andrews.pandoras_creatures.content.item;

import andrews.pandoras_creatures.content.material.PCArmorMaterials;
import andrews.pandoras_creatures.lang.PCLanguageKeys;
import andrews.pandoras_creatures.registry.item.PCItemIds;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.List;

/**
 * 1.21.2+ resuelve la textura de armadura via el sistema Equippable/EquipmentModel (JSON en
 * assets/pandoras_creatures/equipment/plant_hat.json), no via override de Java -- getArmorTexture
 * ya no existe como punto de extension.
 */
public class ItemPlantHat extends ArmorItem {
    public ItemPlantHat() {
        super(PCArmorMaterials.PLANT_HAT, ArmorType.HELMET, new Properties().stacksTo(1).setId(PCItemIds.key(PCItemIds.PLANT_HAT)));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable(PCLanguageKeys.PLANT_HAT_TOOLTIP));
        super.appendHoverText(stack, context, tooltip, flag);
    }
}
