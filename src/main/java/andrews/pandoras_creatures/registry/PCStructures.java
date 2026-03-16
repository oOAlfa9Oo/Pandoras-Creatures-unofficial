package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.util.Reference;
import andrews.pandoras_creatures.world.structures.end_prison.EndPrisonPieces;
import andrews.pandoras_creatures.world.structures.end_prison.EndPrisonStructure;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PCStructures {

    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, Reference.MODID);

    public static final DeferredHolder<StructureType<?>, StructureType<EndPrisonStructure>> END_PRISON_TYPE =
            STRUCTURE_TYPES.register("end_prison", () -> () -> EndPrisonStructure.CODEC);

    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_PIECE, Reference.MODID);

    public static final DeferredHolder<StructurePieceType, StructurePieceType> END_PRISON_PIECE =
            STRUCTURE_PIECE_TYPES.register("end_prison_piece",
                    () -> (StructurePieceSerializationContext ctx, net.minecraft.nbt.CompoundTag tag) ->
                            new EndPrisonPieces.Piece(ctx.structureTemplateManager(), tag));
}
