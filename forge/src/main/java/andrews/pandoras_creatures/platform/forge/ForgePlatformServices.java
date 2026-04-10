package andrews.pandoras_creatures.forge.platform;

import andrews.pandoras_creatures.platform.EntityBridge;
import andrews.pandoras_creatures.platform.MenuBridge;
import andrews.pandoras_creatures.platform.NetworkBridge;
import andrews.pandoras_creatures.platform.PlatformServices;
import andrews.pandoras_creatures.platform.RegistryBridge;
import andrews.pandoras_creatures.platform.SidedHooks;

public final class ForgePlatformServices implements PlatformServices {
    public static final ForgePlatformServices INSTANCE = new ForgePlatformServices();

    private static final RegistryBridge REGISTRY = ForgeRegistryBridge.INSTANCE;
    private static final NetworkBridge NETWORK = ForgeNetworkBridge.INSTANCE;
    private static final MenuBridge MENUS = ForgeMenuBridge.INSTANCE;
    private static final EntityBridge ENTITIES = ForgeEntityBridge.INSTANCE;
    private static final SidedHooks SIDED_HOOKS = ForgeSidedHooks.INSTANCE;

    private ForgePlatformServices() {
    }

    @Override
    public String loaderName() {
        return "forge";
    }

    @Override
    public RegistryBridge registry() {
        return REGISTRY;
    }

    @Override
    public NetworkBridge network() {
        return NETWORK;
    }

    @Override
    public MenuBridge menus() {
        return MENUS;
    }

    @Override
    public EntityBridge entities() {
        return ENTITIES;
    }

    @Override
    public SidedHooks sidedHooks() {
        return SIDED_HOOKS;
    }
}
