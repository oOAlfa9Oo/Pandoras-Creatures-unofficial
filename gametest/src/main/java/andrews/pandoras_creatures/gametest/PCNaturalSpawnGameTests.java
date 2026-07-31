package andrews.pandoras_creatures.gametest;

import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.registry.entity.PCEntitySpawnRules;
import andrews.pandoras_creatures.test.PCGameTestRegistry;
import andrews.pandoras_creatures.world.biome.PCBiomeSpawnCatalog;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.List;

public final class PCNaturalSpawnGameTests {
    private static final BlockPos FIXTURE_POS = new BlockPos(3, 2, 3);

    private PCNaturalSpawnGameTests() {
    }

    public static void catalogIsAppliedToRuntimeBiomes(GameTestHelper helper) {
        Registry<Biome> biomes = helper.getLevel().registryAccess().registryOrThrow(Registries.BIOME);

        for (PCBiomeSpawnCatalog.SpawnDefinition definition : PCBiomeSpawnCatalog.definitions()) {
            EntityType<?> entityType = entityType(definition.entityTypeId());
            List<Holder.Reference<Biome>> selectedBiomes = biomes.holders()
                    .filter(biome -> definition.biomes().stream().anyMatch(selector -> matches(biome, selector)))
                    .toList();

            helper.assertTrue(!selectedBiomes.isEmpty(), "Spawn definition should select at least one runtime biome: " + definition.name());
            for (Holder.Reference<Biome> biome : selectedBiomes) {
                boolean exactSpawnerPresent = biome.value().getMobSettings()
                        .getMobs(entityType.getCategory())
                        .unwrap()
                        .stream()
                        .anyMatch(spawner -> spawner.type == entityType
                                && spawner.getWeight().asInt() == definition.weight()
                                && spawner.minCount == definition.minCount()
                                && spawner.maxCount == definition.maxCount());
                helper.assertTrue(exactSpawnerPresent,
                        "Runtime biome " + biome.key().location() + " should contain " + definition.name());
            }
        }
        helper.succeed();
    }

    public static void endTrollIsAbsentFromNaturalSpawnTables(GameTestHelper helper) {
        Registry<Biome> biomes = helper.getLevel().registryAccess().registryOrThrow(Registries.BIOME);
        EntityType<?> endTroll = PCGameTestRegistry.entityType(PCEntityIds.END_TROLL);

        boolean declaredNaturally = PCBiomeSpawnCatalog.definitions().stream()
                .anyMatch(definition -> definition.entityTypeId().equals("pandoras_creatures:" + PCEntityIds.END_TROLL));
        helper.assertTrue(!declaredNaturally, "End Troll must not be declared in the shared natural spawn catalog");

        for (Holder.Reference<Biome> biome : biomes.holders().toList()) {
            boolean present = biome.value().getMobSettings()
                    .getMobs(endTroll.getCategory())
                    .unwrap()
                    .stream()
                    .anyMatch(spawner -> spawner.type == endTroll);
            helper.assertTrue(!present, "End Troll must not appear naturally in biome " + biome.key().location());
        }
        helper.succeed();
    }

    public static void naturalSpawnPlacementMetadataMatchesContracts(GameTestHelper helper) {
        assertPlacement(helper, PCEntityIds.ACIDIC_ARCHVINE, SpawnPlacements.Type.NO_RESTRICTIONS);
        assertPlacement(helper, PCEntityIds.ARACHNON, SpawnPlacements.Type.ON_GROUND);
        assertPlacement(helper, PCEntityIds.BUFFLON, SpawnPlacements.Type.ON_GROUND);
        assertPlacement(helper, PCEntityIds.CRAB, SpawnPlacements.Type.NO_RESTRICTIONS);
        assertPlacement(helper, PCEntityIds.HELLHOUND, SpawnPlacements.Type.ON_GROUND);
        assertPlacement(helper, PCEntityIds.SEAHORSE, SpawnPlacements.Type.IN_WATER);
        helper.succeed();
    }

    public static void bufflonPlacementUsesSharedRule(GameTestHelper helper) {
        BlockPos pos = helper.absolutePos(FIXTURE_POS);
        helper.getLevel().setBlockAndUpdate(pos.below(), Blocks.GRASS_BLOCK.defaultBlockState());
        helper.getLevel().setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());

        helper.runAfterDelay(2L, () -> {
            boolean expected = PCEntitySpawnRules.canSpawnBufflon(
                    helper.getLevel().getRawBrightness(pos, 0),
                    helper.getLevel().getBlockState(pos.below()).is(Blocks.GRASS_BLOCK));
            assertSpawnRule(helper, PCEntityIds.BUFFLON, pos, expected);
        });
    }

    public static void crabPlacementUsesSharedRule(GameTestHelper helper) {
        BlockPos pos = helper.absolutePos(FIXTURE_POS);
        helper.getLevel().setBlockAndUpdate(pos.below(), Blocks.SAND.defaultBlockState());
        helper.getLevel().setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());

        boolean expected = PCEntitySpawnRules.canSpawnCrab(
                helper.getLevel().getBiome(pos).is(net.minecraft.world.level.biome.Biomes.BEACH),
                helper.getLevel().getBiome(pos).is(net.minecraft.world.level.biome.Biomes.WARM_OCEAN),
                pos.getY(),
                true,
                false,
                true);
        assertSpawnRule(helper, PCEntityIds.CRAB, pos, expected);
    }

    public static void hellhoundPlacementUsesSharedRule(GameTestHelper helper) {
        BlockPos pos = helper.absolutePos(FIXTURE_POS);
        boolean expected = PCEntitySpawnRules.canSpawnHostileGroundMob(helper.getLevel().getDifficulty() != Difficulty.PEACEFUL);
        assertSpawnRule(helper, PCEntityIds.HELLHOUND, pos, expected);
    }

    public static void seahorsePlacementUsesSharedRule(GameTestHelper helper) {
        BlockPos pos = helper.absolutePos(FIXTURE_POS);
        helper.getLevel().setBlockAndUpdate(pos, Blocks.WATER.defaultBlockState());

        boolean expected = PCEntitySpawnRules.canSpawnSeahorse(helper.getLevel().getFluidState(pos).is(FluidTags.WATER));
        assertSpawnRule(helper, PCEntityIds.SEAHORSE, pos, expected);
    }

    public static void acidicArchvinePlacementUsesSharedRule(GameTestHelper helper) {
        BlockPos pos = helper.absolutePos(FIXTURE_POS);
        helper.getLevel().setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        helper.getLevel().setBlockAndUpdate(pos.above(), Blocks.AIR.defaultBlockState());
        helper.getLevel().setBlockAndUpdate(pos.above(2), Blocks.NETHERRACK.defaultBlockState());

        boolean expected = PCEntitySpawnRules.canSpawnAcidicArchvine(
                helper.getLevel().getDifficulty() != Difficulty.PEACEFUL,
                PCEntitySpawnRules.isJungleArchvineBiome(helper.getLevel(), pos),
                PCEntitySpawnRules.isNetherArchvineBiome(helper.getLevel(), pos),
                pos.getY(),
                helper.getLevel().getBlockState(pos).isAir(),
                helper.getLevel().getBlockState(pos.above()).isAir(),
                PCEntitySpawnRules.hasValidAcidicArchvineCeiling(helper.getLevel(), pos),
                PCEntitySpawnRules.hasConsecutiveAirBelow(
                        helper.getLevel(), pos, PCEntitySpawnRules.acidicArchvineRequiredAirDepth()));
        assertSpawnRule(helper, PCEntityIds.ACIDIC_ARCHVINE, pos, expected);
    }

    private static void assertPlacement(GameTestHelper helper, String entityId, SpawnPlacements.Type expectedType) {
        EntityType<?> entityType = PCGameTestRegistry.entityType(entityId);
        helper.assertTrue(SpawnPlacements.getPlacementType(entityType) == expectedType,
                entityId + " should use placement type " + expectedType);
        helper.assertTrue(SpawnPlacements.getHeightmapType(entityType) == Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                entityId + " should use the shared heightmap contract");
    }

    private static void assertSpawnRule(GameTestHelper helper, String entityId, BlockPos pos, boolean expected) {
        boolean actual = SpawnPlacements.checkSpawnRules(
                PCGameTestRegistry.entityType(entityId),
                helper.getLevel(),
                MobSpawnType.NATURAL,
                pos,
                helper.getLevel().getRandom());
        helper.assertTrue(actual == expected, entityId + " runtime placement should match its shared rule");
        helper.succeed();
    }

    private static EntityType<?> entityType(String namespacedId) {
        ResourceLocation id = new ResourceLocation(namespacedId);
        return PCGameTestRegistry.entityType(id.getPath());
    }

    private static boolean matches(Holder<Biome> biome, String selector) {
        if (selector.startsWith("#")) {
            TagKey<Biome> tag = TagKey.create(Registries.BIOME, new ResourceLocation(selector.substring(1)));
            return biome.is(tag);
        }
        ResourceLocation biomeId = new ResourceLocation(selector);
        return biome.unwrapKey().map(key -> key.location().equals(biomeId)).orElse(false);
    }
}
