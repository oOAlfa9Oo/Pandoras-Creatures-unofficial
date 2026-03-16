package andrews.pandoras_creatures.objects.items;

import andrews.pandoras_creatures.entities.SeahorseEntity;
import andrews.pandoras_creatures.registry.PCEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

public class ItemSeahorseBucket extends ItemMobBucket {
    public ItemSeahorseBucket() {
        super(() -> PCEntities.SEAHORSE.get(), () -> Fluids.WATER, new Properties().stacksTo(1));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        CustomData customData = stack.get(DataComponents.BUCKET_ENTITY_DATA);
        if (customData != null) {
            CompoundTag compoundtag = customData.copyTag();
            if (compoundtag.contains("BucketVariantTag", Tag.TAG_INT) && compoundtag.contains("BucketSizeTag", Tag.TAG_INT)) {
                int i = compoundtag.getInt("BucketVariantTag");
                int size = compoundtag.getInt("BucketSizeTag");
                tooltip.add(Component.translatable(SeahorseEntity.getNameById(i))
                        .withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
                tooltip.add(Component.translatable(SeahorseEntity.getSizeById(size))
                        .withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
            }
        }
    }
}
