package andrews.pandoras_creatures.lang;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PCLanguageKeysTest {
    @Test
    void translationHelpersBuildStableNamespaceKeys() {
        assertEquals("item.pandoras_creatures.bufflon_saddle", PCLanguageKeys.item("bufflon_saddle"));
        assertEquals("block.pandoras_creatures.end_troll_box", PCLanguageKeys.block("end_troll_box"));
        assertEquals("entity.pandoras_creatures.end_troll", PCLanguageKeys.entity("end_troll"));
        assertEquals("container.pandoras_creatures.end_troll_box", PCLanguageKeys.container("end_troll_box"));
        assertEquals("gui.button.pandoras_creatures.bufflon.follow", PCLanguageKeys.guiButton("bufflon.follow"));
        assertEquals("chat.pandoras_creatures.failedCheck", PCLanguageKeys.chat("failedCheck"));
    }

    @Test
    void namedTooltipAndContainerKeysStayStable() {
        assertEquals("item.pandoras_creatures.arachnon_hammer.tooltip", PCLanguageKeys.ARACHNON_HAMMER_TOOLTIP);
        assertEquals("item.pandoras_creatures.plant_hat.tooltip", PCLanguageKeys.PLANT_HAT_TOOLTIP);
        assertEquals("block.pandoras_creatures.end_troll_box.tooltip", PCLanguageKeys.END_TROLL_BOX_TOOLTIP);
        assertEquals("container.pandoras_creatures.end_troll_box", PCLanguageKeys.END_TROLL_BOX_CONTAINER);
    }
}
