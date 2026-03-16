package andrews.pandoras_creatures.network.payload;

import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
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
        context.enqueueWork(() -> {
            Level level = Minecraft.getInstance().level;
            if (level != null) {
                Entity entity = level.getEntity(payload.entityId());
                // TODO: Implement animation handling when entity classes are migrated
                // if (entity instanceof IAnimatedEntity animatedEntity) {
                //     if (payload.animationIndex() == -1) {
                //         animatedEntity.resetAnimation();
                //     } else {
                //         Animation[] animations = animatedEntity.getAnimations();
                //         if (payload.animationIndex() >= 0 && payload.animationIndex() < animations.length) {
                //             animatedEntity.setPlayingAnimation(animations[payload.animationIndex()]);
                //         }
                //     }
                //     animatedEntity.setAnimationTick(0);
                // }
            }
        });
    }
}
