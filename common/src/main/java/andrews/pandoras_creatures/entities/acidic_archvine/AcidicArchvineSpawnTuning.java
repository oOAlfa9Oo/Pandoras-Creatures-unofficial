package andrews.pandoras_creatures.entities.acidic_archvine;

public final class AcidicArchvineSpawnTuning {
    private static final int COMPANION_RADIUS = 10;
    private static final int EASY_COMPANION_ATTEMPTS = 3;
    private static final int NORMAL_COMPANION_ATTEMPTS = 5;
    private static final int HARD_COMPANION_ATTEMPTS = 7;

    private AcidicArchvineSpawnTuning() {
    }

    public static float companionChance(int difficultyId) {
        return switch (difficultyId) {
            case 1 -> 0.25F;
            case 2 -> 0.5F;
            case 3 -> 0.75F;
            default -> 0.0F;
        };
    }

    public static int companionAttempts(int difficultyId) {
        return switch (difficultyId) {
            case 1 -> EASY_COMPANION_ATTEMPTS;
            case 2 -> NORMAL_COMPANION_ATTEMPTS;
            case 3 -> HARD_COMPANION_ATTEMPTS;
            default -> 0;
        };
    }

    public static boolean shouldAttemptCompanion(boolean naturalSpawn, boolean chunkGenerationSpawn) {
        return naturalSpawn || chunkGenerationSpawn;
    }

    public static int companionRadius() {
        return COMPANION_RADIUS;
    }
}
