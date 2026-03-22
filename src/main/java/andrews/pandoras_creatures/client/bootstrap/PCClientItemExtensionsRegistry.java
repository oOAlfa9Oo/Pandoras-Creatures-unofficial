package andrews.pandoras_creatures.client.bootstrap;

import andrews.pandoras_creatures.client.item.EndTrollBoxClientItemExtensions;
import andrews.pandoras_creatures.client.item.PlantHatClientItemExtensions;
import andrews.pandoras_creatures.registry.PCBlocks;
import andrews.pandoras_creatures.registry.PCItems;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

public final class PCClientItemExtensionsRegistry {
    private PCClientItemExtensionsRegistry() {
    }

    public static void registerAll(RegisterClientExtensionsEvent event) {
        event.registerItem(EndTrollBoxClientItemExtensions.INSTANCE, PCBlocks.getEndTrollBoxItems());
        event.registerItem(PlantHatClientItemExtensions.INSTANCE, PCItems.PLANT_HAT.get());
    }
}
