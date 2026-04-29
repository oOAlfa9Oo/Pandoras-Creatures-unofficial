package andrews.pandoras_creatures.client.model;

import andrews.pandoras_creatures.client.model.base.PCEntityModel;
import andrews.pandoras_creatures.entities.EndTrollEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

/**
 * EndTrollModel - Complete model migrated from the original 1.16.5 Tabula source.
 */
public class EndTrollModel<T extends EndTrollEntity> extends PCEntityModel<T> {
    private final ModelPart movement_base;
    private final ModelPart bottom_body;
    private final ModelPart stomach;
    private final ModelPart leg_left_1;
    private final ModelPart leg_right_1;
    private final ModelPart torso;
    private final ModelPart body_abs;
    private final ModelPart right_eyelid_base;
    private final ModelPart left_eyelid_base;
    private final ModelPart eye;
    private final ModelPart eye_details_top;
    private final ModelPart eye_details_top_1;
    private final ModelPart eye_details_bottom;
    private final ModelPart eye_details_bottom_1;
    private final ModelPart neck_bottom_front;
    private final ModelPart yaw_right;
    private final ModelPart yaw_left;
    private final ModelPart mouth_cheeck_left;
    private final ModelPart back;
    private final ModelPart head_top;
    private final ModelPart mouth_cheeck_right;
    private final ModelPart meat_left;
    private final ModelPart meat_right;
    private final ModelPart shoulder_main_left;
    private final ModelPart shoulder_main_right;
    private final ModelPart arm_left_connection;
    private final ModelPart arm_right_connection;
    private final ModelPart shoulder_bone_left;
    private final ModelPart shoulder_bone_left_1;
    private final ModelPart right_eyelid_side;
    private final ModelPart right_eyelid_top;
    private final ModelPart right_eyelid_bottom;
    private final ModelPart right_eyelid_side_top;
    private final ModelPart right_eyelid_side_bottom;
    private final ModelPart left_eyelid_side;
    private final ModelPart left_eyelid_top;
    private final ModelPart left_eyelid_bottom;
    private final ModelPart left_eyelid_side_top;
    private final ModelPart left_eyelid_side_bottom;
    private final ModelPart mouth_bottom_front;
    private final ModelPart mouth_bottom_left;
    private final ModelPart mouth_bottom_right;
    private final ModelPart tooth;
    private final ModelPart tooth_1;
    private final ModelPart neck_bottom_left;
    private final ModelPart tooth_2;
    private final ModelPart neck_bottom_right;
    private final ModelPart tooth_3;
    private final ModelPart tooth_4;
    private final ModelPart mouth_top_lip;
    private final ModelPart head_back;
    private final ModelPart head_parts;
    private final ModelPart head_parts_1;
    private final ModelPart nose_main;
    private final ModelPart mouth_top_front;
    private final ModelPart mouth_top_left;
    private final ModelPart mouth_top_right;
    private final ModelPart tooth_5;
    private final ModelPart tooth_6;
    private final ModelPart mouth_top_lips_left;
    private final ModelPart mouth_top_left_back;
    private final ModelPart tooth_7;
    private final ModelPart mouth_skin_left;
    private final ModelPart shape62;
    private final ModelPart mouth_top_lips_right;
    private final ModelPart mouth_top_right_back;
    private final ModelPart tooth_8;
    private final ModelPart mouth_skin_right;
    private final ModelPart shape72;
    private final ModelPart head_left;
    private final ModelPart head_right;
    private final ModelPart head_back_cover;
    private final ModelPart head_parts_2;
    private final ModelPart head_parts_3;
    private final ModelPart head_parts_4;
    private final ModelPart head_parts_5;
    private final ModelPart nose_bottom;
    private final ModelPart nose_left;
    private final ModelPart nose_right;
    private final ModelPart nose_top;
    private final ModelPart nose_front;
    private final ModelPart nose_left_2;
    private final ModelPart nose_left_3;
    private final ModelPart nose_right_2;
    private final ModelPart nose_right_3;
    private final ModelPart nose_tip;
    private final ModelPart shape90;
    private final ModelPart shape91;
    private final ModelPart shape92;
    private final ModelPart shape95;
    private final ModelPart shape97;
    private final ModelPart shape93;
    private final ModelPart shape96;
    private final ModelPart shape90_1;
    private final ModelPart shape91_1;
    private final ModelPart shape92_1;
    private final ModelPart shape95_1;
    private final ModelPart shape97_1;
    private final ModelPart shape93_1;
    private final ModelPart shape96_1;
    private final ModelPart arm_left_rotation_point;
    private final ModelPart arm_left_main;
    private final ModelPart arm_left_1;
    private final ModelPart spike;
    private final ModelPart arm_left_2;
    private final ModelPart arm_left_1_decoration;
    private final ModelPart arm_left_1_decoration_1;
    private final ModelPart hand_left_main;
    private final ModelPart hand_left_back;
    private final ModelPart hand_left_finger_right_1;
    private final ModelPart hand_left_finger_left_1;
    private final ModelPart hand_left_2;
    private final ModelPart hand_left_finger_right_2;
    private final ModelPart hand_left_finger_right_3;
    private final ModelPart hand_left_finger_left_2;
    private final ModelPart hand_left_finger_left_3;
    private final ModelPart hand_left_thumb_1;
    private final ModelPart hand_left_thumb_2;
    private final ModelPart spike_1;
    private final ModelPart spike_2;
    private final ModelPart arm_right_rotation_point;
    private final ModelPart arm_right_main;
    private final ModelPart arm_right_1;
    private final ModelPart spike_3;
    private final ModelPart arm_right_2;
    private final ModelPart arm_right_1_decoration;
    private final ModelPart arm_right_1_decoration_1;
    private final ModelPart hand_right_main;
    private final ModelPart hand_right_back;
    private final ModelPart hand_right_finger_right_1;
    private final ModelPart hand_right_finger_left_1;
    private final ModelPart hand_right_2;
    private final ModelPart hand_right_finger_right_2;
    private final ModelPart hand_right_finger_right_3;
    private final ModelPart hand_right_finger_left_2;
    private final ModelPart hand_right_finger_left_3;
    private final ModelPart hand_right_thumb_1;
    private final ModelPart hand_right_thumb_2;
    private final ModelPart spike_4;
    private final ModelPart spike_5;
    private final ModelPart spike_6;
    private final ModelPart spike_7;
    private final ModelPart spike_8;
    private final ModelPart spike_9;
    private final ModelPart spike_10;
    private final ModelPart spike_11;
    private final ModelPart body_abs_bottom;
    private final ModelPart leg_left_2;
    private final ModelPart leg_left_top_decoration;
    private final ModelPart leg_left_3;
    private final ModelPart leg_left_calf_muscle;
    private final ModelPart leg_left_4;
    private final ModelPart foot_left_front_slope;
    private final ModelPart foot_left_back;
    private final ModelPart foot_left_back_slope;
    private final ModelPart leg_right_2;
    private final ModelPart leg_right_top_decoration;
    private final ModelPart leg_right_3;
    private final ModelPart leg_right_calf_muscle;
    private final ModelPart leg_right_4;
    private final ModelPart foot_right_front_slope;
    private final ModelPart foot_right_back;
    private final ModelPart foot_right_back_slope;

    public EndTrollModel(ModelPart root) {
        super(root);
        this.movement_base = root.getChild("movement_base");
        this.bottom_body = this.movement_base.getChild("bottom_body");
        this.stomach = this.bottom_body.getChild("stomach");
        this.leg_left_1 = this.bottom_body.getChild("leg_left_1");
        this.leg_right_1 = this.bottom_body.getChild("leg_right_1");
        this.torso = this.stomach.getChild("torso");
        this.body_abs = this.stomach.getChild("body_abs");
        this.leg_left_2 = this.leg_left_1.getChild("leg_left_2");
        this.leg_left_top_decoration = this.leg_left_1.getChild("leg_left_top_decoration");
        this.leg_right_2 = this.leg_right_1.getChild("leg_right_2");
        this.leg_right_top_decoration = this.leg_right_1.getChild("leg_right_top_decoration");
        this.right_eyelid_base = this.torso.getChild("right_eyelid_base");
        this.left_eyelid_base = this.torso.getChild("left_eyelid_base");
        this.eye = this.torso.getChild("eye");
        this.eye_details_top = this.torso.getChild("eye_details_top");
        this.eye_details_top_1 = this.torso.getChild("eye_details_top_1");
        this.eye_details_bottom = this.torso.getChild("eye_details_bottom");
        this.eye_details_bottom_1 = this.torso.getChild("eye_details_bottom_1");
        this.neck_bottom_front = this.torso.getChild("neck_bottom_front");
        this.yaw_right = this.torso.getChild("yaw_right");
        this.yaw_left = this.torso.getChild("yaw_left");
        this.mouth_cheeck_left = this.torso.getChild("mouth_cheeck_left");
        this.back = this.torso.getChild("back");
        this.head_top = this.torso.getChild("head_top");
        this.mouth_cheeck_right = this.torso.getChild("mouth_cheeck_right");
        this.meat_left = this.torso.getChild("meat_left");
        this.meat_right = this.torso.getChild("meat_right");
        this.shoulder_main_left = this.torso.getChild("shoulder_main_left");
        this.shoulder_main_right = this.torso.getChild("shoulder_main_right");
        this.arm_left_connection = this.torso.getChild("arm_left_connection");
        this.arm_right_connection = this.torso.getChild("arm_right_connection");
        this.shoulder_bone_left = this.torso.getChild("shoulder_bone_left");
        this.shoulder_bone_left_1 = this.torso.getChild("shoulder_bone_left_1");
        this.body_abs_bottom = this.body_abs.getChild("body_abs_bottom");
        this.leg_left_3 = this.leg_left_2.getChild("leg_left_3");
        this.leg_left_calf_muscle = this.leg_left_2.getChild("leg_left_calf_muscle");
        this.leg_right_3 = this.leg_right_2.getChild("leg_right_3");
        this.leg_right_calf_muscle = this.leg_right_2.getChild("leg_right_calf_muscle");
        this.right_eyelid_side = this.right_eyelid_base.getChild("right_eyelid_side");
        this.right_eyelid_top = this.right_eyelid_base.getChild("right_eyelid_top");
        this.right_eyelid_bottom = this.right_eyelid_base.getChild("right_eyelid_bottom");
        this.left_eyelid_side = this.left_eyelid_base.getChild("left_eyelid_side");
        this.left_eyelid_top = this.left_eyelid_base.getChild("left_eyelid_top");
        this.left_eyelid_bottom = this.left_eyelid_base.getChild("left_eyelid_bottom");
        this.mouth_bottom_front = this.neck_bottom_front.getChild("mouth_bottom_front");
        this.mouth_top_lip = this.head_top.getChild("mouth_top_lip");
        this.head_back = this.head_top.getChild("head_back");
        this.head_parts = this.head_top.getChild("head_parts");
        this.head_parts_1 = this.head_top.getChild("head_parts_1");
        this.nose_main = this.head_top.getChild("nose_main");
        this.shape90 = this.shoulder_main_left.getChild("shape90");
        this.shape91 = this.shoulder_main_left.getChild("shape91");
        this.shape90_1 = this.shoulder_main_right.getChild("shape90_1");
        this.shape91_1 = this.shoulder_main_right.getChild("shape91_1");
        this.arm_left_rotation_point = this.arm_left_connection.getChild("arm_left_rotation_point");
        this.arm_right_rotation_point = this.arm_right_connection.getChild("arm_right_rotation_point");
        this.spike_6 = this.shoulder_bone_left.getChild("spike_6");
        this.spike_7 = this.shoulder_bone_left.getChild("spike_7");
        this.spike_8 = this.shoulder_bone_left.getChild("spike_8");
        this.spike_9 = this.shoulder_bone_left_1.getChild("spike_9");
        this.spike_10 = this.shoulder_bone_left_1.getChild("spike_10");
        this.spike_11 = this.shoulder_bone_left_1.getChild("spike_11");
        this.leg_left_4 = this.leg_left_3.getChild("leg_left_4");
        this.foot_left_front_slope = this.leg_left_3.getChild("foot_left_front_slope");
        this.leg_right_4 = this.leg_right_3.getChild("leg_right_4");
        this.foot_right_front_slope = this.leg_right_3.getChild("foot_right_front_slope");
        this.right_eyelid_side_top = this.right_eyelid_top.getChild("right_eyelid_side_top");
        this.right_eyelid_side_bottom = this.right_eyelid_bottom.getChild("right_eyelid_side_bottom");
        this.left_eyelid_side_top = this.left_eyelid_top.getChild("left_eyelid_side_top");
        this.left_eyelid_side_bottom = this.left_eyelid_bottom.getChild("left_eyelid_side_bottom");
        this.mouth_bottom_left = this.mouth_bottom_front.getChild("mouth_bottom_left");
        this.mouth_bottom_right = this.mouth_bottom_front.getChild("mouth_bottom_right");
        this.tooth = this.mouth_bottom_front.getChild("tooth");
        this.mouth_top_front = this.mouth_top_lip.getChild("mouth_top_front");
        this.head_left = this.head_back.getChild("head_left");
        this.head_right = this.head_back.getChild("head_right");
        this.head_back_cover = this.head_back.getChild("head_back_cover");
        this.head_parts_2 = this.head_parts.getChild("head_parts_2");
        this.head_parts_4 = this.head_parts_1.getChild("head_parts_4");
        this.nose_bottom = this.nose_main.getChild("nose_bottom");
        this.nose_left = this.nose_main.getChild("nose_left");
        this.nose_right = this.nose_main.getChild("nose_right");
        this.nose_top = this.nose_main.getChild("nose_top");
        this.nose_front = this.nose_main.getChild("nose_front");
        this.shape92 = this.shape90.getChild("shape92");
        this.shape93 = this.shape91.getChild("shape93");
        this.shape92_1 = this.shape90_1.getChild("shape92_1");
        this.shape93_1 = this.shape91_1.getChild("shape93_1");
        this.arm_left_main = this.arm_left_rotation_point.getChild("arm_left_main");
        this.arm_right_main = this.arm_right_rotation_point.getChild("arm_right_main");
        this.foot_left_back = this.leg_left_4.getChild("foot_left_back");
        this.foot_right_back = this.leg_right_4.getChild("foot_right_back");
        this.tooth_1 = this.mouth_bottom_left.getChild("tooth_1");
        this.neck_bottom_left = this.mouth_bottom_left.getChild("neck_bottom_left");
        this.tooth_2 = this.mouth_bottom_right.getChild("tooth_2");
        this.neck_bottom_right = this.mouth_bottom_right.getChild("neck_bottom_right");
        this.tooth_3 = this.tooth.getChild("tooth_3");
        this.tooth_4 = this.tooth.getChild("tooth_4");
        this.mouth_top_left = this.mouth_top_front.getChild("mouth_top_left");
        this.mouth_top_right = this.mouth_top_front.getChild("mouth_top_right");
        this.tooth_5 = this.mouth_top_front.getChild("tooth_5");
        this.tooth_6 = this.mouth_top_front.getChild("tooth_6");
        this.head_parts_3 = this.head_parts_2.getChild("head_parts_3");
        this.head_parts_5 = this.head_parts_4.getChild("head_parts_5");
        this.nose_left_2 = this.nose_left.getChild("nose_left_2");
        this.nose_right_2 = this.nose_right.getChild("nose_right_2");
        this.nose_tip = this.nose_front.getChild("nose_tip");
        this.shape95 = this.shape92.getChild("shape95");
        this.shape96 = this.shape93.getChild("shape96");
        this.shape95_1 = this.shape92_1.getChild("shape95_1");
        this.shape96_1 = this.shape93_1.getChild("shape96_1");
        this.arm_left_1 = this.arm_left_main.getChild("arm_left_1");
        this.spike = this.arm_left_main.getChild("spike");
        this.arm_right_1 = this.arm_right_main.getChild("arm_right_1");
        this.spike_3 = this.arm_right_main.getChild("spike_3");
        this.foot_left_back_slope = this.foot_left_back.getChild("foot_left_back_slope");
        this.foot_right_back_slope = this.foot_right_back.getChild("foot_right_back_slope");
        this.mouth_top_lips_left = this.mouth_top_left.getChild("mouth_top_lips_left");
        this.mouth_top_left_back = this.mouth_top_left.getChild("mouth_top_left_back");
        this.tooth_7 = this.mouth_top_left.getChild("tooth_7");
        this.mouth_top_lips_right = this.mouth_top_right.getChild("mouth_top_lips_right");
        this.mouth_top_right_back = this.mouth_top_right.getChild("mouth_top_right_back");
        this.tooth_8 = this.mouth_top_right.getChild("tooth_8");
        this.nose_left_3 = this.nose_left_2.getChild("nose_left_3");
        this.nose_right_3 = this.nose_right_2.getChild("nose_right_3");
        this.shape97 = this.shape95.getChild("shape97");
        this.shape97_1 = this.shape95_1.getChild("shape97_1");
        this.arm_left_2 = this.arm_left_1.getChild("arm_left_2");
        this.arm_left_1_decoration = this.arm_left_1.getChild("arm_left_1_decoration");
        this.arm_left_1_decoration_1 = this.arm_left_1.getChild("arm_left_1_decoration_1");
        this.spike_1 = this.spike.getChild("spike_1");
        this.spike_2 = this.spike.getChild("spike_2");
        this.arm_right_2 = this.arm_right_1.getChild("arm_right_2");
        this.arm_right_1_decoration = this.arm_right_1.getChild("arm_right_1_decoration");
        this.arm_right_1_decoration_1 = this.arm_right_1.getChild("arm_right_1_decoration_1");
        this.spike_4 = this.spike_3.getChild("spike_4");
        this.spike_5 = this.spike_3.getChild("spike_5");
        this.mouth_skin_left = this.mouth_top_left_back.getChild("mouth_skin_left");
        this.shape62 = this.mouth_top_left_back.getChild("shape62");
        this.mouth_skin_right = this.mouth_top_right_back.getChild("mouth_skin_right");
        this.shape72 = this.mouth_top_right_back.getChild("shape72");
        this.hand_left_main = this.arm_left_2.getChild("hand_left_main");
        this.hand_right_main = this.arm_right_2.getChild("hand_right_main");
        this.hand_left_back = this.hand_left_main.getChild("hand_left_back");
        this.hand_left_finger_right_1 = this.hand_left_main.getChild("hand_left_finger_right_1");
        this.hand_left_finger_left_1 = this.hand_left_main.getChild("hand_left_finger_left_1");
        this.hand_left_2 = this.hand_left_main.getChild("hand_left_2");
        this.hand_right_back = this.hand_right_main.getChild("hand_right_back");
        this.hand_right_finger_right_1 = this.hand_right_main.getChild("hand_right_finger_right_1");
        this.hand_right_finger_left_1 = this.hand_right_main.getChild("hand_right_finger_left_1");
        this.hand_right_2 = this.hand_right_main.getChild("hand_right_2");
        this.hand_left_finger_right_2 = this.hand_left_finger_right_1.getChild("hand_left_finger_right_2");
        this.hand_left_finger_left_2 = this.hand_left_finger_left_1.getChild("hand_left_finger_left_2");
        this.hand_left_thumb_1 = this.hand_left_2.getChild("hand_left_thumb_1");
        this.hand_right_finger_right_2 = this.hand_right_finger_right_1.getChild("hand_right_finger_right_2");
        this.hand_right_finger_left_2 = this.hand_right_finger_left_1.getChild("hand_right_finger_left_2");
        this.hand_right_thumb_1 = this.hand_right_2.getChild("hand_right_thumb_1");
        this.hand_left_finger_right_3 = this.hand_left_finger_right_2.getChild("hand_left_finger_right_3");
        this.hand_left_finger_left_3 = this.hand_left_finger_left_2.getChild("hand_left_finger_left_3");
        this.hand_left_thumb_2 = this.hand_left_thumb_1.getChild("hand_left_thumb_2");
        this.hand_right_finger_right_3 = this.hand_right_finger_right_2.getChild("hand_right_finger_right_3");
        this.hand_right_finger_left_3 = this.hand_right_finger_left_2.getChild("hand_right_finger_left_3");
        this.hand_right_thumb_2 = this.hand_right_thumb_1.getChild("hand_right_thumb_2");

        // Rotation fixes for the original default position.
        this.head_top.xRot = (float) Math.toRadians(8.0F);
        this.left_eyelid_base.yRot = (float) Math.toRadians(0.0F);
        this.right_eyelid_base.yRot = (float) Math.toRadians(0.0F);
        this.leg_left_1.y = -1.4F;
        this.leg_right_1.y = -1.4F;
        this.movement_base.z = -20.0F;
        this.bottom_body.xRot = (float) Math.toRadians(-90F);
        this.leg_left_1.xRot = (float) Math.toRadians(56.5F);
        this.leg_right_1.xRot = (float) Math.toRadians(56.5F);
        this.stomach.xRot = (float) Math.toRadians(-8.0F);
        this.torso.xRot = (float) Math.toRadians(105.5F);
        this.hand_left_main.yRot = (float) Math.toRadians(0.0F);
        this.hand_right_main.yRot = (float) Math.toRadians(180.0F);
        this.left_eyelid_base.yRot = (float) Math.toRadians(-14.0F);
        this.right_eyelid_base.yRot = (float) Math.toRadians(14.0F);
        this.arm_left_main.xRot = (float) Math.toRadians(0.0F);
        this.arm_left_main.zRot = (float) Math.toRadians(-6.8F);
        this.arm_left_main.yRot = (float) Math.toRadians(6.8F);
        this.arm_right_main.xRot = (float) Math.toRadians(0.0F);
        this.arm_right_main.zRot = (float) Math.toRadians(6.8F);
        this.arm_right_main.yRot = (float) Math.toRadians(-6.8F);
        this.hand_left_thumb_1.zRot = (float) Math.toRadians(14.0F);
        this.hand_left_thumb_2.zRot = (float) Math.toRadians(18.0F);
        this.hand_right_thumb_1.zRot = (float) Math.toRadians(14.0F);
        this.hand_right_thumb_2.zRot = (float) Math.toRadians(18.0F);
        this.leg_left_1.z = -1.8F;
        this.leg_right_1.z = -1.8F;

        registerAnimatedParts();
    }

    private void registerAnimatedParts() {
        registerAnimatedPart(movement_base);
        registerAnimatedPart(bottom_body);
        registerAnimatedPart(stomach);
        registerAnimatedPart(leg_left_1);
        registerAnimatedPart(leg_right_1);
        registerAnimatedPart(torso);
        registerAnimatedPart(body_abs);
        registerAnimatedPart(leg_left_2);
        registerAnimatedPart(leg_left_top_decoration);
        registerAnimatedPart(leg_right_2);
        registerAnimatedPart(leg_right_top_decoration);
        registerAnimatedPart(right_eyelid_base);
        registerAnimatedPart(left_eyelid_base);
        registerAnimatedPart(eye);
        registerAnimatedPart(eye_details_top);
        registerAnimatedPart(eye_details_top_1);
        registerAnimatedPart(eye_details_bottom);
        registerAnimatedPart(eye_details_bottom_1);
        registerAnimatedPart(neck_bottom_front);
        registerAnimatedPart(yaw_right);
        registerAnimatedPart(yaw_left);
        registerAnimatedPart(mouth_cheeck_left);
        registerAnimatedPart(back);
        registerAnimatedPart(head_top);
        registerAnimatedPart(mouth_cheeck_right);
        registerAnimatedPart(meat_left);
        registerAnimatedPart(meat_right);
        registerAnimatedPart(shoulder_main_left);
        registerAnimatedPart(shoulder_main_right);
        registerAnimatedPart(arm_left_connection);
        registerAnimatedPart(arm_right_connection);
        registerAnimatedPart(shoulder_bone_left);
        registerAnimatedPart(shoulder_bone_left_1);
        registerAnimatedPart(body_abs_bottom);
        registerAnimatedPart(leg_left_3);
        registerAnimatedPart(leg_left_calf_muscle);
        registerAnimatedPart(leg_right_3);
        registerAnimatedPart(leg_right_calf_muscle);
        registerAnimatedPart(right_eyelid_side);
        registerAnimatedPart(right_eyelid_top);
        registerAnimatedPart(right_eyelid_bottom);
        registerAnimatedPart(left_eyelid_side);
        registerAnimatedPart(left_eyelid_top);
        registerAnimatedPart(left_eyelid_bottom);
        registerAnimatedPart(mouth_bottom_front);
        registerAnimatedPart(mouth_top_lip);
        registerAnimatedPart(head_back);
        registerAnimatedPart(head_parts);
        registerAnimatedPart(head_parts_1);
        registerAnimatedPart(nose_main);
        registerAnimatedPart(shape90);
        registerAnimatedPart(shape91);
        registerAnimatedPart(shape90_1);
        registerAnimatedPart(shape91_1);
        registerAnimatedPart(arm_left_rotation_point);
        registerAnimatedPart(arm_right_rotation_point);
        registerAnimatedPart(spike_6);
        registerAnimatedPart(spike_7);
        registerAnimatedPart(spike_8);
        registerAnimatedPart(spike_9);
        registerAnimatedPart(spike_10);
        registerAnimatedPart(spike_11);
        registerAnimatedPart(leg_left_4);
        registerAnimatedPart(foot_left_front_slope);
        registerAnimatedPart(leg_right_4);
        registerAnimatedPart(foot_right_front_slope);
        registerAnimatedPart(right_eyelid_side_top);
        registerAnimatedPart(right_eyelid_side_bottom);
        registerAnimatedPart(left_eyelid_side_top);
        registerAnimatedPart(left_eyelid_side_bottom);
        registerAnimatedPart(mouth_bottom_left);
        registerAnimatedPart(mouth_bottom_right);
        registerAnimatedPart(tooth);
        registerAnimatedPart(mouth_top_front);
        registerAnimatedPart(head_left);
        registerAnimatedPart(head_right);
        registerAnimatedPart(head_back_cover);
        registerAnimatedPart(head_parts_2);
        registerAnimatedPart(head_parts_4);
        registerAnimatedPart(nose_bottom);
        registerAnimatedPart(nose_left);
        registerAnimatedPart(nose_right);
        registerAnimatedPart(nose_top);
        registerAnimatedPart(nose_front);
        registerAnimatedPart(shape92);
        registerAnimatedPart(shape93);
        registerAnimatedPart(shape92_1);
        registerAnimatedPart(shape93_1);
        registerAnimatedPart(arm_left_main);
        registerAnimatedPart(arm_right_main);
        registerAnimatedPart(foot_left_back);
        registerAnimatedPart(foot_right_back);
        registerAnimatedPart(tooth_1);
        registerAnimatedPart(neck_bottom_left);
        registerAnimatedPart(tooth_2);
        registerAnimatedPart(neck_bottom_right);
        registerAnimatedPart(tooth_3);
        registerAnimatedPart(tooth_4);
        registerAnimatedPart(mouth_top_left);
        registerAnimatedPart(mouth_top_right);
        registerAnimatedPart(tooth_5);
        registerAnimatedPart(tooth_6);
        registerAnimatedPart(head_parts_3);
        registerAnimatedPart(head_parts_5);
        registerAnimatedPart(nose_left_2);
        registerAnimatedPart(nose_right_2);
        registerAnimatedPart(nose_tip);
        registerAnimatedPart(shape95);
        registerAnimatedPart(shape96);
        registerAnimatedPart(shape95_1);
        registerAnimatedPart(shape96_1);
        registerAnimatedPart(arm_left_1);
        registerAnimatedPart(spike);
        registerAnimatedPart(arm_right_1);
        registerAnimatedPart(spike_3);
        registerAnimatedPart(foot_left_back_slope);
        registerAnimatedPart(foot_right_back_slope);
        registerAnimatedPart(mouth_top_lips_left);
        registerAnimatedPart(mouth_top_left_back);
        registerAnimatedPart(tooth_7);
        registerAnimatedPart(mouth_top_lips_right);
        registerAnimatedPart(mouth_top_right_back);
        registerAnimatedPart(tooth_8);
        registerAnimatedPart(nose_left_3);
        registerAnimatedPart(nose_right_3);
        registerAnimatedPart(shape97);
        registerAnimatedPart(shape97_1);
        registerAnimatedPart(arm_left_2);
        registerAnimatedPart(arm_left_1_decoration);
        registerAnimatedPart(arm_left_1_decoration_1);
        registerAnimatedPart(spike_1);
        registerAnimatedPart(spike_2);
        registerAnimatedPart(arm_right_2);
        registerAnimatedPart(arm_right_1_decoration);
        registerAnimatedPart(arm_right_1_decoration_1);
        registerAnimatedPart(spike_4);
        registerAnimatedPart(spike_5);
        registerAnimatedPart(mouth_skin_left);
        registerAnimatedPart(shape62);
        registerAnimatedPart(mouth_skin_right);
        registerAnimatedPart(shape72);
        registerAnimatedPart(hand_left_main);
        registerAnimatedPart(hand_right_main);
        registerAnimatedPart(hand_left_back);
        registerAnimatedPart(hand_left_finger_right_1);
        registerAnimatedPart(hand_left_finger_left_1);
        registerAnimatedPart(hand_left_2);
        registerAnimatedPart(hand_right_back);
        registerAnimatedPart(hand_right_finger_right_1);
        registerAnimatedPart(hand_right_finger_left_1);
        registerAnimatedPart(hand_right_2);
        registerAnimatedPart(hand_left_finger_right_2);
        registerAnimatedPart(hand_left_finger_left_2);
        registerAnimatedPart(hand_left_thumb_1);
        registerAnimatedPart(hand_right_finger_right_2);
        registerAnimatedPart(hand_right_finger_left_2);
        registerAnimatedPart(hand_right_thumb_1);
        registerAnimatedPart(hand_left_finger_right_3);
        registerAnimatedPart(hand_left_finger_left_3);
        registerAnimatedPart(hand_left_thumb_2);
        registerAnimatedPart(hand_right_finger_right_3);
        registerAnimatedPart(hand_right_finger_left_3);
        registerAnimatedPart(hand_right_thumb_2);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition root = meshdefinition.getRoot();
        PartDefinition movement_base = root.addOrReplaceChild("movement_base",
                CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bottom_body = movement_base.addOrReplaceChild("bottom_body",
                CubeListBuilder.create().texOffs(0, 125).addBox(-13.0F, -7.5F, -8.0F, 26, 15, 16, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -25.0F, 19.0F));

        PartDefinition stomach = bottom_body.addOrReplaceChild("stomach",
                CubeListBuilder.create().texOffs(0, 90).addBox(-10.0F, 0.0F, -21.0F, 20, 13, 21, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -5.5F, 0.0F, -0.5009094953223726F, 0.0F, 0.0F));

        PartDefinition leg_left_1 = bottom_body.addOrReplaceChild("leg_left_1",
                CubeListBuilder.create().texOffs(0, 170).addBox(0.0F, -1.8F, -5.0F, 6, 14, 10, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(13.0F, 0.0F, 0.0F, -0.593411945678072F, 0.0F, 0.0F));

        PartDefinition leg_right_1 = bottom_body.addOrReplaceChild("leg_right_1",
                CubeListBuilder.create().texOffs(46, 170).addBox(-6.0F, -1.8F, -5.0F, 6, 14, 10, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-13.0F, 0.0F, 0.0F, -0.5756095873077298F, 0.0F, 0.0F));

        PartDefinition torso = stomach.addOrReplaceChild("torso",
                CubeListBuilder.create().texOffs(62, 70).addBox(-16.0F, -16.0F, -8.0F, 32, 19, 20, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 10.0F, -18.0F, 1.2304571226560024F, 0.0F, 0.0F));

        PartDefinition body_abs = stomach.addOrReplaceChild("body_abs",
                CubeListBuilder.create().texOffs(0, 57).addBox(-9.0F, -5.0F, 0.0F, 18, 5, 7, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 16.4F, -20.0F, -0.14800392056911915F, 0.0F, 0.0F));

        PartDefinition leg_left_2 = leg_left_1.addOrReplaceChild("leg_left_2",
                CubeListBuilder.create().texOffs(0, 195).addBox(-2.5F, -3.9F, 0.0F, 5, 6, 11, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(3.0F, 10.0F, 2.5F, -0.27314402793711257F, 0.0F, -0.019198621771937624F));

        PartDefinition leg_left_top_decoration = leg_left_1.addOrReplaceChild("leg_left_top_decoration",
                CubeListBuilder.create().texOffs(0, 157).addBox(-7.0F, 0.0F, -4.0F, 7, 4, 8, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(5.0F, -1.8F, 0.0F, 0.0F, 0.0F, 0.7089527421600966F));

        PartDefinition leg_right_2 = leg_right_1.addOrReplaceChild("leg_right_2",
                CubeListBuilder.create().texOffs(46, 195).addBox(-2.5F, -3.9F, 0.0F, 5, 6, 11, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-3.0F, 10.0F, 2.5F, -0.27314402793711257F, 0.0F, -0.019198621771937624F));

        PartDefinition leg_right_top_decoration = leg_right_1.addOrReplaceChild("leg_right_top_decoration",
                CubeListBuilder.create().texOffs(46, 157).addBox(-7.0F, 0.0F, -4.0F, 7, 4, 8, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-5.0F, -1.8F, 0.0F, 0.0F, 3.141592653589793F, -0.7089527421600966F));

        PartDefinition right_eyelid_base = torso.addOrReplaceChild("right_eyelid_base",
                CubeListBuilder.create().texOffs(207, 27).addBox(-4.0F, -7.0F, -19.0F, 4, 7, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -9.5F, 8.0F, 0.0F, 0.3141592653589793F, 0.0F));

        PartDefinition left_eyelid_base = torso.addOrReplaceChild("left_eyelid_base",
                CubeListBuilder.create().texOffs(222, 27).addBox(0.0F, -7.0F, -19.0F, 4, 7, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -9.5F, 8.0F, 0.0F, -0.3141592653589793F, 0.0F));

        PartDefinition eye = torso.addOrReplaceChild("eye",
                CubeListBuilder.create().texOffs(112, 59).addBox(-2.5F, -2.5F, -2.5F, 5, 5, 5, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -13.0F, -7.5F));

        PartDefinition eye_details_top = torso.addOrReplaceChild("eye_details_top",
                CubeListBuilder.create().texOffs(93, 59).addBox(-7.0F, 0.0F, -1.0F, 7, 2, 2, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -17.4F, -7.4F, 0.0F, -0.1361356816555577F, -0.17453292519943295F));

        PartDefinition eye_details_top_1 = torso.addOrReplaceChild("eye_details_top_1",
                CubeListBuilder.create().texOffs(133, 59).addBox(0.0F, 0.0F, -1.0F, 7, 2, 2, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -17.4F, -7.4F, 0.0F, 0.1361356816555577F, 0.17453292519943295F));

        PartDefinition eye_details_bottom = torso.addOrReplaceChild("eye_details_bottom",
                CubeListBuilder.create().texOffs(133, 64).addBox(0.0F, -2.0F, -1.0F, 7, 2, 2, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -8.6F, -7.4F, 0.0F, 0.1361356816555577F, -0.17453292519943295F));

        PartDefinition eye_details_bottom_1 = torso.addOrReplaceChild("eye_details_bottom_1",
                CubeListBuilder.create().texOffs(93, 64).addBox(-7.0F, -2.0F, -1.0F, 7, 2, 2, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -8.6F, -7.4F, 0.0F, -0.1361356816555577F, 0.17453292519943295F));

        PartDefinition neck_bottom_front = torso.addOrReplaceChild("neck_bottom_front",
                CubeListBuilder.create().texOffs(205, 216).addBox(-5.0F, -10.0F, 0.0F, 10, 7, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -11.8F, -5.4F, 0.4974188368183839F, 0.0F, 0.0F));

        PartDefinition yaw_right = torso.addOrReplaceChild("yaw_right",
                CubeListBuilder.create().texOffs(182, 239).addBox(-3.0F, -8.0F, 0.0F, 3, 8, 8, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(12.2F, -14.7F, -5.9F, 0.0F, 0.1884955592153876F, -0.23561944901923448F));

        PartDefinition yaw_left = torso.addOrReplaceChild("yaw_left",
                CubeListBuilder.create().texOffs(205, 239).addBox(0.0F, -8.0F, 0.0F, 3, 8, 8, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-12.2F, -14.7F, -5.9F, 0.0F, -0.1884955592153876F, 0.23561944901923448F));

        PartDefinition mouth_cheeck_left = torso.addOrReplaceChild("mouth_cheeck_left",
                CubeListBuilder.create().texOffs(206, 164).addBox(-4.0F, -12.0F, 0.0F, 4, 12, 6, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(12.0F, -16.0F, 0.0F, 0.0F, 0.3385938748868999F, -0.08726646259971647F));

        PartDefinition back = torso.addOrReplaceChild("back",
                CubeListBuilder.create().texOffs(184, 144).addBox(-8.0F, -29.0F, -5.0F, 16, 14, 5, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 1.2F, 11.1F, -0.05811946409141117F, 0.0F, 0.0F));

        PartDefinition head_top = torso.addOrReplaceChild("head_top",
                CubeListBuilder.create().texOffs(180, 130).addBox(-5.0F, -14.0F, -12.6F, 10, 3, 10, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -20.2F, 7.9F, -0.40980330836826856F, 0.0F, 0.0F));

        PartDefinition mouth_cheeck_right = torso.addOrReplaceChild("mouth_cheeck_right",
                CubeListBuilder.create().texOffs(185, 164).addBox(0.0F, -12.0F, 0.0F, 4, 12, 6, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-12.0F, -16.0F, 0.0F, 0.0F, -0.3385938748868999F, 0.08726646259971647F));

        PartDefinition meat_left = torso.addOrReplaceChild("meat_left",
                CubeListBuilder.create().texOffs(227, 149).addBox(-4.0F, -12.0F, 0.0F, 4, 12, 8, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(13.0F, -16.0F, 6.0F, 0.0F, -0.6806784082777886F, 0.025132741228718346F));

        PartDefinition meat_right = torso.addOrReplaceChild("meat_right",
                CubeListBuilder.create().texOffs(227, 170).addBox(0.0F, -12.0F, 0.0F, 4, 12, 8, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-13.0F, -16.0F, 6.0F, 0.0F, 0.6806784082777886F, -0.025132741228718346F));

        PartDefinition shoulder_main_left = torso.addOrReplaceChild("shoulder_main_left",
                CubeListBuilder.create().texOffs(118, 0).addBox(0.0F, -7.0F, -7.0F, 5, 5, 14, new CubeDeformation(0.0F)),
                PartPose.offset(12.0F, -14.0F, 2.0F));

        PartDefinition shoulder_main_right = torso.addOrReplaceChild("shoulder_main_right",
                CubeListBuilder.create().texOffs(38, 0).addBox(0.0F, -7.0F, -7.0F, 5, 5, 14, new CubeDeformation(0.0F)),
                PartPose.offset(-17.0F, -14.0F, 2.0F));

        PartDefinition arm_left_connection = torso.addOrReplaceChild("arm_left_connection",
                CubeListBuilder.create().texOffs(92, 110).addBox(0.0F, -3.5F, -3.5F, 3, 7, 7, new CubeDeformation(0.0F)),
                PartPose.offset(16.0F, -12.3F, 2.0F));

        PartDefinition arm_right_connection = torso.addOrReplaceChild("arm_right_connection",
                CubeListBuilder.create().texOffs(141, 110).addBox(-3.0F, -3.5F, -3.5F, 3, 7, 7, new CubeDeformation(0.0F)),
                PartPose.offset(-16.0F, -12.3F, 2.0F));

        PartDefinition shoulder_bone_left = torso.addOrReplaceChild("shoulder_bone_left",
                CubeListBuilder.create().texOffs(147, 64).addBox(0.0F, 0.0F, -5.0F, 14, 20, 5, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -20.2F, 15.0F, 0.0F, 0.22689280275926282F, 0.0F));

        PartDefinition shoulder_bone_left_1 = torso.addOrReplaceChild("shoulder_bone_left_1",
                CubeListBuilder.create().texOffs(186, 64).addBox(-14.0F, 0.0F, -5.0F, 14, 20, 5, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -20.21F, 15.0F, 0.0F, -0.22689280275926282F, 0.0F));

        PartDefinition body_abs_bottom = body_abs.addOrReplaceChild("body_abs_bottom",
                CubeListBuilder.create().texOffs(0, 70).addBox(-8.0F, -5.0F, 0.0F, 16, 5, 14, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 7.0F, 0.3410373358396919F, 0.0F, 0.0F));

        PartDefinition leg_left_3 = leg_left_2.addOrReplaceChild("leg_left_3",
                CubeListBuilder.create().texOffs(0, 223).addBox(-3.0F, 0.0F, -0.5F, 6, 7, 11, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -4.0F, 11.0F, -1.1215485773315563F, 0.0F, 0.0F));

        PartDefinition leg_left_calf_muscle = leg_left_2.addOrReplaceChild("leg_left_calf_muscle",
                CubeListBuilder.create().texOffs(23, 213).addBox(-1.5F, 0.0F, -7.0F, 3, 2, 7, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -4.0F, 6.0F, -0.4052654523130833F, 0.0F, 0.0F));

        PartDefinition leg_right_3 = leg_right_2.addOrReplaceChild("leg_right_3",
                CubeListBuilder.create().texOffs(46, 223).addBox(-3.0F, 0.0F, -0.5F, 6, 7, 11, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -4.0F, 11.0F, -1.1215485773315563F, 0.0F, 0.0F));

        PartDefinition leg_right_calf_muscle = leg_right_2.addOrReplaceChild("leg_right_calf_muscle",
                CubeListBuilder.create().texOffs(69, 213).addBox(-1.5F, 0.0F, -7.0F, 3, 2, 7, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -4.0F, 6.0F, -0.4052654523130833F, 0.0F, 0.0F));

        PartDefinition right_eyelid_side = right_eyelid_base.addOrReplaceChild("right_eyelid_side",
                CubeListBuilder.create().texOffs(188, 27).addBox(-6.0F, -7.0F, 0.0F, 6, 7, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-4.0F, 0.01F, -19.0F, 0.0F, 0.5410520681182421F, 0.0F));

        PartDefinition right_eyelid_top = right_eyelid_base.addOrReplaceChild("right_eyelid_top",
                CubeListBuilder.create().texOffs(205, 18).addBox(-4.0F, -5.0F, 0.0F, 4, 5, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.01F, -7.0F, -19.0F, -0.6632251157578453F, 0.0F, -0.0F));

        PartDefinition right_eyelid_bottom = right_eyelid_base.addOrReplaceChild("right_eyelid_bottom",
                CubeListBuilder.create().texOffs(211, 38).addBox(-4.0F, 0.0F, 0.0F, 4, 5, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.01F, 0.0F, -19.0F, 0.6632251157578453F, 0.0F, 0.0F));

        PartDefinition left_eyelid_side = left_eyelid_base.addOrReplaceChild("left_eyelid_side",
                CubeListBuilder.create().texOffs(237, 27).addBox(0.0F, -7.0F, 0.0F, 6, 7, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(4.0F, 0.01F, -19.0F, 0.0F, -0.5410520681182421F, 0.0F));

        PartDefinition left_eyelid_top = left_eyelid_base.addOrReplaceChild("left_eyelid_top",
                CubeListBuilder.create().texOffs(220, 18).addBox(0.0F, -5.0F, 0.0F, 4, 5, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-0.01F, -7.0F, -19.0F, -0.6632251157578453F, 0.0F, 0.0F));

        PartDefinition left_eyelid_bottom = left_eyelid_base.addOrReplaceChild("left_eyelid_bottom",
                CubeListBuilder.create().texOffs(226, 38).addBox(0.0F, 0.0F, 0.0F, 4, 5, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, -19.0F, 0.6632251157578453F, 0.0F, 0.0F));

        PartDefinition mouth_bottom_front = neck_bottom_front.addOrReplaceChild("mouth_bottom_front",
                CubeListBuilder.create().texOffs(204, 227).addBox(-5.0F, -3.0F, 0.0F, 10, 3, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -10.0F, 0.0F, -0.589397688398485F, 0.0F, 0.0F));

        PartDefinition mouth_top_lip = head_top.addOrReplaceChild("mouth_top_lip",
                CubeListBuilder.create().texOffs(205, 191).addBox(-5.0F, -7.0F, 0.0F, 10, 7, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -8.8F, -17.3F, -0.7285004297824331F, 0.0F, 0.0F));

        PartDefinition head_back = head_top.addOrReplaceChild("head_back",
                CubeListBuilder.create().texOffs(211, 127).addBox(-5.0F, 0.0F, 0.0F, 10, 3, 9, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.01F, -14.0F, -2.6F, -0.665144977935039F, 0.0F, 0.0F));

        PartDefinition head_parts = head_top.addOrReplaceChild("head_parts",
                CubeListBuilder.create().texOffs(182, 97).addBox(0.0F, 0.0F, 0.0F, 7, 2, 4, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(4.9F, -14.3F, -7.0F, 0.008726646259971648F, 0.43458698374658805F, 0.7609635538695276F));

        PartDefinition head_parts_1 = head_top.addOrReplaceChild("head_parts_1",
                CubeListBuilder.create().texOffs(182, 111).addBox(-7.0F, 0.0F, 0.0F, 7, 2, 4, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-4.9F, -14.3F, -7.0F, 0.008726646259971648F, -0.43458698374658805F, -0.7609635538695276F));

        PartDefinition nose_main = head_top.addOrReplaceChild("nose_main",
                CubeListBuilder.create().texOffs(199, 0).addBox(-1.0F, 0.0F, 0.0F, 2, 7, 7, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -14.2F, -17.9F, 0.7682939367279039F, 0.0F, 0.0F));

        PartDefinition shape90 = shoulder_main_left.addOrReplaceChild("shape90",
                CubeListBuilder.create().texOffs(109, 0).addBox(0.0F, 0.0F, -6.0F, 5, 5, 6, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.01F, -7.0F, -7.0F, 0.9075712110370513F, 0.0F, 0.0F));

        PartDefinition shape91 = shoulder_main_left.addOrReplaceChild("shape91",
                CubeListBuilder.create().texOffs(143, 0).addBox(0.0F, 0.0F, 0.0F, 5, 5, 6, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.01F, -7.0F, 7.0F, -0.9075712110370513F, 0.0F, 0.0F));

        PartDefinition shape90_1 = shoulder_main_right.addOrReplaceChild("shape90_1",
                CubeListBuilder.create().texOffs(63, 0).addBox(0.0F, 0.0F, -6.0F, 5, 5, 6, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.01F, -7.0F, -7.0F, 0.9075712110370513F, 0.0F, 0.0F));

        PartDefinition shape91_1 = shoulder_main_right.addOrReplaceChild("shape91_1",
                CubeListBuilder.create().texOffs(29, 0).addBox(0.0F, 0.0F, 0.0F, 5, 5, 6, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.01F, -7.0F, 7.0F, -0.9075712110370513F, 0.0F, 0.0F));

        PartDefinition arm_left_rotation_point = arm_left_connection.addOrReplaceChild("arm_left_rotation_point",
                CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, -0.7155849933176751F, 0.0F, 0.0F));

        PartDefinition arm_right_rotation_point = arm_right_connection.addOrReplaceChild("arm_right_rotation_point",
                CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, -0.7155849933176751F, 0.0F, 0.0F));

        PartDefinition spike_6 = shoulder_bone_left.addOrReplaceChild("spike_6",
                CubeListBuilder.create().texOffs(225, 82).addBox(0.0F, -4.0F, 0.0F, 4, 4, 5, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.0F, 6.0F, -0.5F, 0.22689280275926282F, -0.06981317007977318F, 0.0F));

        PartDefinition spike_7 = shoulder_bone_left.addOrReplaceChild("spike_7",
                CubeListBuilder.create().texOffs(225, 73).addBox(0.0F, 0.0F, 0.0F, 4, 4, 4, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(8.0F, 6.0F, 0.0F, 0.0F, 0.10471975511965977F, 0.0F));

        PartDefinition spike_8 = shoulder_bone_left.addOrReplaceChild("spike_8",
                CubeListBuilder.create().texOffs(225, 62).addBox(0.0F, 0.0F, 0.0F, 4, 4, 5, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(4.0F, 12.0F, -0.5F, -0.40142572795869574F, 0.0F, 0.0F));

        PartDefinition spike_9 = shoulder_bone_left_1.addOrReplaceChild("spike_9",
                CubeListBuilder.create().texOffs(238, 77).addBox(-4.0F, -4.0F, 0.0F, 4, 4, 5, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-2.0F, 6.0F, -0.5F, 0.22689280275926282F, -0.06981317007977318F, 0.0F));

        PartDefinition spike_10 = shoulder_bone_left_1.addOrReplaceChild("spike_10",
                CubeListBuilder.create().texOffs(240, 68).addBox(-4.0F, 0.0F, 0.0F, 4, 4, 4, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-8.0F, 6.0F, 0.0F, 0.0F, -0.10471975511965977F, 0.0F));

        PartDefinition spike_11 = shoulder_bone_left_1.addOrReplaceChild("spike_11",
                CubeListBuilder.create().texOffs(238, 57).addBox(-4.0F, 0.0F, 0.0F, 4, 4, 5, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-4.0F, 12.0F, -0.5F, -0.40142572795869574F, 0.0F, 0.0F));

        PartDefinition leg_left_4 = leg_left_3.addOrReplaceChild("leg_left_4",
                CubeListBuilder.create().texOffs(0, 243).addBox(-4.5F, -8.3F, 0.0F, 9, 10, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 7.0F, 10.5F, 0.41887902047863906F, 0.0F, 0.0F));

        PartDefinition foot_left_front_slope = leg_left_3.addOrReplaceChild("foot_left_front_slope",
                CubeListBuilder.create().texOffs(0, 213).addBox(-2.0F, -2.0F, -7.0F, 4, 2, 7, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 7.0F, 8.0F, 0.3143337982841788F, 0.0F, 0.0F));

        PartDefinition leg_right_4 = leg_right_3.addOrReplaceChild("leg_right_4",
                CubeListBuilder.create().texOffs(46, 243).addBox(-4.5F, -8.3F, 0.0F, 9, 10, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 7.0F, 10.5F, 0.41887902047863906F, 0.0F, 0.0F));

        PartDefinition foot_right_front_slope = leg_right_3.addOrReplaceChild("foot_right_front_slope",
                CubeListBuilder.create().texOffs(46, 213).addBox(-2.0F, -2.0F, -7.0F, 4, 2, 7, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 7.0F, 8.0F, 0.3143337982841788F, 0.0F, 0.0F));

        PartDefinition right_eyelid_side_top = right_eyelid_top.addOrReplaceChild("right_eyelid_side_top",
                CubeListBuilder.create().texOffs(184, 20).addBox(-9.0F, -5.0F, 0.0F, 9, 5, 1, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-4.0F, -0.1F, 0.0F, -0.024434609527920613F, 0.41887902047863906F, 0.2792526803190927F));

        PartDefinition right_eyelid_side_bottom = right_eyelid_bottom.addOrReplaceChild("right_eyelid_side_bottom",
                CubeListBuilder.create().texOffs(196, 38).addBox(-6.0F, 0.0F, 0.0F, 6, 5, 1, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-4.0F, 0.1F, 0.0F, 0.024434609527920613F, 0.41887902047863906F, -0.2792526803190927F));

        PartDefinition left_eyelid_side_top = left_eyelid_top.addOrReplaceChild("left_eyelid_side_top",
                CubeListBuilder.create().texOffs(235, 20).addBox(0.0F, -5.0F, 0.0F, 9, 5, 1, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(4.0F, -0.1F, 0.0F, -0.024434609527920613F, -0.41887902047863906F, -0.2792526803190927F));

        PartDefinition left_eyelid_side_bottom = left_eyelid_bottom.addOrReplaceChild("left_eyelid_side_bottom",
                CubeListBuilder.create().texOffs(241, 38).addBox(0.0F, 0.0F, 0.0F, 6, 5, 1, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(4.0F, 0.1F, 0.0F, 0.024434609527920613F, -0.41887902047863906F, 0.2792526803190927F));

        PartDefinition mouth_bottom_left = mouth_bottom_front.addOrReplaceChild("mouth_bottom_left",
                CubeListBuilder.create().texOffs(231, 227).addBox(0.0F, 0.0F, 0.0F, 7, 3, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(5.0F, -2.8F, 0.0F, 0.0F, -0.6981317007977318F, 0.08726646259971647F));

        PartDefinition mouth_bottom_right = mouth_bottom_front.addOrReplaceChild("mouth_bottom_right",
                CubeListBuilder.create().texOffs(183, 227).addBox(-7.0F, 0.0F, 0.0F, 7, 3, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-5.0F, -2.8F, 0.0F, 0.0F, 0.6981317007977318F, -0.08726646259971647F));

        PartDefinition tooth = mouth_bottom_front.addOrReplaceChild("tooth",
                CubeListBuilder.create().texOffs(241, 253).addBox(-0.5F, -1.0F, -0.5F, 1, 1, 1, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -2.9F, 0.9F, -0.2617993877991494F, 0.0F, 0.0F));

        PartDefinition mouth_top_front = mouth_top_lip.addOrReplaceChild("mouth_top_front",
                CubeListBuilder.create().texOffs(205, 209).addBox(-5.0F, 0.0F, 0.0F, 10, 3, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5153957281139255F, 0.0F, 0.0F));

        PartDefinition head_left = head_back.addOrReplaceChild("head_left",
                CubeListBuilder.create().texOffs(205, 92).addBox(1.1F, -0.2F, 0.0F, 9, 2, 9, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(4.3F, -0.8F, 0.0F, -0.05480333851262195F, 0.0F, 0.8604473212332043F));

        PartDefinition head_right = head_back.addOrReplaceChild("head_right",
                CubeListBuilder.create().texOffs(205, 106).addBox(-10.1F, -0.2F, 0.0F, 9, 2, 9, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-4.3F, -0.8F, 0.0F, -0.05480333851262195F, 0.0F, -0.8604473212332043F));

        PartDefinition head_back_cover = head_back.addOrReplaceChild("head_back_cover",
                CubeListBuilder.create().texOffs(181, 118).addBox(-5.0F, 0.0F, 0.0F, 10, 3, 8, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 9.0F, -0.9395107363485474F, 0.0F, 0.0F));

        PartDefinition head_parts_2 = head_parts.addOrReplaceChild("head_parts_2",
                CubeListBuilder.create().texOffs(167, 90).addBox(0.0F, 0.0F, -5.5F, 2, 1, 6, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.48520153205442357F, 0.03822271061867581F));

        PartDefinition head_parts_4 = head_parts_1.addOrReplaceChild("head_parts_4",
                CubeListBuilder.create().texOffs(167, 104).addBox(-2.0F, 0.0F, -5.5F, 2, 1, 6, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.48520153205442357F, -0.03822271061867581F));

        PartDefinition nose_bottom = nose_main.addOrReplaceChild("nose_bottom",
                CubeListBuilder.create().texOffs(242, 6).addBox(-1.0F, 0.0F, -5.0F, 2, 2, 5, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.01F, 1.128355361414334F, 0.0F, 0.0F));

        PartDefinition nose_left = nose_main.addOrReplaceChild("nose_left",
                CubeListBuilder.create().texOffs(218, 0).addBox(0.0F, 0.0F, -1.0F, 4, 7, 2, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.4F, 0.2F, 6.0F, 0.0F, 0.3839724354387525F, 0.0F));

        PartDefinition nose_right = nose_main.addOrReplaceChild("nose_right",
                CubeListBuilder.create().texOffs(186, 0).addBox(-4.0F, 0.0F, -1.0F, 4, 7, 2, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-0.4F, 0.2F, 6.0F, 0.0F, -0.3839724354387525F, 0.0F));

        PartDefinition nose_top = nose_main.addOrReplaceChild("nose_top",
                CubeListBuilder.create().texOffs(190, 10).addBox(-1.0F, 0.0F, -2.0F, 2, 7, 2, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 2.81F, 7.0F, 0.3270747018237373F, 0.0F, 0.0F));

        PartDefinition nose_front = nose_main.addOrReplaceChild("nose_front",
                CubeListBuilder.create().texOffs(223, 8).addBox(-1.0F, 0.0F, 0.0F, 2, 2, 7, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.01F, 0.0F, 0.0F, 0.2734930937875114F, 0.0F, 0.0F));

        PartDefinition shape92 = shape90.addOrReplaceChild("shape92",
                CubeListBuilder.create().texOffs(98, 11).addBox(0.0F, 0.0F, -9.0F, 5, 5, 9, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.01F, 0.0F, -6.0F, 0.6632251157578453F, 0.0F, 0.0F));

        PartDefinition shape93 = shape91.addOrReplaceChild("shape93",
                CubeListBuilder.create().texOffs(148, 11).addBox(0.0F, 0.0F, 0.0F, 5, 5, 9, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.01F, 0.0F, 6.0F, -0.6632251157578453F, 0.0F, 0.0F));

        PartDefinition shape92_1 = shape90_1.addOrReplaceChild("shape92_1",
                CubeListBuilder.create().texOffs(68, 11).addBox(0.0F, 0.0F, -9.0F, 5, 5, 9, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.01F, 0.0F, -6.0F, 0.6632251157578453F, 0.0F, 0.0F));

        PartDefinition shape93_1 = shape91_1.addOrReplaceChild("shape93_1",
                CubeListBuilder.create().texOffs(16, 11).addBox(0.0F, 0.0F, 0.0F, 5, 5, 9, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.01F, 0.0F, 6.0F, -0.6632251157578453F, 0.0F, 0.0F));

        PartDefinition arm_left_main = arm_left_rotation_point.addOrReplaceChild("arm_left_main",
                CubeListBuilder.create().texOffs(92, 125).addBox(0.0F, -6.0F, -6.0F, 7, 12, 12, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0016444577195458F, 0.0F, 0.0F));

        PartDefinition arm_right_main = arm_right_rotation_point.addOrReplaceChild("arm_right_main",
                CubeListBuilder.create().texOffs(141, 125).addBox(-7.0F, -6.0F, -6.0F, 7, 12, 12, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0016444577195458F, 0.0F, 0.0F));

        PartDefinition foot_left_back = leg_left_4.addOrReplaceChild("foot_left_back",
                CubeListBuilder.create().texOffs(25, 252).addBox(-2.0F, -2.0F, -1.0F, 4, 2, 2, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -8.3F, 2.0F));

        PartDefinition foot_right_back = leg_right_4.addOrReplaceChild("foot_right_back",
                CubeListBuilder.create().texOffs(71, 252).addBox(-2.0F, -2.0F, -1.0F, 4, 2, 2, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -8.3F, 2.0F));

        PartDefinition tooth_1 = mouth_bottom_left.addOrReplaceChild("tooth_1",
                CubeListBuilder.create().texOffs(231, 253).addBox(-0.5F, -1.0F, -0.5F, 1, 1, 1, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(4.0F, 0.1F, 1.4F, -0.17453292519943295F, 0.0F, 0.0F));

        PartDefinition neck_bottom_left = mouth_bottom_left.addOrReplaceChild("neck_bottom_left",
                CubeListBuilder.create().texOffs(232, 217).addBox(0.0F, 0.0F, 0.0F, 7, 6, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.3F, 3.1F, 0.0F, 0.3839724354387525F, -0.023212879051524582F, -0.28797932657906433F));

        PartDefinition tooth_2 = mouth_bottom_right.addOrReplaceChild("tooth_2",
                CubeListBuilder.create().texOffs(251, 253).addBox(-0.5F, -1.0F, -0.5F, 1, 1, 1, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-4.0F, 0.1F, 1.4F, -0.17453292519943295F, 0.0F, 0.0F));

        PartDefinition neck_bottom_right = mouth_bottom_right.addOrReplaceChild("neck_bottom_right",
                CubeListBuilder.create().texOffs(184, 217).addBox(-7.0F, 0.0F, 0.0F, 7, 6, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-0.3F, 3.1F, 0.0F, 0.3839724354387525F, 0.023212879051524582F, 0.28797932657906433F));

        PartDefinition tooth_3 = tooth.addOrReplaceChild("tooth_3",
                CubeListBuilder.create().texOffs(236, 252).addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(4.0F, 0.0F, 0.5F, 0.0F, -0.4363323129985824F, 0.13962634015954636F));

        PartDefinition tooth_4 = tooth.addOrReplaceChild("tooth_4",
                CubeListBuilder.create().texOffs(246, 252).addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-4.0F, 0.0F, 0.5F, 0.0F, 0.4363323129985824F, -0.13962634015954636F));

        PartDefinition mouth_top_left = mouth_top_front.addOrReplaceChild("mouth_top_left",
                CubeListBuilder.create().texOffs(232, 209).addBox(0.0F, 0.0F, 0.0F, 7, 3, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(5.0F, -0.1F, 0.0F, 0.0F, -0.6981317007977318F, 0.08726646259971647F));

        PartDefinition mouth_top_right = mouth_top_front.addOrReplaceChild("mouth_top_right",
                CubeListBuilder.create().texOffs(184, 209).addBox(-7.0F, 0.0F, 0.0F, 7, 3, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-5.0F, -0.1F, 0.0F, 0.0F, 0.6981317007977318F, -0.08726646259971647F));

        PartDefinition tooth_5 = mouth_top_front.addOrReplaceChild("tooth_5",
                CubeListBuilder.create().texOffs(246, 249).addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, new CubeDeformation(0.0F)),
                PartPose.offset(-2.4F, 3.0F, 1.5F));

        PartDefinition tooth_6 = mouth_top_front.addOrReplaceChild("tooth_6",
                CubeListBuilder.create().texOffs(241, 249).addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, new CubeDeformation(0.0F)),
                PartPose.offset(2.4F, 3.0F, 1.5F));

        PartDefinition head_parts_3 = head_parts_2.addOrReplaceChild("head_parts_3",
                CubeListBuilder.create().texOffs(167, 98).addBox(0.0F, 0.0F, -4.0F, 3, 1, 4, new CubeDeformation(0.0F)),
                PartPose.offset(2.0F, 0.0F, -0.4F));

        PartDefinition head_parts_5 = head_parts_4.addOrReplaceChild("head_parts_5",
                CubeListBuilder.create().texOffs(167, 112).addBox(-3.0F, 0.0F, -4.0F, 3, 1, 4, new CubeDeformation(0.0F)),
                PartPose.offset(-2.0F, 0.0F, -0.4F));

        PartDefinition nose_left_2 = nose_left.addOrReplaceChild("nose_left_2",
                CubeListBuilder.create().texOffs(231, 0).addBox(0.0F, 0.0F, -2.0F, 3, 6, 2, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(4.0F, 0.01F, 1.0F, 0.0F, 1.0821041362364843F, 0.0F));

        PartDefinition nose_right_2 = nose_right.addOrReplaceChild("nose_right_2",
                CubeListBuilder.create().texOffs(175, 0).addBox(-3.0F, 0.0F, -2.0F, 3, 6, 2, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-4.0F, 0.01F, 1.01F, 0.0F, -1.0821041362364843F, 0.0F));

        PartDefinition nose_tip = nose_front.addOrReplaceChild("nose_tip",
                CubeListBuilder.create().texOffs(218, 10).addBox(-1.0F, 0.0F, 0.0F, 2, 2, 2, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.01F, 0.0F, 7.0F, -0.2848377339254746F, 0.0F, 0.0F));

        PartDefinition shape95 = shape92.addOrReplaceChild("shape95",
                CubeListBuilder.create().texOffs(97, 26).addBox(0.0F, 0.0F, -6.0F, 5, 5, 6, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.01F, 0.0F, -9.0F, 0.9075712110370513F, 0.0F, 0.0F));

        PartDefinition shape96 = shape93.addOrReplaceChild("shape96",
                CubeListBuilder.create().texOffs(155, 26).addBox(0.0F, 0.0F, 0.0F, 5, 5, 6, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.01F, 0.0F, 9.0F, -0.9075712110370513F, 0.0F, 0.0F));

        PartDefinition shape95_1 = shape92_1.addOrReplaceChild("shape95_1",
                CubeListBuilder.create().texOffs(74, 26).addBox(0.0F, 0.0F, -6.0F, 5, 5, 6, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.01F, 0.0F, -9.0F, 0.9075712110370513F, 0.0F, 0.0F));

        PartDefinition shape96_1 = shape93_1.addOrReplaceChild("shape96_1",
                CubeListBuilder.create().texOffs(16, 26).addBox(0.0F, 0.0F, 0.0F, 5, 5, 6, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.01F, 0.0F, 9.0F, -0.9075712110370513F, 0.0F, 0.0F));

        PartDefinition arm_left_1 = arm_left_main.addOrReplaceChild("arm_left_1",
                CubeListBuilder.create().texOffs(92, 150).addBox(-3.0F, 0.0F, -4.5F, 6, 18, 9, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(3.5F, 0.0F, 0.0F, 0.7853981633974483F, 0.0F, 0.0F));

        PartDefinition spike = arm_left_main.addOrReplaceChild("spike",
                CubeListBuilder.create().texOffs(119, 132).addBox(0.0F, -1.0F, -1.0F, 2, 2, 2, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(6.4F, -3.0F, -3.0F, -0.6283185307179586F, 0.3490658503988659F, -0.3490658503988659F));

        PartDefinition arm_right_1 = arm_right_main.addOrReplaceChild("arm_right_1",
                CubeListBuilder.create().texOffs(141, 150).addBox(-3.0F, 0.0F, -4.5F, 6, 18, 9, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.7853981633974483F, 0.0F, 0.0F));

        PartDefinition spike_3 = arm_right_main.addOrReplaceChild("spike_3",
                CubeListBuilder.create().texOffs(168, 132).addBox(-2.0F, -1.0F, -1.0F, 2, 2, 2, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-6.4F, -3.0F, -3.0F, -0.6283185307179586F, -0.3490658503988659F, 0.3490658503988659F));

        PartDefinition foot_left_back_slope = foot_left_back.addOrReplaceChild("foot_left_back_slope",
                CubeListBuilder.create().texOffs(25, 242).addBox(-1.5F, 0.0F, -7.0F, 3, 2, 7, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.25569073541716925F, 0.0F, 0.0F));

        PartDefinition foot_right_back_slope = foot_right_back.addOrReplaceChild("foot_right_back_slope",
                CubeListBuilder.create().texOffs(71, 242).addBox(-1.5F, 0.0F, -7.0F, 3, 2, 7, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.25569073541716925F, 0.0F, 0.0F));

        PartDefinition mouth_top_lips_left = mouth_top_left.addOrReplaceChild("mouth_top_lips_left",
                CubeListBuilder.create().texOffs(232, 191).addBox(0.0F, -7.0F, 0.0F, 7, 7, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3839724354387525F, -0.003490658503988659F, 0.26354471705114374F));

        PartDefinition mouth_top_left_back = mouth_top_left.addOrReplaceChild("mouth_top_left_back",
                CubeListBuilder.create().texOffs(211, 202).addBox(0.0F, 0.0F, 0.0F, 10, 3, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, -0.6370451769779303F, 0.0F));

        PartDefinition tooth_7 = mouth_top_left.addOrReplaceChild("tooth_7",
                CubeListBuilder.create().texOffs(236, 249).addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, new CubeDeformation(0.0F)),
                PartPose.offset(3.0F, 3.0F, 1.5F));

        PartDefinition mouth_top_lips_right = mouth_top_right.addOrReplaceChild("mouth_top_lips_right",
                CubeListBuilder.create().texOffs(184, 191).addBox(-7.0F, -7.0F, 0.0F, 7, 7, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3839724354387525F, 0.003490658503988659F, -0.26354471705114374F));

        PartDefinition mouth_top_right_back = mouth_top_right.addOrReplaceChild("mouth_top_right_back",
                CubeListBuilder.create().texOffs(184, 202).addBox(-10.0F, 0.0F, 0.0F, 10, 3, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-7.0F, 0.0F, 0.0F, 0.0F, 0.6370451769779303F, 0.0F));

        PartDefinition tooth_8 = mouth_top_right.addOrReplaceChild("tooth_8",
                CubeListBuilder.create().texOffs(251, 249).addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, new CubeDeformation(0.0F)),
                PartPose.offset(-3.0F, 3.0F, 1.5F));

        PartDefinition nose_left_3 = nose_left_2.addOrReplaceChild("nose_left_3",
                CubeListBuilder.create().texOffs(242, 0).addBox(0.0F, 0.0F, -2.0F, 5, 3, 2, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(3.0F, 0.0F, -0.01F, 0.0F, 0.0F, 0.8203047484373349F));

        PartDefinition nose_right_3 = nose_right_2.addOrReplaceChild("nose_right_3",
                CubeListBuilder.create().texOffs(160, 0).addBox(-5.0F, 0.0F, -2.0F, 5, 3, 2, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-3.0F, 0.0F, -0.01F, 0.0F, 0.0F, -0.8203047484373349F));

        PartDefinition shape97 = shape95.addOrReplaceChild("shape97",
                CubeListBuilder.create().texOffs(120, 20).addBox(0.0F, 0.0F, -12.0F, 5, 5, 12, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, -6.0F, 0.6632251157578453F, 0.0F, 0.0F));

        PartDefinition shape97_1 = shape95_1.addOrReplaceChild("shape97_1",
                CubeListBuilder.create().texOffs(39, 20).addBox(0.0F, 0.0F, -12.0F, 5, 5, 12, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, -6.0F, 0.6632251157578453F, 0.0F, 0.0F));

        PartDefinition arm_left_2 = arm_left_1.addOrReplaceChild("arm_left_2",
                CubeListBuilder.create().texOffs(92, 178).addBox(-4.5F, 0.0F, -6.0F, 9, 18, 12, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 15.5F, 0.0F, -0.4553564018453205F, 0.0F, 0.0F));

        PartDefinition arm_left_1_decoration = arm_left_1.addOrReplaceChild("arm_left_1_decoration",
                CubeListBuilder.create().texOffs(123, 177).addBox(-2.0F, -10.0F, 0.0F, 4, 10, 2, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 10.2F, -4.5F, 0.317649923862968F, 0.0F, 0.0F));

        PartDefinition arm_left_1_decoration_1 = arm_left_1.addOrReplaceChild("arm_left_1_decoration_1",
                CubeListBuilder.create().texOffs(123, 164).addBox(-2.0F, -10.0F, -2.0F, 4, 10, 2, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 10.2F, 4.5F, -0.317649923862968F, 0.0F, 0.0F));

        PartDefinition spike_1 = spike.addOrReplaceChild("spike_1",
                CubeListBuilder.create().texOffs(119, 127).addBox(0.0F, -1.0F, -1.0F, 2, 2, 2, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-2.0F, 2.8F, 3.4F, 0.0F, -0.17453292519943295F, 0.41887902047863906F));

        PartDefinition spike_2 = spike.addOrReplaceChild("spike_2",
                CubeListBuilder.create().texOffs(119, 122).addBox(0.0F, -1.0F, -1.0F, 2, 2, 2, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-2.0F, -1.7F, 3.2F, -0.4886921905584123F, -0.3490658503988659F, -0.20943951023931953F));

        PartDefinition arm_right_2 = arm_right_1.addOrReplaceChild("arm_right_2",
                CubeListBuilder.create().texOffs(141, 178).addBox(-4.5F, 0.0F, -6.0F, 9, 18, 12, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 15.5F, 0.0F, -0.4553564018453205F, 0.0F, 0.0F));

        PartDefinition arm_right_1_decoration = arm_right_1.addOrReplaceChild("arm_right_1_decoration",
                CubeListBuilder.create().texOffs(172, 177).addBox(-2.0F, -10.0F, 0.0F, 4, 10, 2, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 10.2F, -4.5F, 0.317649923862968F, 0.0F, 0.0F));

        PartDefinition arm_right_1_decoration_1 = arm_right_1.addOrReplaceChild("arm_right_1_decoration_1",
                CubeListBuilder.create().texOffs(172, 164).addBox(-2.0F, -10.0F, -2.0F, 4, 10, 2, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 10.2F, 4.5F, -0.317649923862968F, 0.0F, 0.0F));

        PartDefinition spike_4 = spike_3.addOrReplaceChild("spike_4",
                CubeListBuilder.create().texOffs(168, 127).addBox(-2.0F, -1.0F, -1.0F, 2, 2, 2, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.0F, 2.8F, 3.4F, 0.0F, -0.17453292519943295F, -0.41887902047863906F));

        PartDefinition spike_5 = spike_3.addOrReplaceChild("spike_5",
                CubeListBuilder.create().texOffs(168, 122).addBox(-2.0F, -1.0F, -1.0F, 2, 2, 2, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(1.2F, -1.7F, 3.2F, 0.4886921905584123F, 0.7890633548266364F, -0.20943951023931953F));

        PartDefinition mouth_skin_left = mouth_top_left_back.addOrReplaceChild("mouth_skin_left",
                CubeListBuilder.create().texOffs(228, 241).addBox(0.0F, -2.0F, 0.0F, 7, 2, 1, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 3.0F, 1.5F, 0.0F, 0.04537856055185257F, 0.4293509959906051F));

        PartDefinition shape62 = mouth_top_left_back.addOrReplaceChild("shape62",
                CubeListBuilder.create().texOffs(206, 183).addBox(0.0F, -5.0F, 0.0F, 8, 5, 2, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.7F, 0.0F, 0.5F, -0.0715584993317675F, 0.0F, 0.41015237421866746F));

        PartDefinition mouth_skin_right = mouth_top_right_back.addOrReplaceChild("mouth_skin_right",
                CubeListBuilder.create().texOffs(228, 245).addBox(-7.0F, -2.0F, 0.0F, 7, 2, 1, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 3.0F, 1.5F, 0.0F, -0.04537856055185257F, -0.4642575810304917F));

        PartDefinition shape72 = mouth_top_right_back.addOrReplaceChild("shape72",
                CubeListBuilder.create().texOffs(185, 183).addBox(-8.0F, -5.0F, 0.0F, 8, 5, 2, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-0.7F, 0.0F, 0.5F, -0.0715584993317675F, 0.0F, -0.41015237421866746F));

        PartDefinition hand_left_main = arm_left_2.addOrReplaceChild("hand_left_main",
                CubeListBuilder.create().texOffs(106, 209).addBox(-1.0F, 0.0F, -5.0F, 4, 6, 10, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 18.0F, 0.0F, 0.0F, 1.5707963267948966F, 0.0F));

        PartDefinition hand_right_main = arm_right_2.addOrReplaceChild("hand_right_main",
                CubeListBuilder.create().texOffs(155, 209).addBox(-1.0F, 0.0F, -5.0F, 4, 6, 10, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 18.0F, 0.0F, 0.0F, 1.5707963267948966F, 0.0F));

        PartDefinition hand_left_back = hand_left_main.addOrReplaceChild("hand_left_back",
                CubeListBuilder.create().texOffs(92, 217).addBox(-1.0F, 0.0F, -4.5F, 2, 6, 9, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.0F, -0.1F, 0.0F, 0.0F, 0.0F, -0.24434609527920614F));

        PartDefinition hand_left_finger_right_1 = hand_left_main.addOrReplaceChild("hand_left_finger_right_1",
                CubeListBuilder.create().texOffs(92, 233).addBox(-2.0F, 0.0F, -1.5F, 3, 4, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(3.2F, 5.0F, -2.9F, 0.0F, 0.0F, -0.10471975511965977F));

        PartDefinition hand_left_finger_left_1 = hand_left_main.addOrReplaceChild("hand_left_finger_left_1",
                CubeListBuilder.create().texOffs(105, 233).addBox(-2.0F, 0.0F, -1.5F, 3, 4, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(3.2F, 5.0F, 2.8F, 0.0F, 0.0F, -0.10471975511965977F));

        PartDefinition hand_left_2 = hand_left_main.addOrReplaceChild("hand_left_2",
                CubeListBuilder.create().texOffs(118, 227).addBox(-2.7F, 0.0F, -3.5F, 4, 6, 7, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-1.0F, -0.2F, 0.0F, 0.0F, 0.0F, 0.19268434942017396F));

        PartDefinition hand_right_back = hand_right_main.addOrReplaceChild("hand_right_back",
                CubeListBuilder.create().texOffs(141, 217).addBox(-1.0F, 0.0F, -4.5F, 2, 6, 9, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.0F, -0.1F, 0.0F, 0.0F, 0.0F, -0.24434609527920614F));

        PartDefinition hand_right_finger_right_1 = hand_right_main.addOrReplaceChild("hand_right_finger_right_1",
                CubeListBuilder.create().texOffs(154, 233).addBox(-2.0F, 0.0F, -1.5F, 3, 4, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(3.2F, 5.0F, -2.9F, 0.0F, 0.0F, -0.10471975511965977F));

        PartDefinition hand_right_finger_left_1 = hand_right_main.addOrReplaceChild("hand_right_finger_left_1",
                CubeListBuilder.create().texOffs(141, 233).addBox(-2.0F, 0.0F, -1.5F, 3, 4, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(3.2F, 5.0F, 2.8F, 0.0F, 0.0F, -0.10471975511965977F));

        PartDefinition hand_right_2 = hand_right_main.addOrReplaceChild("hand_right_2",
                CubeListBuilder.create().texOffs(167, 227).addBox(-2.7F, 0.0F, -3.5F, 4, 6, 7, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-1.0F, -0.2F, 0.0F, 0.0F, 0.0F, 0.19268434942017396F));

        PartDefinition hand_left_finger_right_2 = hand_left_finger_right_1.addOrReplaceChild("hand_left_finger_right_2",
                CubeListBuilder.create().texOffs(92, 241).addBox(-3.0F, 0.0F, -1.5F, 3, 4, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(1.0F, 4.0F, 0.01F, 0.0F, 0.0F, 0.4886921905584123F));

        PartDefinition hand_left_finger_left_2 = hand_left_finger_left_1.addOrReplaceChild("hand_left_finger_left_2",
                CubeListBuilder.create().texOffs(105, 241).addBox(-3.0F, 0.0F, -1.5F, 3, 4, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(1.0F, 4.0F, -0.01F, 0.0F, 0.0F, 0.4886921905584123F));

        PartDefinition hand_left_thumb_1 = hand_left_2.addOrReplaceChild("hand_left_thumb_1",
                CubeListBuilder.create().texOffs(118, 241).addBox(-2.0F, 0.0F, -1.5F, 3, 4, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-1.5F, 4.5F, 0.0F, 0.0F, 3.141592653589793F, 1.0618583169133502F));

        PartDefinition hand_right_finger_right_2 = hand_right_finger_right_1.addOrReplaceChild("hand_right_finger_right_2",
                CubeListBuilder.create().texOffs(154, 241).addBox(-3.0F, 0.0F, -1.5F, 3, 4, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(1.0F, 4.0F, 0.01F, 0.0F, 0.0F, 0.4886921905584123F));

        PartDefinition hand_right_finger_left_2 = hand_right_finger_left_1.addOrReplaceChild("hand_right_finger_left_2",
                CubeListBuilder.create().texOffs(141, 241).addBox(-3.0F, 0.0F, -1.5F, 3, 4, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(1.0F, 4.0F, -0.01F, 0.0F, 0.0F, 0.4886921905584123F));

        PartDefinition hand_right_thumb_1 = hand_right_2.addOrReplaceChild("hand_right_thumb_1",
                CubeListBuilder.create().texOffs(167, 241).addBox(-2.0F, 0.0F, -1.5F, 3, 4, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-1.5F, 4.5F, 0.0F, 0.0F, 3.141592653589793F, 1.0618583169133502F));

        PartDefinition hand_left_finger_right_3 = hand_left_finger_right_2.addOrReplaceChild("hand_left_finger_right_3",
                CubeListBuilder.create().texOffs(92, 249).addBox(-3.0F, 0.0F, -1.5F, 3, 4, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 4.0F, 0.01F, 0.0F, 0.0F, 0.2792526803190927F));

        PartDefinition hand_left_finger_left_3 = hand_left_finger_left_2.addOrReplaceChild("hand_left_finger_left_3",
                CubeListBuilder.create().texOffs(105, 249).addBox(-3.0F, 0.0F, -1.5F, 3, 4, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 4.0F, -0.01F, 0.0F, 0.0F, 0.2792526803190927F));

        PartDefinition hand_left_thumb_2 = hand_left_thumb_1.addOrReplaceChild("hand_left_thumb_2",
                CubeListBuilder.create().texOffs(118, 249).addBox(-3.0F, 0.0F, -1.5F, 3, 4, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(1.0F, 4.0F, 0.01F, 0.0F, 0.0F, 0.7686430025783028F));

        PartDefinition hand_right_finger_right_3 = hand_right_finger_right_2.addOrReplaceChild("hand_right_finger_right_3",
                CubeListBuilder.create().texOffs(154, 249).addBox(-3.0F, 0.0F, -1.5F, 3, 4, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 4.0F, 0.01F, 0.0F, 0.0F, 0.2792526803190927F));

        PartDefinition hand_right_finger_left_3 = hand_right_finger_left_2.addOrReplaceChild("hand_right_finger_left_3",
                CubeListBuilder.create().texOffs(141, 249).addBox(-3.0F, 0.0F, -1.5F, 3, 4, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 4.0F, -0.01F, 0.0F, 0.0F, 0.2792526803190927F));

        PartDefinition hand_right_thumb_2 = hand_right_thumb_1.addOrReplaceChild("hand_right_thumb_2",
                CubeListBuilder.create().texOffs(167, 249).addBox(-3.0F, 0.0F, -1.5F, 3, 4, 3, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(1.0F, 4.0F, 0.01F, 0.0F, 0.0F, 0.7686430025783028F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        revertToDefaultBoxValues();

        if(entity.isAnimationPlaying(EndTrollEntity.BLANK_ANIMATION) || entity.isAnimationPlaying(EndTrollEntity.SHOOT_ANIMATION) || entity.isAnimationPlaying(EndTrollEntity.RIGHT_PUNCH_ANIMATION) || entity.isAnimationPlaying(EndTrollEntity.LEFT_PUNCH_ANIMATION) || entity.isAnimationPlaying(EndTrollEntity.DOUBLE_PUNCH_ANIMATION))
        {
            if(!entity.isEntityStanding())
            {
                if(entity.isEntityMovingHorizontally())//Entity moving while not standing
                {
                    float globalSpeed = 4.0F;
                    float globalHeight = 1.0F;
                    float globalDegree = 1.6F;

                    bounce(bottom_body, globalSpeed * 0.4F, globalHeight * 0.8F, false, limbSwing, limbSwingAmount);
                    //Left Leg
                    swing(leg_left_1, globalSpeed * 0.2F, globalDegree * 0.22F, false, 2.2F, 0.0F, limbSwing, limbSwingAmount);
                    swing(leg_left_2, globalSpeed * 0.2F, globalDegree * 0.25F, false, 2.7F, 0.0F, limbSwing, limbSwingAmount);
                    swing(leg_left_3, globalSpeed * 0.2F, globalDegree * 0.2F, true, 1.3F, 0.1F, limbSwing, limbSwingAmount);
                    swing(leg_left_4, globalSpeed * 0.2F, globalDegree * 0.28F, true, 3.3F, -0.03F, limbSwing, limbSwingAmount);
                    swing(foot_left_front_slope, globalSpeed * 0.2F, globalDegree * 0.2F, false, 1.0F, -0.12F, limbSwing, limbSwingAmount);
                    //Right Leg
                    swing(leg_right_1, globalSpeed * 0.2F, globalDegree * 0.22F, true, 2.2F, 0.0F, limbSwing, limbSwingAmount);
                    swing(leg_right_2, globalSpeed * 0.2F, globalDegree * 0.25F, true, 2.7F, 0.0F, limbSwing, limbSwingAmount);
                    swing(leg_right_3, globalSpeed * 0.2F, globalDegree * 0.2F, false, 1.3F, 0.1F, limbSwing, limbSwingAmount);
                    swing(leg_right_4, globalSpeed * 0.2F, globalDegree * 0.28F, false, 3.3F, -0.03F, limbSwing, limbSwingAmount);
                    swing(foot_right_front_slope, globalSpeed * 0.2F, globalDegree * 0.2F, true, 1.0F, -0.12F, limbSwing, limbSwingAmount);
                    //Torso
                    shake(torso, globalSpeed * 0.2F, globalDegree * 0.04F, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
                    swing(torso, globalSpeed * 0.4F, globalDegree * 0.02F, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
                    swing(stomach, globalSpeed * 0.4F, globalDegree * 0.02F, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);

                    //Left Arm
                    swing(arm_left_main, globalSpeed * 0.2F, globalDegree * 0.35F, false, -0.4F, 0.1F, limbSwing, limbSwingAmount);
                    swing(arm_left_2, globalSpeed * 0.2F, globalDegree * 0.35F, false, 2.0F, -0.08F, limbSwing, limbSwingAmount);
                    //Left Thumb
                    flap(hand_left_thumb_1, globalSpeed * 0.2F, globalDegree * 0.4F, true, 1.0F, -0.2F, limbSwing, limbSwingAmount);
                    flap(hand_left_thumb_2, globalSpeed * 0.2F, globalDegree * 0.4F, false, 1.0F, 0.1F, limbSwing, limbSwingAmount);
                    //Left Fingers
                    flap(hand_left_finger_left_1, globalSpeed * 0.2F, globalDegree * 0.2F, false, 1.7F, 0.0F, limbSwing, limbSwingAmount);
                    flap(hand_left_finger_left_2, globalSpeed * 0.2F, globalDegree * 0.35F, false, 1.0F, 0.2F, limbSwing, limbSwingAmount);
                    flap(hand_left_finger_left_3, globalSpeed * 0.2F, globalDegree * 0.3F, false, 1.0F, 0.2F, limbSwing, limbSwingAmount);

                    flap(hand_left_finger_right_1, globalSpeed * 0.2F, globalDegree * 0.2F, false, 1.2F, 0.0F, limbSwing, limbSwingAmount);
                    flap(hand_left_finger_right_2, globalSpeed * 0.2F, globalDegree * 0.35F, false, 0.5F, 0.2F, limbSwing, limbSwingAmount);
                    flap(hand_left_finger_right_3, globalSpeed * 0.2F, globalDegree * 0.3F, false, 0.5F, 0.2F, limbSwing, limbSwingAmount);

                    //Right Arm
                    swing(arm_right_main, globalSpeed * 0.2F, globalDegree * 0.35F, true, -0.4F, 0.1F, limbSwing, limbSwingAmount);
                    swing(arm_right_2, globalSpeed * 0.2F, globalDegree * 0.35F, true, 2.0F, -0.08F, limbSwing, limbSwingAmount);
                    //Right Thumb
                    flap(hand_right_thumb_1, globalSpeed * 0.2F, globalDegree * 0.4F, false, 1.0F, -0.2F, limbSwing, limbSwingAmount);
                    flap(hand_right_thumb_2, globalSpeed * 0.2F, globalDegree * 0.4F, true, 1.0F, 0.1F, limbSwing, limbSwingAmount);
                    //Right Fingers
                    flap(hand_right_finger_left_1, globalSpeed * 0.2F, globalDegree * 0.2F, true, 1.5F, 0.0F, limbSwing, limbSwingAmount);
                    flap(hand_right_finger_left_2, globalSpeed * 0.2F, globalDegree * 0.35F, true, 0.5F, 0.2F, limbSwing, limbSwingAmount);
                    flap(hand_right_finger_left_3, globalSpeed * 0.2F, globalDegree * 0.3F, true, 0.5F, 0.2F, limbSwing, limbSwingAmount);

                    flap(hand_right_finger_right_1, globalSpeed * 0.2F, globalDegree * 0.2F, true, 1.7F, 0.0F, limbSwing, limbSwingAmount);
                    flap(hand_right_finger_right_2, globalSpeed * 0.2F, globalDegree * 0.35F, true, 1.0F, 0.2F, limbSwing, limbSwingAmount);
                    flap(hand_right_finger_right_3, globalSpeed * 0.2F, globalDegree * 0.3F, true, 1.0F, 0.2F, limbSwing, limbSwingAmount);

                    //Head
                    swing(head_top, globalSpeed * 0.4F, globalDegree * 0.05F, false, 0.0F, -0.08F, limbSwing, limbSwingAmount);
                }
                else //Entity is not moving while not standing
                {
                    limbSwing = entity.tickCount;
                    limbSwingAmount = 1;

                    float globalSpeed = 1.0F;
                    float globalHeight = 1.0F;
                    float globalDegree = 0.8F;

                    bounce(bottom_body, globalSpeed * 0.3F, globalHeight * 0.56F, false, limbSwing, limbSwingAmount);
                    swing(torso, globalSpeed * 0.3F, globalDegree * 0.006F, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
                    swing(stomach, globalSpeed * 0.3F, globalDegree * 0.006F, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);

                    this.leg_left_1.y = -1.4F;
                    bounce(leg_left_1, globalSpeed * 0.3F, globalHeight * -0.56F, false, limbSwing, limbSwingAmount);
                    flap(leg_left_top_decoration, globalSpeed * 0.3F, globalDegree * 0.14F, false, 1.4F, -0.1F, limbSwing, limbSwingAmount);

                    this.leg_right_1.y = -1.4F;
                    bounce(leg_right_1, globalSpeed * 0.3F, globalHeight * -0.56F, false, limbSwing, limbSwingAmount);
                    flap(leg_right_top_decoration, globalSpeed * 0.3F, globalDegree * 0.14F, true, 1.4F, 0.1F, limbSwing, limbSwingAmount);

                    swing(leg_left_1, globalSpeed * 0.3F, globalDegree * 0.05F, false, 1.2F, 0.0F, limbSwing, limbSwingAmount);
                    swing(leg_left_2, globalSpeed * 0.3F, globalDegree * 0.05F, true, 1.2F, -0.1F, limbSwing, limbSwingAmount);
                    swing(leg_left_3, globalSpeed * 0.3F, globalDegree * 0.03F, true, 1.2F, 0.105F, limbSwing, limbSwingAmount);
                    swing(leg_left_4, globalSpeed * 0.3F, globalDegree * 0.03F, false, 1.2F, 0.0F, limbSwing, limbSwingAmount);
                    swing(leg_left_calf_muscle, globalSpeed * 0.3F, globalDegree * 0.08F, false, 1.2F, 0.05F, limbSwing, limbSwingAmount);
                    swing(foot_left_front_slope, globalSpeed * 0.3F, globalDegree * 0.04F, true, 1.2F, -0.03F, limbSwing, limbSwingAmount);

                    swing(leg_right_1, globalSpeed * 0.3F, globalDegree * 0.05F, false, 1.2F, 0.0F, limbSwing, limbSwingAmount);
                    swing(leg_right_2, globalSpeed * 0.3F, globalDegree * 0.05F, true, 1.2F, -0.1F, limbSwing, limbSwingAmount);
                    swing(leg_right_3, globalSpeed * 0.3F, globalDegree * 0.03F, true, 1.2F, 0.105F, limbSwing, limbSwingAmount);
                    swing(leg_right_4, globalSpeed * 0.3F, globalDegree * 0.03F, false, 1.2F, 0.0F, limbSwing, limbSwingAmount);
                    swing(leg_right_calf_muscle, globalSpeed * 0.3F, globalDegree * 0.08F, false, 1.2F, 0.05F, limbSwing, limbSwingAmount);
                    swing(foot_right_front_slope, globalSpeed * 0.3F, globalDegree * 0.04F, true, 1.2F, -0.03F, limbSwing, limbSwingAmount);

                    swing(arm_left_main, globalSpeed * 0.3F, globalDegree * 0.02F, true, 1.2F, 0.02F, limbSwing, limbSwingAmount);
                    swing(arm_left_2, globalSpeed * 0.3F, globalDegree * 0.05F, false, 1.2F, 0.02F, limbSwing, limbSwingAmount);

                    swing(arm_right_main, globalSpeed * 0.3F, globalDegree * 0.02F, true, 1.2F, 0.02F, limbSwing, limbSwingAmount);
                    swing(arm_right_2, globalSpeed * 0.3F, globalDegree * 0.05F, false, 1.2F, 0.02F, limbSwing, limbSwingAmount);

                    flap(hand_left_finger_left_1, globalSpeed * 0.3F, globalDegree * 0.05F, false, 1.2F, -0.25F, limbSwing, limbSwingAmount);
                    flap(hand_left_finger_left_2, globalSpeed * 0.3F, globalDegree * 0.04F, true, 1.2F, 0.1F, limbSwing, limbSwingAmount);
                    flap(hand_left_finger_left_3, globalSpeed * 0.3F, globalDegree * 0.04F, true, 1.2F, 0.1F, limbSwing, limbSwingAmount);

                    flap(hand_left_finger_right_1, globalSpeed * 0.3F, globalDegree * 0.05F, false, 1.2F, -0.25F, limbSwing, limbSwingAmount);
                    flap(hand_left_finger_right_2, globalSpeed * 0.3F, globalDegree * 0.04F, true, 1.2F, 0.1F, limbSwing, limbSwingAmount);
                    flap(hand_left_finger_right_3, globalSpeed * 0.3F, globalDegree * 0.04F, true, 1.2F, 0.1F, limbSwing, limbSwingAmount);

                    flap(hand_left_thumb_1, globalSpeed * 0.3F, globalDegree * 0.1F, true, 1.2F, 0.0F, limbSwing, limbSwingAmount);
                    flap(hand_left_thumb_2, globalSpeed * 0.3F, globalDegree * 0.1F, true, 1.2F, 0.0F, limbSwing, limbSwingAmount);

                    flap(hand_right_finger_left_1, globalSpeed * 0.3F, globalDegree * 0.05F, false, 1.2F, -0.25F, limbSwing, limbSwingAmount);
                    flap(hand_right_finger_left_2, globalSpeed * 0.3F, globalDegree * 0.04F, true, 1.2F, 0.1F, limbSwing, limbSwingAmount);
                    flap(hand_right_finger_left_3, globalSpeed * 0.3F, globalDegree * 0.04F, true, 1.2F, 0.1F, limbSwing, limbSwingAmount);

                    flap(hand_right_finger_right_1, globalSpeed * 0.3F, globalDegree * 0.05F, false, 1.2F, -0.25F, limbSwing, limbSwingAmount);
                    flap(hand_right_finger_right_2, globalSpeed * 0.3F, globalDegree * 0.04F, true, 1.2F, 0.1F, limbSwing, limbSwingAmount);
                    flap(hand_right_finger_right_3, globalSpeed * 0.3F, globalDegree * 0.04F, true, 1.2F, 0.1F, limbSwing, limbSwingAmount);

                    flap(hand_right_thumb_1, globalSpeed * 0.3F, globalDegree * 0.1F, true, 1.2F, 0.0F, limbSwing, limbSwingAmount);
                    flap(hand_right_thumb_2, globalSpeed * 0.3F, globalDegree * 0.1F, true, 1.2F, 0.0F, limbSwing, limbSwingAmount);

                    swing(body_abs, globalSpeed * 0.3F, globalDegree * 0.08F, true, 1.6F, 0.0F, limbSwing, limbSwingAmount);
                    swing(body_abs_bottom, globalSpeed * 0.3F, globalDegree * 0.08F, false, 2.0F, 0.1F, limbSwing, limbSwingAmount);

                    swing(head_top, globalSpeed * 0.3F, globalDegree * 0.03F, false, 1.2F, -0.05F, limbSwing, limbSwingAmount);
                }
            }
            else //Standing Entity
            {
                //Adjustments to the rotations of the body parts when the entity is Standing
                this.movement_base.z = -20.0F;
                this.bottom_body.xRot = (float) Math.toRadians(-90F);
                this.leg_left_1.xRot = (float) Math.toRadians(56.5F);
                this.leg_right_1.xRot = (float) Math.toRadians(56.5F);
                this.stomach.xRot = (float) Math.toRadians(-8.0F);
                this.torso.xRot = (float) Math.toRadians(105.5F);
                this.hand_left_main.yRot = (float) Math.toRadians(0.0F);
                this.hand_right_main.yRot = (float) Math.toRadians(180.0F);
                this.left_eyelid_base.yRot = (float) Math.toRadians(-14.0F);
                this.right_eyelid_base.yRot = (float) Math.toRadians(14.0F);
                this.arm_left_main.xRot = (float) Math.toRadians(0.0F);
                this.arm_left_main.zRot = (float) Math.toRadians(-6.8F);
                this.arm_left_main.yRot = (float) Math.toRadians(6.8F);
                this.arm_right_main.xRot = (float) Math.toRadians(0.0F);
                this.arm_right_main.zRot = (float) Math.toRadians(6.8F);
                this.arm_right_main.yRot = (float) Math.toRadians(-6.8F);

                this.hand_left_thumb_1.zRot = (float) Math.toRadians(14.0F);
                this.hand_left_thumb_2.zRot = (float) Math.toRadians(18.0F);

                this.hand_right_thumb_1.zRot = (float) Math.toRadians(14.0F);
                this.hand_right_thumb_2.zRot = (float) Math.toRadians(18.0F);

                if(entity.isEntityMovingHorizontally())
                {
                    if(entity.isEntityStanding())
                    {
                        this.eye.yRot += (netHeadYaw * ((float)Math.PI / 180) / 1.2F);
                        this.eye.xRot += (headPitch * ((float)Math.PI / 180) / 1.2F);
                    }

                    float globalSpeed = 4.8F;
                    float globalHeight = 1.0F;
                    float globalDegree = 2.0F;

                    bounce(bottom_body, globalSpeed * 0.3F, globalHeight * 0.8F, false, limbSwing, limbSwingAmount);
                    swing(head_top, globalSpeed * 0.3F, globalDegree * 0.03F, false, 1.2F, -0.05F, limbSwing, limbSwingAmount);

                    shake(bottom_body, globalSpeed * 0.15F, globalHeight * 0.14F, true, 2.0F, 0.0F, limbSwing, limbSwingAmount);
                    flap(stomach, globalSpeed * 0.15F, globalHeight * 0.05F, false, 1.2F, 0.0F, limbSwing, limbSwingAmount);
                    flap(torso, globalSpeed * 0.15F, globalHeight * 0.05F, false, 1.2F, 0.0F, limbSwing, limbSwingAmount);

                    //Left Leg
                    swing(leg_left_1, globalSpeed * 0.15F, globalDegree * 0.22F, false, 2.2F, 0.0F, limbSwing, limbSwingAmount);
                    swing(leg_left_2, globalSpeed * 0.15F, globalDegree * 0.25F, false, 2.7F, 0.0F, limbSwing, limbSwingAmount);
                    swing(leg_left_3, globalSpeed * 0.15F, globalDegree * 0.2F, true, 1.3F, 0.1F, limbSwing, limbSwingAmount);
                    swing(leg_left_4, globalSpeed * 0.15F, globalDegree * 0.28F, true, 3.3F, -0.03F, limbSwing, limbSwingAmount);
                    swing(foot_left_front_slope, globalSpeed * 0.15F, globalDegree * 0.2F, false, 1.0F, -0.11F, limbSwing, limbSwingAmount);
                    //Right Leg
                    swing(leg_right_1, globalSpeed * 0.15F, globalDegree * 0.22F, true, 2.2F, 0.0F, limbSwing, limbSwingAmount);
                    swing(leg_right_2, globalSpeed * 0.15F, globalDegree * 0.25F, true, 2.7F, 0.0F, limbSwing, limbSwingAmount);
                    swing(leg_right_3, globalSpeed * 0.15F, globalDegree * 0.2F, false, 1.3F, 0.1F, limbSwing, limbSwingAmount);
                    swing(leg_right_4, globalSpeed * 0.15F, globalDegree * 0.28F, false, 3.3F, -0.03F, limbSwing, limbSwingAmount);
                    swing(foot_right_front_slope, globalSpeed * 0.15F, globalDegree * 0.2F, true, 1.0F, -0.11F, limbSwing, limbSwingAmount);

                    this.leg_left_1.z = -1.8F;
                    this.leg_right_1.z = -1.8F;

                    swing(stomach, globalSpeed * 0.3F, globalDegree * 0.02F, true, 1.2F, 0.0F, limbSwing, limbSwingAmount);
                    swing(torso, globalSpeed * 0.3F, globalDegree * 0.02F, true, 1.2F, 0.0F, limbSwing, limbSwingAmount);

                    swing(arm_left_main, globalSpeed * 0.15F, globalDegree * 0.1F, false, -0.5F, 0.0F, limbSwing, limbSwingAmount);
                    swing(arm_left_2, globalSpeed * 0.15F, globalDegree * 0.1F, false, -0.5F, 0.0F, limbSwing, limbSwingAmount);

                    swing(arm_right_main, globalSpeed * 0.15F, globalDegree * 0.1F, true, -0.5F, 0.0F, limbSwing, limbSwingAmount);
                    swing(arm_right_2, globalSpeed * 0.15F, globalDegree * 0.1F, true, -0.5F, 0.0F, limbSwing, limbSwingAmount);
                }
                else
                {
                    limbSwing = entity.tickCount;
                    limbSwingAmount = 1;

                    if(entity.isEntityStanding())
                    {
                        this.eye.yRot += (netHeadYaw * ((float)Math.PI / 180) / 1.2F);
                        this.eye.xRot += (headPitch * ((float)Math.PI / 180) / 1.2F);
                    }

                    float globalSpeed = 1.0F;
                    float globalHeight = 1.0F;
                    float globalDegree = 1.0F;

                    bounce(bottom_body, globalSpeed * 0.3F, globalHeight * 0.51F, false, limbSwing, limbSwingAmount);
                    swing(head_top, globalSpeed * 0.3F, globalDegree * 0.03F, false, 1.2F, -0.05F, limbSwing, limbSwingAmount);

                    bounce(leg_left_1, globalSpeed * 0.3F, globalHeight * -0.2F, false, limbSwing, limbSwingAmount);
                    swing(leg_left_1, globalSpeed * 0.3F, globalDegree * 0.03F, true, 1.3F, -0.01F, limbSwing, limbSwingAmount);
                    swing(leg_left_2, globalSpeed * 0.3F, globalDegree * 0.03F, false, 1.3F, -0.01F, limbSwing, limbSwingAmount);
                    swing(leg_left_3, globalSpeed * 0.3F, globalDegree * 0.03F, false, 1.3F, 0.00F, limbSwing, limbSwingAmount);
                    swing(leg_left_4, globalSpeed * 0.3F, globalDegree * 0.03F, true, 1.3F, 0.00F, limbSwing, limbSwingAmount);

                    bounce(leg_right_1, globalSpeed * 0.3F, globalHeight * -0.2F, false, limbSwing, limbSwingAmount);
                    swing(leg_right_1, globalSpeed * 0.3F, globalDegree * 0.03F, true, 1.3F, -0.01F, limbSwing, limbSwingAmount);
                    swing(leg_right_2, globalSpeed * 0.3F, globalDegree * 0.03F, false, 1.3F, -0.01F, limbSwing, limbSwingAmount);
                    swing(leg_right_3, globalSpeed * 0.3F, globalDegree * 0.03F, false, 1.3F, 0.00F, limbSwing, limbSwingAmount);
                    swing(leg_right_4, globalSpeed * 0.3F, globalDegree * 0.03F, true, 1.3F, 0.00F, limbSwing, limbSwingAmount);

                    swing(stomach, globalSpeed * 0.3F, globalDegree * 0.01F, true, 1.2F, 0.0F, limbSwing, limbSwingAmount);
                    swing(torso, globalSpeed * 0.3F, globalDegree * 0.02F, false, 1.2F, 0.0F, limbSwing, limbSwingAmount);

                    swing(body_abs_bottom, globalSpeed * 0.3F, globalDegree * 0.04F, true, 1.2F, 0.05F, limbSwing, limbSwingAmount);

                    swing(arm_left_main, globalSpeed * 0.3F, globalDegree * 0.01F, false, 1.2F, 0.0F, limbSwing, limbSwingAmount);
                    swing(arm_left_2, globalSpeed * 0.3F, globalDegree * 0.01F, false, 1.0F, 0.0F, limbSwing, limbSwingAmount);
                    swing(arm_right_main, globalSpeed * 0.3F, globalDegree * 0.01F, false, 1.2F, 0.0F, limbSwing, limbSwingAmount);
                    swing(arm_right_2, globalSpeed * 0.3F, globalDegree * 0.01F, false, 1.0F, 0.0F, limbSwing, limbSwingAmount);
                }
            }
        }

        if(entity.isAnimationPlaying(EndTrollEntity.TRANSFORM_ANIMATION))
        {
            limbSwing = entity.tickCount;
            limbSwingAmount = 1;

            swing(head_top, 0.3F, 0.03F, false, 1.2F, -0.05F, limbSwing, limbSwingAmount);

            if(entity.getAnimationTick() > 0 && entity.getAnimationTick() <= 19)
            {
                shake(left_eyelid_base, 2.0F, 0.005F, false, 0.0F, 0.02F, limbSwing, limbSwingAmount);
                shake(right_eyelid_base, 2.0F, 0.005F, true, 0.0F, -0.02F, limbSwing, limbSwingAmount);
            }
        }
        if(entity.isAnimationPlaying(EndTrollEntity.SCREAM_ANIMATION))
        {
            limbSwing = entity.tickCount;
            limbSwingAmount = 1;

            swing(head_top, 0.3F, 0.03F, false, 1.2F, -0.05F, limbSwing, limbSwingAmount);
        }

        this.animateModel(entity);
    }

    @Override
    public void animateModel(T animatedEntity) {
        this.animator.updateAnimations(animatedEntity);

        if(animatedEntity.isAnimationPlaying(EndTrollEntity.TRANSFORM_ANIMATION))
        {
            this.animator.setAnimationToPlay(EndTrollEntity.TRANSFORM_ANIMATION);

            this.animator.startKeyframe(6);
            this.animator.move(movement_base, 0, 4.0F, 0);
            this.animator.rotate(leg_left_2, 0.4F, 0, 0);
            this.animator.rotate(leg_right_2, 0.4F, 0, 0);
            this.animator.rotate(leg_left_3, -0.4F, 0, 0);
            this.animator.rotate(leg_right_3, -0.4F, 0, 0);
            this.animator.rotate(arm_left_2, -0.2F, 0, 0);
            this.animator.rotate(arm_right_2, -0.2F, 0, 0);
            this.animator.endKeyframe();

            this.animator.startKeyframe(10);
            this.animator.move(movement_base, 0, -4, -20.0F);
            this.animator.rotate(bottom_body, -1.6F, 0, 0);
            this.animator.rotate(leg_left_1, 1.6F, 0, 0);
            this.animator.rotate(leg_right_1, 1.6F, 0, 0);
            this.animator.rotate(leg_left_2, 0.4F, 0, 0);
            this.animator.rotate(leg_right_2, 0.4F, 0, 0);
            this.animator.rotate(leg_left_3, -0.4F, 0, 0);
            this.animator.rotate(leg_right_3, -0.4F, 0, 0);
            this.animator.rotate(stomach, 0.4F, 0, 0);
            this.animator.rotate(torso, 0.6F, 0, 0);
            this.animator.rotate(hand_left_main, 0, -1.6F, 0);
            this.animator.rotate(hand_right_main, 0, 1.6F, 0);
            this.animator.rotate(arm_left_main, 1.0F, 0.12F, -0.12F);
            this.animator.rotate(arm_right_main, 1.0F, -0.12F, 0.12F);
            this.animator.rotate(arm_left_2, -0.2F, 0, 0);
            this.animator.rotate(arm_right_2, -0.2F, 0, 0);

            this.animator.rotate(hand_left_thumb_1, 0, 0, -0.8F);
            this.animator.rotate(hand_left_thumb_2, 0, 0, -0.4F);
            this.animator.rotate(hand_right_thumb_1, 0, 0, -0.8F);
            this.animator.rotate(hand_right_thumb_2, 0, 0, -0.4F);
            this.animator.endKeyframe();

            this.animator.startKeyframe(3);
            this.animator.move(movement_base, 0, 0, -20.0F);
            this.animator.rotate(bottom_body, -1.6F, 0, 0);
            this.animator.rotate(leg_left_1, 1.6F, 0, 0);
            this.animator.rotate(leg_right_1, 1.6F, 0, 0);
            this.animator.rotate(leg_left_2, 0.0F, 0, 0);
            this.animator.rotate(leg_right_2, 0.0F, 0, 0);
            this.animator.rotate(leg_left_3, 0.0F, 0, 0);
            this.animator.rotate(leg_right_3, 0.0F, 0, 0);
            this.animator.rotate(stomach, 0.4F, 0, 0);
            this.animator.rotate(torso, 0.6F, 0, 0);
            this.animator.rotate(hand_left_main, 0, -1.6F, 0);
            this.animator.rotate(hand_right_main, 0, 1.6F, 0);
            this.animator.rotate(arm_left_main, 1.0F, 0.12F, -0.12F);
            this.animator.rotate(arm_right_main, 1.0F, -0.12F, 0.12F);
            this.animator.rotate(arm_left_2, 0.0F, 0, 0);
            this.animator.rotate(arm_right_2, 0.0F, 0, 0);

            this.animator.rotate(hand_left_thumb_1, 0, 0, -0.8F);
            this.animator.rotate(hand_left_thumb_2, 0, 0, -0.4F);
            this.animator.rotate(hand_right_thumb_1, 0, 0, -0.8F);
            this.animator.rotate(hand_right_thumb_2, 0, 0, -0.4F);
            this.animator.endKeyframe();

            this.animator.startKeyframe(1);
            this.animator.move(movement_base, 0, 0, -20.0F);
            this.animator.rotate(bottom_body, -1.6F, 0, 0);
            this.animator.rotate(leg_left_1, 1.6F, 0, 0);
            this.animator.rotate(leg_right_1, 1.6F, 0, 0);
            this.animator.rotate(leg_left_2, 0.0F, 0, 0);
            this.animator.rotate(leg_right_2, 0.0F, 0, 0);
            this.animator.rotate(leg_left_3, 0.0F, 0, 0);
            this.animator.rotate(leg_right_3, 0.0F, 0, 0);
            this.animator.rotate(stomach, 0.4F, 0, 0);
            this.animator.rotate(torso, 0.6F, 0, 0);
            this.animator.rotate(hand_left_main, 0, -1.6F, 0);
            this.animator.rotate(hand_right_main, 0, 1.6F, 0);
            this.animator.rotate(arm_left_main, 1.0F, 0.12F, -0.12F);
            this.animator.rotate(arm_right_main, 1.0F, -0.12F, 0.12F);
            this.animator.rotate(left_eyelid_base, 0, -0.3F, 0);
            this.animator.rotate(right_eyelid_base, 0, 0.3F, 0);
            this.animator.rotate(arm_left_2, 0.0F, 0, 0);
            this.animator.rotate(arm_right_2, 0.0F, 0, 0);

            this.animator.rotate(hand_left_thumb_1, 0, 0, -0.8F);
            this.animator.rotate(hand_left_thumb_2, 0, 0, -0.4F);
            this.animator.rotate(hand_right_thumb_1, 0, 0, -0.8F);
            this.animator.rotate(hand_right_thumb_2, 0, 0, -0.4F);
            this.animator.endKeyframe();
        }
        if(animatedEntity.isAnimationPlaying(EndTrollEntity.SCREAM_ANIMATION))
        {
            this.animator.setAnimationToPlay(EndTrollEntity.SCREAM_ANIMATION);

            this.animator.startKeyframe(0);
            this.animator.move(movement_base, 0, 0, -20.0F);
            this.animator.rotate(bottom_body, -1.6F, 0, 0);
            this.animator.rotate(leg_left_1, 1.6F, 0, 0);
            this.animator.rotate(leg_right_1, 1.6F, 0, 0);
            this.animator.rotate(leg_left_2, 0.0F, 0, 0);
            this.animator.rotate(leg_right_2, 0.0F, 0, 0);
            this.animator.rotate(leg_left_3, 0.0F, 0, 0);
            this.animator.rotate(leg_right_3, 0.0F, 0, 0);
            this.animator.rotate(stomach, 0.4F, 0, 0);
            this.animator.rotate(torso, 0.6F, 0, 0);
            this.animator.rotate(hand_left_main, 0, -1.6F, 0);
            this.animator.rotate(hand_right_main, 0, 1.6F, 0);
            this.animator.rotate(arm_left_main, 1.0F, 0.12F, -0.12F);
            this.animator.rotate(arm_right_main, 1.0F, -0.12F, 0.12F);
            this.animator.rotate(left_eyelid_base, 0, -0.3F, 0);
            this.animator.rotate(right_eyelid_base, 0, 0.3F, 0);
            this.animator.rotate(arm_left_2, 0.0F, 0, 0);
            this.animator.rotate(arm_right_2, 0.0F, 0, 0);
            this.animator.rotate(hand_left_thumb_1, 0, 0, -0.8F);
            this.animator.rotate(hand_left_thumb_2, 0, 0, -0.4F);
            this.animator.rotate(hand_right_thumb_1, 0, 0, -0.8F);
            this.animator.rotate(hand_right_thumb_2, 0, 0, -0.4F);
            this.animator.endKeyframe();

            this.animator.setStaticKeyframe(4);

            this.animator.startKeyframe(6);
            this.animator.move(movement_base, 0, 0, -20.0F);
            this.animator.rotate(bottom_body, -1.6F, 0, 0);
            this.animator.rotate(leg_left_1, 1.6F, 0, 0);
            this.animator.rotate(leg_right_1, 1.6F, 0, 0);
            this.animator.rotate(leg_left_2, 0.0F, 0, 0);
            this.animator.rotate(leg_right_2, 0.0F, 0, 0);
            this.animator.rotate(leg_left_3, 0.0F, 0, 0);
            this.animator.rotate(leg_right_3, 0.0F, 0, 0);
            this.animator.rotate(stomach, 0.6F, 0, 0);
            this.animator.rotate(torso, 0.9F, 0, 0);
            this.animator.rotate(hand_left_main, 0, -1.6F, 0);
            this.animator.rotate(hand_right_main, 0, 1.6F, 0);

            this.animator.rotate(arm_left_main, -0.1F, 0.12F, -0.12F);
            this.animator.rotate(arm_right_main, 0.1F, -0.12F, 0.12F);

            this.animator.rotate(arm_left_2, -0.9F, 0.7F, 0.8F);
            this.animator.rotate(arm_right_2, -0.9F, -0.7F, -0.7F);

            this.animator.rotate(left_eyelid_base, 0, -0.3F, 0);
            this.animator.rotate(right_eyelid_base, 0, 0.3F, 0);

            this.animator.rotate(hand_left_thumb_1, 0, 0, -0.8F);
            this.animator.rotate(hand_left_thumb_2, 0, 0, -0.4F);
            this.animator.rotate(hand_right_thumb_1, 0, 0, -0.8F);
            this.animator.rotate(hand_right_thumb_2, 0, 0, -0.4F);
            this.animator.endKeyframe();

            this.animator.startKeyframe(7);
            this.animator.move(movement_base, 0, 0, -20.0F);
            this.animator.rotate(bottom_body, -1.6F, 0, 0);
            this.animator.rotate(leg_left_1, 1.6F, 0, 0);
            this.animator.rotate(leg_right_1, 1.6F, 0, 0);
            this.animator.rotate(leg_left_2, 0.0F, 0, 0);
            this.animator.rotate(leg_right_2, 0.0F, 0, 0);
            this.animator.rotate(leg_left_3, 0.0F, 0, 0);
            this.animator.rotate(leg_right_3, 0.0F, 0, 0);
            this.animator.rotate(stomach, 0.2F, 0, 0);
            this.animator.rotate(torso, 0.2F, 0, 0);
            this.animator.rotate(hand_left_main, 0, -1.6F, 0);
            this.animator.rotate(hand_right_main, 0, 1.6F, 0);

            this.animator.rotate(arm_left_main, 1.5F, 0.6F, -0.6F);
            this.animator.rotate(arm_right_main, 1.5F, -0.6F, 0.6F);

            this.animator.rotate(arm_left_2, -0.8F, -0.4F, 0.4F);
            this.animator.rotate(arm_right_2, -0.8F, 0.4F, -0.4F);

            this.animator.rotate(head_top, -0.5F, 0, 0);

            this.animator.rotate(left_eyelid_base, 0, -0.3F, 0);
            this.animator.rotate(right_eyelid_base, 0, 0.3F, 0);

            this.animator.rotate(hand_left_thumb_1, 0, 0, -0.8F);
            this.animator.rotate(hand_left_thumb_2, 0, 0, -0.4F);
            this.animator.rotate(hand_right_thumb_1, 0, 0, -0.8F);
            this.animator.rotate(hand_right_thumb_2, 0, 0, -0.4F);
            this.animator.endKeyframe();

            this.animator.setStaticKeyframe(3);

            this.animator.startKeyframe(12);
            this.animator.move(movement_base, 0, 0, -20.0F);
            this.animator.rotate(bottom_body, -1.6F, 0, 0);
            this.animator.rotate(leg_left_1, 1.6F, 0, 0);
            this.animator.rotate(leg_right_1, 1.6F, 0, 0);
            this.animator.rotate(leg_left_2, 0.0F, 0, 0);
            this.animator.rotate(leg_right_2, 0.0F, 0, 0);
            this.animator.rotate(leg_left_3, 0.0F, 0, 0);
            this.animator.rotate(leg_right_3, 0.0F, 0, 0);
            this.animator.rotate(stomach, 0.4F, 0, 0);
            this.animator.rotate(torso, 0.6F, 0, 0);
            this.animator.rotate(hand_left_main, 0, -1.6F, 0);
            this.animator.rotate(hand_right_main, 0, 1.6F, 0);
            this.animator.rotate(arm_left_main, 1.0F, 0.12F, -0.12F);
            this.animator.rotate(arm_right_main, 1.0F, -0.12F, 0.12F);
            this.animator.rotate(left_eyelid_base, 0, -0.3F, 0);
            this.animator.rotate(right_eyelid_base, 0, 0.3F, 0);
            this.animator.rotate(arm_left_2, 0.0F, 0, 0);
            this.animator.rotate(arm_right_2, 0.0F, 0, 0);
            this.animator.rotate(hand_left_thumb_1, 0, 0, -0.8F);
            this.animator.rotate(hand_left_thumb_2, 0, 0, -0.4F);
            this.animator.rotate(hand_right_thumb_1, 0, 0, -0.8F);
            this.animator.rotate(hand_right_thumb_2, 0, 0, -0.4F);
            this.animator.endKeyframe();
        }
        if(animatedEntity.isAnimationPlaying(EndTrollEntity.SHOOT_ANIMATION))
        {
            this.animator.setAnimationToPlay(EndTrollEntity.SHOOT_ANIMATION);

            this.animator.startKeyframe(6);
            this.animator.rotate(stomach, -0.2F, 0, 0);
            this.animator.move(torso, 0, 0, -2);
            this.animator.rotate(torso, 0.1F, 0, 0);
            this.animator.rotate(arm_left_main, 0.3F, 0.12F, -0.12F);
            this.animator.rotate(arm_right_main, 0.3F, -0.12F, 0.12F);
            this.animator.rotate(arm_left_2, -0.8F, 0, 0);
            this.animator.rotate(arm_right_2, -0.8F, 0, 0);
            this.animator.endKeyframe();

            this.animator.setStaticKeyframe(1);

            this.animator.startKeyframe(5);
            this.animator.rotate(head_top, -0.5F, 0, 0);
            this.animator.move(stomach, 0, 0, -1);
            this.animator.rotate(stomach, 0.3F, 0, 0);
            this.animator.move(torso, 0, 0, -1);
            this.animator.rotate(torso, -0.2F, 0, 0);
            this.animator.rotate(arm_left_main, 0.8F, 0.12F, -0.12F);
            this.animator.rotate(arm_right_main, 0.8F, -0.12F, 0.12F);
            this.animator.rotate(arm_left_2, -0.1F, 0, 0);
            this.animator.rotate(arm_right_2, -0.1F, 0, 0);
            this.animator.endKeyframe();

            this.animator.setStaticKeyframe(1);
            this.animator.resetKeyframe(7);
        }
        if(animatedEntity.isAnimationPlaying(EndTrollEntity.RIGHT_PUNCH_ANIMATION))
        {
            this.animator.setAnimationToPlay(EndTrollEntity.RIGHT_PUNCH_ANIMATION);

            this.animator.startKeyframe(9);
            this.animator.rotate(torso, 0.2F, -0.2F, 0.3F);
            this.animator.rotate(stomach, -0.1F, 0, 0);
            this.animator.rotate(arm_right_main, 0.5F, -0.7F, 0.7F);
            this.animator.rotate(arm_right_2, -1.5F, 0, 0);

            this.animator.rotate(arm_left_main, -0.1F, 0.4F, -0.2F);
            this.animator.rotate(arm_left_2, -0.5F, 0, 0);

            this.animator.rotate(hand_right_finger_right_1, 0, 0, 0.2F);
            this.animator.rotate(hand_right_finger_right_2, 0, 0, 0.4F);
            this.animator.rotate(hand_right_finger_right_3, 0, 0, 0.4F);
            this.animator.rotate(hand_right_finger_left_1, 0, 0, 0.2F);
            this.animator.rotate(hand_right_finger_left_2, 0, 0, 0.4F);
            this.animator.rotate(hand_right_finger_left_3, 0, 0, 0.4F);
            this.animator.rotate(hand_right_thumb_1, 0, 0, -0.2F);
            this.animator.rotate(hand_right_thumb_2, 0, 0, 0.8F);
            this.animator.endKeyframe();

            this.animator.setStaticKeyframe(1);

            this.animator.startKeyframe(5);
            this.animator.rotate(torso, 0.2F, 0.4F, -0.4F);
            this.animator.rotate(stomach, 0.0F, 0, 0);

            this.animator.rotate(arm_right_main, -1.2F, -0.7F, 0.7F);
            this.animator.rotate(arm_right_2, 0.1F, 0, 0);

            this.animator.rotate(arm_left_main, 0.5F, 0.1F, -0.1F);
            this.animator.rotate(arm_left_2, -1.1F, 0.2F, -0.2F);

            this.animator.rotate(hand_right_finger_right_1, 0, 0, 0.2F);
            this.animator.rotate(hand_right_finger_right_2, 0, 0, 0.4F);
            this.animator.rotate(hand_right_finger_right_3, 0, 0, 0.4F);
            this.animator.rotate(hand_right_finger_left_1, 0, 0, 0.2F);
            this.animator.rotate(hand_right_finger_left_2, 0, 0, 0.4F);
            this.animator.rotate(hand_right_finger_left_3, 0, 0, 0.4F);
            this.animator.rotate(hand_right_thumb_1, 0, 0, -0.2F);
            this.animator.rotate(hand_right_thumb_2, 0, 0, 0.8F);
            this.animator.endKeyframe();

            this.animator.setStaticKeyframe(1);

            this.animator.resetKeyframe(12);
        }
        if(animatedEntity.isAnimationPlaying(EndTrollEntity.LEFT_PUNCH_ANIMATION))
        {
            this.animator.setAnimationToPlay(EndTrollEntity.LEFT_PUNCH_ANIMATION);

            this.animator.startKeyframe(9);
            this.animator.rotate(torso, 0.2F, 0.2F, -0.3F);
            this.animator.rotate(stomach, -0.1F, 0, 0);
            this.animator.rotate(arm_left_main, 0.5F, 0.7F, -0.7F);
            this.animator.rotate(arm_left_2, -1.5F, 0, 0);

            this.animator.rotate(arm_right_main, -0.1F, -0.4F, 0.2F);
            this.animator.rotate(arm_right_2, -0.5F, 0, 0);

            this.animator.rotate(hand_left_finger_right_1, 0, 0, 0.2F);
            this.animator.rotate(hand_left_finger_right_2, 0, 0, 0.4F);
            this.animator.rotate(hand_left_finger_right_3, 0, 0, 0.4F);
            this.animator.rotate(hand_left_finger_left_1, 0, 0, 0.2F);
            this.animator.rotate(hand_left_finger_left_2, 0, 0, 0.4F);
            this.animator.rotate(hand_left_finger_left_3, 0, 0, 0.4F);
            this.animator.rotate(hand_left_thumb_1, 0, 0, -0.2F);
            this.animator.rotate(hand_left_thumb_2, 0, 0, 0.8F);
            this.animator.endKeyframe();

            this.animator.setStaticKeyframe(1);

            this.animator.startKeyframe(5);
            this.animator.rotate(torso, 0.2F, -0.4F, 0.4F);
            this.animator.rotate(stomach, 0.0F, 0, 0);

            this.animator.rotate(arm_left_main, -1.2F, 0.7F, -0.7F);
            this.animator.rotate(arm_left_2, 0.1F, 0, 0);

            this.animator.rotate(arm_right_main, 0.5F, -0.1F, 0.1F);
            this.animator.rotate(arm_right_2, -1.1F, -0.2F, 0.2F);

            this.animator.rotate(hand_left_finger_right_1, 0, 0, 0.2F);
            this.animator.rotate(hand_left_finger_right_2, 0, 0, 0.4F);
            this.animator.rotate(hand_left_finger_right_3, 0, 0, 0.4F);
            this.animator.rotate(hand_left_finger_left_1, 0, 0, 0.2F);
            this.animator.rotate(hand_left_finger_left_2, 0, 0, 0.4F);
            this.animator.rotate(hand_left_finger_left_3, 0, 0, 0.4F);
            this.animator.rotate(hand_left_thumb_1, 0, 0, -0.2F);
            this.animator.rotate(hand_left_thumb_2, 0, 0, 0.8F);
            this.animator.endKeyframe();

            this.animator.setStaticKeyframe(1);

            this.animator.resetKeyframe(12);
        }
        if(animatedEntity.isAnimationPlaying(EndTrollEntity.DOUBLE_PUNCH_ANIMATION))
        {
            this.animator.setAnimationToPlay(EndTrollEntity.DOUBLE_PUNCH_ANIMATION);

            this.animator.startKeyframe(9);
            this.animator.rotate(torso, 0.3F, 0, 0);
            this.animator.rotate(stomach, -0.3F, 0, 0);
            this.animator.rotate(arm_left_main, 0.5F, 0.7F, -0.7F);
            this.animator.rotate(arm_left_2, -1.5F, 0, 0);
            this.animator.rotate(arm_right_main, 0.5F, -0.7F, 0.7F);
            this.animator.rotate(arm_right_2, -1.5F, 0, 0);

            this.animator.rotate(hand_left_finger_right_1, 0, 0, 0.2F);
            this.animator.rotate(hand_left_finger_right_2, 0, 0, 0.4F);
            this.animator.rotate(hand_left_finger_right_3, 0, 0, 0.4F);
            this.animator.rotate(hand_left_finger_left_1, 0, 0, 0.2F);
            this.animator.rotate(hand_left_finger_left_2, 0, 0, 0.4F);
            this.animator.rotate(hand_left_finger_left_3, 0, 0, 0.4F);
            this.animator.rotate(hand_left_thumb_1, 0, 0, -0.2F);
            this.animator.rotate(hand_left_thumb_2, 0, 0, 0.8F);
            this.animator.rotate(hand_right_finger_right_1, 0, 0, 0.2F);
            this.animator.rotate(hand_right_finger_right_2, 0, 0, 0.4F);
            this.animator.rotate(hand_right_finger_right_3, 0, 0, 0.4F);
            this.animator.rotate(hand_right_finger_left_1, 0, 0, 0.2F);
            this.animator.rotate(hand_right_finger_left_2, 0, 0, 0.4F);
            this.animator.rotate(hand_right_finger_left_3, 0, 0, 0.4F);
            this.animator.rotate(hand_right_thumb_1, 0, 0, -0.2F);
            this.animator.rotate(hand_right_thumb_2, 0, 0, 0.8F);
            this.animator.endKeyframe();

            this.animator.setStaticKeyframe(1);

            this.animator.startKeyframe(5);
            this.animator.rotate(torso, 0.2F, 0, 0);
            this.animator.rotate(stomach, 0.2F, 0, 0);

            this.animator.rotate(arm_left_main, -1.6F, 0.7F, -0.7F);
            this.animator.rotate(arm_left_2, 0.1F, 0, 0.4F);
            this.animator.rotate(arm_right_main, -1.6F, -0.7F, 0.7F);
            this.animator.rotate(arm_right_2, 0.1F, 0, -0.4F);

            this.animator.rotate(hand_left_finger_right_1, 0, 0, 0.2F);
            this.animator.rotate(hand_left_finger_right_2, 0, 0, 0.4F);
            this.animator.rotate(hand_left_finger_right_3, 0, 0, 0.4F);
            this.animator.rotate(hand_left_finger_left_1, 0, 0, 0.2F);
            this.animator.rotate(hand_left_finger_left_2, 0, 0, 0.4F);
            this.animator.rotate(hand_left_finger_left_3, 0, 0, 0.4F);
            this.animator.rotate(hand_left_thumb_1, 0, 0, -0.2F);
            this.animator.rotate(hand_left_thumb_2, 0, 0, 0.8F);
            this.animator.rotate(hand_right_finger_right_1, 0, 0, 0.2F);
            this.animator.rotate(hand_right_finger_right_2, 0, 0, 0.4F);
            this.animator.rotate(hand_right_finger_right_3, 0, 0, 0.4F);
            this.animator.rotate(hand_right_finger_left_1, 0, 0, 0.2F);
            this.animator.rotate(hand_right_finger_left_2, 0, 0, 0.4F);
            this.animator.rotate(hand_right_finger_left_3, 0, 0, 0.4F);
            this.animator.rotate(hand_right_thumb_1, 0, 0, -0.2F);
            this.animator.rotate(hand_right_thumb_2, 0, 0, 0.8F);
            this.animator.endKeyframe();

            this.animator.setStaticKeyframe(1);

            this.animator.resetKeyframe(12);
        }
        if(animatedEntity.isAnimationPlaying(EndTrollEntity.DEATH_ANIMATION))
        {
            this.animator.setAnimationToPlay(EndTrollEntity.DEATH_ANIMATION);

            this.animator.startKeyframe(0);
            this.animator.move(movement_base, 0, 0, -20.0F);
            this.animator.rotate(bottom_body, -1.6F, 0, 0);
            this.animator.rotate(leg_left_1, 1.6F, 0, 0);
            this.animator.rotate(leg_right_1, 1.6F, 0, 0);
            this.animator.rotate(leg_left_2, 0.0F, 0, 0);
            this.animator.rotate(leg_right_2, 0.0F, 0, 0);
            this.animator.rotate(leg_left_3, 0.0F, 0, 0);
            this.animator.rotate(leg_right_3, 0.0F, 0, 0);
            this.animator.rotate(stomach, 0.4F, 0, 0);
            this.animator.rotate(torso, 0.6F, 0, 0);
            this.animator.rotate(hand_left_main, 0, -1.6F, 0);
            this.animator.rotate(hand_right_main, 0, 1.6F, 0);
            this.animator.rotate(arm_left_main, 1.0F, 0.12F, -0.12F);
            this.animator.rotate(arm_right_main, 1.0F, -0.12F, 0.12F);
            this.animator.rotate(left_eyelid_base, 0, -0.3F, 0);
            this.animator.rotate(right_eyelid_base, 0, 0.3F, 0);
            this.animator.rotate(arm_left_2, 0.0F, 0, 0);
            this.animator.rotate(arm_right_2, 0.0F, 0, 0);
            this.animator.rotate(hand_left_thumb_1, 0, 0, -0.8F);
            this.animator.rotate(hand_left_thumb_2, 0, 0, -0.4F);
            this.animator.rotate(hand_right_thumb_1, 0, 0, -0.8F);
            this.animator.rotate(hand_right_thumb_2, 0, 0, -0.4F);
            this.animator.endKeyframe();

            //Legs Move Up
            this.animator.startKeyframe(6);
            this.animator.move(movement_base, 0, 0, -20.0F);
            this.animator.rotate(bottom_body, -1.5F, 0, 0);
            this.animator.move(bottom_body, 0, 6, 0);
            this.animator.rotate(leg_left_1, 1.5F, 0, 0);
            this.animator.rotate(leg_right_1, 1.5F, 0, 0);
            this.animator.rotate(leg_left_2, 0.6F, 0, 0);
            this.animator.rotate(leg_right_2, 0.6F, 0, 0);
            this.animator.rotate(leg_left_3, -0.6F, 0, 0);
            this.animator.rotate(leg_right_3, -0.6F, 0, 0);
            this.animator.rotate(stomach, 0.4F, 0, 0);
            this.animator.rotate(torso, 0.6F, 0, 0);
            this.animator.rotate(hand_left_main, 0, -1.6F, 0);
            this.animator.rotate(hand_right_main, 0, 1.6F, 0);
            this.animator.rotate(arm_left_main, 0.8F, 0.12F, -0.12F);
            this.animator.rotate(arm_right_main, 0.8F, -0.12F, 0.12F);
            this.animator.rotate(left_eyelid_base, 0, -0.3F, 0);
            this.animator.rotate(right_eyelid_base, 0, 0.3F, 0);
            this.animator.rotate(arm_left_2, -0.1F, 0, 0);
            this.animator.rotate(arm_right_2, -0.1F, 0, 0);
            this.animator.rotate(hand_left_thumb_1, 0, 0, -0.8F);
            this.animator.rotate(hand_left_thumb_2, 0, 0, -0.4F);
            this.animator.rotate(hand_right_thumb_1, 0, 0, -0.8F);
            this.animator.rotate(hand_right_thumb_2, 0, 0, -0.4F);
            this.animator.rotate(hand_left_finger_right_1, 0, 0, 0.2F);
            this.animator.rotate(hand_left_finger_right_2, 0, 0, 0.4F);
            this.animator.rotate(hand_left_finger_right_3, 0, 0, 0.4F);
            this.animator.rotate(hand_left_finger_left_1, 0, 0, 0.2F);
            this.animator.rotate(hand_left_finger_left_2, 0, 0, 0.4F);
            this.animator.rotate(hand_left_finger_left_3, 0, 0, 0.4F);
            this.animator.rotate(hand_left_thumb_1, 0, 0, -0.2F);
            this.animator.rotate(hand_left_thumb_2, 0, 0, 0.8F);
            this.animator.rotate(hand_right_finger_right_1, 0, 0, 0.2F);
            this.animator.rotate(hand_right_finger_right_2, 0, 0, 0.4F);
            this.animator.rotate(hand_right_finger_right_3, 0, 0, 0.4F);
            this.animator.rotate(hand_right_finger_left_1, 0, 0, 0.2F);
            this.animator.rotate(hand_right_finger_left_2, 0, 0, 0.4F);
            this.animator.rotate(hand_right_finger_left_3, 0, 0, 0.4F);
            this.animator.rotate(hand_right_thumb_1, 0, 0, -0.2F);
            this.animator.rotate(hand_right_thumb_2, 0, 0, 0.8F);
            this.animator.endKeyframe();

            //Fall Forwards
            this.animator.startKeyframe(8);
            this.animator.move(movement_base, 0, 0, -20.0F);
            this.animator.rotate(bottom_body, -0.7F, 0, 0);
            this.animator.move(bottom_body, 0, 6, 0);
            this.animator.rotate(leg_left_1, 0.7F, 0, 0);
            this.animator.rotate(leg_right_1, 0.7F, 0, 0);
            this.animator.rotate(leg_left_2, 0.6F, 0, 0);
            this.animator.rotate(leg_right_2, 0.6F, 0, 0);
            this.animator.rotate(leg_left_3, -0.6F, 0, 0);
            this.animator.rotate(leg_right_3, -0.6F, 0, 0);
            this.animator.rotate(stomach, 0.4F, 0, 0);
            this.animator.rotate(torso, 0.6F, 0, 0);
            this.animator.rotate(hand_left_main, 0, -1.6F, 0);
            this.animator.rotate(hand_right_main, 0, 1.6F, 0);
            this.animator.rotate(arm_left_main, 0.1F, 0.12F, -0.12F);
            this.animator.rotate(arm_right_main, 0.1F, -0.12F, 0.12F);
            this.animator.rotate(arm_left_2, -0.4F, 0, 0);
            this.animator.rotate(arm_right_2, -0.4F, 0, 0);
            this.animator.rotate(left_eyelid_base, 0, 0.0F, 0);
            this.animator.rotate(right_eyelid_base, 0, 0.0F, 0);
            this.animator.rotate(hand_left_thumb_1, 0, 0, -0.8F);
            this.animator.rotate(hand_left_thumb_2, 0, 0, -0.4F);
            this.animator.rotate(hand_right_thumb_1, 0, 0, -0.8F);
            this.animator.rotate(hand_right_thumb_2, 0, 0, -0.4F);
            this.animator.rotate(hand_left_finger_right_1, 0, 0, 0.2F);
            this.animator.rotate(hand_left_finger_right_2, 0, 0, 0.4F);
            this.animator.rotate(hand_left_finger_right_3, 0, 0, 0.4F);
            this.animator.rotate(hand_left_finger_left_1, 0, 0, 0.2F);
            this.animator.rotate(hand_left_finger_left_2, 0, 0, 0.4F);
            this.animator.rotate(hand_left_finger_left_3, 0, 0, 0.4F);
            this.animator.rotate(hand_left_thumb_1, 0, 0, -0.2F);
            this.animator.rotate(hand_left_thumb_2, 0, 0, 0.8F);
            this.animator.rotate(hand_right_finger_right_1, 0, 0, 0.2F);
            this.animator.rotate(hand_right_finger_right_2, 0, 0, 0.4F);
            this.animator.rotate(hand_right_finger_right_3, 0, 0, 0.4F);
            this.animator.rotate(hand_right_finger_left_1, 0, 0, 0.2F);
            this.animator.rotate(hand_right_finger_left_2, 0, 0, 0.4F);
            this.animator.rotate(hand_right_finger_left_3, 0, 0, 0.4F);
            this.animator.rotate(hand_right_thumb_1, 0, 0, -0.2F);
            this.animator.rotate(hand_right_thumb_2, 0, 0, 0.8F);
            this.animator.endKeyframe();

            this.animator.setStaticKeyframe(4);

            //Final Death Part
            this.animator.startKeyframe(16);
            this.animator.move(movement_base, 0, 0, -20.0F);
            this.animator.rotate(bottom_body, 0.1F, 0, 0);
            this.animator.move(bottom_body, 0, 6, 0);
            this.animator.rotate(leg_left_1, -0.1F, 0, 0);
            this.animator.rotate(leg_right_1, -0.1F, 0, 0);
            this.animator.rotate(leg_left_2, 0.6F, 0, 0);
            this.animator.rotate(leg_right_2, 0.6F, 0, 0);
            this.animator.rotate(leg_left_3, -0.6F, 0, 0);
            this.animator.rotate(leg_right_3, -0.6F, 0, 0);
            this.animator.rotate(stomach, 0.4F, 0, 0);
            this.animator.rotate(torso, 0.4F, 0, 0);
            this.animator.rotate(hand_left_main, 0, -1.6F, 0);
            this.animator.rotate(hand_right_main, 0, 1.6F, 0);

            this.animator.rotate(arm_left_main, -0.7F, -0.7F, -0.7F);
            this.animator.rotate(arm_right_main, -0.7F, 0.7F, 0.7F);
            this.animator.rotate(arm_left_2, -0.4F, 0, 0);
            this.animator.rotate(arm_right_2, -0.4F, 0, 0);

            this.animator.rotate(left_eyelid_base, 0, 0.0F, 0);
            this.animator.rotate(right_eyelid_base, 0, 0.0F, 0);
            this.animator.rotate(hand_left_thumb_1, 0, 0, -0.8F);
            this.animator.rotate(hand_left_thumb_2, 0, 0, -0.4F);
            this.animator.rotate(hand_right_thumb_1, 0, 0, -0.8F);
            this.animator.rotate(hand_right_thumb_2, 0, 0, -0.4F);
            this.animator.rotate(hand_left_finger_right_1, 0, 0, 0.2F);
            this.animator.rotate(hand_left_finger_right_2, 0, 0, 0.4F);
            this.animator.rotate(hand_left_finger_right_3, 0, 0, 0.4F);
            this.animator.rotate(hand_left_finger_left_1, 0, 0, 0.2F);
            this.animator.rotate(hand_left_finger_left_2, 0, 0, 0.4F);
            this.animator.rotate(hand_left_finger_left_3, 0, 0, 0.4F);
            this.animator.rotate(hand_left_thumb_1, 0, 0, -0.2F);
            this.animator.rotate(hand_left_thumb_2, 0, 0, 0.8F);
            this.animator.rotate(hand_right_finger_right_1, 0, 0, 0.2F);
            this.animator.rotate(hand_right_finger_right_2, 0, 0, 0.4F);
            this.animator.rotate(hand_right_finger_right_3, 0, 0, 0.4F);
            this.animator.rotate(hand_right_finger_left_1, 0, 0, 0.2F);
            this.animator.rotate(hand_right_finger_left_2, 0, 0, 0.4F);
            this.animator.rotate(hand_right_finger_left_3, 0, 0, 0.4F);
            this.animator.rotate(hand_right_thumb_1, 0, 0, -0.2F);
            this.animator.rotate(hand_right_thumb_2, 0, 0, 0.8F);
            this.animator.endKeyframe();

            this.animator.setStaticKeyframe(16);
        }

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.movement_base.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
