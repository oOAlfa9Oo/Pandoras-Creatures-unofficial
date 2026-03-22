package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.entities.hellhound.HellhoundVariantCatalog;
import andrews.pandoras_creatures.registry.PCEntities;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;

@GameTestHolder(Reference.MODID)
@PrefixGameTestTemplate(false)
public final class HellhoundGameTests {
    private static final String HELLHOUND_BATCH = "hellhound";
    private static final String SHARED_TEMPLATE = "gametest/bufflon_arena";
    private static final BlockPos HELLHOUND_POS = new BlockPos(3, 2, 3);
    private static final BlockPos TARGET_POS = new BlockPos(7, 2, 3);

    private HellhoundGameTests() {
    }

    @GameTest(template = SHARED_TEMPLATE, batch = HELLHOUND_BATCH)
    public static void saveDataRestoresVariant(GameTestHelper helper) {
        HellhoundEntity hellhound = new HellhoundEntity(PCEntities.HELLHOUND.get(), helper.getLevel());
        hellhound.setHellhoundType(HellhoundVariantCatalog.WITHER_TYPE);

        CompoundTag savedData = new CompoundTag();
        hellhound.addAdditionalSaveData(savedData);

        HellhoundEntity restored = new HellhoundEntity(PCEntities.HELLHOUND.get(), helper.getLevel());
        restored.readAdditionalSaveData(savedData);

        helper.assertValueEqual(restored.getHellhoundType(), HellhoundVariantCatalog.WITHER_TYPE, "restored hellhound type");
        helper.succeed();
    }

    @GameTest(template = SHARED_TEMPLATE, batch = HELLHOUND_BATCH)
    public static void witherVariantAttackAppliesWither(GameTestHelper helper) {
        HellhoundEntity hellhound = helper.spawn(PCEntities.HELLHOUND.get(), HELLHOUND_POS);
        hellhound.setHellhoundType(HellhoundVariantCatalog.WITHER_TYPE);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);

        boolean hurt = hellhound.doHurtTarget(target);

        helper.assertTrue(hurt, "Hellhound attack should damage the target");
        helper.assertTrue(target.hasEffect(MobEffects.WITHER), "Wither hellhound should apply wither");
        helper.succeed();
    }
}
