package andrews.pandoras_creatures.forge.network;

import andrews.pandoras_creatures.entities.bufflon.BufflonHandle;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public final class ForgeBufflonPayloadHandlers {
    private ForgeBufflonPayloadHandlers() {
    }

    public static void handleInventoryRequest(int entityId, Supplier<NetworkEvent.Context> contextSupplier) {
        withBufflon(contextSupplier, entityId, (player, bufflon) -> bufflon.openBufflonMenu(player));
        contextSupplier.get().setPacketHandled(true);
    }

    public static void handleSitRequest(int entityId, boolean shouldSit, Supplier<NetworkEvent.Context> contextSupplier) {
        withOwnedBufflon(contextSupplier, entityId, (player, bufflon) -> bufflon.setBufflonOrderedToSit(shouldSit));
        contextSupplier.get().setPacketHandled(true);
    }

    public static void handleFollowRequest(int entityId, boolean shouldFollow, Supplier<NetworkEvent.Context> contextSupplier) {
        withOwnedBufflon(contextSupplier, entityId, (player, bufflon) -> bufflon.setBufflonFollowingOwner(shouldFollow));
        contextSupplier.get().setPacketHandled(true);
    }

    public static void handleCombatModeRequest(int entityId, boolean combatMode, Supplier<NetworkEvent.Context> contextSupplier) {
        withOwnedBufflon(contextSupplier, entityId, (player, bufflon) -> bufflon.setBufflonCombatMode(combatMode));
        contextSupplier.get().setPacketHandled(true);
    }

    private static void withOwnedBufflon(Supplier<NetworkEvent.Context> contextSupplier, int entityId, BufflonAction action) {
        withBufflon(contextSupplier, entityId, (player, bufflon) -> {
            if (bufflon.isBufflonTamed() && bufflon.isBufflonOwnedBy(player)) {
                action.accept(player, bufflon);
            }
        });
    }

    private static void withBufflon(Supplier<NetworkEvent.Context> contextSupplier, int entityId, BufflonAction action) {
        NetworkEvent.Context context = contextSupplier.get();
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
