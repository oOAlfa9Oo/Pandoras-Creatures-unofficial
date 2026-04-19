package andrews.pandoras_creatures.content.item;

import andrews.pandoras_creatures.content.material.PCArmorMaterials;
import andrews.pandoras_creatures.lang.PCLanguageKeys;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.List;
import java.util.function.Consumer;

public class ItemPlantHat extends Item {
    private static final int ORIGINAL_DURABILITY = 30;

    public ItemPlantHat() {
        this(new Properties());
    }

    public ItemPlantHat(Properties properties) {
        super(properties
                .stacksTo(1)
                .durability(ORIGINAL_DURABILITY)
                .humanoidArmor(PCArmorMaterials.PLANT_HAT, ArmorType.HELMET));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flag) {
        tooltip.accept(Component.translatable(PCLanguageKeys.PLANT_HAT_TOOLTIP));
        super.appendHoverText(stack, context, display, tooltip, flag);
    }
}
