package andrews.pandoras_creatures.entities.arachnon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArachnonVisualRulesTest {
    @Test
    void exposesTexturePaths() {
        assertEquals("textures/entity/arachnon/arachnon.png", ArachnonVisualRules.texturePathString());
        assertEquals("textures/entity/arachnon/arachnon_eye.png", ArachnonVisualRules.eyeTexturePathString());
    }

    @Test
    void exposesRenderTransform() {
        assertEquals(1.4F, ArachnonVisualRules.renderScale());
        assertEquals(-0.1F, ArachnonVisualRules.renderYOffset());
    }
}
