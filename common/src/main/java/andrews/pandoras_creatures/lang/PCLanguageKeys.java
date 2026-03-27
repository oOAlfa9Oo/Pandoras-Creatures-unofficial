package andrews.pandoras_creatures.lang;

import andrews.pandoras_creatures.registry.block.PCBlockIds;
import andrews.pandoras_creatures.registry.item.PCItemIds;
import andrews.pandoras_creatures.util.Reference;

/**
 * Shared translation keys that should stay stable across runtime and datagen.
 */
public final class PCLanguageKeys {
    public static final String ACIDIC_ARCHVINE_SPAWN_EGG_TOOLTIP = itemTooltip(PCItemIds.ACIDIC_ARCHVINE_SPAWN_EGG);
    public static final String ARACHNON_HAMMER_TOOLTIP = itemTooltip(PCItemIds.ARACHNON_HAMMER);
    public static final String PLANT_HAT_TOOLTIP = itemTooltip(PCItemIds.PLANT_HAT);
    public static final String END_TROLL_BOX_TOOLTIP = blockTooltip(PCBlockIds.END_TROLL_BOX);
    public static final String END_TROLL_BOX_CONTAINER = container(PCBlockIds.END_TROLL_BOX);

    private PCLanguageKeys() {
    }

    public static String item(String path) {
        return key("item", path);
    }

    public static String block(String path) {
        return key("block", path);
    }

    public static String entity(String path) {
        return key("entity", path);
    }

    public static String container(String path) {
        return key("container", path);
    }

    public static String guiButton(String path) {
        return key("gui.button", path);
    }

    public static String chat(String path) {
        return key("chat", path);
    }

    public static String itemTooltip(String path) {
        return item(path) + ".tooltip";
    }

    public static String blockTooltip(String path) {
        return block(path) + ".tooltip";
    }

    private static String key(String type, String path) {
        return type + "." + Reference.MODID + "." + path;
    }
}
