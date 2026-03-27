package andrews.pandoras_creatures.network.payload;

import andrews.pandoras_creatures.network.PCPayloadIds;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * Payload for toggling Bufflon sit state from client to server.
 * Replaces the old MessageServerBufflonSit class.
 */
public record BufflonSitPayload(int entityId, boolean shouldSit) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<BufflonSitPayload> TYPE =
            new CustomPacketPayload.Type<>(PCPayloadIds.id(PCPayloadIds.BUFFLON_SIT));

    public static final StreamCodec<RegistryFriendlyByteBuf, BufflonSitPayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.INT, BufflonSitPayload::entityId,
                    ByteBufCodecs.BOOL, BufflonSitPayload::shouldSit,
                    BufflonSitPayload::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    /**
     * Handle the payload on the server side
     */
    public static void handleServer(BufflonSitPayload payload, IPayloadContext context) {
        BufflonPayloadHandlers.handleSitRequest(payload.entityId(), payload.shouldSit(), context);
    }
}
