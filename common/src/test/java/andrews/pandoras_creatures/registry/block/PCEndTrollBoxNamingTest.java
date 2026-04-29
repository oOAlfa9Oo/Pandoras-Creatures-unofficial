package andrews.pandoras_creatures.registry.block;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PCEndTrollBoxNamingTest {
    @Test
    void orderedColorNamesStayStable() {
        var names = PCEndTrollBoxNaming.orderedColorNames();
        assertEquals(16, names.size());
        assertEquals("white", names.get(0));
        assertEquals("black", names.get(names.size() - 1));
    }

    @Test
    void namesMatchExpectedConventions() {
        assertEquals("end_troll_box", PCEndTrollBoxNaming.blockName(null));
        assertEquals("light_blue_end_troll_box", PCEndTrollBoxNaming.blockName("light_blue"));
        assertEquals("textures/tile/end_troll_box.png", PCEndTrollBoxNaming.texturePath(null));
        assertEquals("textures/tile/light_blue_end_troll_box.png", PCEndTrollBoxNaming.texturePath("light_blue"));
    }
}
