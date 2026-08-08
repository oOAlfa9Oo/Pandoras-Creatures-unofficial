package andrews.pandoras_creatures.datagen.recipe;

import andrews.pandoras_creatures.registry.block.PCBlockIds;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxPalette;
import andrews.pandoras_creatures.registry.item.PCItemIds;
import andrews.pandoras_creatures.registry.recipe.PCRecipeIds;
import andrews.pandoras_creatures.util.Reference;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public final class PCRecipeDataProvider implements DataProvider {
    private static final CookingRecipeDefinition[] COOKING_RECIPES = new CookingRecipeDefinition[]{
            new CookingRecipeDefinition("bufflon_beef_cooked", "minecraft:smelting", PCItemIds.BUFFLON_BEEF, PCItemIds.BUFFLON_BEEF_COOKED, 0.35F, 200),
            new CookingRecipeDefinition("bufflon_beef_cooked_from_smoking", "minecraft:smoking", PCItemIds.BUFFLON_BEEF, PCItemIds.BUFFLON_BEEF_COOKED, 0.35F, 100),
            new CookingRecipeDefinition("bufflon_beef_cooked_from_campfire", "minecraft:campfire_cooking", PCItemIds.BUFFLON_BEEF, PCItemIds.BUFFLON_BEEF_COOKED, 0.35F, 600),
            new CookingRecipeDefinition("crab_meat_cooked", "minecraft:smelting", PCItemIds.CRAB_MEAT, PCItemIds.CRAB_MEAT_COOKED, 0.35F, 200),
            new CookingRecipeDefinition("crab_meat_cooked_from_smoking", "minecraft:smoking", PCItemIds.CRAB_MEAT, PCItemIds.CRAB_MEAT_COOKED, 0.35F, 100),
            new CookingRecipeDefinition("crab_meat_cooked_from_campfire", "minecraft:campfire_cooking", PCItemIds.CRAB_MEAT, PCItemIds.CRAB_MEAT_COOKED, 0.35F, 600),
            new CookingRecipeDefinition("seahorse_cooked", "minecraft:smelting", PCItemIds.SEAHORSE, PCItemIds.SEAHORSE_COOKED, 0.35F, 200),
            new CookingRecipeDefinition("seahorse_cooked_from_smoking", "minecraft:smoking", PCItemIds.SEAHORSE, PCItemIds.SEAHORSE_COOKED, 0.35F, 100),
            new CookingRecipeDefinition("seahorse_cooked_from_campfire", "minecraft:campfire_cooking", PCItemIds.SEAHORSE, PCItemIds.SEAHORSE_COOKED, 0.35F, 600)
    };

    private final PackOutput.PathProvider recipePathProvider;

    public PCRecipeDataProvider(PackOutput output) {
        this.recipePathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "recipe");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        Map<ResourceLocation, JsonObject> recipes = new LinkedHashMap<>();
        addCustomEndTrollBoxRecipes(recipes);
        addBasicCraftingRecipes(recipes);
        addCookingRecipes(recipes);

        List<CompletableFuture<?>> futures = new ArrayList<>();
        recipes.forEach((id, json) -> futures.add(DataProvider.saveStable(cachedOutput, json, recipePathProvider.json(id))));
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "Pandoras Creatures Recipes";
    }

    private static void addCustomEndTrollBoxRecipes(Map<ResourceLocation, JsonObject> recipes) {
        recipes.put(PCRecipeIds.id(PCRecipeIds.END_TROLL_BOX), createEndTrollBoxRecipe());
        PCEndTrollBoxPalette.orderedColors().forEach(color -> {
            String recipeName = PCEndTrollBoxPalette.blockName(color);
            recipes.put(id(recipeName), createEndTrollBoxColoringRecipe(color, recipeName));
        });
    }

    private static void addBasicCraftingRecipes(Map<ResourceLocation, JsonObject> recipes) {
        recipes.put(id("herb_bundle"), createShapelessRecipe(id(PCItemIds.HERB_BUNDLE), 3,
                itemIngredient(PCBlockIds.HORSETAIL),
                itemIngredient(PCBlockIds.DHANIA),
                itemIngredient(PCBlockIds.HILL_BLOOM)));

        recipes.put(id("bufflon_hide"), createShapelessRecipe(vanillaId("leather"), 3, itemIngredient(PCItemIds.BUFFLON_HIDE)));
        recipes.put(id("bufflon_beef"), createShapelessRecipe(vanillaId("beef"), 2, itemIngredient(PCItemIds.BUFFLON_BEEF)));

        recipes.put(id("plant_hat"), createShapedRecipe(PCItemIds.PLANT_HAT,
                List.of("LLL", "SSS", "THT"),
                Map.of(
                        "L", tagIngredient("minecraft:leaves"),
                        "S", itemIngredient(vanillaId("stick")),
                        "T", itemIngredient(PCItemIds.ACIDIC_ARCHVINE_TONGUE),
                        "H", itemIngredient(vanillaId("leather_helmet"))
                )));

        recipes.put(id("arachnon_hammer"), createShapedRecipe(PCItemIds.ARACHNON_HAMMER,
                List.of("AAA", "ADA", "LIL"),
                Map.of(
                        "A", itemIngredient(PCBlockIds.ARACHNON_CRYSTAL),
                        "D", itemIngredient(vanillaId("diamond")),
                        "L", itemIngredient(vanillaId("leather")),
                        "I", itemIngredient(vanillaId("iron_ingot"))
                )));
    }

    private static void addCookingRecipes(Map<ResourceLocation, JsonObject> recipes) {
        for (CookingRecipeDefinition definition : COOKING_RECIPES) {
            recipes.put(id(definition.name()), createCookingRecipe(definition));
        }
    }

    private static JsonObject createEndTrollBoxRecipe() {
        JsonObject root = new JsonObject();
        root.addProperty("type", PCRecipeIds.qualified(PCRecipeIds.END_TROLL_BOX));
        root.add("pattern", toJsonArray(List.of("SSS", "SBS", "SSS")));

        JsonObject key = new JsonObject();
        key.add("S", itemIngredient(PCItemIds.END_TROLL_SKIN));
        key.add("B", tagIngredient("pandoras_creatures:vanilla_shulker_boxes"));
        root.add("key", key);
        root.add("result", resultObject(PCBlockIds.END_TROLL_BOX, 1));
        return root;
    }

    private static JsonObject createEndTrollBoxColoringRecipe(DyeColor color, String outputId) {
        JsonObject root = new JsonObject();
        root.addProperty("type", PCRecipeIds.qualified(PCRecipeIds.END_TROLL_BOX_COLORING));
        root.addProperty("group", PCRecipeIds.END_TROLL_BOX_COLORING_GROUP);

        JsonArray ingredients = new JsonArray();
        ingredients.add(tagIngredient("pandoras_creatures:end_troll_boxes"));
        ingredients.add(tagIngredient("c:dyes/" + color.getName()));
        root.add("ingredients", ingredients);
        root.add("result", resultObject(outputId, 1));
        return root;
    }

    private static JsonObject createShapelessRecipe(ResourceLocation output, int count, JsonObject... ingredients) {
        JsonObject root = new JsonObject();
        root.addProperty("type", "minecraft:crafting_shapeless");
        JsonArray jsonIngredients = new JsonArray();
        for (JsonObject ingredient : ingredients) {
            jsonIngredients.add(ingredient);
        }
        root.add("ingredients", jsonIngredients);
        root.add("result", resultObject(output, count));
        return root;
    }

    private static JsonObject createShapedRecipe(String output, List<String> pattern, Map<String, JsonObject> keys) {
        JsonObject root = new JsonObject();
        root.addProperty("type", "minecraft:crafting_shaped");
        root.add("pattern", toJsonArray(pattern));

        JsonObject key = new JsonObject();
        keys.forEach(key::add);
        root.add("key", key);
        root.add("result", resultObject(output, 1));
        return root;
    }

    private static JsonObject createCookingRecipe(CookingRecipeDefinition definition) {
        JsonObject root = new JsonObject();
        root.addProperty("type", definition.type());
        root.add("ingredient", itemIngredient(definition.ingredientId()));
        root.add("result", resultObject(definition.resultId(), 1));
        root.addProperty("experience", definition.experience());
        root.addProperty("cookingtime", definition.cookingTime());
        return root;
    }

    private static JsonObject itemIngredient(String itemId) {
        return itemIngredient(id(itemId));
    }

    private static JsonObject itemIngredient(ResourceLocation itemId) {
        JsonObject ingredient = new JsonObject();
        ingredient.addProperty("item", itemId.toString());
        return ingredient;
    }

    private static JsonObject tagIngredient(String tag) {
        JsonObject ingredient = new JsonObject();
        ingredient.addProperty("tag", tag);
        return ingredient;
    }

    private static JsonArray toJsonArray(List<String> values) {
        JsonArray array = new JsonArray();
        values.forEach(array::add);
        return array;
    }

    private static JsonObject resultObject(String itemId, int count) {
        return resultObject(id(itemId), count);
    }

    private static JsonObject resultObject(ResourceLocation itemId, int count) {
        JsonObject result = new JsonObject();
        result.addProperty("id", itemId.toString());
        if (count != 1) {
            result.addProperty("count", count);
        }
        return result;
    }

    private static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MODID, path);
    }

    private static ResourceLocation vanillaId(String path) {
        return ResourceLocation.withDefaultNamespace(path);
    }

    private record CookingRecipeDefinition(String name, String type, String ingredientId, String resultId, float experience,
                                           int cookingTime) {
    }
}
