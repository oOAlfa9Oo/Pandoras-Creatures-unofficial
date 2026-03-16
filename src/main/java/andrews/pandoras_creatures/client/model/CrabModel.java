package andrews.pandoras_creatures.client.model;

import andrews.pandoras_creatures.client.model.base.PCEntityModel;
import andrews.pandoras_creatures.entities.CrabEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrabModel<T extends CrabEntity> extends PCEntityModel<T> {
    private final ModelPart Body;
    private final ModelPart BodyBottom;
    private final ModelPart BodyTop;
    private final ModelPart Hat;
    private final ModelPart HatTop;
    private final ModelPart ArmBackRight;
    private final ModelPart ArmBackLeft;
    private final ModelPart ArmFrontRight;
    private final ModelPart ArmFrontLeft;
    private final ModelPart ScissorBigRight;
    private final ModelPart ScissorSmallRight;
    private final ModelPart ScissorBigLeft;
    private final ModelPart ScissorSmallLeft;
    private final ModelPart LegLeftFirst_1;
    private final ModelPart LegLeftFirst_2;
    private final ModelPart LegLeftSecond_1;
    private final ModelPart LegLeftSecond_2;
    private final ModelPart LegLeftThird_1;
    private final ModelPart LegLeftThird_2;
    private final ModelPart LegLeftFourth_1;
    private final ModelPart LegLeftFourth_2;
    private final ModelPart LegRightFirst_1;
    private final ModelPart LegRightFirst_2;
    private final ModelPart LegRightSecond_1;
    private final ModelPart LegRightSecond_2;
    private final ModelPart LegRightThird_1;
    private final ModelPart LegRightThird_2;
    private final ModelPart LegRightFourth_1;
    private final ModelPart LegRightFourth_2;

    public CrabModel(ModelPart root) {
        super(root);
        this.Body = root.getChild("Body");
        this.BodyBottom = this.Body.getChild("BodyBottom");
        this.BodyTop = this.BodyBottom.getChild("BodyTop");
        this.Hat = this.BodyTop.getChild("Hat");
        this.HatTop = this.Hat.getChild("HatTop");
        this.ArmBackRight = this.Body.getChild("ArmBackRight");
        this.ArmBackLeft = this.Body.getChild("ArmBackLeft");
        this.ArmFrontRight = this.ArmBackRight.getChild("ArmFrontRight");
        this.ArmFrontLeft = this.ArmBackLeft.getChild("ArmFrontLeft");
        this.ScissorBigRight = this.ArmFrontRight.getChild("ScissorBigRight");
        this.ScissorSmallRight = this.ScissorBigRight.getChild("ScissorSmallRight");
        this.ScissorBigLeft = this.ArmFrontLeft.getChild("ScissorBigLeft");
        this.ScissorSmallLeft = this.ScissorBigLeft.getChild("ScissorSmallLeft");
        this.LegLeftFirst_1 = this.Body.getChild("LegLeftFirst_1");
        this.LegLeftFirst_2 = this.LegLeftFirst_1.getChild("LegLeftFirst_2");
        this.LegLeftSecond_1 = this.Body.getChild("LegLeftSecond_1");
        this.LegLeftSecond_2 = this.LegLeftSecond_1.getChild("LegLeftSecond_2");
        this.LegLeftThird_1 = this.Body.getChild("LegLeftThird_1");
        this.LegLeftThird_2 = this.LegLeftThird_1.getChild("LegLeftThird_2");
        this.LegLeftFourth_1 = this.Body.getChild("LegLeftFourth_1");
        this.LegLeftFourth_2 = this.LegLeftFourth_1.getChild("LegLeftFourth_2");
        this.LegRightFirst_1 = this.Body.getChild("LegRightFirst_1");
        this.LegRightFirst_2 = this.LegRightFirst_1.getChild("LegRightFirst_2");
        this.LegRightSecond_1 = this.Body.getChild("LegRightSecond_1");
        this.LegRightSecond_2 = this.LegRightSecond_1.getChild("LegRightSecond_2");
        this.LegRightThird_1 = this.Body.getChild("LegRightThird_1");
        this.LegRightThird_2 = this.LegRightThird_1.getChild("LegRightThird_2");
        this.LegRightFourth_1 = this.Body.getChild("LegRightFourth_1");
        this.LegRightFourth_2 = this.LegRightFourth_1.getChild("LegRightFourth_2");

        // Register all animated parts for default value storage
        registerAnimatedParts();
    }

    private void registerAnimatedParts() {
        registerAnimatedPart(Body);
        registerAnimatedPart(BodyBottom);
        registerAnimatedPart(BodyTop);
        registerAnimatedPart(Hat);
        registerAnimatedPart(HatTop);
        registerAnimatedPart(ArmBackRight);
        registerAnimatedPart(ArmBackLeft);
        registerAnimatedPart(ArmFrontRight);
        registerAnimatedPart(ArmFrontLeft);
        registerAnimatedPart(ScissorBigRight);
        registerAnimatedPart(ScissorSmallRight);
        registerAnimatedPart(ScissorBigLeft);
        registerAnimatedPart(ScissorSmallLeft);
        registerAnimatedPart(LegLeftFirst_1);
        registerAnimatedPart(LegLeftFirst_2);
        registerAnimatedPart(LegLeftSecond_1);
        registerAnimatedPart(LegLeftSecond_2);
        registerAnimatedPart(LegLeftThird_1);
        registerAnimatedPart(LegLeftThird_2);
        registerAnimatedPart(LegLeftFourth_1);
        registerAnimatedPart(LegLeftFourth_2);
        registerAnimatedPart(LegRightFirst_1);
        registerAnimatedPart(LegRightFirst_2);
        registerAnimatedPart(LegRightSecond_1);
        registerAnimatedPart(LegRightSecond_2);
        registerAnimatedPart(LegRightThird_1);
        registerAnimatedPart(LegRightThird_2);
        registerAnimatedPart(LegRightFourth_1);
        registerAnimatedPart(LegRightFourth_2);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-3.0F, 0.0F, 0.0F, 6, 1, 7),
                PartPose.offset(0.0F, 21.0F, -0.5F));

        PartDefinition BodyBottom = Body.addOrReplaceChild("BodyBottom", CubeListBuilder.create()
                .texOffs(0, 15).addBox(-2.5F, 0.0F, 0.0F, 5, 1, 6),
                PartPose.offset(0.0F, 0.5F, 0.5F));

        PartDefinition BodyTop = BodyBottom.addOrReplaceChild("BodyTop", CubeListBuilder.create()
                .texOffs(0, 8).addBox(-2.5F, 0.0F, 0.0F, 5, 1, 6),
                PartPose.offset(0.0F, -1.1F, 0.0F));

        PartDefinition Hat = BodyTop.addOrReplaceChild("Hat", CubeListBuilder.create()
                .texOffs(43, 0).addBox(-2.0F, 0.0F, -2.0F, 4, 1, 4),
                PartPose.offset(0.0F, -0.4F, 2.2F));

        Hat.addOrReplaceChild("HatTop", CubeListBuilder.create()
                .texOffs(45, 6).addBox(-1.5F, 0.0F, -1.5F, 3, 4, 3),
                PartPose.offset(0.0F, -3.3F, 0.0F));

        // Arms
        PartDefinition ArmBackRight = Body.addOrReplaceChild("ArmBackRight", CubeListBuilder.create()
                .texOffs(25, 1).addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3),
                PartPose.offsetAndRotation(-2.3F, 0.5F, 0.5F, 0.0F, 0.6981317F, 0.0F));

        PartDefinition ArmFrontRight = ArmBackRight.addOrReplaceChild("ArmFrontRight", CubeListBuilder.create()
                .texOffs(26, 6).addBox(0.0F, -0.5F, -2.0F, 1, 1, 2),
                PartPose.offsetAndRotation(-0.5F, 0.01F, -3.0F, 0.0F, -0.7853982F, 0.0F));

        PartDefinition ScissorBigRight = ArmFrontRight.addOrReplaceChild("ScissorBigRight", CubeListBuilder.create()
                .texOffs(23, 10).addBox(-1.0F, -1.0F, -2.5F, 2, 2, 3),
                PartPose.offset(0.0F, 0.0F, -2.5F));

        ScissorBigRight.addOrReplaceChild("ScissorSmallRight", CubeListBuilder.create()
                .texOffs(26, 16).addBox(-0.5F, -0.5F, -2.0F, 1, 1, 2),
                PartPose.offsetAndRotation(0.8F, 0.0F, -0.1F, 0.0F, -0.3490659F, 0.0F));

        PartDefinition ArmBackLeft = Body.addOrReplaceChild("ArmBackLeft", CubeListBuilder.create()
                .texOffs(34, 1).addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3),
                PartPose.offsetAndRotation(2.3F, 0.5F, 0.5F, 0.0F, -0.6981317F, 0.0F));

        PartDefinition ArmFrontLeft = ArmBackLeft.addOrReplaceChild("ArmFrontLeft", CubeListBuilder.create()
                .texOffs(35, 6).addBox(-1.0F, -0.5F, -2.0F, 1, 1, 2),
                PartPose.offsetAndRotation(0.5F, 0.01F, -3.0F, 0.0F, 0.7853982F, 0.0F));

        PartDefinition ScissorBigLeft = ArmFrontLeft.addOrReplaceChild("ScissorBigLeft", CubeListBuilder.create()
                .texOffs(34, 10).addBox(-1.0F, -1.0F, -2.5F, 2, 2, 3),
                PartPose.offset(0.0F, 0.0F, -2.5F));

        ScissorBigLeft.addOrReplaceChild("ScissorSmallLeft", CubeListBuilder.create()
                .texOffs(35, 16).addBox(-0.5F, -0.5F, -2.0F, 1, 1, 2),
                PartPose.offsetAndRotation(-0.8F, 0.0F, -0.1F, 0.0F, 0.3490659F, 0.0F));

        // Left Legs
        PartDefinition LegLeftFirst_1 = Body.addOrReplaceChild("LegLeftFirst_1", CubeListBuilder.create()
                .texOffs(0, 23).addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1),
                PartPose.offsetAndRotation(2.2F, 1.0F, 0.8F, 0.0F, 0.0F, -2.0943951F));

        LegLeftFirst_1.addOrReplaceChild("LegLeftFirst_2", CubeListBuilder.create()
                .texOffs(0, 28).addBox(0.0F, 0.0F, -0.5F, 1, 4, 1),
                PartPose.offsetAndRotation(0.5F, 3.0F, 0.01F, 0.0F, 0.0F, 1.8325957F));

        PartDefinition LegLeftSecond_1 = Body.addOrReplaceChild("LegLeftSecond_1", CubeListBuilder.create()
                .texOffs(5, 23).addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1),
                PartPose.offsetAndRotation(2.2F, 1.0F, 2.6F, 0.0F, 0.0F, -2.0943951F));

        LegLeftSecond_1.addOrReplaceChild("LegLeftSecond_2", CubeListBuilder.create()
                .texOffs(5, 28).addBox(0.0F, 0.0F, -0.5F, 1, 4, 1),
                PartPose.offsetAndRotation(0.5F, 3.0F, 0.01F, 0.0F, 0.0F, 1.8325957F));

        PartDefinition LegLeftThird_1 = Body.addOrReplaceChild("LegLeftThird_1", CubeListBuilder.create()
                .texOffs(10, 23).addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1),
                PartPose.offsetAndRotation(2.2F, 1.0F, 4.4F, 0.0F, 0.0F, -2.0943951F));

        LegLeftThird_1.addOrReplaceChild("LegLeftThird_2", CubeListBuilder.create()
                .texOffs(10, 28).addBox(0.0F, 0.0F, -0.5F, 1, 4, 1),
                PartPose.offsetAndRotation(0.5F, 3.0F, 0.01F, 0.0F, 0.0F, 1.8325957F));

        PartDefinition LegLeftFourth_1 = Body.addOrReplaceChild("LegLeftFourth_1", CubeListBuilder.create()
                .texOffs(15, 23).addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1),
                PartPose.offsetAndRotation(2.2F, 1.0F, 6.2F, 0.0F, 0.0F, -2.0943951F));

        LegLeftFourth_1.addOrReplaceChild("LegLeftFourth_2", CubeListBuilder.create()
                .texOffs(15, 28).addBox(0.0F, 0.0F, -0.5F, 1, 4, 1),
                PartPose.offsetAndRotation(0.5F, 3.0F, 0.01F, 0.0F, 0.0F, 1.8325957F));

        // Right Legs
        PartDefinition LegRightFirst_1 = Body.addOrReplaceChild("LegRightFirst_1", CubeListBuilder.create()
                .texOffs(20, 23).addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1),
                PartPose.offsetAndRotation(-2.2F, 1.0F, 0.8F, 0.0F, 0.0F, 2.0943951F));

        LegRightFirst_1.addOrReplaceChild("LegRightFirst_2", CubeListBuilder.create()
                .texOffs(20, 28).addBox(-1.0F, 0.0F, -0.5F, 1, 4, 1),
                PartPose.offsetAndRotation(-0.5F, 3.0F, 0.01F, 0.0F, 0.0F, -1.8325957F));

        PartDefinition LegRightSecond_1 = Body.addOrReplaceChild("LegRightSecond_1", CubeListBuilder.create()
                .texOffs(25, 23).addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1),
                PartPose.offsetAndRotation(-2.2F, 1.0F, 2.6F, 0.0F, 0.0F, 2.0943951F));

        LegRightSecond_1.addOrReplaceChild("LegRightSecond_2", CubeListBuilder.create()
                .texOffs(25, 28).addBox(-1.0F, 0.0F, -0.5F, 1, 4, 1),
                PartPose.offsetAndRotation(-0.5F, 3.0F, 0.01F, 0.0F, 0.0F, -1.8325957F));

        PartDefinition LegRightThird_1 = Body.addOrReplaceChild("LegRightThird_1", CubeListBuilder.create()
                .texOffs(30, 23).addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1),
                PartPose.offsetAndRotation(-2.2F, 1.0F, 4.4F, 0.0F, 0.0F, 2.0943951F));

        LegRightThird_1.addOrReplaceChild("LegRightThird_2", CubeListBuilder.create()
                .texOffs(30, 28).addBox(-1.0F, 0.0F, -0.5F, 1, 4, 1),
                PartPose.offsetAndRotation(-0.5F, 3.0F, 0.01F, 0.0F, 0.0F, -1.8325957F));

        PartDefinition LegRightFourth_1 = Body.addOrReplaceChild("LegRightFourth_1", CubeListBuilder.create()
                .texOffs(35, 23).addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1),
                PartPose.offsetAndRotation(-2.2F, 1.0F, 6.2F, 0.0F, 0.0F, 2.0943951F));

        LegRightFourth_1.addOrReplaceChild("LegRightFourth_2", CubeListBuilder.create()
                .texOffs(35, 28).addBox(-1.0F, 0.0F, -0.5F, 1, 4, 1),
                PartPose.offsetAndRotation(-0.5F, 3.0F, 0.01F, 0.0F, 0.0F, -1.8325957F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        // Hat visibility
        this.Hat.visible = entity.getName().getString().equals("fredrick");

        if (entity.isEntityMoving()) {
            // Walk Animation
            float globalSpeed = 2.0F;
            float globalHeight = 1.0F;
            float globalDegree = 1.0F;

            revertToDefaultBoxValues();

            bounce(Body, 2.0F * globalSpeed, 0.3F * globalHeight, false, limbSwing, limbSwingAmount);
            swing(Body, 2.0F * globalSpeed, 0.05F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);

            // Legs animation
            animateLegs(globalSpeed, globalDegree, limbSwing, limbSwingAmount);

            // Claws animation
            swing(ArmBackLeft, 2.0F * globalSpeed, 0.05F * globalDegree, true, 0.2F, 0.0F, limbSwing, limbSwingAmount);
            swing(ArmBackRight, 2.0F * globalSpeed, 0.05F * globalDegree, true, 0.2F, 0.0F, limbSwing, limbSwingAmount);
            swing(ArmFrontLeft, 2.0F * globalSpeed, 0.05F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
            swing(ArmFrontRight, 2.0F * globalSpeed, 0.05F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
            shake(ArmFrontLeft, 2.0F * globalSpeed, 0.03F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            shake(ArmFrontRight, 2.0F * globalSpeed, 0.03F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            shake(ScissorSmallLeft, 1.2F * globalSpeed, 0.2F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            shake(ScissorSmallRight, 1.2F * globalSpeed, 0.2F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        } else if (entity.isPartying()) {
            // Dance Animation
            animateDance(entity, ageInTicks);
        } else {
            // Idle Animation
            animateIdle(entity, ageInTicks);
        }
    }

    private void animateLegs(float globalSpeed, float globalDegree, float limbSwing, float limbSwingAmount) {
        // Left legs
        swing(LegLeftFirst_1, 1.0F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(LegLeftFirst_1, 1.0F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        flap(LegLeftFirst_1, 1.0F * globalSpeed, 0.2F * globalDegree, false, 2.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(LegLeftFirst_2, 1.0F * globalSpeed, 0.1F * globalDegree, false, 0.5F, 0.0F, limbSwing, limbSwingAmount);
        flap(LegLeftFirst_2, 1.0F * globalSpeed, 0.1F * globalDegree, false, 1.5F, 0.0F, limbSwing, limbSwingAmount);

        swing(LegLeftSecond_1, 1.0F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(LegLeftSecond_1, 1.0F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        flap(LegLeftSecond_1, 1.0F * globalSpeed, 0.2F * globalDegree, true, 2.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(LegLeftSecond_2, 1.0F * globalSpeed, 0.1F * globalDegree, true, 0.5F, 0.0F, limbSwing, limbSwingAmount);
        flap(LegLeftSecond_2, 1.0F * globalSpeed, 0.1F * globalDegree, true, 1.5F, 0.0F, limbSwing, limbSwingAmount);

        swing(LegLeftThird_1, 1.0F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(LegLeftThird_1, 1.0F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        flap(LegLeftThird_1, 1.0F * globalSpeed, 0.2F * globalDegree, false, 2.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(LegLeftThird_2, 1.0F * globalSpeed, 0.1F * globalDegree, false, 0.5F, 0.0F, limbSwing, limbSwingAmount);
        flap(LegLeftThird_2, 1.0F * globalSpeed, 0.1F * globalDegree, false, 1.5F, 0.0F, limbSwing, limbSwingAmount);

        swing(LegLeftFourth_1, 1.0F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(LegLeftFourth_1, 1.0F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        flap(LegLeftFourth_1, 1.0F * globalSpeed, 0.2F * globalDegree, true, 2.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(LegLeftFourth_2, 1.0F * globalSpeed, 0.1F * globalDegree, true, 0.5F, 0.0F, limbSwing, limbSwingAmount);
        flap(LegLeftFourth_2, 1.0F * globalSpeed, 0.1F * globalDegree, true, 1.5F, 0.0F, limbSwing, limbSwingAmount);

        // Right legs
        swing(LegRightFirst_1, 1.0F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(LegRightFirst_1, 1.0F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        flap(LegRightFirst_1, 1.0F * globalSpeed, 0.2F * globalDegree, false, 2.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(LegRightFirst_2, 1.0F * globalSpeed, 0.1F * globalDegree, false, 0.5F, 0.0F, limbSwing, limbSwingAmount);
        flap(LegRightFirst_2, 1.0F * globalSpeed, 0.1F * globalDegree, false, 1.5F, 0.0F, limbSwing, limbSwingAmount);

        swing(LegRightSecond_1, 1.0F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(LegRightSecond_1, 1.0F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        flap(LegRightSecond_1, 1.0F * globalSpeed, 0.2F * globalDegree, true, 2.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(LegRightSecond_2, 1.0F * globalSpeed, 0.1F * globalDegree, true, 0.5F, 0.0F, limbSwing, limbSwingAmount);
        flap(LegRightSecond_2, 1.0F * globalSpeed, 0.1F * globalDegree, true, 1.5F, 0.0F, limbSwing, limbSwingAmount);

        swing(LegRightThird_1, 1.0F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(LegRightThird_1, 1.0F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        flap(LegRightThird_1, 1.0F * globalSpeed, 0.2F * globalDegree, false, 2.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(LegRightThird_2, 1.0F * globalSpeed, 0.1F * globalDegree, false, 0.5F, 0.0F, limbSwing, limbSwingAmount);
        flap(LegRightThird_2, 1.0F * globalSpeed, 0.1F * globalDegree, false, 1.5F, 0.0F, limbSwing, limbSwingAmount);

        swing(LegRightFourth_1, 1.0F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(LegRightFourth_1, 1.0F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        flap(LegRightFourth_1, 1.0F * globalSpeed, 0.2F * globalDegree, true, 2.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(LegRightFourth_2, 1.0F * globalSpeed, 0.1F * globalDegree, true, 0.5F, 0.0F, limbSwing, limbSwingAmount);
        flap(LegRightFourth_2, 1.0F * globalSpeed, 0.1F * globalDegree, true, 1.5F, 0.0F, limbSwing, limbSwingAmount);
    }

    private void animateDance(T entity, float ageInTicks) {
        float globalSpeed = 0.6F;
        float globalHeight = 1.0F;
        float globalDegree = 1.0F;

        float limbSwing = entity.tickCount;
        float limbSwingAmount = 1;

        revertToDefaultBoxValues();

        this.Body.y -= 0.7F;
        this.Body.xRot += (float) Math.toRadians(-20);
        bounce(Body, 1.0F * globalSpeed, 0.8F * globalHeight, false, limbSwing, limbSwingAmount);
        swing(Body, 1.0F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(Body, 0.5F * globalSpeed, 0.3F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);

        this.ArmBackLeft.xRot += (float) Math.toRadians(-60);
        this.ArmBackLeft.zRot += (float) Math.toRadians(20);
        flap(ArmBackLeft, 0.5F * globalSpeed, 0.6F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(ArmFrontLeft, 0.5F * globalSpeed, 0.3F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        flap(ArmFrontLeft, 0.5F * globalSpeed, 0.5F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);

        this.ArmBackRight.xRot += (float) Math.toRadians(-60);
        this.ArmBackRight.zRot += (float) Math.toRadians(-20);
        flap(ArmBackRight, 0.5F * globalSpeed, 0.6F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(ArmFrontRight, 0.5F * globalSpeed, 0.3F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        flap(ArmFrontRight, 0.5F * globalSpeed, 0.5F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);

        shake(ScissorSmallLeft, 1.2F * globalSpeed, 0.4F * globalDegree, false, 2.0F, 0.1F, limbSwing, limbSwingAmount);
        shake(ScissorSmallRight, 1.2F * globalSpeed, 0.4F * globalDegree, false, 2.0F, -0.1F, limbSwing, limbSwingAmount);
    }

    private void animateIdle(T entity, float ageInTicks) {
        float globalSpeed = 0.4F;
        float globalHeight = 1.0F;
        float globalDegree = 1.0F;

        float limbSwing = entity.tickCount;
        float limbSwingAmount = 1;

        revertToDefaultBoxValues();

        bounce(Body, 1.0F * globalSpeed, 0.2F * globalHeight, false, limbSwing, limbSwingAmount);

        // Legs idle
        flap(LegLeftFirst_1, 1.0F * globalSpeed, 0.05F * globalDegree, false, 1.2F, 0.0F, limbSwing, limbSwingAmount);
        flap(LegLeftFirst_2, 1.0F * globalSpeed, 0.04F * globalDegree, true, 1.2F, 0.0F, limbSwing, limbSwingAmount);
        flap(LegRightFirst_1, 1.0F * globalSpeed, 0.05F * globalDegree, true, 1.2F, 0.0F, limbSwing, limbSwingAmount);
        flap(LegRightFirst_2, 1.0F * globalSpeed, 0.04F * globalDegree, false, 1.2F, 0.0F, limbSwing, limbSwingAmount);

        // Claws idle
        swing(ArmBackLeft, 1.0F * globalSpeed, 0.05F * globalDegree, true, 1.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(ArmBackRight, 1.0F * globalSpeed, 0.05F * globalDegree, true, 1.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(ArmFrontLeft, 1.0F * globalSpeed, 0.05F * globalDegree, false, 1.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(ArmFrontRight, 1.0F * globalSpeed, 0.05F * globalDegree, false, 1.2F, 0.0F, limbSwing, limbSwingAmount);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        this.Body.render(poseStack, buffer, packedLight, packedOverlay, color);
    }
}
