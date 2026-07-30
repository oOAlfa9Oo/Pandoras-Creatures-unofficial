package andrews.pandoras_creatures.datagen;

import andrews.pandoras_creatures.datagen.loot.PCBlockLootTableDataProvider;
import andrews.pandoras_creatures.datagen.loot.PCChestInjectionLootTableDataProvider;
import andrews.pandoras_creatures.datagen.loot.PCEntityLootTableDataProvider;
import andrews.pandoras_creatures.datagen.loot.PCLootModifierDataProvider;
import andrews.pandoras_creatures.datagen.lang.PCLanguageDataProvider;
import andrews.pandoras_creatures.datagen.recipe.PCRecipeDataProvider;
import andrews.pandoras_creatures.datagen.tag.PCTagDataProvider;
import andrews.pandoras_creatures.datagen.worldgen.PCConfiguredFeatureDataProvider;
import andrews.pandoras_creatures.datagen.worldgen.PCBiomeModifierDataProvider;
import andrews.pandoras_creatures.datagen.worldgen.PCPlacedFeatureDataProvider;
import andrews.pandoras_creatures.datagen.worldgen.PCStructureDataProvider;
import andrews.pandoras_creatures.datagen.worldgen.PCStructureSetDataProvider;
import andrews.pandoras_creatures.datagen.worldgen.PCTemplatePoolDataProvider;
import andrews.pandoras_creatures.datagen.worldgen.PCWorldgenTagDataProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.nio.file.Path;

public final class PCDataGenerators {
    private static final String SHARED_CLIENT_OUTPUT_PROPERTY = "pandoras_creatures.sharedGeneratedClientOutput";
    private static final String SHARED_DATA_OUTPUT_PROPERTY = "pandoras_creatures.sharedGeneratedDataOutput";
    private static final String SHARED_WORLDGEN_OUTPUT_PROPERTY = "pandoras_creatures.sharedGeneratedWorldgenOutput";

    private PCDataGenerators() {
    }

    public static void gatherServerData(GatherDataEvent.Server event) {
        PackOutput output = event.getGenerator().getPackOutput();
        PackOutput sharedDataOutput = sharedDataOutput(output);
        PackOutput sharedWorldgenOutput = sharedWorldgenOutput(output);
        event.addProvider(new PCTagDataProvider(sharedDataOutput));
        event.addProvider(new PCRecipeDataProvider(sharedDataOutput));
        event.addProvider(new PCBlockLootTableDataProvider(sharedDataOutput));
        event.addProvider(new PCEntityLootTableDataProvider(sharedDataOutput));
        event.addProvider(new PCChestInjectionLootTableDataProvider(sharedDataOutput));
        event.addProvider(new PCLootModifierDataProvider(output));
        event.addProvider(new PCWorldgenTagDataProvider(sharedWorldgenOutput));
        event.addProvider(new PCBiomeModifierDataProvider(output, sharedWorldgenOutput));
        event.addProvider(new PCConfiguredFeatureDataProvider(sharedWorldgenOutput));
        event.addProvider(new PCPlacedFeatureDataProvider(sharedWorldgenOutput));
        event.addProvider(new PCTemplatePoolDataProvider(sharedWorldgenOutput));
        event.addProvider(new PCStructureDataProvider(sharedWorldgenOutput));
        event.addProvider(new PCStructureSetDataProvider(sharedWorldgenOutput));
    }

    public static void gatherClientData(GatherDataEvent.Client event) {
        PackOutput output = event.getGenerator().getPackOutput();
        PackOutput sharedClientOutput = sharedClientOutput(output);
        event.addProvider(new PCLanguageDataProvider(sharedClientOutput));
    }

    private static PackOutput sharedClientOutput(PackOutput fallbackOutput) {
        return redirectedOutput(SHARED_CLIENT_OUTPUT_PROPERTY, fallbackOutput);
    }

    private static PackOutput sharedDataOutput(PackOutput fallbackOutput) {
        return redirectedOutput(SHARED_DATA_OUTPUT_PROPERTY, fallbackOutput);
    }

    private static PackOutput sharedWorldgenOutput(PackOutput fallbackOutput) {
        return redirectedOutput(SHARED_WORLDGEN_OUTPUT_PROPERTY, fallbackOutput);
    }

    private static PackOutput redirectedOutput(String propertyName, PackOutput fallbackOutput) {
        String sharedOutputPath = System.getProperty(propertyName);
        if (sharedOutputPath == null || sharedOutputPath.isBlank()) {
            return fallbackOutput;
        }

        return new PackOutput(Path.of(sharedOutputPath));
    }
}
