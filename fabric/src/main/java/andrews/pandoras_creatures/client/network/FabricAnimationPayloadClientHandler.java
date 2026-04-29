package andrews.pandoras_creatures.client.network;

import andrews.pandoras_creatures.network.AnimationSync;
import andrews.pandoras_creatures.network.PCPayloadIds;
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

        ClientPlayNetworking.registerGlobalReceiver(PCPayloadIds.id(PCPayloadIds.ANIMATION), (client, handler, buf, responseSender) -> {
            int entityId = buf.readInt();
            int animationIndex = buf.readInt();
            client.execute(() -> AnimationSync.apply(Minecraft.getInstance().level, entityId, animationIndex));
        });
    }
}
