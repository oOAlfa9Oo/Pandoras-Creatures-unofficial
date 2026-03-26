package andrews.pandoras_creatures.client.model.base;

import andrews.pandoras_creatures.entities.bases.IAnimatedEntity;
import andrews.pandoras_creatures.util.animation.Animator;
import com.google.common.collect.Lists;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base class for animated entity models in NeoForge 1.21.1
 * Adapted from the original PCEntityModel for the new model system
 */
public abstract class PCEntityModel<E extends Entity & IAnimatedEntity> extends EntityModel<E> {
    protected final ModelPart root;
    protected E entity;
    protected Animator animator = new Animator();

    // Store default values for animation reset
    protected final Map<ModelPart, float[]> defaultRotations = new HashMap<>();
    protected final Map<ModelPart, float[]> defaultPositions = new HashMap<>();
    protected final List<ModelPart> animatedParts = Lists.newArrayList();

    public PCEntityModel(ModelPart root) {
        this.root = root;
    }

    /**
     * Call this in subclass constructor after getting all model parts to store their default values
     */
    protected void registerAnimatedPart(ModelPart part) {
        this.animatedParts.add(part);
        this.defaultRotations.put(part, new float[]{part.xRot, part.yRot, part.zRot});
        this.defaultPositions.put(part, new float[]{part.x, part.y, part.z});
    }

    /**
     * Reverts all animated parts to their default values
     */
    public void revertToDefaultBoxValues() {
        for (ModelPart part : animatedParts) {
            float[] rotations = defaultRotations.get(part);
            float[] positions = defaultPositions.get(part);
            if (rotations != null) {
                part.xRot = rotations[0];
                part.yRot = rotations[1];
                part.zRot = rotations[2];
            }
            if (positions != null) {
                part.x = positions[0];
                part.y = positions[1];
                part.z = positions[2];
            }
        }
    }

    @Override
    public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.entity = entity;
        revertToDefaultBoxValues();
    }

    public void animateModel(E animatedEntity) {}

    public ModelPart root() {
        return this.root;
    }

    // ===============================================================================
    // Animation helper methods
    // ===============================================================================

    /**
     * Rotates this box back and forth (xRot)
     */
    protected void swing(ModelPart box, float speed, float degree, boolean invert, float delay, float weight, float limbSwing, float limbSwingAmount) {
        float rotation = calculateRotation(speed, degree, invert, delay, weight, limbSwing, limbSwingAmount);
        box.xRot += rotation;
    }

    /**
     * Rotates this box right and left (zRot)
     */
    protected void flap(ModelPart box, float speed, float degree, boolean invert, float delay, float weight, float limbSwing, float limbSwingAmount) {
        float rotation = calculateRotation(speed, degree, invert, delay, weight, limbSwing, limbSwingAmount);
        box.zRot += rotation;
    }

    /**
     * Rotates this box side to side (yRot)
     */
    protected void shake(ModelPart box, float speed, float degree, boolean invert, float delay, float weight, float limbSwing, float limbSwingAmount) {
        float rotation = calculateRotation(speed, degree, invert, delay, weight, limbSwing, limbSwingAmount);
        box.yRot += rotation;
    }

    /**
     * Makes the given box move up and down on the Y axis
     */
    protected void bounce(ModelPart box, float speed, float height, boolean extraBouncy, float limbSwing, float limbSwingAmount) {
        float bounce = (float) (Math.sin(limbSwing * speed) * limbSwingAmount * height - limbSwingAmount * height);
        if (extraBouncy) {
            bounce = (float) -Math.abs((Math.sin(limbSwing * speed) * limbSwingAmount * height));
        }
        box.y += bounce;
    }

    private float calculateRotation(float speed, float degree, boolean invert, float delay, float weight, float limbSwing, float limbSwingAmount) {
        float rotation = (float) (Math.cos(limbSwing * speed + delay) * degree * limbSwingAmount) + (weight * limbSwingAmount);
        return invert ? -rotation : rotation;
    }
}
