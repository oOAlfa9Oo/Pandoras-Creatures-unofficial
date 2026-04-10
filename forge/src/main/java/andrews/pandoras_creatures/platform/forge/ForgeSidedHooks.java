package andrews.pandoras_creatures.forge.platform;

import andrews.pandoras_creatures.platform.SidedHooks;
import net.minecraftforge.fml.loading.FMLEnvironment;

final class ForgeSidedHooks implements SidedHooks {
    static final ForgeSidedHooks INSTANCE = new ForgeSidedHooks();

    private ForgeSidedHooks() {
    }

    @Override
    public boolean isClientEnvironment() {
        return FMLEnvironment.dist.isClient();
    }
}
