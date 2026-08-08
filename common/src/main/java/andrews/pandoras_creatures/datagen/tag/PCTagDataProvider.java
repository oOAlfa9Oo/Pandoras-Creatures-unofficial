package andrews.pandoras_creatures.datagen.tag;

import andrews.pandoras_creatures.registry.block.PCBlockIds;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxPalette;
import andrews.pandoras_creatures.util.Reference;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class PCTagDataProvider implements DataProvider {
    private final PackOutput.PathProvider itemTagPathProvider;
    private final PackOutput.PathProvider entityTypeTagPathProvider;

    public PCTagDataProvider(PackOutput output) {
        this.itemTagPathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "tags/item");
        this.entityTypeTagPathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "tags/entity_type");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        futures.add(DataProvider.saveStable(cachedOutput, createTagJson(endTrollBoxValues()), itemTagPathProvider.json(id("end_troll_boxes"))));
        futures.add(DataProvider.saveStable(cachedOutput, createTagJson(vanillaShulkerBoxValues()), itemTagPathProvider.json(id("vanilla_shulker_boxes"))));
        futures.add(DataProvider.saveStable(cachedOutput, createTagJson(List.of(modId(PCEntityIds.ARACHNON))), entityTypeTagPathProvider.json(Identifier.withDefaultNamespace("arthropod"))));
        futures.add(DataProvider.saveStable(cachedOutput, createTagJson(List.of(modId(PCEntityIds.HELLHOUND))), entityTypeTagPathProvider.json(Identifier.withDefaultNamespace("undead"))));
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "Pandoras Creatures Tags";
    }

    private static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(Reference.MODID, path);
    }

    private static JsonObject createTagJson(List<String> values) {
        JsonObject root = new JsonObject();
        root.addProperty("replace", false);
        JsonArray jsonValues = new JsonArray();
        values.forEach(jsonValues::add);
        root.add("values", jsonValues);
        return root;
    }

    private static List<String> endTrollBoxValues() {
        List<String> values = new ArrayList<>();
        values.add(modId(PCBlockIds.END_TROLL_BOX));
        PCEndTrollBoxPalette.orderedColors().forEach(color ->
                values.add(modId(PCBlockIds.endTrollBox(color.getName()))));
        return values;
    }

    private static List<String> vanillaShulkerBoxValues() {
        List<String> values = new ArrayList<>();
        values.add("minecraft:shulker_box");
        PCEndTrollBoxPalette.orderedColors().forEach(color ->
                values.add("minecraft:" + color.getName() + "_shulker_box"));
        return values;
    }

    private static String modId(String path) {
        return Identifier.fromNamespaceAndPath(Reference.MODID, path).toString();
    }
}
