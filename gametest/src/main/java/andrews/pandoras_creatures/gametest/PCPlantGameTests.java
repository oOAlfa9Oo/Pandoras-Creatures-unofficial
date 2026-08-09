package andrews.pandoras_creatures.gametest;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.content.block.PCPlantBlock;
import andrews.pandoras_creatures.registry.block.PCBlockIds;
import andrews.pandoras_creatures.world.biome.PCBiomeFeatureCatalog;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public final class PCPlantGameTests {
    private static final List<PlantDefinition> PLANTS = List.of(
            new PlantDefinition("horse_tail", PCBlockIds.HORSETAIL),
            new PlantDefinition("dhania", PCBlockIds.DHANIA),
            new PlantDefinition("hill_bloom", PCBlockIds.HILL_BLOOM)
    );

    private PCPlantGameTests() {
    }

    public static void runtimeBiomesContainOfficialPlantFeatures(GameTestHelper helper) {
        Registry<Biome> biomes = helper.getLevel().registryAccess().lookupOrThrow(Registries.BIOME);

        for (PCBiomeFeatureCatalog.FeatureDefinition definition : PCBiomeFeatureCatalog.definitions()) {
            ResourceKey<PlacedFeature> featureKey = ResourceKey.create(
                    Registries.PLACED_FEATURE, ResourceLocation.parse(definition.featureId()));
            List<Holder.Reference<Biome>> selectedBiomes = biomes.listElements()
                    .filter(biome -> definition.biomes().stream().anyMatch(selector -> matches(biome, selector)))
                    .toList();

            helper.assertTrue(!selectedBiomes.isEmpty(),
                    "Plant feature should select a runtime biome: " + definition.name());
            for (Holder.Reference<Biome> biome : selectedBiomes) {
                boolean present = biome.value().getGenerationSettings().features().stream()
                        .flatMap(holders -> holders.stream())
                        .anyMatch(feature -> feature.is(featureKey));
                helper.assertTrue(present,
                        "Runtime biome " + biome.key().location() + " should contain " + definition.featureId());
            }
        }
        helper.succeed();
    }

    public static void configuredFeaturesPlaceEveryOfficialPlant(GameTestHelper helper) {
        for (int index = 0; index < PLANTS.size(); index++) {
            placeConfiguredPlant(helper, PLANTS.get(index), new BlockPos(3 + index * 24, 4, 3));
        }
        helper.succeed();
    }

    public static void officialPlantsRemainSingleStageDecorations(GameTestHelper helper) {
        for (PlantDefinition plant : PLANTS) {
            Block block = PandorasCreaturesCommon.platform().registry().block(plant.blockId());
            helper.assertTrue(block instanceof PCPlantBlock, plant.blockId() + " should use the shared plant block");
            helper.assertTrue(!block.defaultBlockState().isRandomlyTicking(),
                    plant.blockId() + " should not grow from random ticks");
            helper.assertTrue(block.defaultBlockState().getProperties().isEmpty(),
                    plant.blockId() + " should remain a single-stage decorative plant");
        }
        helper.succeed();
    }

    private static void placeConfiguredPlant(GameTestHelper helper, PlantDefinition plant, BlockPos relativeOrigin) {
        BlockPos origin = helper.absolutePos(relativeOrigin);
        Block expectedBlock = PandorasCreaturesCommon.platform().registry().block(plant.blockId());
        Registry<ConfiguredFeature<?, ?>> features = helper.getLevel().registryAccess()
                .lookupOrThrow(Registries.CONFIGURED_FEATURE);
        ConfiguredFeature<?, ?> configuredFeature = features.getValue(ResourceLocation.fromNamespaceAndPath(
                "pandoras_creatures", plant.featureId()));
        helper.assertTrue(configuredFeature != null, "Configured plant feature should load: " + plant.featureId());

        for (int x = -8; x <= 8; x++) {
            for (int z = -8; z <= 8; z++) {
                helper.getLevel().setBlockAndUpdate(origin.offset(x, -1, z), Blocks.GRASS_BLOCK.defaultBlockState());
                for (int y = 0; y <= 3; y++) {
                    helper.getLevel().setBlockAndUpdate(origin.offset(x, y, z), Blocks.AIR.defaultBlockState());
                }
            }
        }

        boolean placed = configuredFeature.place(
                helper.getLevel(), helper.getLevel().getChunkSource().getGenerator(), RandomSource.create(42L), origin);
        helper.assertTrue(placed, "Configured feature should place " + plant.blockId());

        int placedBlocks = 0;
        for (int x = -8; x <= 8; x++) {
            for (int z = -8; z <= 8; z++) {
                for (int y = -3; y <= 3; y++) {
                    if (helper.getLevel().getBlockState(origin.offset(x, y, z)).is(expectedBlock)) {
                        placedBlocks++;
                    }
                }
            }
        }
        helper.assertTrue(placedBlocks > 0, "Configured feature should generate blocks for " + plant.blockId());
    }

    private static boolean matches(Holder<Biome> biome, String selector) {
        if (selector.startsWith("#")) {
            TagKey<Biome> tag = TagKey.create(Registries.BIOME, ResourceLocation.parse(selector.substring(1)));
            return biome.is(tag);
        }
        ResourceLocation biomeId = ResourceLocation.parse(selector);
        return biome.unwrapKey().map(key -> key.location().equals(biomeId)).orElse(false);
    }

    private record PlantDefinition(String featureId, String blockId) {
    }
}
