package andrews.pandoras_creatures.entities.bufflon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BufflonCombatRulesTest {

    @Test
    void protectOwnerRequiresTamedUnsatCombatState() {
        assertTrue(BufflonCombatPolicy.canProtectOwner(true, false, true));
        assertFalse(BufflonCombatPolicy.canProtectOwner(false, false, true));
        assertFalse(BufflonCombatPolicy.canProtectOwner(true, true, true));
        assertFalse(BufflonCombatPolicy.canProtectOwner(true, false, false));
    }

    @Test
    void adjustsIncomingDamageOnlyForNonPlayerNonArrowAttackers() {
        assertEquals(3.5F, BufflonCombatPolicy.getAdjustedIncomingDamage(true, false, false, 6.0F));
        assertEquals(6.0F, BufflonCombatPolicy.getAdjustedIncomingDamage(true, true, false, 6.0F));
        assertEquals(6.0F, BufflonCombatPolicy.getAdjustedIncomingDamage(true, false, true, 6.0F));
        assertEquals(6.0F, BufflonCombatPolicy.getAdjustedIncomingDamage(false, false, false, 6.0F));
    }

    @Test
    void attackTargetRulesProtectFriendlyProfiles() {
        assertTrue(BufflonCombatPolicy.shouldAttackTarget(new BufflonCombatTargetProfile(false, false, false, false, false, false)));
        assertFalse(BufflonCombatPolicy.shouldAttackTarget(new BufflonCombatTargetProfile(true, false, false, false, false, false)));
        assertFalse(BufflonCombatPolicy.shouldAttackTarget(new BufflonCombatTargetProfile(false, true, false, false, false, false)));
        assertFalse(BufflonCombatPolicy.shouldAttackTarget(new BufflonCombatTargetProfile(false, false, true, false, false, false)));
        assertFalse(BufflonCombatPolicy.shouldAttackTarget(new BufflonCombatTargetProfile(false, false, false, true, false, false)));
        assertFalse(BufflonCombatPolicy.shouldAttackTarget(new BufflonCombatTargetProfile(false, false, false, false, true, false)));
        assertFalse(BufflonCombatPolicy.shouldAttackTarget(new BufflonCombatTargetProfile(false, false, false, false, false, true)));
    }

    @Test
    void knockbackVectorMatchesEntitySeparation() {
        BufflonCombatKnockback knockback = BufflonCombatPolicy.getAttackKnockback(10.0D, 0.0D, 10.0D, 8.0D, 0.0D, 7.0D, 0.15D);

        assertEquals(-0.3D, knockback.x(), 0.0001D);
        assertEquals(0.0D, knockback.y(), 0.0001D);
        assertEquals(-0.45D, knockback.z(), 0.0001D);
    }
}
