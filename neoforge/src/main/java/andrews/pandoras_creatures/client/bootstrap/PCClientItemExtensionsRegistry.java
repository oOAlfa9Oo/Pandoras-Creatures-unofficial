package andrews.pandoras_creatures.client.bootstrap;

import andrews.pandoras_creatures.client.item.PlantHatClientItemExtensions;
import andrews.pandoras_creatures.registry.PCItems;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

public final class PCClientItemExtensionsRegistry {
    private PCClientItemExtensionsRegistry() {
    }

    public static void registerAll(RegisterClientExtensionsEvent event) {
        // End Troll Box ya no usa IClientItemExtensions#getCustomRenderer (eliminado en 1.21.4);
        // su render especial ahora se registra como SpecialModelRenderer, ver
        // PCClientSpecialModelRendererRegistry.
        event.registerItem(PlantHatClientItemExtensions.INSTANCE, PCItems.PLANT_HAT.get());
    }
}
