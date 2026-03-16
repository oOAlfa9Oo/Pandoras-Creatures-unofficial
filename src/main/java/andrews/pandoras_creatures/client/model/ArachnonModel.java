package andrews.pandoras_creatures.client.model;

import andrews.pandoras_creatures.client.model.base.PCEntityModel;
import andrews.pandoras_creatures.entities.ArachnonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

/**
 * ArachnonModel - Complete model migrated from the original 1.16.5 Tabula source.
 */
@OnlyIn(Dist.CLIENT)
public class ArachnonModel<T extends ArachnonEntity> extends PCEntityModel<T> {
    private final ModelPart body;
    private final ModelPart body2;
    private final ModelPart shoulders_front;
    private final ModelPart body_top_plate;
    private final ModelPart body_head_holder;
    private final ModelPart spike_holder;
    private final ModelPart spike_holder_1;
    private final ModelPart body3;
    private final ModelPart body_bottom;
    private final ModelPart shoulders_back;
    private final ModelPart body_top_plate2;
    private final ModelPart spike_holder_2;
    private final ModelPart spike_holder_3;
    private final ModelPart back_cover_top;
    private final ModelPart shape47;
    private final ModelPart shape48;
    private final ModelPart big_spike;
    private final ModelPart big_spike_1;
    private final ModelPart leg_back_right_rotation_point;
    private final ModelPart leg_back_left_rotation_point;
    private final ModelPart spike;
    private final ModelPart spike_1;
    private final ModelPart leg_back_right;
    private final ModelPart leg_back_right2;
    private final ModelPart leg_back_right_top;
    private final ModelPart leg_back_right3;
    private final ModelPart spike_2;
    private final ModelPart spike_3;
    private final ModelPart spike_4;
    private final ModelPart leg_back_right4;
    private final ModelPart spike_5;
    private final ModelPart spike_6;
    private final ModelPart leg_back_left;
    private final ModelPart leg_back_left2;
    private final ModelPart leg_back_left_top;
    private final ModelPart leg_back_left3;
    private final ModelPart spike_7;
    private final ModelPart spike_8;
    private final ModelPart spike_9;
    private final ModelPart leg_back_left_4;
    private final ModelPart spike_10;
    private final ModelPart spike_11;
    private final ModelPart spike2;
    private final ModelPart spike1;
    private final ModelPart spike3;
    private final ModelPart spike_12;
    private final ModelPart spike_13;
    private final ModelPart spike_14;
    private final ModelPart spike_15;
    private final ModelPart spike_16;
    private final ModelPart spike_17;
    private final ModelPart big_spike_2;
    private final ModelPart spike_18;
    private final ModelPart shape49;
    private final ModelPart shape50;
    private final ModelPart leg_front_left_rotation_point;
    private final ModelPart leg_front_right_rotation_point;
    private final ModelPart leg_front_left;
    private final ModelPart leg_front_left2;
    private final ModelPart leg_front_left_top;
    private final ModelPart leg_front_left3;
    private final ModelPart spike_19;
    private final ModelPart spike_20;
    private final ModelPart spike_21;
    private final ModelPart leg_front_left_4;
    private final ModelPart spike_22;
    private final ModelPart spike_23;
    private final ModelPart leg_front_right;
    private final ModelPart leg_front_right2;
    private final ModelPart leg_front_right_top;
    private final ModelPart leg_front_right3;
    private final ModelPart spike_24;
    private final ModelPart spike_25;
    private final ModelPart spike_26;
    private final ModelPart leg_front_right4;
    private final ModelPart spike_27;
    private final ModelPart spike_28;
    private final ModelPart spike2_1;
    private final ModelPart spike1_1;
    private final ModelPart spike3_1;
    private final ModelPart neck;
    private final ModelPart neck_bottom;
    private final ModelPart neck_front;
    private final ModelPart head;
    private final ModelPart eye;
    private final ModelPart head_side_left;
    private final ModelPart head_side_right;
    private final ModelPart head_top;
    private final ModelPart lip_up_1;
    private final ModelPart mouth_left;
    private final ModelPart mouth_right;
    private final ModelPart mouth_up_front;
    private final ModelPart mouth_up_inside;
    private final ModelPart tooth_top_left;
    private final ModelPart tooth_top_right;
    private final ModelPart tooth_top_mid_right;
    private final ModelPart tooth_top_mid_left;
    private final ModelPart mouth_bottom;
    private final ModelPart eye_ball;
    private final ModelPart eye_brow;
    private final ModelPart eye_brow_left;
    private final ModelPart eye_brow_right;
    private final ModelPart eye_protection_right;
    private final ModelPart eye_protection_left;
    private final ModelPart head_back_lid;
    private final ModelPart head_back_con;
    private final ModelPart head_back_lid_right;
    private final ModelPart head_back_lid_left;
    private final ModelPart lip_up_2;
    private final ModelPart tooth_top_left_2;
    private final ModelPart tooth_top_right_2;
    private final ModelPart tooth_top_mid_right_2;
    private final ModelPart tooth_top_mid_left_2;
    private final ModelPart mouth_bottom_lip_front;
    private final ModelPart tooth_bottom_mid;
    private final ModelPart mouth_bottom_side_left;
    private final ModelPart mouth_bottom_side_right;
    private final ModelPart tooth_bottom_left;
    private final ModelPart tooth_bottom_right;
    private final ModelPart tooth_bottom_left_1;
    private final ModelPart tooth_bottom_right_1;
    private final ModelPart mouth_bottom_side_left_2;
    private final ModelPart mouth_bottom_side_right_2;
    private final ModelPart tooth_bottom_left_2;
    private final ModelPart tooth_bottom_right_2;
    private final ModelPart spike_29;
    private final ModelPart spike_30;
    private final ModelPart spike_31;
    private final ModelPart spike_32;
    private final ModelPart spike_33;
    private final ModelPart spike_34;

    public ArachnonModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.body2 = root.getChild("body").getChild("body2");
        this.shoulders_front = root.getChild("body").getChild("shoulders_front");
        this.body_top_plate = root.getChild("body").getChild("body_top_plate");
        this.body_head_holder = root.getChild("body").getChild("body_head_holder");
        this.spike_holder = root.getChild("body").getChild("spike_holder");
        this.spike_holder_1 = root.getChild("body").getChild("spike_holder_1");
        this.body3 = root.getChild("body").getChild("body2").getChild("body3");
        this.body_bottom = root.getChild("body").getChild("body2").getChild("body_bottom");
        this.shoulders_back = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back");
        this.body_top_plate2 = root.getChild("body").getChild("body2").getChild("body3").getChild("body_top_plate2");
        this.spike_holder_2 = root.getChild("body").getChild("body2").getChild("body3").getChild("spike_holder_2");
        this.spike_holder_3 = root.getChild("body").getChild("body2").getChild("body3").getChild("spike_holder_3");
        this.back_cover_top = root.getChild("body").getChild("body2").getChild("body3").getChild("back_cover_top");
        this.shape47 = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("shape47");
        this.shape48 = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("shape48");
        this.big_spike = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("big_spike");
        this.big_spike_1 = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("big_spike_1");
        this.leg_back_right_rotation_point = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("leg_back_right_rotation_point");
        this.leg_back_left_rotation_point = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("leg_back_left_rotation_point");
        this.spike = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("big_spike").getChild("spike");
        this.spike_1 = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("big_spike_1").getChild("spike_1");
        this.leg_back_right = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("leg_back_right_rotation_point").getChild("leg_back_right");
        this.leg_back_right2 = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("leg_back_right_rotation_point").getChild("leg_back_right").getChild("leg_back_right2");
        this.leg_back_right_top = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("leg_back_right_rotation_point").getChild("leg_back_right").getChild("leg_back_right_top");
        this.leg_back_right3 = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("leg_back_right_rotation_point").getChild("leg_back_right").getChild("leg_back_right2").getChild("leg_back_right3");
        this.spike_2 = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("leg_back_right_rotation_point").getChild("leg_back_right").getChild("leg_back_right2").getChild("spike_2");
        this.spike_3 = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("leg_back_right_rotation_point").getChild("leg_back_right").getChild("leg_back_right2").getChild("spike_3");
        this.spike_4 = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("leg_back_right_rotation_point").getChild("leg_back_right").getChild("leg_back_right2").getChild("spike_4");
        this.leg_back_right4 = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("leg_back_right_rotation_point").getChild("leg_back_right").getChild("leg_back_right2").getChild("leg_back_right3").getChild("leg_back_right4");
        this.spike_5 = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("leg_back_right_rotation_point").getChild("leg_back_right").getChild("leg_back_right2").getChild("leg_back_right3").getChild("spike_5");
        this.spike_6 = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("leg_back_right_rotation_point").getChild("leg_back_right").getChild("leg_back_right2").getChild("spike_2").getChild("spike_6");
        this.leg_back_left = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("leg_back_left_rotation_point").getChild("leg_back_left");
        this.leg_back_left2 = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("leg_back_left_rotation_point").getChild("leg_back_left").getChild("leg_back_left2");
        this.leg_back_left_top = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("leg_back_left_rotation_point").getChild("leg_back_left").getChild("leg_back_left_top");
        this.leg_back_left3 = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("leg_back_left_rotation_point").getChild("leg_back_left").getChild("leg_back_left2").getChild("leg_back_left3");
        this.spike_7 = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("leg_back_left_rotation_point").getChild("leg_back_left").getChild("leg_back_left2").getChild("spike_7");
        this.spike_8 = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("leg_back_left_rotation_point").getChild("leg_back_left").getChild("leg_back_left2").getChild("spike_8");
        this.spike_9 = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("leg_back_left_rotation_point").getChild("leg_back_left").getChild("leg_back_left2").getChild("spike_9");
        this.leg_back_left_4 = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("leg_back_left_rotation_point").getChild("leg_back_left").getChild("leg_back_left2").getChild("leg_back_left3").getChild("leg_back_left_4");
        this.spike_10 = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("leg_back_left_rotation_point").getChild("leg_back_left").getChild("leg_back_left2").getChild("leg_back_left3").getChild("spike_10");
        this.spike_11 = root.getChild("body").getChild("body2").getChild("body3").getChild("shoulders_back").getChild("leg_back_left_rotation_point").getChild("leg_back_left").getChild("leg_back_left2").getChild("spike_7").getChild("spike_11");
        this.spike2 = root.getChild("body").getChild("body2").getChild("body3").getChild("body_top_plate2").getChild("spike2");
        this.spike1 = root.getChild("body").getChild("body2").getChild("body3").getChild("body_top_plate2").getChild("spike1");
        this.spike3 = root.getChild("body").getChild("body2").getChild("body3").getChild("body_top_plate2").getChild("spike3");
        this.spike_12 = root.getChild("body").getChild("body2").getChild("body3").getChild("spike_holder_2").getChild("spike_12");
        this.spike_13 = root.getChild("body").getChild("body2").getChild("body3").getChild("spike_holder_2").getChild("spike_13");
        this.spike_14 = root.getChild("body").getChild("body2").getChild("body3").getChild("spike_holder_2").getChild("spike_14");
        this.spike_15 = root.getChild("body").getChild("body2").getChild("body3").getChild("spike_holder_3").getChild("spike_15");
        this.spike_16 = root.getChild("body").getChild("body2").getChild("body3").getChild("spike_holder_3").getChild("spike_16");
        this.spike_17 = root.getChild("body").getChild("body2").getChild("body3").getChild("spike_holder_3").getChild("spike_17");
        this.big_spike_2 = root.getChild("body").getChild("body2").getChild("body3").getChild("back_cover_top").getChild("big_spike_2");
        this.spike_18 = root.getChild("body").getChild("body2").getChild("body3").getChild("back_cover_top").getChild("big_spike_2").getChild("spike_18");
        this.shape49 = root.getChild("body").getChild("shoulders_front").getChild("shape49");
        this.shape50 = root.getChild("body").getChild("shoulders_front").getChild("shape50");
        this.leg_front_left_rotation_point = root.getChild("body").getChild("shoulders_front").getChild("leg_front_left_rotation_point");
        this.leg_front_right_rotation_point = root.getChild("body").getChild("shoulders_front").getChild("leg_front_right_rotation_point");
        this.leg_front_left = root.getChild("body").getChild("shoulders_front").getChild("leg_front_left_rotation_point").getChild("leg_front_left");
        this.leg_front_left2 = root.getChild("body").getChild("shoulders_front").getChild("leg_front_left_rotation_point").getChild("leg_front_left").getChild("leg_front_left2");
        this.leg_front_left_top = root.getChild("body").getChild("shoulders_front").getChild("leg_front_left_rotation_point").getChild("leg_front_left").getChild("leg_front_left_top");
        this.leg_front_left3 = root.getChild("body").getChild("shoulders_front").getChild("leg_front_left_rotation_point").getChild("leg_front_left").getChild("leg_front_left2").getChild("leg_front_left3");
        this.spike_19 = root.getChild("body").getChild("shoulders_front").getChild("leg_front_left_rotation_point").getChild("leg_front_left").getChild("leg_front_left2").getChild("spike_19");
        this.spike_20 = root.getChild("body").getChild("shoulders_front").getChild("leg_front_left_rotation_point").getChild("leg_front_left").getChild("leg_front_left2").getChild("spike_20");
        this.spike_21 = root.getChild("body").getChild("shoulders_front").getChild("leg_front_left_rotation_point").getChild("leg_front_left").getChild("leg_front_left2").getChild("spike_21");
        this.leg_front_left_4 = root.getChild("body").getChild("shoulders_front").getChild("leg_front_left_rotation_point").getChild("leg_front_left").getChild("leg_front_left2").getChild("leg_front_left3").getChild("leg_front_left_4");
        this.spike_22 = root.getChild("body").getChild("shoulders_front").getChild("leg_front_left_rotation_point").getChild("leg_front_left").getChild("leg_front_left2").getChild("leg_front_left3").getChild("spike_22");
        this.spike_23 = root.getChild("body").getChild("shoulders_front").getChild("leg_front_left_rotation_point").getChild("leg_front_left").getChild("leg_front_left2").getChild("spike_19").getChild("spike_23");
        this.leg_front_right = root.getChild("body").getChild("shoulders_front").getChild("leg_front_right_rotation_point").getChild("leg_front_right");
        this.leg_front_right2 = root.getChild("body").getChild("shoulders_front").getChild("leg_front_right_rotation_point").getChild("leg_front_right").getChild("leg_front_right2");
        this.leg_front_right_top = root.getChild("body").getChild("shoulders_front").getChild("leg_front_right_rotation_point").getChild("leg_front_right").getChild("leg_front_right_top");
        this.leg_front_right3 = root.getChild("body").getChild("shoulders_front").getChild("leg_front_right_rotation_point").getChild("leg_front_right").getChild("leg_front_right2").getChild("leg_front_right3");
        this.spike_24 = root.getChild("body").getChild("shoulders_front").getChild("leg_front_right_rotation_point").getChild("leg_front_right").getChild("leg_front_right2").getChild("spike_24");
        this.spike_25 = root.getChild("body").getChild("shoulders_front").getChild("leg_front_right_rotation_point").getChild("leg_front_right").getChild("leg_front_right2").getChild("spike_25");
        this.spike_26 = root.getChild("body").getChild("shoulders_front").getChild("leg_front_right_rotation_point").getChild("leg_front_right").getChild("leg_front_right2").getChild("spike_26");
        this.leg_front_right4 = root.getChild("body").getChild("shoulders_front").getChild("leg_front_right_rotation_point").getChild("leg_front_right").getChild("leg_front_right2").getChild("leg_front_right3").getChild("leg_front_right4");
        this.spike_27 = root.getChild("body").getChild("shoulders_front").getChild("leg_front_right_rotation_point").getChild("leg_front_right").getChild("leg_front_right2").getChild("leg_front_right3").getChild("spike_27");
        this.spike_28 = root.getChild("body").getChild("shoulders_front").getChild("leg_front_right_rotation_point").getChild("leg_front_right").getChild("leg_front_right2").getChild("spike_24").getChild("spike_28");
        this.spike2_1 = root.getChild("body").getChild("body_top_plate").getChild("spike2_1");
        this.spike1_1 = root.getChild("body").getChild("body_top_plate").getChild("spike1_1");
        this.spike3_1 = root.getChild("body").getChild("body_top_plate").getChild("spike3_1");
        this.neck = root.getChild("body").getChild("body_head_holder").getChild("neck");
        this.neck_bottom = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom");
        this.neck_front = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_front");
        this.head = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head");
        this.eye = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("eye");
        this.head_side_left = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("head_side_left");
        this.head_side_right = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("head_side_right");
        this.head_top = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("head_top");
        this.lip_up_1 = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("lip_up_1");
        this.mouth_left = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("mouth_left");
        this.mouth_right = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("mouth_right");
        this.mouth_up_front = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("mouth_up_front");
        this.mouth_up_inside = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("mouth_up_inside");
        this.tooth_top_left = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("tooth_top_left");
        this.tooth_top_right = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("tooth_top_right");
        this.tooth_top_mid_right = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("tooth_top_mid_right");
        this.tooth_top_mid_left = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("tooth_top_mid_left");
        this.mouth_bottom = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("mouth_bottom");
        this.eye_ball = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("eye").getChild("eye_ball");
        this.eye_brow = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("eye").getChild("eye_brow");
        this.eye_brow_left = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("eye").getChild("eye_brow").getChild("eye_brow_left");
        this.eye_brow_right = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("eye").getChild("eye_brow").getChild("eye_brow_right");
        this.eye_protection_right = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("head_top").getChild("eye_protection_right");
        this.eye_protection_left = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("head_top").getChild("eye_protection_left");
        this.head_back_lid = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("head_top").getChild("head_back_lid");
        this.head_back_con = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("head_top").getChild("head_back_con");
        this.head_back_lid_right = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("head_top").getChild("head_back_lid").getChild("head_back_lid_right");
        this.head_back_lid_left = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("head_top").getChild("head_back_lid").getChild("head_back_lid_left");
        this.lip_up_2 = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("lip_up_1").getChild("lip_up_2");
        this.tooth_top_left_2 = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("tooth_top_left").getChild("tooth_top_left_2");
        this.tooth_top_right_2 = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("tooth_top_right").getChild("tooth_top_right_2");
        this.tooth_top_mid_right_2 = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("tooth_top_mid_right").getChild("tooth_top_mid_right_2");
        this.tooth_top_mid_left_2 = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("tooth_top_mid_left").getChild("tooth_top_mid_left_2");
        this.mouth_bottom_lip_front = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("mouth_bottom").getChild("mouth_bottom_lip_front");
        this.tooth_bottom_mid = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("mouth_bottom").getChild("tooth_bottom_mid");
        this.mouth_bottom_side_left = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("mouth_bottom").getChild("mouth_bottom_side_left");
        this.mouth_bottom_side_right = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("mouth_bottom").getChild("mouth_bottom_side_right");
        this.tooth_bottom_left = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("mouth_bottom").getChild("tooth_bottom_left");
        this.tooth_bottom_right = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("mouth_bottom").getChild("tooth_bottom_right");
        this.tooth_bottom_left_1 = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("mouth_bottom").getChild("tooth_bottom_mid").getChild("tooth_bottom_left_1");
        this.tooth_bottom_right_1 = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("mouth_bottom").getChild("tooth_bottom_mid").getChild("tooth_bottom_right_1");
        this.mouth_bottom_side_left_2 = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("mouth_bottom").getChild("mouth_bottom_side_left").getChild("mouth_bottom_side_left_2");
        this.mouth_bottom_side_right_2 = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("mouth_bottom").getChild("mouth_bottom_side_right").getChild("mouth_bottom_side_right_2");
        this.tooth_bottom_left_2 = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("mouth_bottom").getChild("tooth_bottom_left").getChild("tooth_bottom_left_2");
        this.tooth_bottom_right_2 = root.getChild("body").getChild("body_head_holder").getChild("neck").getChild("neck_bottom").getChild("head").getChild("mouth_bottom").getChild("tooth_bottom_right").getChild("tooth_bottom_right_2");
        this.spike_29 = root.getChild("body").getChild("spike_holder").getChild("spike_29");
        this.spike_30 = root.getChild("body").getChild("spike_holder").getChild("spike_30");
        this.spike_31 = root.getChild("body").getChild("spike_holder").getChild("spike_31");
        this.spike_32 = root.getChild("body").getChild("spike_holder_1").getChild("spike_32");
        this.spike_33 = root.getChild("body").getChild("spike_holder_1").getChild("spike_33");
        this.spike_34 = root.getChild("body").getChild("spike_holder_1").getChild("spike_34");

        registerAnimatedPart(body);
        registerAnimatedPart(body2);
        registerAnimatedPart(shoulders_front);
        registerAnimatedPart(body_top_plate);
        registerAnimatedPart(body_head_holder);
        registerAnimatedPart(spike_holder);
        registerAnimatedPart(spike_holder_1);
        registerAnimatedPart(body3);
        registerAnimatedPart(body_bottom);
        registerAnimatedPart(shoulders_back);
        registerAnimatedPart(body_top_plate2);
        registerAnimatedPart(spike_holder_2);
        registerAnimatedPart(spike_holder_3);
        registerAnimatedPart(back_cover_top);
        registerAnimatedPart(shape47);
        registerAnimatedPart(shape48);
        registerAnimatedPart(big_spike);
        registerAnimatedPart(big_spike_1);
        registerAnimatedPart(leg_back_right_rotation_point);
        registerAnimatedPart(leg_back_left_rotation_point);
        registerAnimatedPart(spike);
        registerAnimatedPart(spike_1);
        registerAnimatedPart(leg_back_right);
        registerAnimatedPart(leg_back_right2);
        registerAnimatedPart(leg_back_right_top);
        registerAnimatedPart(leg_back_right3);
        registerAnimatedPart(spike_2);
        registerAnimatedPart(spike_3);
        registerAnimatedPart(spike_4);
        registerAnimatedPart(leg_back_right4);
        registerAnimatedPart(spike_5);
        registerAnimatedPart(spike_6);
        registerAnimatedPart(leg_back_left);
        registerAnimatedPart(leg_back_left2);
        registerAnimatedPart(leg_back_left_top);
        registerAnimatedPart(leg_back_left3);
        registerAnimatedPart(spike_7);
        registerAnimatedPart(spike_8);
        registerAnimatedPart(spike_9);
        registerAnimatedPart(leg_back_left_4);
        registerAnimatedPart(spike_10);
        registerAnimatedPart(spike_11);
        registerAnimatedPart(spike2);
        registerAnimatedPart(spike1);
        registerAnimatedPart(spike3);
        registerAnimatedPart(spike_12);
        registerAnimatedPart(spike_13);
        registerAnimatedPart(spike_14);
        registerAnimatedPart(spike_15);
        registerAnimatedPart(spike_16);
        registerAnimatedPart(spike_17);
        registerAnimatedPart(big_spike_2);
        registerAnimatedPart(spike_18);
        registerAnimatedPart(shape49);
        registerAnimatedPart(shape50);
        registerAnimatedPart(leg_front_left_rotation_point);
        registerAnimatedPart(leg_front_right_rotation_point);
        registerAnimatedPart(leg_front_left);
        registerAnimatedPart(leg_front_left2);
        registerAnimatedPart(leg_front_left_top);
        registerAnimatedPart(leg_front_left3);
        registerAnimatedPart(spike_19);
        registerAnimatedPart(spike_20);
        registerAnimatedPart(spike_21);
        registerAnimatedPart(leg_front_left_4);
        registerAnimatedPart(spike_22);
        registerAnimatedPart(spike_23);
        registerAnimatedPart(leg_front_right);
        registerAnimatedPart(leg_front_right2);
        registerAnimatedPart(leg_front_right_top);
        registerAnimatedPart(leg_front_right3);
        registerAnimatedPart(spike_24);
        registerAnimatedPart(spike_25);
        registerAnimatedPart(spike_26);
        registerAnimatedPart(leg_front_right4);
        registerAnimatedPart(spike_27);
        registerAnimatedPart(spike_28);
        registerAnimatedPart(spike2_1);
        registerAnimatedPart(spike1_1);
        registerAnimatedPart(spike3_1);
        registerAnimatedPart(neck);
        registerAnimatedPart(neck_bottom);
        registerAnimatedPart(neck_front);
        registerAnimatedPart(head);
        registerAnimatedPart(eye);
        registerAnimatedPart(head_side_left);
        registerAnimatedPart(head_side_right);
        registerAnimatedPart(head_top);
        registerAnimatedPart(lip_up_1);
        registerAnimatedPart(mouth_left);
        registerAnimatedPart(mouth_right);
        registerAnimatedPart(mouth_up_front);
        registerAnimatedPart(mouth_up_inside);
        registerAnimatedPart(tooth_top_left);
        registerAnimatedPart(tooth_top_right);
        registerAnimatedPart(tooth_top_mid_right);
        registerAnimatedPart(tooth_top_mid_left);
        registerAnimatedPart(mouth_bottom);
        registerAnimatedPart(eye_ball);
        registerAnimatedPart(eye_brow);
        registerAnimatedPart(eye_brow_left);
        registerAnimatedPart(eye_brow_right);
        registerAnimatedPart(eye_protection_right);
        registerAnimatedPart(eye_protection_left);
        registerAnimatedPart(head_back_lid);
        registerAnimatedPart(head_back_con);
        registerAnimatedPart(head_back_lid_right);
        registerAnimatedPart(head_back_lid_left);
        registerAnimatedPart(lip_up_2);
        registerAnimatedPart(tooth_top_left_2);
        registerAnimatedPart(tooth_top_right_2);
        registerAnimatedPart(tooth_top_mid_right_2);
        registerAnimatedPart(tooth_top_mid_left_2);
        registerAnimatedPart(mouth_bottom_lip_front);
        registerAnimatedPart(tooth_bottom_mid);
        registerAnimatedPart(mouth_bottom_side_left);
        registerAnimatedPart(mouth_bottom_side_right);
        registerAnimatedPart(tooth_bottom_left);
        registerAnimatedPart(tooth_bottom_right);
        registerAnimatedPart(tooth_bottom_left_1);
        registerAnimatedPart(tooth_bottom_right_1);
        registerAnimatedPart(mouth_bottom_side_left_2);
        registerAnimatedPart(mouth_bottom_side_right_2);
        registerAnimatedPart(tooth_bottom_left_2);
        registerAnimatedPart(tooth_bottom_right_2);
        registerAnimatedPart(spike_29);
        registerAnimatedPart(spike_30);
        registerAnimatedPart(spike_31);
        registerAnimatedPart(spike_32);
        registerAnimatedPart(spike_33);
        registerAnimatedPart(spike_34);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(224, 0).addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8), PartPose.offset(0.0F, 4.9F, -4.5F));
        PartDefinition body2 = body.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(199, 0).addBox(0.0F, 0.0F, -1.0F, 6, 7, 6), PartPose.offset(-3.0F, -3.1F, 4.0F));
        body2.addOrReplaceChild("body_bottom", CubeListBuilder.create().texOffs(202, 17).addBox(0.0F, 0.0F, 0.0F, 4, 1, 20), PartPose.offset(1.0F, 7.0F, -8.0F));
        PartDefinition body3 = body2.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(166, 0).addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8), PartPose.offset(3.0F, 3.1F, 8.0F));
        PartDefinition spike_holder_3 = body3.addOrReplaceChild("spike_holder_3", CubeListBuilder.create().texOffs(42, 11).addBox(-3.0F, 0.0F, 0.0F, 3, 2, 4), PartPose.offsetAndRotation(-4.0F, -1.4F, -0.5F, 0.0F, 0.0F, -0.5585053606381855F));
        spike_holder_3.addOrReplaceChild("spike_15", CubeListBuilder.create().texOffs(52, 18).addBox(-1.0F, -2.0F, -1.0F, 1, 2, 1), PartPose.offsetAndRotation(-1.0F, 0.0F, 1.0F, 0.7853981633974483F, 0.0F, 0.0F));
        spike_holder_3.addOrReplaceChild("spike_16", CubeListBuilder.create().texOffs(47, 18).addBox(-1.0F, -2.0F, -1.0F, 1, 2, 1), PartPose.offset(-1.0F, 0.3F, 2.5F));
        spike_holder_3.addOrReplaceChild("spike_17", CubeListBuilder.create().texOffs(42, 18).addBox(-1.0F, -2.0F, 0.0F, 1, 2, 1), PartPose.offsetAndRotation(-1.0F, 0.0F, 3.0F, -0.7853981633974483F, 0.0F, 0.0F));
        PartDefinition shoulders_back = body3.addOrReplaceChild("shoulders_back", CubeListBuilder.create().texOffs(125, 9).addBox(0.0F, 0.0F, 0.0F, 16, 4, 4), PartPose.offset(-8.0F, 0.01F, 1.0F));
        PartDefinition big_spike = shoulders_back.addOrReplaceChild("big_spike", CubeListBuilder.create().texOffs(12, 0).addBox(-2.0F, -5.0F, -2.0F, 2, 5, 2), PartPose.offsetAndRotation(6.0F, 3.5F, 4.0F, -0.8726646259971648F, -0.4363323129985824F, 0.0F));
        big_spike.addOrReplaceChild("spike", CubeListBuilder.create().texOffs(12, 8).addBox(0.0F, -3.0F, -1.0F, 1, 3, 1), PartPose.offsetAndRotation(-1.5F, -5.0F, -0.5F, 0.3490658503988659F, 0.0F, 0.0F));
        shoulders_back.addOrReplaceChild("shape47", CubeListBuilder.create().texOffs(124, 29).addBox(0.0F, 0.0F, -6.0F, 4, 4, 6), PartPose.offsetAndRotation(0.0F, 0.01F, 0.0F, 0.0F, -0.7853981633974483F, 0.0F));
        PartDefinition big_spike_1 = shoulders_back.addOrReplaceChild("big_spike_1", CubeListBuilder.create().texOffs(30, 0).addBox(0.0F, -5.0F, -2.0F, 2, 5, 2), PartPose.offsetAndRotation(10.0F, 3.5F, 4.0F, -0.8726646259971648F, 0.4363323129985824F, 0.0F));
        big_spike_1.addOrReplaceChild("spike_1", CubeListBuilder.create().texOffs(30, 8).addBox(-1.0F, -3.0F, -1.0F, 1, 3, 1), PartPose.offsetAndRotation(1.5F, -5.0F, -0.5F, 0.3490658503988659F, 0.0F, 0.0F));
        PartDefinition leg_back_right_rotation_point = shoulders_back.addOrReplaceChild("leg_back_right_rotation_point", CubeListBuilder.create().texOffs(5, 0).addBox(0.0F, 0.0F, 0.0F, 1, 1, 1), PartPose.offset(1.0F, 2.9F, 2.0F));
        PartDefinition leg_back_right = leg_back_right_rotation_point.addOrReplaceChild("leg_back_right", CubeListBuilder.create().texOffs(72, 0).addBox(-1.0F, -10.0F, -1.0F, 2, 10, 2), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6981317007977318F));
        leg_back_right.addOrReplaceChild("leg_back_right_top", CubeListBuilder.create().texOffs(78, 13).addBox(-2.0F, 0.0F, 0.0F, 2, 10, 1), PartPose.offsetAndRotation(1.0F, -9.0F, -0.5F, 0.0F, 0.0F, -0.22689280275926282F));
        PartDefinition leg_back_right2 = leg_back_right.addOrReplaceChild("leg_back_right2", CubeListBuilder.create().texOffs(74, 41).addBox(-3.0F, 0.0F, -2.5F, 3, 10, 5), PartPose.offsetAndRotation(1.0F, -10.0F, 0.0F, 0.0F, 0.0F, 0.9599310885968813F));
        PartDefinition leg_back_right3 = leg_back_right2.addOrReplaceChild("leg_back_right3", CubeListBuilder.create().texOffs(95, 94).addBox(0.0F, 0.0F, -2.0F, 2, 7, 4), PartPose.offsetAndRotation(-2.5F, 10.0F, 0.0F, 0.0F, 0.0F, -0.17453292519943295F));
        leg_back_right3.addOrReplaceChild("spike_5", CubeListBuilder.create().texOffs(90, 94).addBox(0.0F, -3.0F, 0.0F, 1, 3, 1), PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, 0.0F, 0.0F, -0.5235987755982988F));
        leg_back_right3.addOrReplaceChild("leg_back_right4", CubeListBuilder.create().texOffs(86, 99).addBox(0.0F, 0.0F, -1.5F, 1, 3, 3), PartPose.offsetAndRotation(0.5F, 7.0F, 0.0F, 0.0F, 0.0F, -0.17453292519943295F));
        leg_back_right2.addOrReplaceChild("spike_4", CubeListBuilder.create().texOffs(76, 101).addBox(0.0F, -3.0F, -1.0F, 1, 3, 1), PartPose.offsetAndRotation(-2.0F, 5.0F, 2.5F, -0.5235987755982988F, 0.0F, 0.0F));
        PartDefinition spike_2 = leg_back_right2.addOrReplaceChild("spike_2", CubeListBuilder.create().texOffs(80, 96).addBox(0.0F, -1.0F, 0.0F, 1, 1, 3), PartPose.offset(-2.0F, 0.0F, -1.5F));
        spike_2.addOrReplaceChild("spike_6", CubeListBuilder.create().texOffs(78, 95).addBox(0.0F, 0.0F, 0.0F, 1, 2, 1), PartPose.offset(0.0F, -3.0F, 1.0F));
        leg_back_right2.addOrReplaceChild("spike_3", CubeListBuilder.create().texOffs(81, 101).addBox(0.0F, -3.0F, 0.0F, 1, 3, 1), PartPose.offsetAndRotation(-2.0F, 5.0F, -2.5F, 0.5235987755982988F, 0.0F, 0.0F));
        shoulders_back.addOrReplaceChild("shape48", CubeListBuilder.create().texOffs(145, 29).addBox(-4.0F, 0.0F, -6.0F, 4, 4, 6), PartPose.offsetAndRotation(16.0F, 0.01F, 0.0F, 0.0F, 0.7853981633974483F, 0.0F));
        PartDefinition leg_back_left_rotation_point = shoulders_back.addOrReplaceChild("leg_back_left_rotation_point", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1), PartPose.offset(15.0F, 3.0F, 2.0F));
        PartDefinition leg_back_left = leg_back_left_rotation_point.addOrReplaceChild("leg_back_left", CubeListBuilder.create().texOffs(90, 0).addBox(-1.0F, -10.0F, -1.0F, 2, 10, 2), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6981317007977318F));
        PartDefinition leg_back_left2 = leg_back_left.addOrReplaceChild("leg_back_left2", CubeListBuilder.create().texOffs(74, 25).addBox(0.0F, 0.0F, -2.5F, 3, 10, 5), PartPose.offsetAndRotation(-1.0F, -10.0F, 0.0F, 0.0F, 0.0F, -0.9599310885968813F));
        leg_back_left2.addOrReplaceChild("spike_9", CubeListBuilder.create().texOffs(76, 77).addBox(-1.0F, -3.0F, -1.0F, 1, 3, 1), PartPose.offsetAndRotation(2.0F, 5.0F, 2.5F, -0.5235987755982988F, 0.0F, 0.0F));
        leg_back_left2.addOrReplaceChild("spike_8", CubeListBuilder.create().texOffs(81, 77).addBox(-1.0F, -3.0F, 0.0F, 1, 3, 1), PartPose.offsetAndRotation(2.0F, 5.0F, -2.5F, 0.5235987755982988F, 0.0F, 0.0F));
        PartDefinition leg_back_left3 = leg_back_left2.addOrReplaceChild("leg_back_left3", CubeListBuilder.create().texOffs(95, 70).addBox(-2.0F, 0.0F, -2.0F, 2, 7, 4), PartPose.offsetAndRotation(2.5F, 10.0F, 0.0F, 0.0F, 0.0F, 0.17453292519943295F));
        leg_back_left3.addOrReplaceChild("leg_back_left_4", CubeListBuilder.create().texOffs(86, 75).addBox(-1.0F, 0.0F, -1.5F, 1, 3, 3), PartPose.offsetAndRotation(-0.5F, 7.0F, 0.0F, 0.0F, 0.0F, 0.17453292519943295F));
        leg_back_left3.addOrReplaceChild("spike_10", CubeListBuilder.create().texOffs(90, 70).addBox(-1.0F, -3.0F, 0.0F, 1, 3, 1), PartPose.offsetAndRotation(0.0F, 5.0F, -0.5F, 0.0F, 0.0F, 0.5235987755982988F));
        PartDefinition spike_7 = leg_back_left2.addOrReplaceChild("spike_7", CubeListBuilder.create().texOffs(80, 72).addBox(0.0F, -1.0F, 0.0F, 1, 1, 3), PartPose.offset(1.0F, 0.0F, -1.5F));
        spike_7.addOrReplaceChild("spike_11", CubeListBuilder.create().texOffs(78, 71).addBox(0.0F, 0.0F, 0.0F, 1, 2, 1), PartPose.offset(0.0F, -3.0F, 1.0F));
        leg_back_left.addOrReplaceChild("leg_back_left_top", CubeListBuilder.create().texOffs(92, 13).addBox(0.0F, 0.0F, 0.0F, 2, 10, 1), PartPose.offsetAndRotation(-1.0F, -9.0F, -0.5F, 0.0F, 0.0F, 0.22689280275926282F));
        PartDefinition body_top_plate2 = body3.addOrReplaceChild("body_top_plate2", CubeListBuilder.create().texOffs(173, 17).addBox(-3.0F, 0.0F, -3.0F, 6, 1, 6), PartPose.offset(0.0F, -5.0F, 0.0F));
        body_top_plate2.addOrReplaceChild("spike1", CubeListBuilder.create().texOffs(181, 26).addBox(0.0F, -1.0F, 0.0F, 1, 1, 1), PartPose.offsetAndRotation(-0.5F, 1.0F, -3.0F, 0.7853981633974483F, 0.0F, 0.0F));
        body_top_plate2.addOrReplaceChild("spike2", CubeListBuilder.create().texOffs(186, 25).addBox(0.0F, -2.0F, 0.0F, 1, 2, 1), PartPose.offset(-0.5F, 0.8F, -0.5F));
        body_top_plate2.addOrReplaceChild("spike3", CubeListBuilder.create().texOffs(191, 25).addBox(0.0F, -2.0F, -1.0F, 1, 2, 1), PartPose.offsetAndRotation(-0.5F, 1.5F, 3.0F, -0.7853981633974483F, 0.0F, 0.0F));
        PartDefinition spike_holder_2 = body3.addOrReplaceChild("spike_holder_2", CubeListBuilder.create().texOffs(42, 0).addBox(0.0F, 0.0F, 0.0F, 3, 2, 4), PartPose.offsetAndRotation(4.0F, -1.4F, -0.5F, 0.0F, 0.0F, 0.5585053606381855F));
        spike_holder_2.addOrReplaceChild("spike_12", CubeListBuilder.create().texOffs(52, 7).addBox(0.0F, -2.0F, -1.0F, 1, 2, 1), PartPose.offsetAndRotation(1.0F, 0.0F, 1.0F, 0.7853981633974483F, 0.0F, 0.0F));
        spike_holder_2.addOrReplaceChild("spike_14", CubeListBuilder.create().texOffs(47, 7).addBox(0.0F, -2.0F, 0.0F, 1, 2, 1), PartPose.offset(1.0F, 0.3F, 1.5F));
        spike_holder_2.addOrReplaceChild("spike_13", CubeListBuilder.create().texOffs(42, 7).addBox(0.0F, -2.0F, 0.0F, 1, 2, 1), PartPose.offsetAndRotation(1.0F, 0.0F, 3.0F, -0.7853981633974483F, 0.0F, 0.0F));
        PartDefinition back_cover_top = body3.addOrReplaceChild("back_cover_top", CubeListBuilder.create().texOffs(199, 14).addBox(0.0F, 0.0F, -1.0F, 6, 4, 1), PartPose.offsetAndRotation(-3.0F, -3.5F, 4.0F, 0.24434609527920614F, 0.0F, 0.0F));
        PartDefinition big_spike_2 = back_cover_top.addOrReplaceChild("big_spike_2", CubeListBuilder.create().texOffs(21, 0).addBox(0.0F, -5.0F, -2.0F, 2, 5, 2), PartPose.offsetAndRotation(4.0F, 3.0F, -2.5F, -2.1816615649929116F, 0.0F, 3.141592653589793F));
        big_spike_2.addOrReplaceChild("spike_18", CubeListBuilder.create().texOffs(21, 8).addBox(-1.0F, -3.0F, -1.0F, 1, 3, 1), PartPose.offsetAndRotation(1.5F, -5.0F, -0.5F, 0.3490658503988659F, 0.0F, 0.0F));
        PartDefinition spike_holder = body.addOrReplaceChild("spike_holder", CubeListBuilder.create().texOffs(57, 0).addBox(0.0F, 0.0F, 0.0F, 3, 2, 4), PartPose.offsetAndRotation(4.0F, -1.4F, -3.5F, 0.0F, 0.0F, 0.5585053606381855F));
        spike_holder.addOrReplaceChild("spike_29", CubeListBuilder.create().texOffs(67, 7).addBox(0.0F, -2.0F, -1.0F, 1, 2, 1), PartPose.offsetAndRotation(1.0F, 0.0F, 1.0F, 0.7853981633974483F, 0.0F, 0.0F));
        spike_holder.addOrReplaceChild("spike_30", CubeListBuilder.create().texOffs(57, 7).addBox(0.0F, -2.0F, 0.0F, 1, 2, 1), PartPose.offsetAndRotation(1.0F, 0.0F, 3.0F, -0.7853981633974483F, 0.0F, 0.0F));
        spike_holder.addOrReplaceChild("spike_31", CubeListBuilder.create().texOffs(62, 7).addBox(0.0F, -2.0F, 0.0F, 1, 2, 1), PartPose.offset(1.0F, 0.3F, 1.5F));
        PartDefinition spike_holder_1 = body.addOrReplaceChild("spike_holder_1", CubeListBuilder.create().texOffs(57, 11).addBox(-3.0F, 0.0F, 0.0F, 3, 2, 4), PartPose.offsetAndRotation(-4.0F, -1.4F, -3.5F, 0.0F, 0.0F, -0.5585053606381855F));
        spike_holder_1.addOrReplaceChild("spike_32", CubeListBuilder.create().texOffs(67, 18).addBox(-1.0F, -2.0F, -1.0F, 1, 2, 1), PartPose.offsetAndRotation(-1.0F, 0.0F, 1.0F, 0.7853981633974483F, 0.0F, 0.0F));
        spike_holder_1.addOrReplaceChild("spike_33", CubeListBuilder.create().texOffs(62, 18).addBox(-1.0F, -2.0F, -1.0F, 1, 2, 1), PartPose.offset(-1.0F, 0.3F, 2.5F));
        spike_holder_1.addOrReplaceChild("spike_34", CubeListBuilder.create().texOffs(57, 18).addBox(-1.0F, -2.0F, 0.0F, 1, 2, 1), PartPose.offsetAndRotation(-1.0F, 0.0F, 3.0F, -0.7853981633974483F, 0.0F, 0.0F));
        PartDefinition body_head_holder = body.addOrReplaceChild("body_head_holder", CubeListBuilder.create().texOffs(198, 20).addBox(0.0F, 0.0F, 0.0F, 6, 4, 1), PartPose.offsetAndRotation(-3.0F, -3.9F, -4.0F, -0.22689280275926282F, 0.0F, 0.0F));
        PartDefinition neck = body_head_holder.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(108, 0).addBox(-2.0F, 0.0F, -4.0F, 4, 4, 4), PartPose.offsetAndRotation(3.0F, 1.0F, 1.0F, 0.08726646259971647F, 0.0F, 0.0F));
        neck.addOrReplaceChild("neck_front", CubeListBuilder.create().texOffs(108, 18).addBox(-2.0F, 0.0F, -2.0F, 4, 4, 2), PartPose.offsetAndRotation(0.01F, 0.0F, -4.0F, 0.27314402793711257F, 0.0F, 0.0F));
        PartDefinition neck_bottom = neck.addOrReplaceChild("neck_bottom", CubeListBuilder.create().texOffs(106, 9).addBox(0.0F, -2.0F, 0.0F, 3, 2, 6), PartPose.offsetAndRotation(-1.5F, 4.0F, -4.0F, -0.3141592653589793F, 0.0F, 0.0F));
        PartDefinition head = neck_bottom.addOrReplaceChild("head", CubeListBuilder.create().texOffs(239, 64).addBox(-2.0F, -1.0F, -4.0F, 4, 1, 4), PartPose.offsetAndRotation(1.5F, -2.5F, -4.0F, 0.6108652381980153F, 0.0F, 0.0F));
        PartDefinition tooth_top_mid_right = head.addOrReplaceChild("tooth_top_mid_right", CubeListBuilder.create().texOffs(185, 65).addBox(0.0F, 0.0F, 0.0F, 1, 2, 1), PartPose.offsetAndRotation(-1.5F, 0.0F, -4.8F, -0.3490658503988659F, 0.0F, 0.0F));
        tooth_top_mid_right.addOrReplaceChild("tooth_top_mid_right_2", CubeListBuilder.create().texOffs(185, 69).addBox(0.0F, 0.0F, 0.0F, 1, 1, 1), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.17453292519943295F, 0.0F, 0.0F));
        head.addOrReplaceChild("head_side_right", CubeListBuilder.create().texOffs(210, 50).addBox(-1.0F, 0.0F, 0.0F, 1, 6, 3), PartPose.offsetAndRotation(-1.0F, -4.0F, -2.0F, 0.0F, 0.0F, 0.20943951023931953F));
        PartDefinition lip_up_1 = head.addOrReplaceChild("lip_up_1", CubeListBuilder.create().texOffs(245, 70).addBox(0.0F, 0.0F, -1.0F, 4, 1, 1), PartPose.offsetAndRotation(-2.0F, -1.0F, -4.0F, 0.6981317007977318F, 0.0F, 0.0F));
        lip_up_1.addOrReplaceChild("lip_up_2", CubeListBuilder.create().texOffs(245, 73).addBox(0.0F, 0.0F, -1.0F, 4, 1, 1), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.6981317007977318F, 0.0F, 0.0F));
        head.addOrReplaceChild("mouth_left", CubeListBuilder.create().texOffs(208, 64).addBox(0.0F, 0.0F, 0.0F, 1, 2, 5), PartPose.offsetAndRotation(1.0F, 0.0F, -4.0F, -0.08726646259971647F, 0.17453292519943295F, -0.45378560551852565F));
        head.addOrReplaceChild("head_side_left", CubeListBuilder.create().texOffs(201, 50).addBox(0.0F, 0.0F, 0.0F, 1, 6, 3), PartPose.offsetAndRotation(1.0F, -4.0F, -2.0F, 0.0F, 0.0F, -0.20943951023931953F));
        head.addOrReplaceChild("mouth_right", CubeListBuilder.create().texOffs(208, 72).addBox(-1.0F, 0.0F, 0.0F, 1, 2, 5), PartPose.offsetAndRotation(-1.0F, 0.0F, -4.0F, -0.08726646259971647F, -0.17453292519943295F, 0.45378560551852565F));
        PartDefinition mouth_bottom = head.addOrReplaceChild("mouth_bottom", CubeListBuilder.create().texOffs(219, 60).addBox(-3.0F, 0.0F, -5.0F, 6, 1, 5), PartPose.offsetAndRotation(0.0F, 5.5F, 0.5F, 0.03490658503988659F, 0.0F, 0.0F));
        PartDefinition tooth_bottom_left = mouth_bottom.addOrReplaceChild("tooth_bottom_left", CubeListBuilder.create().texOffs(180, 73).addBox(-1.0F, -2.0F, -1.0F, 1, 2, 1), PartPose.offsetAndRotation(2.2F, 0.3F, -4.0F, 0.13962634015954636F, -1.3089969389957472F, 0.6108652381980153F));
        tooth_bottom_left.addOrReplaceChild("tooth_bottom_left_2", CubeListBuilder.create().texOffs(180, 77).addBox(0.0F, -2.0F, 0.0F, 1, 2, 1), PartPose.offsetAndRotation(-1.0F, -2.0F, -1.0F, -0.5235987755982988F, 0.0F, 0.0F));
        mouth_bottom.addOrReplaceChild("mouth_bottom_lip_front", CubeListBuilder.create().texOffs(207, 60).addBox(0.0F, -1.0F, -2.0F, 6, 1, 2), PartPose.offsetAndRotation(-3.0F, 1.0F, -5.0F, -0.5235987755982988F, 0.0F, 0.0F));
        PartDefinition mouth_bottom_side_left = mouth_bottom.addOrReplaceChild("mouth_bottom_side_left", CubeListBuilder.create().texOffs(232, 67).addBox(-1.0F, -3.0F, -4.0F, 1, 3, 4), PartPose.offsetAndRotation(3.0F, 1.0F, 0.0F, -0.08726646259971647F, 0.0F, 0.22689280275926282F));
        mouth_bottom_side_left.addOrReplaceChild("mouth_bottom_side_left_2", CubeListBuilder.create().texOffs(221, 67).addBox(-1.0F, -3.0F, -4.0F, 1, 3, 4), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 0.0F, -0.6981317007977318F));
        PartDefinition mouth_bottom_side_right = mouth_bottom.addOrReplaceChild("mouth_bottom_side_right", CubeListBuilder.create().texOffs(232, 75).addBox(0.0F, -3.0F, -4.0F, 1, 3, 4), PartPose.offsetAndRotation(-3.0F, 1.0F, 0.0F, -0.08726646259971647F, 0.0F, -0.22689280275926282F));
        mouth_bottom_side_right.addOrReplaceChild("mouth_bottom_side_right_2", CubeListBuilder.create().texOffs(221, 75).addBox(0.0F, -3.0F, -4.0F, 1, 3, 4), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 0.0F, 0.6981317007977318F));
        PartDefinition tooth_bottom_mid = mouth_bottom.addOrReplaceChild("tooth_bottom_mid", CubeListBuilder.create().texOffs(175, 69).addBox(0.0F, -2.0F, -1.0F, 1, 2, 1), PartPose.offsetAndRotation(-0.5F, 0.0F, -4.9F, 0.17453292519943295F, 0.0F, 0.0F));
        tooth_bottom_mid.addOrReplaceChild("tooth_bottom_left_1", CubeListBuilder.create().texOffs(175, 73).addBox(0.0F, -2.0F, -1.0F, 1, 2, 1), PartPose.offset(1.8F, 0.0F, 0.0F));
        tooth_bottom_mid.addOrReplaceChild("tooth_bottom_right_1", CubeListBuilder.create().texOffs(175, 65).addBox(0.0F, -2.0F, -1.0F, 1, 2, 1), PartPose.offset(-1.8F, 0.0F, 0.0F));
        PartDefinition tooth_bottom_right = mouth_bottom.addOrReplaceChild("tooth_bottom_right", CubeListBuilder.create().texOffs(180, 65).addBox(0.0F, -2.0F, -1.0F, 1, 2, 1), PartPose.offsetAndRotation(-2.2F, 0.3F, -4.0F, 0.13962634015954636F, 1.3089969389957472F, -0.6108652381980153F));
        tooth_bottom_right.addOrReplaceChild("tooth_bottom_right_2", CubeListBuilder.create().texOffs(180, 69).addBox(0.0F, -2.0F, 0.0F, 1, 2, 1), PartPose.offsetAndRotation(0.0F, -2.0F, -1.0F, -0.5235987755982988F, 0.0F, 0.0F));
        PartDefinition head_top = head.addOrReplaceChild("head_top", CubeListBuilder.create().texOffs(219, 50).addBox(0.0F, 0.0F, 0.0F, 4, 1, 3), PartPose.offset(-2.0F, -4.1F, -2.0F));
        PartDefinition head_back_lid = head_top.addOrReplaceChild("head_back_lid", CubeListBuilder.create().texOffs(245, 50).addBox(0.0F, 0.0F, -1.0F, 4, 9, 1), PartPose.offset(0.0F, 1.0F, 3.0F));
        head_back_lid.addOrReplaceChild("head_back_lid_right", CubeListBuilder.create().texOffs(224, 55).addBox(-1.0F, 0.0F, 0.0F, 1, 3, 1), PartPose.offset(0.0F, 5.6F, -1.0F));
        head_back_lid.addOrReplaceChild("head_back_lid_left", CubeListBuilder.create().texOffs(229, 55).addBox(0.0F, 0.0F, 0.0F, 1, 3, 1), PartPose.offset(4.0F, 5.6F, -1.0F));
        head_top.addOrReplaceChild("eye_protection_right", CubeListBuilder.create().texOffs(189, 50).addBox(0.0F, 0.0F, 0.0F, 1, 4, 1), PartPose.offsetAndRotation(0.2F, 0.0F, -0.2F, -0.3141592653589793F, 0.2617993877991494F, 0.0F));
        head_top.addOrReplaceChild("eye_protection_left", CubeListBuilder.create().texOffs(189, 56).addBox(-1.0F, 0.0F, 0.0F, 1, 4, 1), PartPose.offsetAndRotation(3.8F, 0.0F, 0.0F, -0.3141592653589793F, -0.2617993877991494F, 0.0F));
        head_top.addOrReplaceChild("head_back_con", CubeListBuilder.create().texOffs(234, 50).addBox(0.0F, 0.0F, -2.0F, 3, 6, 2), PartPose.offsetAndRotation(0.5F, 0.0F, 3.0F, 0.36425021489121656F, 0.0F, 0.0F));
        PartDefinition tooth_top_mid_left = head.addOrReplaceChild("tooth_top_mid_left", CubeListBuilder.create().texOffs(185, 72).addBox(0.0F, 0.0F, 0.0F, 1, 2, 1), PartPose.offsetAndRotation(0.5F, 0.0F, -4.8F, -0.3490658503988659F, 0.0F, 0.0F));
        tooth_top_mid_left.addOrReplaceChild("tooth_top_mid_left_2", CubeListBuilder.create().texOffs(185, 76).addBox(0.0F, 0.0F, 0.0F, 1, 1, 1), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.17453292519943295F, 0.0F, 0.0F));
        head.addOrReplaceChild("mouth_up_front", CubeListBuilder.create().texOffs(243, 76).addBox(0.0F, 0.0F, -1.0F, 5, 1, 1), PartPose.offsetAndRotation(-2.5F, 0.7F, -3.9F, -0.136659280431156F, 0.0F, 0.0F));
        PartDefinition tooth_top_left = head.addOrReplaceChild("tooth_top_left", CubeListBuilder.create().texOffs(190, 73).addBox(0.0F, 0.0F, -1.0F, 1, 2, 1), PartPose.offsetAndRotation(1.3F, 0.3F, -4.0F, -0.6981317007977318F, -0.6108652381980153F, 0.0F));
        tooth_top_left.addOrReplaceChild("tooth_top_left_2", CubeListBuilder.create().texOffs(190, 77).addBox(0.0F, 0.0F, 0.0F, 1, 2, 1), PartPose.offsetAndRotation(0.0F, 2.0F, -1.0F, 0.5235987755982988F, 0.0F, 0.0F));
        PartDefinition tooth_top_right = head.addOrReplaceChild("tooth_top_right", CubeListBuilder.create().texOffs(190, 65).addBox(-1.0F, 0.0F, -1.0F, 1, 2, 1), PartPose.offsetAndRotation(-1.3F, 0.3F, -4.0F, -0.6981317007977318F, 0.6108652381980153F, 0.0F));
        tooth_top_right.addOrReplaceChild("tooth_top_right_2", CubeListBuilder.create().texOffs(190, 69).addBox(0.0F, 0.0F, 0.0F, 1, 2, 1), PartPose.offsetAndRotation(-1.0F, 2.0F, -1.0F, 0.5235987755982988F, 0.0F, 0.0F));
        PartDefinition eye = head.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(194, 50).addBox(0.0F, -3.0F, -1.0F, 2, 3, 1), PartPose.offsetAndRotation(-1.0F, -0.8F, -2.0F, -0.17453292519943295F, 0.0F, 0.0F));
        PartDefinition eye_brow = eye.addOrReplaceChild("eye_brow", CubeListBuilder.create().texOffs(251, 61).addBox(0.0F, 0.0F, 0.0F, 1, 1, 1), PartPose.offset(0.5F, -3.1F, -1.5F));
        eye_brow.addOrReplaceChild("eye_brow_right", CubeListBuilder.create().texOffs(244, 61).addBox(-2.0F, -1.0F, 0.0F, 2, 1, 1), PartPose.offsetAndRotation(0.0F, 1.0F, 0.01F, 0.0F, 0.0F, 0.2617993877991494F));
        eye_brow.addOrReplaceChild("eye_brow_left", CubeListBuilder.create().texOffs(237, 61).addBox(0.0F, -1.0F, 0.0F, 2, 1, 1), PartPose.offsetAndRotation(1.0F, 1.0F, 0.01F, 0.0F, 0.0F, -0.2617993877991494F));
        eye.addOrReplaceChild("eye_ball", CubeListBuilder.create().texOffs(195, 55).addBox(0.0F, 0.0F, 0.0F, 1, 1, 1), PartPose.offset(0.5F, -1.6F, -1.2F));
        head.addOrReplaceChild("mouth_up_inside", CubeListBuilder.create().texOffs(235, 83).addBox(0.0F, 0.0F, 0.0F, 5, 1, 5), PartPose.offset(-2.5F, 0.4F, -4.2F));
        PartDefinition body_top_plate = body.addOrReplaceChild("body_top_plate", CubeListBuilder.create().texOffs(231, 17).addBox(-3.0F, 0.0F, -3.0F, 6, 1, 6), PartPose.offset(0.0F, -5.0F, 0.0F));
        body_top_plate.addOrReplaceChild("spike2_1", CubeListBuilder.create().texOffs(171, 25).addBox(0.0F, -2.0F, 0.0F, 1, 2, 1), PartPose.offset(-0.5F, 0.8F, -0.5F));
        body_top_plate.addOrReplaceChild("spike1_1", CubeListBuilder.create().texOffs(166, 25).addBox(0.0F, -2.0F, 0.0F, 1, 2, 1), PartPose.offsetAndRotation(-0.5F, 1.5F, -3.0F, 0.7853981633974483F, 0.0F, 0.0F));
        body_top_plate.addOrReplaceChild("spike3_1", CubeListBuilder.create().texOffs(176, 26).addBox(0.0F, -1.0F, -1.0F, 1, 1, 1), PartPose.offsetAndRotation(-0.5F, 1.0F, 3.0F, -0.7853981633974483F, 0.0F, 0.0F));
        PartDefinition shoulders_front = body.addOrReplaceChild("shoulders_front", CubeListBuilder.create().texOffs(125, 0).addBox(0.0F, 0.0F, 0.0F, 16, 4, 4), PartPose.offset(-8.0F, 0.01F, -5.0F));
        shoulders_front.addOrReplaceChild("shape49", CubeListBuilder.create().texOffs(124, 18).addBox(0.0F, 0.0F, 0.0F, 4, 4, 6), PartPose.offsetAndRotation(0.0F, 0.01F, 4.0F, 0.0F, 0.7853981633974483F, 0.0F));
        PartDefinition leg_front_right_rotation_point = shoulders_front.addOrReplaceChild("leg_front_right_rotation_point", CubeListBuilder.create().texOffs(0, 3).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1), PartPose.offset(1.0F, 3.0F, 2.0F));
        PartDefinition leg_front_right = leg_front_right_rotation_point.addOrReplaceChild("leg_front_right", CubeListBuilder.create().texOffs(81, 0).addBox(-1.0F, -10.0F, -1.0F, 2, 10, 2), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6981317007977318F));
        leg_front_right.addOrReplaceChild("leg_front_right_top", CubeListBuilder.create().texOffs(85, 13).addBox(-2.0F, 0.0F, 0.0F, 2, 10, 1), PartPose.offsetAndRotation(1.0F, -9.0F, -0.5F, 0.0F, 0.0F, -0.22689280275926282F));
        PartDefinition leg_front_right2 = leg_front_right.addOrReplaceChild("leg_front_right2", CubeListBuilder.create().texOffs(91, 41).addBox(-3.0F, 0.0F, -2.5F, 3, 10, 5), PartPose.offsetAndRotation(1.0F, -10.0F, 0.0F, 0.0F, 0.0F, 0.9599310885968813F));
        leg_front_right2.addOrReplaceChild("spike_25", CubeListBuilder.create().texOffs(81, 89).addBox(0.0F, -3.0F, 0.0F, 1, 3, 1), PartPose.offsetAndRotation(-2.0F, 5.0F, -2.5F, 0.5235987755982988F, 0.0F, 0.0F));
        PartDefinition leg_front_right3 = leg_front_right2.addOrReplaceChild("leg_front_right3", CubeListBuilder.create().texOffs(95, 82).addBox(0.0F, 0.0F, -2.0F, 2, 7, 4), PartPose.offsetAndRotation(-2.5F, 10.0F, 0.0F, 0.0F, 0.0F, -0.17453292519943295F));
        leg_front_right3.addOrReplaceChild("spike_27", CubeListBuilder.create().texOffs(90, 82).addBox(0.0F, -3.0F, 0.0F, 1, 3, 1), PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, 0.0F, 0.0F, -0.5235987755982988F));
        leg_front_right3.addOrReplaceChild("leg_front_right4", CubeListBuilder.create().texOffs(86, 87).addBox(0.0F, 0.0F, -1.5F, 1, 3, 3), PartPose.offsetAndRotation(0.5F, 7.0F, 0.0F, 0.0F, 0.0F, -0.17453292519943295F));
        PartDefinition spike_24 = leg_front_right2.addOrReplaceChild("spike_24", CubeListBuilder.create().texOffs(80, 84).addBox(0.0F, -1.0F, 0.0F, 1, 1, 3), PartPose.offset(-2.0F, 0.0F, -1.5F));
        spike_24.addOrReplaceChild("spike_28", CubeListBuilder.create().texOffs(78, 83).addBox(0.0F, 0.0F, 0.0F, 1, 2, 1), PartPose.offset(0.0F, -3.0F, 1.0F));
        leg_front_right2.addOrReplaceChild("spike_26", CubeListBuilder.create().texOffs(76, 89).addBox(0.0F, -3.0F, -1.0F, 1, 3, 1), PartPose.offsetAndRotation(-2.0F, 5.0F, 2.5F, -0.5235987755982988F, 0.0F, 0.0F));
        shoulders_front.addOrReplaceChild("shape50", CubeListBuilder.create().texOffs(145, 18).addBox(-4.0F, 0.0F, 0.0F, 4, 4, 6), PartPose.offsetAndRotation(16.0F, 0.01F, 4.0F, 0.0F, -0.7853981633974483F, 0.0F));
        PartDefinition leg_front_left_rotation_point = shoulders_front.addOrReplaceChild("leg_front_left_rotation_point", CubeListBuilder.create().texOffs(5, 3).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1), PartPose.offset(15.0F, 3.0F, 2.0F));
        PartDefinition leg_front_left = leg_front_left_rotation_point.addOrReplaceChild("leg_front_left", CubeListBuilder.create().texOffs(99, 0).addBox(-1.0F, -10.0F, -1.0F, 2, 10, 2), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6981317007977318F));
        PartDefinition leg_front_left2 = leg_front_left.addOrReplaceChild("leg_front_left2", CubeListBuilder.create().texOffs(91, 25).addBox(0.0F, 0.0F, -2.5F, 3, 10, 5), PartPose.offsetAndRotation(-1.0F, -10.0F, 0.0F, 0.0F, 0.0F, -0.9599310885968813F));
        leg_front_left2.addOrReplaceChild("spike_21", CubeListBuilder.create().texOffs(76, 64).addBox(-1.0F, -3.0F, -1.0F, 1, 3, 1), PartPose.offsetAndRotation(2.0F, 5.0F, 2.5F, -0.5235987755982988F, 0.0F, 0.0F));
        PartDefinition spike_19 = leg_front_left2.addOrReplaceChild("spike_19", CubeListBuilder.create().texOffs(80, 59).addBox(0.0F, -1.0F, 0.0F, 1, 1, 3), PartPose.offset(1.0F, 0.0F, -1.5F));
        spike_19.addOrReplaceChild("spike_23", CubeListBuilder.create().texOffs(78, 58).addBox(0.0F, 0.0F, 0.0F, 1, 2, 1), PartPose.offset(0.0F, -3.0F, 1.0F));
        PartDefinition leg_front_left3 = leg_front_left2.addOrReplaceChild("leg_front_left3", CubeListBuilder.create().texOffs(95, 57).addBox(-2.0F, 0.0F, -2.0F, 2, 7, 4), PartPose.offsetAndRotation(2.5F, 10.0F, 0.0F, 0.0F, 0.0F, 0.17453292519943295F));
        leg_front_left3.addOrReplaceChild("leg_front_left_4", CubeListBuilder.create().texOffs(86, 62).addBox(-1.0F, 0.0F, -1.5F, 1, 3, 3), PartPose.offsetAndRotation(-0.5F, 7.0F, 0.0F, 0.0F, 0.0F, 0.17453292519943295F));
        leg_front_left3.addOrReplaceChild("spike_22", CubeListBuilder.create().texOffs(90, 57).addBox(-1.0F, -3.0F, 0.0F, 1, 3, 1), PartPose.offsetAndRotation(0.0F, 5.0F, -0.5F, 0.0F, 0.0F, 0.5235987755982988F));
        leg_front_left2.addOrReplaceChild("spike_20", CubeListBuilder.create().texOffs(81, 64).addBox(-1.0F, -3.0F, 0.0F, 1, 3, 1), PartPose.offsetAndRotation(2.0F, 5.0F, -2.5F, 0.5235987755982988F, 0.0F, 0.0F));
        leg_front_left.addOrReplaceChild("leg_front_left_top", CubeListBuilder.create().texOffs(99, 13).addBox(0.0F, 0.0F, 0.0F, 2, 10, 1), PartPose.offsetAndRotation(-1.0F, -9.0F, -0.5F, 0.0F, 0.0F, 0.22689280275926282F));

        return LayerDefinition.create(meshdefinition, 256, 128);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        poseStack.pushPose();
        poseStack.translate(0.0D, -0.1D, 0.0D);
        poseStack.scale(1.4F, 1.4F, 1.4F);
        this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        poseStack.popPose();
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        if(entity.isEntityMoving()) //Walk Animation
            	{
            		float globalHeight = 1;
            		float globalSpeed = 1.5F;
            		float globalDegree = 1;

            		revertToDefaultBoxValues();

            		//Makes the Head Rotate where the Entity is Looking
            		this.neck.yRot = (netHeadYaw * ((float)Math.PI / 180)) / 2;
            		this.head.yRot = (netHeadYaw * ((float)Math.PI / 180)) / 4;
            		this.head.zRot = (netHeadYaw * ((float)Math.PI / 180)) / 4;

            		bounce(body, 0.2F * globalSpeed, 3.0F * globalHeight, true, limbSwing, limbSwingAmount);

            		//Head
            		swing(neck, 0.1F * globalSpeed, 0.2F * globalDegree, false, -0.2F, 0.05F, limbSwing, limbSwingAmount);
            		shake(neck, 0.2F * globalSpeed, 0.06F * globalDegree, true, -0.4F, 0.0F, limbSwing, limbSwingAmount);
            		swing(neck_front, 0.1F * globalSpeed, 0.2F * globalDegree, true, 0.2F, 0.0F, limbSwing, limbSwingAmount);
            		swing(head, 0.1F * globalSpeed, 0.2F * globalDegree, true, 0.6F, 0.0F, limbSwing, limbSwingAmount);
            		bounce(eye_ball, 0.1F * globalSpeed, -0.2F * globalHeight, false, limbSwing, limbSwingAmount);
            		bounce(eye_brow, 0.05F * globalSpeed, -0.3F * globalHeight, true, limbSwing, limbSwingAmount);

            		//Mouth
            		swing(mouth_bottom, 0.3F * globalSpeed, 0.1F * globalDegree, false, 0.4F, 0.0F, limbSwing, limbSwingAmount);

            		//Legs
            		shake(leg_front_left_rotation_point, 0.2F * globalSpeed, 0.5F * globalDegree, false, 0.3F, 0.0F, limbSwing, limbSwingAmount);
            		shake(leg_back_left_rotation_point, 0.2F * globalSpeed, 0.5F * globalDegree, true, 0.3F, 0.0F, limbSwing, limbSwingAmount);
            		shake(leg_back_right_rotation_point, 0.2F * globalSpeed, 0.5F * globalDegree, true, 0.3F, 0.0F, limbSwing, limbSwingAmount);

            		flap(leg_front_left, 0.2F * globalSpeed, 0.5F * globalDegree, false, -1.0F, 0.08F, limbSwing, limbSwingAmount);
            		flap(leg_front_left2, 0.2F * globalSpeed, 0.5F * globalDegree, true, -0.8F, -0.34F, limbSwing, limbSwingAmount);

            		flap(leg_back_left, 0.2F * globalSpeed, 0.5F * globalDegree, true, -1.0F, 0.08F, limbSwing, limbSwingAmount);
            		flap(leg_back_left2, 0.2F * globalSpeed, 0.5F * globalDegree, false, -0.8F, -0.34F, limbSwing, limbSwingAmount);

            		flap(leg_front_right, 0.2F * globalSpeed, 0.5F * globalDegree, false, -1.0F, -0.08F, limbSwing, limbSwingAmount);

            		flap(leg_back_right, 0.2F * globalSpeed, 0.5F * globalDegree, true, -1.0F, -0.08F, limbSwing, limbSwingAmount);
            		flap(leg_back_right2, 0.2F * globalSpeed, 0.5F * globalDegree, false, -0.8F, 0.34F, limbSwing, limbSwingAmount);

            		//Body
            		swing(body, 0.4F * globalSpeed, 0.045F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            		shake(body, 0.2F * globalSpeed, 0.04F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            		shake(body3, 0.2F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);

            		//Front Right Leg
            		if(entity.getAttackTimer() == 0)
                	{
            			shake(leg_front_right_rotation_point, 0.2F * globalSpeed, 0.5F * globalDegree, false, 0.3F, 0.0F, limbSwing, limbSwingAmount);
            			flap(leg_front_right2, 0.2F * globalSpeed, 0.5F * globalDegree, true, -0.8F, 0.34F, limbSwing, limbSwingAmount);
                	}
            		else
            		{
            			limbSwing = entity.tickCount;
                		limbSwingAmount = 1;

                		leg_front_right_rotation_point.xRot += -83;
                		flap(leg_front_right2, 0.1F, 0.4F, false, 3, 1.1F, limbSwing, limbSwingAmount);
                		shake(leg_front_right_rotation_point, 0.4F, 1.2F, false, -2, -1.2F, limbSwing, limbSwingAmount);
            		}
            	}
            	else //Else is Idle Animation
            	{
            		float globalHeight = 1;
            		float globalSpeed = 1F;
            		float globalDegree = 1;

            		revertToDefaultBoxValues();

            		//Makes the Head Rotate where the Entity is Looking
            		this.neck.yRot = (netHeadYaw * ((float)Math.PI / 180)) / 2;
            		this.head.yRot = (netHeadYaw * ((float)Math.PI / 180)) / 4;
            		this.head.zRot = (netHeadYaw * ((float)Math.PI / 180)) / 4;
            	    this.neck.xRot = (headPitch * ((float)Math.PI / 180F)) / 2;

            		limbSwing = entity.tickCount;
            		limbSwingAmount = 1;

            		bounce(body, 0.2F * globalSpeed, 0.25F * globalHeight, false, limbSwing, limbSwingAmount);

            		//Legs
            		flap(leg_front_left, 0.15F * globalSpeed, 0.05F * globalDegree, false, 2.5F, 0.1F, limbSwing, limbSwingAmount);
            		flap(leg_front_left2, 0.15F * globalSpeed, 0.05F * globalDegree, true, 2.5F, -0.1F, limbSwing, limbSwingAmount);

            		flap(leg_back_left, 0.15F * globalSpeed, 0.05F * globalDegree, false, 0.0F, 0.1F, limbSwing, limbSwingAmount);
            		flap(leg_back_left2, 0.15F * globalSpeed, 0.05F * globalDegree, true, 0.0F, -0.1F, limbSwing, limbSwingAmount);

            		flap(leg_front_right, 0.15F * globalSpeed, 0.05F * globalDegree, true, 2.5F, 0.1F, limbSwing, limbSwingAmount);

            		flap(leg_back_right, 0.15F * globalSpeed, 0.05F * globalDegree, true, 0.0F, 0.1F, limbSwing, limbSwingAmount);
            		flap(leg_back_right2, 0.15F * globalSpeed, 0.05F * globalDegree, false, 0.0F, -0.1F, limbSwing, limbSwingAmount);

            		//Head
            		swing(neck, 0.2F * globalSpeed, 0.04F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            		swing(neck_front, 0.2F * globalSpeed, 0.06F * globalDegree, false, 0.0F, -0.1F, limbSwing, limbSwingAmount);
            		swing(mouth_bottom, 0.15F * globalSpeed, 0.08F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            		bounce(eye_ball, 0.1F * globalSpeed, -0.1F * globalHeight, false, limbSwing, limbSwingAmount);
            		bounce(eye_brow, 0.07F * globalSpeed, -0.5F * globalHeight, true, limbSwing, limbSwingAmount);

            		//Body
            		swing(body, 0.2F * globalSpeed, 0.012F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            		swing(body3, 0.2F * globalSpeed, 0.02F * globalDegree, false, 0.5F, 0.0F, limbSwing, limbSwingAmount);

            		//Front Right Leg
            		if(entity.getAttackTimer() == 0)
                	{
            			flap(leg_front_right2, 0.15F * globalSpeed, 0.05F * globalDegree, false, 2.5F, -0.1F, limbSwing, limbSwingAmount);
                	}
            		else
            		{
            			limbSwing = entity.tickCount;
                		limbSwingAmount = 1;

                		leg_front_right_rotation_point.xRot += -83;
                		flap(leg_front_right2, 0.1F, 0.4F, false, 3, 1.1F, limbSwing, limbSwingAmount);
                		shake(leg_front_right_rotation_point, 0.4F, 1.2F, false, -2, -1.2F, limbSwing, limbSwingAmount);
            		}
            	}
        //
    }
}
