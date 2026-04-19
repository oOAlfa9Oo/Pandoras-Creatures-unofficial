package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.registry.creative.PCCreativeTabEntries;
import andrews.pandoras_creatures.registry.creative.PCCreativeTabIds;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public final class PCFabricCreativeTabs {
    public static CreativeModeTab PANDORAS_CREATURES_TAB;

    private static boolean initialized;

    private PCFabricCreativeTabs() {
    }

    public static void register() {
        if (initialized) {
            return;
        }
        initialized = true;

        PANDORAS_CREATURES_TAB = Registry.register(
                BuiltInRegistries.CREATIVE_MODE_TAB,
                Identifier.fromNamespaceAndPath(Reference.MODID, PCCreativeTabIds.PANDORAS_CREATURES),
                CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                        .title(Component.translatable(PCCreativeTabIds.PANDORAS_CREATURES_TRANSLATION_KEY))
                        .icon(() -> new ItemStack(PCFabricItems.getItem(andrews.pandoras_creatures.registry.item.PCItemIds.BUFFLON_SADDLE)))
                        .displayItems((parameters, output) -> PCCreativeTabEntries.populatePandorasCreaturesTab(output::accept))
                        .build()
        );
    }
}
