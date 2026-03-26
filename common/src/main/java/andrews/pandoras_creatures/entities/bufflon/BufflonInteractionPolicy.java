package andrews.pandoras_creatures.entities.bufflon;

/**
 * Pure interaction rules for Bufflon player actions.
 */
public final class BufflonInteractionPolicy {
    private BufflonInteractionPolicy() {
    }

    public static MobInteractAction resolveMobInteractAction(boolean isTamed, boolean holdingHerbBundle, boolean itemEmpty,
                                                             boolean isSaddled, boolean hasBackAttachment,
                                                             boolean holdingSaddleItem, boolean holdingAttachmentItem) {
        if (holdingHerbBundle) {
            return MobInteractAction.HANDLE_HERB_BUNDLE;
        }
        if (shouldOpenEquipmentMenu(isTamed, isSaddled, hasBackAttachment, holdingSaddleItem, holdingAttachmentItem)) {
            return MobInteractAction.OPEN_EQUIPMENT_MENU;
        }
        if (itemEmpty) {
            return MobInteractAction.HANDLE_EMPTY_HAND;
        }
        return MobInteractAction.PASS_TO_SUPER;
    }

    public static OwnedInteractionAction resolveOwnedInteractionAction(boolean isTamed, boolean secondaryUseActive) {
        if (!isTamed) {
            return OwnedInteractionAction.NONE;
        }
        return secondaryUseActive ? OwnedInteractionAction.OPEN_MENU : OwnedInteractionAction.MOUNT;
    }

    private static boolean shouldOpenEquipmentMenu(boolean isTamed, boolean isSaddled, boolean hasBackAttachment,
                                                   boolean holdingSaddleItem, boolean holdingAttachmentItem) {
        if (!isTamed) {
            return false;
        }
        if (!isSaddled && holdingSaddleItem) {
            return true;
        }
        return !hasBackAttachment && holdingAttachmentItem;
    }

    public enum MobInteractAction {
        HANDLE_HERB_BUNDLE,
        OPEN_EQUIPMENT_MENU,
        HANDLE_EMPTY_HAND,
        PASS_TO_SUPER
    }

    public enum OwnedInteractionAction {
        NONE,
        OPEN_MENU,
        MOUNT
    }
}
