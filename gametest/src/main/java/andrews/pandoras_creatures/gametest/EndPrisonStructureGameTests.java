package andrews.pandoras_creatures.gametest;

import andrews.pandoras_creatures.registry.structure.PCStructureIds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.Optional;

/**
 * Valida en un servidor real que la estructura End Prison (la unica del mod)
 * se carga desde datapack y puede generarse de verdad (jigsaw + piezas).
 * Registrada desde el catalogo compartido mediante el adaptador de cada loader.
 */
public final class EndPrisonStructureGameTests {

    private EndPrisonStructureGameTests() {
    }

    public static void structureAndStructureSetAreLoadedFromDatapack(GameTestHelper helper) {
        ServerLevel level = helper.getLevel();

        Registry<Structure> structures = level.registryAccess().lookupOrThrow(Registries.STRUCTURE);
        Structure endPrison = structures.getValue(PCStructureIds.id(PCStructureIds.END_PRISON));
        helper.assertTrue(endPrison != null, "End Prison structure JSON should load into the structure registry");

        Registry<StructureSet> structureSets = level.registryAccess().lookupOrThrow(Registries.STRUCTURE_SET);
        StructureSet endPrisonSet = structureSets.getValue(PCStructureIds.id(PCStructureIds.END_PRISON));
        helper.assertTrue(endPrisonSet != null, "End Prison structure_set JSON should load into the structure_set registry");
        helper.assertTrue(endPrisonSet != null && !endPrisonSet.structures().isEmpty(),
                "End Prison structure_set should reference at least one structure");
        helper.succeed();
    }

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

    public static void structureGeneratesValidStartWithPieces(GameTestHelper helper) {
        ServerLevel level = helper.getLevel();
        Registry<Structure> structures = level.registryAccess().lookupOrThrow(Registries.STRUCTURE);
        Optional<Holder.Reference<Structure>> endPrison = structures.get(PCStructureIds.id(PCStructureIds.END_PRISON));
        helper.assertTrue(endPrison.isPresent(), "End Prison structure must exist to test generation");

        ChunkGenerator generator = level.getChunkSource().getGenerator();
        BlockPos origin = helper.absolutePos(BlockPos.ZERO);
        StructureStart start = endPrison.get().value().generate(
                endPrison.get(),
                level.dimension(),
                level.registryAccess(),
                generator,
                generator.getBiomeSource(),
                level.getChunkSource().randomState(),
                level.getStructureManager(),
                level.getSeed(),
                new ChunkPos(origin.getX() >> 4, origin.getZ() >> 4),
                0,
                level,
                biome -> true);

        helper.assertTrue(start.isValid(), "End Prison should produce a valid structure start (jigsaw + pieces)");
        helper.assertTrue(!start.getPieces().isEmpty(), "End Prison structure start should contain generated pieces");
        helper.succeed();
    }
}
