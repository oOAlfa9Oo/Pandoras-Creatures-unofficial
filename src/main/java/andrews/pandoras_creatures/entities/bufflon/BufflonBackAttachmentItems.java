package andrews.pandoras_creatures.entities.bufflon;

import andrews.pandoras_creatures.registry.PCItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * Centralizes mapping between attachment items and their semantic type.
 */
public final class BufflonBackAttachmentItems {
    private BufflonBackAttachmentItems() {
    }

    public static BufflonBackAttachmentType getType(Item item) {
        if (item == PCItems.BUFFLON_PLAYER_SEATS.get()) {
            return BufflonBackAttachmentType.PLAYER_SEATS;
        }
        if (item == PCItems.BUFFLON_SMALL_STORAGE.get()) {
            return BufflonBackAttachmentType.SMALL_STORAGE;
        }
        if (item == PCItems.BUFFLON_LARGE_STORAGE.get()) {
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
