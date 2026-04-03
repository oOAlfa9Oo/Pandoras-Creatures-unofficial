package andrews.pandoras_creatures.network.payload;

import andrews.pandoras_creatures.network.PCPayloadIds;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record BufflonSitPayload(int entityId, boolean shouldSit) implements CustomPacketPayload {
    public static final Type<BufflonSitPayload> TYPE =
            new Type<>(PCPayloadIds.id(PCPayloadIds.BUFFLON_SIT));

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
}
