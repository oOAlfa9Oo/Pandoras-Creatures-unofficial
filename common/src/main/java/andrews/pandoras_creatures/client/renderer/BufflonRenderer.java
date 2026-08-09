package andrews.pandoras_creatures.client.renderer;

import andrews.pandoras_creatures.client.model.BufflonModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.client.renderer.state.BufflonRenderState;
import andrews.pandoras_creatures.entities.BufflonEntity;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BufflonRenderer extends MobRenderer<BufflonEntity, BufflonRenderState, BufflonModel<BufflonRenderState>> {

    public BufflonRenderer(EntityRendererProvider.Context context) {
        super(context, new BufflonModel<>(context.bakeLayer(PCModelLayers.BUFFLON)), 1.8F);
    }

    @Override
    public BufflonRenderState createRenderState() {
        return new BufflonRenderState();
    }

    @Override
    public void extractRenderState(BufflonEntity entity, BufflonRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.isSaddled = entity.isSaddled();
        state.hasBackAttachment = entity.hasBackAttachment();
        state.backAttachment = entity.getBackAttachment();
        state.occupiedStorageSlotCount = entity.getOccupiedStorageSlotCount();
        state.isSitting = entity.isSitting();
        state.isVehicle = entity.isVehicle();
        state.isMoving = entity.isMoving();
        state.tickCount = entity.tickCount;
        state.bufflonType = entity.getBufflonType();
    }

    @Override
    public ResourceLocation getTextureLocation(BufflonRenderState state) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MODID, "textures/entity/bufflon/bufflon_" + state.bufflonType + ".png");
    }
}
