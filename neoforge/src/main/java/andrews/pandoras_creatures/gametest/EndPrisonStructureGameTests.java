package andrews.pandoras_creatures.gametest;

import andrews.pandoras_creatures.registry.structure.PCStructureIds;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;

import java.util.Optional;

/**
 * Valida en un servidor real que la estructura End Prison (la unica del mod)
 * se carga desde datapack y puede generarse de verdad (jigsaw + piezas).
 */
@GameTestHolder(Reference.MODID)
@PrefixGameTestTemplate(false)
public final class EndPrisonStructureGameTests {
    private static final String STRUCTURE_BATCH = "end_prison_structure";
    private static final String SHARED_TEMPLATE = "gametest/bufflon_arena";

    private EndPrisonStructureGameTests() {
    }

    @GameTest(template = SHARED_TEMPLATE, batch = STRUCTURE_BATCH)
    public static void structureAndStructureSetAreLoadedFromDatapack(GameTestHelper helper) {
        ServerLevel level = helper.getLevel();

        Registry<Structure> structures = level.registryAccess().registryOrThrow(Registries.STRUCTURE);
        Structure endPrison = structures.get(PCStructureIds.id(PCStructureIds.END_PRISON));
        helper.assertTrue(endPrison != null, "End Prison structure JSON should load into the structure registry");

        Registry<StructureSet> structureSets = level.registryAccess().registryOrThrow(Registries.STRUCTURE_SET);
        StructureSet endPrisonSet = structureSets.get(PCStructureIds.id(PCStructureIds.END_PRISON));
        helper.assertTrue(endPrisonSet != null, "End Prison structure_set JSON should load into the structure_set registry");
        helper.assertTrue(endPrisonSet != null && !endPrisonSet.structures().isEmpty(),
                "End Prison structure_set should reference at least one structure");
        helper.succeed();
    }

    @GameTest(template = SHARED_TEMPLATE, batch = STRUCTURE_BATCH)
    public static void structureTemplatesLoadWithRealSize(GameTestHelper helper) {
        ServerLevel level = helper.getLevel();

        Optional<StructureTemplate> body = level.getStructureManager().get(PCStructureIds.id(PCStructureIds.END_PRISON_BODY_TEMPLATE));
        Optional<StructureTemplate> ship = level.getStructureManager().get(PCStructureIds.id(PCStructureIds.END_PRISON_SHIP_TEMPLATE));

        helper.assertTrue(body.isPresent(), "End Prison body template NBT should load");
        helper.assertTrue(ship.isPresent(), "End Prison ship template NBT should load");
        helper.assertTrue(body.isPresent() && body.get().getSize().getX() > 0 && body.get().getSize().getY() > 0,
                "End Prison body template should have a real size");
        helper.assertTrue(ship.isPresent() && ship.get().getSize().getX() > 0 && ship.get().getSize().getY() > 0,
                "End Prison ship template should have a real size");
        helper.succeed();
    }

    @GameTest(template = SHARED_TEMPLATE, batch = STRUCTURE_BATCH)
    public static void structureGeneratesValidStartWithPieces(GameTestHelper helper) {
        ServerLevel level = helper.getLevel();
        Registry<Structure> structures = level.registryAccess().registryOrThrow(Registries.STRUCTURE);
        Structure endPrison = structures.get(PCStructureIds.id(PCStructureIds.END_PRISON));
        helper.assertTrue(endPrison != null, "End Prison structure must exist to test generation");

        ChunkGenerator generator = level.getChunkSource().getGenerator();
        StructureStart start = endPrison.generate(
                level.registryAccess(),
                generator,
                generator.getBiomeSource(),
                level.getChunkSource().randomState(),
                level.getStructureManager(),
                level.getSeed(),
                new ChunkPos(helper.absolutePos(BlockPos.ZERO)),
                0,
                level,
                biome -> true);

        helper.assertTrue(start.isValid(), "End Prison should produce a valid structure start (jigsaw + pieces)");
        helper.assertTrue(!start.getPieces().isEmpty(), "End Prison structure start should contain generated pieces");
        helper.succeed();
    }
}
