package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.entities.arachnon.ArachnonAttackRules;
import andrews.pandoras_creatures.entities.arachnon.ArachnonSpawnTuning;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.test.PCGameTestRegistry;
import andrews.pandoras_creatures.registry.entity.PCEntitySpawnRules;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;

public final class ArachnonGameTests {
    private static final String ARACHNON_BATCH = "arachnon";
    private static final String SHARED_TEMPLATE = "gametest/bufflon_arena";
    private static final BlockPos ARACHNON_POS = new BlockPos(3, 2, 3);
    private static final BlockPos TARGET_POS = new BlockPos(7, 2, 3);

    private ArachnonGameTests() {
    }

    public static void doHurtTargetStartsAttackTimerAndDamagesTarget(GameTestHelper helper) {
        ArachnonEntity arachnon = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.ARACHNON), ARACHNON_POS);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);

        boolean hurt = arachnon.doHurtTarget(target);

        helper.assertTrue(hurt, "Arachnon attack should damage the target");
        helper.assertTrue((arachnon.getAttackTimer()) == (ArachnonAttackRules.ATTACK_TIMER_TICKS), "arachnon attack timer" + ": expected " + (ArachnonAttackRules.ATTACK_TIMER_TICKS) + ", got " + (arachnon.getAttackTimer()));
        helper.assertTrue(target.getHealth() < target.getMaxHealth(), "Arachnon attack should lower target health");
        helper.succeed();
    }

    public static void aiStepTicksAttackTimerDown(GameTestHelper helper) {
        ArachnonEntity arachnon = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.ARACHNON), ARACHNON_POS);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);
        arachnon.doHurtTarget(target);

        arachnon.aiStep();

        helper.assertTrue((arachnon.getAttackTimer()) == (ArachnonAttackRules.ATTACK_TIMER_TICKS - 1), "arachnon attack timer after aiStep" + ": expected " + (ArachnonAttackRules.ATTACK_TIMER_TICKS - 1) + ", got " + (arachnon.getAttackTimer()));
        helper.succeed();
    }

    public static void officialBiomeSpawnListIncludesArachnon(GameTestHelper helper) {
        Registry<Biome> biomes = helper.getLevel().registryAccess().registryOrThrow(Registries.BIOME);
        Biome plains = biomes.get(Biomes.PLAINS.location());
        helper.assertTrue(plains != null, "Minecraft plains biome should be registered");

        MobSpawnSettings.SpawnerData arachnonSpawner = plains.getMobSettings()
                .getMobs(MobCategory.MONSTER)
                .unwrap()
                .stream()
                .filter(candidate -> candidate.type == PCGameTestRegistry.entityType(PCEntityIds.ARACHNON))
                .findFirst()
                .orElse(null);

        helper.assertTrue(arachnonSpawner != null, "Arachnon should be injected into the official plains spawn list");
        helper.assertTrue((arachnonSpawner.getWeight().asInt()) == (ArachnonSpawnTuning.overworldSpawnWeight()), "arachnon overworld spawn weight" + ": expected " + (ArachnonSpawnTuning.overworldSpawnWeight()) + ", got " + (arachnonSpawner.getWeight().asInt()));
        helper.assertTrue((arachnonSpawner.minCount) == (ArachnonSpawnTuning.minSpawnGroup()), "arachnon spawn min count" + ": expected " + (ArachnonSpawnTuning.minSpawnGroup()) + ", got " + (arachnonSpawner.minCount));
        helper.assertTrue((arachnonSpawner.maxCount) == (ArachnonSpawnTuning.maxSpawnGroup()), "arachnon spawn max count" + ": expected " + (ArachnonSpawnTuning.maxSpawnGroup()) + ", got " + (arachnonSpawner.maxCount));
        helper.succeed();
    }

    public static void spawnPlacementMatchesSharedArachnonRule(GameTestHelper helper) {
        BlockPos spawnPos = helper.absolutePos(ARACHNON_POS.offset(10, 0, 10));

        helper.getLevel().getServer().setDifficulty(Difficulty.NORMAL, true);
        helper.getLevel().setDayTime(18000L);
        helper.getLevel().setBlockAndUpdate(spawnPos.below(), Blocks.GRASS_BLOCK.defaultBlockState());
        helper.getLevel().setBlockAndUpdate(spawnPos, Blocks.AIR.defaultBlockState());
        helper.getLevel().setBlockAndUpdate(spawnPos.above(), Blocks.AIR.defaultBlockState());

        helper.runAfterDelay(2L, () -> {
            int brightness = helper.getLevel().getRawBrightness(spawnPos, 0);
            boolean expected = PCEntitySpawnRules.canSpawnArachnon(helper.getLevel().getDifficulty() != Difficulty.PEACEFUL, brightness);
            boolean actual = SpawnPlacements.checkSpawnRules(PCGameTestRegistry.entityType(PCEntityIds.ARACHNON), helper.getLevel(), MobSpawnType.NATURAL, spawnPos, helper.getLevel().getRandom());

            helper.assertTrue((actual) == (expected), "Arachnon spawn placement should mirror the shared spawn rule at brightness " + brightness + ": expected " + (expected) + ", got " + (actual));
            helper.succeed();
        });
    }
}
