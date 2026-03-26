package andrews.pandoras_creatures.entities.acidic_archvine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AcidicArchvineAttackStateTest {
    @Test
    void mapsKnownEntityEventIds() {
        assertEquals(AcidicArchvineAttackState.IDLE, AcidicArchvineAttackState.fromEventId((byte) 4));
        assertEquals(AcidicArchvineAttackState.GRABBING, AcidicArchvineAttackState.fromEventId((byte) 5));
        assertEquals(AcidicArchvineAttackState.CHEWING, AcidicArchvineAttackState.fromEventId((byte) 6));
    }

    @Test
    void returnsNullForUnknownEntityEventId() {
        assertNull(AcidicArchvineAttackState.fromEventId((byte) 99));
    }
}
