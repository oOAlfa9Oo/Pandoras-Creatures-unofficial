package andrews.pandoras_creatures.platform.fabric;

import andrews.pandoras_creatures.platform.NetworkBridge;
import andrews.pandoras_creatures.network.PCPayloadIds;
import andrews.pandoras_creatures.util.Reference;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.FriendlyByteBuf;

final class FabricNetworkBridge implements NetworkBridge {
    static final FabricNetworkBridge INSTANCE = new FabricNetworkBridge();

    private FabricNetworkBridge() {
    }

    @Override
    public String channelNamespace() {
        return Reference.MODID;
    }

    @Override
    public void requestBufflonInventory(int entityId) {
        FriendlyByteBuf buf = PacketByteBufs.create();
        buf.writeInt(entityId);
        ClientPlayNetworking.send(PCPayloadIds.id(PCPayloadIds.BUFFLON_INVENTORY), buf);
    }

    @Override
    public void requestBufflonSit(int entityId, boolean shouldSit) {
        FriendlyByteBuf buf = PacketByteBufs.create();
        buf.writeInt(entityId);
        buf.writeBoolean(shouldSit);
        ClientPlayNetworking.send(PCPayloadIds.id(PCPayloadIds.BUFFLON_SIT), buf);
    }

    @Override
    public void requestBufflonFollow(int entityId, boolean shouldFollow) {
        FriendlyByteBuf buf = PacketByteBufs.create();
        buf.writeInt(entityId);
        buf.writeBoolean(shouldFollow);
        ClientPlayNetworking.send(PCPayloadIds.id(PCPayloadIds.BUFFLON_FOLLOW), buf);
    }

    @Override
    public void requestBufflonCombatMode(int entityId, boolean combatMode) {
        FriendlyByteBuf buf = PacketByteBufs.create();
        buf.writeInt(entityId);
        buf.writeBoolean(combatMode);
        ClientPlayNetworking.send(PCPayloadIds.id(PCPayloadIds.BUFFLON_COMBAT_MODE), buf);
    }
}
