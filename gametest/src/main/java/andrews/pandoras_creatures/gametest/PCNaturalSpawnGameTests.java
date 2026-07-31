package andrews.pandoras_creatures.gametest;

import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.test.PCGameTestRegistry;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;

public final class PCNaturalSpawnGameTests {
    private static final BlockPos FIXTURE_POS = new BlockPos(3, 2, 3);
    private static final int NATURAL_SPAWN_ATTEMPTS = 512;

    private PCNaturalSpawnGameTests() {
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

        helper.runAfterDelay(20L, () -> runNaturalSpawnCycle(helper, spawnPos, arachnon));
    }

    private static void runNaturalSpawnCycle(GameTestHelper helper, BlockPos spawnPos, EntityType<?> arachnon) {
        helper.assertTrue(helper.getLevel().getDifficulty() != Difficulty.PEACEFUL,
                "Natural hostile spawning requires a non-peaceful GameTest server");
        LevelData.RespawnData respawnData = helper.getLevel().getRespawnData();
        helper.assertTrue(respawnData.dimension() != helper.getLevel().dimension()
                        || !respawnData.pos().closerToCenterThan(spawnPos.getCenter(), 24.0),
                "Natural spawn fixture must be more than 24 blocks from the world spawn point");

        ServerPlayer player = helper.makeMockServerPlayerInLevel();
        List<Mob> naturallySpawned = new ArrayList<>();
        int[] eligibleCandidates = {0};
        try {
            player.snapTo(spawnPos.getX() + 32.5, spawnPos.getY(), spawnPos.getZ() + 0.5, 0.0F, 0.0F);
            assertNaturalSpawnFixture(helper, spawnPos, arachnon, player);
            spawnArachnonNaturally(helper, spawnPos, arachnon, naturallySpawned, eligibleCandidates);
            assertNaturalSpawnResult(helper, arachnon, naturallySpawned, eligibleCandidates[0]);
        } finally {
            naturallySpawned.forEach(Mob::discard);
            helper.getLevel().getServer().getPlayerList().remove(player);
        }
        helper.succeed();
    }

    private static void spawnArachnonNaturally(
            GameTestHelper helper,
            BlockPos spawnPos,
            EntityType<?> arachnon,
            List<Mob> naturallySpawned,
            int[] eligibleCandidates
    ) {
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
    }

    private static void assertNaturalSpawnFixture(
            GameTestHelper helper, BlockPos spawnPos, EntityType<?> arachnon, ServerPlayer player) {
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
                .anyMatch(weightedSpawner -> weightedSpawner.value().type() == entityType);
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

    private static void assertNaturalSpawnResult(
            GameTestHelper helper, EntityType<?> arachnon, List<Mob> naturallySpawned, int eligibleCandidates) {
        helper.assertTrue(!naturallySpawned.isEmpty(),
                "Vanilla natural spawning should create Arachnon in a dark permitted biome; eligible candidates="
                        + eligibleCandidates);
        Mob spawned = naturallySpawned.get(0);
        helper.assertTrue(spawned.getType() == arachnon, "Natural spawn callback should receive Arachnon");
        helper.assertTrue(helper.getLevel().getEntity(spawned.getUUID()) == spawned,
                "Naturally spawned Arachnon should be added to the server level");
    }
}
