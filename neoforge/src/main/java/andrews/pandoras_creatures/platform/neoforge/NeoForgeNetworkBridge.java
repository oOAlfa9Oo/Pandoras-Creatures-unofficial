package andrews.pandoras_creatures.platform.neoforge;

import andrews.pandoras_creatures.network.payload.BufflonCombatModePayload;
import andrews.pandoras_creatures.network.payload.BufflonFollowPayload;
import andrews.pandoras_creatures.network.payload.BufflonInventoryPayload;
import andrews.pandoras_creatures.network.payload.BufflonSitPayload;
import andrews.pandoras_creatures.platform.NetworkBridge;
import andrews.pandoras_creatures.util.Reference;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

final class NeoForgeNetworkBridge implements NetworkBridge {
    static final NeoForgeNetworkBridge INSTANCE = new NeoForgeNetworkBridge();

    private NeoForgeNetworkBridge() {
    }

    @Override
    public String channelNamespace() {
        return Reference.MODID;
    }

    @Override
    public void requestBufflonInventory(int entityId) {
        ClientPacketDistributor.sendToServer(new BufflonInventoryPayload(entityId));
    }

    @Override
    public void requestBufflonSit(int entityId, boolean shouldSit) {
        ClientPacketDistributor.sendToServer(new BufflonSitPayload(entityId, shouldSit));
    }

    @Override
    public void requestBufflonFollow(int entityId, boolean shouldFollow) {
        ClientPacketDistributor.sendToServer(new BufflonFollowPayload(entityId, shouldFollow));
    }

    @Override
    public void requestBufflonCombatMode(int entityId, boolean combatMode) {
        ClientPacketDistributor.sendToServer(new BufflonCombatModePayload(entityId, combatMode));
    }
}
