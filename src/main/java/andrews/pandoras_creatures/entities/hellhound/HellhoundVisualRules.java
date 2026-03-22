package andrews.pandoras_creatures.entities.hellhound;

public final class HellhoundVisualRules {
    private static final double BASE_PARTICLE_OFFSET_Y = 0.8D;
    private static final double WITHER_PARTICLE_EXTRA_OFFSET_Y = 0.2D;
    private static final float WITHER_RENDER_SCALE = 1.2F;
    private static final float WITHER_RENDER_Y_OFFSET = -0.3F;

    private HellhoundVisualRules() {
    }

    public static double particleY(double entityY, int typeId) {
        double particleY = entityY + BASE_PARTICLE_OFFSET_Y;
        if (HellhoundVariantCatalog.isWitherType(typeId)) {
            particleY += WITHER_PARTICLE_EXTRA_OFFSET_Y;
        }
        return particleY;
    }

    public static boolean showsEyesLayer(int typeId) {
        return HellhoundVariantCatalog.isWitherType(typeId);
    }

    public static boolean usesSoulFire(int typeId) {
        return HellhoundVariantCatalog.isWitherType(typeId);
    }

    public static float renderScale(int typeId) {
        return HellhoundVariantCatalog.isWitherType(typeId) ? WITHER_RENDER_SCALE : 1.0F;
    }

    public static float renderYOffset(int typeId) {
        return HellhoundVariantCatalog.isWitherType(typeId) ? WITHER_RENDER_Y_OFFSET : 0.0F;
    }
}
