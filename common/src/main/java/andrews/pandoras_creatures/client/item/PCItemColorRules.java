package andrews.pandoras_creatures.client.item;

public final class PCItemColorRules {
    private static final int OPAQUE_ALPHA_MASK = 0xFF000000;

    private PCItemColorRules() {
    }

    public static int withOpaqueAlpha(int rgb) {
        return OPAQUE_ALPHA_MASK | rgb;
    }
}
