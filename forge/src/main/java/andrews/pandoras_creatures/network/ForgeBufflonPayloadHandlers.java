package andrews.pandoras_creatures.forge.network;

import andrews.pandoras_creatures.entities.bufflon.BufflonHandle;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.network.CustomPayloadEvent;

public final class ForgeBufflonPayloadHandlers {
    private ForgeBufflonPayloadHandlers() {
    }

    public static void handleInventoryRequest(int entityId, CustomPayloadEvent.Context context) {
        withBufflon(context, entityId, (player, bufflon) -> bufflon.openBufflonMenu(player));
        context.setPacketHandled(true);
    }

    public static void handleSitRequest(int entityId, boolean shouldSit, CustomPayloadEvent.Context context) {
        withOwnedBufflon(context, entityId, (player, bufflon) -> bufflon.setBufflonOrderedToSit(shouldSit));
        context.setPacketHandled(true);
    }

    public static void handleFollowRequest(int entityId, boolean shouldFollow, CustomPayloadEvent.Context context) {
        withOwnedBufflon(context, entityId, (player, bufflon) -> bufflon.setBufflonFollowingOwner(shouldFollow));
        context.setPacketHandled(true);
    }

    public static void handleCombatModeRequest(int entityId, boolean combatMode, CustomPayloadEvent.Context context) {
        withOwnedBufflon(context, entityId, (player, bufflon) -> bufflon.setBufflonCombatMode(combatMode));
        context.setPacketHandled(true);
    }

    private static void withOwnedBufflon(CustomPayloadEvent.Context context, int entityId, BufflonAction action) {
        withBufflon(context, entityId, (player, bufflon) -> {
            if (bufflon.isBufflonTamed() && bufflon.isBufflonOwnedBy(player)) {
                action.accept(player, bufflon);
            }
        });
    }

    private static void withBufflon(CustomPayloadEvent.Context context, int entityId, BufflonAction action) {
        ServerPlayer player = context.getSender();
        if (player == null) {
            return;
        }

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
