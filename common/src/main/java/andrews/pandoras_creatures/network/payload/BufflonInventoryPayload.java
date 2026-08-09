package andrews.pandoras_creatures.network.payload;

import andrews.pandoras_creatures.network.PCPayloadIds;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record BufflonInventoryPayload(int entityId) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<BufflonInventoryPayload> TYPE =
            new CustomPacketPayload.Type<>(PCPayloadIds.id(PCPayloadIds.BUFFLON_INVENTORY));

    public static final StreamCodec<net.minecraft.network.FriendlyByteBuf, BufflonInventoryPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, BufflonInventoryPayload::entityId,
            BufflonInventoryPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
