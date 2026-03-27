package andrews.pandoras_creatures.registry.structure;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PCStructureIdsTest {
    @Test
    void structurePathsStayUniqueAndLoaderNeutral() {
        Set<String> uniquePaths = Set.copyOf(PCStructureIds.allPaths());

        assertEquals(PCStructureIds.allPaths().size(), uniquePaths.size());
        assertTrue(PCStructureIds.allPaths().stream().allMatch(path -> path.equals(path.toLowerCase())));
        assertTrue(PCStructureIds.allPaths().stream().allMatch(path -> !path.contains(":")));
    }

    @Test
    void endPrisonNamespacedReferencesStayStable() {
        assertEquals("pandoras_creatures:end_prison", PCStructureIds.qualified(PCStructureIds.END_PRISON));
        assertEquals("#pandoras_creatures:has_structure/end_prison", PCStructureIds.tagReference(PCStructureIds.HAS_STRUCTURE_END_PRISON));
        assertEquals("pandoras_creatures:end_prison/end_prison_ship", PCStructureIds.qualified(PCStructureIds.END_PRISON_SHIP_TEMPLATE));
    }
}
