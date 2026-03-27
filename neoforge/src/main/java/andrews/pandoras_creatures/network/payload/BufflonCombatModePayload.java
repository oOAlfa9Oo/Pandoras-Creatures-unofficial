package andrews.pandoras_creatures.network.payload;

import andrews.pandoras_creatures.network.PCPayloadIds;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * Payload for toggling Bufflon combat mode from client to server.
 * Replaces the old MessageServerBufflonCombatMode class.
 */
public record BufflonCombatModePayload(int entityId, boolean combatMode) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<BufflonCombatModePayload> TYPE =
            new CustomPacketPayload.Type<>(PCPayloadIds.id(PCPayloadIds.BUFFLON_COMBAT_MODE));

    public static final StreamCodec<RegistryFriendlyByteBuf, BufflonCombatModePayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.INT, BufflonCombatModePayload::entityId,
                    ByteBufCodecs.BOOL, BufflonCombatModePayload::combatMode,
                    BufflonCombatModePayload::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    /**
     * Handle the payload on the server side
     */
    public static void handleServer(BufflonCombatModePayload payload, IPayloadContext context) {
        BufflonPayloadHandlers.handleCombatModeRequest(payload.entityId(), payload.combatMode(), context);
    }
}
