package andrews.pandoras_creatures.registry.block;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PCEndTrollBoxNamingTest {
    @Test
    void orderedColorNamesStayStable() {
        assertEquals(16, PCEndTrollBoxNaming.orderedColorNames().size());
        assertEquals("white", PCEndTrollBoxNaming.orderedColorNames().getFirst());
        assertEquals("black", PCEndTrollBoxNaming.orderedColorNames().getLast());
    }

    @Test
    void namesMatchExpectedConventions() {
        assertEquals("end_troll_box", PCEndTrollBoxNaming.blockName(null));
        assertEquals("light_blue_end_troll_box", PCEndTrollBoxNaming.blockName("light_blue"));
        assertEquals("textures/tile/end_troll_box.png", PCEndTrollBoxNaming.texturePath(null));
        assertEquals("textures/tile/light_blue_end_troll_box.png", PCEndTrollBoxNaming.texturePath("light_blue"));
    }
}
