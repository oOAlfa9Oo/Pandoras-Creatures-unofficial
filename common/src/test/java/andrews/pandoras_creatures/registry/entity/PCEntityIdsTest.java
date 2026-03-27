package andrews.pandoras_creatures.registry.entity;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PCEntityIdsTest {
    @Test
    void entityPathsStayUnique() {
        Set<String> uniquePaths = Set.copyOf(PCEntityIds.allPaths());

        assertEquals(PCEntityIds.allPaths().size(), uniquePaths.size());
    }

    @Test
    void entityPathsStayLowerCaseAndLoaderNeutral() {
        assertTrue(PCEntityIds.allPaths().stream().allMatch(path -> path.equals(path.toLowerCase())));
        assertTrue(PCEntityIds.allPaths().stream().allMatch(path -> !path.contains(":")));
    }
}
