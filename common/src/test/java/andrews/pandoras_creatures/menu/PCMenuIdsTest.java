package andrews.pandoras_creatures.menu;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PCMenuIdsTest {
    @Test
    void menuPathsStayUnique() {
        Set<String> uniquePaths = Set.copyOf(PCMenuIds.allPaths());

        assertEquals(PCMenuIds.allPaths().size(), uniquePaths.size());
    }

    @Test
    void menuPathsKeepExpectedSuffix() {
        assertTrue(PCMenuIds.allPaths().stream().allMatch(path -> path.endsWith("_menu")));
    }
}
