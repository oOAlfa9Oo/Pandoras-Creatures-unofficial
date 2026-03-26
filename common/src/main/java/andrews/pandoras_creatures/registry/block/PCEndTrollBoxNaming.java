package andrews.pandoras_creatures.registry.block;

import java.util.List;

public final class PCEndTrollBoxNaming {
    private static final List<String> ORDERED_COLOR_NAMES = List.of(
            "white",
            "orange",
            "magenta",
            "light_blue",
            "yellow",
            "lime",
            "pink",
            "gray",
            "light_gray",
            "cyan",
            "purple",
            "blue",
            "brown",
            "green",
            "red",
            "black"
    );

    private PCEndTrollBoxNaming() {
    }

    public static List<String> orderedColorNames() {
        return ORDERED_COLOR_NAMES;
    }

    public static String blockName(String colorName) {
        return colorName == null ? "end_troll_box" : colorName + "_end_troll_box";
    }

    public static String texturePath(String colorName) {
        return colorName == null
                ? "textures/tile/end_troll_box.png"
                : "textures/tile/" + colorName + "_end_troll_box.png";
    }
}
