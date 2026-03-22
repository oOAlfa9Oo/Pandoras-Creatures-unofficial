package andrews.pandoras_creatures.entities.bufflon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BufflonInteractionPolicyTest {

    @Test
    void herbBundleTakesPriorityInMobInteractRouting() {
        assertEquals(
                BufflonInteractionPolicy.MobInteractAction.HANDLE_HERB_BUNDLE,
                BufflonInteractionPolicy.resolveMobInteractAction(true, true, false, true, true, false, false)
        );
    }

    @Test
    void equipmentItemsOpenEquipmentMenuWhenApplicable() {
        assertEquals(
                BufflonInteractionPolicy.MobInteractAction.OPEN_EQUIPMENT_MENU,
                BufflonInteractionPolicy.resolveMobInteractAction(true, false, false, false, false, true, false)
        );
        assertEquals(
                BufflonInteractionPolicy.MobInteractAction.OPEN_EQUIPMENT_MENU,
                BufflonInteractionPolicy.resolveMobInteractAction(true, false, false, true, false, false, true)
        );
    }

    @Test
    void unsupportedItemsFallBackToVanillaMobInteract() {
        assertEquals(
                BufflonInteractionPolicy.MobInteractAction.PASS_TO_SUPER,
                BufflonInteractionPolicy.resolveMobInteractAction(true, false, false, true, true, false, false)
        );
    }

    @Test
    void ownedInteractionDistinguishesMountMenuAndUntamedCases() {
        assertEquals(
                BufflonInteractionPolicy.OwnedInteractionAction.NONE,
                BufflonInteractionPolicy.resolveOwnedInteractionAction(false, true)
        );
        assertEquals(
                BufflonInteractionPolicy.OwnedInteractionAction.MOUNT,
                BufflonInteractionPolicy.resolveOwnedInteractionAction(true, false)
        );
        assertEquals(
                BufflonInteractionPolicy.OwnedInteractionAction.OPEN_MENU,
                BufflonInteractionPolicy.resolveOwnedInteractionAction(true, true)
        );
    }
}
