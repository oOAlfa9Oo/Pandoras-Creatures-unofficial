package andrews.pandoras_creatures.entities.bufflon;

/**
 * Semantic representation of Bufflon back attachments.
 * This replaces raw integer checks spread across entity, screen, model and menu code.
 */
public enum BufflonBackAttachmentType {
    NONE(0, 1, 0, 0),
    PLAYER_SEATS(1, 3, 0, 0),
    SMALL_STORAGE(2, 2, 3, 27),
    LARGE_STORAGE(3, 1, 6, 54);

    private final int id;
    private final int maxPassengers;
    private final int storageRows;
    private final int storageSlotCount;

    BufflonBackAttachmentType(int id, int maxPassengers, int storageRows, int storageSlotCount) {
        this.id = id;
        this.maxPassengers = maxPassengers;
        this.storageRows = storageRows;
        this.storageSlotCount = storageSlotCount;
    }

    public int getId() {
        return this.id;
    }

    public int getMaxPassengers() {
        return this.maxPassengers;
    }

    public int getStorageRows() {
        return this.storageRows;
    }

    public int getStorageSlotCount() {
        return this.storageSlotCount;
    }

    public boolean hasStorage() {
        return this.storageRows > 0;
    }

    public static BufflonBackAttachmentType fromId(int id) {
        for (BufflonBackAttachmentType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        return NONE;
    }
}
