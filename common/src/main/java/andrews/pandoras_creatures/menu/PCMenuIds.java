package andrews.pandoras_creatures.menu;

import java.util.List;

/**
 * Shared menu ids that should stay stable across loader adapters.
 */
public final class PCMenuIds {
    public static final String BUFFLON = "bufflon_menu";
    public static final String END_TROLL_BOX = "end_troll_box_menu";

    private PCMenuIds() {
    }

    public static List<String> allPaths() {
        return List.of(BUFFLON, END_TROLL_BOX);
    }
}
