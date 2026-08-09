package andrews.pandoras_creatures.network.payload;

import andrews.pandoras_creatures.network.PCPayloadIds;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

/**
 * Shared animation sync payload data for the 1.20.1 family.
 */
public record AnimationPayload(int entityId, int animationIndex) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<AnimationPayload> TYPE =
            new CustomPacketPayload.Type<>(PCPayloadIds.id(PCPayloadIds.ANIMATION));

    public static final StreamCodec<net.minecraft.network.FriendlyByteBuf, AnimationPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, AnimationPayload::entityId,
            ByteBufCodecs.VAR_INT, AnimationPayload::animationIndex,
            AnimationPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
