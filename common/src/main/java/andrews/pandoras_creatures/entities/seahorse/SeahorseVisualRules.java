package andrews.pandoras_creatures.entities.seahorse;

public final class SeahorseVisualRules {
    public static final String SPECIAL_NAME = "Mr.Sparkles";
    private static final int DEFAULT_RENDER_SIZE = 3;

    private SeahorseVisualRules() {
    }

    public static boolean isSpecialNamed(String customName) {
        return SPECIAL_NAME.equals(customName);
    }

    public static String texturePath(int typeId, String customName) {
        if (isSpecialNamed(customName)) {
            return "textures/entity/seahorse/unicorn.png";
        }
        return "textures/entity/seahorse/seahorse_" + SeahorseVariantCatalog.normalizeType(typeId) + ".png";
    }

    public static float renderScale(int sizeId) {
        return 1.0F + (((SeahorseVariantCatalog.normalizeSize(sizeId) - DEFAULT_RENDER_SIZE) * 2) * 0.07F);
    }

    public static double renderYOffset(int sizeId) {
        return -((SeahorseVariantCatalog.normalizeSize(sizeId) - DEFAULT_RENDER_SIZE) * 0.18D);
    }

    public static float rainbowParticleUnit(float bbHeight, int sizeId) {
        return (bbHeight - 0.2F - (SeahorseVariantCatalog.normalizeSize(sizeId) - DEFAULT_RENDER_SIZE) * -0.1F) / 7.0F;
    }
}
