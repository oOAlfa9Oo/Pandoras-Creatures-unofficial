package andrews.pandoras_creatures.datagen;

import andrews.pandoras_creatures.datagen.model.PCBlockStateModelDataProvider;
import andrews.pandoras_creatures.datagen.model.PCItemModelDataProvider;
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

    public static void gatherData(GatherDataEvent event) {
        PackOutput output = event.getGenerator().getPackOutput();
        PackOutput sharedClientOutput = sharedClientOutput(output);
        PackOutput sharedDataOutput = sharedDataOutput(output);
        PackOutput sharedWorldgenOutput = sharedWorldgenOutput(output);
        event.getGenerator().addProvider(event.includeServer(), new PCTagDataProvider(sharedDataOutput));
        event.getGenerator().addProvider(event.includeServer(), new PCRecipeDataProvider(sharedDataOutput));
        event.getGenerator().addProvider(event.includeServer(), new PCBlockLootTableDataProvider(sharedDataOutput));
        event.getGenerator().addProvider(event.includeServer(), new PCEntityLootTableDataProvider(sharedDataOutput));
        event.getGenerator().addProvider(event.includeServer(), new PCChestInjectionLootTableDataProvider(sharedDataOutput));
        event.getGenerator().addProvider(event.includeServer(), new PCLootModifierDataProvider(output));
        event.getGenerator().addProvider(event.includeServer(), new PCWorldgenTagDataProvider(sharedWorldgenOutput));
        event.getGenerator().addProvider(event.includeServer(), new PCBiomeModifierDataProvider(output, sharedWorldgenOutput));
        event.getGenerator().addProvider(event.includeServer(), new PCConfiguredFeatureDataProvider(sharedWorldgenOutput));
        event.getGenerator().addProvider(event.includeServer(), new PCPlacedFeatureDataProvider(sharedWorldgenOutput));
        event.getGenerator().addProvider(event.includeServer(), new PCTemplatePoolDataProvider(sharedWorldgenOutput));
        event.getGenerator().addProvider(event.includeServer(), new PCStructureDataProvider(sharedWorldgenOutput));
        event.getGenerator().addProvider(event.includeServer(), new PCStructureSetDataProvider(sharedWorldgenOutput));
        event.getGenerator().addProvider(event.includeClient(), new PCLanguageDataProvider(sharedClientOutput));
        event.getGenerator().addProvider(event.includeClient(), new PCBlockStateModelDataProvider(sharedClientOutput, event.getExistingFileHelper()));
        event.getGenerator().addProvider(event.includeClient(), new PCItemModelDataProvider(sharedClientOutput, event.getExistingFileHelper()));
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
