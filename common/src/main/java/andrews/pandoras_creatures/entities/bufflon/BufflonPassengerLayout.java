package andrews.pandoras_creatures.entities.bufflon;

/**
 * Centralizes seat positioning rules for Bufflon passengers.
 */
public final class BufflonPassengerLayout {
    private static final float FIRST_PASSENGER_X = 0.95F;
    private static final float FIRST_PASSENGER_Y = 2.3F;
    private static final float UNSADDLED_FRONT_PASSENGER_Y_ADJUSTMENT = -0.08F;

    private static final float SECOND_PASSENGER_X = -0.9F;
    private static final float SECOND_PASSENGER_Y = 2.1F;

    private static final float THIRD_PASSENGER_X = -1.59F;
    private static final float THIRD_PASSENGER_Y = 2.0F;

    private static final float MOVING_REAR_PASSENGER_BOUNCE_MULTIPLIER = 1.4F;

    private BufflonPassengerLayout() {
    }

    public static BufflonPassengerOffset getOffset(int passengerIndex, boolean saddled, boolean moving, float passengerMovement) {
        return switch (passengerIndex) {
            case 0 -> firstPassengerOffset(saddled, passengerMovement);
            case 1 -> rearPassengerOffset(SECOND_PASSENGER_X, SECOND_PASSENGER_Y, moving, passengerMovement);
            case 2 -> rearPassengerOffset(THIRD_PASSENGER_X, THIRD_PASSENGER_Y, moving, passengerMovement);
            default -> new BufflonPassengerOffset(0.0F, 0.0F, false);
        };
    }

    private static BufflonPassengerOffset firstPassengerOffset(boolean saddled, float passengerMovement) {
        float y = FIRST_PASSENGER_Y - passengerMovement;
        if (!saddled) {
            y += UNSADDLED_FRONT_PASSENGER_Y_ADJUSTMENT;
        }
        return new BufflonPassengerOffset(FIRST_PASSENGER_X, y, false);
    }

    private static BufflonPassengerOffset rearPassengerOffset(float x, float baseY, boolean moving, float passengerMovement) {
        float adjustedMovement = moving ? passengerMovement * MOVING_REAR_PASSENGER_BOUNCE_MULTIPLIER : passengerMovement;
        return new BufflonPassengerOffset(x, baseY - adjustedMovement, true);
    }
}
