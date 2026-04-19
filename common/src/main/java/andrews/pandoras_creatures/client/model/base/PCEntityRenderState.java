package andrews.pandoras_creatures.client.model.base;

import andrews.pandoras_creatures.entities.bases.IAnimatedEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.Entity;

/**
 * Carries legacy entity animation data through Minecraft 26.1's render-state model API.
 */
public class PCEntityRenderState<E extends Entity & IAnimatedEntity> extends LivingEntityRenderState {
    public E entity;
    public float limbSwing;
    public float limbSwingAmount;
    public float netHeadYaw;
    public float headPitch;
    public float partialTick;
}
