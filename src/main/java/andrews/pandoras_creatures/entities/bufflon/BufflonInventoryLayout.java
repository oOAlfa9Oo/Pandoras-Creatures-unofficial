package andrews.pandoras_creatures.entities.bufflon;

/**
 * Shared Bufflon inventory slot semantics used by entity, menu and slot logic.
 */
public final class BufflonInventoryLayout {
    public static final int SADDLE_SLOT = 0;
    public static final int BACK_ATTACHMENT_SLOT = 1;
    public static final int FIRST_STORAGE_SLOT = 2;
    public static final int EQUIPMENT_SLOT_COUNT = FIRST_STORAGE_SLOT;

    private BufflonInventoryLayout() {
    }

    public static int getAccessibleSlotCount(BufflonBackAttachmentType attachmentType) {
        return FIRST_STORAGE_SLOT + attachmentType.getStorageSlotCount();
    }

    public static int getTotalSlotCount() {
        return getAccessibleSlotCount(BufflonBackAttachmentType.LARGE_STORAGE);
    }

    public static boolean isStorageSlot(int slotIndex) {
        return slotIndex >= FIRST_STORAGE_SLOT;
    }

    public static boolean isStorageSlotActive(BufflonBackAttachmentType attachmentType, int slotIndex) {
        return attachmentType.hasStorage()
                && isStorageSlot(slotIndex)
                && slotIndex < getAccessibleSlotCount(attachmentType);
    }
}
