package andrews.pandoras_creatures.network.payload;

import andrews.pandoras_creatures.network.PCPayloadIds;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * Payload for toggling Bufflon follow state from client to server.
 * Replaces the old MessageServerBufflonFollow class.
 */
public record BufflonFollowPayload(int entityId, boolean shouldFollow) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<BufflonFollowPayload> TYPE =
            new CustomPacketPayload.Type<>(PCPayloadIds.id(PCPayloadIds.BUFFLON_FOLLOW));

    public static final StreamCodec<RegistryFriendlyByteBuf, BufflonFollowPayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.INT, BufflonFollowPayload::entityId,
                    ByteBufCodecs.BOOL, BufflonFollowPayload::shouldFollow,
                    BufflonFollowPayload::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    /**
     * Handle the payload on the server side
     */
    public static void handleServer(BufflonFollowPayload payload, IPayloadContext context) {
        BufflonPayloadHandlers.handleFollowRequest(payload.entityId(), payload.shouldFollow(), context);
    }
}
