package andrews.pandoras_creatures.client.bootstrap;

import andrews.pandoras_creatures.client.item.PCItemColorRules;
import andrews.pandoras_creatures.registry.PCFabricItems;
import andrews.pandoras_creatures.registry.item.PCSpawnEggPalette;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.world.item.SpawnEggItem;

public final class PCFabricClientItemRegistry {
    private static boolean initialized;

    private PCFabricClientItemRegistry() {
    }

    public static void registerAll() {
        if (initialized) {
            return;
        }
        initialized = true;

        for (PCSpawnEggPalette palette : PCSpawnEggPalette.values()) {
            if (!PCFabricItems.hasItem(palette.itemName())) {
                continue;
            }
            var item = PCFabricItems.getItem(palette.itemName());
            if (item instanceof SpawnEggItem spawnEggItem) {
                ColorProviderRegistry.ITEM.register(
                        (stack, tintIndex) -> PCItemColorRules.withOpaqueAlpha(spawnEggItem.getColor(tintIndex)),
                        spawnEggItem
                );
            }
        }
    }
}
