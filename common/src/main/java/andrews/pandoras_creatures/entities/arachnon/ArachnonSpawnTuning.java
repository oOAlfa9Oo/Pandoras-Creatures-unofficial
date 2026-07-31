package andrews.pandoras_creatures.entities.arachnon;

public final class ArachnonSpawnTuning {
    private static final int OVERWORLD_SPAWN_WEIGHT = 20;
    private static final int MIN_SPAWN_GROUP = 1;
    private static final int MAX_SPAWN_GROUP = 1;

    private ArachnonSpawnTuning() {
    }

    public static int overworldSpawnWeight() {
        return OVERWORLD_SPAWN_WEIGHT;
    }

    public static int minSpawnGroup() {
        return MIN_SPAWN_GROUP;
    }

    public static int maxSpawnGroup() {
        return MAX_SPAWN_GROUP;
    }
}
