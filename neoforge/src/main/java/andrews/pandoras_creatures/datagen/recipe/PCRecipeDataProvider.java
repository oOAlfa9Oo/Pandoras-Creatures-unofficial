package andrews.pandoras_creatures.datagen.recipe;

import andrews.pandoras_creatures.registry.PCBlocks;
import andrews.pandoras_creatures.registry.PCItems;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxPalette;
import andrews.pandoras_creatures.registry.recipe.PCRecipeIds;
import andrews.pandoras_creatures.util.Reference;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public final class PCRecipeDataProvider implements DataProvider {
    private static final CookingRecipeDefinition[] COOKING_RECIPES = new CookingRecipeDefinition[]{
            new CookingRecipeDefinition("bufflon_beef_cooked", "minecraft:smelting", PCItems.BUFFLON_BEEF.get(), PCItems.BUFFLON_BEEF_COOKED.get(), 0.35F, 200),
            new CookingRecipeDefinition("bufflon_beef_cooked_from_smoking", "minecraft:smoking", PCItems.BUFFLON_BEEF.get(), PCItems.BUFFLON_BEEF_COOKED.get(), 0.35F, 100),
            new CookingRecipeDefinition("bufflon_beef_cooked_from_campfire", "minecraft:campfire_cooking", PCItems.BUFFLON_BEEF.get(), PCItems.BUFFLON_BEEF_COOKED.get(), 0.35F, 600),
            new CookingRecipeDefinition("crab_meat_cooked", "minecraft:smelting", PCItems.CRAB_MEAT.get(), PCItems.CRAB_MEAT_COOKED.get(), 0.35F, 200),
            new CookingRecipeDefinition("crab_meat_cooked_from_smoking", "minecraft:smoking", PCItems.CRAB_MEAT.get(), PCItems.CRAB_MEAT_COOKED.get(), 0.35F, 100),
            new CookingRecipeDefinition("crab_meat_cooked_from_campfire", "minecraft:campfire_cooking", PCItems.CRAB_MEAT.get(), PCItems.CRAB_MEAT_COOKED.get(), 0.35F, 600),
            new CookingRecipeDefinition("seahorse_cooked", "minecraft:smelting", PCItems.SEAHORSE.get(), PCItems.SEAHORSE_COOKED.get(), 0.35F, 200),
            new CookingRecipeDefinition("seahorse_cooked_from_smoking", "minecraft:smoking", PCItems.SEAHORSE.get(), PCItems.SEAHORSE_COOKED.get(), 0.35F, 100),
            new CookingRecipeDefinition("seahorse_cooked_from_campfire", "minecraft:campfire_cooking", PCItems.SEAHORSE.get(), PCItems.SEAHORSE_COOKED.get(), 0.35F, 600)
    };

    private final PackOutput.PathProvider recipePathProvider;

    public PCRecipeDataProvider(PackOutput output) {
        this.recipePathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "recipes");
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
            recipes.put(id(recipeName), createEndTrollBoxColoringRecipe(color, PCBlocks.getEndTrollBox(color).asItem()));
        });
    }

    private static void addBasicCraftingRecipes(Map<ResourceLocation, JsonObject> recipes) {
        recipes.put(id("herb_bundle"), createShapelessRecipe(PCItems.HERB_BUNDLE.get(), 3,
                itemIngredient(PCBlocks.HORSETAIL.get()),
                itemIngredient(PCBlocks.DHANIA.get()),
                itemIngredient(PCBlocks.HILL_BLOOM.get())));

        recipes.put(id("bufflon_hide"), createShapelessRecipe(Items.LEATHER, 3, itemIngredient(PCItems.BUFFLON_HIDE.get())));
        recipes.put(id("bufflon_beef"), createShapelessRecipe(Items.BEEF, 2, itemIngredient(PCItems.BUFFLON_BEEF.get())));

        recipes.put(id("plant_hat"), createShapedRecipe(PCItems.PLANT_HAT.get(),
                List.of("LLL", "SSS", "THT"),
                Map.of(
                        "L", tagIngredient("minecraft:leaves"),
                        "S", itemIngredient(Items.STICK),
                        "T", itemIngredient(PCItems.ACIDIC_ARCHVINE_TONGUE.get()),
                        "H", itemIngredient(Items.LEATHER_HELMET)
                )));

        recipes.put(id("arachnon_hammer"), createShapedRecipe(PCItems.ARACHNON_HAMMER.get(),
                List.of("AAA", "ADA", "LIL"),
                Map.of(
                        "A", itemIngredient(PCBlocks.ARACHNON_CRYSTAL.get()),
                        "D", itemIngredient(Items.DIAMOND),
                        "L", itemIngredient(Items.LEATHER),
                        "I", itemIngredient(Items.IRON_INGOT)
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
        key.add("S", itemIngredient(PCItems.END_TROLL_SKIN.get()));
        key.add("B", tagIngredient("pandoras_creatures:vanilla_shulker_boxes"));
        root.add("key", key);
        root.add("result", resultObject(PCBlocks.END_TROLL_BOX.get().asItem(), 1));
        return root;
    }

    private static JsonObject createEndTrollBoxColoringRecipe(DyeColor color, Item output) {
        JsonObject root = new JsonObject();
        root.addProperty("type", PCRecipeIds.qualified(PCRecipeIds.END_TROLL_BOX_COLORING));
        root.addProperty("group", PCRecipeIds.END_TROLL_BOX_COLORING_GROUP);

        JsonArray ingredients = new JsonArray();
        ingredients.add(tagIngredient("pandoras_creatures:end_troll_boxes"));
        ingredients.add(tagIngredient("c:dyes/" + color.getName()));
        root.add("ingredients", ingredients);
        root.add("result", resultObject(output, 1));
        return root;
    }

    private static JsonObject createShapelessRecipe(ItemLike output, int count, JsonObject... ingredients) {
        JsonObject root = new JsonObject();
        root.addProperty("type", "minecraft:crafting_shapeless");
        JsonArray jsonIngredients = new JsonArray();
        for (JsonObject ingredient : ingredients) {
            jsonIngredients.add(ingredient);
        }
        root.add("ingredients", jsonIngredients);
        root.add("result", resultObject(output.asItem(), count));
        return root;
    }

    private static JsonObject createShapedRecipe(ItemLike output, List<String> pattern, Map<String, JsonObject> keys) {
        JsonObject root = new JsonObject();
        root.addProperty("type", "minecraft:crafting_shaped");
        root.add("pattern", toJsonArray(pattern));

        JsonObject key = new JsonObject();
        keys.forEach(key::add);
        root.add("key", key);
        root.add("result", resultObject(output.asItem(), 1));
        return root;
    }

    private static JsonObject createCookingRecipe(CookingRecipeDefinition definition) {
        JsonObject root = new JsonObject();
        root.addProperty("type", definition.type());
        root.add("ingredient", itemIngredient(definition.ingredient()));
        root.add("result", resultObject(definition.result(), 1));
        root.addProperty("experience", definition.experience());
        root.addProperty("cookingtime", definition.cookingTime());
        return root;
    }

    private static JsonObject itemIngredient(ItemLike itemLike) {
        JsonObject ingredient = new JsonObject();
        ingredient.addProperty("item", itemId(itemLike.asItem()));
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

    private static JsonObject resultObject(Item item, int count) {
        JsonObject result = new JsonObject();
        result.addProperty("id", itemId(item));
        if (count != 1) {
            result.addProperty("count", count);
        }
        return result;
    }

    private static String itemId(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).toString();
    }

    private static ResourceLocation id(String path) {
        return new ResourceLocation(Reference.MODID, path);
    }

    private record CookingRecipeDefinition(String name, String type, Item ingredient, Item result, float experience,
                                           int cookingTime) {
    }
}

