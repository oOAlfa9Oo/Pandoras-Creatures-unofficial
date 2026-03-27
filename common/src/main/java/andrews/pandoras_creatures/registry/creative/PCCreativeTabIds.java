package andrews.pandoras_creatures.registry.creative;

import andrews.pandoras_creatures.util.Reference;

import java.util.List;

/**
 * Shared creative tab ids that should stay stable across loader adapters.
 */
public final class PCCreativeTabIds {
    public static final String PANDORAS_CREATURES = Reference.MODID;
    public static final String PANDORAS_CREATURES_TRANSLATION_KEY = "itemGroup." + Reference.MODID;

    private PCCreativeTabIds() {
    }

    public static List<String> allPaths() {
        return List.of(PANDORAS_CREATURES);
    }
}
