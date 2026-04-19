package andrews.pandoras_creatures.client.bootstrap;

public final class PCFabricClientItemRegistry {
    private static boolean initialized;

    private PCFabricClientItemRegistry() {
    }

    public static void registerAll() {
        if (initialized) {
            return;
        }
        initialized = true;
    }
}
