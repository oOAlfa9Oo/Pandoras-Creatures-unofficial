package andrews.pandoras_creatures.registry.bootstrap;

import andrews.pandoras_creatures.registry.PCBlockEntities;
import andrews.pandoras_creatures.registry.PCBlocks;
import andrews.pandoras_creatures.registry.PCCreativeTabs;
import andrews.pandoras_creatures.registry.PCEntities;
import andrews.pandoras_creatures.registry.PCItems;
import andrews.pandoras_creatures.registry.PCMenuTypes;
import andrews.pandoras_creatures.registry.PCRecipeSerializers;
import andrews.pandoras_creatures.registry.PCSounds;
import andrews.pandoras_creatures.registry.PCStructures;
import net.neoforged.bus.api.IEventBus;

public final class PCDeferredRegisters {
    private PCDeferredRegisters() {
    }

    public static void registerAll(IEventBus modEventBus) {
        PCItems.ITEMS.register(modEventBus);
        PCBlocks.BLOCKS.register(modEventBus);
        PCSounds.SOUNDS.register(modEventBus);
        PCBlockEntities.BLOCK_ENTITY_TYPES.register(modEventBus);
        PCMenuTypes.MENU_TYPES.register(modEventBus);
        PCEntities.ENTITY_TYPES.register(modEventBus);
        PCCreativeTabs.CREATIVE_TABS.register(modEventBus);
        PCRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
        PCStructures.STRUCTURE_TYPES.register(modEventBus);
        PCStructures.STRUCTURE_PIECE_TYPES.register(modEventBus);
    }
}
