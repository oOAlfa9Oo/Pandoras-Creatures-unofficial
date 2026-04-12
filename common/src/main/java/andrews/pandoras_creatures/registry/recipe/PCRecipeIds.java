package andrews.pandoras_creatures.registry.recipe;

import andrews.pandoras_creatures.registry.block.PCBlockIds;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.resources.Identifier;

import java.util.List;

/**
 * Shared recipe ids that should stay stable across loader adapters.
 */
public final class PCRecipeIds {
    public static final String END_TROLL_BOX = PCBlockIds.END_TROLL_BOX;
    public static final String END_TROLL_BOX_COLORING = "end_troll_box_coloring";
    public static final String END_TROLL_BOX_COLORING_GROUP = END_TROLL_BOX_COLORING;

    private PCRecipeIds() {
    }

    public static List<String> allPaths() {
        return List.of(END_TROLL_BOX, END_TROLL_BOX_COLORING);
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(Reference.MODID, path);
    }

    public static String qualified(String path) {
        return Reference.MODID + ":" + path;
    }
}
