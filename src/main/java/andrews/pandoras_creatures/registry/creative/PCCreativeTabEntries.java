package andrews.pandoras_creatures.registry.creative;

import andrews.pandoras_creatures.registry.PCBlocks;
import andrews.pandoras_creatures.registry.PCItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;

import java.util.function.Supplier;

public final class PCCreativeTabEntries {
    private PCCreativeTabEntries() {
    }

    public static void populatePandorasCreaturesTab(CreativeModeTab.Output output) {
        acceptAll(output,
                PCItems.CRAB_MEAT,
                PCItems.CRAB_MEAT_COOKED,
                PCItems.SEAHORSE,
                PCItems.SEAHORSE_COOKED,
                PCItems.ACIDIC_ARCHVINE_TONGUE,
                PCItems.HERB_BUNDLE,
                PCItems.BUFFLON_BEEF,
                PCItems.BUFFLON_BEEF_COOKED,
                PCItems.BUFFLON_HIDE,
                PCItems.BUFFLON_SADDLE,
                PCItems.BUFFLON_PLAYER_SEATS,
                PCItems.BUFFLON_SMALL_STORAGE,
                PCItems.BUFFLON_LARGE_STORAGE,
                PCItems.END_TROLL_SKIN,
                PCItems.ARACHNON_HAMMER,
                PCItems.CRAB_BUCKET,
                PCItems.SEAHORSE_BUCKET,
                PCItems.PLANT_HAT
        );

        PCItems.getSpawnEggs().forEach(holder -> output.accept(holder.get()));

        acceptAll(output,
                PCBlocks.ARACHNON_CRYSTAL,
                PCBlocks.HORSETAIL,
                PCBlocks.DHANIA,
                PCBlocks.HILL_BLOOM,
                PCBlocks.PANDORIC_SHARD
        );

        PCBlocks.acceptEndTrollBoxes(output);
    }

    @SafeVarargs
    private static void acceptAll(CreativeModeTab.Output output, Supplier<? extends ItemLike>... entries) {
        for (Supplier<? extends ItemLike> entry : entries) {
            output.accept(entry.get());
        }
    }
}
