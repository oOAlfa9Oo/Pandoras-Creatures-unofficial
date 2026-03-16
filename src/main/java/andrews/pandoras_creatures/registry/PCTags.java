package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.util.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class PCTags {
    public static class Items {
        public static final TagKey<Item> VANILLA_SHULKER_BOXES = ItemTags.create(
                ResourceLocation.fromNamespaceAndPath(Reference.MODID, "vanilla_shulker_boxes"));
        public static final TagKey<Item> END_TROLL_BOXES = ItemTags.create(
                ResourceLocation.fromNamespaceAndPath(Reference.MODID, "end_troll_boxes"));
    }
}
