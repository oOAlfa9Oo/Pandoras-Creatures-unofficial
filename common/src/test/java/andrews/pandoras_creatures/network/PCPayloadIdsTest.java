package andrews.pandoras_creatures.network;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PCPayloadIdsTest {
    @Test
    void payloadPathsStayUnique() {
        Set<String> uniquePaths = Set.copyOf(PCPayloadIds.allPaths());

        assertEquals(PCPayloadIds.allPaths().size(), uniquePaths.size());
    }

    @Test
    void payloadPathsStayLoaderNeutral() {
        assertTrue(PCPayloadIds.allPaths().stream().allMatch(path -> !path.contains(":")));
        assertTrue(PCPayloadIds.allPaths().stream().allMatch(path -> path.equals(path.toLowerCase())));
    }
}
