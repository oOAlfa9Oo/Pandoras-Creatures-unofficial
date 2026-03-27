package andrews.pandoras_creatures;

import andrews.pandoras_creatures.bootstrap.PCNeoForgeBootstrap;
import andrews.pandoras_creatures.util.Reference;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(Reference.MODID)
public class PandorasCreatures {
    public static final Logger LOGGER = LoggerFactory.getLogger(Reference.MODID);
    public static PandorasCreatures instance;

    public PandorasCreatures(IEventBus modEventBus, ModContainer modContainer) {
        instance = this;

        PCNeoForgeBootstrap.register(modEventBus);
    }
}
