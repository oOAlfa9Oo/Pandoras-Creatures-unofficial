package andrews.pandoras_creatures.network.payload;

import andrews.pandoras_creatures.network.PCPayloadIds;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record BufflonFollowPayload(int entityId, boolean shouldFollow) implements CustomPacketPayload {
    public static final Type<BufflonFollowPayload> TYPE =
            new Type<>(PCPayloadIds.id(PCPayloadIds.BUFFLON_FOLLOW));

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
}
