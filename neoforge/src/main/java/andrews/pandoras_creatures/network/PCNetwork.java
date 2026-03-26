package andrews.pandoras_creatures.network;

import andrews.pandoras_creatures.network.payload.AnimationPayload;
import andrews.pandoras_creatures.network.payload.BufflonCombatModePayload;
import andrews.pandoras_creatures.network.payload.BufflonFollowPayload;
import andrews.pandoras_creatures.network.payload.BufflonInventoryPayload;
import andrews.pandoras_creatures.network.payload.BufflonSitPayload;
import andrews.pandoras_creatures.util.Reference;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

/**
 * Network handler for Pandoras Creatures using NeoForge's PayloadRegistrar system.
 * This replaces the old SimpleChannel system from Forge 1.16-1.20.
 */
public class PCNetwork {
    public static final String PROTOCOL_VERSION = "1";

    /**
     * Register all network payloads - called from RegisterPayloadHandlersEvent
     */
    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(Reference.MODID)
                .versioned(PROTOCOL_VERSION)
                .optional();

        // Client-bound payloads (Server -> Client)
        registrar.playToClient(
                AnimationPayload.TYPE,
                AnimationPayload.STREAM_CODEC,
                AnimationPayload::handleClient
        );

        // Server-bound payloads (Client -> Server)
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
