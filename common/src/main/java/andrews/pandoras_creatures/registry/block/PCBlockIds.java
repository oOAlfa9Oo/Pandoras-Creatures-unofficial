package andrews.pandoras_creatures.registry.block;

import java.util.ArrayList;
import java.util.List;

/**
 * Shared block ids that should remain stable across loader adapters.
 */
public final class PCBlockIds {
    public static final String ARACHNON_CRYSTAL = "arachnon_crystal";
    public static final String HORSETAIL = "horsetail";
    public static final String DHANIA = "dhania";
    public static final String HILL_BLOOM = "hill_bloom";
    public static final String END_TROLL_BOX = "end_troll_box";
    public static final String PANDORIC_SHARD = "pandoric_shard";

    private PCBlockIds() {
    }

    public static String endTrollBox(String colorName) {
        return PCEndTrollBoxNaming.blockName(colorName);
    }

    public static List<String> allPaths() {
        List<String> paths = new ArrayList<>();
        paths.add(ARACHNON_CRYSTAL);
        paths.add(HORSETAIL);
        paths.add(DHANIA);
        paths.add(HILL_BLOOM);
        paths.add(END_TROLL_BOX);
        paths.add(PANDORIC_SHARD);
        PCEndTrollBoxNaming.orderedColorNames().stream()
                .map(PCBlockIds::endTrollBox)
                .forEach(paths::add);
        return List.copyOf(paths);
    }
}
