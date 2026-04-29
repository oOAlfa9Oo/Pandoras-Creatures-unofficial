package andrews.pandoras_creatures.world;

import andrews.pandoras_creatures.world.biome.PCBiomeFeatureCatalog;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

public final class PCFabricWorldGeneration {
    private PCFabricWorldGeneration() {
    }

    public static void register() {
        for (PCBiomeFeatureCatalog.FeatureDefinition definition : PCBiomeFeatureCatalog.definitions()) {
            BiomeModifications.addFeature(
                    toBiomeSelector(definition.biomes()),
                    toDecorationStep(definition.step()),
                    ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(definition.featureId()))
            );
        }
    }

    private static Predicate<BiomeSelectionContext> toBiomeSelector(List<String> biomeSelectors) {
        if (biomeSelectors.size() == 1 && biomeSelectors.get(0).startsWith("#")) {
            TagKey<Biome> tag = TagKey.create(Registries.BIOME, new ResourceLocation(biomeSelectors.get(0).substring(1)));
            return BiomeSelectors.tag(tag);
        }

        List<ResourceKey<Biome>> biomeKeys = biomeSelectors.stream()
                .map(ResourceLocation::new)
                .map(id -> ResourceKey.create(Registries.BIOME, id))
                .toList();
        return BiomeSelectors.includeByKey(biomeKeys);
    }

    private static GenerationStep.Decoration toDecorationStep(String step) {
        return GenerationStep.Decoration.valueOf(step.toUpperCase(Locale.ROOT));
    }
}
