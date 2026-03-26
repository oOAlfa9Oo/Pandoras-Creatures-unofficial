package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class PCTags {
    private PCTags() {
    }

    public static final class Items {
        public static final TagKey<Item> VANILLA_SHULKER_BOXES = TagKey.create(
                Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(Reference.MODID, "vanilla_shulker_boxes"));
        public static final TagKey<Item> END_TROLL_BOXES = TagKey.create(
                Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(Reference.MODID, "end_troll_boxes"));

        private Items() {
        }
    }
}
