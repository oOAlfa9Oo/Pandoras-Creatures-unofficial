package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.entities.end_troll.EndTrollPunchAnimation;
import andrews.pandoras_creatures.registry.PCEntities;
import andrews.pandoras_creatures.util.NetworkUtil;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;

@GameTestHolder(Reference.MODID)
@PrefixGameTestTemplate(false)
public final class EndTrollGameTests {
    private static final String END_TROLL_BATCH = "end_troll";
    private static final String SHARED_TEMPLATE = "gametest/bufflon_arena";
    private static final BlockPos END_TROLL_POS = new BlockPos(3, 2, 3);
    private static final BlockPos TARGET_POS = new BlockPos(6, 2, 3);

    private EndTrollGameTests() {
    }

    @GameTest(template = SHARED_TEMPLATE, batch = END_TROLL_BATCH)
    public static void saveDataRestoresStandingAndScreamState(GameTestHelper helper) {
        EndTrollEntity endTroll = helper.spawn(PCEntities.END_TROLL.get(), END_TROLL_POS);

        endTroll.setEntityStanding(true);
        endTroll.setHasScreamed(true);

        CompoundTag savedData = new CompoundTag();
        endTroll.addAdditionalSaveData(savedData);

        EndTrollEntity restored = new EndTrollEntity(PCEntities.END_TROLL.get(), helper.getLevel());
        restored.readAdditionalSaveData(savedData);

        helper.assertTrue(restored.isEntityStanding(), "Restored End Troll should preserve standing state");
        helper.assertTrue(restored.hasScreamed(), "Restored End Troll should preserve scream state");
        helper.succeed();
    }

    @GameTest(template = SHARED_TEMPLATE, batch = END_TROLL_BATCH)
    public static void standingAiTicksCombatCooldowns(GameTestHelper helper) {
        EndTrollEntity endTroll = helper.spawn(PCEntities.END_TROLL.get(), END_TROLL_POS);

        endTroll.setEntityStanding(true);
        endTroll.setHasScreamed(true);
        endTroll.resetShootCooldown();
        endTroll.resetScreamCooldown();
        int initialShootCooldown = endTroll.getShootCooldown();
        int initialScreamCooldown = endTroll.getScreamCooldown();

        endTroll.aiStep();

        helper.assertValueEqual(endTroll.getShootCooldown(), initialShootCooldown - 1, "shoot cooldown after aiStep");
        helper.assertValueEqual(endTroll.getScreamCooldown(), initialScreamCooldown - 1, "scream cooldown after aiStep");
        helper.succeed();
    }

    @GameTest(template = SHARED_TEMPLATE, batch = END_TROLL_BATCH)
    public static void punchAttackHurtsLivingTarget(GameTestHelper helper) {
        EndTrollEntity endTroll = helper.spawn(PCEntities.END_TROLL.get(), END_TROLL_POS);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);
        float initialHealth = target.getHealth();

        boolean attackSucceeded = endTroll.performPunchAttack(target, false);

        helper.assertTrue(attackSucceeded, "End Troll punch attack should report a successful hit");
        helper.assertTrue(target.getHealth() < initialHealth, "End Troll punch attack should damage the target");
        helper.succeed();
    }

    @GameTest(template = SHARED_TEMPLATE, batch = END_TROLL_BATCH)
    public static void playPunchAnimationUpdatesServerAnimationState(GameTestHelper helper) {
        EndTrollEntity endTroll = helper.spawn(PCEntities.END_TROLL.get(), END_TROLL_POS);

        endTroll.playPunchAnimation(EndTrollPunchAnimation.RIGHT);

        helper.assertTrue(endTroll.isAnimationPlaying(EndTrollEntity.RIGHT_PUNCH_ANIMATION),
                "End Troll should track punch animation state on the server");
        helper.succeed();
    }

    @GameTest(template = SHARED_TEMPLATE, batch = END_TROLL_BATCH)
    public static void transformAnimationCompletesAndSetsStandingOnServer(GameTestHelper helper) {
        EndTrollEntity endTroll = helper.spawn(PCEntities.END_TROLL.get(), END_TROLL_POS);

        NetworkUtil.sendAnimationPacket(endTroll, EndTrollEntity.TRANSFORM_ANIMATION);
        for (int i = 0; i < EndTrollEntity.TRANSFORM_ANIMATION.getAnimationTickDuration(); i++) {
            endTroll.tick();
        }

        helper.assertTrue(endTroll.isEntityStanding(), "End Troll should become standing after transform animation completes");
        helper.assertTrue(endTroll.isAnimationPlaying(EndTrollEntity.BLANK_ANIMATION),
                "End Troll should return to the blank animation after transform completes");
        helper.succeed();
    }
}
