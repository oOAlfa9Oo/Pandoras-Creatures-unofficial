package andrews.pandoras_creatures.registry.entity;

public final class PCEntitySpawnRules {
    private static final int MAX_ARACHNON_BRIGHTNESS = 7;
    private static final int MIN_BUFFLON_BRIGHTNESS = 9;
    private static final int MIN_JUNGLE_ARCHVINE_Y = 62;
    private static final int MIN_NETHER_ARCHVINE_Y = 40;
    private static final int MIN_WARM_OCEAN_CRAB_Y = 30;
    private static final int MAX_WARM_OCEAN_CRAB_Y = 60;
    private static final int MIN_BEACH_CRAB_Y = 56;
    private static final int MAX_BEACH_CRAB_Y = 70;

    private PCEntitySpawnRules() {
    }

    public static boolean canSpawnSeahorse(boolean inWater) {
        return inWater;
    }

    public static boolean canSpawnCrab(boolean inBeach, boolean inWarmOcean, int y, boolean sandBelow, boolean grassBelow) {
        return isAllowedCrabHeight(inBeach, inWarmOcean, y) && (sandBelow || grassBelow);
    }

    public static boolean canSpawnHostileGroundMob(boolean hostileDifficulty) {
        return hostileDifficulty;
    }

    public static boolean canSpawnArachnon(boolean hostileDifficulty, int brightness) {
        return canSpawnHostileGroundMob(hostileDifficulty) && brightness <= MAX_ARACHNON_BRIGHTNESS;
    }

    public static boolean canSpawnAcidicArchvine(boolean hostileDifficulty,
            boolean inJungleBiome,
            boolean inNetherBiome,
            int y,
            boolean blockAtPosIsAir,
            boolean blockAboveIsAir,
            boolean validCeiling,
            boolean hasVerticalAirShaft) {
        return canSpawnHostileGroundMob(hostileDifficulty)
                && isAllowedAcidicArchvineHeight(inJungleBiome, inNetherBiome, y)
                && blockAtPosIsAir
                && blockAboveIsAir
                && validCeiling
                && hasVerticalAirShaft;
    }

    public static boolean canSpawnBufflon(int brightness, boolean grassBelow) {
        return brightness >= MIN_BUFFLON_BRIGHTNESS && grassBelow;
    }

    static boolean isAllowedCrabHeight(boolean inBeach, boolean inWarmOcean, int y) {
        if (inBeach && (y > MAX_BEACH_CRAB_Y || y < MIN_BEACH_CRAB_Y)) {
            return false;
        }
        if (inWarmOcean && (y > MAX_WARM_OCEAN_CRAB_Y || y < MIN_WARM_OCEAN_CRAB_Y)) {
            return false;
        }
        return true;
    }

    static boolean isAllowedAcidicArchvineHeight(boolean inJungleBiome, boolean inNetherBiome, int y) {
        if (inJungleBiome && y < MIN_JUNGLE_ARCHVINE_Y) {
            return false;
        }
        if (inNetherBiome && y < MIN_NETHER_ARCHVINE_Y) {
            return false;
        }
        return true;
    }
}
