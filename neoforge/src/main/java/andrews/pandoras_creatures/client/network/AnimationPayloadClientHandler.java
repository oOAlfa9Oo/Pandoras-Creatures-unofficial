package andrews.pandoras_creatures.client.network;

import andrews.pandoras_creatures.network.AnimationSync;
import andrews.pandoras_creatures.network.payload.AnimationPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;

/**
 * Client-only handler for animation synchronization payloads.
 */
public final class AnimationPayloadClientHandler {

    private AnimationPayloadClientHandler() {
    }

    public static void handle(AnimationPayload payload) {
        Level level = Minecraft.getInstance().level;
        AnimationSync.apply(level, payload.entityId(), payload.animationIndex());
    }
}
