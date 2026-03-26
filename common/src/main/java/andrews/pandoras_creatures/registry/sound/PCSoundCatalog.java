package andrews.pandoras_creatures.registry.sound;

import java.util.List;

public final class PCSoundCatalog {
    public static final String ARACHNON_AMBIENT = entity("arachnon", "ambient");
    public static final String ARACHNON_HURT = entity("arachnon", "hurt");
    public static final String ARACHNON_DEATH = entity("arachnon", "death");

    public static final String HELLHOUND_AMBIENT = entity("hellhound", "ambient");
    public static final String HELLHOUND_HURT = entity("hellhound", "hurt");
    public static final String HELLHOUND_DEATH = entity("hellhound", "death");

    public static final String CRAB_HURT = entity("crab", "hurt");
    public static final String CRAB_DEATH = entity("crab", "death");

    public static final String ACIDIC_ARCHVINE_ATTACK = entity("acidic_archvine", "attack");

    public static final String BUFFLON_AMBIENT = entity("bufflon", "ambient");
    public static final String BUFFLON_HURT = entity("bufflon", "hurt");
    public static final String BUFFLON_DEATH = entity("bufflon", "death");
    public static final String BUFFLON_ATTACK = entity("bufflon", "attack");

    public static final String END_TROLL_SCREAM = entity("end_troll", "scream");
    public static final String END_TROLL_ATTACK = entity("end_troll", "attack");
    public static final String END_TROLL_DEATH = entity("end_troll", "death");

    private static final List<String> SOUND_IDS = List.of(
            ARACHNON_AMBIENT,
            ARACHNON_HURT,
            ARACHNON_DEATH,
            HELLHOUND_AMBIENT,
            HELLHOUND_HURT,
            HELLHOUND_DEATH,
            CRAB_HURT,
            CRAB_DEATH,
            ACIDIC_ARCHVINE_ATTACK,
            BUFFLON_AMBIENT,
            BUFFLON_HURT,
            BUFFLON_DEATH,
            BUFFLON_ATTACK,
            END_TROLL_SCREAM,
            END_TROLL_ATTACK,
            END_TROLL_DEATH
    );

    private PCSoundCatalog() {
    }

    public static List<String> allSoundIds() {
        return SOUND_IDS;
    }

    public static String entity(String entityName, String actionName) {
        return "entity." + entityName + "." + actionName;
    }
}
