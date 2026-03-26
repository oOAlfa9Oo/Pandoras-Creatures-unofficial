package andrews.pandoras_creatures.entities.bufflon;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * Centralizes mapping between attachment items and their semantic type.
 */
public final class BufflonBackAttachmentItems {
    private static final String PLAYER_SEATS_ITEM_ID = "bufflon_player_seats";
    private static final String SMALL_STORAGE_ITEM_ID = "bufflon_small_storage";
    private static final String LARGE_STORAGE_ITEM_ID = "bufflon_large_storage";

    private BufflonBackAttachmentItems() {
    }

    public static BufflonBackAttachmentType getType(Item item) {
        if (item == PandorasCreaturesCommon.platform().registry().item(PLAYER_SEATS_ITEM_ID)) {
            return BufflonBackAttachmentType.PLAYER_SEATS;
        }
        if (item == PandorasCreaturesCommon.platform().registry().item(SMALL_STORAGE_ITEM_ID)) {
            return BufflonBackAttachmentType.SMALL_STORAGE;
        }
        if (item == PandorasCreaturesCommon.platform().registry().item(LARGE_STORAGE_ITEM_ID)) {
            return BufflonBackAttachmentType.LARGE_STORAGE;
        }
        return BufflonBackAttachmentType.NONE;
    }

    public static BufflonBackAttachmentType getType(ItemStack stack) {
        return getType(stack.getItem());
    }

    public static boolean isSupported(ItemStack stack) {
        return getType(stack) != BufflonBackAttachmentType.NONE;
    }
}
