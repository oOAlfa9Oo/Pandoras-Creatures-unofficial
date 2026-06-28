package andrews.pandoras_creatures.registry.block;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PCBlockIdsTest {
    @Test
    void blockPathsStayUnique() {
        Set<String> uniquePaths = Set.copyOf(PCBlockIds.allPaths());

        assertEquals(PCBlockIds.allPaths().size(), uniquePaths.size());
    }

    @Test
    void blockPathsStayLowerCaseAndLoaderNeutral() {
        assertTrue(PCBlockIds.allPaths().stream().allMatch(path -> path.equals(path.toLowerCase())));
        assertTrue(PCBlockIds.allPaths().stream().allMatch(path -> !path.contains(":")));
    }

    @Test
    void endTrollBoxesReuseNamingConventions() {
        assertEquals(PCBlockIds.END_TROLL_BOX, PCBlockIds.endTrollBox(null));
        assertEquals("light_blue_end_troll_box", PCBlockIds.endTrollBox("light_blue"));
    }

    @Test
    void blockCatalogAlwaysContainsBaseIds() {
        // Las 6 ids base deben estar siempre presentes (las cajas de color derivadas
        // se validan aparte en PCEndTrollBoxNamingTest). Borrar una base rompe este test.
        assertTrue(PCBlockIds.allPaths().containsAll(Set.of(
                "arachnon_crystal",
                "horsetail",
                "dhania",
                "hill_bloom",
                "end_troll_box",
                "pandoric_shard")));
    }
}
