package andrews.pandoras_creatures.registry.entity;

import java.util.List;

/**
 * Shared entity ids that should remain stable across loader adapters.
 */
public final class PCEntityIds {
    public static final String ARACHNON = "arachnon";
    public static final String HELLHOUND = "hellhound";
    public static final String CRAB = "crab";
    public static final String SEAHORSE = "seahorse";
    public static final String ACIDIC_ARCHVINE = "acidic_archvine";
    public static final String BUFFLON = "bufflon";
    public static final String END_TROLL = "end_troll";

    public static final String END_TROLL_BULLET_DAMAGE = "end_troll_bullet_damage";
    public static final String END_TROLL_BULLET_POISON = "end_troll_bullet_poison";
    public static final String END_TROLL_BULLET_WITHER = "end_troll_bullet_wither";

    private PCEntityIds() {
    }

    public static List<String> allPaths() {
        return List.of(
                ARACHNON,
                HELLHOUND,
                CRAB,
                SEAHORSE,
                ACIDIC_ARCHVINE,
                BUFFLON,
                END_TROLL,
                END_TROLL_BULLET_DAMAGE,
                END_TROLL_BULLET_POISON,
                END_TROLL_BULLET_WITHER
        );
    }
}
