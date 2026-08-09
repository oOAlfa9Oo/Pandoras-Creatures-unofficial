package andrews.pandoras_creatures.gametest;

import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.registry.entity.PCEntitySpawnRules;
import andrews.pandoras_creatures.test.PCGameTestRegistry;
import andrews.pandoras_creatures.world.biome.PCBiomeSpawnCatalog;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.ArrayList;
import java.util.List;

public final class PCNaturalSpawnGameTests {
    private static final BlockPos FIXTURE_POS = new BlockPos(3, 2, 3);
    private static final int NATURAL_SPAWN_ATTEMPTS = 512;

    private PCNaturalSpawnGameTests() {
    }

    public static void catalogIsAppliedToRuntimeBiomes(GameTestHelper helper) {
        Registry<Biome> biomes = helper.getLevel().registryAccess().lookupOrThrow(Registries.BIOME);

        for (PCBiomeSpawnCatalog.SpawnDefinition definition : PCBiomeSpawnCatalog.definitions()) {
            EntityType<?> entityType = entityType(definition.entityTypeId());
            List<Holder.Reference<Biome>> selectedBiomes = biomes.listElements()
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
        Registry<Biome> biomes = helper.getLevel().registryAccess().lookupOrThrow(Registries.BIOME);
        EntityType<?> endTroll = PCGameTestRegistry.entityType(PCEntityIds.END_TROLL);

        boolean declaredNaturally = PCBiomeSpawnCatalog.definitions().stream()
                .anyMatch(definition -> definition.entityTypeId().equals("pandoras_creatures:" + PCEntityIds.END_TROLL));
        helper.assertTrue(!declaredNaturally, "End Troll must not be declared in the shared natural spawn catalog");

        for (Holder.Reference<Biome> biome : biomes.listElements().toList()) {
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
        assertPlacement(helper, PCEntityIds.ACIDIC_ARCHVINE, SpawnPlacementTypes.NO_RESTRICTIONS);
        assertPlacement(helper, PCEntityIds.ARACHNON, SpawnPlacementTypes.ON_GROUND);
        assertPlacement(helper, PCEntityIds.BUFFLON, SpawnPlacementTypes.ON_GROUND);
        assertPlacement(helper, PCEntityIds.CRAB, SpawnPlacementTypes.NO_RESTRICTIONS);
        assertPlacement(helper, PCEntityIds.HELLHOUND, SpawnPlacementTypes.ON_GROUND);
        assertPlacement(helper, PCEntityIds.SEAHORSE, SpawnPlacementTypes.IN_WATER);
        helper.succeed();
    }

    public static void arachnonCompletesVanillaNaturalSpawnCycle(GameTestHelper helper) {
        EntityType<?> arachnon = PCGameTestRegistry.entityType(PCEntityIds.ARACHNON);
        BlockPos spawnPos = findArachnonSpawnPosition(helper, arachnon);
        assertRuntimeSpawnEntry(helper, spawnPos, arachnon);

        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                BlockPos floorPos = spawnPos.offset(x, -1, z);
                boolean perimeter = Math.abs(x) == 3 || Math.abs(z) == 3;
                helper.getLevel().setBlockAndUpdate(floorPos, Blocks.GRASS_BLOCK.defaultBlockState());
                helper.getLevel().setBlockAndUpdate(floorPos.above(),
                        perimeter ? Blocks.STONE.defaultBlockState() : Blocks.AIR.defaultBlockState());
                helper.getLevel().setBlockAndUpdate(floorPos.above(2),
                        perimeter ? Blocks.STONE.defaultBlockState() : Blocks.AIR.defaultBlockState());
                helper.getLevel().setBlockAndUpdate(floorPos.above(3), Blocks.STONE.defaultBlockState());
            }
        }

        helper.runAfterDelay(5L, () -> {
            helper.assertTrue(helper.getLevel().getDifficulty() != Difficulty.PEACEFUL,
                    "Natural hostile spawning requires a non-peaceful GameTest server");
            helper.assertTrue(!helper.getLevel().getSharedSpawnPos().closerToCenterThan(spawnPos.getCenter(), 24.0),
                    "Natural spawn fixture must be more than 24 blocks from the world spawn point");

            ServerPlayer player = helper.makeMockServerPlayerInLevel();
            List<Mob> naturallySpawned = new ArrayList<>();
            int[] eligibleCandidates = {0};
            try {
                player.moveTo(spawnPos.getX() + 32.5, spawnPos.getY(), spawnPos.getZ() + 0.5);
                helper.assertTrue(helper.getLevel().getNearestPlayer(
                                spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5, -1.0, false) == player,
                        "Natural spawn fixture should find its mock player");
                helper.assertTrue(SpawnPlacements.isSpawnPositionOk(arachnon, helper.getLevel(), spawnPos),
                        "Arachnon ground placement should accept the fixture position");
                helper.assertTrue(SpawnPlacements.checkSpawnRules(
                                arachnon, helper.getLevel(), EntitySpawnReason.NATURAL, spawnPos, helper.getLevel().getRandom()),
                        "Arachnon natural spawn rule should accept the dark fixture position; raw brightness="
                                + helper.getLevel().getRawBrightness(spawnPos, 0));
                helper.assertTrue(helper.getLevel().noCollision(arachnon.getSpawnAABB(
                                spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5)),
                        "Arachnon spawn box should fit inside the fixture");
                for (int attempt = 0; attempt < NATURAL_SPAWN_ATTEMPTS && naturallySpawned.isEmpty(); attempt++) {
                    NaturalSpawner.spawnCategoryForPosition(
                            MobCategory.MONSTER,
                            helper.getLevel(),
                            helper.getLevel().getChunk(spawnPos),
                            spawnPos,
                            (candidate, pos, chunk) -> {
                                eligibleCandidates[0]++;
                                return naturallySpawned.isEmpty() && candidate == arachnon;
                            },
                            (mob, chunk) -> naturallySpawned.add(mob));
                }

                helper.assertTrue(!naturallySpawned.isEmpty(),
                        "Vanilla natural spawning should create Arachnon in a dark permitted biome; eligible candidates="
                                + eligibleCandidates[0]);
                Mob spawned = naturallySpawned.get(0);
                helper.assertTrue(spawned.getType() == arachnon, "Natural spawn callback should receive Arachnon");
                helper.assertTrue(helper.getLevel().getEntity(spawned.getUUID()) == spawned,
                        "Naturally spawned Arachnon should be added to the server level");
            } finally {
                naturallySpawned.forEach(Mob::discard);
                helper.getLevel().getServer().getPlayerList().remove(player);
            }
            helper.succeed();
        });
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
                false);
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

    private static void assertPlacement(GameTestHelper helper, String entityId, SpawnPlacementType expectedType) {
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
                EntitySpawnReason.NATURAL,
                pos,
                helper.getLevel().getRandom());
        helper.assertTrue(actual == expected, entityId + " runtime placement should match its shared rule");
        helper.succeed();
    }

    private static void assertRuntimeSpawnEntry(GameTestHelper helper, BlockPos pos, EntityType<?> entityType) {
        helper.assertTrue(hasRuntimeSpawnEntry(helper.getLevel().getBiome(pos), entityType),
                "Arachnon should be present in the fixture biome natural spawn table");
    }

    private static boolean hasRuntimeSpawnEntry(Holder<Biome> biome, EntityType<?> entityType) {
        return biome.value().getMobSettings()
                .getMobs(entityType.getCategory())
                .unwrap()
                .stream()
                .anyMatch(spawner -> spawner.type == entityType);
    }

    private static BlockPos findArachnonSpawnPosition(GameTestHelper helper, EntityType<?> arachnon) {
        Pair<BlockPos, Holder<Biome>> located = helper.getLevel().findClosestBiome3d(
                biome -> hasRuntimeSpawnEntry(biome, arachnon),
                helper.absolutePos(FIXTURE_POS),
                6400,
                32,
                64);
        helper.assertTrue(located != null, "GameTest world should contain a biome that permits Arachnon");
        return located.getFirst();
    }

    private static EntityType<?> entityType(String namespacedId) {
        ResourceLocation id = ResourceLocation.parse(namespacedId);
        return PCGameTestRegistry.entityType(id.getPath());
    }

    private static boolean matches(Holder<Biome> biome, String selector) {
        if (selector.startsWith("#")) {
            TagKey<Biome> tag = TagKey.create(Registries.BIOME, ResourceLocation.parse(selector.substring(1)));
            return biome.is(tag);
        }
        ResourceLocation biomeId = ResourceLocation.parse(selector);
        return biome.unwrapKey().map(key -> key.location().equals(biomeId)).orElse(false);
    }
}
