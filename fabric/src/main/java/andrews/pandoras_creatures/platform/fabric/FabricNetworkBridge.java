package andrews.pandoras_creatures.platform.fabric;

import andrews.pandoras_creatures.platform.NetworkBridge;
import andrews.pandoras_creatures.network.payload.BufflonCombatModePayload;
import andrews.pandoras_creatures.network.payload.BufflonFollowPayload;
import andrews.pandoras_creatures.network.payload.BufflonInventoryPayload;
import andrews.pandoras_creatures.network.payload.BufflonSitPayload;
import andrews.pandoras_creatures.util.Reference;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

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
        ClientPlayNetworking.send(new BufflonInventoryPayload(entityId));
    }

    @Override
    public void requestBufflonSit(int entityId, boolean shouldSit) {
        ClientPlayNetworking.send(new BufflonSitPayload(entityId, shouldSit));
    }

    @Override
    public void requestBufflonFollow(int entityId, boolean shouldFollow) {
        ClientPlayNetworking.send(new BufflonFollowPayload(entityId, shouldFollow));
    }

    @Override
    public void requestBufflonCombatMode(int entityId, boolean combatMode) {
        ClientPlayNetworking.send(new BufflonCombatModePayload(entityId, combatMode));
    }
}
