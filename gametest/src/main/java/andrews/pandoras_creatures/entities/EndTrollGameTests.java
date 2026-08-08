package andrews.pandoras_creatures.entities;

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
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;

import com.mojang.authlib.GameProfile;

import java.util.UUID;

public final class EndTrollGameTests {
    private static final String END_TROLL_BATCH = "end_troll";
    private static final String SHARED_TEMPLATE = "gametest/bufflon_arena";
    private static final BlockPos END_TROLL_POS = new BlockPos(3, 2, 3);
    private static final BlockPos TARGET_POS = new BlockPos(6, 2, 3);

    private EndTrollGameTests() {
    }

    // 1.20.1: makeMockPlayer() devuelve un jugador creativo y el End Troll ignora
    // jugadores creativos (EndTrollEntity: !player.isCreative()). La variante
    // makeMockPlayer(GameType) recien existe en 1.20.3+, asi que replicamos el mock
    // vanilla en modo survival.
    private static Player makeSurvivalMockPlayer(GameTestHelper helper) {
        return new Player(helper.getLevel(), BlockPos.ZERO, 0.0F,
                new GameProfile(UUID.randomUUID(), "test-mock-player")) {
            @Override
            public boolean isSpectator() {
                return false;
            }

            @Override
            public boolean isCreative() {
                return false;
            }
        };
    }

    public static void saveDataRestoresStandingAndScreamState(GameTestHelper helper) {
        EndTrollEntity endTroll = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.END_TROLL), END_TROLL_POS);

        endTroll.setEntityStanding(true);
        endTroll.setHasScreamed(true);

        CompoundTag savedData = new CompoundTag();
        endTroll.addAdditionalSaveData(savedData);

        EndTrollEntity restored = new EndTrollEntity(PCGameTestRegistry.entityType(PCEntityIds.END_TROLL), helper.getLevel());
        restored.readAdditionalSaveData(savedData);

        helper.assertTrue(restored.isEntityStanding(), "Restored End Troll should preserve standing state");
        helper.assertTrue(restored.hasScreamed(), "Restored End Troll should preserve scream state");
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

        helper.assertTrue((endTroll.getShootCooldown()) == (initialShootCooldown - 1), "shoot cooldown after aiStep" + ": expected " + (initialShootCooldown - 1) + ", got " + (endTroll.getShootCooldown()));
        helper.assertTrue((endTroll.getScreamCooldown()) == (initialScreamCooldown - 1), "scream cooldown after aiStep" + ": expected " + (initialScreamCooldown - 1) + ", got " + (endTroll.getScreamCooldown()));
        helper.succeed();
    }

    public static void punchAttackHurtsLivingTarget(GameTestHelper helper) {
        EndTrollEntity endTroll = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.END_TROLL), END_TROLL_POS);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);
        float initialHealth = target.getHealth();

        boolean attackSucceeded = endTroll.performPunchAttack(target, false);

        helper.assertTrue(attackSucceeded, "End Troll punch attack should report a successful hit");
        helper.assertTrue(target.getHealth() < initialHealth, "End Troll punch attack should damage the target");
        helper.succeed();
    }

    public static void playPunchAnimationUpdatesServerAnimationState(GameTestHelper helper) {
        EndTrollEntity endTroll = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.END_TROLL), END_TROLL_POS);

        endTroll.playPunchAnimation(EndTrollPunchAnimation.RIGHT);

        helper.assertTrue(endTroll.isAnimationPlaying(EndTrollEntity.RIGHT_PUNCH_ANIMATION),
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

        helper.assertTrue(attackGoal.canContinueToUse(),
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

        helper.assertTrue(attackGoal.canContinueToUse(),
                "End Troll melee goal should remain active when the target is already inside punch reach");
        helper.succeed();
    }

    public static void transformAnimationCompletesAndSetsStandingOnServer(GameTestHelper helper) {
        EndTrollEntity endTroll = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.END_TROLL), END_TROLL_POS);

        PandorasCreaturesCommon.platform().entities().syncAnimation(endTroll, EndTrollEntity.TRANSFORM_ANIMATION);
        for (int i = 0; i < EndTrollEntity.TRANSFORM_ANIMATION.getAnimationTickDuration(); i++) {
            endTroll.tick();
        }

        helper.assertTrue(endTroll.isEntityStanding(), "End Troll should become standing after transform animation completes");
        helper.assertTrue(endTroll.isAnimationPlaying(EndTrollEntity.BLANK_ANIMATION),
                "End Troll should return to the blank animation after transform completes");
        helper.succeed();
    }

    public static void liberationAdvancementIsLoaded(GameTestHelper helper) {
        // 1.20.2+: ServerAdvancementManager#get devuelve AdvancementHolder (antes Advancement via getAdvancement)
        AdvancementHolder advancement = helper.getLevel().getServer().getAdvancements()
                .get(Reference.id(PCAdvancements.FREE_THE_END_TROLL));

        helper.assertTrue(advancement != null, "End Troll liberation advancement should be present in the loaded advancement tree");
        helper.succeed();
    }

    public static void invalidTargetClearsCombatAnimationDuringAiStep(GameTestHelper helper) {
        EndTrollEntity endTroll = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.END_TROLL), END_TROLL_POS);

        endTroll.setEntityStanding(true);
        endTroll.setHasScreamed(true);
        endTroll.playPunchAnimation(EndTrollPunchAnimation.RIGHT);
        endTroll.setTarget(null);

        endTroll.aiStep();

        helper.assertTrue(endTroll.isAnimationPlaying(EndTrollEntity.BLANK_ANIMATION),
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

        helper.assertTrue(endTroll.getTarget() == null, "End Troll should clear its combat target in peaceful difficulty");
        helper.assertTrue(endTroll.isAnimationPlaying(EndTrollEntity.BLANK_ANIMATION),
                "End Troll should stop combat animations in peaceful difficulty");
        helper.getLevel().getServer().setDifficulty(Difficulty.NORMAL, true);
        helper.succeed();
    }

    public static void deadTargetResetsEncounterStateForNextCombat(GameTestHelper helper) {
        EndTrollEntity endTroll = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.END_TROLL), END_TROLL_POS);
        Player firstTarget = makeSurvivalMockPlayer(helper);

        firstTarget.moveTo(helper.absolutePos(TARGET_POS), 0.0F, 0.0F);
        endTroll.setEntityStanding(true);
        endTroll.setHasScreamed(true);
        endTroll.setTarget(firstTarget);
        endTroll.resetShootCooldown();
        endTroll.resetScreamCooldown();

        endTroll.aiStep();
        endTroll.playPunchAnimation(EndTrollPunchAnimation.RIGHT);
        firstTarget.kill();

        endTroll.aiStep();

        helper.assertTrue(endTroll.getTarget() == null, "End Troll should clear a dead combat target");
        helper.assertFalse(endTroll.hasScreamed(), "End Troll should reset encounter scream state when combat target dies");
        helper.assertTrue(endTroll.isAnimationPlaying(EndTrollEntity.BLANK_ANIMATION),
                "End Troll should clear lingering combat animations when combat target dies");
        helper.assertTrue((endTroll.getShootCooldown()) == (300), "shoot cooldown after dead target reset" + ": expected " + (300) + ", got " + (endTroll.getShootCooldown()));
        helper.assertTrue((endTroll.getScreamCooldown()) == (400), "scream cooldown after dead target reset" + ": expected " + (400) + ", got " + (endTroll.getScreamCooldown()));
        helper.succeed();
    }

    public static void freshPlayerAfterDeathStartsFreshEncounter(GameTestHelper helper) {
        EndTrollEntity endTroll = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.END_TROLL), END_TROLL_POS);
        Player firstTarget = makeSurvivalMockPlayer(helper);
        Player secondTarget = makeSurvivalMockPlayer(helper);

        firstTarget.moveTo(helper.absolutePos(TARGET_POS), 0.0F, 0.0F);
        secondTarget.moveTo(helper.absolutePos(TARGET_POS.offset(1, 0, 0)), 0.0F, 0.0F);

        endTroll.setEntityStanding(true);
        endTroll.setHasScreamed(true);
        endTroll.setTarget(firstTarget);
        firstTarget.kill();

        endTroll.aiStep();
        endTroll.setTarget(secondTarget);
        endTroll.aiStep();

        helper.assertTrue(endTroll.isAnimationPlaying(EndTrollEntity.SCREAM_ANIMATION),
                "End Troll should restart a clean encounter against a newly respawned player target");
        helper.succeed();
    }
}
