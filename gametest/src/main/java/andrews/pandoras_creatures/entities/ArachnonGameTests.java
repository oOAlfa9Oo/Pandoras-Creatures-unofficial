package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.entities.arachnon.ArachnonAttackRules;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.test.PCGameTestRegistry;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;

public final class ArachnonGameTests {
    private static final String ARACHNON_BATCH = "arachnon";
    private static final String SHARED_TEMPLATE = "gametest/bufflon_arena";
    private static final BlockPos ARACHNON_POS = new BlockPos(3, 2, 3);
    private static final BlockPos TARGET_POS = new BlockPos(7, 2, 3);

    private ArachnonGameTests() {
    }

    @GameTest(template = SHARED_TEMPLATE, batch = ARACHNON_BATCH)
    public static void doHurtTargetStartsAttackTimerAndDamagesTarget(GameTestHelper helper) {
        ArachnonEntity arachnon = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.ARACHNON), ARACHNON_POS);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);

        boolean hurt = arachnon.doHurtTarget(target);

        helper.assertTrue(hurt, "Arachnon attack should damage the target");
        helper.assertValueEqual(arachnon.getAttackTimer(), ArachnonAttackRules.ATTACK_TIMER_TICKS, "arachnon attack timer");
        helper.assertTrue(target.getHealth() < target.getMaxHealth(), "Arachnon attack should lower target health");
        helper.succeed();
    }

    @GameTest(template = SHARED_TEMPLATE, batch = ARACHNON_BATCH)
    public static void aiStepTicksAttackTimerDown(GameTestHelper helper) {
        ArachnonEntity arachnon = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.ARACHNON), ARACHNON_POS);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);
        arachnon.doHurtTarget(target);

        arachnon.aiStep();

        helper.assertValueEqual(arachnon.getAttackTimer(), ArachnonAttackRules.ATTACK_TIMER_TICKS - 1, "arachnon attack timer after aiStep");
        helper.succeed();
    }
}
