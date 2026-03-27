package andrews.pandoras_creatures.registry.item;

import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PCItemIdsTest {
    @Test
    void itemPathsStayUnique() {
        Set<String> uniquePaths = Set.copyOf(PCItemIds.allPaths());

        assertEquals(PCItemIds.allPaths().size(), uniquePaths.size());
    }

    @Test
    void spawnEggIdsDeriveFromEntityIds() {
        assertEquals(PCItemIds.CRAB_SPAWN_EGG, PCItemIds.spawnEggId(PCEntityIds.CRAB));
        assertEquals(PCItemIds.SEAHORSE_SPAWN_EGG, PCItemIds.spawnEggId(PCEntityIds.SEAHORSE));
        assertEquals(PCItemIds.END_TROLL_SPAWN_EGG, PCItemIds.spawnEggId(PCEntityIds.END_TROLL));
    }

    @Test
    void itemPathsStayLowerCaseAndLoaderNeutral() {
        assertTrue(PCItemIds.allPaths().stream().allMatch(path -> path.equals(path.toLowerCase())));
        assertTrue(PCItemIds.allPaths().stream().allMatch(path -> !path.contains(":")));
    }
}
