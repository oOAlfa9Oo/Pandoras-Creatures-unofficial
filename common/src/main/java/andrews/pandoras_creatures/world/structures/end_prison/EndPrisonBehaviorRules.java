package andrews.pandoras_creatures.world.structures.end_prison;

/**
 * Version-independent gameplay contract preserved from the official End Prison.
 */
public final class EndPrisonBehaviorRules {
    public static final String VANILLA_SHIP_TEMPLATE = "minecraft:end_city/ship";
    public static final int SHIP_CHANCE_BOUND = 3;
    public static final double END_TROLL_X = 18.27531668920085D;
    public static final double END_TROLL_Y = 11.0D;
    public static final double END_TROLL_Z = 20.87783590069225D;
    public static final float END_TROLL_YAW = 272.5234F;
    public static final float END_TROLL_HEALTH = 200.0F;

    private EndPrisonBehaviorRules() {
    }

    public static boolean shouldAddShip(int randomValue) {
        return randomValue == 0;
    }

    public static float rotatedEndTrollYaw(String rotationName) {
        float rotationDegrees = switch (rotationName) {
            case "CLOCKWISE_180" -> 180.0F;
            case "COUNTERCLOCKWISE_90" -> 270.0F;
            case "CLOCKWISE_90" -> 90.0F;
            default -> 0.0F;
        };
        return wrapDegrees(END_TROLL_YAW) + rotationDegrees;
    }

    private static float wrapDegrees(float value) {
        float wrapped = value % 360.0F;
        if (wrapped >= 180.0F) {
            wrapped -= 360.0F;
        }
        if (wrapped < -180.0F) {
            wrapped += 360.0F;
        }
        return wrapped;
    }
}
