package andrews.pandoras_creatures.util;

import net.minecraft.resources.ResourceLocation;

/**
 * A reference class used to easily store and change values, for this Mod
 */
public class Reference {
    public static final String MODID = "pandoras_creatures";
    public static final String VERSION = "1.21.1-3.0.0";

    /**
     * Punto unico que construye un ResourceLocation del mod. Centralizar aqui mantiene el
     * constructor especifico de version en un solo lugar por rama. Ver ADR-0005.
     */
    public static ResourceLocation id(String path) {
        return id(MODID, path);
    }

    public static ResourceLocation id(String namespace, String path) {
        return new ResourceLocation(namespace, path);
    }
}
