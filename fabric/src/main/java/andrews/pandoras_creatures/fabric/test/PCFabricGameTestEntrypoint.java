package andrews.pandoras_creatures.fabric.test;

import andrews.pandoras_creatures.entities.AcidicArchvineGameTests;
import andrews.pandoras_creatures.entities.ArachnonGameTests;
import andrews.pandoras_creatures.entities.BufflonGameTests;
import andrews.pandoras_creatures.entities.CrabGameTests;
import andrews.pandoras_creatures.gametest.EndPrisonShipGameTests;
import andrews.pandoras_creatures.gametest.EndPrisonStructureGameTests;
import andrews.pandoras_creatures.gametest.EndTrollBoxGameTests;
import andrews.pandoras_creatures.entities.EndTrollGameTests;
import andrews.pandoras_creatures.entities.projectiles.EndTrollProjectileGameTests;
import andrews.pandoras_creatures.entities.HellhoundGameTests;
import andrews.pandoras_creatures.gametest.PCDataLoadGameTests;
import andrews.pandoras_creatures.gametest.PCEntitySpawnGameTests;
import andrews.pandoras_creatures.gametest.PCNaturalSpawnGameTests;
import andrews.pandoras_creatures.gametest.PCPlantGameTests;
import andrews.pandoras_creatures.entities.SeahorseGameTests;
import net.fabricmc.fabric.api.gametest.v1.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;

/**
 * fabric-gametest-api-v1 (1.21.5) descubre tests via @GameTest en metodos declarados en las
 * clases del entrypoint "fabric-gametest" (ver fabric.mod.json), no via el catalogo generico
 * de PCGameTestCatalog que usan forge/neoforge. Este archivo esta generado 1:1 desde
 * PCGameTestCatalog.DEFINITIONS para no duplicar logica de test, solo el punto de entrada.
 */
public final class PCFabricGameTestEntrypoint {
    private static final String STRUCTURE = "pandoras_creatures:gametest/bufflon_arena";

    // fabric-loader instancia esta clase por reflection al resolver el entrypoint
    // "fabric-gametest" (DefaultLanguageAdapter exige un constructor accesible).
    public PCFabricGameTestEntrypoint() {
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void acidicArchvineSaveDataRestoresArchvineType(GameTestHelper helper) {
        AcidicArchvineGameTests.saveDataRestoresArchvineType(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void acidicArchvineBiteAttackDamagesLivingTarget(GameTestHelper helper) {
        AcidicArchvineGameTests.biteAttackDamagesLivingTarget(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void arachnonDoHurtTargetStartsAttackTimerAndDamagesTarget(GameTestHelper helper) {
        ArachnonGameTests.doHurtTargetStartsAttackTimerAndDamagesTarget(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void arachnonAiStepTicksAttackTimerDown(GameTestHelper helper) {
        ArachnonGameTests.aiStepTicksAttackTimerDown(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void bufflonEquipmentStateTracksInventory(GameTestHelper helper) {
        BufflonGameTests.equipmentStateTracksInventory(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void bufflonSaveDataRestoresStateAndInventory(GameTestHelper helper) {
        BufflonGameTests.saveDataRestoresStateAndInventory(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void bufflonDropEquipmentSpawnsStoredItems(GameTestHelper helper) {
        BufflonGameTests.dropEquipmentSpawnsStoredItems(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void crabSaveDataRestoresVariant(GameTestHelper helper) {
        CrabGameTests.saveDataRestoresVariant(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void crabBucketTagRestoresVariant(GameTestHelper helper) {
        CrabGameTests.bucketTagRestoresVariant(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void endTrollSaveDataRestoresStandingAndScreamState(GameTestHelper helper) {
        EndTrollGameTests.saveDataRestoresStandingAndScreamState(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void endTrollStandingAiTicksCombatCooldowns(GameTestHelper helper) {
        EndTrollGameTests.standingAiTicksCombatCooldowns(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void endTrollPunchAttackHurtsLivingTarget(GameTestHelper helper) {
        EndTrollGameTests.punchAttackHurtsLivingTarget(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void endTrollPlayPunchAnimationUpdatesServerAnimationState(GameTestHelper helper) {
        EndTrollGameTests.playPunchAnimationUpdatesServerAnimationState(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void endTrollMeleeGoalKeepsRunningWhilePunchAnimationIsActive(GameTestHelper helper) {
        EndTrollGameTests.meleeGoalKeepsRunningWhilePunchAnimationIsActive(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void endTrollMeleeGoalKeepsRunningAtCloseRangeWithoutPath(GameTestHelper helper) {
        EndTrollGameTests.meleeGoalKeepsRunningAtCloseRangeWithoutPath(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void endTrollTransformAnimationCompletesAndSetsStandingOnServer(GameTestHelper helper) {
        EndTrollGameTests.transformAnimationCompletesAndSetsStandingOnServer(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void endTrollLiberationAdvancementIsLoaded(GameTestHelper helper) {
        EndTrollGameTests.liberationAdvancementIsLoaded(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void endTrollInvalidTargetClearsCombatAnimationDuringAiStep(GameTestHelper helper) {
        EndTrollGameTests.invalidTargetClearsCombatAnimationDuringAiStep(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void endTrollPeacefulDifficultyClearsCombatState(GameTestHelper helper) {
        EndTrollGameTests.peacefulDifficultyClearsCombatState(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void endTrollDeadTargetResetsEncounterStateForNextCombat(GameTestHelper helper) {
        EndTrollGameTests.deadTargetResetsEncounterStateForNextCombat(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void endTrollFreshPlayerAfterDeathStartsFreshEncounter(GameTestHelper helper) {
        EndTrollGameTests.freshPlayerAfterDeathStartsFreshEncounter(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void hellhoundSaveDataRestoresVariant(GameTestHelper helper) {
        HellhoundGameTests.saveDataRestoresVariant(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void hellhoundWitherVariantAttackAppliesWither(GameTestHelper helper) {
        HellhoundGameTests.witherVariantAttackAppliesWither(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void seahorseSaveDataRestoresVariantAndSize(GameTestHelper helper) {
        SeahorseGameTests.saveDataRestoresVariantAndSize(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void seahorseBucketTagRestoresVariantAndSize(GameTestHelper helper) {
        SeahorseGameTests.bucketTagRestoresVariantAndSize(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void endTrollProjectilesProjectileSaveDataRestoresOwnerTargetAndMotion(GameTestHelper helper) {
        EndTrollProjectileGameTests.projectileSaveDataRestoresOwnerTargetAndMotion(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void endTrollProjectilesPoisonBulletHitAppliesEffectAndDiscards(GameTestHelper helper) {
        EndTrollProjectileGameTests.poisonBulletHitAppliesEffectAndDiscards(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void endPrisonStructureStructureAndStructureSetAreLoadedFromDatapack(GameTestHelper helper) {
        EndPrisonStructureGameTests.structureAndStructureSetAreLoadedFromDatapack(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void endPrisonStructureStructureTemplatesLoadWithRealSize(GameTestHelper helper) {
        EndPrisonStructureGameTests.structureTemplatesLoadWithRealSize(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void endPrisonStructureStructureGeneratesValidStartWithPieces(GameTestHelper helper) {
        EndPrisonStructureGameTests.structureGeneratesValidStartWithPieces(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void endPrisonStructureGeneratedShipUsesOfficialProximity(GameTestHelper helper) {
        EndPrisonStructureGameTests.generatedShipUsesOfficialProximity(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void endPrisonStructureVanillaShipMarkersCreateOfficialContentsInEveryRotation(GameTestHelper helper) {
        EndPrisonShipGameTests.vanillaShipMarkersCreateOfficialContentsInEveryRotation(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void endPrisonStructureNaturallyPlacedEndPrisonContainsEndTroll(GameTestHelper helper) {
        EndPrisonStructureGameTests.naturallyPlacedEndPrisonContainsEndTroll(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void dataLoadRecipesLoadFromDatapack(GameTestHelper helper) {
        PCDataLoadGameTests.recipesLoadFromDatapack(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void dataLoadLootTablesLoadFromDatapack(GameTestHelper helper) {
        PCDataLoadGameTests.lootTablesLoadFromDatapack(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void dataLoadEndTrollBoxItemTagIsBound(GameTestHelper helper) {
        PCDataLoadGameTests.endTrollBoxItemTagIsBound(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void entitySpawnsArachnonSpawns(GameTestHelper helper) {
        PCEntitySpawnGameTests.arachnonSpawns(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void entitySpawnsAcidicArchvineSpawns(GameTestHelper helper) {
        PCEntitySpawnGameTests.acidicArchvineSpawns(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void entitySpawnsBufflonSpawns(GameTestHelper helper) {
        PCEntitySpawnGameTests.bufflonSpawns(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void entitySpawnsCrabSpawns(GameTestHelper helper) {
        PCEntitySpawnGameTests.crabSpawns(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void entitySpawnsHellhoundSpawns(GameTestHelper helper) {
        PCEntitySpawnGameTests.hellhoundSpawns(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void entitySpawnsSeahorseSpawns(GameTestHelper helper) {
        PCEntitySpawnGameTests.seahorseSpawns(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void entitySpawnsEndTrollSpawns(GameTestHelper helper) {
        PCEntitySpawnGameTests.endTrollSpawns(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void naturalSpawnsCatalogIsAppliedToRuntimeBiomes(GameTestHelper helper) {
        PCNaturalSpawnGameTests.catalogIsAppliedToRuntimeBiomes(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void naturalSpawnsEndTrollIsAbsentFromNaturalSpawnTables(GameTestHelper helper) {
        PCNaturalSpawnGameTests.endTrollIsAbsentFromNaturalSpawnTables(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void naturalSpawnsNaturalSpawnPlacementMetadataMatchesContracts(GameTestHelper helper) {
        PCNaturalSpawnGameTests.naturalSpawnPlacementMetadataMatchesContracts(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void naturalSpawnsArachnonCompletesVanillaNaturalSpawnCycle(GameTestHelper helper) {
        PCNaturalSpawnGameTests.arachnonCompletesVanillaNaturalSpawnCycle(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void naturalSpawnsBufflonPlacementUsesSharedRule(GameTestHelper helper) {
        PCNaturalSpawnGameTests.bufflonPlacementUsesSharedRule(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void naturalSpawnsCrabPlacementUsesSharedRule(GameTestHelper helper) {
        PCNaturalSpawnGameTests.crabPlacementUsesSharedRule(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void naturalSpawnsHellhoundPlacementUsesSharedRule(GameTestHelper helper) {
        PCNaturalSpawnGameTests.hellhoundPlacementUsesSharedRule(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void naturalSpawnsSeahorsePlacementUsesSharedRule(GameTestHelper helper) {
        PCNaturalSpawnGameTests.seahorsePlacementUsesSharedRule(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void naturalSpawnsAcidicArchvinePlacementUsesSharedRule(GameTestHelper helper) {
        PCNaturalSpawnGameTests.acidicArchvinePlacementUsesSharedRule(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void plantsRuntimeBiomesContainOfficialPlantFeatures(GameTestHelper helper) {
        PCPlantGameTests.runtimeBiomesContainOfficialPlantFeatures(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void plantsConfiguredFeaturesPlaceEveryOfficialPlant(GameTestHelper helper) {
        PCPlantGameTests.configuredFeaturesPlaceEveryOfficialPlant(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void plantsOfficialPlantsRemainSingleStageDecorations(GameTestHelper helper) {
        PCPlantGameTests.officialPlantsRemainSingleStageDecorations(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void endTrollBoxMenuAcceptsNormalAndShiftClickInsertion(GameTestHelper helper) {
        EndTrollBoxGameTests.menuAcceptsNormalAndShiftClickInsertion(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void endTrollBoxMenuRejectsNestedPortableBoxes(GameTestHelper helper) {
        EndTrollBoxGameTests.menuRejectsNestedPortableBoxes(helper);
    }

    @GameTest(structure = STRUCTURE, maxTicks = 100, setupTicks = 0)
    public void endTrollBoxInventorySurvivesBlockEntitySaveAndLoad(GameTestHelper helper) {
        EndTrollBoxGameTests.inventorySurvivesBlockEntitySaveAndLoad(helper);
    }

}