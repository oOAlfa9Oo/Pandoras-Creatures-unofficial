package andrews.pandoras_creatures.objects.items;

import andrews.pandoras_creatures.objects.util.PCArmorMaterials;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

public class ItemPlantHat extends ArmorItem {
    private static final int ORIGINAL_DURABILITY = 30;

    public ItemPlantHat() {
        super(PCArmorMaterials.PLANT_HAT, Type.HELMET, new Properties()
                .stacksTo(1)
                .durability(ORIGINAL_DURABILITY));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.pandoras_creatures.plant_hat.tooltip"));
        super.appendHoverText(stack, context, tooltip, flag);
    }

    @Override
    public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MODID, "textures/models/armor/plant_hat.png");
    }
}
