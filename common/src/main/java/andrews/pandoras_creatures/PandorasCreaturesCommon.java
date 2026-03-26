package andrews.pandoras_creatures;

import andrews.pandoras_creatures.platform.PlatformServices;

import java.util.Objects;

/**
 * Common entrypoint used by loader adapters to expose platform services to shared code.
 */
public final class PandorasCreaturesCommon {
    private static PlatformServices platformServices;

    private PandorasCreaturesCommon() {
    }

    public static void initialize(PlatformServices services) {
        Objects.requireNonNull(services, "services");
        if (platformServices == null) {
            platformServices = services;
        }
    }

    public static boolean isInitialized() {
        return platformServices != null;
    }

    public static PlatformServices platform() {
        if (platformServices == null) {
            throw new IllegalStateException("PandorasCreaturesCommon has not been initialized yet");
        }
        return platformServices;
    }
}
