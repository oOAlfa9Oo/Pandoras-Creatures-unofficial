package andrews.pandoras_creatures.network.payload;

import andrews.pandoras_creatures.network.PCPayloadIds;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record BufflonFollowPayload(int entityId, boolean shouldFollow) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<BufflonFollowPayload> TYPE =
            new CustomPacketPayload.Type<>(PCPayloadIds.id(PCPayloadIds.BUFFLON_FOLLOW));

    public static final StreamCodec<net.minecraft.network.FriendlyByteBuf, BufflonFollowPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, BufflonFollowPayload::entityId,
            ByteBufCodecs.BOOL, BufflonFollowPayload::shouldFollow,
            BufflonFollowPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
