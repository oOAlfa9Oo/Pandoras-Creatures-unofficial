package andrews.pandoras_creatures.entities.end_troll;

public final class EndTrollProjectileRules {
    private static final int HOMING_STEP_BASE = 10;
    private static final int HOMING_STEP_MULTIPLIER = 10;

    private EndTrollProjectileRules() {
    }

    public static EndTrollProjectileKind selectProjectileKind(int randomIndex) {
        return switch (Math.floorMod(randomIndex, 3)) {
            case 0 -> EndTrollProjectileKind.POISON;
            case 1 -> EndTrollProjectileKind.WITHER;
            default -> EndTrollProjectileKind.DAMAGE;
        };
    }

    public static EndTrollProjectileLaunch createLaunchVector(float headYawDegrees, float xRotDegrees) {
        double yawRadians = Math.toRadians(headYawDegrees);
        double pitchRadians = Math.toRadians(xRotDegrees);

        return new EndTrollProjectileLaunch(
                -Math.sin(yawRadians) * Math.cos(pitchRadians),
                -Math.sin(pitchRadians),
                Math.cos(yawRadians) * Math.cos(pitchRadians)
        );
    }

    public static int computeStepCount(int randomMultiplier) {
        return HOMING_STEP_BASE + Math.max(0, randomMultiplier) * HOMING_STEP_MULTIPLIER;
    }
}
