package andrews.pandoras_creatures.network.payload;

import andrews.pandoras_creatures.network.PCPayloadIds;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * Payload for opening Bufflon inventory from client to server.
 * Replaces the old MessageServerBufflonInventory class.
 */
public record BufflonInventoryPayload(int entityId) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<BufflonInventoryPayload> TYPE =
            new CustomPacketPayload.Type<>(PCPayloadIds.id(PCPayloadIds.BUFFLON_INVENTORY));

    public static final StreamCodec<RegistryFriendlyByteBuf, BufflonInventoryPayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.INT, BufflonInventoryPayload::entityId,
                    BufflonInventoryPayload::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    /**
     * Handle the payload on the server side
     */
    public static void handleServer(BufflonInventoryPayload payload, IPayloadContext context) {
        BufflonPayloadHandlers.handleInventoryRequest(payload.entityId(), context);
    }
}
