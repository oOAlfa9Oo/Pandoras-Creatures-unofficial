package andrews.pandoras_creatures.entities.crab;

public final class CrabBehaviorRules {
    public static final String SPECIAL_HAT_NAME = "fredrick";
    public static final double PARTY_JUKEBOX_RANGE = 4 * 3.46D;

    private CrabBehaviorRules() {
    }

    public static boolean shouldBeUnderwater(boolean blockIsWater) {
        return blockIsWater;
    }

    public static boolean shouldKeepPartying(boolean hasJukeboxPosition, boolean withinRange, boolean blockIsJukebox) {
        return hasJukeboxPosition && withinRange && blockIsJukebox;
    }

    public static boolean showsHat(String customName) {
        return SPECIAL_HAT_NAME.equals(customName);
    }
}
