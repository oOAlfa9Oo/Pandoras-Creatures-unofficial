package andrews.pandoras_creatures.client.bootstrap;

import andrews.pandoras_creatures.client.item.PCItemColorRules;
import andrews.pandoras_creatures.registry.PCItems;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

public final class PCClientItemColorRegistry {
    private PCClientItemColorRegistry() {
    }

    public static void registerAll(RegisterColorHandlersEvent.Item event) {
        PCItems.getSpawnEggs().forEach(holder -> event.register(
                (stack, tintIndex) -> PCItemColorRules.withOpaqueAlpha(((SpawnEggItem) stack.getItem()).getColor(tintIndex)),
                holder.get()
        ));
    }
}
