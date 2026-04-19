package andrews.pandoras_creatures.forge.client.bootstrap;

import net.minecraftforge.eventbus.api.bus.BusGroup;

public final class PCForgeClientBlockRegistry {
    private PCForgeClientBlockRegistry() {
    }

    public static void register(BusGroup modEventBus) {
        // 26.1 no longer exposes the legacy ItemBlockRenderTypes hook.
        // Plant transparency is handled by the generated block models/resources.
    }
}
