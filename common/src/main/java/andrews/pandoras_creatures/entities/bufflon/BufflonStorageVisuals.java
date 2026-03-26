package andrews.pandoras_creatures.entities.bufflon;

/**
 * Shared thresholds for rendering Bufflon storage cargo on the model.
 */
public final class BufflonStorageVisuals {
    private static final int[] SMALL_STORAGE_THRESHOLDS = {5, 14, 23};
    private static final int[] LARGE_STORAGE_THRESHOLDS = {5, 14, 23, 32, 41, 50};

    private BufflonStorageVisuals() {
    }

    public static int getVisibleBoxCount(BufflonBackAttachmentType attachmentType, int occupiedStorageSlots) {
        return switch (attachmentType) {
            case SMALL_STORAGE -> countReachedThresholds(occupiedStorageSlots, SMALL_STORAGE_THRESHOLDS);
            case LARGE_STORAGE -> countReachedThresholds(occupiedStorageSlots, LARGE_STORAGE_THRESHOLDS);
            default -> 0;
        };
    }

    private static int countReachedThresholds(int occupiedStorageSlots, int[] thresholds) {
        int visibleBoxes = 0;
        for (int threshold : thresholds) {
            if (occupiedStorageSlots >= threshold) {
                visibleBoxes++;
            }
        }
        return visibleBoxes;
    }
}
