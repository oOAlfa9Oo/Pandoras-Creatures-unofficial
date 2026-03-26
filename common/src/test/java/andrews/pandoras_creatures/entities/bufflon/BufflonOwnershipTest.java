package andrews.pandoras_creatures.entities.bufflon;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BufflonOwnershipTest {

    @Test
    void comparesOwnerIdsSafely() {
        UUID ownerId = UUID.fromString("11111111-1111-1111-1111-111111111111");
        UUID otherId = UUID.fromString("22222222-2222-2222-2222-222222222222");

        assertTrue(BufflonOwnershipRules.isOwnedBy(ownerId, ownerId));
        assertFalse(BufflonOwnershipRules.isOwnedBy(ownerId, otherId));
        assertFalse(BufflonOwnershipRules.isOwnedBy(ownerId, (UUID) null));
        assertFalse(BufflonOwnershipRules.isOwnedBy(null, ownerId));
    }

    @Test
    void ownerRelationsRequireTamedStateAndResolvedOwner() {
        assertTrue(BufflonOwnershipRules.shouldUseOwnerRelations(true, true));
        assertFalse(BufflonOwnershipRules.shouldUseOwnerRelations(false, true));
        assertFalse(BufflonOwnershipRules.shouldUseOwnerRelations(true, false));
    }

    @Test
    void deathMessagesRequireServerSideRuleAndServerPlayerOwner() {
        assertTrue(BufflonOwnershipRules.shouldSendDeathMessage(true, true, true));
        assertFalse(BufflonOwnershipRules.shouldSendDeathMessage(false, true, true));
        assertFalse(BufflonOwnershipRules.shouldSendDeathMessage(true, false, true));
        assertFalse(BufflonOwnershipRules.shouldSendDeathMessage(true, true, false));
    }
}
