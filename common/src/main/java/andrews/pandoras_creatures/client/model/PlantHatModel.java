package andrews.pandoras_creatures.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.util.Mth;

public class PlantHatModel extends HumanoidModel<HumanoidRenderState> {
    private static final float HP_BASE_Y = 0.7853981633974483F;
    private static final float HP2_BASE_Y = 2.356194490192345F;

    private final ModelPart hatBase;
    private final ModelPart hangingPlants;
    private final ModelPart hangingPlants1;
    private final ModelPart hangingPlants2;

    public PlantHatModel(ModelPart root) {
        super(root);
        this.hatBase = root.getChild("hat_base");
        this.hangingPlants = hatBase.getChild("hanging_plants");
        this.hangingPlants1 = hatBase.getChild("hanging_plants_1");
        this.hangingPlants2 = hatBase.getChild("hanging_plants_2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);

        PartDefinition hatBase = root.addOrReplaceChild("hat_base",
                CubeListBuilder.create().texOffs(104, 15)
                        .addBox(-3.0F, -13.0F, -3.0F, 6, 5, 6, CubeDeformation.NONE),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.012740903539558604F));

        hatBase.addOrReplaceChild("top_plants",
                CubeListBuilder.create().texOffs(52, 0).addBox(-6.0F, 0.0F, -10.0F, 12, 0, 12, CubeDeformation.NONE),
                PartPose.offsetAndRotation(0.0F, -13.0F, -3.0F, 0.35779249665883756F, 0.0F, 0.0F));
        hatBase.addOrReplaceChild("top_plants_1",
                CubeListBuilder.create().texOffs(52, 14).addBox(-6.0F, 0.0F, -2.0F, 12, 0, 12, CubeDeformation.NONE),
                PartPose.offsetAndRotation(0.0F, -13.0F, 3.0F, -0.619591884457987F, 0.0F, 0.0F));
        hatBase.addOrReplaceChild("top_plants_2",
                CubeListBuilder.create().texOffs(68, 26).addBox(-2.0F, 0.0F, -6.0F, 12, 0, 12, CubeDeformation.NONE),
                PartPose.offsetAndRotation(3.0F, -13.0F, 0.0F, 0.0F, 0.0F, 0.6283185307179586F));
        hatBase.addOrReplaceChild("top_plants_3",
                CubeListBuilder.create().texOffs(92, 26).addBox(-10.0F, 0.0F, -6.0F, 12, 0, 12, CubeDeformation.NONE),
                PartPose.offsetAndRotation(-3.0F, -13.0F, 0.0F, 0.0F, 0.0F, -0.6283185307179586F));

        hatBase.addOrReplaceChild("wood_beam",
                CubeListBuilder.create().texOffs(94, 2).addBox(-0.5F, 0.0F, -10.0F, 1, 1, 10, CubeDeformation.NONE),
                PartPose.offsetAndRotation(0.0F, -9.0F, -2.8F, -0.148352986419518F, 0.0F, 0.0F));
        hatBase.addOrReplaceChild("wood_beam_1",
                CubeListBuilder.create().texOffs(82, 3).addBox(-0.5F, 0.0F, 0.0F, 1, 1, 10, CubeDeformation.NONE),
                PartPose.offsetAndRotation(0.0F, -9.0F, 3.0F, -0.2181661564992912F, 0.0F, 0.0F));
        hatBase.addOrReplaceChild("wood_beam_2",
                CubeListBuilder.create().texOffs(106, 2).addBox(0.0F, 0.0F, -0.5F, 10, 1, 1, CubeDeformation.NONE),
                PartPose.offsetAndRotation(3.0F, -9.0F, 0.0F, 0.0F, 0.0F, 0.2181661564992912F));
        hatBase.addOrReplaceChild("wood_beam_3",
                CubeListBuilder.create().texOffs(106, 8).addBox(-10.0F, 0.0F, -0.5F, 10, 1, 1, CubeDeformation.NONE),
                PartPose.offsetAndRotation(-3.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.2181661564992912F));
        hatBase.addOrReplaceChild("wood_beam_4",
                CubeListBuilder.create().texOffs(108, 0).addBox(-1.0F, 0.0F, -0.5F, 9, 1, 1, CubeDeformation.NONE),
                PartPose.offsetAndRotation(3.0F, -9.01F, -3.0F, 0.0F, 0.7853981633974483F, 0.0F));
        hatBase.addOrReplaceChild("wood_beam_5",
                CubeListBuilder.create().texOffs(108, 10).addBox(-1.0F, 0.0F, -0.5F, 9, 1, 1, CubeDeformation.NONE),
                PartPose.offsetAndRotation(-3.0F, -9.01F, -3.0F, 0.0F, 2.356194490192345F, 0.0F));
        hatBase.addOrReplaceChild("wood_beam_6",
                CubeListBuilder.create().texOffs(108, 4).addBox(-1.0F, 0.0F, -0.5F, 9, 1, 1, CubeDeformation.NONE),
                PartPose.offsetAndRotation(3.0F, -9.01F, 3.0F, 0.0F, -0.7853981633974483F, 0.08726646259971647F));
        hatBase.addOrReplaceChild("wood_beam_7",
                CubeListBuilder.create().texOffs(108, 6).addBox(-1.0F, 0.0F, -0.5F, 9, 1, 1, CubeDeformation.NONE),
                PartPose.offsetAndRotation(-3.0F, -9.01F, 3.0F, 0.0F, -2.356194490192345F, -0.08726646259971647F));

        hatBase.addOrReplaceChild("plants",
                CubeListBuilder.create().texOffs(54, 54).addBox(0.0F, 0.0F, -10.0F, 8, 0, 10, CubeDeformation.NONE),
                PartPose.offsetAndRotation(0.2F, -8.8F, -3.0F, -0.1361356816555577F, 0.0F, 0.1361356816555577F));
        hatBase.addOrReplaceChild("plants_1",
                CubeListBuilder.create().texOffs(54, 32).addBox(-8.0F, 0.0F, -10.0F, 8, 0, 10, CubeDeformation.NONE),
                PartPose.offsetAndRotation(-0.2F, -8.8F, -3.0F, -0.1361356816555577F, 0.0F, -0.1361356816555577F));
        hatBase.addOrReplaceChild("plants_2",
                CubeListBuilder.create().texOffs(54, 43).addBox(0.0F, 0.0F, 0.0F, 8, 0, 10, CubeDeformation.NONE),
                PartPose.offsetAndRotation(0.2F, -8.7F, 3.0F, -0.22689280275926282F, 0.0F, -0.08726646259971647F));
        hatBase.addOrReplaceChild("plants_3",
                CubeListBuilder.create().texOffs(71, 45).addBox(-8.0F, 0.0F, 0.0F, 8, 0, 10, CubeDeformation.NONE),
                PartPose.offsetAndRotation(-0.2F, -8.7F, 3.0F, -0.22689280275926282F, 0.0F, 0.08726646259971647F));
        hatBase.addOrReplaceChild("plants_4",
                CubeListBuilder.create().texOffs(94, 56).addBox(0.0F, 0.0F, 0.0F, 10, 0, 8, CubeDeformation.NONE),
                PartPose.offsetAndRotation(3.0F, -8.8F, 0.3F, 0.06981317007977318F, 0.0F, 0.22689280275926282F));
        hatBase.addOrReplaceChild("plants_5",
                CubeListBuilder.create().texOffs(73, 56).addBox(0.0F, 0.0F, -8.0F, 10, 0, 8, CubeDeformation.NONE),
                PartPose.offsetAndRotation(3.0F, -8.7F, -0.3F, -0.10471975511965977F, 0.0F, 0.22689280275926282F));
        hatBase.addOrReplaceChild("plants_6",
                CubeListBuilder.create().texOffs(90, 47).addBox(-10.0F, 0.0F, 0.0F, 10, 0, 8, CubeDeformation.NONE),
                PartPose.offsetAndRotation(-3.0F, -8.8F, 0.3F, 0.06981317007977318F, 0.0F, -0.22689280275926282F));
        hatBase.addOrReplaceChild("plants_7",
                CubeListBuilder.create().texOffs(90, 38).addBox(-10.0F, 0.0F, -8.0F, 10, 0, 8, CubeDeformation.NONE),
                PartPose.offsetAndRotation(-3.0F, -8.7F, -0.3F, -0.10471975511965977F, 0.0F, -0.22689280275926282F));

        hatBase.addOrReplaceChild("hanging_plants",
                CubeListBuilder.create().texOffs(94, -5).addBox(0.0F, 0.0F, 0.0F, 0, 6, 5, CubeDeformation.NONE),
                PartPose.offsetAndRotation(-8.0F, -8.4F, -8.0F, 0.0F, HP_BASE_Y, 0.0F));
        hatBase.addOrReplaceChild("hanging_plants_1",
                CubeListBuilder.create().texOffs(94, 1).addBox(0.0F, 0.0F, 0.0F, 0, 6, 5, CubeDeformation.NONE),
                PartPose.offset(0.0F, -7.3F, 5.3F));
        hatBase.addOrReplaceChild("hanging_plants_2",
                CubeListBuilder.create().texOffs(89, 9).addBox(0.0F, 0.0F, 0.0F, 0, 6, 6, CubeDeformation.NONE),
                PartPose.offsetAndRotation(4.0F, -8.0F, -4.0F, 0.0F, HP2_BASE_Y, 0.0F));

        PartDefinition strap = hatBase.addOrReplaceChild("strap",
                CubeListBuilder.create().texOffs(108, 13).addBox(-4.5F, 0.0F, 0.0F, 9, 1, 1, CubeDeformation.NONE),
                PartPose.offsetAndRotation(0.0F, -0.5F, -3.5F, -0.3700098014227978F, 0.0F, 0.0F));
        strap.addOrReplaceChild("strap_1",
                CubeListBuilder.create().texOffs(119, 39).addBox(-1.0F, -8.0F, 0.0F, 1, 8, 1, CubeDeformation.NONE),
                PartPose.offset(4.5F, 0.0F, 0.0F));
        strap.addOrReplaceChild("strap_2",
                CubeListBuilder.create().texOffs(124, 39).addBox(0.0F, -8.0F, 0.0F, 1, 8, 1, CubeDeformation.NONE),
                PartPose.offset(-4.5F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 64);
    }

    public void prepareForRender(HumanoidRenderState state, HumanoidModel<HumanoidRenderState> original) {
        this.head.xRot = original.head.xRot;
        this.head.yRot = original.head.yRot;
        this.head.zRot = original.head.zRot;
        this.hatBase.xRot = this.head.xRot;
        this.hatBase.yRot = this.head.yRot;
        this.hatBase.zRot = this.head.zRot;

        this.hangingPlants.yRot = HP_BASE_Y;
        this.hangingPlants.zRot = 0.0F;
        this.hangingPlants1.yRot = 0.0F;
        this.hangingPlants1.zRot = 0.0F;
        this.hangingPlants2.yRot = HP2_BASE_Y;
        this.hangingPlants2.zRot = 0.0F;

        if (state.walkAnimationSpeed > 0.0F) {
            float limbSwing = state.walkAnimationPos;
            float limbSwingAmount = state.walkAnimationSpeed;

            this.hangingPlants.zRot += calcRot(0.4F, 0.12F, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            this.hangingPlants.yRot += calcRot(0.4F, 0.10F, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            this.hangingPlants1.zRot += calcRot(0.4F, 0.12F, false, 0.0F, 0.05F, limbSwing, limbSwingAmount);
            this.hangingPlants1.yRot += calcRot(0.4F, 0.10F, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            this.hangingPlants2.zRot += calcRot(0.4F, 0.02F, false, 0.0F, -0.1F, limbSwing, limbSwingAmount);
            this.hangingPlants2.yRot += calcRot(0.4F, 0.04F, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        }
    }

    private float calcRot(float speed, float degree, boolean invert, float delay, float weight, float limbSwing, float limbSwingAmount) {
        float rotation = (Mth.cos(limbSwing * speed + delay) * degree * limbSwingAmount) + (weight * limbSwingAmount);
        return invert ? -rotation : rotation;
    }
}
