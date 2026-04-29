package andrews.pandoras_creatures.content.item;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.entities.bucket.BucketEntityDataKeys;
import andrews.pandoras_creatures.entities.seahorse.SeahorseVariantCatalog;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import net.minecraft.world.level.material.Fluids;

import java.util.List;

public class ItemSeahorseBucket extends ItemMobBucket {
    public ItemSeahorseBucket() {
        super(() -> PandorasCreaturesCommon.platform().registry().entityType(PCEntityIds.SEAHORSE), () -> Fluids.WATER, new Properties().stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        CompoundTag compoundtag = stack.getTag();
        if (compoundtag != null
                && compoundtag.contains(BucketEntityDataKeys.BUCKET_VARIANT_TAG, Tag.TAG_INT)
                && compoundtag.contains(BucketEntityDataKeys.BUCKET_SIZE_TAG, Tag.TAG_INT)) {
            int i = compoundtag.getInt(BucketEntityDataKeys.BUCKET_VARIANT_TAG);
            int size = compoundtag.getInt(BucketEntityDataKeys.BUCKET_SIZE_TAG);
            tooltip.add(Component.translatable(SeahorseVariantCatalog.variantTooltipKey(i))
                    .withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
            tooltip.add(Component.translatable(SeahorseVariantCatalog.sizeTooltipKey(size))
                    .withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
        }
    }
}
