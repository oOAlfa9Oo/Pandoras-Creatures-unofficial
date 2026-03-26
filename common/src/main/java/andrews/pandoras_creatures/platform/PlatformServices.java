package andrews.pandoras_creatures.platform;

/**
 * Minimal platform contract for the first common-loader boundary.
 * Additional loader services can be added here as the split matures.
 */
public interface PlatformServices {
    String loaderName();

    RegistryBridge registry();

    NetworkBridge network();

    MenuBridge menus();

    EntityBridge entities();

    SidedHooks sidedHooks();
}
