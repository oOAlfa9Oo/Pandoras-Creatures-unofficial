package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.test.PCGameTestAssertions;

import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.test.PCGameTestRegistry;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;

public final class AcidicArchvineGameTests {
    private static final String ACIDIC_ARCHVINE_BATCH = "acidic_archvine";
    private static final String SHARED_TEMPLATE = "gametest/bufflon_arena";
    private static final BlockPos ARCHVINE_POS = new BlockPos(3, 2, 3);
    private static final BlockPos TARGET_POS = new BlockPos(6, 2, 3);

    private AcidicArchvineGameTests() {
    }

    public static void saveDataRestoresArchvineType(GameTestHelper helper) {
        AcidicArchvineEntity archvine = new AcidicArchvineEntity(PCGameTestRegistry.entityType(PCEntityIds.ACIDIC_ARCHVINE), helper.getLevel());
        archvine.setArchvineType(3);

        CompoundTag savedData = new CompoundTag();
        archvine.addAdditionalSaveData(savedData);

        AcidicArchvineEntity restored = new AcidicArchvineEntity(PCGameTestRegistry.entityType(PCEntityIds.ACIDIC_ARCHVINE), helper.getLevel());
        restored.readAdditionalSaveData(savedData);

        PCGameTestAssertions.assertValueEqual(helper, restored.getArchvineType(), 3, "restored archvine type");
        helper.succeed();
    }

    public static void biteAttackDamagesLivingTarget(GameTestHelper helper) {
        AcidicArchvineEntity archvine = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.ACIDIC_ARCHVINE), ARCHVINE_POS);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);
        float initialHealth = target.getHealth();

        boolean attackSucceeded = archvine.doHurtTarget((net.minecraft.server.level.ServerLevel) archvine.level(), target);

        PCGameTestAssertions.assertTrue(helper, attackSucceeded, "Acidic Archvine bite should report a successful hit");
        PCGameTestAssertions.assertTrue(helper, target.getHealth() < initialHealth, "Acidic Archvine bite should damage the target");
        helper.succeed();
    }
}
