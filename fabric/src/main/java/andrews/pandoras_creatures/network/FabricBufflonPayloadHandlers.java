package andrews.pandoras_creatures.network;

import andrews.pandoras_creatures.entities.bufflon.BufflonHandle;
import andrews.pandoras_creatures.network.PCPayloadIds;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

public final class FabricBufflonPayloadHandlers {
    private FabricBufflonPayloadHandlers() {
    }

    public static void registerReceivers() {
        ServerPlayNetworking.registerGlobalReceiver(PCPayloadIds.id(PCPayloadIds.BUFFLON_INVENTORY),
                (server, player, handler, buf, responseSender) -> server.execute(() ->
                        withBufflon(player, buf.readInt(), (serverPlayer, bufflon) -> bufflon.openBufflonMenu(serverPlayer))));

        ServerPlayNetworking.registerGlobalReceiver(PCPayloadIds.id(PCPayloadIds.BUFFLON_SIT),
                (server, player, handler, buf, responseSender) -> server.execute(() ->
                        withOwnedBufflon(player, buf.readInt(), (serverPlayer, bufflon) -> bufflon.setBufflonOrderedToSit(buf.readBoolean()))));

        ServerPlayNetworking.registerGlobalReceiver(PCPayloadIds.id(PCPayloadIds.BUFFLON_FOLLOW),
                (server, player, handler, buf, responseSender) -> server.execute(() ->
                        withOwnedBufflon(player, buf.readInt(), (serverPlayer, bufflon) -> bufflon.setBufflonFollowingOwner(buf.readBoolean()))));

        ServerPlayNetworking.registerGlobalReceiver(PCPayloadIds.id(PCPayloadIds.BUFFLON_COMBAT_MODE),
                (server, player, handler, buf, responseSender) -> server.execute(() ->
                        withOwnedBufflon(player, buf.readInt(), (serverPlayer, bufflon) -> bufflon.setBufflonCombatMode(buf.readBoolean()))));
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
