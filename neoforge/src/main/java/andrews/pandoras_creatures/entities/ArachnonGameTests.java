package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.entities.arachnon.ArachnonAttackRules;
import andrews.pandoras_creatures.registry.PCEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.cow.Cow;

public final class ArachnonGameTests {
    private static final BlockPos ARACHNON_POS = new BlockPos(3, 2, 3);
    private static final BlockPos TARGET_POS = new BlockPos(7, 2, 3);

    private ArachnonGameTests() {
    }

    public static void doHurtTargetStartsAttackTimerAndDamagesTarget(GameTestHelper helper) {
        ArachnonEntity arachnon = helper.spawn(PCEntities.ARACHNON.get(), ARACHNON_POS);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);

        boolean hurt = arachnon.doHurtTarget(helper.getLevel(), target);

        helper.assertTrue(hurt, "Arachnon attack should damage the target");
        helper.assertValueEqual(arachnon.getAttackTimer(), ArachnonAttackRules.ATTACK_TIMER_TICKS, "arachnon attack timer");
        helper.assertTrue(target.getHealth() < target.getMaxHealth(), "Arachnon attack should lower target health");
        helper.succeed();
    }

    public static void aiStepTicksAttackTimerDown(GameTestHelper helper) {
        ArachnonEntity arachnon = helper.spawn(PCEntities.ARACHNON.get(), ARACHNON_POS);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);
        arachnon.doHurtTarget(helper.getLevel(), target);

        arachnon.aiStep();

        helper.assertValueEqual(arachnon.getAttackTimer(), ArachnonAttackRules.ATTACK_TIMER_TICKS - 1, "arachnon attack timer after aiStep");
        helper.succeed();
    }
}
