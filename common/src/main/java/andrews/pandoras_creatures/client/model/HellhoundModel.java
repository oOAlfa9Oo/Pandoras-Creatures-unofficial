package andrews.pandoras_creatures.client.model;

import andrews.pandoras_creatures.client.model.base.PCEntityModel;
import andrews.pandoras_creatures.client.renderer.state.HellhoundRenderState;
import andrews.pandoras_creatures.entities.hellhound.HellhoundChargeState;
import andrews.pandoras_creatures.entities.hellhound.HellhoundVisualRules;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class HellhoundModel<T extends HellhoundRenderState> extends PCEntityModel<T> {
    private final ModelPart body;
    private final ModelPart body2;
    private final ModelPart body3;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart mouth_bottom;
    private final ModelPart ear_left;
    private final ModelPart ear_right;
    private final ModelPart hip;
    private final ModelPart hip_left;
    private final ModelPart hip_right;
    private final ModelPart tail;
    private final ModelPart tail_1;
    private final ModelPart shoulder_left;
    private final ModelPart shoulder_right;
    private final ModelPart rotation_point_left_front;
    private final ModelPart rotation_point_right_front;
    private final ModelPart rotation_point_left_back;
    private final ModelPart rotation_point_right_back;
    private final ModelPart leg_left_front;
    private final ModelPart leg_left_front_2;
    private final ModelPart leg_left_front_3;
    private final ModelPart leg_right_front;
    private final ModelPart leg_right_front_2;
    private final ModelPart leg_right_front_3;
    private final ModelPart leg_left_back;
    private final ModelPart leg_left_back_2;
    private final ModelPart leg_left_back_3;
    private final ModelPart leg_right_back;
    private final ModelPart leg_right_back_2;
    private final ModelPart leg_right_back_3;

    public HellhoundModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.body2 = this.body.getChild("body2");
        this.body3 = this.body2.getChild("body3");
        this.neck = this.body.getChild("neck");
        this.head = this.neck.getChild("head");
        this.mouth_bottom = this.head.getChild("head_bottom").getChild("mouth_bottom");
        this.ear_left = this.head.getChild("ear_left");
        this.ear_right = this.head.getChild("ear_right");
        this.hip = this.body3.getChild("hip");
        this.hip_left = this.hip.getChild("hip_left");
        this.hip_right = this.hip.getChild("hip_right");
        this.tail = this.hip.getChild("tail");
        this.tail_1 = this.tail.getChild("tail_1");
        this.shoulder_left = this.body.getChild("shoulder_left");
        this.shoulder_right = this.body.getChild("shoulder_right");
        this.rotation_point_left_front = this.body.getChild("rotation_point_left_front");
        this.rotation_point_right_front = this.body.getChild("rotation_point_right_front");
        this.leg_left_front = this.rotation_point_left_front.getChild("leg_left_front");
        this.leg_left_front_2 = this.leg_left_front.getChild("leg_left_front_2");
        this.leg_left_front_3 = this.leg_left_front_2.getChild("leg_left_front_3");
        this.leg_right_front = this.rotation_point_right_front.getChild("leg_right_front");
        this.leg_right_front_2 = this.leg_right_front.getChild("leg_right_front_2");
        this.leg_right_front_3 = this.leg_right_front_2.getChild("leg_right_front_3");
        this.rotation_point_left_back = this.hip.getChild("rotation_point_left_back");
        this.rotation_point_right_back = this.hip.getChild("rotation_point_right_back");
        this.leg_left_back = this.rotation_point_left_back.getChild("leg_left_back");
        this.leg_left_back_2 = this.leg_left_back.getChild("leg_left_back_2");
        this.leg_left_back_3 = this.leg_left_back_2.getChild("leg_left_back_3");
        this.leg_right_back = this.rotation_point_right_back.getChild("leg_right_back");
        this.leg_right_back_2 = this.leg_right_back.getChild("leg_right_back_2");
        this.leg_right_back_3 = this.leg_right_back_2.getChild("leg_right_back_3");

        registerAnimatedParts();
    }

    private void registerAnimatedParts() {
        registerAnimatedPart(body);
        registerAnimatedPart(body2);
        registerAnimatedPart(body3);
        registerAnimatedPart(neck);
        registerAnimatedPart(head);
        registerAnimatedPart(mouth_bottom);
        registerAnimatedPart(ear_left);
        registerAnimatedPart(ear_right);
        registerAnimatedPart(hip);
        registerAnimatedPart(hip_left);
        registerAnimatedPart(hip_right);
        registerAnimatedPart(tail);
        registerAnimatedPart(tail_1);
        registerAnimatedPart(shoulder_left);
        registerAnimatedPart(shoulder_right);
        registerAnimatedPart(rotation_point_left_front);
        registerAnimatedPart(rotation_point_right_front);
        registerAnimatedPart(rotation_point_left_back);
        registerAnimatedPart(rotation_point_right_back);
        registerAnimatedPart(leg_left_front);
        registerAnimatedPart(leg_left_front_2);
        registerAnimatedPart(leg_left_front_3);
        registerAnimatedPart(leg_right_front);
        registerAnimatedPart(leg_right_front_2);
        registerAnimatedPart(leg_right_front_3);
        registerAnimatedPart(leg_left_back);
        registerAnimatedPart(leg_left_back_2);
        registerAnimatedPart(leg_left_back_3);
        registerAnimatedPart(leg_right_back);
        registerAnimatedPart(leg_right_back_2);
        registerAnimatedPart(leg_right_back_3);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(105, 0).addBox(-1.0F, 0.0F, -8.0F, 2, 2, 9),
                PartPose.offset(0.0F, 6.4F, 0.3F));

        // Spikes on body
        body.addOrReplaceChild("spike", CubeListBuilder.create()
                .texOffs(0, 0).addBox(0.0F, -1.0F, 0.0F, 1, 1, 1),
                PartPose.offset(-0.5F, 0.0F, -7.0F));
        body.addOrReplaceChild("spike_1", CubeListBuilder.create()
                .texOffs(5, 0).addBox(0.0F, -1.0F, 0.0F, 1, 1, 1),
                PartPose.offset(-0.5F, 0.0F, -5.0F));
        body.addOrReplaceChild("spike_2", CubeListBuilder.create()
                .texOffs(10, 0).addBox(0.0F, -1.0F, 0.0F, 1, 1, 1),
                PartPose.offset(-0.5F, 0.0F, -3.0F));
        body.addOrReplaceChild("spike_3", CubeListBuilder.create()
                .texOffs(15, 0).addBox(0.0F, -1.0F, 0.0F, 1, 1, 1),
                PartPose.offset(-0.5F, 0.0F, -1.0F));

        // Ribs (simplified - keeping main structure)
        PartDefinition rib_1 = body.addOrReplaceChild("rib_1", CubeListBuilder.create()
                .texOffs(37, 59).addBox(-1.0F, 0.0F, 0.0F, 1, 3, 1),
                PartPose.offsetAndRotation(1.0F, 0.2F, -7.0F, 0.0F, 0.0F, -1.1344640137963142F));
        PartDefinition rib_1_1 = body.addOrReplaceChild("rib_1_1", CubeListBuilder.create()
                .texOffs(57, 59).addBox(0.0F, 0.0F, 0.0F, 1, 3, 1),
                PartPose.offsetAndRotation(-1.0F, 0.2F, -7.0F, 0.0F, 0.0F, 1.1344640137963142F));

        // Body2
        PartDefinition body2 = body.addOrReplaceChild("body2", CubeListBuilder.create()
                .texOffs(103, 0).addBox(-0.5F, 0.0F, 0.0F, 1, 2, 4),
                PartPose.offsetAndRotation(0.0F, 0.2F, 1.0F, -0.17453292519943295F, 0.0F, 0.0F));
        body2.addOrReplaceChild("spike_4", CubeListBuilder.create()
                .texOffs(20, 0).addBox(0.0F, -1.0F, 0.0F, 1, 1, 1),
                PartPose.offset(-0.5F, 0.0F, 1.0F));
        body2.addOrReplaceChild("spike_5", CubeListBuilder.create()
                .texOffs(25, 0).addBox(0.0F, -1.0F, 0.0F, 1, 1, 1),
                PartPose.offset(-0.5F, 0.0F, 3.0F));

        // Body3
        PartDefinition body3 = body2.addOrReplaceChild("body3", CubeListBuilder.create()
                .texOffs(90, 0).addBox(-0.5F, 0.0F, 0.0F, 1, 1, 5),
                PartPose.offsetAndRotation(0.0F, 0.0F, 4.0F, -0.17453292519943295F, 0.0F, 0.0F));
        body3.addOrReplaceChild("spike_6", CubeListBuilder.create()
                .texOffs(30, 0).addBox(0.0F, -1.0F, 0.0F, 1, 1, 1),
                PartPose.offset(-0.5F, 0.0F, 1.0F));
        body3.addOrReplaceChild("spike_7", CubeListBuilder.create()
                .texOffs(35, 0).addBox(0.0F, -1.0F, 3.0F, 1, 1, 1),
                PartPose.offset(-0.5F, 0.0F, 0.0F));
        body3.addOrReplaceChild("hip_front", CubeListBuilder.create()
                .texOffs(118, 0).addBox(-1.5F, -1.0F, 0.0F, 3, 1, 2),
                PartPose.offsetAndRotation(0.0F, 1.0F, 3.0F, -0.5235987755982988F, 0.0F, 0.0F));

        // Hip
        PartDefinition hip = body3.addOrReplaceChild("hip", CubeListBuilder.create()
                .texOffs(111, 12).addBox(-2.5F, 0.0F, 0.0F, 5, 3, 3),
                PartPose.offsetAndRotation(0.0F, -0.1F, 5.0F, -0.17453292519943295F, 0.0F, 0.0F));
        hip.addOrReplaceChild("spike_8", CubeListBuilder.create()
                .texOffs(40, 0).addBox(0.0F, -0.6F, 0.0F, 1, 1, 1),
                PartPose.offset(-0.5F, 0.0F, 0.0F));
        hip.addOrReplaceChild("spike_9", CubeListBuilder.create()
                .texOffs(45, 0).addBox(0.0F, 0.0F, 0.0F, 1, 1, 1),
                PartPose.offset(-0.5F, -0.3F, 2.0F));

        hip.addOrReplaceChild("hip_left", CubeListBuilder.create()
                .texOffs(111, 19).addBox(0.0F, 0.0F, -4.5F, 3, 1, 5),
                PartPose.offsetAndRotation(2.5F, 0.0F, 3.5F, -0.2617993877991494F, -0.2617993877991494F, 0.6981317007977318F));
        hip.addOrReplaceChild("hip_right", CubeListBuilder.create()
                .texOffs(111, 26).addBox(-3.0F, 0.0F, -4.5F, 3, 1, 5),
                PartPose.offsetAndRotation(-2.5F, 0.0F, 3.5F, -0.2617993877991494F, 0.2617993877991494F, -0.6981317007977318F));

        // Tail
        PartDefinition tail = hip.addOrReplaceChild("tail", CubeListBuilder.create()
                .texOffs(123, 33).addBox(-0.5F, 0.0F, -1.0F, 1, 3, 1),
                PartPose.offsetAndRotation(0.0F, 0.5F, 3.0F, 1.0471975511965976F, 0.0F, 0.0F));
        tail.addOrReplaceChild("tail_1", CubeListBuilder.create()
                .texOffs(118, 33).addBox(-0.5F, 0.0F, -1.0F, 1, 3, 1),
                PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, -0.2617993877991494F, 0.0F, 0.0F));

        // Back legs rotation points
        PartDefinition rotation_point_left_back = hip.addOrReplaceChild("rotation_point_left_back", CubeListBuilder.create()
                .texOffs(65, 0).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1),
                PartPose.offsetAndRotation(2.0F, 2.0F, 1.0F, 0.5235987755982988F, 0.0F, 0.0F));
        PartDefinition rotation_point_right_back = hip.addOrReplaceChild("rotation_point_right_back", CubeListBuilder.create()
                .texOffs(54, 0).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1),
                PartPose.offsetAndRotation(-2.0F, 2.0F, 1.0F, 0.5235987755982988F, 0.0F, 0.0F));

        // Left back leg
        PartDefinition leg_left_back = rotation_point_left_back.addOrReplaceChild("leg_left_back", CubeListBuilder.create()
                .texOffs(61, 3).addBox(0.0F, -0.5F, -1.0F, 2, 7, 2),
                PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, -0.3490658503988659F, 0.0F, -0.17453292519943295F));
        PartDefinition leg_left_back_2 = leg_left_back.addOrReplaceChild("leg_left_back_2", CubeListBuilder.create()
                .texOffs(61, 13).addBox(0.0F, 0.0F, 0.0F, 1, 6, 1),
                PartPose.offsetAndRotation(0.5F, 6.5F, -1.0F, 0.6108652381980153F, 0.0F, 0.17453292519943295F));
        leg_left_back_2.addOrReplaceChild("leg_left_back_dec", CubeListBuilder.create()
                .texOffs(66, 13).addBox(0.0F, -4.0F, 0.0F, 1, 4, 1),
                PartPose.offsetAndRotation(-0.01F, 4.0F, 0.0F, -0.2617993877991494F, 0.0F, 0.0F));
        PartDefinition leg_left_back_3 = leg_left_back_2.addOrReplaceChild("leg_left_back_3", CubeListBuilder.create()
                .texOffs(59, 21).addBox(-0.5F, 0.0F, -2.0F, 2, 1, 3),
                PartPose.offset(0.0F, 6.0F, 0.0F));
        leg_left_back_3.addOrReplaceChild("leg_left_back_toe", CubeListBuilder.create()
                .texOffs(59, 26).addBox(0.0F, -1.0F, -1.0F, 1, 1, 1),
                PartPose.offsetAndRotation(0.0F, 1.0F, -2.0F, -0.2617993877991494F, 0.0F, 0.0F));

        // Right back leg
        PartDefinition leg_right_back = rotation_point_right_back.addOrReplaceChild("leg_right_back", CubeListBuilder.create()
                .texOffs(50, 3).addBox(-2.0F, -0.5F, -1.0F, 2, 7, 2),
                PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, -0.3490658503988659F, 0.0F, 0.17453292519943295F));
        PartDefinition leg_right_back_2 = leg_right_back.addOrReplaceChild("leg_right_back_2", CubeListBuilder.create()
                .texOffs(50, 13).addBox(-1.0F, 0.0F, 0.0F, 1, 6, 1),
                PartPose.offsetAndRotation(-0.5F, 6.5F, -1.0F, 0.6108652381980153F, 0.0F, -0.17453292519943295F));
        leg_right_back_2.addOrReplaceChild("leg_right_back_dec", CubeListBuilder.create()
                .texOffs(55, 13).addBox(-1.0F, -4.0F, 0.0F, 1, 4, 1),
                PartPose.offsetAndRotation(0.01F, 4.0F, 0.0F, -0.2617993877991494F, 0.0F, 0.0F));
        PartDefinition leg_right_back_3 = leg_right_back_2.addOrReplaceChild("leg_right_back_3", CubeListBuilder.create()
                .texOffs(48, 21).addBox(-1.5F, 0.0F, -2.0F, 2, 1, 3),
                PartPose.offset(0.0F, 6.0F, 0.0F));
        leg_right_back_3.addOrReplaceChild("leg_right_back_toe", CubeListBuilder.create()
                .texOffs(48, 26).addBox(0.0F, -1.0F, -1.0F, 1, 1, 1),
                PartPose.offsetAndRotation(-1.0F, 1.0F, -2.0F, -0.2617993877991494F, 0.0F, 0.0F));

        // Neck and head
        PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create()
                .texOffs(109, 33).addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3),
                PartPose.offsetAndRotation(0.0F, 1.0F, -8.0F, -0.3490658503988659F, 0.0F, 0.0F));

        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(1, 57).addBox(-2.5F, -2.0F, -2.0F, 5, 3, 4),
                PartPose.offsetAndRotation(0.0F, -0.5F, -4.0F, 0.3490658503988659F, 0.0F, 0.0F));
        head.addOrReplaceChild("head_top", CubeListBuilder.create()
                .texOffs(1, 45).addBox(0.0F, 0.0F, 0.0F, 3, 1, 3),
                PartPose.offset(-1.5F, -3.0F, -1.5F));
        head.addOrReplaceChild("ear_left", CubeListBuilder.create()
                .texOffs(8, 41).addBox(-1.0F, -2.0F, -1.0F, 2, 2, 1),
                PartPose.offsetAndRotation(2.0F, -2.0F, 1.0F, 0.17453292519943295F, 0.08726646259971647F, 0.6981317007977318F));
        head.addOrReplaceChild("ear_right", CubeListBuilder.create()
                .texOffs(1, 41).addBox(-1.0F, -2.0F, -1.0F, 2, 2, 1),
                PartPose.offsetAndRotation(-2.0F, -2.0F, 1.0F, 0.17453292519943295F, -0.08726646259971647F, -0.6981317007977318F));

        PartDefinition head_bottom = head.addOrReplaceChild("head_bottom", CubeListBuilder.create()
                .texOffs(1, 50).addBox(0.0F, 0.0F, 0.0F, 4, 2, 4),
                PartPose.offsetAndRotation(-2.0F, 1.0F, -2.0F, 0.2617993877991494F, 0.0F, 0.0F));
        head_bottom.addOrReplaceChild("mouth_bottom", CubeListBuilder.create()
                .texOffs(20, 57).addBox(0.0F, 0.0F, -4.0F, 3, 1, 5),
                PartPose.offset(0.5F, 0.9F, 0.0F));

        PartDefinition mouth_top = head.addOrReplaceChild("mouth_top", CubeListBuilder.create()
                .texOffs(18, 51).addBox(0.0F, 0.0F, 0.0F, 3, 1, 4),
                PartPose.offset(-1.5F, 0.2F, -6.0F));
        mouth_top.addOrReplaceChild("mouth_top_2", CubeListBuilder.create()
                .texOffs(14, 44).addBox(0.0F, 0.0F, 0.0F, 2, 1, 5),
                PartPose.offsetAndRotation(0.5F, 0.0F, 0.5F, 0.08726646259971647F, 0.0F, 0.0F));
        // Teeth
        mouth_top.addOrReplaceChild("tooth", CubeListBuilder.create()
                .texOffs(6, 26).addBox(0.0F, 0.0F, 0.0F, 1, 1, 1),
                PartPose.offset(0.1F, 0.5F, 0.1F));
        mouth_top.addOrReplaceChild("tooth_1", CubeListBuilder.create()
                .texOffs(11, 26).addBox(0.0F, 0.0F, 0.0F, 1, 1, 1),
                PartPose.offset(1.9F, 0.5F, 0.1F));
        mouth_top.addOrReplaceChild("tooth_2", CubeListBuilder.create()
                .texOffs(1, 26).addBox(0.0F, 0.0F, 0.0F, 1, 1, 1),
                PartPose.offset(1.0F, 0.5F, 0.3F));
        mouth_top.addOrReplaceChild("tooth_3", CubeListBuilder.create()
                .texOffs(1, 35).addBox(0.0F, 0.0F, 0.0F, 1, 1, 4),
                PartPose.offset(0.2F, 0.5F, 1.0F));
        mouth_top.addOrReplaceChild("tooth_4", CubeListBuilder.create()
                .texOffs(1, 29).addBox(0.0F, 0.0F, 0.0F, 1, 1, 4),
                PartPose.offset(1.8F, 0.5F, 1.0F));

        // Shoulders
        body.addOrReplaceChild("shoulder_left", CubeListBuilder.create()
                .texOffs(109, 57).addBox(0.0F, -1.0F, 0.0F, 4, 1, 5),
                PartPose.offsetAndRotation(3.5F, 2.0F, -5.5F, 0.0F, 0.17453292519943295F, 1.0471975511965976F));
        body.addOrReplaceChild("shoulder_right", CubeListBuilder.create()
                .texOffs(109, 50).addBox(-4.0F, -1.0F, 0.0F, 4, 1, 5),
                PartPose.offsetAndRotation(-3.5F, 2.0F, -5.5F, 0.0F, -0.17453292519943295F, -1.0471975511965976F));

        // Front legs rotation points
        PartDefinition rotation_point_left_front = body.addOrReplaceChild("rotation_point_left_front", CubeListBuilder.create()
                .texOffs(85, 0).addBox(1.0F, -0.5F, -0.5F, 1, 1, 1),
                PartPose.offset(3.0F, 5.0F, -4.0F));
        PartDefinition rotation_point_right_front = body.addOrReplaceChild("rotation_point_right_front", CubeListBuilder.create()
                .texOffs(75, 0).addBox(-1.5F, -0.5F, -0.5F, 1, 1, 1),
                PartPose.offset(-3.0F, 5.0F, -4.0F));

        // Left front leg
        PartDefinition leg_left_front = rotation_point_left_front.addOrReplaceChild("leg_left_front", CubeListBuilder.create()
                .texOffs(81, 3).addBox(0.0F, -0.5F, -1.0F, 2, 7, 2),
                PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, 0.2617993877991494F, 0.0F, 0.0F));
        PartDefinition leg_left_front_2 = leg_left_front.addOrReplaceChild("leg_left_front_2", CubeListBuilder.create()
                .texOffs(81, 13).addBox(0.0F, 0.0F, 0.0F, 1, 6, 1),
                PartPose.offsetAndRotation(0.5F, 5.5F, -0.5F, -0.6108652381980153F, 0.0F, 0.0F));
        leg_left_front_2.addOrReplaceChild("leg_left_front_dec", CubeListBuilder.create()
                .texOffs(86, 13).addBox(0.0F, -4.0F, 0.0F, 1, 4, 1),
                PartPose.offsetAndRotation(-0.01F, 4.0F, 0.0F, -0.2617993877991494F, 0.0F, 0.0F));
        PartDefinition leg_left_front_3 = leg_left_front_2.addOrReplaceChild("leg_left_front_3", CubeListBuilder.create()
                .texOffs(81, 21).addBox(-0.5F, 0.0F, -2.0F, 2, 1, 3),
                PartPose.offsetAndRotation(0.0F, 6.0F, 0.0F, 0.6108652381980153F, 0.0F, 0.0F));
        leg_left_front_3.addOrReplaceChild("leg_left_front_toe", CubeListBuilder.create()
                .texOffs(81, 26).addBox(0.0F, -1.0F, -1.0F, 1, 1, 1),
                PartPose.offsetAndRotation(0.0F, 1.0F, -2.0F, -0.2617993877991494F, 0.0F, 0.0F));

        // Right front leg
        PartDefinition leg_right_front = rotation_point_right_front.addOrReplaceChild("leg_right_front", CubeListBuilder.create()
                .texOffs(71, 3).addBox(-2.0F, -0.5F, -1.0F, 2, 7, 2),
                PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.2617993877991494F, 0.0F, 0.0F));
        PartDefinition leg_right_front_2 = leg_right_front.addOrReplaceChild("leg_right_front_2", CubeListBuilder.create()
                .texOffs(71, 13).addBox(-1.0F, 0.0F, 0.0F, 1, 6, 1),
                PartPose.offsetAndRotation(-0.5F, 5.5F, -1.0F, -0.6108652381980153F, 0.0F, 0.0F));
        leg_right_front_2.addOrReplaceChild("leg_right_front_dec", CubeListBuilder.create()
                .texOffs(76, 13).addBox(-1.0F, -4.0F, 0.0F, 1, 4, 1),
                PartPose.offsetAndRotation(0.01F, 4.0F, 0.0F, -0.2617993877991494F, 0.0F, 0.0F));
        PartDefinition leg_right_front_3 = leg_right_front_2.addOrReplaceChild("leg_right_front_3", CubeListBuilder.create()
                .texOffs(70, 21).addBox(-1.5F, 0.0F, -2.0F, 2, 1, 3),
                PartPose.offsetAndRotation(0.0F, 6.0F, 0.0F, 0.6108652381980153F, 0.0F, 0.0F));
        leg_right_front_3.addOrReplaceChild("leg_right_front_toe", CubeListBuilder.create()
                .texOffs(70, 26).addBox(0.0F, -1.0F, -1.0F, 1, 1, 1),
                PartPose.offsetAndRotation(-1.0F, 1.0F, -2.0F, -0.2617993877991494F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 64);
    }

    @Override
    public void setupAnim(T state) {
        super.setupAnim(state);

        // ModelPart.x/y/z estan en "pixeles" (se dividen por 16 en translateAndRotate), mientras
        // que renderYOffset()/renderScale() estaban pensados para PoseStack.translate/scale
        // directo -- se multiplica por 16 para preservar la magnitud real (ver SeahorseModel).
        float hellhoundScale = HellhoundVisualRules.renderScale(state.hellhoundType);
        float yOffset = HellhoundVisualRules.renderYOffset(state.hellhoundType);
        if (hellhoundScale != 1.0F || yOffset != 0.0F) {
            this.root().xScale = hellhoundScale;
            this.root().yScale = hellhoundScale;
            this.root().zScale = hellhoundScale;
            this.root().y += yOffset * 16.0F;
        }

        float limbSwing = state.walkAnimationPos;
        float limbSwingAmount = state.walkAnimationSpeed;
        float netHeadYaw = state.yRot;
        float headPitch = state.xRot;

        if (state.isEntityMoving) {
            if (HellhoundChargeState.isCharging(state.isCharging)) {
                animateCharging(state, limbSwing, limbSwingAmount, netHeadYaw, headPitch);
            } else {
                animateWalking(state, limbSwing, limbSwingAmount, netHeadYaw);
            }
        } else {
            animateIdle(state, netHeadYaw, headPitch);
        }
    }

    private void animateCharging(T entity, float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch) {
        float globalHeight = 1;
        float globalSpeed = 0.7F;
        float globalDegree = 1;

        revertToDefaultBoxValues();

        this.neck.yRot = (netHeadYaw * ((float) Math.PI / 180) / 2);
        this.head.yRot = (netHeadYaw * ((float) Math.PI / 180) / 2);
        this.head.zRot = (netHeadYaw * ((float) Math.PI / 180) / 4);
        this.neck.xRot = (headPitch * ((float) Math.PI / 180F) / 2);
        this.head.xRot = (headPitch * ((float) Math.PI / 180F) / 2);

        bounce(body, 1F * globalSpeed, 0.7F * globalHeight, false, limbSwing, limbSwingAmount);
        swing(body, 1F * globalSpeed, 0.15F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(body2, 1F * globalSpeed, 0.08F * globalDegree, true, 0.0F, 0.02F, limbSwing, limbSwingAmount);
        swing(body3, 1F * globalSpeed, 0.08F * globalDegree, true, 0.0F, 0.02F, limbSwing, limbSwingAmount);
        swing(hip, 1F * globalSpeed, 0.08F * globalDegree, false, 0.0F, 0.02F, limbSwing, limbSwingAmount);

        swing(tail, 1F * globalSpeed, 0.2F * globalDegree, false, 1.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_1, 1F * globalSpeed, 0.5F * globalDegree, false, 1.0F, 0.0F, limbSwing, limbSwingAmount);

        swing(leg_left_front, 1F * globalSpeed, 1F * globalDegree, false, 2.0F, -0.1F, limbSwing, limbSwingAmount);
        swing(leg_left_front_2, 1F * globalSpeed, 1F * globalDegree, false, -2.0F, -0.5F, limbSwing, limbSwingAmount);
        swing(leg_left_front_3, 1F * globalSpeed, 0.6F * globalDegree, false, -1.5F, 0.0F, limbSwing, limbSwingAmount);

        swing(leg_right_front, 1F * globalSpeed, 1F * globalDegree, false, 2.5F, -0.1F, limbSwing, limbSwingAmount);
        swing(leg_right_front_2, 1F * globalSpeed, 1F * globalDegree, false, -1.5F, -0.5F, limbSwing, limbSwingAmount);
        swing(leg_right_front_3, 1F * globalSpeed, 0.6F * globalDegree, false, -1.0F, 0.0F, limbSwing, limbSwingAmount);

        swing(leg_left_back, 1F * globalSpeed, 0.8F * globalDegree, false, -0.5F, 0.0F, limbSwing, limbSwingAmount);
        swing(leg_left_back_2, 1F * globalSpeed, 0.8F * globalDegree, false, -1.9F, 0.6F, limbSwing, limbSwingAmount);
        swing(leg_left_back_3, 1F * globalSpeed, 0.6F * globalDegree, false, -0.5F, 0.1F, limbSwing, limbSwingAmount);

        swing(leg_right_back, 1F * globalSpeed, 0.8F * globalDegree, false, -1.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leg_right_back_2, 1F * globalSpeed, 0.8F * globalDegree, false, -2.4F, 0.6F, limbSwing, limbSwingAmount);
        swing(leg_right_back_3, 1F * globalSpeed, 0.6F * globalDegree, false, -1.0F, 0.1F, limbSwing, limbSwingAmount);

        flap(shoulder_left, 1F * globalSpeed, 0.2F * globalDegree, false, 0.0F, -0.1F, limbSwing, limbSwingAmount);
        flap(shoulder_right, 1F * globalSpeed, 0.2F * globalDegree, true, 0.0F, -0.1F, limbSwing, limbSwingAmount);
        shake(shoulder_left, 1F * globalSpeed, 0.2F * globalDegree, true, 1.2F, 0.0F, limbSwing, limbSwingAmount);
        shake(shoulder_right, 1F * globalSpeed, 0.2F * globalDegree, false, 1.2F, 0.0F, limbSwing, limbSwingAmount);

        flap(hip_left, 1F * globalSpeed, 0.2F * globalDegree, false, 2.0F, -0.1F, limbSwing, limbSwingAmount);
        flap(hip_right, 1F * globalSpeed, 0.2F * globalDegree, true, 2.0F, 0.1F, limbSwing, limbSwingAmount);

        swing(neck, 1F * globalSpeed, 0.4F * globalDegree, true, 0.0F, 0.1F, limbSwing, limbSwingAmount);
        swing(head, 1F * globalSpeed, 0.4F * globalDegree, false, 0.2F, -0.1F, limbSwing, limbSwingAmount);
        swing(mouth_bottom, 1F * globalSpeed, 0.4F * globalDegree, true, 1.4F, 0.15F, limbSwing, limbSwingAmount);

        swing(ear_left, 1F * globalSpeed, 0.2F * globalDegree, false, 2.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(ear_right, 1F * globalSpeed, 0.2F * globalDegree, false, 2.0F, 0.0F, limbSwing, limbSwingAmount);
    }

    private void animateWalking(T entity, float limbSwing, float limbSwingAmount, float netHeadYaw) {
        float globalHeight = 1;
        float globalSpeed = 2.0F;
        float globalDegree = 1;

        revertToDefaultBoxValues();

        this.neck.yRot = (netHeadYaw * ((float) Math.PI / 180)) / 2;
        this.head.yRot = (netHeadYaw * ((float) Math.PI / 180)) / 4;
        this.head.zRot = (netHeadYaw * ((float) Math.PI / 180)) / 4;

        bounce(body, 0.6F * globalSpeed, 0.8F * globalHeight, false, limbSwing, limbSwingAmount);

        swing(rotation_point_left_front, 0.3F * globalSpeed, 0.6F * globalDegree, false, -0.6F, 0.0F, limbSwing, limbSwingAmount);
        swing(leg_left_front_2, 0.3F * globalSpeed, 1.0F * globalDegree, false, 1.5F, -0.1F, limbSwing, limbSwingAmount);
        swing(leg_left_front_3, 0.3F * globalSpeed, 0.8F * globalDegree, false, 1.0F, 0.0F, limbSwing, limbSwingAmount);

        swing(rotation_point_right_front, 0.3F * globalSpeed, 0.6F * globalDegree, true, -0.6F, 0.0F, limbSwing, limbSwingAmount);
        swing(leg_right_front_2, 0.3F * globalSpeed, 1.0F * globalDegree, true, 1.5F, -0.1F, limbSwing, limbSwingAmount);
        swing(leg_right_front_3, 0.3F * globalSpeed, 0.8F * globalDegree, true, 1.0F, 0.0F, limbSwing, limbSwingAmount);

        swing(rotation_point_left_back, 0.3F * globalSpeed, 0.6F * globalDegree, true, 0.6F, 0.3F, limbSwing, limbSwingAmount);
        swing(leg_left_back_2, 0.3F * globalSpeed, 1.0F * globalDegree, true, -1.5F, 0.2F, limbSwing, limbSwingAmount);
        swing(leg_left_back_3, 0.3F * globalSpeed, 1.0F * globalDegree, false, -1.6F, -0.3F, limbSwing, limbSwingAmount);

        swing(rotation_point_right_back, 0.3F * globalSpeed, 0.6F * globalDegree, false, 0.6F, 0.3F, limbSwing, limbSwingAmount);
        swing(leg_right_back_2, 0.3F * globalSpeed, 1.0F * globalDegree, false, -1.5F, 0.2F, limbSwing, limbSwingAmount);
        swing(leg_right_back_3, 0.3F * globalSpeed, 1.0F * globalDegree, true, -1.6F, -0.3F, limbSwing, limbSwingAmount);

        swing(neck, 0.6F * globalSpeed, 0.1F * globalDegree, false, 1.5F, 0.0F, limbSwing, limbSwingAmount);
        swing(head, 0.6F * globalSpeed, 0.1F * globalDegree, false, -2.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(mouth_bottom, 0.6F * globalSpeed, 0.1F * globalDegree, false, 2.0F, -0.2F, limbSwing, limbSwingAmount);
        swing(ear_left, 0.6F * globalSpeed, 0.1F * globalDegree, false, 2.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(ear_right, 0.6F * globalSpeed, 0.1F * globalDegree, false, 2.2F, 0.0F, limbSwing, limbSwingAmount);

        flap(shoulder_left, 0.6F * globalSpeed, 0.1F * globalDegree, false, 1.0F, 0.0F, limbSwing, limbSwingAmount);
        flap(shoulder_right, 0.6F * globalSpeed, 0.1F * globalDegree, true, 1.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(shoulder_left, 0.3F * globalSpeed, 0.2F * globalDegree, true, 0.3F, 0.0F, limbSwing, limbSwingAmount);
        shake(shoulder_right, 0.3F * globalSpeed, 0.2F * globalDegree, false, 0.3F, 0.0F, limbSwing, limbSwingAmount);

        flap(hip_left, 0.6F * globalSpeed, 0.1F * globalDegree, false, 1.0F, 0.0F, limbSwing, limbSwingAmount);
        flap(hip_right, 0.6F * globalSpeed, 0.1F * globalDegree, true, 1.0F, 0.0F, limbSwing, limbSwingAmount);

        swing(body, 0.6F * globalSpeed, 0.02F * globalDegree, true, 1.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(body2, 0.6F * globalSpeed, 0.05F * globalDegree, false, 2.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(body3, 0.6F * globalSpeed, 0.05F * globalDegree, false, 2.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(hip, 0.6F * globalSpeed, 0.05F * globalDegree, true, 2.0F, 0.0F, limbSwing, limbSwingAmount);

        swing(tail, 0.6F * globalSpeed, 0.2F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_1, 0.6F * globalSpeed, 0.2F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
    }

    private void animateIdle(T entity, float netHeadYaw, float headPitch) {
        float globalHeight = 1;
        float globalSpeed = 0.5F;
        float globalDegree = 1;

        float limbSwing = entity.tickCount;
        float limbSwingAmount = 1;

        revertToDefaultBoxValues();

        this.neck.yRot = (netHeadYaw * ((float) Math.PI / 180) / 2);
        this.head.yRot = (netHeadYaw * ((float) Math.PI / 180) / 2);
        this.head.zRot = (netHeadYaw * ((float) Math.PI / 180) / 4);
        this.neck.xRot = (headPitch * ((float) Math.PI / 180F) / 2);
        this.head.xRot = (headPitch * ((float) Math.PI / 180F) / 2);

        bounce(body, 0.4F * globalSpeed, 0.3F * globalHeight, false, limbSwing, limbSwingAmount);

        swing(rotation_point_left_front, 0.4F * globalSpeed, 0.05F * globalDegree, false, -2.2F, 0.1F, limbSwing, limbSwingAmount);
        swing(rotation_point_right_front, 0.4F * globalSpeed, 0.05F * globalDegree, false, -2.2F, 0.1F, limbSwing, limbSwingAmount);
        swing(leg_left_front_2, 0.4F * globalSpeed, 0.05F * globalDegree, true, -2.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(leg_right_front_2, 0.4F * globalSpeed, 0.05F * globalDegree, true, -2.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(leg_left_front_3, 0.4F * globalSpeed, 0.1F * globalDegree, true, -2.2F, 0.1F, limbSwing, limbSwingAmount);
        swing(leg_right_front_3, 0.4F * globalSpeed, 0.1F * globalDegree, true, -2.2F, 0.1F, limbSwing, limbSwingAmount);

        swing(rotation_point_left_back, 0.4F * globalSpeed, 0.05F * globalDegree, false, -2.2F, 0.1F, limbSwing, limbSwingAmount);
        swing(rotation_point_right_back, 0.4F * globalSpeed, 0.05F * globalDegree, false, -2.2F, 0.1F, limbSwing, limbSwingAmount);
        swing(leg_left_back_2, 0.4F * globalSpeed, 0.05F * globalDegree, true, -2.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(leg_right_back_2, 0.4F * globalSpeed, 0.05F * globalDegree, true, -2.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(leg_left_back_3, 0.4F * globalSpeed, 0.1F * globalDegree, true, -2.2F, 0.1F, limbSwing, limbSwingAmount);
        swing(leg_right_back_3, 0.4F * globalSpeed, 0.1F * globalDegree, true, -2.2F, 0.1F, limbSwing, limbSwingAmount);

        swing(body, 0.4F * globalSpeed, 0.01F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(body2, 0.4F * globalSpeed, 0.01F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(body3, 0.4F * globalSpeed, 0.01F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(hip, 0.4F * globalSpeed, 0.01F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);

        swing(neck, 0.4F * globalSpeed, 0.1F * globalDegree, false, 1.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(head, 0.4F * globalSpeed, 0.05F * globalDegree, true, 1.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(mouth_bottom, 0.4F * globalSpeed, 0.15F * globalDegree, false, 1.0F, -0.1F, limbSwing, limbSwingAmount);
        swing(ear_left, 0.4F * globalSpeed, 0.1F * globalDegree, false, 1.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(ear_right, 0.4F * globalSpeed, 0.1F * globalDegree, false, 1.2F, 0.0F, limbSwing, limbSwingAmount);

        flap(shoulder_left, 0.4F * globalSpeed, 0.1F * globalDegree, false, 1.0F, 0.0F, limbSwing, limbSwingAmount);
        flap(shoulder_right, 0.4F * globalSpeed, 0.1F * globalDegree, true, 1.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(shoulder_left, 0.4F * globalSpeed, 0.05F * globalDegree, true, 0.3F, 0.0F, limbSwing, limbSwingAmount);
        shake(shoulder_right, 0.4F * globalSpeed, 0.05F * globalDegree, false, 0.3F, 0.0F, limbSwing, limbSwingAmount);

        flap(hip_left, 0.4F * globalSpeed, 0.1F * globalDegree, false, 1.0F, 0.0F, limbSwing, limbSwingAmount);
        flap(hip_right, 0.4F * globalSpeed, 0.1F * globalDegree, true, 1.0F, 0.0F, limbSwing, limbSwingAmount);

        swing(tail, 0.4F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_1, 0.4F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
    }

}
