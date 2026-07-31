package andrews.pandoras_creatures.test;

import andrews.pandoras_creatures.util.Reference;
import net.minecraft.gametest.framework.GameTestGenerator;
import net.minecraft.gametest.framework.TestFunction;
import net.neoforged.neoforge.gametest.GameTestHolder;

import java.util.Collection;

@GameTestHolder(Reference.MODID)
public final class PCNeoForgeGameTests {
    private PCNeoForgeGameTests() {
    }

    @GameTestGenerator
    public static Collection<TestFunction> generateTests() {
        return PCGameTestCatalog.generateTests();
    }
}