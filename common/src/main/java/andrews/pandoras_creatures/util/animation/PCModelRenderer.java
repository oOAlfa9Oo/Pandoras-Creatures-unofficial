package andrews.pandoras_creatures.util.animation;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

/**
 * Backward compatibility wrapper for PCModelRenderer from 1.16.5
 * This class provides the same API as the original PCModelRenderer but works with ModelPart
 *
 * In 1.21.1, this is a compatibility layer that delegates to ModelPart
 * For new models, consider using ModelPart directly with PCEntityModel
 *
 * Copied Library Functions and Classes from Endergetic
 * see <a href="https://www.curseforge.com/minecraft/mc-mods/endergetic">Mod Page</a>.
 * @author SmellyModder(Luke Tonon)
 *
 * Ported to 1.21.1 NeoForge - Compatibility wrapper
 */
public class PCModelRenderer {
    private final ModelPart modelPart;

    public float defaultX, defaultY, defaultZ;
    public float defaultXRot, defaultYRot, defaultZRot;
    public boolean scaleChildren = true;
    public float[] scales = {1.0F, 1.0F, 1.0F};

    // Expose ModelPart properties for compatibility
    public float x;
    public float y;
    public float z;
    public float xRot;
    public float yRot;
    public float zRot;
    public boolean visible;

    /**
     * Creates a PCModelRenderer wrapping a ModelPart
     */
    public PCModelRenderer(ModelPart modelPart) {
        this.modelPart = modelPart;
        syncFromModelPart();
    }

    /**
     * Syncs the wrapper's properties from the underlying ModelPart
     */
    private void syncFromModelPart() {
        this.x = modelPart.x;
        this.y = modelPart.y;
        this.z = modelPart.z;
        this.xRot = modelPart.xRot;
        this.yRot = modelPart.yRot;
        this.zRot = modelPart.zRot;
        this.visible = modelPart.visible;
    }

    /**
     * Syncs the wrapper's properties to the underlying ModelPart
     */
    public void syncToModelPart() {
        modelPart.x = this.x;
        modelPart.y = this.y;
        modelPart.z = this.z;
        modelPart.xRot = this.xRot;
        modelPart.yRot = this.yRot;
        modelPart.zRot = this.zRot;
        modelPart.visible = this.visible;
    }

    /**
     * Gets the underlying ModelPart
     */
    public ModelPart getModelPart() {
        return this.modelPart;
    }

    /**
     * Sets the default box values for animation reset
     */
    public void setDefaultBoxValues() {
        syncFromModelPart();
        this.defaultX = this.x;
        this.defaultY = this.y;
        this.defaultZ = this.z;

        this.defaultXRot = this.xRot;
        this.defaultYRot = this.yRot;
        this.defaultZRot = this.zRot;
    }

    /**
     * Reverts the current box's values back to the default values
     */
    public void revertToDefaultBoxValues() {
        this.x = this.defaultX;
        this.y = this.defaultY;
        this.z = this.defaultZ;

        this.xRot = this.defaultXRot;
        this.yRot = this.defaultYRot;
        this.zRot = this.defaultZRot;

        syncToModelPart();
    }

    // ===============================================================================
    // Scaling Related Stuff
    // ===============================================================================

    /**
     * Sets the scale
     */
    public void setScale(float x, float y, float z) {
        this.scales[0] = x;
        this.scales[1] = y;
        this.scales[2] = z;
    }

    /**
     * Sets the scale of the X axis
     */
    public void setScaleX(float scaleX) {
        this.scales[0] = scaleX;
    }

    /**
     * Sets the scale of the Y axis
     */
    public void setScaleY(float scaleY) {
        this.scales[1] = scaleY;
    }

    /**
     * Sets the scale of the Z axis
     */
    public void setScaleZ(float scaleZ) {
        this.scales[2] = scaleZ;
    }

    public void setShouldScaleChildren(boolean scaleChildren) {
        this.scaleChildren = scaleChildren;
    }

    // ===============================================================================
    // Looped Animation Methods
    // ===============================================================================

    private float calculateRotation(float speed, float degree, boolean invert, float delay, float weight, float limbSwing, float limbSwingAmount) {
        if (invert) {
            float rotation = (Mth.cos(limbSwing * speed + delay) * degree * limbSwingAmount) - (weight * limbSwingAmount);
            return -rotation;
        } else {
            float rotation = (Mth.cos(limbSwing * speed + delay) * degree * limbSwingAmount) + (weight * limbSwingAmount);
            return rotation;
        }
    }

    /**
     * Rotates this box back and forth (xRot)
     */
    public void swing(float speed, float degree, boolean invert, float delay, float weight, float limbSwing, float limbSwingAmount) {
        this.xRot += this.calculateRotation(speed, degree, invert, delay, weight, limbSwing, limbSwingAmount);
        modelPart.xRot = this.xRot;
    }

    /**
     * Rotates this box right and left (zRot)
     */
    public void flap(float speed, float degree, boolean invert, float delay, float weight, float limbSwing, float limbSwingAmount) {
        this.zRot += this.calculateRotation(speed, degree, invert, delay, weight, limbSwing, limbSwingAmount);
        modelPart.zRot = this.zRot;
    }

    /**
     * Rotates this box side to side (yRot)
     */
    public void shake(float speed, float degree, boolean invert, float delay, float weight, float limbSwing, float limbSwingAmount) {
        this.yRot += this.calculateRotation(speed, degree, invert, delay, weight, limbSwing, limbSwingAmount);
        modelPart.yRot = this.yRot;
    }

    /**
     * This makes the given Box move Up and Down on the Y Axis
     */
    public void bounce(float speed, float height, boolean extraBouncy, float limbSwing, float limbSwingAmount) {
        float bounce = (float) (Math.sin(limbSwing * speed) * limbSwingAmount * height - limbSwingAmount * height);
        if (extraBouncy) {
            bounce = (float) -Math.abs((Math.sin(limbSwing * speed) * limbSwingAmount * height));
        }
        this.y += bounce;
        modelPart.y = this.y;
    }
}
