package andrews.pandoras_creatures.client.renderer;

import andrews.pandoras_creatures.client.model.EndTrollBulletModel;
import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.entities.projectiles.AbstractEndTrollBulletEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public abstract class EndTrollBulletRenderer<T extends AbstractEndTrollBulletEntity> extends EntityRenderer<T, EntityRenderState> {
    private static final int TRANSLUCENT_WHITE = 0x26FFFFFF;

    private final EndTrollBulletModel model;
    private final Identifier texture;
    private final RenderType translucentRenderType;

    protected EndTrollBulletRenderer(EntityRendererProvider.Context context, Identifier texture) {
        super(context);
        this.model = new EndTrollBulletModel(context.bakeLayer(PCModelLayers.END_TROLL_BULLET));
        this.texture = texture;
        this.translucentRenderType = RenderTypes.entityTranslucent(texture);
    }

    @Override
    public EntityRenderState createRenderState() {
        return new EntityRenderState();
    }

    @Override
    public void submit(EntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        float ageInTicks = state.ageInTicks;

        poseStack.pushPose();
        poseStack.translate(0.0F, 0.15F, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.sin(ageInTicks * 0.1F) * 180.0F));
        poseStack.mulPose(Axis.XP.rotationDegrees(Mth.cos(ageInTicks * 0.1F) * 180.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.sin(ageInTicks * 0.15F) * 360.0F));
        poseStack.scale(-0.5F, -0.5F, 0.5F);

        submitNodeCollector.submitModel(this.model, state, poseStack, this.texture, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);

        poseStack.scale(1.5F, 1.5F, 1.5F);
        submitNodeCollector.order(1).submitModel(
                this.model,
                state,
                poseStack,
                this.translucentRenderType,
                state.lightCoords,
                OverlayTexture.NO_OVERLAY,
                TRANSLUCENT_WHITE,
                null,
                state.outlineColor,
                null
        );

        poseStack.popPose();
        super.submit(state, poseStack, submitNodeCollector, cameraRenderState);
    }

    @Override
    protected int getBlockLightLevel(T entity, BlockPos pos) {
        return 15;
    }
}
