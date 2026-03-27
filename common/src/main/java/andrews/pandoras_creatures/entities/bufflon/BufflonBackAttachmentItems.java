package andrews.pandoras_creatures.entities.bufflon;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.registry.item.PCItemIds;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * Centralizes mapping between attachment items and their semantic type.
 */
public final class BufflonBackAttachmentItems {
    private BufflonBackAttachmentItems() {
    }

    public static BufflonBackAttachmentType getType(Item item) {
        if (item == PandorasCreaturesCommon.platform().registry().item(PCItemIds.BUFFLON_PLAYER_SEATS)) {
            return BufflonBackAttachmentType.PLAYER_SEATS;
        }
        if (item == PandorasCreaturesCommon.platform().registry().item(PCItemIds.BUFFLON_SMALL_STORAGE)) {
            return BufflonBackAttachmentType.SMALL_STORAGE;
        }
        if (item == PandorasCreaturesCommon.platform().registry().item(PCItemIds.BUFFLON_LARGE_STORAGE)) {
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
