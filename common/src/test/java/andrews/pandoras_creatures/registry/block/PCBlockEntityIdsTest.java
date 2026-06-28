package andrews.pandoras_creatures.registry.block;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PCBlockEntityIdsTest {
    @Test
    void blockEntityPathsStayUnique() {
        Set<String> uniquePaths = Set.copyOf(PCBlockEntityIds.allPaths());

        assertEquals(PCBlockEntityIds.allPaths().size(), uniquePaths.size());
    }

    @Test
    void keepsLegacyEndTrollBoxIdStable() {
        assertEquals("end_troll_shulker", PCBlockEntityIds.END_TROLL_BOX);
        assertTrue(PCBlockEntityIds.END_TROLL_BOX.contains("shulker"));
    }

    @Test
    void blockEntityCatalogContainsExactlyExpectedIds() {
        assertEquals(
                Set.of("end_troll_shulker", "pandoric_shard"),
                Set.copyOf(PCBlockEntityIds.allPaths()));
        assertEquals(2, PCBlockEntityIds.allPaths().size());
    }
}
