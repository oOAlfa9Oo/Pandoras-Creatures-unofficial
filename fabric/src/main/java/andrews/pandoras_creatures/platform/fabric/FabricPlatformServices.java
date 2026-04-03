package andrews.pandoras_creatures.platform.fabric;

import andrews.pandoras_creatures.platform.EntityBridge;
import andrews.pandoras_creatures.platform.MenuBridge;
import andrews.pandoras_creatures.platform.NetworkBridge;
import andrews.pandoras_creatures.platform.PlatformServices;
import andrews.pandoras_creatures.platform.RegistryBridge;
import andrews.pandoras_creatures.platform.SidedHooks;

public final class FabricPlatformServices implements PlatformServices {
    public static final FabricPlatformServices INSTANCE = new FabricPlatformServices();

    private static final RegistryBridge REGISTRY = FabricRegistryBridge.INSTANCE;
    private static final NetworkBridge NETWORK = FabricNetworkBridge.INSTANCE;
    private static final MenuBridge MENUS = FabricMenuBridge.INSTANCE;
    private static final EntityBridge ENTITIES = FabricEntityBridge.INSTANCE;
    private static final SidedHooks SIDED_HOOKS = FabricSidedHooks.INSTANCE;

    private FabricPlatformServices() {
    }

    @Override
    public String loaderName() {
        return "fabric";
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
