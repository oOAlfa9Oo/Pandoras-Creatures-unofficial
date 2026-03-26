package andrews.pandoras_creatures.platform;

/**
 * Networking-facing bridge for shared code.
 */
public interface NetworkBridge {
    String channelNamespace();

    void requestBufflonInventory(int entityId);

    void requestBufflonSit(int entityId, boolean shouldSit);

    void requestBufflonFollow(int entityId, boolean shouldFollow);

    void requestBufflonCombatMode(int entityId, boolean combatMode);
}
