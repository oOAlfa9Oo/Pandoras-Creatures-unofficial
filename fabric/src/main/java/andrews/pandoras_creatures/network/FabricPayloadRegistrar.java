package andrews.pandoras_creatures.network;

import andrews.pandoras_creatures.network.payload.AnimationPayload;
import andrews.pandoras_creatures.network.payload.BufflonCombatModePayload;
import andrews.pandoras_creatures.network.payload.BufflonFollowPayload;
import andrews.pandoras_creatures.network.payload.BufflonInventoryPayload;
import andrews.pandoras_creatures.network.payload.BufflonSitPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public final class FabricPayloadRegistrar {
    private static boolean initialized;

    private FabricPayloadRegistrar() {
    }

    public static void registerCommonPayloadTypes() {
        if (initialized) {
            return;
        }
        initialized = true;

        PayloadTypeRegistry.playS2C().register(AnimationPayload.TYPE, AnimationPayload.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(BufflonInventoryPayload.TYPE, BufflonInventoryPayload.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(BufflonSitPayload.TYPE, BufflonSitPayload.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(BufflonFollowPayload.TYPE, BufflonFollowPayload.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(BufflonCombatModePayload.TYPE, BufflonCombatModePayload.STREAM_CODEC);
    }
}
