package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.entities.bufflon.BufflonBackAttachmentType;
import andrews.pandoras_creatures.entities.bufflon.BufflonInventoryLayout;
import andrews.pandoras_creatures.registry.PCEntities;
import andrews.pandoras_creatures.registry.PCItems;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;

import java.util.UUID;

@GameTestHolder(Reference.MODID)
@PrefixGameTestTemplate(false)
public final class BufflonGameTests {
    private static final String BUFFLON_BATCH = "bufflon";
    private static final String BUFFLON_TEMPLATE = "gametest/bufflon_arena";
    private static final BlockPos BUFFLON_POS = new BlockPos(3, 2, 3);

    private BufflonGameTests() {
    }

    @GameTest(template = BUFFLON_TEMPLATE, batch = BUFFLON_BATCH)
    public static void equipmentStateTracksInventory(GameTestHelper helper) {
        BufflonEntity bufflon = helper.spawn(PCEntities.BUFFLON.get(), BUFFLON_POS);

        bufflon.bufflonStorage.setItem(BufflonInventoryLayout.SADDLE_SLOT, new ItemStack(PCItems.BUFFLON_SADDLE.get()));
        bufflon.bufflonStorage.setItem(BufflonInventoryLayout.BACK_ATTACHMENT_SLOT, new ItemStack(PCItems.BUFFLON_SMALL_STORAGE.get()));
        bufflon.bufflonStorage.setItem(BufflonInventoryLayout.FIRST_STORAGE_SLOT, new ItemStack(Items.DIRT));
        bufflon.containerChanged(bufflon.bufflonStorage);

        helper.assertTrue(bufflon.isSaddled(), "Bufflon should become saddled after adding a saddle");
        helper.assertValueEqual(bufflon.getBackAttachment(), BufflonBackAttachmentType.SMALL_STORAGE, "back attachment");
        helper.assertValueEqual(bufflon.getOccupiedStorageSlotCount(), 1, "occupied storage slots");
        helper.succeed();
    }

    @GameTest(template = BUFFLON_TEMPLATE, batch = BUFFLON_BATCH)
    public static void saveDataRestoresStateAndInventory(GameTestHelper helper) {
        BufflonEntity bufflon = helper.spawn(PCEntities.BUFFLON.get(), BUFFLON_POS);
        UUID ownerId = UUID.fromString("11111111-1111-1111-1111-111111111111");

        bufflon.setBufflonType(4);
        bufflon.setTamed(true);
        bufflon.setOwnerId(ownerId);
        bufflon.setFollowingOwner(true);
        bufflon.setIsInCombatMode(true);
        bufflon.setOrderedToSit(true);
        bufflon.bufflonStorage.setItem(BufflonInventoryLayout.SADDLE_SLOT, new ItemStack(PCItems.BUFFLON_SADDLE.get()));
        bufflon.bufflonStorage.setItem(BufflonInventoryLayout.BACK_ATTACHMENT_SLOT, new ItemStack(PCItems.BUFFLON_LARGE_STORAGE.get()));
        bufflon.bufflonStorage.setItem(BufflonInventoryLayout.FIRST_STORAGE_SLOT + 1, new ItemStack(Items.DIRT));
        bufflon.containerChanged(bufflon.bufflonStorage);

        CompoundTag savedData = new CompoundTag();
        bufflon.addAdditionalSaveData(savedData);

        BufflonEntity restored = new BufflonEntity(PCEntities.BUFFLON.get(), helper.getLevel());
        restored.readAdditionalSaveData(savedData);

        helper.assertTrue(restored.isTamed(), "Restored Bufflon should stay tamed");
        helper.assertTrue(ownerId.equals(restored.getOwnerId()), "Restored Bufflon should preserve owner UUID");
        helper.assertValueEqual(restored.getBufflonType(), 4, "bufflon type");
        helper.assertTrue(restored.isFollowingOwner(), "Restored Bufflon should preserve follow state");
        helper.assertTrue(restored.isInCombatMode(), "Restored Bufflon should preserve combat mode");
        helper.assertTrue(restored.isSitting(), "Restored Bufflon should preserve sitting state");
        helper.assertValueEqual(restored.getBackAttachment(), BufflonBackAttachmentType.LARGE_STORAGE, "restored back attachment");
        helper.assertTrue(restored.bufflonStorage.getItem(BufflonInventoryLayout.FIRST_STORAGE_SLOT + 1).is(Items.DIRT), "Restored Bufflon should preserve storage contents");
        helper.succeed();
    }

    @GameTest(template = BUFFLON_TEMPLATE, batch = BUFFLON_BATCH)
    public static void dropEquipmentSpawnsStoredItems(GameTestHelper helper) {
        BufflonEntity bufflon = helper.spawn(PCEntities.BUFFLON.get(), BUFFLON_POS);

        bufflon.bufflonStorage.setItem(BufflonInventoryLayout.SADDLE_SLOT, new ItemStack(PCItems.BUFFLON_SADDLE.get()));
        bufflon.bufflonStorage.setItem(BufflonInventoryLayout.BACK_ATTACHMENT_SLOT, new ItemStack(PCItems.BUFFLON_LARGE_STORAGE.get()));
        bufflon.bufflonStorage.setItem(BufflonInventoryLayout.FIRST_STORAGE_SLOT + 2, new ItemStack(Items.DIRT));
        bufflon.containerChanged(bufflon.bufflonStorage);
        bufflon.dropEquipment();

        helper.assertItemEntityPresent(PCItems.BUFFLON_SADDLE.get());
        helper.assertItemEntityPresent(PCItems.BUFFLON_LARGE_STORAGE.get());
        helper.assertItemEntityPresent(Items.DIRT);
        helper.succeed();
    }
}
