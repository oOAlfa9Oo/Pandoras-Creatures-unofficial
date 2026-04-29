package andrews.pandoras_creatures.client.model;

import andrews.pandoras_creatures.client.model.base.PCEntityModel;
import andrews.pandoras_creatures.entities.SeahorseEntity;
import andrews.pandoras_creatures.entities.seahorse.SeahorseVisualRules;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class SeahorseModel<T extends SeahorseEntity> extends PCEntityModel<T> {
    private final ModelPart body;
    private final ModelPart back_bottom;
    private final ModelPart neck_front;
    private final ModelPart tail_1;
    private final ModelPart back_wing;
    private final ModelPart back_top;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart face_top;
    private final ModelPart ear_left;
    private final ModelPart ear_right;
    private final ModelPart horn;
    private final ModelPart face_top_1;
    private final ModelPart tail_2;
    private final ModelPart tail_front;
    private final ModelPart tail_left;
    private final ModelPart tail_right;
    private final ModelPart tail_3;
    private final ModelPart tail_4;
    private final ModelPart tail_5;
    private final ModelPart tail_6;
    private final ModelPart tail_7;
    private final ModelPart tail_8;

    public SeahorseModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.back_bottom = this.body.getChild("back_bottom");
        this.neck_front = this.body.getChild("neck_front");
        this.tail_1 = this.body.getChild("tail_1");
        this.back_wing = this.body.getChild("back_wing");
        this.back_top = this.back_bottom.getChild("back_top");
        this.neck = this.back_top.getChild("neck");
        this.head = this.neck.getChild("head");
        this.face_top = this.head.getChild("face_top");
        this.ear_left = this.head.getChild("ear_left");
        this.ear_right = this.head.getChild("ear_right");
        this.horn = this.head.getChild("horn");
        this.face_top_1 = this.face_top.getChild("face_top_1");
        this.tail_2 = this.tail_1.getChild("tail_2");
        this.tail_front = this.tail_1.getChild("tail_front");
        this.tail_left = this.tail_1.getChild("tail_left");
        this.tail_right = this.tail_1.getChild("tail_right");
        this.tail_3 = this.tail_2.getChild("tail_3");
        this.tail_4 = this.tail_3.getChild("tail_4");
        this.tail_5 = this.tail_4.getChild("tail_5");
        this.tail_6 = this.tail_5.getChild("tail_6");
        this.tail_7 = this.tail_6.getChild("tail_7");
        this.tail_8 = this.tail_7.getChild("tail_8");
        registerAnimatedParts();
    }

    private void registerAnimatedParts() {
        registerAnimatedPart(body);
        registerAnimatedPart(back_bottom);
        registerAnimatedPart(neck_front);
        registerAnimatedPart(tail_1);
        registerAnimatedPart(back_wing);
        registerAnimatedPart(back_top);
        registerAnimatedPart(neck);
        registerAnimatedPart(head);
        registerAnimatedPart(face_top);
        registerAnimatedPart(ear_left);
        registerAnimatedPart(ear_right);
        registerAnimatedPart(horn);
        registerAnimatedPart(face_top_1);
        registerAnimatedPart(tail_2);
        registerAnimatedPart(tail_front);
        registerAnimatedPart(tail_left);
        registerAnimatedPart(tail_right);
        registerAnimatedPart(tail_3);
        registerAnimatedPart(tail_4);
        registerAnimatedPart(tail_5);
        registerAnimatedPart(tail_6);
        registerAnimatedPart(tail_7);
        registerAnimatedPart(tail_8);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(56, 0).addBox(-1.0F, -1.5F, -1.0F, 2, 3, 2),
                PartPose.offsetAndRotation(0.0F, 18.3F, 0.0F, 0.17453292519943295F, 0.0F, 0.0F));
        PartDefinition back_bottom = body.addOrReplaceChild("back_bottom", CubeListBuilder.create()
                .texOffs(60, 6).addBox(-0.5F, -3.0F, -1.0F, 1, 3, 1),
                PartPose.offsetAndRotation(0.0F, 1.2F, 1.0F, -0.3141592653589793F, 0.0F, 0.0F));
        PartDefinition back_top = back_bottom.addOrReplaceChild("back_top", CubeListBuilder.create()
                .texOffs(55, 6).addBox(-0.5F, -3.0F, -1.0F, 1, 3, 1),
                PartPose.offsetAndRotation(0.01F, -3.0F, 0.0F, 0.4363323129985824F, 0.0F, 0.0F));
        PartDefinition neck = back_top.addOrReplaceChild("neck", CubeListBuilder.create()
                .texOffs(51, 0).addBox(-0.5F, 0.0F, -1.0F, 1, 3, 1),
                PartPose.offsetAndRotation(0.01F, -2.0F, -0.8F, -0.136659280431156F, 0.0F, 0.0F));
        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(42, 0).addBox(-1.0F, -2.0F, -1.0F, 2, 2, 2),
                PartPose.offsetAndRotation(0.0F, 0.5F, -0.5F, -0.08726646259971647F, 0.0F, 0.0F));
        PartDefinition face_top = head.addOrReplaceChild("face_top", CubeListBuilder.create()
                .texOffs(33, 0).addBox(-0.5F, 0.0F, -2.3F, 1, 1, 3),
                PartPose.offsetAndRotation(0.0F, -1.5F, -0.8F, 0.091106186954104F, 0.0F, 0.0F));
        face_top.addOrReplaceChild("face_top_1", CubeListBuilder.create()
                .texOffs(33, 5).addBox(-0.5F, 0.0F, -2.3F, 1, 1, 3),
                PartPose.offsetAndRotation(0.01F, 0.0F, 0.0F, 0.22689280275926282F, 0.0F, 0.0F));
        head.addOrReplaceChild("ear_left", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-1.0F, -1.0F, 0.0F, 1, 2, 1),
                PartPose.offsetAndRotation(1.0F, -1.2F, 0.4F, 0.08726646259971647F, 0.3141592653589793F, 0.0F));
        head.addOrReplaceChild("ear_right", CubeListBuilder.create()
                .texOffs(5, 0).addBox(-1.0F, -1.0F, 0.0F, 1, 2, 1),
                PartPose.offsetAndRotation(-1.0F, -1.2F, 0.4F, 0.08726646259971647F, -0.3141592653589793F, 0.0F));
        head.addOrReplaceChild("horn", CubeListBuilder.create()
                .texOffs(33, 10).addBox(-1.0F, -3.0F, 0.0F, 1, 3, 1),
                PartPose.offsetAndRotation(0.0F, -1.4F, -1.3F, 0.36425021489121656F, 0.0F, 0.0F));
        body.addOrReplaceChild("neck_front", CubeListBuilder.create()
                .texOffs(50, 5).addBox(-0.5F, -2.0F, 0.0F, 1, 2, 1),
                PartPose.offsetAndRotation(-0.01F, -1.5F, -1.0F, -0.5462880558742251F, 0.0F, 0.0F));
        PartDefinition tail_1 = body.addOrReplaceChild("tail_1", CubeListBuilder.create()
                .texOffs(35, 28).addBox(-0.5F, -1.0F, -1.0F, 1, 3, 1),
                PartPose.offsetAndRotation(0.0F, 1.7F, 1.0F, 0.17453292519943295F, 0.0F, 0.0F));
        tail_1.addOrReplaceChild("tail_front", CubeListBuilder.create()
                .texOffs(40, 29).addBox(-0.5F, -2.0F, 0.0F, 1, 2, 1),
                PartPose.offsetAndRotation(0.01F, 1.5F, -1.0F, 0.3141592653589793F, 0.0F, 0.0F));
        tail_1.addOrReplaceChild("tail_left", CubeListBuilder.create()
                .texOffs(45, 29).addBox(-1.0F, -2.0F, -1.0F, 1, 2, 1),
                PartPose.offsetAndRotation(0.5F, 1.5F, -0.01F, 0.0F, 0.0F, 0.22689280275926282F));
        tail_1.addOrReplaceChild("tail_right", CubeListBuilder.create()
                .texOffs(50, 29).addBox(0.0F, -2.0F, -1.0F, 1, 2, 1),
                PartPose.offsetAndRotation(-0.5F, 1.5F, -0.01F, 0.0F, 0.0F, -0.22689280275926282F));
        PartDefinition tail_2 = tail_1.addOrReplaceChild("tail_2", CubeListBuilder.create()
                .texOffs(30, 30).addBox(-0.5F, 0.0F, -1.0F, 1, 1, 1),
                PartPose.offsetAndRotation(0.01F, 2.0F, 0.0F, -0.3141592653589793F, 0.0F, 0.0F));
        PartDefinition tail_3 = tail_2.addOrReplaceChild("tail_3", CubeListBuilder.create()
                .texOffs(25, 30).addBox(-0.5F, 0.0F, -1.0F, 1, 1, 1),
                PartPose.offsetAndRotation(0.01F, 1.0F, 0.0F, -0.593411945678072F, 0.0F, 0.0F));
        PartDefinition tail_4 = tail_3.addOrReplaceChild("tail_4", CubeListBuilder.create()
                .texOffs(20, 30).addBox(-0.5F, 0.0F, -1.0F, 1, 1, 1),
                PartPose.offsetAndRotation(0.01F, 1.0F, 0.0F, -0.593411945678072F, 0.0F, 0.0F));
        PartDefinition tail_5 = tail_4.addOrReplaceChild("tail_5", CubeListBuilder.create()
                .texOffs(15, 30).addBox(-0.5F, 0.0F, -1.0F, 1, 1, 1),
                PartPose.offsetAndRotation(0.01F, 1.0F, 0.0F, -0.593411945678072F, 0.0F, 0.0F));
        PartDefinition tail_6 = tail_5.addOrReplaceChild("tail_6", CubeListBuilder.create()
                .texOffs(10, 30).addBox(-0.5F, 0.0F, -1.0F, 1, 1, 1),
                PartPose.offsetAndRotation(0.01F, 1.0F, 0.0F, -0.593411945678072F, 0.0F, 0.0F));
        PartDefinition tail_7 = tail_6.addOrReplaceChild("tail_7", CubeListBuilder.create()
                .texOffs(5, 30).addBox(-0.5F, 0.0F, -1.0F, 1, 1, 1),
                PartPose.offsetAndRotation(0.01F, 1.0F, 0.0F, -0.593411945678072F, 0.0F, 0.0F));
        tail_7.addOrReplaceChild("tail_8", CubeListBuilder.create()
                .texOffs(0, 30).addBox(-0.5F, 0.0F, -1.0F, 1, 1, 1),
                PartPose.offsetAndRotation(0.01F, 1.0F, 0.0F, -0.593411945678072F, 0.0F, 0.0F));
        body.addOrReplaceChild("back_wing", CubeListBuilder.create()
                .texOffs(55, 27).addBox(-1.0F, -1.5F, 0.0F, 1, 3, 2),
                PartPose.offset(0.0F, 1.5F, 1.0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.horn.visible = SeahorseVisualRules.isSpecialNamed(entity.getName().getString());
        if (!entity.isInWater()) {
            limbSwing = entity.tickCount;
            limbSwingAmount = 1;
            float globalSpeed = 0.5F;
            float globalDegree = 1.0F;
            revertToDefaultBoxValues();
            if (entity.isAlive()) {
                this.body.zRot = (float) Math.toRadians(90);
            }
            flap(body, 0.7F * globalSpeed, 0.3F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            shake(ear_left, 0.7F * globalSpeed, 0.2F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            shake(ear_right, 0.7F * globalSpeed, 0.2F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            swing(back_bottom, 0.7F * globalSpeed, 0.12F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            swing(back_top, 0.7F * globalSpeed, 0.12F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            swing(neck_front, 0.7F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            swing(head, 0.7F * globalSpeed, 0.3F * globalDegree, true, -0.2F, -0.1F, limbSwing, limbSwingAmount);
            flap(back_bottom, 0.7F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            flap(back_top, 0.7F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            animateTailOutOfWater(globalSpeed, globalDegree, limbSwing, limbSwingAmount);
        } else if (entity.isEntityMovingHorizontally()) {
            float globalSpeed = 5.0F;
            float globalDegree = 2.0F;
            revertToDefaultBoxValues();
            this.head.yRot = (netHeadYaw * ((float) Math.PI / 180) / 1.8F);
            swing(body, 0.3F * globalSpeed, 0.2F * globalDegree, false, 0.0F, 0.2F, limbSwing, limbSwingAmount);
            shake(ear_left, 0.3F * globalSpeed, 0.2F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            shake(ear_right, 0.3F * globalSpeed, 0.2F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            swing(face_top_1, 0.4F * globalSpeed, 0.05F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            swing(back_bottom, 0.3F * globalSpeed, 0.12F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            swing(back_top, 0.3F * globalSpeed, 0.12F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            swing(neck_front, 0.3F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            swing(head, 0.3F * globalSpeed, 0.3F * globalDegree, true, -0.2F, -0.1F, limbSwing, limbSwingAmount);
            swing(back_wing, 0.3F * globalSpeed, 0.2F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            animateTailSwimming(globalSpeed, globalDegree, limbSwing, limbSwingAmount);
        } else {
            limbSwing = entity.tickCount;
            limbSwingAmount = 1;
            float globalSpeed = 0.8F;
            float globalDegree = 1.0F;
            float globalHeight = 1.0F;
            revertToDefaultBoxValues();
            this.head.yRot = (netHeadYaw * ((float) Math.PI / 180) / 1.8F);
            bounce(body, 0.4F * globalSpeed, 0.4F * globalHeight, false, limbSwing, limbSwingAmount);
            shake(ear_left, 0.4F * globalSpeed, 0.2F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            shake(ear_right, 0.4F * globalSpeed, 0.2F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            swing(face_top_1, 0.4F * globalSpeed, 0.05F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            swing(back_bottom, 0.4F * globalSpeed, 0.08F * globalDegree, true, -1.0F, 0.0F, limbSwing, limbSwingAmount);
            swing(back_top, 0.4F * globalSpeed, 0.08F * globalDegree, false, -1.0F, 0.0F, limbSwing, limbSwingAmount);
            swing(neck_front, 0.4F * globalSpeed, 0.1F * globalDegree, true, -1.0F, 0.0F, limbSwing, limbSwingAmount);
            swing(body, 0.4F * globalSpeed, 0.03F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            swing(back_wing, 0.4F * globalSpeed, 0.05F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            animateTailIdle(globalSpeed, globalDegree, limbSwing, limbSwingAmount);
        }
    }

    private void animateTailOutOfWater(float globalSpeed, float globalDegree, float limbSwing, float limbSwingAmount) {
        swing(tail_1, 0.7F * globalSpeed, 0.2F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
        flap(tail_1, 0.7F * globalSpeed, 0.3F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_2, 0.7F * globalSpeed, 0.1F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
        flap(tail_2, 0.7F * globalSpeed, 0.2F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_3, 0.7F * globalSpeed, 0.1F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
        flap(tail_3, 0.7F * globalSpeed, 0.2F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_4, 0.7F * globalSpeed, 0.1F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
        flap(tail_4, 0.7F * globalSpeed, 0.2F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_5, 0.7F * globalSpeed, 0.05F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
        flap(tail_5, 0.7F * globalSpeed, 0.2F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_6, 0.7F * globalSpeed, 0.05F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
        flap(tail_6, 0.7F * globalSpeed, 0.1F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_7, 0.7F * globalSpeed, 0.05F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
        flap(tail_7, 0.7F * globalSpeed, 0.1F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_8, 0.7F * globalSpeed, 0.05F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
        flap(tail_8, 0.7F * globalSpeed, 0.1F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
    }

    private void animateTailSwimming(float globalSpeed, float globalDegree, float limbSwing, float limbSwingAmount) {
        swing(tail_1, 0.3F * globalSpeed, 0.2F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_2, 0.3F * globalSpeed, 0.1F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_3, 0.3F * globalSpeed, 0.1F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_4, 0.3F * globalSpeed, 0.1F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_5, 0.3F * globalSpeed, 0.05F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_6, 0.3F * globalSpeed, 0.05F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_7, 0.3F * globalSpeed, 0.05F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_8, 0.3F * globalSpeed, 0.05F * globalDegree, false, 0.2F, 0.0F, limbSwing, limbSwingAmount);
    }

    private void animateTailIdle(float globalSpeed, float globalDegree, float limbSwing, float limbSwingAmount) {
        swing(tail_1, 0.4F * globalSpeed, 0.1F * globalDegree, true, -0.6F, -0.05F, limbSwing, limbSwingAmount);
        swing(tail_2, 0.4F * globalSpeed, 0.1F * globalDegree, true, -0.6F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_3, 0.4F * globalSpeed, 0.05F * globalDegree, true, -0.6F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_4, 0.4F * globalSpeed, 0.05F * globalDegree, true, -0.6F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_5, 0.4F * globalSpeed, 0.05F * globalDegree, true, -0.6F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_6, 0.4F * globalSpeed, 0.05F * globalDegree, true, -0.6F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_7, 0.4F * globalSpeed, 0.05F * globalDegree, true, -0.6F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_8, 0.4F * globalSpeed, 0.05F * globalDegree, true, -0.6F, 0.0F, limbSwing, limbSwingAmount);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.pushPose();
        float size = SeahorseVisualRules.renderScale(entity.getSeahorseSize());
        poseStack.translate(0, SeahorseVisualRules.renderYOffset(entity.getSeahorseSize()), 0);
        if (!this.entity.isInWater()) {
            poseStack.translate(0, 0.3F, 0);
        }
        poseStack.scale(size, size, size);
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        poseStack.popPose();
    }
}
