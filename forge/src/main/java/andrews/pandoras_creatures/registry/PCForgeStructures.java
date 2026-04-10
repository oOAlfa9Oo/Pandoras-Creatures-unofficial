package andrews.pandoras_creatures.forge.registry;

import andrews.pandoras_creatures.registry.structure.PCStructureIds;
import andrews.pandoras_creatures.world.structures.end_prison.EndPrisonPieces;
import andrews.pandoras_creatures.world.structures.end_prison.EndPrisonStructure;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegisterEvent;

public final class PCForgeStructures {
    private PCForgeStructures() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(PCForgeStructures::registerStructures);
    }

    private static void registerStructures(RegisterEvent event) {
        if (event.getRegistryKey().equals(Registries.STRUCTURE_TYPE)) {
            event.register(
                    Registries.STRUCTURE_TYPE,
                    helper -> helper.register(
                            PCStructureIds.id(PCStructureIds.END_PRISON),
                            (StructureType<EndPrisonStructure>) () -> EndPrisonStructure.CODEC
                    )
            );
        } else if (event.getRegistryKey().equals(Registries.STRUCTURE_PIECE)) {
            event.register(
                    Registries.STRUCTURE_PIECE,
                    helper -> helper.register(
                            PCStructureIds.id(PCStructureIds.END_PRISON_PIECE),
                            (StructurePieceType) (StructurePieceSerializationContext ctx, net.minecraft.nbt.CompoundTag tag) ->
                                    new EndPrisonPieces.Piece(ctx.structureTemplateManager(), tag)
                    )
            );
        }
    }
}
