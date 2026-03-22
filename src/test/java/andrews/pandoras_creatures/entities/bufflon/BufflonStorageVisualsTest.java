package andrews.pandoras_creatures.entities.bufflon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BufflonStorageVisualsTest {

    @Test
    void smallStorageRevealsThreeBoxesAcrossConfiguredThresholds() {
        assertEquals(0, BufflonStorageVisuals.getVisibleBoxCount(BufflonBackAttachmentType.SMALL_STORAGE, 4));
        assertEquals(1, BufflonStorageVisuals.getVisibleBoxCount(BufflonBackAttachmentType.SMALL_STORAGE, 5));
        assertEquals(2, BufflonStorageVisuals.getVisibleBoxCount(BufflonBackAttachmentType.SMALL_STORAGE, 14));
        assertEquals(3, BufflonStorageVisuals.getVisibleBoxCount(BufflonBackAttachmentType.SMALL_STORAGE, 27));
    }

    @Test
    void largeStorageRevealsSixBoxesAcrossConfiguredThresholds() {
        assertEquals(0, BufflonStorageVisuals.getVisibleBoxCount(BufflonBackAttachmentType.LARGE_STORAGE, 0));
        assertEquals(1, BufflonStorageVisuals.getVisibleBoxCount(BufflonBackAttachmentType.LARGE_STORAGE, 5));
        assertEquals(3, BufflonStorageVisuals.getVisibleBoxCount(BufflonBackAttachmentType.LARGE_STORAGE, 23));
        assertEquals(6, BufflonStorageVisuals.getVisibleBoxCount(BufflonBackAttachmentType.LARGE_STORAGE, 54));
    }

    @Test
    void nonStorageAttachmentsNeverRevealBoxes() {
        assertEquals(0, BufflonStorageVisuals.getVisibleBoxCount(BufflonBackAttachmentType.NONE, 99));
        assertEquals(0, BufflonStorageVisuals.getVisibleBoxCount(BufflonBackAttachmentType.PLAYER_SEATS, 99));
    }
}
