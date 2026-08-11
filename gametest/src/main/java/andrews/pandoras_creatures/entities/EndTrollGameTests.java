package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.test.PCGameTestAssertions;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.advancement.PCAdvancements;
import andrews.pandoras_creatures.entities.end_troll.EndTrollPunchAnimation;
import andrews.pandoras_creatures.entities.goals.end_troll.EndTrollAttackGoal;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.test.PCGameTestRegistry;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;

public final class EndTrollGameTests {
    private static final String END_TROLL_BATCH = "end_troll";
    private static final String SHARED_TEMPLATE = "gametest/bufflon_arena";
    private static final BlockPos END_TROLL_POS = new BlockPos(3, 2, 3);
    private static final BlockPos TARGET_POS = new BlockPos(6, 2, 3);

    private EndTrollGameTests() {
    }

    public static void saveDataRestoresStandingAndScreamState(GameTestHelper helper) {
        EndTrollEntity endTroll = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.END_TROLL), END_TROLL_POS);

        endTroll.setEntityStanding(true);
        endTroll.setHasScreamed(true);

        CompoundTag savedData = new CompoundTag();
        endTroll.addAdditionalSaveData(savedData);

        EndTrollEntity restored = new EndTrollEntity(PCGameTestRegistry.entityType(PCEntityIds.END_TROLL), helper.getLevel());
        restored.readAdditionalSaveData(savedData);

        PCGameTestAssertions.assertTrue(helper, restored.isEntityStanding(), "Restored End Troll should preserve standing state");
        PCGameTestAssertions.assertTrue(helper, restored.hasScreamed(), "Restored End Troll should preserve scream state");
        helper.succeed();
    }

    public static void standingAiTicksCombatCooldowns(GameTestHelper helper) {
        EndTrollEntity endTroll = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.END_TROLL), END_TROLL_POS);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);

        endTroll.setEntityStanding(true);
        endTroll.setHasScreamed(true);
        endTroll.setTarget(target);
        endTroll.resetShootCooldown();
        endTroll.resetScreamCooldown();
        int initialShootCooldown = endTroll.getShootCooldown();
        int initialScreamCooldown = endTroll.getScreamCooldown();

        endTroll.aiStep();

        PCGameTestAssertions.assertValueEqual(helper, endTroll.getShootCooldown(), initialShootCooldown - 1, "shoot cooldown after aiStep");
        PCGameTestAssertions.assertValueEqual(helper, endTroll.getScreamCooldown(), initialScreamCooldown - 1, "scream cooldown after aiStep");
        helper.succeed();
    }

    public static void punchAttackHurtsLivingTarget(GameTestHelper helper) {
        EndTrollEntity endTroll = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.END_TROLL), END_TROLL_POS);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);
        float initialHealth = target.getHealth();

        boolean attackSucceeded = endTroll.performPunchAttack(target, false);

        PCGameTestAssertions.assertTrue(helper, attackSucceeded, "End Troll punch attack should report a successful hit");
        PCGameTestAssertions.assertTrue(helper, target.getHealth() < initialHealth, "End Troll punch attack should damage the target");
        helper.succeed();
    }

    public static void playPunchAnimationUpdatesServerAnimationState(GameTestHelper helper) {
        EndTrollEntity endTroll = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.END_TROLL), END_TROLL_POS);

        endTroll.playPunchAnimation(EndTrollPunchAnimation.RIGHT);

        PCGameTestAssertions.assertTrue(helper, endTroll.isAnimationPlaying(EndTrollEntity.RIGHT_PUNCH_ANIMATION),
                "End Troll should track punch animation state on the server");
        helper.succeed();
    }

    public static void meleeGoalKeepsRunningWhilePunchAnimationIsActive(GameTestHelper helper) {
        EndTrollEntity endTroll = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.END_TROLL), END_TROLL_POS);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);
        EndTrollAttackGoal attackGoal = new EndTrollAttackGoal(endTroll, 0.3D, false);

        endTroll.setEntityStanding(true);
        endTroll.setHasScreamed(true);
        endTroll.setTarget(target);
        endTroll.playPunchAnimation(EndTrollPunchAnimation.RIGHT);

        PCGameTestAssertions.assertTrue(helper, attackGoal.canContinueToUse(),
                "End Troll melee goal should stay active while a punch animation is still resolving");
        helper.succeed();
    }

    public static void meleeGoalKeepsRunningAtCloseRangeWithoutPath(GameTestHelper helper) {
        EndTrollEntity endTroll = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.END_TROLL), END_TROLL_POS);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);
        EndTrollAttackGoal attackGoal = new EndTrollAttackGoal(endTroll, 0.3D, false);

        endTroll.setEntityStanding(true);
        endTroll.setHasScreamed(true);
        endTroll.setTarget(target);

        PCGameTestAssertions.assertTrue(helper, attackGoal.canContinueToUse(),
                "End Troll melee goal should remain active when the target is already inside punch reach");
        helper.succeed();
    }

    public static void transformAnimationCompletesAndSetsStandingOnServer(GameTestHelper helper) {
        EndTrollEntity endTroll = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.END_TROLL), END_TROLL_POS);

        PandorasCreaturesCommon.platform().entities().syncAnimation(endTroll, EndTrollEntity.TRANSFORM_ANIMATION);
        for (int i = 0; i < EndTrollEntity.TRANSFORM_ANIMATION.getAnimationTickDuration(); i++) {
            endTroll.tick();
        }

        PCGameTestAssertions.assertTrue(helper, endTroll.isEntityStanding(), "End Troll should become standing after transform animation completes");
        PCGameTestAssertions.assertTrue(helper, endTroll.isAnimationPlaying(EndTrollEntity.BLANK_ANIMATION),
                "End Troll should return to the blank animation after transform completes");
        helper.succeed();
    }

    public static void liberationAdvancementIsLoaded(GameTestHelper helper) {
        AdvancementHolder advancement = helper.getLevel().getServer().getAdvancements()
                .get(ResourceLocation.fromNamespaceAndPath(Reference.MODID, PCAdvancements.FREE_THE_END_TROLL));

        PCGameTestAssertions.assertTrue(helper, advancement != null, "End Troll liberation advancement should be present in the loaded advancement tree");
        helper.succeed();
    }

    public static void invalidTargetClearsCombatAnimationDuringAiStep(GameTestHelper helper) {
        EndTrollEntity endTroll = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.END_TROLL), END_TROLL_POS);

        endTroll.setEntityStanding(true);
        endTroll.setHasScreamed(true);
        endTroll.playPunchAnimation(EndTrollPunchAnimation.RIGHT);
        endTroll.setTarget(null);

        endTroll.aiStep();

        PCGameTestAssertions.assertTrue(helper, endTroll.isAnimationPlaying(EndTrollEntity.BLANK_ANIMATION),
                "End Troll should reset combat animation when it no longer has a valid target");
        helper.succeed();
    }

    public static void peacefulDifficultyClearsCombatState(GameTestHelper helper) {
        EndTrollEntity endTroll = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.END_TROLL), END_TROLL_POS);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);

        endTroll.setEntityStanding(true);
        endTroll.setHasScreamed(true);
        endTroll.setTarget(target);
        endTroll.playPunchAnimation(EndTrollPunchAnimation.RIGHT);
        helper.getLevel().getServer().setDifficulty(Difficulty.PEACEFUL, true);

        endTroll.aiStep();

        PCGameTestAssertions.assertTrue(helper, endTroll.getTarget() == null, "End Troll should clear its combat target in peaceful difficulty");
        PCGameTestAssertions.assertTrue(helper, endTroll.isAnimationPlaying(EndTrollEntity.BLANK_ANIMATION),
                "End Troll should stop combat animations in peaceful difficulty");
        helper.getLevel().getServer().setDifficulty(Difficulty.NORMAL, true);
        helper.succeed();
    }

    public static void deadTargetResetsEncounterStateForNextCombat(GameTestHelper helper) {
        EndTrollEntity endTroll = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.END_TROLL), END_TROLL_POS);
        Player firstTarget = helper.makeMockPlayer(GameType.SURVIVAL);

        firstTarget.snapTo(helper.absolutePos(TARGET_POS), 0.0F, 0.0F);
        endTroll.setEntityStanding(true);
        endTroll.setHasScreamed(true);
        endTroll.setTarget(firstTarget);
        endTroll.resetShootCooldown();
        endTroll.resetScreamCooldown();

        endTroll.aiStep();
        endTroll.playPunchAnimation(EndTrollPunchAnimation.RIGHT);
        firstTarget.kill((net.minecraft.server.level.ServerLevel) firstTarget.level());

        endTroll.aiStep();

        PCGameTestAssertions.assertTrue(helper, endTroll.getTarget() == null, "End Troll should clear a dead combat target");
        PCGameTestAssertions.assertFalse(helper, endTroll.hasScreamed(), "End Troll should reset encounter scream state when combat target dies");
        PCGameTestAssertions.assertTrue(helper, endTroll.isAnimationPlaying(EndTrollEntity.BLANK_ANIMATION),
                "End Troll should clear lingering combat animations when combat target dies");
        PCGameTestAssertions.assertValueEqual(helper, endTroll.getShootCooldown(), 300, "shoot cooldown after dead target reset");
        PCGameTestAssertions.assertValueEqual(helper, endTroll.getScreamCooldown(), 400, "scream cooldown after dead target reset");
        helper.succeed();
    }

    public static void freshPlayerAfterDeathStartsFreshEncounter(GameTestHelper helper) {
        EndTrollEntity endTroll = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.END_TROLL), END_TROLL_POS);
        Player firstTarget = helper.makeMockPlayer(GameType.SURVIVAL);
        Player secondTarget = helper.makeMockPlayer(GameType.SURVIVAL);

        firstTarget.snapTo(helper.absolutePos(TARGET_POS), 0.0F, 0.0F);
        secondTarget.snapTo(helper.absolutePos(TARGET_POS.offset(1, 0, 0)), 0.0F, 0.0F);

        endTroll.setEntityStanding(true);
        endTroll.setHasScreamed(true);
        endTroll.setTarget(firstTarget);
        firstTarget.kill((net.minecraft.server.level.ServerLevel) firstTarget.level());

        endTroll.aiStep();
        endTroll.setTarget(secondTarget);
        endTroll.aiStep();

        PCGameTestAssertions.assertTrue(helper, endTroll.isAnimationPlaying(EndTrollEntity.SCREAM_ANIMATION),
                "End Troll should restart a clean encounter against a newly respawned player target");
        helper.succeed();
    }
}
