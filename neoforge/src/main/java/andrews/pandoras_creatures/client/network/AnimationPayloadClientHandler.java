package andrews.pandoras_creatures.client.network;

import andrews.pandoras_creatures.network.AnimationSync;
import andrews.pandoras_creatures.network.payload.AnimationPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

/**
 * Client-only handler for animation synchronization payloads.
 */
@OnlyIn(Dist.CLIENT)
public final class AnimationPayloadClientHandler {

    private AnimationPayloadClientHandler() {
    }

    public static void handle(AnimationPayload payload) {
        Level level = Minecraft.getInstance().level;
        AnimationSync.apply(level, payload.entityId(), payload.animationIndex());
    }
}
