package andrews.pandoras_creatures.network;

import andrews.pandoras_creatures.network.payload.AnimationPayload;
import andrews.pandoras_creatures.network.payload.BufflonCombatModePayload;
import andrews.pandoras_creatures.network.payload.BufflonFollowPayload;
import andrews.pandoras_creatures.network.payload.BufflonInventoryPayload;
import andrews.pandoras_creatures.network.payload.BufflonSitPayload;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

/**
 * NeoForge-only payload registration entrypoint.
 */
public final class NeoForgePayloadRegistrar {
    private NeoForgePayloadRegistrar() {
    }

    public static void register(PayloadRegistrar registrar) {
        registrar.playToClient(
                AnimationPayload.TYPE,
                AnimationPayload.STREAM_CODEC,
                AnimationPayload::handleClient
        );

        registrar.playToServer(
                BufflonInventoryPayload.TYPE,
                BufflonInventoryPayload.STREAM_CODEC,
                BufflonInventoryPayload::handleServer
        );

        registrar.playToServer(
                BufflonSitPayload.TYPE,
                BufflonSitPayload.STREAM_CODEC,
                BufflonSitPayload::handleServer
        );

        registrar.playToServer(
                BufflonFollowPayload.TYPE,
                BufflonFollowPayload.STREAM_CODEC,
                BufflonFollowPayload::handleServer
        );

        registrar.playToServer(
                BufflonCombatModePayload.TYPE,
                BufflonCombatModePayload.STREAM_CODEC,
                BufflonCombatModePayload::handleServer
        );
    }
}
