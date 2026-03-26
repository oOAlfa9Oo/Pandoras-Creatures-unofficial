package andrews.pandoras_creatures.entities.bufflon;

/**
 * Loader-agnostic Bufflon combat rules.
 */
public final class BufflonCombatPolicy {
    private BufflonCombatPolicy() {
    }

    public static boolean canProtectOwner(boolean isTamed, boolean isSitting, boolean inCombatMode) {
        return isTamed && !isSitting && inCombatMode;
    }

    public static boolean shouldInterruptSitOnDamage(boolean inCombatMode) {
        return inCombatMode;
    }

    public static float getAdjustedIncomingDamage(boolean hasAttacker, boolean attackerIsPlayer, boolean attackerIsArrow, float amount) {
        if (hasAttacker && !attackerIsPlayer && !attackerIsArrow) {
            return (amount + 1.0F) / 2.0F;
        }
        return amount;
    }

    public static boolean shouldAttackTarget(BufflonCombatTargetProfile profile) {
        return !profile.explosiveMob()
                && !profile.ownersTamedWolf()
                && !profile.ownersTamedBufflon()
                && !profile.protectedPlayer()
                && !profile.tamedHorse()
                && !profile.tamedCat();
    }

    public static BufflonCombatKnockback getAttackKnockback(
            double attackerX, double attackerY, double attackerZ,
            double targetX, double targetY, double targetZ,
            double strength
    ) {
        return new BufflonCombatKnockback(
                (attackerX - targetX) * -strength,
                0.0D,
                (attackerZ - targetZ) * -strength
        );
    }
}
