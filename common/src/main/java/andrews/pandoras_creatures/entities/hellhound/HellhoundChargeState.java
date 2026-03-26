package andrews.pandoras_creatures.entities.hellhound;

public final class HellhoundChargeState {
    public static final int IDLE = 0;
    public static final int CHARGING = 1;
    public static final byte START_EVENT_ID = 4;
    public static final byte STOP_EVENT_ID = 5;

    private HellhoundChargeState() {
    }

    public static int normalize(int state) {
        return state == CHARGING ? CHARGING : IDLE;
    }

    public static boolean isCharging(int state) {
        return normalize(state) == CHARGING;
    }
}
