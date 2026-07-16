package andrews.pandoras_creatures.gametest;

import andrews.pandoras_creatures.forge.registry.PCForgeEntities;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;

/**
 * Valida en un servidor real que cada criatura del mod puede spawnearse:
 * el EntityType esta registrado, la entidad se construye, entra al mundo y queda viva.
 */
@GameTestHolder(Reference.MODID)
@PrefixGameTestTemplate(false)
public final class PCEntitySpawnGameTests {
    private static final String SPAWN_BATCH = "entity_spawns";
    private static final String SHARED_TEMPLATE = "gametest/bufflon_arena";
    private static final BlockPos SPAWN_POS = new BlockPos(3, 2, 3);

    private PCEntitySpawnGameTests() {
    }

    private static void assertSpawns(GameTestHelper helper, Mob mob, String name) {
        helper.assertTrue(mob != null, name + " should spawn");
        helper.assertTrue(mob.isAlive(), name + " should be alive after spawning");
        helper.assertTrue(mob.level() == helper.getLevel(), name + " should be added to the test level");
        helper.succeed();
    }

    @GameTest(template = SHARED_TEMPLATE, batch = SPAWN_BATCH)
    public static void arachnonSpawns(GameTestHelper helper) {
        assertSpawns(helper, helper.spawn(PCForgeEntities.arachnon(), SPAWN_POS), "Arachnon");
    }

    @GameTest(template = SHARED_TEMPLATE, batch = SPAWN_BATCH)
    public static void acidicArchvineSpawns(GameTestHelper helper) {
        assertSpawns(helper, helper.spawn(PCForgeEntities.acidicArchvine(), SPAWN_POS), "Acidic Archvine");
    }

    @GameTest(template = SHARED_TEMPLATE, batch = SPAWN_BATCH)
    public static void bufflonSpawns(GameTestHelper helper) {
        assertSpawns(helper, helper.spawn(PCForgeEntities.bufflon(), SPAWN_POS), "Bufflon");
    }

    @GameTest(template = SHARED_TEMPLATE, batch = SPAWN_BATCH)
    public static void crabSpawns(GameTestHelper helper) {
        assertSpawns(helper, helper.spawn(PCForgeEntities.crab(), SPAWN_POS), "Crab");
    }

    @GameTest(template = SHARED_TEMPLATE, batch = SPAWN_BATCH)
    public static void hellhoundSpawns(GameTestHelper helper) {
        assertSpawns(helper, helper.spawn(PCForgeEntities.hellhound(), SPAWN_POS), "Hellhound");
    }

    @GameTest(template = SHARED_TEMPLATE, batch = SPAWN_BATCH)
    public static void seahorseSpawns(GameTestHelper helper) {
        assertSpawns(helper, helper.spawn(PCForgeEntities.seahorse(), SPAWN_POS), "Seahorse");
    }

    @GameTest(template = SHARED_TEMPLATE, batch = SPAWN_BATCH)
    public static void endTrollSpawns(GameTestHelper helper) {
        assertSpawns(helper, helper.spawn(PCForgeEntities.endTroll(), SPAWN_POS), "End Troll");
    }
}
