package andrews.pandoras_creatures.registry.recipe;

import andrews.pandoras_creatures.registry.block.PCBlockIds;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PCRecipeIdsTest {
    @Test
    void recipePathsStayUniqueAndLoaderNeutral() {
        Set<String> uniquePaths = Set.copyOf(PCRecipeIds.allPaths());

        assertEquals(PCRecipeIds.allPaths().size(), uniquePaths.size());
        assertTrue(PCRecipeIds.allPaths().stream().allMatch(path -> path.equals(path.toLowerCase())));
        assertTrue(PCRecipeIds.allPaths().stream().allMatch(path -> !path.contains(":")));
    }

    @Test
    void endTrollBoxRecipeIdStaysAlignedWithBlockId() {
        assertEquals(PCBlockIds.END_TROLL_BOX, PCRecipeIds.END_TROLL_BOX);
        assertEquals("pandoras_creatures:end_troll_box_coloring", PCRecipeIds.qualified(PCRecipeIds.END_TROLL_BOX_COLORING));
    }

    @Test
    void recipeCatalogContainsExactlyExpectedIds() {
        assertEquals(
                Set.of("end_troll_box", "end_troll_box_coloring"),
                Set.copyOf(PCRecipeIds.allPaths()));
        assertEquals(2, PCRecipeIds.allPaths().size());
    }
}
