package andrews.pandoras_creatures.entities.seahorse;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SeahorseVisualRulesTest {
    @Test
    void resolvesSpecialNameAndTexturePaths() {
        assertTrue(SeahorseVisualRules.isSpecialNamed("Mr.Sparkles"));
        assertTrue(SeahorseVisualRules.texturePath(2, "Mr.Sparkles").contains("unicorn"));
        assertTrue(SeahorseVisualRules.texturePath(2, "Plain").contains("seahorse_2"));
    }

    @Test
    void derivesScaleAndYOffsetFromSize() {
        assertEquals(1.0F, SeahorseVisualRules.renderScale(3), 0.0001F);
        assertEquals(0.0D, SeahorseVisualRules.renderYOffset(3), 0.0001D);
        assertTrue(SeahorseVisualRules.renderScale(5) > SeahorseVisualRules.renderScale(1));
    }
}
