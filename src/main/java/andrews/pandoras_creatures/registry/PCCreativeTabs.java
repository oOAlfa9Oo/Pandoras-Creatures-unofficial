package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PCCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> PANDORAS_CREATURES_TAB =
            CREATIVE_TABS.register("pandoras_creatures", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + Reference.MODID))
                    .icon(() -> new ItemStack(PCItems.BUFFLON_SADDLE.get()))
                    .displayItems((parameters, output) -> {
                        // Items
                        output.accept(PCItems.CRAB_MEAT.get());
                        output.accept(PCItems.CRAB_MEAT_COOKED.get());
                        output.accept(PCItems.SEAHORSE.get());
                        output.accept(PCItems.SEAHORSE_COOKED.get());
                        output.accept(PCItems.ACIDIC_ARCHVINE_TONGUE.get());
                        output.accept(PCItems.HERB_BUNDLE.get());
                        output.accept(PCItems.BUFFLON_BEEF.get());
                        output.accept(PCItems.BUFFLON_BEEF_COOKED.get());
                        output.accept(PCItems.BUFFLON_HIDE.get());
                        output.accept(PCItems.BUFFLON_SADDLE.get());
                        output.accept(PCItems.BUFFLON_PLAYER_SEATS.get());
                        output.accept(PCItems.BUFFLON_SMALL_STORAGE.get());
                        output.accept(PCItems.BUFFLON_LARGE_STORAGE.get());
                        output.accept(PCItems.END_TROLL_SKIN.get());
                        output.accept(PCItems.ARACHNON_HAMMER.get());
                        output.accept(PCItems.CRAB_BUCKET.get());
                        output.accept(PCItems.SEAHORSE_BUCKET.get());
                        output.accept(PCItems.PLANT_HAT.get());

                        // Spawn Eggs
                        output.accept(PCItems.ARACHNON_SPAWN_EGG.get());
                        output.accept(PCItems.HELLHOUND_SPAWN_EGG.get());
                        output.accept(PCItems.CRAB_SPAWN_EGG.get());
                        output.accept(PCItems.SEAHORSE_SPAWN_EGG.get());
                        output.accept(PCItems.ACIDIC_ARCHVINE_SPAWN_EGG.get());
                        output.accept(PCItems.BUFFLON_SPAWN_EGG.get());
                        output.accept(PCItems.END_TROLL_SPAWN_EGG.get());

                        // Blocks
                        output.accept(PCBlocks.ARACHNON_CRYSTAL.get());
                        output.accept(PCBlocks.HORSETAIL.get());
                        output.accept(PCBlocks.DHANIA.get());
                        output.accept(PCBlocks.HILL_BLOOM.get());
                        output.accept(PCBlocks.PANDORIC_SHARD.get());

                        // End Troll Boxes
                        output.accept(PCBlocks.END_TROLL_BOX.get());
                        output.accept(PCBlocks.WHITE_END_TROLL_BOX.get());
                        output.accept(PCBlocks.ORANGE_END_TROLL_BOX.get());
                        output.accept(PCBlocks.MAGENTA_END_TROLL_BOX.get());
                        output.accept(PCBlocks.LIGHT_BLUE_END_TROLL_BOX.get());
                        output.accept(PCBlocks.YELLOW_END_TROLL_BOX.get());
                        output.accept(PCBlocks.LIME_END_TROLL_BOX.get());
                        output.accept(PCBlocks.PINK_END_TROLL_BOX.get());
                        output.accept(PCBlocks.GRAY_END_TROLL_BOX.get());
                        output.accept(PCBlocks.LIGHT_GRAY_END_TROLL_BOX.get());
                        output.accept(PCBlocks.CYAN_END_TROLL_BOX.get());
                        output.accept(PCBlocks.PURPLE_END_TROLL_BOX.get());
                        output.accept(PCBlocks.BLUE_END_TROLL_BOX.get());
                        output.accept(PCBlocks.BROWN_END_TROLL_BOX.get());
                        output.accept(PCBlocks.GREEN_END_TROLL_BOX.get());
                        output.accept(PCBlocks.RED_END_TROLL_BOX.get());
                        output.accept(PCBlocks.BLACK_END_TROLL_BOX.get());
                    })
                    .build());
}
