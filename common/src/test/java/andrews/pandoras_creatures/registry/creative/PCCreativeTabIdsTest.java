package andrews.pandoras_creatures.registry.creative;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PCCreativeTabIdsTest {
    @Test
    void creativeTabPathsStayUniqueAndLoaderNeutral() {
        Set<String> uniquePaths = Set.copyOf(PCCreativeTabIds.allPaths());

        assertEquals(PCCreativeTabIds.allPaths().size(), uniquePaths.size());
        assertTrue(PCCreativeTabIds.allPaths().stream().allMatch(path -> path.equals(path.toLowerCase())));
        assertTrue(PCCreativeTabIds.allPaths().stream().allMatch(path -> !path.contains(":")));
    }

    @Test
    void creativeTabTranslationKeyStaysStable() {
        assertEquals("itemGroup.pandoras_creatures", PCCreativeTabIds.PANDORAS_CREATURES_TRANSLATION_KEY);
    }
}
