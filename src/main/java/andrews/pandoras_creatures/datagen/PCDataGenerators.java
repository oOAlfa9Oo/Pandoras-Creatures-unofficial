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

public final class PCDataGenerators {
    private PCDataGenerators() {
    }

    public static void gatherData(GatherDataEvent event) {
        PackOutput output = event.getGenerator().getPackOutput();
        event.getGenerator().addProvider(event.includeServer(), new PCTagDataProvider(output));
        event.getGenerator().addProvider(event.includeServer(), new PCRecipeDataProvider(output));
        event.getGenerator().addProvider(event.includeServer(), new PCBlockLootTableDataProvider(output));
        event.getGenerator().addProvider(event.includeServer(), new PCEntityLootTableDataProvider(output));
        event.getGenerator().addProvider(event.includeServer(), new PCChestInjectionLootTableDataProvider(output));
        event.getGenerator().addProvider(event.includeServer(), new PCLootModifierDataProvider(output));
        event.getGenerator().addProvider(event.includeServer(), new PCWorldgenTagDataProvider(output));
        event.getGenerator().addProvider(event.includeServer(), new PCBiomeModifierDataProvider(output));
        event.getGenerator().addProvider(event.includeServer(), new PCConfiguredFeatureDataProvider(output));
        event.getGenerator().addProvider(event.includeServer(), new PCPlacedFeatureDataProvider(output));
        event.getGenerator().addProvider(event.includeServer(), new PCTemplatePoolDataProvider(output));
        event.getGenerator().addProvider(event.includeServer(), new PCStructureDataProvider(output));
        event.getGenerator().addProvider(event.includeServer(), new PCStructureSetDataProvider(output));
        event.getGenerator().addProvider(event.includeClient(), new PCLanguageDataProvider(output));
        event.getGenerator().addProvider(event.includeClient(), new PCBlockStateModelDataProvider(output, event.getExistingFileHelper()));
        event.getGenerator().addProvider(event.includeClient(), new PCItemModelDataProvider(output, event.getExistingFileHelper()));
    }
}
