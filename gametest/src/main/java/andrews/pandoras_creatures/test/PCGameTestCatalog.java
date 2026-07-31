package andrews.pandoras_creatures.test;

import andrews.pandoras_creatures.entities.AcidicArchvineGameTests;
import andrews.pandoras_creatures.entities.ArachnonGameTests;
import andrews.pandoras_creatures.entities.BufflonGameTests;
import andrews.pandoras_creatures.entities.CrabGameTests;
import andrews.pandoras_creatures.entities.EndTrollGameTests;
import andrews.pandoras_creatures.entities.HellhoundGameTests;
import andrews.pandoras_creatures.entities.SeahorseGameTests;
import andrews.pandoras_creatures.entities.projectiles.EndTrollProjectileGameTests;
import andrews.pandoras_creatures.gametest.EndTrollBoxGameTests;
import andrews.pandoras_creatures.gametest.EndPrisonShipGameTests;
import andrews.pandoras_creatures.gametest.EndPrisonStructureGameTests;
import andrews.pandoras_creatures.gametest.PCDataLoadGameTests;
import andrews.pandoras_creatures.gametest.PCEntitySpawnGameTests;
import andrews.pandoras_creatures.gametest.PCNaturalSpawnGameTests;
import andrews.pandoras_creatures.gametest.PCPlantGameTests;
import net.minecraft.gametest.framework.GameTestGenerator;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.TestFunction;
import net.minecraft.world.level.block.Rotation;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public final class PCGameTestCatalog {
    private static final String TEMPLATE = "pandoras_creatures:gametest/bufflon_arena";
    private static final List<TestDefinition> DEFINITIONS = List.of(
            definition("acidic_archvine/save_data_restores_archvine_type", AcidicArchvineGameTests::saveDataRestoresArchvineType),
            definition("acidic_archvine/bite_attack_damages_living_target", AcidicArchvineGameTests::biteAttackDamagesLivingTarget),
            definition("arachnon/do_hurt_target_starts_attack_timer_and_damages_target", ArachnonGameTests::doHurtTargetStartsAttackTimerAndDamagesTarget),
            definition("arachnon/ai_step_ticks_attack_timer_down", ArachnonGameTests::aiStepTicksAttackTimerDown),
            definition("arachnon/official_biome_spawn_list_includes_arachnon", ArachnonGameTests::officialBiomeSpawnListIncludesArachnon),
            definition("arachnon/spawn_placement_matches_shared_arachnon_rule", ArachnonGameTests::spawnPlacementMatchesSharedArachnonRule),
            definition("bufflon/equipment_state_tracks_inventory", BufflonGameTests::equipmentStateTracksInventory),
            definition("bufflon/save_data_restores_state_and_inventory", BufflonGameTests::saveDataRestoresStateAndInventory),
            definition("bufflon/drop_equipment_spawns_stored_items", BufflonGameTests::dropEquipmentSpawnsStoredItems),
            definition("crab/save_data_restores_variant", CrabGameTests::saveDataRestoresVariant),
            definition("crab/bucket_tag_restores_variant", CrabGameTests::bucketTagRestoresVariant),
            definition("end_troll/save_data_restores_standing_and_scream_state", EndTrollGameTests::saveDataRestoresStandingAndScreamState),
            definition("end_troll/standing_ai_ticks_combat_cooldowns", EndTrollGameTests::standingAiTicksCombatCooldowns),
            definition("end_troll/punch_attack_hurts_living_target", EndTrollGameTests::punchAttackHurtsLivingTarget),
            definition("end_troll/play_punch_animation_updates_server_animation_state", EndTrollGameTests::playPunchAnimationUpdatesServerAnimationState),
            definition("end_troll/melee_goal_keeps_running_while_punch_animation_is_active", EndTrollGameTests::meleeGoalKeepsRunningWhilePunchAnimationIsActive),
            definition("end_troll/melee_goal_keeps_running_at_close_range_without_path", EndTrollGameTests::meleeGoalKeepsRunningAtCloseRangeWithoutPath),
            definition("end_troll/transform_animation_completes_and_sets_standing_on_server", EndTrollGameTests::transformAnimationCompletesAndSetsStandingOnServer),
            definition("end_troll/liberation_advancement_is_loaded", EndTrollGameTests::liberationAdvancementIsLoaded),
            definition("end_troll/invalid_target_clears_combat_animation_during_ai_step", EndTrollGameTests::invalidTargetClearsCombatAnimationDuringAiStep),
            definition("end_troll/peaceful_difficulty_clears_combat_state", EndTrollGameTests::peacefulDifficultyClearsCombatState),
            definition("end_troll/dead_target_resets_encounter_state_for_next_combat", EndTrollGameTests::deadTargetResetsEncounterStateForNextCombat),
            definition("end_troll/fresh_player_after_death_starts_fresh_encounter", EndTrollGameTests::freshPlayerAfterDeathStartsFreshEncounter),
            definition("hellhound/save_data_restores_variant", HellhoundGameTests::saveDataRestoresVariant),
            definition("hellhound/wither_variant_attack_applies_wither", HellhoundGameTests::witherVariantAttackAppliesWither),
            definition("seahorse/save_data_restores_variant_and_size", SeahorseGameTests::saveDataRestoresVariantAndSize),
            definition("seahorse/bucket_tag_restores_variant_and_size", SeahorseGameTests::bucketTagRestoresVariantAndSize),
            definition("end_troll_projectiles/projectile_save_data_restores_owner_target_and_motion", EndTrollProjectileGameTests::projectileSaveDataRestoresOwnerTargetAndMotion),
            definition("end_troll_projectiles/poison_bullet_hit_applies_effect_and_discards", EndTrollProjectileGameTests::poisonBulletHitAppliesEffectAndDiscards),
            definition("end_prison_structure/structure_and_structure_set_are_loaded_from_datapack", EndPrisonStructureGameTests::structureAndStructureSetAreLoadedFromDatapack),
            definition("end_prison_structure/structure_templates_load_with_real_size", EndPrisonStructureGameTests::structureTemplatesLoadWithRealSize),
            definition("end_prison_structure/structure_generates_valid_start_with_pieces", EndPrisonStructureGameTests::structureGeneratesValidStartWithPieces),
            definition("end_prison_structure/generated_ship_uses_official_proximity", EndPrisonStructureGameTests::generatedShipUsesOfficialProximity),
            definition("end_prison_structure/vanilla_ship_markers_create_official_contents_in_every_rotation", EndPrisonShipGameTests::vanillaShipMarkersCreateOfficialContentsInEveryRotation),
            definition("end_prison_structure/naturally_placed_end_prison_contains_end_troll", EndPrisonStructureGameTests::naturallyPlacedEndPrisonContainsEndTroll),
            definition("data_load/recipes_load_from_datapack", PCDataLoadGameTests::recipesLoadUnder1201Format),
            definition("data_load/loot_tables_load_from_datapack", PCDataLoadGameTests::lootTablesLoadUnder1201Format),
            definition("data_load/end_troll_box_item_tag_is_bound", PCDataLoadGameTests::endTrollBoxItemTagIsBound),
            definition("entity_spawns/arachnon_spawns", PCEntitySpawnGameTests::arachnonSpawns),
            definition("entity_spawns/acidic_archvine_spawns", PCEntitySpawnGameTests::acidicArchvineSpawns),
            definition("entity_spawns/bufflon_spawns", PCEntitySpawnGameTests::bufflonSpawns),
            definition("entity_spawns/crab_spawns", PCEntitySpawnGameTests::crabSpawns),
            definition("entity_spawns/hellhound_spawns", PCEntitySpawnGameTests::hellhoundSpawns),
            definition("entity_spawns/seahorse_spawns", PCEntitySpawnGameTests::seahorseSpawns),
            definition("entity_spawns/end_troll_spawns", PCEntitySpawnGameTests::endTrollSpawns),
            definition("natural_spawns/catalog_is_applied_to_runtime_biomes", PCNaturalSpawnGameTests::catalogIsAppliedToRuntimeBiomes),
            definition("natural_spawns/end_troll_is_absent_from_natural_spawn_tables", PCNaturalSpawnGameTests::endTrollIsAbsentFromNaturalSpawnTables),
            definition("natural_spawns/natural_spawn_placement_metadata_matches_contracts", PCNaturalSpawnGameTests::naturalSpawnPlacementMetadataMatchesContracts),
            definition("natural_spawns/arachnon_completes_vanilla_natural_spawn_cycle", PCNaturalSpawnGameTests::arachnonCompletesVanillaNaturalSpawnCycle),
            definition("natural_spawns/bufflon_placement_uses_shared_rule", PCNaturalSpawnGameTests::bufflonPlacementUsesSharedRule),
            definition("natural_spawns/crab_placement_uses_shared_rule", PCNaturalSpawnGameTests::crabPlacementUsesSharedRule),
            definition("natural_spawns/hellhound_placement_uses_shared_rule", PCNaturalSpawnGameTests::hellhoundPlacementUsesSharedRule),
            definition("natural_spawns/seahorse_placement_uses_shared_rule", PCNaturalSpawnGameTests::seahorsePlacementUsesSharedRule),
            definition("natural_spawns/acidic_archvine_placement_uses_shared_rule", PCNaturalSpawnGameTests::acidicArchvinePlacementUsesSharedRule),
            definition("plants/runtime_biomes_contain_official_plant_features", PCPlantGameTests::runtimeBiomesContainOfficialPlantFeatures),
            definition("plants/configured_features_place_every_official_plant", PCPlantGameTests::configuredFeaturesPlaceEveryOfficialPlant),
            definition("plants/official_plants_remain_single_stage_decorations", PCPlantGameTests::officialPlantsRemainSingleStageDecorations),
            definition("end_troll_box/menu_accepts_normal_and_shift_click_insertion", EndTrollBoxGameTests::menuAcceptsNormalAndShiftClickInsertion),
            definition("end_troll_box/menu_rejects_nested_portable_boxes", EndTrollBoxGameTests::menuRejectsNestedPortableBoxes),
            definition("end_troll_box/inventory_survives_block_entity_save_and_load", EndTrollBoxGameTests::inventorySurvivesBlockEntitySaveAndLoad)
    );

    public PCGameTestCatalog() {
    }

    public static List<TestDefinition> definitions() {
        return DEFINITIONS;
    }

    @GameTestGenerator
    public static Collection<TestFunction> generateTests() {
        return DEFINITIONS.stream()
                .map(PCGameTestCatalog::function)
                .toList();
    }

    private static TestFunction function(TestDefinition definition) {
        String batch = definition.path().substring(0, definition.path().indexOf('/'));
        String testName = "pandoras_creatures." + definition.path().replace('/', '.');
        return new TestFunction(
                batch,
                testName,
                TEMPLATE,
                Rotation.NONE,
                100,
                0,
                true,
                1,
                1,
                definition.test()
        );
    }

    private static TestDefinition definition(String path, Consumer<GameTestHelper> test) {
        return new TestDefinition(path, test);
    }

    public record TestDefinition(String path, Consumer<GameTestHelper> test) {
    }
}
