package andrews.pandoras_creatures.client.model.tile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

/**
 * EndTrollBoxModel - andrew0030
 * Migrated to 1.21.1 LayerDefinition system
 */
@OnlyIn(Dist.CLIENT)
public class EndTrollBoxModel extends Model {
    public final ModelPart base;
    public final ModelPart bottom_front_left;
    public final ModelPart bottom_back_left;
    public final ModelPart bottom_front_right;
    public final ModelPart bottom_back_right;
    public final ModelPart top_front_left;
    public final ModelPart top_back_left;
    public final ModelPart top_back_right;
    public final ModelPart top_front_left_1;
    public final ModelPart lid_top_left;
    public final ModelPart lid_top_right;
    public final ModelPart lid_front;
    public final ModelPart lid_left;
    public final ModelPart lid_back;
    public final ModelPart lid_right;
    public final ModelPart decoration_front_right;
    public final ModelPart decoration_front_left;
    public final ModelPart decoration_back_left;
    public final ModelPart decoration_back_right;

    public EndTrollBoxModel(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        this.base = root.getChild("base");
        this.bottom_front_left = root.getChild("bottom_front_left");
        this.bottom_back_left = root.getChild("bottom_back_left");
        this.bottom_front_right = root.getChild("bottom_front_right");
        this.bottom_back_right = root.getChild("bottom_back_right");
        this.top_front_left = root.getChild("top_front_left");
        this.top_back_left = root.getChild("top_back_left");
        this.top_back_right = root.getChild("top_back_right");
        this.top_front_left_1 = root.getChild("top_front_left_1");
        this.lid_top_left = root.getChild("lid_top_left");
        this.lid_top_right = root.getChild("lid_top_right");
        this.lid_front = root.getChild("lid_front");
        this.lid_left = root.getChild("lid_left");
        this.lid_back = root.getChild("lid_back");
        this.lid_right = root.getChild("lid_right");
        this.decoration_front_right = root.getChild("decoration_front_right");
        this.decoration_front_left = root.getChild("decoration_front_left");
        this.decoration_back_left = root.getChild("decoration_back_left");
        this.decoration_back_right = root.getChild("decoration_back_right");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        // Base
        partdefinition.addOrReplaceChild("base", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-6.0F, -10.0F, -6.0F, 12, 10, 12),
                PartPose.offset(0.0F, 23.5F, 0.0F));

        // Bottom corners
        partdefinition.addOrReplaceChild("bottom_front_left", CubeListBuilder.create()
                .texOffs(0, 23).addBox(-3.0F, -3.0F, 0.0F, 3, 3, 3),
                PartPose.offset(6.5F, 24.0F, -6.5F));

        partdefinition.addOrReplaceChild("bottom_back_left", CubeListBuilder.create()
                .texOffs(0, 30).addBox(-3.0F, -3.0F, -3.0F, 3, 3, 3),
                PartPose.offset(6.5F, 24.0F, 6.5F));

        partdefinition.addOrReplaceChild("bottom_front_right", CubeListBuilder.create()
                .texOffs(13, 23).addBox(0.0F, -3.0F, 0.0F, 3, 3, 3),
                PartPose.offset(-6.5F, 24.0F, -6.5F));

        partdefinition.addOrReplaceChild("bottom_back_right", CubeListBuilder.create()
                .texOffs(13, 30).addBox(0.0F, -3.0F, -3.0F, 3, 3, 3),
                PartPose.offset(-6.5F, 24.0F, 6.5F));

        // Top corners
        partdefinition.addOrReplaceChild("top_front_left", CubeListBuilder.create()
                .texOffs(39, 23).addBox(0.0F, 0.0F, 0.0F, 3, 3, 3),
                PartPose.offset(-6.5F, 11.0F, -6.5F));

        partdefinition.addOrReplaceChild("top_back_left", CubeListBuilder.create()
                .texOffs(26, 30).addBox(-3.0F, 0.0F, -3.0F, 3, 3, 3),
                PartPose.offset(6.5F, 11.0F, 6.5F));

        partdefinition.addOrReplaceChild("top_back_right", CubeListBuilder.create()
                .texOffs(39, 30).addBox(0.0F, 0.0F, -3.0F, 3, 3, 3),
                PartPose.offset(-6.5F, 11.0F, 6.5F));

        partdefinition.addOrReplaceChild("top_front_left_1", CubeListBuilder.create()
                .texOffs(26, 23).addBox(-3.0F, 0.0F, 0.0F, 3, 3, 3),
                PartPose.offset(6.5F, 11.0F, -6.5F));

        // Lids
        partdefinition.addOrReplaceChild("lid_top_left", CubeListBuilder.create()
                .texOffs(0, 53).addBox(-6.0F, 0.0F, -5.0F, 6, 1, 10),
                PartPose.offset(6.0F, 11.5F, 0.0F));

        partdefinition.addOrReplaceChild("lid_top_right", CubeListBuilder.create()
                .texOffs(31, 41).addBox(0.0F, 0.0F, -5.0F, 6, 1, 10),
                PartPose.offset(-6.0F, 11.5F, 0.0F));

        partdefinition.addOrReplaceChild("lid_front", CubeListBuilder.create()
                .texOffs(0, 37).addBox(-5.0F, -2.0F, 0.0F, 10, 2, 1),
                PartPose.offset(0.0F, 13.5F, -6.0F));

        partdefinition.addOrReplaceChild("lid_left", CubeListBuilder.create()
                .texOffs(0, 41).addBox(-1.0F, -2.0F, -5.0F, 1, 2, 10),
                PartPose.offset(6.0F, 13.5F, 0.0F));

        partdefinition.addOrReplaceChild("lid_back", CubeListBuilder.create()
                .texOffs(23, 37).addBox(-5.0F, -2.0F, -1.0F, 10, 2, 1),
                PartPose.offset(0.0F, 13.5F, 6.0F));

        partdefinition.addOrReplaceChild("lid_right", CubeListBuilder.create()
                .texOffs(42, 0).addBox(0.0F, -2.0F, -5.0F, 1, 2, 10),
                PartPose.offset(-6.0F, 13.5F, 0.0F));

        // Decorations
        partdefinition.addOrReplaceChild("decoration_front_right", CubeListBuilder.create()
                .texOffs(0, 0).addBox(0.0F, -1.0F, 0.0F, 1, 1, 1),
                PartPose.offset(-6.0F, 13.5F, -6.0F));

        partdefinition.addOrReplaceChild("decoration_front_left", CubeListBuilder.create()
                .texOffs(5, 0).addBox(-1.0F, -1.0F, 0.0F, 1, 1, 1),
                PartPose.offset(6.0F, 13.5F, -6.0F));

        partdefinition.addOrReplaceChild("decoration_back_left", CubeListBuilder.create()
                .texOffs(0, 3).addBox(-1.0F, -1.0F, -1.0F, 1, 1, 1),
                PartPose.offset(6.0F, 13.5F, 6.0F));

        partdefinition.addOrReplaceChild("decoration_back_right", CubeListBuilder.create()
                .texOffs(5, 3).addBox(0.0F, -1.0F, -1.0F, 1, 1, 1),
                PartPose.offset(-6.0F, 13.5F, 6.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        // This is called for simple rendering, but we handle complex rendering in the BEWLR
        base.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bottom_front_left.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bottom_back_left.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bottom_front_right.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bottom_back_right.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        top_front_left.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        top_back_left.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        top_back_right.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        top_front_left_1.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        lid_top_left.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        lid_top_right.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        lid_front.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        lid_left.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        lid_back.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        lid_right.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        decoration_front_right.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        decoration_front_left.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        decoration_back_left.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        decoration_back_right.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
