package andrews.pandoras_creatures.platform.neoforge;

import andrews.pandoras_creatures.platform.SidedHooks;
import net.neoforged.fml.loading.FMLEnvironment;

final class NeoForgeSidedHooks implements SidedHooks {
    static final NeoForgeSidedHooks INSTANCE = new NeoForgeSidedHooks();

    private NeoForgeSidedHooks() {
    }

    @Override
    public boolean isClientEnvironment() {
        return FMLEnvironment.dist.isClient();
    }
}
