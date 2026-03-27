package andrews.pandoras_creatures.registry.block;

import java.util.List;

/**
 * Shared block entity ids that should remain stable across loader adapters.
 */
public final class PCBlockEntityIds {
    /**
     * Legacy persisted id kept intentionally to avoid breaking saved worlds.
     */
    public static final String END_TROLL_BOX = "end_troll_shulker";
    public static final String PANDORIC_SHARD = PCBlockIds.PANDORIC_SHARD;

    private PCBlockEntityIds() {
    }

    public static List<String> allPaths() {
        return List.of(END_TROLL_BOX, PANDORIC_SHARD);
    }
}
