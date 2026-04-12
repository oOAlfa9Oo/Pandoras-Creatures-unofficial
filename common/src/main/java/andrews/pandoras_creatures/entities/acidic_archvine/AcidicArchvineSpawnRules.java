package andrews.pandoras_creatures.entities.acidic_archvine;

import andrews.pandoras_creatures.registry.entity.PCEntitySpawnRules;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.level.ServerLevelAccessor;

public final class AcidicArchvineSpawnRules {
    private AcidicArchvineSpawnRules() {
    }

    public static boolean shouldAttemptCompanion(EntitySpawnReason spawnType) {
        return AcidicArchvineSpawnTuning.shouldAttemptCompanion(
                spawnType == EntitySpawnReason.NATURAL,
                spawnType == EntitySpawnReason.CHUNK_GENERATION
        );
    }

    public static float companionChance(Difficulty difficulty) {
        return AcidicArchvineSpawnTuning.companionChance(difficulty.getId());
    }

    public static int companionAttempts(Difficulty difficulty) {
        return AcidicArchvineSpawnTuning.companionAttempts(difficulty.getId());
    }

    public static int companionRadius() {
        return AcidicArchvineSpawnTuning.companionRadius();
    }

    public static BlockPos randomNearbyPosition(BlockPos origin, RandomSource random) {
        int radius = AcidicArchvineSpawnTuning.companionRadius();
        int xOffset = random.nextInt(radius * 2 + 1) - radius;
        int yOffset = random.nextInt(5) - 2;
        int zOffset = random.nextInt(radius * 2 + 1) - radius;
        return origin.offset(xOffset, yOffset, zOffset);
    }

    public static boolean isValidCompanionSpawn(ServerLevelAccessor level, BlockPos pos) {
        return PCEntitySpawnRules.canSpawnAcidicArchvine(
                level.getDifficulty() != Difficulty.PEACEFUL,
                PCEntitySpawnRules.isJungleArchvineBiome(level, pos),
                PCEntitySpawnRules.isNetherArchvineBiome(level, pos),
                pos.getY(),
                level.getBlockState(pos).isAir(),
                level.getBlockState(pos.above()).isAir(),
                PCEntitySpawnRules.hasValidAcidicArchvineCeiling(level, pos),
                PCEntitySpawnRules.hasConsecutiveAirBelow(level, pos, PCEntitySpawnRules.acidicArchvineRequiredAirDepth())
        );
    }
}
