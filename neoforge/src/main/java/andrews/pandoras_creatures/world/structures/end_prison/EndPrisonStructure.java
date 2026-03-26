package andrews.pandoras_creatures.world.structures.end_prison;

import andrews.pandoras_creatures.registry.PCStructures;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import net.minecraft.world.level.levelgen.structure.pools.DimensionPadding;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasLookup;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;

import java.util.Optional;

public class EndPrisonStructure extends Structure {
    public static final MapCodec<EndPrisonStructure> CODEC = RecordCodecBuilder.mapCodec(instance ->
        instance.group(
            settingsCodec(instance),
            StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(s -> s.startPool)
        ).apply(instance, EndPrisonStructure::new)
    );

    private final Holder<StructureTemplatePool> startPool;

    public EndPrisonStructure(StructureSettings settings, Holder<StructureTemplatePool> startPool) {
        super(settings);
        this.startPool = startPool;
    }

    @Override
    public StructureType<?> type() {
        return PCStructures.END_PRISON_TYPE.get();
    }

    @Override
    protected Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        ChunkPos chunkPos = context.chunkPos();
        BlockPos pos = new BlockPos(chunkPos.getMinBlockX(), 120, chunkPos.getMinBlockZ());

        Optional<GenerationStub> jigsawResult = JigsawPlacement.addPieces(
                context,
                this.startPool,
                Optional.empty(),
                3,
                pos,
                false,
                Optional.empty(),
                80,
                PoolAliasLookup.EMPTY,
                DimensionPadding.ZERO,
                LiquidSettings.APPLY_WATERLOGGING
        );

        if (jigsawResult.isEmpty()) {
            return Optional.empty();
        }

        GenerationStub jigsawStub = jigsawResult.get();
        StructurePiecesBuilder builder = jigsawStub.getPiecesBuilder();

        PiecesContainer builtList = builder.build();
        BlockPos shipAnchor = pos;
        Rotation jigsawRotation = Rotation.NONE;

        if (!builtList.pieces().isEmpty()) {
            if (builtList.pieces().get(0) instanceof PoolElementStructurePiece startPiece) {
                shipAnchor = startPiece.getPosition();
                jigsawRotation = startPiece.getRotation();
            } else {
                jigsawRotation = builtList.pieces().get(0).getRotation();
            }
        }

        EndPrisonPieces.addPieces(context.structureTemplateManager(), shipAnchor, jigsawRotation, builder, context.random());

        return Optional.of(new GenerationStub(jigsawStub.position(), Either.right(builder)));
    }
}
