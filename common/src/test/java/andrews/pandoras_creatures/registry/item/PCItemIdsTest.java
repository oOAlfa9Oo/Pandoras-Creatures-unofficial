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

    @Test
    void itemCatalogContainsExactlyExpectedIds() {
        // Contrato de contenido: 18 items base + 7 spawn eggs. Borrar/renombrar rompe el test.
        assertEquals(
                Set.of(
                        "crab_meat",
                        "crab_meat_cooked",
                        "seahorse",
                        "seahorse_cooked",
                        "acidic_archvine_tongue",
                        "herb_bundle",
                        "bufflon_beef",
                        "bufflon_beef_cooked",
                        "bufflon_hide",
                        "bufflon_saddle",
                        "bufflon_player_seats",
                        "bufflon_small_storage",
                        "bufflon_large_storage",
                        "end_troll_skin",
                        "arachnon_hammer",
                        "crab_bucket",
                        "seahorse_bucket",
                        "plant_hat",
                        "arachnon_spawn_egg",
                        "hellhound_spawn_egg",
                        "crab_spawn_egg",
                        "seahorse_spawn_egg",
                        "acidic_archvine_spawn_egg",
                        "bufflon_spawn_egg",
                        "end_troll_spawn_egg"),
                Set.copyOf(PCItemIds.allPaths()));
        assertEquals(25, PCItemIds.allPaths().size());
    }
}
