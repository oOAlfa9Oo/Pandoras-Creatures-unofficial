package andrews.pandoras_creatures.entities.acidic_archvine;

public final class AcidicArchvinePlacementRules {
    public static final int DEFAULT_ARCHVINE_TYPE = 1;
    public static final int WARPED_ARCHVINE_TYPE = 2;
    public static final int CRIMSON_ARCHVINE_TYPE = 3;

    private AcidicArchvinePlacementRules() {
    }

    public static int resolveBiomeType(String biomeName) {
        if (biomeName.contains("crimson_forest")) {
            return CRIMSON_ARCHVINE_TYPE;
        }

        if (biomeName.contains("nether_wastes")
                || biomeName.contains("soul_sand_valley")
                || biomeName.contains("warped_forest")
                || biomeName.contains("basalt_deltas")) {
            return WARPED_ARCHVINE_TYPE;
        }

        return DEFAULT_ARCHVINE_TYPE;
    }

    public static double resolveSpawnYOffset(boolean immediateCeiling, boolean upperCeiling) {
        if (immediateCeiling) {
            return -0.5D;
        }

        if (upperCeiling) {
            return 0.5D;
        }

        return Double.NaN;
    }

    public static boolean shouldLockVerticalMotion(boolean upperCeiling) {
        return upperCeiling;
    }
}
