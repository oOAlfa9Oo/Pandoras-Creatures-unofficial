package andrews.pandoras_creatures.forge.client.bootstrap;

import andrews.pandoras_creatures.client.item.PCItemColorRules;
import andrews.pandoras_creatures.forge.registry.PCForgeItems;
import andrews.pandoras_creatures.registry.item.PCSpawnEggPalette;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public final class PCForgeClientItemColorRegistry {
    private PCForgeClientItemColorRegistry() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(PCForgeClientItemColorRegistry::registerItemColors);
    }

    private static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        for (PCSpawnEggPalette palette : java.util.List.of(
                PCSpawnEggPalette.ACIDIC_ARCHVINE,
                PCSpawnEggPalette.ARACHNON,
                PCSpawnEggPalette.BUFFLON,
                PCSpawnEggPalette.CRAB,
                PCSpawnEggPalette.SEAHORSE,
                PCSpawnEggPalette.HELLHOUND,
                PCSpawnEggPalette.END_TROLL)) {
            if (!PCForgeItems.hasItem(palette.itemName())) {
                continue;
            }

            var item = PCForgeItems.getItem(palette.itemName());
            if (item instanceof SpawnEggItem spawnEggItem) {
                event.register(
                        (stack, tintIndex) -> PCItemColorRules.withOpaqueAlpha(spawnEggItem.getColor(tintIndex)),
                        spawnEggItem
                );
            }
        }
    }
}
