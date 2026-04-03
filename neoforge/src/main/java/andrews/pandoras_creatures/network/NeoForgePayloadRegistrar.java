package andrews.pandoras_creatures.network;

import andrews.pandoras_creatures.client.network.AnimationPayloadClientHandler;
import andrews.pandoras_creatures.network.payload.AnimationPayload;
import andrews.pandoras_creatures.network.payload.BufflonCombatModePayload;
import andrews.pandoras_creatures.network.payload.BufflonFollowPayload;
import andrews.pandoras_creatures.network.payload.BufflonInventoryPayload;
import andrews.pandoras_creatures.network.payload.BufflonPayloadHandlers;
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
                (payload, context) -> context.enqueueWork(() -> AnimationPayloadClientHandler.handle(payload))
        );

        registrar.playToServer(
                BufflonInventoryPayload.TYPE,
                BufflonInventoryPayload.STREAM_CODEC,
                (payload, context) -> BufflonPayloadHandlers.handleInventoryRequest(payload.entityId(), context)
        );

        registrar.playToServer(
                BufflonSitPayload.TYPE,
                BufflonSitPayload.STREAM_CODEC,
                (payload, context) -> BufflonPayloadHandlers.handleSitRequest(payload.entityId(), payload.shouldSit(), context)
        );

        registrar.playToServer(
                BufflonFollowPayload.TYPE,
                BufflonFollowPayload.STREAM_CODEC,
                (payload, context) -> BufflonPayloadHandlers.handleFollowRequest(payload.entityId(), payload.shouldFollow(), context)
        );

        registrar.playToServer(
                BufflonCombatModePayload.TYPE,
                BufflonCombatModePayload.STREAM_CODEC,
                (payload, context) -> BufflonPayloadHandlers.handleCombatModeRequest(payload.entityId(), payload.combatMode(), context)
        );
    }
}
