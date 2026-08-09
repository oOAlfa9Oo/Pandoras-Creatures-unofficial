package andrews.pandoras_creatures.content.item;

import andrews.pandoras_creatures.lang.PCLanguageKeys;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class ItemPlantHat extends ArmorItem {
    private static final int ORIGINAL_DURABILITY = 30;

    public ItemPlantHat() {
        super(ArmorMaterials.LEATHER, Type.HELMET, new Properties()
                .stacksTo(1)
                .durability(ORIGINAL_DURABILITY));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable(PCLanguageKeys.PLANT_HAT_TOOLTIP));
        super.appendHoverText(stack, context, tooltip, flag);
    }

    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return Reference.id("textures/models/armor/plant_hat.png").toString();
    }
}

