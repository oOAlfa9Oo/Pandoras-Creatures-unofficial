package andrews.pandoras_creatures.menu;

/**
 * Shared layout constants for the Bufflon menu and screen.
 * Slot positions are item-icon coordinates, not the outer slot border.
 */
public final class BufflonMenuLayout {
    public static final int IMAGE_WIDTH = 256;
    public static final int IMAGE_HEIGHT = 226;

    public static final int TITLE_LABEL_X = 11;
    public static final int TITLE_LABEL_Y = 8;
    public static final int PLAYER_INVENTORY_LABEL_X = 48;
    public static final int PLAYER_INVENTORY_LABEL_Y = 130;

    public static final int SLOT_SPACING = 18;
    public static final int STORAGE_ROWS = 6;
    public static final int STORAGE_COLUMNS = 9;

    public static final int SADDLE_SLOT_X = 23;
    public static final int SADDLE_SLOT_Y = 102;
    public static final int BACK_ATTACHMENT_SLOT_X = 51;
    public static final int BACK_ATTACHMENT_SLOT_Y = 102;

    public static final int STORAGE_SLOT_X = 85;
    public static final int STORAGE_SLOT_Y = 20;
    public static final int STORAGE_BACKGROUND_X = STORAGE_SLOT_X - 1;
    public static final int STORAGE_BACKGROUND_Y = STORAGE_SLOT_Y - 1;

    public static final int PLAYER_INVENTORY_X = 48;
    public static final int PLAYER_INVENTORY_Y = 142;
    public static final int HOTBAR_Y = 200;

    public static final int ENTITY_RENDER_X = 46;
    public static final int ENTITY_RENDER_Y = 76;
    public static final int ENTITY_RENDER_MOUSE_Y = 20;
    public static final int ENTITY_RENDER_SIZE = 15;
    public static final float ENTITY_RENDER_SCALE = 0.0625F;

    public static final int LEFT_BUTTON_X = 9;
    public static final int SIT_BUTTON_Y = 138;
    public static final int FOLLOW_BUTTON_Y = 166;
    public static final int MOVE_FREELY_BUTTON_Y = 194;

    public static final int RIGHT_BUTTON_X = 221;
    public static final int COMBAT_BUTTON_Y = 148;
    public static final int PEACEFUL_BUTTON_Y = 188;

    private BufflonMenuLayout() {
    }
}
