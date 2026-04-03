package andrews.pandoras_creatures.registry.block;

import java.util.List;

/**
 * Shared block render-layer catalogs used by loader client adapters.
 */
public final class PCBlockRenderLayers {
    private static final List<String> CUTOUT_BLOCK_IDS = List.of(
            PCBlockIds.ARACHNON_CRYSTAL,
            PCBlockIds.HORSETAIL,
            PCBlockIds.DHANIA,
            PCBlockIds.HILL_BLOOM
    );

    private PCBlockRenderLayers() {
    }

    public static List<String> cutoutBlockIds() {
        return CUTOUT_BLOCK_IDS;
    }
}
