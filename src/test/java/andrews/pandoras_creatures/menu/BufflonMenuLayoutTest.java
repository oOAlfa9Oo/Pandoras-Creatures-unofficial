package andrews.pandoras_creatures.menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BufflonMenuLayoutTest {

    @Test
    void storageBackgroundWrapsStorageGridOrigin() {
        assertEquals(BufflonMenuLayout.STORAGE_SLOT_X - 1, BufflonMenuLayout.STORAGE_BACKGROUND_X);
        assertEquals(BufflonMenuLayout.STORAGE_SLOT_Y - 1, BufflonMenuLayout.STORAGE_BACKGROUND_Y);
    }

    @Test
    void menuDimensionsCanContainStorageGrid() {
        int storageWidth = BufflonMenuLayout.STORAGE_SLOT_X
                + (BufflonMenuLayout.STORAGE_COLUMNS * BufflonMenuLayout.SLOT_SPACING);
        int storageHeight = BufflonMenuLayout.STORAGE_SLOT_Y
                + (BufflonMenuLayout.STORAGE_ROWS * BufflonMenuLayout.SLOT_SPACING);

        assertTrue(BufflonMenuLayout.IMAGE_WIDTH >= storageWidth);
        assertTrue(BufflonMenuLayout.IMAGE_HEIGHT >= storageHeight);
    }
}
