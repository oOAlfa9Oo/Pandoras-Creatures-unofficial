package andrews.pandoras_creatures.world.structures.end_prison;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.entities.EndTrollEntity;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.registry.structure.PCStructureIds;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class EndPrisonStructure extends Structure {
    private static final Vec3 END_TROLL_TEMPLATE_POSITION =
            new Vec3(
                    EndPrisonBehaviorRules.END_TROLL_X,
                    EndPrisonBehaviorRules.END_TROLL_Y,
                    EndPrisonBehaviorRules.END_TROLL_Z);

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
        return PandorasCreaturesCommon.platform().registry().structureType(PCStructureIds.END_PRISON);
    }

    @Override
    public void afterPlace(
            WorldGenLevel level,
            StructureManager structureManager,
            ChunkGenerator generator,
            RandomSource random,
            BoundingBox chunkBox,
            ChunkPos chunkPos,
            PiecesContainer pieces) {
        super.afterPlace(level, structureManager, generator, random, chunkBox, chunkPos, pieces);
        if (!Level.END.equals(level.getLevel().dimension()) || pieces.pieces().isEmpty()
                || !(pieces.pieces().get(0) instanceof PoolElementStructurePiece bodyPiece)) {
            return;
        }

        Vec3 relativePosition = StructureTemplate.transform(
                END_TROLL_TEMPLATE_POSITION,
                Mirror.NONE,
                bodyPiece.getRotation(),
                BlockPos.ZERO);
        Vec3 spawnPosition = relativePosition.add(
                bodyPiece.getPosition().getX(),
                bodyPiece.getPosition().getY(),
                bodyPiece.getPosition().getZ());
        BlockPos spawnBlock = BlockPos.containing(spawnPosition);
        if (!chunkBox.isInside(spawnBlock)) {
            return;
        }

        EntityType<EndTrollEntity> type =
                PandorasCreaturesCommon.platform().registry().entityType(PCEntityIds.END_TROLL);
        EndTrollEntity endTroll = type.create(level.getLevel());
        if (endTroll == null) {
            return;
        }

        float yaw = EndPrisonBehaviorRules.rotatedEndTrollYaw(bodyPiece.getRotation().name());
        endTroll.moveTo(spawnPosition.x, spawnPosition.y, spawnPosition.z, yaw, 0.0F);
        endTroll.setHealth(EndPrisonBehaviorRules.END_TROLL_HEALTH);
        endTroll.setEntityStanding(false);
        endTroll.setHasScreamed(false);
        endTroll.setPersistenceRequired();
        endTroll.setOnGround(true);
        level.addFreshEntityWithPassengers(endTroll);
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
                80
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
