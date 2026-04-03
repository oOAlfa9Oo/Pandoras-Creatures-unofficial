package andrews.pandoras_creatures.network;

import andrews.pandoras_creatures.entities.bufflon.BufflonHandle;
import andrews.pandoras_creatures.network.payload.BufflonCombatModePayload;
import andrews.pandoras_creatures.network.payload.BufflonFollowPayload;
import andrews.pandoras_creatures.network.payload.BufflonInventoryPayload;
import andrews.pandoras_creatures.network.payload.BufflonSitPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

public final class FabricBufflonPayloadHandlers {
    private FabricBufflonPayloadHandlers() {
    }

    public static void registerReceivers() {
        ServerPlayNetworking.registerGlobalReceiver(BufflonInventoryPayload.TYPE,
                (payload, context) -> context.server().execute(() ->
                        withBufflon(context.player(), payload.entityId(), (player, bufflon) -> bufflon.openBufflonMenu(player))));

        ServerPlayNetworking.registerGlobalReceiver(BufflonSitPayload.TYPE,
                (payload, context) -> context.server().execute(() ->
                        withOwnedBufflon(context.player(), payload.entityId(), (player, bufflon) -> bufflon.setBufflonOrderedToSit(payload.shouldSit()))));

        ServerPlayNetworking.registerGlobalReceiver(BufflonFollowPayload.TYPE,
                (payload, context) -> context.server().execute(() ->
                        withOwnedBufflon(context.player(), payload.entityId(), (player, bufflon) -> bufflon.setBufflonFollowingOwner(payload.shouldFollow()))));

        ServerPlayNetworking.registerGlobalReceiver(BufflonCombatModePayload.TYPE,
                (payload, context) -> context.server().execute(() ->
                        withOwnedBufflon(context.player(), payload.entityId(), (player, bufflon) -> bufflon.setBufflonCombatMode(payload.combatMode()))));
    }

    private static void withOwnedBufflon(ServerPlayer player, int entityId, BufflonAction action) {
        withBufflon(player, entityId, (serverPlayer, bufflon) -> {
            if (bufflon.isBufflonTamed() && bufflon.isBufflonOwnedBy(serverPlayer)) {
                action.accept(serverPlayer, bufflon);
            }
        });
    }

    private static void withBufflon(ServerPlayer player, int entityId, BufflonAction action) {
        Entity entity = player.level().getEntity(entityId);
        if (entity instanceof BufflonHandle bufflon) {
            action.accept(player, bufflon);
        }
    }

    @FunctionalInterface
    private interface BufflonAction {
        void accept(ServerPlayer player, BufflonHandle bufflon);
    }
}
