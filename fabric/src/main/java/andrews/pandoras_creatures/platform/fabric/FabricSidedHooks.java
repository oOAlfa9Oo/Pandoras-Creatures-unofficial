package andrews.pandoras_creatures.platform.fabric;

import andrews.pandoras_creatures.platform.SidedHooks;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.api.EnvType;

final class FabricSidedHooks implements SidedHooks {
    static final FabricSidedHooks INSTANCE = new FabricSidedHooks();

    private FabricSidedHooks() {
    }

    @Override
    public boolean isClientEnvironment() {
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
    }
}
