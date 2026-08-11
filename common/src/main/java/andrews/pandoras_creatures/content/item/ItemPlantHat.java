package andrews.pandoras_creatures.content.item;

import andrews.pandoras_creatures.content.material.PCArmorMaterials;
import andrews.pandoras_creatures.lang.PCLanguageKeys;
import andrews.pandoras_creatures.registry.item.PCItemIds;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.function.Consumer;

/**
 * 1.21.2+ resuelve la textura de armadura via el sistema Equippable/EquipmentModel (JSON en
 * assets/pandoras_creatures/equipment/plant_hat.json), no via override de Java -- getArmorTexture
 * ya no existe como punto de extension. 1.21.5 va mas lejos y elimina ArmorItem del todo: ahora
 * es un Item comun con Properties#humanoidArmor(ArmorMaterial, ArmorType) aplicando el componente
 * Equippable.
 */
public class ItemPlantHat extends Item {
    public ItemPlantHat() {
        super(new Properties().humanoidArmor(PCArmorMaterials.PLANT_HAT, ArmorType.HELMET).stacksTo(1).setId(PCItemIds.key(PCItemIds.PLANT_HAT)));
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        tooltipAdder.accept(Component.translatable(PCLanguageKeys.PLANT_HAT_TOOLTIP));
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
    }
}
