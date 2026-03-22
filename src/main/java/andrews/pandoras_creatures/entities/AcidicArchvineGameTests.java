package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.registry.PCEntities;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;

@GameTestHolder(Reference.MODID)
@PrefixGameTestTemplate(false)
public final class AcidicArchvineGameTests {
    private static final String ACIDIC_ARCHVINE_BATCH = "acidic_archvine";
    private static final String SHARED_TEMPLATE = "gametest/bufflon_arena";
    private static final BlockPos ARCHVINE_POS = new BlockPos(3, 2, 3);
    private static final BlockPos TARGET_POS = new BlockPos(6, 2, 3);

    private AcidicArchvineGameTests() {
    }

    @GameTest(template = SHARED_TEMPLATE, batch = ACIDIC_ARCHVINE_BATCH)
    public static void saveDataRestoresArchvineType(GameTestHelper helper) {
        AcidicArchvineEntity archvine = new AcidicArchvineEntity(PCEntities.ACIDIC_ARCHVINE.get(), helper.getLevel());
        archvine.setArchvineType(3);

        CompoundTag savedData = new CompoundTag();
        archvine.addAdditionalSaveData(savedData);

        AcidicArchvineEntity restored = new AcidicArchvineEntity(PCEntities.ACIDIC_ARCHVINE.get(), helper.getLevel());
        restored.readAdditionalSaveData(savedData);

        helper.assertValueEqual(restored.getArchvineType(), 3, "restored archvine type");
        helper.succeed();
    }

    @GameTest(template = SHARED_TEMPLATE, batch = ACIDIC_ARCHVINE_BATCH)
    public static void biteAttackDamagesLivingTarget(GameTestHelper helper) {
        AcidicArchvineEntity archvine = helper.spawn(PCEntities.ACIDIC_ARCHVINE.get(), ARCHVINE_POS);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);
        float initialHealth = target.getHealth();

        boolean attackSucceeded = archvine.doHurtTarget(target);

        helper.assertTrue(attackSucceeded, "Acidic Archvine bite should report a successful hit");
        helper.assertTrue(target.getHealth() < initialHealth, "Acidic Archvine bite should damage the target");
        helper.succeed();
    }
}
