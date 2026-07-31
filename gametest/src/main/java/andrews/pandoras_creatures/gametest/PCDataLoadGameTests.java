package andrews.pandoras_creatures.gametest;

import andrews.pandoras_creatures.registry.PCTags;
import andrews.pandoras_creatures.registry.structure.PCStructureIds;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.List;

/**
 * Valida en un servidor real que los datos del mod (recipes, loot tables, tags)
 * cargan desde datapack en el formato de esta familia (1.21.1).
 */
public final class PCDataLoadGameTests {
    private static final String DATA_BATCH = "data_load";
    private static final String SHARED_TEMPLATE = "gametest/bufflon_arena";

    private static final List<String> EXPECTED_RECIPES = List.of(
            "arachnon_hammer",
            "end_troll_box",
            "black_end_troll_box",
            "bufflon_beef_cooked",
            "bufflon_beef_cooked_from_campfire",
            "bufflon_beef_cooked_from_smoking",
            "herb_bundle",
            "plant_hat");

    private static final List<String> EXPECTED_BLOCK_LOOT = List.of(
            "blocks/arachnon_crystal",
            "blocks/end_troll_box",
            "blocks/black_end_troll_box",
            "blocks/dhania");

    private static final List<String> EXPECTED_ENTITY_LOOT = List.of(
            "entities/arachnon",
            "entities/bufflon",
            "entities/crab",
            "entities/end_troll",
            "entities/hellhound");

    private PCDataLoadGameTests() {
    }

    private static ResourceLocation modId(String path) {
        return PCStructureIds.id(path);
    }

    @GameTest(template = SHARED_TEMPLATE, batch = DATA_BATCH)
    public static void recipesLoadFromDatapack(GameTestHelper helper) {
        MinecraftServer server = helper.getLevel().getServer();

        for (String recipeId : EXPECTED_RECIPES) {
            helper.assertTrue(server.getRecipeManager().byKey(modId(recipeId)).isPresent(),
                    "Recipe should load from datapack: " + recipeId);
        }
        helper.succeed();
    }

    @GameTest(template = SHARED_TEMPLATE, batch = DATA_BATCH)
    public static void lootTablesLoadFromDatapack(GameTestHelper helper) {
        MinecraftServer server = helper.getLevel().getServer();

        for (String lootId : EXPECTED_BLOCK_LOOT) {
            LootTable table = server.reloadableRegistries()
                    .getLootTable(ResourceKey.create(Registries.LOOT_TABLE, modId(lootId)));
            helper.assertTrue(table != LootTable.EMPTY, "Block loot table should load from datapack: " + lootId);
        }
        for (String lootId : EXPECTED_ENTITY_LOOT) {
            LootTable table = server.reloadableRegistries()
                    .getLootTable(ResourceKey.create(Registries.LOOT_TABLE, modId(lootId)));
            helper.assertTrue(table != LootTable.EMPTY, "Entity loot table should load from datapack: " + lootId);
        }
        helper.succeed();
    }

    @GameTest(template = SHARED_TEMPLATE, batch = DATA_BATCH)
    public static void endTrollBoxItemTagIsBound(GameTestHelper helper) {
        boolean tagLoaded = BuiltInRegistries.ITEM.getTag(PCTags.Items.END_TROLL_BOXES)
                .map(named -> named.size() > 0)
                .orElse(false);

        helper.assertTrue(tagLoaded, "pandoras_creatures:end_troll_boxes item tag should load with entries");
        helper.succeed();
    }
}
