package andrews.pandoras_creatures.registry.bootstrap;

import andrews.pandoras_creatures.registry.PCBlocks;
import andrews.pandoras_creatures.registry.PCCreativeTabs;
import andrews.pandoras_creatures.registry.PCItems;
import andrews.pandoras_creatures.registry.PCSounds;
import net.neoforged.bus.api.IEventBus;

public final class PCContentRegisterGroup {
    private PCContentRegisterGroup() {
    }

    public static void register(IEventBus modEventBus) {
        PCItems.ITEMS.register(modEventBus);
        PCBlocks.BLOCKS.register(modEventBus);
        PCSounds.SOUNDS.register(modEventBus);
        PCCreativeTabs.CREATIVE_TABS.register(modEventBus);
    }
}
