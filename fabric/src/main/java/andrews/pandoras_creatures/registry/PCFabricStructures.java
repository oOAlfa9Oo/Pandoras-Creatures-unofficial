package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.registry.structure.PCStructureIds;
import andrews.pandoras_creatures.world.structures.end_prison.EndPrisonPieces;
import andrews.pandoras_creatures.world.structures.end_prison.EndPrisonStructure;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

public final class PCFabricStructures {
    private PCFabricStructures() {
    }

    public static void register() {
        Registry.register(BuiltInRegistries.STRUCTURE_TYPE, PCStructureIds.id(PCStructureIds.END_PRISON),
                (StructureType<EndPrisonStructure>) () -> EndPrisonStructure.CODEC.codec());
        Registry.register(BuiltInRegistries.STRUCTURE_PIECE, PCStructureIds.id(PCStructureIds.END_PRISON_PIECE),
                (StructurePieceType) (StructurePieceSerializationContext ctx, net.minecraft.nbt.CompoundTag tag) ->
                        new EndPrisonPieces.Piece(ctx.structureTemplateManager(), tag));
    }
}
