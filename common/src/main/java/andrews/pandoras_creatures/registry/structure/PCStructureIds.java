package andrews.pandoras_creatures.registry.structure;

import andrews.pandoras_creatures.util.Reference;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

/**
 * Shared structure and structure-adjacent ids that should stay stable across loader adapters.
 */
public final class PCStructureIds {
    public static final String END_PRISON = "end_prison";
    public static final String END_PRISON_PIECE = "end_prison_piece";
    public static final String END_PRISON_CHORUS_PLANT = "end_prison_chorus_plant";
    public static final String END_PRISON_SHIP = "end_prison_ship";

    public static final String HAS_STRUCTURE_END_PRISON = "has_structure/" + END_PRISON;
    public static final String END_PRISON_BODY_TEMPLATE = END_PRISON + "/end_prison_body";
    public static final String END_PRISON_SHIP_TEMPLATE = END_PRISON + "/" + END_PRISON_SHIP;

    private PCStructureIds() {
    }

    public static List<String> allPaths() {
        return List.of(
                END_PRISON,
                END_PRISON_PIECE,
                END_PRISON_CHORUS_PLANT,
                END_PRISON_SHIP
        );
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MODID, path);
    }

    public static String qualified(String path) {
        return Reference.MODID + ":" + path;
    }

    public static String tagReference(String tagPath) {
        return "#" + qualified(tagPath);
    }
}
