package andrews.pandoras_creatures.entities.bufflon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BufflonBackAttachmentTypeTest {

    @Test
    void resolvesKnownIds() {
        assertSame(BufflonBackAttachmentType.NONE, BufflonBackAttachmentType.fromId(0));
        assertSame(BufflonBackAttachmentType.PLAYER_SEATS, BufflonBackAttachmentType.fromId(1));
        assertSame(BufflonBackAttachmentType.SMALL_STORAGE, BufflonBackAttachmentType.fromId(2));
        assertSame(BufflonBackAttachmentType.LARGE_STORAGE, BufflonBackAttachmentType.fromId(3));
    }

    @Test
    void defaultsUnknownIdsToNone() {
        assertSame(BufflonBackAttachmentType.NONE, BufflonBackAttachmentType.fromId(99));
    }

    @Test
    void exposesStorageAndPassengerCapabilities() {
        assertEquals(3, BufflonBackAttachmentType.PLAYER_SEATS.getMaxPassengers());
        assertEquals(27, BufflonBackAttachmentType.SMALL_STORAGE.getStorageSlotCount());
        assertEquals(6, BufflonBackAttachmentType.LARGE_STORAGE.getStorageRows());
        assertTrue(BufflonBackAttachmentType.SMALL_STORAGE.hasStorage());
    }
}
