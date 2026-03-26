package andrews.pandoras_creatures.entities.bufflon;

/**
 * Shared bounce curve for Bufflon passengers.
 */
public final class BufflonPassengerMotion {
    private static final float IDLE_BOUNCE_HEIGHT = 0.03F;
    private static final float IDLE_BOUNCE_SPEED = 0.24F;
    private static final float WALK_BOUNCE_HEIGHT = 0.05F;
    private static final float WALK_BOUNCE_SPEED = 0.45F;
    private static final float BASE_OFFSET = 0.08F;

    private BufflonPassengerMotion() {
    }

    public static float getVerticalOffset(boolean moving, int tickCount, float walkAnimationPosition, float walkAnimationSpeed) {
        if (!moving) {
            return (float) (Math.sin(tickCount * IDLE_BOUNCE_SPEED) * IDLE_BOUNCE_HEIGHT - IDLE_BOUNCE_HEIGHT) + BASE_OFFSET;
        }

        return (float) (Math.sin(walkAnimationPosition * WALK_BOUNCE_SPEED - 0.04F) * walkAnimationSpeed * WALK_BOUNCE_HEIGHT
                - walkAnimationSpeed * WALK_BOUNCE_HEIGHT) + BASE_OFFSET;
    }
}
