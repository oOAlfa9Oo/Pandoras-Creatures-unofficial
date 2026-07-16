package andrews.pandoras_creatures.gametest;

import andrews.pandoras_creatures.registry.PCEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.Mob;

/**
 * Valida en un servidor real que cada criatura del mod puede spawnearse:
 * el EntityType esta registrado, la entidad se construye, entra al mundo y queda viva.
 * Registrada via PCNeoForgeGameTests (API funcional de GameTests de 26.1).
 */
public final class PCEntitySpawnGameTests {
    private static final BlockPos SPAWN_POS = new BlockPos(3, 2, 3);

    private PCEntitySpawnGameTests() {
    }

    private static void assertSpawns(GameTestHelper helper, Mob mob, String name) {
        helper.assertTrue(mob != null, name + " should spawn");
        helper.assertTrue(mob.isAlive(), name + " should be alive after spawning");
        helper.assertTrue(mob.level() == helper.getLevel(), name + " should be added to the test level");
        helper.succeed();
    }

    public static void arachnonSpawns(GameTestHelper helper) {
        assertSpawns(helper, helper.spawn(PCEntities.ARACHNON.get(), SPAWN_POS), "Arachnon");
    }

    public static void acidicArchvineSpawns(GameTestHelper helper) {
        assertSpawns(helper, helper.spawn(PCEntities.ACIDIC_ARCHVINE.get(), SPAWN_POS), "Acidic Archvine");
    }

    public static void bufflonSpawns(GameTestHelper helper) {
        assertSpawns(helper, helper.spawn(PCEntities.BUFFLON.get(), SPAWN_POS), "Bufflon");
    }

    public static void crabSpawns(GameTestHelper helper) {
        assertSpawns(helper, helper.spawn(PCEntities.CRAB.get(), SPAWN_POS), "Crab");
    }

    public static void hellhoundSpawns(GameTestHelper helper) {
        assertSpawns(helper, helper.spawn(PCEntities.HELLHOUND.get(), SPAWN_POS), "Hellhound");
    }

    public static void seahorseSpawns(GameTestHelper helper) {
        assertSpawns(helper, helper.spawn(PCEntities.SEAHORSE.get(), SPAWN_POS), "Seahorse");
    }

    public static void endTrollSpawns(GameTestHelper helper) {
        assertSpawns(helper, helper.spawn(PCEntities.END_TROLL.get(), SPAWN_POS), "End Troll");
    }
}
