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
 * Payload for toggling Bufflon sit state from client to server.
 * Replaces the old MessageServerBufflonSit class.
 */
public record BufflonSitPayload(int entityId, boolean shouldSit) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<BufflonSitPayload> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Reference.MODID, "bufflon_sit"));

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
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                Entity entity = serverPlayer.level().getEntity(payload.entityId());
                if (entity instanceof BufflonEntity bufflon && bufflon.isTamed() && bufflon.isOwnedBy(serverPlayer)) {
                    bufflon.setOrderedToSit(payload.shouldSit());
                }
            }
        });
    }
}
