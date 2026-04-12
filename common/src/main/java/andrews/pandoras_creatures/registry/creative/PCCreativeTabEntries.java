package andrews.pandoras_creatures.registry.creative;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.registry.block.PCBlockIds;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxBootstrap;
import andrews.pandoras_creatures.registry.item.PCItemIds;
import andrews.pandoras_creatures.registry.item.PCSpawnEggPalette;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;

public final class PCCreativeTabEntries {
    private PCCreativeTabEntries() {
    }

    public static void populatePandorasCreaturesTab(CreativeModeTab.Output output) {
        acceptItem(output, PCItemIds.CRAB_MEAT);
        acceptItem(output, PCItemIds.CRAB_MEAT_COOKED);
        acceptItem(output, PCItemIds.SEAHORSE);
        acceptItem(output, PCItemIds.SEAHORSE_COOKED);
        acceptItem(output, PCItemIds.ACIDIC_ARCHVINE_TONGUE);
        acceptItem(output, PCItemIds.HERB_BUNDLE);
        acceptItem(output, PCItemIds.BUFFLON_BEEF);
        acceptItem(output, PCItemIds.BUFFLON_BEEF_COOKED);
        acceptItem(output, PCItemIds.BUFFLON_HIDE);
        acceptItem(output, PCItemIds.BUFFLON_SADDLE);
        acceptItem(output, PCItemIds.BUFFLON_PLAYER_SEATS);
        acceptItem(output, PCItemIds.BUFFLON_SMALL_STORAGE);
        acceptItem(output, PCItemIds.BUFFLON_LARGE_STORAGE);
        acceptItem(output, PCItemIds.END_TROLL_SKIN);
        acceptItem(output, PCItemIds.ARACHNON_HAMMER);
        acceptItem(output, PCItemIds.CRAB_BUCKET);
        acceptItem(output, PCItemIds.SEAHORSE_BUCKET);
        acceptItem(output, PCItemIds.PLANT_HAT);

        for (PCSpawnEggPalette palette : PCSpawnEggPalette.values()) {
            acceptItem(output, palette.itemName());
        }

        acceptBlock(output, PCBlockIds.ARACHNON_CRYSTAL);
        acceptBlock(output, PCBlockIds.HORSETAIL);
        acceptBlock(output, PCBlockIds.DHANIA);
        acceptBlock(output, PCBlockIds.HILL_BLOOM);
        acceptBlock(output, PCBlockIds.PANDORIC_SHARD);

        for (String blockId : PCEndTrollBoxBootstrap.blockIds()) {
            acceptBlock(output, blockId);
        }
    }

    private static void acceptItem(CreativeModeTab.Output output, String itemId) {
        Identifier id = PandorasCreaturesCommon.platform().registry().id(itemId);
        if (BuiltInRegistries.ITEM.containsKey(id)) {
            ItemLike item = PandorasCreaturesCommon.platform().registry().item(itemId);
            output.accept(item);
        }
    }

    private static void acceptBlock(CreativeModeTab.Output output, String blockId) {
        Identifier id = PandorasCreaturesCommon.platform().registry().id(blockId);
        if (BuiltInRegistries.BLOCK.containsKey(id)) {
            ItemLike block = PandorasCreaturesCommon.platform().registry().block(blockId);
            output.accept(block);
        }
    }
}
