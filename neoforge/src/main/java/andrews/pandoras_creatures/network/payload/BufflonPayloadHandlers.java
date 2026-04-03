package andrews.pandoras_creatures.network.payload;

import andrews.pandoras_creatures.entities.BufflonEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.function.BiConsumer;

public final class BufflonPayloadHandlers {
    private BufflonPayloadHandlers() {
    }

    public static void handleInventoryRequest(int entityId, IPayloadContext context) {
        withBufflon(context, entityId, (serverPlayer, bufflon) -> bufflon.openGUI(serverPlayer));
    }

    public static void handleSitRequest(int entityId, boolean shouldSit, IPayloadContext context) {
        withOwnedBufflon(context, entityId, (serverPlayer, bufflon) -> bufflon.setOrderedToSit(shouldSit));
    }

    public static void handleFollowRequest(int entityId, boolean shouldFollow, IPayloadContext context) {
        withOwnedBufflon(context, entityId, (serverPlayer, bufflon) -> bufflon.setFollowingOwner(shouldFollow));
    }

    public static void handleCombatModeRequest(int entityId, boolean combatMode, IPayloadContext context) {
        withOwnedBufflon(context, entityId, (serverPlayer, bufflon) -> bufflon.setIsInCombatMode(combatMode));
    }

    private static void withOwnedBufflon(IPayloadContext context, int entityId, BiConsumer<ServerPlayer, BufflonEntity> action) {
        withBufflon(context, entityId, (serverPlayer, bufflon) -> {
            if (bufflon.isTamed() && bufflon.isOwnedBy(serverPlayer)) {
                action.accept(serverPlayer, bufflon);
            }
        });
    }

    private static void withBufflon(IPayloadContext context, int entityId, BiConsumer<ServerPlayer, BufflonEntity> action) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer serverPlayer)) {
                return;
            }

            Entity entity = serverPlayer.level().getEntity(entityId);
            if (entity instanceof BufflonEntity bufflon) {
                action.accept(serverPlayer, bufflon);
            }
        });
    }
}
