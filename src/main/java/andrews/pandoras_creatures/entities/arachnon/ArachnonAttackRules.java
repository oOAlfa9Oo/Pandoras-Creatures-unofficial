package andrews.pandoras_creatures.entities.arachnon;

public final class ArachnonAttackRules {
    public static final byte ATTACK_EVENT_ID = 4;
    public static final int ATTACK_TIMER_TICKS = 10;
    private static final int ATTACK_DAMAGE_MIN = 6;
    private static final int ATTACK_DAMAGE_ROLL = 5;

    private ArachnonAttackRules() {
    }

    public static int attackDamageFromRoll(int roll) {
        return ATTACK_DAMAGE_MIN + Math.floorMod(roll, ATTACK_DAMAGE_ROLL);
    }

    public static int tickAttackTimer(int attackTimer) {
        return Math.max(attackTimer - 1, 0);
    }
}
