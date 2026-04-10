package andrews.pandoras_creatures.forge.platform;

import andrews.pandoras_creatures.forge.network.PCForgeNetwork;
import andrews.pandoras_creatures.platform.NetworkBridge;
import andrews.pandoras_creatures.network.payload.BufflonCombatModePayload;
import andrews.pandoras_creatures.network.payload.BufflonFollowPayload;
import andrews.pandoras_creatures.network.payload.BufflonInventoryPayload;
import andrews.pandoras_creatures.network.payload.BufflonSitPayload;
import andrews.pandoras_creatures.util.Reference;

final class ForgeNetworkBridge implements NetworkBridge {
    static final ForgeNetworkBridge INSTANCE = new ForgeNetworkBridge();

    private ForgeNetworkBridge() {
    }

    @Override
    public String channelNamespace() {
        return Reference.MODID;
    }

    @Override
    public void requestBufflonInventory(int entityId) {
        PCForgeNetwork.sendToServer(new BufflonInventoryPayload(entityId));
    }

    @Override
    public void requestBufflonSit(int entityId, boolean shouldSit) {
        PCForgeNetwork.sendToServer(new BufflonSitPayload(entityId, shouldSit));
    }

    @Override
    public void requestBufflonFollow(int entityId, boolean shouldFollow) {
        PCForgeNetwork.sendToServer(new BufflonFollowPayload(entityId, shouldFollow));
    }

    @Override
    public void requestBufflonCombatMode(int entityId, boolean combatMode) {
        PCForgeNetwork.sendToServer(new BufflonCombatModePayload(entityId, combatMode));
    }
}
