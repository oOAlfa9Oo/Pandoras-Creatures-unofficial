package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.test.PCGameTestAssertions;

import andrews.pandoras_creatures.entities.arachnon.ArachnonAttackRules;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.test.PCGameTestRegistry;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.BlockPos;
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

    public static void doHurtTargetStartsAttackTimerAndDamagesTarget(GameTestHelper helper) {
        ArachnonEntity arachnon = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.ARACHNON), ARACHNON_POS);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);

        boolean hurt = arachnon.doHurtTarget((net.minecraft.server.level.ServerLevel) arachnon.level(), target);

        PCGameTestAssertions.assertTrue(helper, hurt, "Arachnon attack should damage the target");
        PCGameTestAssertions.assertValueEqual(helper, arachnon.getAttackTimer(), ArachnonAttackRules.ATTACK_TIMER_TICKS, "arachnon attack timer");
        PCGameTestAssertions.assertTrue(helper, target.getHealth() < target.getMaxHealth(), "Arachnon attack should lower target health");
        helper.succeed();
    }

    public static void aiStepTicksAttackTimerDown(GameTestHelper helper) {
        ArachnonEntity arachnon = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.ARACHNON), ARACHNON_POS);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);
        arachnon.doHurtTarget((net.minecraft.server.level.ServerLevel) arachnon.level(), target);

        arachnon.aiStep();

        PCGameTestAssertions.assertValueEqual(helper, arachnon.getAttackTimer(), ArachnonAttackRules.ATTACK_TIMER_TICKS - 1, "arachnon attack timer after aiStep");
        helper.succeed();
    }
}
