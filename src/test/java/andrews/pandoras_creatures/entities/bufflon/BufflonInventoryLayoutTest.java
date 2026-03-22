package andrews.pandoras_creatures.entities.bufflon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BufflonInventoryLayoutTest {

    @Test
    void exposesAccessibleSlotCountsPerAttachment() {
        assertEquals(2, BufflonInventoryLayout.getAccessibleSlotCount(BufflonBackAttachmentType.NONE));
        assertEquals(2, BufflonInventoryLayout.getAccessibleSlotCount(BufflonBackAttachmentType.PLAYER_SEATS));
        assertEquals(29, BufflonInventoryLayout.getAccessibleSlotCount(BufflonBackAttachmentType.SMALL_STORAGE));
        assertEquals(56, BufflonInventoryLayout.getAccessibleSlotCount(BufflonBackAttachmentType.LARGE_STORAGE));
        assertEquals(56, BufflonInventoryLayout.getTotalSlotCount());
    }

    @Test
    void tracksWhichStorageSlotsAreActive() {
        assertFalse(BufflonInventoryLayout.isStorageSlotActive(BufflonBackAttachmentType.NONE, 2));
        assertFalse(BufflonInventoryLayout.isStorageSlotActive(BufflonBackAttachmentType.SMALL_STORAGE, 1));
        assertTrue(BufflonInventoryLayout.isStorageSlotActive(BufflonBackAttachmentType.SMALL_STORAGE, 28));
        assertFalse(BufflonInventoryLayout.isStorageSlotActive(BufflonBackAttachmentType.SMALL_STORAGE, 29));
        assertTrue(BufflonInventoryLayout.isStorageSlotActive(BufflonBackAttachmentType.LARGE_STORAGE, 55));
    }
}
