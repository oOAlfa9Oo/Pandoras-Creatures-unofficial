package andrews.pandoras_creatures.client.network;

import andrews.pandoras_creatures.network.AnimationSync;
import andrews.pandoras_creatures.network.payload.AnimationPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;

public final class FabricAnimationPayloadClientHandler {
    private static boolean initialized;

    private FabricAnimationPayloadClientHandler() {
    }

    public static void registerReceiver() {
        if (initialized) {
            return;
        }
        initialized = true;

        ClientPlayNetworking.registerGlobalReceiver(AnimationPayload.TYPE, (payload, context) ->
                Minecraft.getInstance().execute(() -> AnimationSync.apply(Minecraft.getInstance().level, payload.entityId(), payload.animationIndex())));
    }
}
