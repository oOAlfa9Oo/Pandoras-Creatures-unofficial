package andrews.pandoras_creatures.network.payload;

import andrews.pandoras_creatures.client.network.AnimationPayloadClientHandler;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * Payload for syncing entity animations from server to client.
 * Replaces the old MessageClientAnimation class.
 */
public record AnimationPayload(int entityId, int animationIndex) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<AnimationPayload> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Reference.MODID, "animation"));

    public static final StreamCodec<RegistryFriendlyByteBuf, AnimationPayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.INT, AnimationPayload::entityId,
                    ByteBufCodecs.INT, AnimationPayload::animationIndex,
                    AnimationPayload::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    /**
     * Handle the payload on the client side
     */
    public static void handleClient(AnimationPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> AnimationPayloadClientHandler.handle(payload));
    }
}
