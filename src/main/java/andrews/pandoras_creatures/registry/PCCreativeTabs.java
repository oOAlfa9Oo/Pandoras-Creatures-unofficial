package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.registry.creative.PCCreativeTabEntries;
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
                    .displayItems((parameters, output) -> PCCreativeTabEntries.populatePandorasCreaturesTab(output))
                    .build());
}
