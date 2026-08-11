package andrews.pandoras_creatures.content.item;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.entities.bucket.BucketEntityDataKeys;
import andrews.pandoras_creatures.entities.crab.CrabVariantCatalog;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.registry.item.PCItemIds;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.material.Fluids;

import java.util.function.Consumer;

public class ItemCrabBucket extends ItemMobBucket {
    public ItemCrabBucket() {
        super(() -> PandorasCreaturesCommon.platform().registry().entityType(PCEntityIds.CRAB), () -> Fluids.WATER,
                new Properties().stacksTo(1).setId(PCItemIds.key(PCItemIds.CRAB_BUCKET)));
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        CustomData customData = stack.get(DataComponents.BUCKET_ENTITY_DATA);
        if (customData != null) {
            CompoundTag compoundtag = customData.copyTag();
            if (compoundtag.contains(BucketEntityDataKeys.BUCKET_VARIANT_TAG)) {
                int i = compoundtag.getIntOr(BucketEntityDataKeys.BUCKET_VARIANT_TAG, 0);
                tooltipAdder.accept(Component.translatable(CrabVariantCatalog.tooltipKey(i))
                        .withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
            }
        }
    }
}
