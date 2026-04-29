package andrews.pandoras_creatures.forge.registry;

import andrews.pandoras_creatures.registry.creative.PCCreativeTabEntries;
import andrews.pandoras_creatures.registry.creative.PCCreativeTabIds;
import andrews.pandoras_creatures.registry.item.PCItemIds;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegisterEvent;

public final class PCForgeCreativeTabs {
    private static boolean registered;

    private PCForgeCreativeTabs() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(PCForgeCreativeTabs::registerCreativeTabs);
    }

    private static void registerCreativeTabs(RegisterEvent event) {
        if (registered || !event.getRegistryKey().equals(Registries.CREATIVE_MODE_TAB)) {
            return;
        }

        registered = true;
        event.register(
                Registries.CREATIVE_MODE_TAB,
                helper -> helper.register(
                        new ResourceLocation(Reference.MODID, PCCreativeTabIds.PANDORAS_CREATURES),
                        CreativeModeTab.builder()
                                .title(Component.translatable(PCCreativeTabIds.PANDORAS_CREATURES_TRANSLATION_KEY))
                                .icon(() -> new ItemStack(PCForgeItems.getItem(PCItemIds.BUFFLON_SADDLE)))
                                .displayItems((parameters, output) -> PCCreativeTabEntries.populatePandorasCreaturesTab(output))
                                .build()
                )
        );
    }
}

