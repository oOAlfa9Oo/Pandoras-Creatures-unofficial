package andrews.pandoras_creatures.client.renderer.base;

import andrews.pandoras_creatures.client.model.base.PCEntityModel;
import andrews.pandoras_creatures.client.model.base.PCEntityRenderState;
import andrews.pandoras_creatures.entities.bases.IAnimatedEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Mob;

/**
 * Shared bridge from legacy entity-driven mod renderers to Minecraft 26.1 render states.
 */
public abstract class PCMobRenderer<E extends Mob & IAnimatedEntity, M extends PCEntityModel<E>> extends MobRenderer<E, PCEntityRenderState<E>, M> {
    protected PCMobRenderer(EntityRendererProvider.Context context, M model, float shadowRadius) {
        super(context, model, shadowRadius);
    }

    @Override
    public PCEntityRenderState<E> createRenderState() {
        return new PCEntityRenderState<>();
    }

    @Override
    public void extractRenderState(E entity, PCEntityRenderState<E> state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.entity = entity;
        state.limbSwing = state.walkAnimationPos;
        state.limbSwingAmount = state.walkAnimationSpeed;
        state.netHeadYaw = state.yRot - state.bodyRot;
        state.headPitch = state.xRot;
        state.partialTick = partialTick;
    }

    @Override
    public Identifier getTextureLocation(PCEntityRenderState<E> state) {
        return getTextureLocation(state.entity);
    }

    protected abstract Identifier getTextureLocation(E entity);
}
