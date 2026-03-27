package andrews.pandoras_creatures.registry.bootstrap;

import andrews.pandoras_creatures.registry.PCBlockEntities;
import andrews.pandoras_creatures.registry.PCMenuTypes;
import andrews.pandoras_creatures.registry.PCRecipeSerializers;
import net.neoforged.bus.api.IEventBus;

public final class PCGameplayRegisterGroup {
    private PCGameplayRegisterGroup() {
    }

    public static void register(IEventBus modEventBus) {
        PCBlockEntities.BLOCK_ENTITY_TYPES.register(modEventBus);
        PCMenuTypes.MENU_TYPES.register(modEventBus);
        PCRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
    }
}
