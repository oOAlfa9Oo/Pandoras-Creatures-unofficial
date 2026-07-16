package andrews.pandoras_creatures.gametest;

import andrews.pandoras_creatures.registry.PCTags;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;

import java.util.List;

/**
 * Valida en un servidor real que los datos portados a formato 1.20.1
 * (recipes, loot tables, tags, advancement) realmente cargan.
 * Cubre la validacion en juego del port de datos de ADR-0007 (F3).
 */
@GameTestHolder(Reference.MODID)
@PrefixGameTestTemplate(false)
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

    @GameTest(template = SHARED_TEMPLATE, batch = DATA_BATCH)
    public static void recipesLoadUnder1201Format(GameTestHelper helper) {
        MinecraftServer server = helper.getLevel().getServer();

        for (String recipeId : EXPECTED_RECIPES) {
            helper.assertTrue(server.getRecipeManager().byKey(Reference.id(recipeId)).isPresent(),
                    "Recipe should load from datapack: " + recipeId);
        }
        helper.succeed();
    }

    @GameTest(template = SHARED_TEMPLATE, batch = DATA_BATCH)
    public static void lootTablesLoadUnder1201Format(GameTestHelper helper) {
        MinecraftServer server = helper.getLevel().getServer();

        for (String lootId : EXPECTED_BLOCK_LOOT) {
            helper.assertTrue(server.getLootData().getLootTable(Reference.id(lootId)) != LootTable.EMPTY,
                    "Block loot table should load from datapack: " + lootId);
        }
        for (String lootId : EXPECTED_ENTITY_LOOT) {
            helper.assertTrue(server.getLootData().getLootTable(Reference.id(lootId)) != LootTable.EMPTY,
                    "Entity loot table should load from datapack: " + lootId);
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
