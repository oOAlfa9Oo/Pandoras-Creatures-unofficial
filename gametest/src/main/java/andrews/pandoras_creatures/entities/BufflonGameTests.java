package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.test.PCGameTestAssertions;

import andrews.pandoras_creatures.entities.bufflon.BufflonBackAttachmentType;
import andrews.pandoras_creatures.entities.bufflon.BufflonInventoryLayout;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.test.PCGameTestRegistry;
import andrews.pandoras_creatures.registry.item.PCItemIds;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.UUID;

public final class BufflonGameTests {
    private static final String BUFFLON_BATCH = "bufflon";
    private static final String BUFFLON_TEMPLATE = "gametest/bufflon_arena";
    private static final BlockPos BUFFLON_POS = new BlockPos(3, 2, 3);

    private BufflonGameTests() {
    }

    public static void equipmentStateTracksInventory(GameTestHelper helper) {
        BufflonEntity bufflon = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.BUFFLON), BUFFLON_POS);

        bufflon.bufflonStorage.setItem(BufflonInventoryLayout.SADDLE_SLOT, new ItemStack(PCGameTestRegistry.item(PCItemIds.BUFFLON_SADDLE)));
        bufflon.bufflonStorage.setItem(BufflonInventoryLayout.BACK_ATTACHMENT_SLOT, new ItemStack(PCGameTestRegistry.item(PCItemIds.BUFFLON_SMALL_STORAGE)));
        bufflon.bufflonStorage.setItem(BufflonInventoryLayout.FIRST_STORAGE_SLOT, new ItemStack(Items.DIRT));
        bufflon.containerChanged(bufflon.bufflonStorage);

        PCGameTestAssertions.assertTrue(helper, bufflon.isSaddled(), "Bufflon should become saddled after adding a saddle");
        PCGameTestAssertions.assertValueEqual(helper, bufflon.getBackAttachment(), BufflonBackAttachmentType.SMALL_STORAGE, "back attachment");
        PCGameTestAssertions.assertValueEqual(helper, bufflon.getOccupiedStorageSlotCount(), 1, "occupied storage slots");
        helper.succeed();
    }

    public static void saveDataRestoresStateAndInventory(GameTestHelper helper) {
        BufflonEntity bufflon = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.BUFFLON), BUFFLON_POS);
        UUID ownerId = UUID.fromString("11111111-1111-1111-1111-111111111111");

        bufflon.setBufflonType(4);
        bufflon.setTamed(true);
        bufflon.setOwnerId(ownerId);
        bufflon.setFollowingOwner(true);
        bufflon.setIsInCombatMode(true);
        bufflon.setOrderedToSit(true);
        bufflon.bufflonStorage.setItem(BufflonInventoryLayout.SADDLE_SLOT, new ItemStack(PCGameTestRegistry.item(PCItemIds.BUFFLON_SADDLE)));
        bufflon.bufflonStorage.setItem(BufflonInventoryLayout.BACK_ATTACHMENT_SLOT, new ItemStack(PCGameTestRegistry.item(PCItemIds.BUFFLON_LARGE_STORAGE)));
        bufflon.bufflonStorage.setItem(BufflonInventoryLayout.FIRST_STORAGE_SLOT + 1, new ItemStack(Items.DIRT));
        bufflon.containerChanged(bufflon.bufflonStorage);

        CompoundTag savedData = new CompoundTag();
        bufflon.addAdditionalSaveData(savedData);

        BufflonEntity restored = new BufflonEntity(PCGameTestRegistry.entityType(PCEntityIds.BUFFLON), helper.getLevel());
        restored.readAdditionalSaveData(savedData);

        PCGameTestAssertions.assertTrue(helper, restored.isTamed(), "Restored Bufflon should stay tamed");
        PCGameTestAssertions.assertTrue(helper, ownerId.equals(restored.getOwnerId()), "Restored Bufflon should preserve owner UUID");
        PCGameTestAssertions.assertValueEqual(helper, restored.getBufflonType(), 4, "bufflon type");
        PCGameTestAssertions.assertTrue(helper, restored.isFollowingOwner(), "Restored Bufflon should preserve follow state");
        PCGameTestAssertions.assertTrue(helper, restored.isInCombatMode(), "Restored Bufflon should preserve combat mode");
        PCGameTestAssertions.assertTrue(helper, restored.isSitting(), "Restored Bufflon should preserve sitting state");
        PCGameTestAssertions.assertValueEqual(helper, restored.getBackAttachment(), BufflonBackAttachmentType.LARGE_STORAGE, "restored back attachment");
        PCGameTestAssertions.assertTrue(helper, restored.bufflonStorage.getItem(BufflonInventoryLayout.FIRST_STORAGE_SLOT + 1).is(Items.DIRT), "Restored Bufflon should preserve storage contents");
        helper.succeed();
    }

    public static void dropEquipmentSpawnsStoredItems(GameTestHelper helper) {
        BufflonEntity bufflon = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.BUFFLON), BUFFLON_POS);

        bufflon.bufflonStorage.setItem(BufflonInventoryLayout.SADDLE_SLOT, new ItemStack(PCGameTestRegistry.item(PCItemIds.BUFFLON_SADDLE)));
        bufflon.bufflonStorage.setItem(BufflonInventoryLayout.BACK_ATTACHMENT_SLOT, new ItemStack(PCGameTestRegistry.item(PCItemIds.BUFFLON_LARGE_STORAGE)));
        bufflon.bufflonStorage.setItem(BufflonInventoryLayout.FIRST_STORAGE_SLOT + 2, new ItemStack(Items.DIRT));
        bufflon.containerChanged(bufflon.bufflonStorage);
        bufflon.dropEquipment((net.minecraft.server.level.ServerLevel) bufflon.level());

        helper.assertItemEntityPresent(PCGameTestRegistry.item(PCItemIds.BUFFLON_SADDLE));
        helper.assertItemEntityPresent(PCGameTestRegistry.item(PCItemIds.BUFFLON_LARGE_STORAGE));
        helper.assertItemEntityPresent(Items.DIRT);
        helper.succeed();
    }
}
