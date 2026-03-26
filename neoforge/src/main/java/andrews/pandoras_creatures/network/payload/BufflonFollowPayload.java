package andrews.pandoras_creatures.network.payload;

import andrews.pandoras_creatures.entities.BufflonEntity;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * Payload for toggling Bufflon follow state from client to server.
 * Replaces the old MessageServerBufflonFollow class.
 */
public record BufflonFollowPayload(int entityId, boolean shouldFollow) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<BufflonFollowPayload> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Reference.MODID, "bufflon_follow"));

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
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                Entity entity = serverPlayer.level().getEntity(payload.entityId());
                if (entity instanceof BufflonEntity bufflon && bufflon.isTamed() && bufflon.isOwnedBy(serverPlayer)) {
                    bufflon.setFollowingOwner(payload.shouldFollow());
                }
            }
        });
    }
}
