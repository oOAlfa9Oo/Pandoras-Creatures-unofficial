package andrews.pandoras_creatures.entities.arachnon;

public final class ArachnonVisualRules {
    private static final String TEXTURE_PATH = "textures/entity/arachnon/arachnon.png";
    private static final String EYE_TEXTURE_PATH = "textures/entity/arachnon/arachnon_eye.png";
    private static final float RENDER_SCALE = 1.4F;
    private static final float RENDER_Y_OFFSET = -0.1F;

    private ArachnonVisualRules() {
    }

    public static String texturePathString() {
        return TEXTURE_PATH;
    }

    public static String eyeTexturePathString() {
        return EYE_TEXTURE_PATH;
    }

    public static float renderScale() {
        return RENDER_SCALE;
    }

    public static float renderYOffset() {
        return RENDER_Y_OFFSET;
    }
}
