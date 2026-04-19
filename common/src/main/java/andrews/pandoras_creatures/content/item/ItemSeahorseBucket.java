package andrews.pandoras_creatures.content.item;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.entities.bucket.BucketEntityDataKeys;
import andrews.pandoras_creatures.entities.seahorse.SeahorseVariantCatalog;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.material.Fluids;

import java.util.function.Consumer;

public class ItemSeahorseBucket extends ItemMobBucket {
    public ItemSeahorseBucket() {
        this(new Properties());
    }

    public ItemSeahorseBucket(Properties properties) {
        super(() -> PandorasCreaturesCommon.platform().registry().entityType(PCEntityIds.SEAHORSE), () -> Fluids.WATER, properties.stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltip, TooltipFlag flag) {
        CustomData customData = stack.get(DataComponents.BUCKET_ENTITY_DATA);
        if (customData != null) {
            CompoundTag compoundtag = customData.copyTag();
            if (compoundtag.contains(BucketEntityDataKeys.BUCKET_VARIANT_TAG)
                    && compoundtag.contains(BucketEntityDataKeys.BUCKET_SIZE_TAG)) {
                int i = compoundtag.getInt(BucketEntityDataKeys.BUCKET_VARIANT_TAG).orElse(SeahorseVariantCatalog.DEFAULT_TYPE);
                int size = compoundtag.getInt(BucketEntityDataKeys.BUCKET_SIZE_TAG).orElse(SeahorseVariantCatalog.DEFAULT_SIZE);
                tooltip.accept(Component.translatable(SeahorseVariantCatalog.variantTooltipKey(i))
                        .withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
                tooltip.accept(Component.translatable(SeahorseVariantCatalog.sizeTooltipKey(size))
                        .withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
            }
        }
    }
}
