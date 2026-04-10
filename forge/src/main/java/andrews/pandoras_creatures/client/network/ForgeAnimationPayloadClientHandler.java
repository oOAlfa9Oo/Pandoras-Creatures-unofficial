package andrews.pandoras_creatures.forge.client.network;

import andrews.pandoras_creatures.network.AnimationSync;
import andrews.pandoras_creatures.network.payload.AnimationPayload;
import net.minecraft.client.Minecraft;

public final class ForgeAnimationPayloadClientHandler {
    private ForgeAnimationPayloadClientHandler() {
    }

    public static void handle(AnimationPayload payload) {
        AnimationSync.apply(Minecraft.getInstance().level, payload.entityId(), payload.animationIndex());
    }
}
