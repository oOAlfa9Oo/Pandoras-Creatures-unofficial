package andrews.pandoras_creatures.entities.acidic_archvine;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public final class AcidicArchvineTargetingRules {
    public static final int BITE_COOLDOWN_TICKS = 20;
    private static final double PULL_SPEED = 0.3D;
    private static final double HORIZONTAL_TARGET_RANGE = 50.0D;
    private static final double PULL_DISTANCE = 1.0D;
    private static final double TELEPORT_CAPTURE_DISTANCE = 0.55D;
    private static final double CLOSE_CAPTURE_DISTANCE = 1.5D;

    private AcidicArchvineTargetingRules() {
    }

    public static boolean isProtectedByPlantHat(boolean wearsPlantHat) {
        return wearsPlantHat;
    }

    public static boolean hasValidTarget(boolean hasTarget,
            boolean targetAlive,
            boolean creativeOrSpectator,
            boolean protectedByPlantHat) {
        return hasTarget && targetAlive && !creativeOrSpectator && !protectedByPlantHat;
    }

    public static boolean isValidTarget(LivingEntity target, boolean protectedByPlantHat) {
        boolean creativeOrSpectator = target instanceof Player player && (player.isCreative() || player.isSpectator());
        return hasValidTarget(target != null, target != null && target.isAlive(), creativeOrSpectator, protectedByPlantHat);
    }

    public static AABB createTargetableArea(AABB archvineBounds, double followDistance) {
        return archvineBounds.inflate(HORIZONTAL_TARGET_RANGE, followDistance, HORIZONTAL_TARGET_RANGE)
                .move(0, -followDistance, 0);
    }

    public static boolean isWithinFollowDistance(double distanceToSqr, double followDistance) {
        return distanceToSqr <= followDistance * followDistance;
    }

    public static boolean canHoldTarget(double distance) {
        return distance <= CLOSE_CAPTURE_DISTANCE;
    }

    public static boolean shouldPullTarget(double distance) {
        return distance > PULL_DISTANCE;
    }

    public static boolean shouldTeleportTarget(double distance) {
        return distance > TELEPORT_CAPTURE_DISTANCE;
    }

    public static double calculatePullMultiplier(double distance) {
        return PULL_SPEED / (distance + 1.0D);
    }

    public static Vec3 createPullVector(Vec3 archvinePosition, Vec3 targetPosition) {
        double distance = archvinePosition.distanceTo(targetPosition);
        if (!shouldPullTarget(distance)) {
            return Vec3.ZERO;
        }

        double multiplier = calculatePullMultiplier(distance);
        return archvinePosition.subtract(targetPosition).multiply(multiplier / 2.0D, multiplier, multiplier / 2.0D);
    }
}
