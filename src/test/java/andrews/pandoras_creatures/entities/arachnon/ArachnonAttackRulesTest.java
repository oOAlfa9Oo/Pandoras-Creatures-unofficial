package andrews.pandoras_creatures.entities.arachnon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArachnonAttackRulesTest {
    @Test
    void computesAttackDamageFromRoll() {
        assertEquals(6, ArachnonAttackRules.attackDamageFromRoll(0));
        assertEquals(10, ArachnonAttackRules.attackDamageFromRoll(4));
        assertEquals(7, ArachnonAttackRules.attackDamageFromRoll(6));
    }

    @Test
    void ticksAttackTimerDownWithoutGoingNegative() {
        assertEquals(9, ArachnonAttackRules.tickAttackTimer(10));
        assertEquals(0, ArachnonAttackRules.tickAttackTimer(0));
    }
}
