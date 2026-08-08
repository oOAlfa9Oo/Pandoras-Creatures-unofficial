package andrews.pandoras_creatures.datagen.worldgen;

import andrews.pandoras_creatures.registry.structure.PCStructureIds;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class PCTemplatePoolDataProvider implements DataProvider {
    private static final SinglePoolElementDefinition[] SINGLE_POOLS = new SinglePoolElementDefinition[]{
            new SinglePoolElementDefinition(
                    PCStructureIds.END_PRISON,
                    PCStructureIds.qualified(PCStructureIds.END_PRISON),
                    PCStructureIds.qualified(PCStructureIds.END_PRISON_BODY_TEMPLATE)
            )
    };
    private static final FeaturePoolElementDefinition[] FEATURE_POOLS = new FeaturePoolElementDefinition[]{
            new FeaturePoolElementDefinition(
                    PCStructureIds.END_PRISON_CHORUS_PLANT,
                    PCStructureIds.qualified(PCStructureIds.END_PRISON_CHORUS_PLANT),
                    PCStructureIds.qualified(PCStructureIds.END_PRISON_CHORUS_PLANT)
            )
    };
    private static final EmptyPoolElementDefinition[] EMPTY_POOLS = new EmptyPoolElementDefinition[]{
            new EmptyPoolElementDefinition(
                    PCStructureIds.END_PRISON_SHIP,
                    PCStructureIds.qualified(PCStructureIds.END_PRISON_SHIP)
            )
    };

    private final PackOutput.PathProvider templatePoolPathProvider;

    public PCTemplatePoolDataProvider(PackOutput output) {
        this.templatePoolPathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "worldgen/template_pool");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        for (SinglePoolElementDefinition definition : SINGLE_POOLS) {
            futures.add(DataProvider.saveStable(cachedOutput, createSinglePool(definition), templatePoolPathProvider.json(PCStructureIds.id(definition.name()))));
        }
        for (FeaturePoolElementDefinition definition : FEATURE_POOLS) {
            futures.add(DataProvider.saveStable(cachedOutput, createFeaturePool(definition), templatePoolPathProvider.json(PCStructureIds.id(definition.name()))));
        }
        for (EmptyPoolElementDefinition definition : EMPTY_POOLS) {
            futures.add(DataProvider.saveStable(cachedOutput, createEmptyPool(definition), templatePoolPathProvider.json(PCStructureIds.id(definition.name()))));
        }
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "Pandoras Creatures Template Pools";
    }

    private static JsonObject createSinglePool(SinglePoolElementDefinition definition) {
        JsonObject root = createBasePool(definition.poolId());

        JsonArray elements = new JsonArray();
        JsonObject weightedElement = new JsonObject();
        weightedElement.addProperty("weight", 1);

        JsonObject element = new JsonObject();
        element.addProperty("location", definition.location());
        element.addProperty("processors", "minecraft:empty");
        element.addProperty("projection", "rigid");
        element.addProperty("element_type", "minecraft:single_pool_element");

        weightedElement.add("element", element);
        elements.add(weightedElement);
        root.add("elements", elements);
        return root;
    }

    private static JsonObject createFeaturePool(FeaturePoolElementDefinition definition) {
        JsonObject root = createBasePool(definition.poolId());

        JsonArray elements = new JsonArray();
        JsonObject weightedElement = new JsonObject();
        weightedElement.addProperty("weight", 1);

        JsonObject element = new JsonObject();
        element.addProperty("feature", definition.featureId());
        element.addProperty("projection", "rigid");
        element.addProperty("element_type", "minecraft:feature_pool_element");

        weightedElement.add("element", element);
        elements.add(weightedElement);
        root.add("elements", elements);
        return root;
    }

    private static JsonObject createEmptyPool(EmptyPoolElementDefinition definition) {
        JsonObject root = createBasePool(definition.poolId());

        JsonArray elements = new JsonArray();
        JsonObject weightedElement = new JsonObject();
        weightedElement.addProperty("weight", 1);

        JsonObject element = new JsonObject();
        element.addProperty("element_type", "minecraft:empty_pool_element");

        weightedElement.add("element", element);
        elements.add(weightedElement);
        root.add("elements", elements);
        return root;
    }

    private static JsonObject createBasePool(String poolId) {
        JsonObject root = new JsonObject();
        root.addProperty("name", poolId);
        root.addProperty("fallback", "minecraft:empty");
        return root;
    }

    private record SinglePoolElementDefinition(String name, String poolId, String location) {
    }

    private record FeaturePoolElementDefinition(String name, String poolId, String featureId) {
    }

    private record EmptyPoolElementDefinition(String name, String poolId) {
    }
}
