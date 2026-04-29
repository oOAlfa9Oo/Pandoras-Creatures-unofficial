package andrews.pandoras_creatures.util.animation;

import andrews.pandoras_creatures.entities.bases.AnimatedCreatureEntity;
import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

import java.util.Map;

/**
 * Copied Library Functions and Classes from Endergetic
 * see <a href="https://www.curseforge.com/minecraft/mc-mods/endergetic">Mod Page</a>.
 * @author SmellyModder(Luke Tonon)
 *
 * Ported to 1.21.1 NeoForge - Updated to use ModelPart instead of ModelRenderer
 */
public class Animator {
    private int tempTick;
    private int prevTempTick;
    private boolean correctAnimation;
    public AnimatedCreatureEntity animatedEntity;
    private Map<ModelPart, float[]> boxValues;
    private Map<ModelPart, float[]> prevBoxValues;

    public Animator() {
        this.tempTick = 0;
        this.prevTempTick = 0;
        this.correctAnimation = false;
        this.boxValues = Maps.newHashMap();
        this.prevBoxValues = Maps.newHashMap();
    }

    /**
     * Sets the animation for this animator instance to play
     * @param animationToPlay - The animation to play
     * @return - Is this the correct animation to play
     */
    public boolean setAnimationToPlay(Animation animationToPlay) {
        this.tempTick = this.prevTempTick = 0;
        this.correctAnimation = this.animatedEntity.getPlayingAnimation() == animationToPlay;
        this.prevBoxValues.clear();
        this.prevBoxValues.putAll(this.boxValues);
        this.boxValues.clear();
        return this.correctAnimation;
    }

    /**
     * Updates the entity for this animator instance
     * @param animatedEntity - The entity to update
     */
    public void updateAnimations(AnimatedCreatureEntity animatedEntity) {
        this.animatedEntity = animatedEntity;
    }

    /**
     * Starts a Keyframe for a set amount of ticks
     * @param tickDuration - The duration of the keyframe; measured in ticks
     */
    public void startKeyframe(int tickDuration) {
        if (!this.correctAnimation) return;
        this.prevTempTick = this.tempTick;
        this.tempTick += tickDuration;
    }

    /**
     * Ends the current Keyframe
     */
    public void endKeyframe() {
        this.endKeyframe(false);
    }

    /**
     * Starts a Keyframe that holds the most recent box values for a set duration
     * @param tickDuration - The duration of the Keyframe; measured in ticks
     */
    public void setStaticKeyframe(int tickDuration) {
        this.startKeyframe(tickDuration);
        this.endKeyframe(true);
    }

    /**
     * Resets the current Keyframe to its default values
     * @param tickDuration - The duration of the Keyframe; measured in ticks
     */
    public void resetKeyframe(int tickDuration) {
        this.startKeyframe(tickDuration);
        this.endKeyframe();
    }

    /**
     * Gets the values of a box stored from a map
     * @param modelPart - The ModelPart to look up in the box values map
     * @return - The ModelPart's float array of box values from the box values map
     */
    public float[] getBoxValues(ModelPart modelPart) {
        float[] empty = {0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
        return this.boxValues.computeIfAbsent(modelPart, a -> empty);
    }

    /**
     * Moves a ModelPart in the current Keyframe
     * @param modelPart - ModelPart to move
     * @param x - The x point
     * @param y - The y point
     * @param z - The z point
     */
    public void move(ModelPart modelPart, float x, float y, float z) {
        if (!this.correctAnimation) return;
        this.getBoxValues(modelPart)[0] = x;
        this.getBoxValues(modelPart)[1] = y;
        this.getBoxValues(modelPart)[2] = z;
    }

    /**
     * Rotates a ModelPart in the current Keyframe
     * @param modelPart - ModelPart to rotate
     * @param x - The x rotation
     * @param y - The y rotation
     * @param z - The z rotation
     */
    public void rotate(ModelPart modelPart, float x, float y, float z) {
        if (!this.correctAnimation) return;
        this.getBoxValues(modelPart)[3] = x;
        this.getBoxValues(modelPart)[4] = y;
        this.getBoxValues(modelPart)[5] = z;
    }

    private void endKeyframe(boolean stationary) {
        if (!this.correctAnimation) return;

        int animationTick = this.animatedEntity.getAnimationTick();

        if (animationTick >= this.prevTempTick && animationTick < this.tempTick) {
            if (stationary) {
                for (ModelPart box : this.prevBoxValues.keySet()) {
                    float[] transform = this.prevBoxValues.get(box);
                    box.x += transform[0];
                    box.y += transform[1];
                    box.z += transform[2];
                    box.xRot += transform[3];
                    box.yRot += transform[4];
                    box.zRot += transform[5];
                }
            } else {
                float tick = (animationTick - this.prevTempTick + getPartialTicks()) / (this.tempTick - this.prevTempTick);
                float increment = Mth.sin((float) (tick * Math.PI / 2.0F));
                float decrement = 1.0F - increment;
                for (ModelPart box : this.prevBoxValues.keySet()) {
                    float[] transform = this.prevBoxValues.get(box);
                    box.x += decrement * transform[0];
                    box.y += decrement * transform[1];
                    box.z += decrement * transform[2];
                    box.xRot += decrement * transform[3];
                    box.yRot += decrement * transform[4];
                    box.zRot += decrement * transform[5];
                }
                for (ModelPart box : this.boxValues.keySet()) {
                    float[] transform = this.boxValues.get(box);
                    box.x += increment * transform[0];
                    box.y += increment * transform[1];
                    box.z += increment * transform[2];
                    box.xRot += increment * transform[3];
                    box.yRot += increment * transform[4];
                    box.zRot += increment * transform[5];
                }
            }
        }
        if (!stationary) {
            this.prevBoxValues.clear();
            this.prevBoxValues.putAll(this.boxValues);
            this.boxValues.clear();
        }
    }

    /**
     * @return - The partial ticks of the minecraft client
     */
    private static float getPartialTicks() {
        return Minecraft.getInstance().getFrameTime();
    }
}
