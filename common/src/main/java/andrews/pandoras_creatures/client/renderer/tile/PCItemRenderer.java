package andrews.pandoras_creatures.client.renderer.tile;

import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.client.model.tile.EndTrollBoxModel;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxPalette;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.Identifier;
import org.joml.Vector3fc;

import java.util.function.Consumer;

public class PCItemRenderer implements NoDataSpecialModelRenderer {
    public static final Identifier DEFAULT_END_TROLL_BOX_TEXTURE = PCEndTrollBoxPalette.textureId(null);

    private final EndTrollBoxModel endTrollBoxModel;
    private final Identifier texture;

    public PCItemRenderer(EntityModelSet modelSet, Identifier texture) {
        this.endTrollBoxModel = new EndTrollBoxModel(modelSet.bakeLayer(PCModelLayers.END_TROLL_BOX));
        this.texture = texture;
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, int packedOverlay, boolean hasFoil, int outlineColor) {
        RenderType renderType = RenderTypes.entityCutout(this.texture);

        poseStack.pushPose();

        submitPart(submitNodeCollector, poseStack, renderType, this.endTrollBoxModel.base, packedLight, packedOverlay);

        submitPart(submitNodeCollector, poseStack, renderType, this.endTrollBoxModel.bottom_front_left, packedLight, packedOverlay);
        submitPart(submitNodeCollector, poseStack, renderType, this.endTrollBoxModel.bottom_front_right, packedLight, packedOverlay);
        submitPart(submitNodeCollector, poseStack, renderType, this.endTrollBoxModel.bottom_back_left, packedLight, packedOverlay);
        submitPart(submitNodeCollector, poseStack, renderType, this.endTrollBoxModel.bottom_back_right, packedLight, packedOverlay);

        submitPart(submitNodeCollector, poseStack, renderType, this.endTrollBoxModel.decoration_front_left, packedLight, packedOverlay);
        submitPart(submitNodeCollector, poseStack, renderType, this.endTrollBoxModel.decoration_front_right, packedLight, packedOverlay);
        submitPart(submitNodeCollector, poseStack, renderType, this.endTrollBoxModel.decoration_back_left, packedLight, packedOverlay);
        submitPart(submitNodeCollector, poseStack, renderType, this.endTrollBoxModel.decoration_back_right, packedLight, packedOverlay);

        submitPart(submitNodeCollector, poseStack, renderType, this.endTrollBoxModel.lid_front, packedLight, packedOverlay);
        submitPart(submitNodeCollector, poseStack, renderType, this.endTrollBoxModel.lid_back, packedLight, packedOverlay);
        submitPart(submitNodeCollector, poseStack, renderType, this.endTrollBoxModel.lid_left, packedLight, packedOverlay);
        submitPart(submitNodeCollector, poseStack, renderType, this.endTrollBoxModel.lid_right, packedLight, packedOverlay);
        submitPart(submitNodeCollector, poseStack, renderType, this.endTrollBoxModel.lid_top_left, packedLight, packedOverlay);
        submitPart(submitNodeCollector, poseStack, renderType, this.endTrollBoxModel.lid_top_right, packedLight, packedOverlay);

        submitPart(submitNodeCollector, poseStack, renderType, this.endTrollBoxModel.top_front_left, packedLight, packedOverlay);
        submitPart(submitNodeCollector, poseStack, renderType, this.endTrollBoxModel.top_front_left_1, packedLight, packedOverlay);
        submitPart(submitNodeCollector, poseStack, renderType, this.endTrollBoxModel.top_back_left, packedLight, packedOverlay);
        submitPart(submitNodeCollector, poseStack, renderType, this.endTrollBoxModel.top_back_right, packedLight, packedOverlay);

        poseStack.popPose();
    }

    @Override
    public void getExtents(Consumer<Vector3fc> consumer) {
        this.endTrollBoxModel.root().getExtentsForGui(new PoseStack(), consumer);
    }

    private static void submitPart(SubmitNodeCollector submitNodeCollector, PoseStack poseStack, RenderType renderType, ModelPart part, int packedLight, int packedOverlay) {
        submitNodeCollector.submitModelPart(part, poseStack, renderType, packedLight, packedOverlay, null, false, false, -1, (ModelFeatureRenderer.CrumblingOverlay) null, 0);
    }

    public record Unbaked(Identifier texture) implements NoDataSpecialModelRenderer.Unbaked {
        public static final MapCodec<Unbaked> MAP_CODEC = Identifier.CODEC
                .optionalFieldOf("texture", DEFAULT_END_TROLL_BOX_TEXTURE)
                .xmap(Unbaked::new, Unbaked::texture);

        @Override
        public SpecialModelRenderer<Void> bake(SpecialModelRenderer.BakingContext context) {
            return new PCItemRenderer(context.entityModelSet(), this.texture);
        }

        @Override
        public MapCodec<Unbaked> type() {
            return MAP_CODEC;
        }
    }
}
