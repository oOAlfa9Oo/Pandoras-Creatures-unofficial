package andrews.pandoras_creatures.platform.neoforge;

import andrews.pandoras_creatures.platform.EntityBridge;
import andrews.pandoras_creatures.platform.MenuBridge;
import andrews.pandoras_creatures.platform.NetworkBridge;
import andrews.pandoras_creatures.platform.PlatformServices;
import andrews.pandoras_creatures.platform.RegistryBridge;
import andrews.pandoras_creatures.platform.SidedHooks;

public final class NeoForgePlatformServices implements PlatformServices {
    public static final NeoForgePlatformServices INSTANCE = new NeoForgePlatformServices();

    private static final RegistryBridge REGISTRY = NeoForgeRegistryBridge.INSTANCE;
    private static final NetworkBridge NETWORK = NeoForgeNetworkBridge.INSTANCE;
    private static final MenuBridge MENUS = NeoForgeMenuBridge.INSTANCE;
    private static final EntityBridge ENTITIES = NeoForgeEntityBridge.INSTANCE;
    private static final SidedHooks SIDED_HOOKS = NeoForgeSidedHooks.INSTANCE;

    private NeoForgePlatformServices() {
    }

    @Override
    public String loaderName() {
        return "neoforge";
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
