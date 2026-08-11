package andrews.pandoras_creatures.test;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.gametest.framework.FunctionGameTestInstance;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.GameTestInstance;
import net.minecraft.gametest.framework.TestData;
import net.minecraft.gametest.framework.TestEnvironmentDefinition;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.function.Consumer;

final class DirectGameTestInstance extends GameTestInstance {
    private final String name;
    private final Consumer<GameTestHelper> test;

    DirectGameTestInstance(String name, TestData<Holder<TestEnvironmentDefinition>> data, Consumer<GameTestHelper> test) {
        super(data);
        this.name = name;
        this.test = test;
    }

    @Override
    public void run(GameTestHelper helper) {
        test.accept(helper);
    }

    @Override
    public MapCodec<? extends GameTestInstance> codec() {
        return FunctionGameTestInstance.CODEC;
    }

    @Override
    protected MutableComponent typeDescription() {
        return Component.literal("pandoras_creatures direct function");
    }

    @Override
    public Component describe() {
        return Component.literal(name).append("\n").append(super.describe());
    }
}
