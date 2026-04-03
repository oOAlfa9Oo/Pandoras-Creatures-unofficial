package andrews.pandoras_creatures.client.model;

import andrews.pandoras_creatures.client.model.base.PCEntityModel;
import andrews.pandoras_creatures.entities.BufflonEntity;
import andrews.pandoras_creatures.entities.bufflon.BufflonBackAttachmentType;
import andrews.pandoras_creatures.entities.bufflon.BufflonStorageVisuals;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

/**
 * BufflonModel - Complete model with all accessory parts migrated from 1.16.5
 */
public class BufflonModel<T extends BufflonEntity> extends PCEntityModel<T> {
    // Main body
    private final ModelPart body;
    private final ModelPart body_top;
    private final ModelPart body_right;
    private final ModelPart body_left;
    private final ModelPart body_back;
    private final ModelPart body_bottom;

    // Front
    private final ModelPart front_top;
    private final ModelPart front_bottom;
    private final ModelPart neck;
    private final ModelPart head_base;

    // Back
    private final ModelPart back_top;
    private final ModelPart back_bottom;
    private final ModelPart tail_base;
    private final ModelPart tail_1;
    private final ModelPart tail_2;
    private final ModelPart tail_3;
    private final ModelPart tail_4;

    // Legs
    private final ModelPart leg_front_left_1;
    private final ModelPart leg_front_left_2;
    private final ModelPart foot_front_left_base;
    private final ModelPart leg_front_right_1;
    private final ModelPart leg_front_right_2;
    private final ModelPart foot_front_right_base;
    private final ModelPart leg_back_left_1;
    private final ModelPart leg_back_left_2;
    private final ModelPart foot_back_left_base;
    private final ModelPart leg_back_right_1;
    private final ModelPart leg_back_right_2;
    private final ModelPart foot_back_right_base;

    // Saddle base
    private final ModelPart front_seat_base;

    // Storage base and holders
    private final ModelPart storage_base;
    private final ModelPart storage_holder_front_left;
    private final ModelPart storage_holder_front_right;
    private final ModelPart storage_holder_mid_left;
    private final ModelPart storage_holder_mid_right;
    private final ModelPart storage_holder_back_left;
    private final ModelPart storage_holder_back_right;
    private final ModelPart storage_base_holder_bottom;

    // Storage straps
    private final ModelPart storage_strap_front_left;
    private final ModelPart storage_strap_front_right;
    private final ModelPart storage_strap_back_left;
    private final ModelPart storage_strap_back_right;
    private final ModelPart storage_strap_left_1;
    private final ModelPart storage_strap_left_2;
    private final ModelPart storage_strap_left_3;
    private final ModelPart storage_strap_left_holder;
    private final ModelPart storage_strap_right_1;
    private final ModelPart storage_strap_right_2;
    private final ModelPart storage_strap_right_3;
    private final ModelPart storage_strap_right_holder;

    // Seats base and parts
    private final ModelPart seats_base;
    private final ModelPart seats_metal_plate;
    private final ModelPart seats_main_part;
    private final ModelPart seats_mid_metal;
    private final ModelPart seats_mid_metal_back;
    private final ModelPart seats_backpart_front;
    private final ModelPart seats_backpart_back;
    private final ModelPart seats_backpart_back_front;

    // Small storage base and parts
    private final ModelPart smallstorage_base;
    private final ModelPart smallstorage_metal_front_left;
    private final ModelPart smallstorage_metal_front_right;
    private final ModelPart smallstorage_seat_metal;
    private final ModelPart smallstorage_seat_main;
    private final ModelPart smallstorage_back_support;
    private final ModelPart smallstorage_back_dec;
    private final ModelPart smallstorage_box_1;
    private final ModelPart smallstorage_box_1_dec;
    private final ModelPart smallstorage_box_2;
    private final ModelPart smallstorage_box_2_dec;
    private final ModelPart smallstorage_box_2_dec_1;
    private final ModelPart smallstorage_box_2_dec_2;
    private final ModelPart smallstorage_box_3;
    private final ModelPart smallstorage_box_3_dec;
    private final ModelPart smallstorage_rod;
    private final ModelPart smallstorage_rod_1;
    private final ModelPart smallstorage_rod_2;
    private final ModelPart smallstorage_rod_3;
    private final ModelPart smallstorage_rod_4;
    private final ModelPart smallstorage_rod_5;
    private final ModelPart smallstorage_rod_6;
    private final ModelPart smallstorage_rod_7;
    private final ModelPart smallstorage_rod_8;
    private final ModelPart smallstorage_rod_9;
    private final ModelPart smallstorage_rod_10;
    private final ModelPart smallstorage_rod_11;

    // Large storage base and parts
    private final ModelPart largestorage_base;
    private final ModelPart storage_metal_front_left;
    private final ModelPart storage_metal_front_right;
    private final ModelPart storage_metal_back_left;
    private final ModelPart storage_metal_back_right;
    private final ModelPart storage_box_1;
    private final ModelPart storage_box_1_dec;
    private final ModelPart storage_box_2;
    private final ModelPart storage_box_2_dec;
    private final ModelPart storage_box_2_dec_1;
    private final ModelPart storage_box_2_dec_2;
    private final ModelPart storage_box_3;
    private final ModelPart storage_box_3_dec;
    private final ModelPart storage_box_4;
    private final ModelPart storage_box_4_dec;
    private final ModelPart storage_box_5;
    private final ModelPart storage_box_5_dec;
    private final ModelPart storage_box_5_dec_1;
    private final ModelPart storage_box_5_dec_2;
    private final ModelPart storage_box_6;
    private final ModelPart storage_box_6_dec;
    private final ModelPart storage_rod;
    private final ModelPart storage_rod_1;
    private final ModelPart storage_rod_2;
    private final ModelPart storage_rod_3;
    private final ModelPart storage_rod_4;
    private final ModelPart storage_rod_5;
    private final ModelPart storage_rod_6;
    private final ModelPart storage_rod_7;
    private final ModelPart storage_rod_8;
    private final ModelPart storage_rod_9;
    private final ModelPart storage_rod_10;
    private final ModelPart storage_rod_11;
    private final ModelPart storage_rod_12;
    private final ModelPart storage_rod_13;
    private final ModelPart storage_rod_14;
    private final ModelPart storage_rod_15;
    private final ModelPart storage_rod_16;
    private final ModelPart storage_rod_17;
    private final ModelPart storage_rod_18;
    private final ModelPart storage_rod_19;

    public BufflonModel(ModelPart root) {
        super(root);

        this.body = root.getChild("body");
        this.body_top = this.body.getChild("body_top");
        this.body_right = this.body.getChild("body_right");
        this.body_left = this.body.getChild("body_left");
        this.body_back = this.body.getChild("body_back");
        this.body_bottom = this.body.getChild("body_bottom");

        this.front_top = this.body.getChild("front_top");
        this.front_bottom = this.front_top.getChild("front_bottom");
        this.neck = this.front_bottom.getChild("neck");
        this.head_base = this.neck.getChild("head_base");

        this.back_top = this.body_back.getChild("back_top");
        this.back_bottom = this.body_back.getChild("back_bottom");
        this.tail_base = this.back_top.getChild("tail_base");
        this.tail_1 = this.tail_base.getChild("tail_1");
        this.tail_2 = this.tail_1.getChild("tail_2");
        this.tail_3 = this.tail_2.getChild("tail_3");
        this.tail_4 = this.tail_3.getChild("tail_4");

        this.leg_front_left_1 = this.body.getChild("leg_front_left_1");
        this.leg_front_left_2 = this.leg_front_left_1.getChild("leg_front_left_2");
        this.foot_front_left_base = this.leg_front_left_2.getChild("foot_front_left_base");

        this.leg_front_right_1 = this.body.getChild("leg_front_right_1");
        this.leg_front_right_2 = this.leg_front_right_1.getChild("leg_front_right_2");
        this.foot_front_right_base = this.leg_front_right_2.getChild("foot_front_right_base");

        this.leg_back_left_1 = this.body_back.getChild("leg_back_left_1");
        this.leg_back_left_2 = this.leg_back_left_1.getChild("leg_back_left_2");
        this.foot_back_left_base = this.leg_back_left_2.getChild("foot_back_left_base");

        this.leg_back_right_1 = this.body_back.getChild("leg_back_right_1");
        this.leg_back_right_2 = this.leg_back_right_1.getChild("leg_back_right_2");
        this.foot_back_right_base = this.leg_back_right_2.getChild("foot_back_right_base");

        this.front_seat_base = this.body.getChild("front_seat_base");
        this.storage_base = this.body_back.getChild("storage_base");

        // Storage holders
        this.storage_holder_front_left = this.storage_base.getChild("storage_holder_front_left");
        this.storage_holder_front_right = this.storage_base.getChild("storage_holder_front_right");
        this.storage_holder_mid_left = this.storage_base.getChild("storage_holder_mid_left");
        this.storage_holder_mid_right = this.storage_base.getChild("storage_holder_mid_right");
        this.storage_holder_back_left = this.storage_base.getChild("storage_holder_back_left");
        this.storage_holder_back_right = this.storage_base.getChild("storage_holder_back_right");
        this.storage_base_holder_bottom = this.storage_base.getChild("storage_base_holder_bottom");

        // Storage straps
        this.storage_strap_front_left = this.storage_holder_front_left.getChild("storage_strap_front_left");
        this.storage_strap_front_right = this.storage_holder_front_right.getChild("storage_strap_front_right");
        this.storage_strap_back_left = this.storage_holder_back_left.getChild("storage_strap_back_left");
        this.storage_strap_back_right = this.storage_holder_back_right.getChild("storage_strap_back_right");
        this.storage_strap_left_1 = this.storage_holder_mid_left.getChild("storage_strap_left_1");
        this.storage_strap_left_2 = this.storage_strap_left_1.getChild("storage_strap_left_2");
        this.storage_strap_left_3 = this.storage_strap_left_2.getChild("storage_strap_left_3");
        this.storage_strap_left_holder = this.storage_strap_left_1.getChild("storage_strap_left_holder");
        this.storage_strap_right_1 = this.storage_holder_mid_right.getChild("storage_strap_right_1");
        this.storage_strap_right_2 = this.storage_strap_right_1.getChild("storage_strap_right_2");
        this.storage_strap_right_3 = this.storage_strap_right_2.getChild("storage_strap_right_3");
        this.storage_strap_right_holder = this.storage_strap_right_1.getChild("storage_strap_right_holder");

        // Seats
        this.seats_base = this.storage_base.getChild("seats_base");
        this.seats_metal_plate = this.seats_base.getChild("seats_metal_plate");
        this.seats_main_part = this.seats_metal_plate.getChild("seats_main_part");
        this.seats_mid_metal = this.seats_base.getChild("seats_mid_metal");
        this.seats_mid_metal_back = this.seats_base.getChild("seats_mid_metal_back");
        this.seats_backpart_front = this.seats_mid_metal.getChild("seats_backpart_front");
        this.seats_backpart_back = this.seats_mid_metal.getChild("seats_backpart_back");
        this.seats_backpart_back_front = this.seats_mid_metal_back.getChild("seats_backpart_back_front");

        // Small storage
        this.smallstorage_base = this.storage_base.getChild("smallstorage_base");
        this.smallstorage_metal_front_left = this.smallstorage_base.getChild("smallstorage_metal_front_left");
        this.smallstorage_metal_front_right = this.smallstorage_base.getChild("smallstorage_metal_front_right");
        this.smallstorage_seat_metal = this.smallstorage_base.getChild("smallstorage_seat_metal");
        this.smallstorage_seat_main = this.smallstorage_seat_metal.getChild("smallstorage_seat_main");
        this.smallstorage_back_support = this.smallstorage_base.getChild("smallstorage_back_support");
        this.smallstorage_back_dec = this.smallstorage_back_support.getChild("smallstorage_back_dec");
        this.smallstorage_box_1 = this.smallstorage_base.getChild("smallstorage_box_1");
        this.smallstorage_box_1_dec = this.smallstorage_box_1.getChild("smallstorage_box_1_dec");
        this.smallstorage_box_2 = this.smallstorage_base.getChild("smallstorage_box_2");
        this.smallstorage_box_2_dec = this.smallstorage_box_2.getChild("smallstorage_box_2_dec");
        this.smallstorage_box_2_dec_1 = this.smallstorage_box_2.getChild("smallstorage_box_2_dec_1");
        this.smallstorage_box_2_dec_2 = this.smallstorage_box_2.getChild("smallstorage_box_2_dec_2");
        this.smallstorage_box_3 = this.smallstorage_base.getChild("smallstorage_box_3");
        this.smallstorage_box_3_dec = this.smallstorage_box_3.getChild("smallstorage_box_3_dec");
        this.smallstorage_rod = this.smallstorage_base.getChild("smallstorage_rod");
        this.smallstorage_rod_1 = this.smallstorage_base.getChild("smallstorage_rod_1");
        this.smallstorage_rod_2 = this.smallstorage_base.getChild("smallstorage_rod_2");
        this.smallstorage_rod_3 = this.smallstorage_rod.getChild("smallstorage_rod_3");
        this.smallstorage_rod_4 = this.smallstorage_rod.getChild("smallstorage_rod_4");
        this.smallstorage_rod_5 = this.smallstorage_rod_3.getChild("smallstorage_rod_5");
        this.smallstorage_rod_6 = this.smallstorage_rod_5.getChild("smallstorage_rod_6");
        this.smallstorage_rod_7 = this.smallstorage_rod_6.getChild("smallstorage_rod_7");
        this.smallstorage_rod_8 = this.smallstorage_rod_6.getChild("smallstorage_rod_8");
        this.smallstorage_rod_9 = this.smallstorage_rod_8.getChild("smallstorage_rod_9");
        this.smallstorage_rod_10 = this.smallstorage_rod_4.getChild("smallstorage_rod_10");
        this.smallstorage_rod_11 = this.smallstorage_rod_10.getChild("smallstorage_rod_11");

        // Large storage
        this.largestorage_base = this.storage_base.getChild("largestorage_base");
        this.storage_metal_front_left = this.largestorage_base.getChild("storage_metal_front_left");
        this.storage_metal_front_right = this.largestorage_base.getChild("storage_metal_front_right");
        this.storage_metal_back_left = this.largestorage_base.getChild("storage_metal_back_left");
        this.storage_metal_back_right = this.largestorage_base.getChild("storage_metal_back_right");
        this.storage_box_1 = this.largestorage_base.getChild("storage_box_1");
        this.storage_box_1_dec = this.storage_box_1.getChild("storage_box_1_dec");
        this.storage_box_2 = this.largestorage_base.getChild("storage_box_2");
        this.storage_box_2_dec = this.storage_box_2.getChild("storage_box_2_dec");
        this.storage_box_2_dec_1 = this.storage_box_2.getChild("storage_box_2_dec_1");
        this.storage_box_2_dec_2 = this.storage_box_2.getChild("storage_box_2_dec_2");
        this.storage_box_3 = this.largestorage_base.getChild("storage_box_3");
        this.storage_box_3_dec = this.storage_box_3.getChild("storage_box_3_dec");
        this.storage_box_4 = this.largestorage_base.getChild("storage_box_4");
        this.storage_box_4_dec = this.storage_box_4.getChild("storage_box_4_dec");
        this.storage_box_5 = this.largestorage_base.getChild("storage_box_5");
        this.storage_box_5_dec = this.storage_box_5.getChild("storage_box_5_dec");
        this.storage_box_5_dec_1 = this.storage_box_5.getChild("storage_box_5_dec_1");
        this.storage_box_5_dec_2 = this.storage_box_5.getChild("storage_box_5_dec_2");
        this.storage_box_6 = this.largestorage_base.getChild("storage_box_6");
        this.storage_box_6_dec = this.storage_box_6.getChild("storage_box_6_dec");
        this.storage_rod = this.largestorage_base.getChild("storage_rod");
        this.storage_rod_1 = this.largestorage_base.getChild("storage_rod_1");
        this.storage_rod_2 = this.largestorage_base.getChild("storage_rod_2");
        this.storage_rod_3 = this.storage_rod.getChild("storage_rod_3");
        this.storage_rod_4 = this.storage_rod.getChild("storage_rod_4");
        this.storage_rod_5 = this.storage_rod.getChild("storage_rod_5");
        this.storage_rod_6 = this.storage_rod_3.getChild("storage_rod_6");
        this.storage_rod_7 = this.storage_rod_6.getChild("storage_rod_7");
        this.storage_rod_8 = this.storage_rod_7.getChild("storage_rod_8");
        this.storage_rod_9 = this.storage_rod_7.getChild("storage_rod_9");
        this.storage_rod_10 = this.storage_rod_4.getChild("storage_rod_10");
        this.storage_rod_11 = this.storage_rod_10.getChild("storage_rod_11");
        this.storage_rod_12 = this.storage_rod_5.getChild("storage_rod_12");
        this.storage_rod_13 = this.storage_rod_5.getChild("storage_rod_13");
        this.storage_rod_14 = this.storage_rod_12.getChild("storage_rod_14");
        this.storage_rod_15 = this.storage_rod_14.getChild("storage_rod_15");
        this.storage_rod_16 = this.storage_rod_13.getChild("storage_rod_16");
        this.storage_rod_17 = this.storage_rod_16.getChild("storage_rod_17");
        this.storage_rod_18 = this.storage_rod_17.getChild("storage_rod_18");
        this.storage_rod_19 = this.storage_rod_18.getChild("storage_rod_19");

        registerAllAnimatedParts();
    }

    private void registerAllAnimatedParts() {
        registerAnimatedPart(body);
        registerAnimatedPart(body_back);
        registerAnimatedPart(neck);
        registerAnimatedPart(head_base);
        registerAnimatedPart(tail_1);
        registerAnimatedPart(tail_2);
        registerAnimatedPart(tail_3);
        registerAnimatedPart(tail_4);
        registerAnimatedPart(leg_front_left_1);
        registerAnimatedPart(leg_front_left_2);
        registerAnimatedPart(foot_front_left_base);
        registerAnimatedPart(leg_front_right_1);
        registerAnimatedPart(leg_front_right_2);
        registerAnimatedPart(foot_front_right_base);
        registerAnimatedPart(leg_back_left_1);
        registerAnimatedPart(leg_back_left_2);
        registerAnimatedPart(foot_back_left_base);
        registerAnimatedPart(leg_back_right_1);
        registerAnimatedPart(leg_back_right_2);
        registerAnimatedPart(foot_back_right_base);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        // Main body
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(151, 0).addBox(-13.0F, 0.0F, -26.0F, 26, 26, 26),
                PartPose.offset(0.0F, -19.6F, 0.0F));

        body.addOrReplaceChild("body_top", CubeListBuilder.create()
                .texOffs(148, 53).addBox(-7.0F, 0.0F, 0.0F, 14, 5, 8),
                PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -0.5450663253978292F, 0.0F, 0.0F));

        body.addOrReplaceChild("body_right", CubeListBuilder.create()
                .texOffs(214, 110).addBox(0.0F, -9.0F, 0.0F, 5, 18, 10),
                PartPose.offsetAndRotation(-12.0F, 15.0F, 0.0F, 0.0F, 0.45378560551852565F, 0.0F));

        body.addOrReplaceChild("body_left", CubeListBuilder.create()
                .texOffs(183, 110).addBox(-5.0F, -9.0F, 0.0F, 5, 18, 10),
                PartPose.offsetAndRotation(12.0F, 15.0F, 0.0F, 0.0F, -0.45378560551852565F, 0.0F));

        body.addOrReplaceChild("body_bottom", CubeListBuilder.create()
                .texOffs(167, 139).addBox(-10.0F, 0.0F, -24.0F, 20, 5, 24),
                PartPose.offset(0.0F, 26.0F, 0.0F));

        // Body back
        PartDefinition body_back = body.addOrReplaceChild("body_back", CubeListBuilder.create()
                .texOffs(163, 54).addBox(-8.0F, 0.0F, 0.0F, 16, 21, 30),
                PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, -0.08726646259971647F, 0.0F, 0.0F));

        body_back.addOrReplaceChild("back_bottom", CubeListBuilder.create()
                .texOffs(175, 169).addBox(-6.0F, 0.0F, -30.0F, 12, 5, 28),
                PartPose.offset(0.0F, 20.8F, 30.0F));

        PartDefinition back_top = body_back.addOrReplaceChild("back_top", CubeListBuilder.create()
                .texOffs(158, 67).addBox(-7.0F, 0.0F, -3.0F, 14, 10, 3),
                PartPose.offsetAndRotation(0.0F, 0.2F, 30.0F, 0.24434609527920614F, 0.0F, 0.0F));

        back_top.addOrReplaceChild("back_bottom_1", CubeListBuilder.create()
                .texOffs(158, 106).addBox(-7.0F, 0.0F, -3.0F, 14, 10, 3),
                PartPose.offsetAndRotation(0.01F, 10.0F, 0.0F, -0.4886921905584123F, 0.0F, 0.0F));

        // Tail
        PartDefinition tail_base = back_top.addOrReplaceChild("tail_base", CubeListBuilder.create()
                .texOffs(204, 106).addBox(-1.5F, 0.0F, 0.0F, 3, 4, 1),
                PartPose.offsetAndRotation(0.0F, 3.0F, -0.4F, -0.1699950691442477F, 0.0F, 0.0F));

        PartDefinition tail_1 = tail_base.addOrReplaceChild("tail_1", CubeListBuilder.create()
                .texOffs(204, 112).addBox(-0.5F, 0.0F, -1.0F, 1, 5, 1),
                PartPose.offsetAndRotation(0.0F, 0.5F, 1.0F, 0.40142572795869574F, 0.0F, 0.0F));

        PartDefinition tail_2 = tail_1.addOrReplaceChild("tail_2", CubeListBuilder.create()
                .texOffs(209, 112).addBox(-0.5F, 0.0F, -1.0F, 1, 5, 1),
                PartPose.offsetAndRotation(0.01F, 5.0F, 0.0F, -0.27314402793711257F, 0.0F, 0.0F));

        PartDefinition tail_3 = tail_2.addOrReplaceChild("tail_3", CubeListBuilder.create()
                .texOffs(213, 106).addBox(-0.5F, 0.0F, -1.0F, 1, 3, 1),
                PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, -0.18430676901060122F, 0.0F, 0.0F));

        tail_3.addOrReplaceChild("tail_4", CubeListBuilder.create()
                .texOffs(214, 111).addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2),
                PartPose.offset(0.0F, 3.0F, -0.5F));

        // Front top and head
        PartDefinition front_top = body.addOrReplaceChild("front_top", CubeListBuilder.create()
                .texOffs(118, 120).addBox(-12.5F, 0.0F, 0.0F, 25, 10, 7),
                PartPose.offsetAndRotation(0.0F, 1.0F, -26.0F, -0.5585053606381855F, 0.0F, 0.0F));

        PartDefinition front_bottom = front_top.addOrReplaceChild("front_bottom", CubeListBuilder.create()
                .texOffs(125, 138).addBox(-12.5F, 0.0F, 0.0F, 25, 16, 7),
                PartPose.offsetAndRotation(0.01F, 10.0F, 0.0F, 0.9075712110370513F, 0.0F, 0.0F));

        PartDefinition neck = front_bottom.addOrReplaceChild("neck", CubeListBuilder.create()
                .texOffs(166, 169).addBox(-5.0F, -12.0F, -8.0F, 10, 12, 8),
                PartPose.offsetAndRotation(0.0F, 14.0F, 0.0F, -0.391826417072727F, 0.0F, 0.0F));

        PartDefinition head_base = neck.addOrReplaceChild("head_base", CubeListBuilder.create()
                .texOffs(121, 162).addBox(-7.0F, 0.0F, -5.0F, 14, 8, 8),
                PartPose.offsetAndRotation(0.0F, -12.4F, -5.0F, -0.33283428835531864F, 0.0F, 0.0F));

        PartDefinition head_front = head_base.addOrReplaceChild("head_front", CubeListBuilder.create()
                .texOffs(131, 179).addBox(-6.0F, 0.0F, 0.0F, 12, 8, 5),
                PartPose.offsetAndRotation(0.0F, 1.0F, -5.0F, -0.5574581630869889F, 0.0F, 0.0F));

        PartDefinition head_main = head_front.addOrReplaceChild("head_main", CubeListBuilder.create()
                .texOffs(194, 203).addBox(-4.5F, -8.0F, 0.0F, 9, 9, 7),
                PartPose.offset(0.0F, 8.0F, 5.0F));

        head_main.addOrReplaceChild("head_main_right", CubeListBuilder.create()
                .texOffs(133, 193).addBox(0.0F, 0.0F, 0.0F, 3, 4, 7),
                PartPose.offsetAndRotation(-4.5F, 1.0F, 0.0F, 0.0F, 0.0F, -0.8639379797371932F));

        head_main.addOrReplaceChild("head_main_left", CubeListBuilder.create()
                .texOffs(154, 193).addBox(-3.0F, 0.0F, 0.0F, 3, 4, 7),
                PartPose.offsetAndRotation(4.5F, 1.0F, 0.0F, 0.0F, 0.0F, 0.8639379797371932F));

        head_front.addOrReplaceChild("head_front_decoration", CubeListBuilder.create()
                .texOffs(228, 169).addBox(-2.5F, 0.0F, 0.0F, 5, 9, 2),
                PartPose.offsetAndRotation(0.0F, 8.0F, 1.0F, 0.8377580409572781F, 0.0F, 0.0F));

        PartDefinition head_bottom = head_base.addOrReplaceChild("head_bottom", CubeListBuilder.create()
                .texOffs(227, 203).addBox(-3.0F, 0.0F, -6.0F, 6, 12, 8),
                PartPose.offset(0.0F, 8.0F, 0.0F));

        head_bottom.addOrReplaceChild("head_bottom_decoration", CubeListBuilder.create()
                .texOffs(228, 181).addBox(-2.5F, -8.0F, -3.0F, 5, 8, 3),
                PartPose.offsetAndRotation(0.0F, 10.0F, 2.0F, -0.4553564018453205F, 0.0F, 0.0F));

        PartDefinition horn_base_left = head_base.addOrReplaceChild("horn_base_left", CubeListBuilder.create()
                .texOffs(245, 224).addBox(0.0F, -2.0F, -2.0F, 1, 4, 4),
                PartPose.offsetAndRotation(7.0F, 4.0F, -1.0F, 0.6283185307179586F, 0.0F, 0.0F));

        PartDefinition horn_left_1 = horn_base_left.addOrReplaceChild("horn_left_1", CubeListBuilder.create()
                .texOffs(237, 233).addBox(0.0F, -1.5F, -3.0F, 6, 3, 3),
                PartPose.offsetAndRotation(0.8F, 0.0F, 2.0F, 0.0F, 0.6370451769779303F, 0.0F));

        horn_left_1.addOrReplaceChild("horn_left_2", CubeListBuilder.create()
                .texOffs(235, 240).addBox(0.0F, -1.5F, -3.0F, 7, 3, 3),
                PartPose.offsetAndRotation(6.0F, 0.01F, 0.0F, 0.0F, 0.6370451769779303F, 0.0F));

        PartDefinition horn_base_right = head_base.addOrReplaceChild("horn_base_right", CubeListBuilder.create()
                .texOffs(224, 224).addBox(-1.0F, -2.0F, -2.0F, 1, 4, 4),
                PartPose.offsetAndRotation(-7.0F, 4.0F, -1.0F, 0.6283185307179586F, 0.0F, 0.0F));

        PartDefinition horn_right_1 = horn_base_right.addOrReplaceChild("horn_right_1", CubeListBuilder.create()
                .texOffs(216, 233).addBox(-6.0F, -1.5F, -3.0F, 6, 3, 3),
                PartPose.offsetAndRotation(-0.8F, 0.0F, 2.0F, 0.0F, -0.6370451769779303F, 0.0F));

        horn_right_1.addOrReplaceChild("horn_right_2", CubeListBuilder.create()
                .texOffs(214, 240).addBox(-7.0F, -1.5F, -3.0F, 7, 3, 3),
                PartPose.offsetAndRotation(-6.0F, 0.01F, 0.0F, 0.0F, -0.6370451769779303F, 0.0F));

        // Front legs
        PartDefinition leg_front_left_1 = body.addOrReplaceChild("leg_front_left_1", CubeListBuilder.create()
                .texOffs(65, 15).addBox(0.0F, -5.0F, -5.0F, 5, 14, 10),
                PartPose.offsetAndRotation(13.0F, 22.0F, -18.0F, 0.5061454830783556F, 0.0F, 0.0F));

        leg_front_left_1.addOrReplaceChild("leg_front_left_top", CubeListBuilder.create()
                .texOffs(71, 0).addBox(-4.0F, -6.0F, -4.0F, 4, 6, 8),
                PartPose.offsetAndRotation(4.0F, -5.0F, 0.0F, 0.0F, 0.0F, -0.8203047484373349F));

        PartDefinition leg_front_left_2 = leg_front_left_1.addOrReplaceChild("leg_front_left_2", CubeListBuilder.create()
                .texOffs(77, 40).addBox(0.0F, 0.0F, -5.0F, 4, 16, 5),
                PartPose.offsetAndRotation(0.5F, 9.0F, 4.0F, -0.8726646259971648F, 0.0F, 0.0F));

        leg_front_left_2.addOrReplaceChild("leg_front_left_decoration", CubeListBuilder.create()
                .texOffs(64, 40).addBox(0.0F, -9.0F, 0.0F, 3, 9, 3),
                PartPose.offsetAndRotation(0.5F, 12.0F, -5.0F, 0.3490658503988659F, 0.0F, 0.0F));

        PartDefinition foot_front_left_base = leg_front_left_2.addOrReplaceChild("foot_front_left_base", CubeListBuilder.create()
                .texOffs(71, 62).addBox(-2.5F, 0.0F, -6.0F, 5, 3, 7),
                PartPose.offsetAndRotation(2.0F, 13.5F, 0.0F, 0.33161255787892263F, 0.0F, 0.0F));

        PartDefinition nail_front_left = foot_front_left_base.addOrReplaceChild("nail_front_left", CubeListBuilder.create()
                .texOffs(66, 57).addBox(-1.5F, 0.0F, -1.0F, 4, 1, 1),
                PartPose.offset(-0.5F, 2.0F, -6.0F));

        nail_front_left.addOrReplaceChild("nail_front_left_top", CubeListBuilder.create()
                .texOffs(66, 53).addBox(-1.5F, -2.0F, 0.0F, 4, 2, 1),
                PartPose.offsetAndRotation(-0.01F, 0.0F, -1.0F, -0.5009094953223726F, 0.0F, 0.0F));

        PartDefinition leg_front_right_1 = body.addOrReplaceChild("leg_front_right_1", CubeListBuilder.create()
                .texOffs(33, 15).addBox(-5.0F, -5.0F, -5.0F, 5, 14, 10),
                PartPose.offsetAndRotation(-13.0F, 22.0F, -18.0F, 0.5061454830783556F, 0.0F, 0.0F));

        leg_front_right_1.addOrReplaceChild("leg_front_right_top", CubeListBuilder.create()
                .texOffs(39, 0).addBox(0.0F, -6.0F, -4.0F, 4, 6, 8),
                PartPose.offsetAndRotation(-4.0F, -5.0F, 0.0F, 0.0F, 0.0F, 0.8203047484373349F));

        PartDefinition leg_front_right_2 = leg_front_right_1.addOrReplaceChild("leg_front_right_2", CubeListBuilder.create()
                .texOffs(45, 40).addBox(-4.0F, 0.0F, -5.0F, 4, 16, 5),
                PartPose.offsetAndRotation(-0.5F, 9.0F, 4.0F, -0.8726646259971648F, 0.0F, 0.0F));

        leg_front_right_2.addOrReplaceChild("leg_front_right_decoration", CubeListBuilder.create()
                .texOffs(32, 40).addBox(-3.0F, -9.0F, 0.0F, 3, 9, 3),
                PartPose.offsetAndRotation(-0.5F, 12.0F, -5.0F, 0.3490658503988659F, 0.0F, 0.0F));

        PartDefinition foot_front_right_base = leg_front_right_2.addOrReplaceChild("foot_front_right_base", CubeListBuilder.create()
                .texOffs(39, 62).addBox(-2.5F, 0.0F, -6.0F, 5, 3, 7),
                PartPose.offsetAndRotation(-2.0F, 13.5F, 0.0F, 0.33161255787892263F, 0.0F, 0.0F));

        PartDefinition nail_front_right = foot_front_right_base.addOrReplaceChild("nail_front_right", CubeListBuilder.create()
                .texOffs(34, 57).addBox(-1.5F, 0.0F, -1.0F, 4, 1, 1),
                PartPose.offset(-0.5F, 2.0F, -6.0F));

        nail_front_right.addOrReplaceChild("nail_front_right_top", CubeListBuilder.create()
                .texOffs(34, 53).addBox(-1.5F, -2.0F, 0.0F, 4, 2, 1),
                PartPose.offsetAndRotation(-0.01F, 0.0F, -1.0F, -0.5009094953223726F, 0.0F, 0.0F));

        // Back legs
        PartDefinition leg_back_left_1 = body_back.addOrReplaceChild("leg_back_left_1", CubeListBuilder.create()
                .texOffs(97, 12).addBox(-4.0F, -4.0F, -4.0F, 4, 11, 8),
                PartPose.offsetAndRotation(-8.0F, 18.0F, 24.0F, -0.17453292519943295F, 0.0F, 0.0F));

        leg_back_left_1.addOrReplaceChild("leg_back_left_top", CubeListBuilder.create()
                .texOffs(103, 0).addBox(0.0F, -5.0F, -3.0F, 3, 5, 6),
                PartPose.offsetAndRotation(-3.0F, -4.0F, 0.0F, 0.0F, 0.0F, 0.6981317007977318F));

        PartDefinition leg_back_left_2 = leg_back_left_1.addOrReplaceChild("leg_back_left_2", CubeListBuilder.create()
                .texOffs(107, 32).addBox(-3.0F, 0.0F, 0.0F, 3, 13, 4),
                PartPose.offsetAndRotation(-0.5F, 7.0F, -3.0F, 0.4886921905584123F, 0.0F, 0.0F));

        leg_back_left_2.addOrReplaceChild("leg_back_right_decoration_1", CubeListBuilder.create()
                .texOffs(96, 32).addBox(-2.0F, -6.0F, -3.0F, 2, 6, 3),
                PartPose.offsetAndRotation(-0.5F, 8.0F, 4.0F, -0.3141592653589793F, 0.0F, 0.0F));

        PartDefinition foot_back_left_base = leg_back_left_2.addOrReplaceChild("foot_back_left_base", CubeListBuilder.create()
                .texOffs(103, 50).addBox(-2.0F, 0.0F, -0.5F, 4, 3, 5),
                PartPose.offsetAndRotation(-1.5F, 11.0F, 0.0F, -0.17453292519943295F, 0.0F, 0.0F));

        PartDefinition nail_back_left = foot_back_left_base.addOrReplaceChild("nail_back_left", CubeListBuilder.create()
                .texOffs(98, 46).addBox(-1.5F, 0.0F, -1.0F, 3, 1, 1),
                PartPose.offset(0.0F, 2.0F, -0.5F));

        nail_back_left.addOrReplaceChild("nail_back_left_top", CubeListBuilder.create()
                .texOffs(98, 42).addBox(-1.5F, -2.0F, 0.0F, 3, 2, 1),
                PartPose.offsetAndRotation(-0.01F, 0.0F, -1.0F, -0.5009094953223726F, 0.0F, 0.0F));

        PartDefinition leg_back_right_1 = body_back.addOrReplaceChild("leg_back_right_1", CubeListBuilder.create()
                .texOffs(123, 12).addBox(0.0F, -4.0F, -4.0F, 4, 11, 8),
                PartPose.offsetAndRotation(8.0F, 18.0F, 24.0F, -0.17453292519943295F, 0.0F, 0.0F));

        leg_back_right_1.addOrReplaceChild("leg_back_right_top", CubeListBuilder.create()
                .texOffs(126, 0).addBox(-3.0F, -5.0F, -3.0F, 3, 5, 6),
                PartPose.offsetAndRotation(3.0F, -4.0F, 0.0F, 0.0F, 0.0F, -0.6981317007977318F));

        PartDefinition leg_back_right_2 = leg_back_right_1.addOrReplaceChild("leg_back_right_2", CubeListBuilder.create()
                .texOffs(133, 32).addBox(0.0F, 0.0F, 0.0F, 3, 13, 4),
                PartPose.offsetAndRotation(0.5F, 7.0F, -3.0F, 0.4886921905584123F, 0.0F, 0.0F));

        leg_back_right_2.addOrReplaceChild("leg_back_right_decoration", CubeListBuilder.create()
                .texOffs(122, 32).addBox(0.0F, -6.0F, -3.0F, 2, 6, 3),
                PartPose.offsetAndRotation(0.5F, 8.0F, 4.0F, -0.3141592653589793F, 0.0F, 0.0F));

        PartDefinition foot_back_right_base = leg_back_right_2.addOrReplaceChild("foot_back_right_base", CubeListBuilder.create()
                .texOffs(129, 50).addBox(-2.0F, 0.0F, -0.5F, 4, 3, 5),
                PartPose.offsetAndRotation(1.5F, 11.0F, 0.0F, -0.17453292519943295F, 0.0F, 0.0F));

        PartDefinition nail_back_right = foot_back_right_base.addOrReplaceChild("nail_back_right", CubeListBuilder.create()
                .texOffs(124, 46).addBox(-1.5F, 0.0F, -1.0F, 3, 1, 1),
                PartPose.offset(0.0F, 2.0F, -0.5F));

        nail_back_right.addOrReplaceChild("nail_back_right_top", CubeListBuilder.create()
                .texOffs(124, 42).addBox(-1.5F, -2.0F, 0.0F, 3, 2, 1),
                PartPose.offsetAndRotation(-0.01F, 0.0F, -1.0F, -0.5009094953223726F, 0.0F, 0.0F));

        // Saddle base with all parts
        PartDefinition front_seat_base = body.addOrReplaceChild("front_seat_base", CubeListBuilder.create()
                .texOffs(0, 94).addBox(-14.0F, -1.0F, -1.0F, 28, 1, 2),
                PartPose.offset(0.0F, 0.0F, -11.0F));

        // Seat bottom and top
        PartDefinition seat_bottom = front_seat_base.addOrReplaceChild("seat_bottom", CubeListBuilder.create()
                .texOffs(0, 60).addBox(-5.0F, -1.0F, -15.0F, 10, 1, 15),
                PartPose.offset(0.0F, 0.0F, -1.0F));

        PartDefinition seat_top = seat_bottom.addOrReplaceChild("seat_top", CubeListBuilder.create()
                .texOffs(0, 77).addBox(-4.0F, -1.0F, -15.0F, 8, 1, 15),
                PartPose.offset(0.0F, -1.0F, 0.0F));

        seat_top.addOrReplaceChild("seat_back", CubeListBuilder.create()
                .texOffs(0, 41).addBox(-4.5F, -4.0F, 0.0F, 9, 5, 4),
                PartPose.offsetAndRotation(0.0F, 0.0F, -1.8F, -0.2617993877991494F, 0.0F, 0.0F));

        seat_bottom.addOrReplaceChild("seat_front", CubeListBuilder.create()
                .texOffs(0, 51).addBox(-4.5F, 0.0F, 0.0F, 9, 5, 3),
                PartPose.offsetAndRotation(0.0F, -1.7F, -15.0F, -0.40142572795869574F, 0.0F, 0.0F));

        // Front straps - left side
        PartDefinition front_strap_left_1 = front_seat_base.addOrReplaceChild("front_strap_left_1", CubeListBuilder.create()
                .texOffs(0, 106).addBox(0.0F, 0.0F, -1.0F, 1, 26, 2),
                PartPose.offset(13.0F, 0.0F, 0.0F));

        front_strap_left_1.addOrReplaceChild("front_holder_left", CubeListBuilder.create()
                .texOffs(20, 118).addBox(0.0F, -2.0F, -2.0F, 2, 4, 4),
                PartPose.offset(-0.4F, 9.6F, 0.0F));

        PartDefinition front_strap_left_2 = front_strap_left_1.addOrReplaceChild("front_strap_left_2", CubeListBuilder.create()
                .texOffs(14, 106).addBox(-1.0F, 0.0F, -1.0F, 1, 7, 2),
                PartPose.offsetAndRotation(1.0F, 26.0F, 0.01F, 0.0F, 0.0F, 0.5759586531581287F));

        front_strap_left_2.addOrReplaceChild("front_strap_left_3", CubeListBuilder.create()
                .texOffs(28, 106).addBox(-1.0F, 0.0F, -1.0F, 1, 9, 2),
                PartPose.offsetAndRotation(0.0F, 7.0F, 0.01F, 0.0F, 0.0F, 0.9948376736367678F));

        // Front straps - right side
        PartDefinition front_strap_right_1 = front_seat_base.addOrReplaceChild("front_strap_right_1", CubeListBuilder.create()
                .texOffs(7, 106).addBox(-1.0F, 0.0F, -1.0F, 1, 26, 2),
                PartPose.offset(-13.0F, 0.0F, 0.0F));

        front_strap_right_1.addOrReplaceChild("front_holder_right", CubeListBuilder.create()
                .texOffs(20, 118).addBox(-2.0F, -2.0F, -2.0F, 2, 4, 4),
                PartPose.offset(0.4F, 9.6F, 0.0F));

        PartDefinition front_strap_right_2 = front_strap_right_1.addOrReplaceChild("front_strap_right_2", CubeListBuilder.create()
                .texOffs(21, 106).addBox(0.0F, 0.0F, -1.0F, 1, 7, 2),
                PartPose.offsetAndRotation(-1.0F, 26.0F, 0.01F, 0.0F, 0.0F, -0.5759586531581287F));

        front_strap_right_2.addOrReplaceChild("front_strap_right_3", CubeListBuilder.create()
                .texOffs(35, 106).addBox(0.0F, 0.0F, -1.0F, 1, 9, 2),
                PartPose.offsetAndRotation(0.0F, 7.0F, 0.01F, 0.0F, 0.0F, -0.9948376736367678F));

        // Front strap bottom
        front_seat_base.addOrReplaceChild("front_strap_bottom", CubeListBuilder.create()
                .texOffs(20, 125).addBox(-2.0F, 0.0F, -2.0F, 4, 2, 4),
                PartPose.offset(0.0F, 30.4F, 0.0F));

        // Head strap
        PartDefinition head_strap_base = front_seat_base.addOrReplaceChild("head_strap_base", CubeListBuilder.create()
                .texOffs(0, 98).addBox(-14.0F, 0.0F, -1.0F, 28, 2, 1),
                PartPose.offsetAndRotation(0.0F, 0.9F, -15.0F, -0.5235987755982988F, 0.0F, 0.0F));

        head_strap_base.addOrReplaceChild("head_strap_left_1", CubeListBuilder.create()
                .texOffs(0, 118).addBox(-1.0F, -1.0F, 0.0F, 1, 2, 17),
                PartPose.offset(14.0F, 1.0F, 0.0F));

        head_strap_base.addOrReplaceChild("head_strap_right_1", CubeListBuilder.create()
                .texOffs(38, 124).addBox(0.0F, -1.0F, 0.0F, 1, 2, 17),
                PartPose.offset(-14.0F, 1.0F, 0.0F));

        // Shoulder strap
        PartDefinition shoulder_strap_base = front_seat_base.addOrReplaceChild("shoulder_strap_base", CubeListBuilder.create()
                .texOffs(0, 102).addBox(-14.0F, 0.0F, 0.0F, 28, 2, 1),
                PartPose.offsetAndRotation(0.0F, 2.0F, 13.0F, -2.4609142453120048F, 0.0F, 0.0F));

        shoulder_strap_base.addOrReplaceChild("shoulder_strap_left_1", CubeListBuilder.create()
                .texOffs(22, 123).addBox(-1.0F, -1.0F, 0.0F, 1, 2, 15),
                PartPose.offset(14.0F, 1.0F, 0.0F));

        shoulder_strap_base.addOrReplaceChild("shoulder_strap_right_1", CubeListBuilder.create()
                .texOffs(60, 129).addBox(0.0F, -1.0F, 0.0F, 1, 2, 15),
                PartPose.offset(-14.0F, 1.0F, 0.0F));

        PartDefinition shoulder_holder = shoulder_strap_base.addOrReplaceChild("shoulder_holder", CubeListBuilder.create()
                .texOffs(20, 118).addBox(-1.0F, -2.0F, -2.0F, 2, 4, 4),
                PartPose.offset(0.0F, 1.2F, 1.2F));

        shoulder_holder.addOrReplaceChild("shoulder_strap_mid", CubeListBuilder.create()
                .texOffs(0, 62).addBox(-1.0F, 0.0F, 0.0F, 2, 11, 1),
                PartPose.offsetAndRotation(0.0F, 2.0F, -1.6F, 0.8726646259971648F, 0.0F, 0.0F));

        // Storage base and accessories
        PartDefinition storage_base = body_back.addOrReplaceChild("storage_base", CubeListBuilder.create()
                .texOffs(62, 73).addBox(-9.0F, -1.0F, -24.0F, 18, 1, 24),
                PartPose.offset(0.0F, 0.0F, 29.0F));

        // Storage holders
        PartDefinition storage_holder_front_left = storage_base.addOrReplaceChild("storage_holder_front_left", CubeListBuilder.create()
                .texOffs(73, 73).addBox(0.0F, -2.0F, -2.0F, 2, 4, 4),
                PartPose.offset(7.6F, 0.5F, -21.0F));

        PartDefinition storage_holder_front_right = storage_base.addOrReplaceChild("storage_holder_front_right", CubeListBuilder.create()
                .texOffs(73, 82).addBox(-2.0F, -2.0F, -2.0F, 2, 4, 4),
                PartPose.offset(-7.6F, 0.5F, -21.0F));

        PartDefinition storage_holder_mid_left = storage_base.addOrReplaceChild("storage_holder_mid_left", CubeListBuilder.create()
                .texOffs(60, 73).addBox(0.0F, -2.0F, -2.0F, 2, 4, 4),
                PartPose.offset(7.6F, 0.5F, -12.0F));

        PartDefinition storage_holder_mid_right = storage_base.addOrReplaceChild("storage_holder_mid_right", CubeListBuilder.create()
                .texOffs(60, 82).addBox(-2.0F, -2.0F, -2.0F, 2, 4, 4),
                PartPose.offset(-7.6F, 0.5F, -12.0F));

        PartDefinition storage_holder_back_left = storage_base.addOrReplaceChild("storage_holder_back_left", CubeListBuilder.create()
                .texOffs(47, 73).addBox(0.0F, -2.0F, -2.0F, 2, 4, 4),
                PartPose.offset(7.6F, 0.5F, -3.0F));

        PartDefinition storage_holder_back_right = storage_base.addOrReplaceChild("storage_holder_back_right", CubeListBuilder.create()
                .texOffs(47, 82).addBox(-2.0F, -2.0F, -2.0F, 2, 4, 4),
                PartPose.offset(-7.6F, 0.5F, -3.0F));

        storage_base.addOrReplaceChild("storage_base_holder_bottom", CubeListBuilder.create()
                .texOffs(123, 90).addBox(-2.0F, 0.0F, -2.0F, 4, 2, 4),
                PartPose.offset(0.0F, 25.1F, -12.0F));

        // Storage straps
        storage_holder_front_left.addOrReplaceChild("storage_strap_front_left", CubeListBuilder.create()
                .texOffs(123, 59).addBox(0.4F, 0.0F, 0.0F, 1, 11, 2),
                PartPose.offsetAndRotation(0.0F, 2.0F, -1.6F, 0.9075712110370513F, 0.0F, 0.0F));

        storage_holder_front_right.addOrReplaceChild("storage_strap_front_right", CubeListBuilder.create()
                .texOffs(123, 73).addBox(-1.4F, 0.0F, 0.0F, 1, 11, 2),
                PartPose.offsetAndRotation(0.0F, 2.0F, -1.6F, 0.9075712110370513F, 0.0F, 0.0F));

        storage_holder_back_left.addOrReplaceChild("storage_strap_back_left", CubeListBuilder.create()
                .texOffs(130, 59).addBox(0.4F, 0.0F, -2.0F, 1, 11, 2),
                PartPose.offsetAndRotation(0.0F, 2.0F, 1.6F, -0.9075712110370513F, 0.0F, 0.0F));

        storage_holder_back_right.addOrReplaceChild("storage_strap_back_right", CubeListBuilder.create()
                .texOffs(130, 73).addBox(-1.4F, 0.0F, -2.0F, 1, 11, 2),
                PartPose.offsetAndRotation(0.0F, 2.0F, 1.6F, -0.9075712110370513F, 0.0F, 0.0F));

        PartDefinition storage_strap_left_1 = storage_holder_mid_left.addOrReplaceChild("storage_strap_left_1", CubeListBuilder.create()
                .texOffs(59, 99).addBox(0.4F, 0.0F, -1.0F, 1, 19, 2),
                PartPose.offset(0.0F, 1.5F, 0.0F));

        PartDefinition storage_strap_left_2 = storage_strap_left_1.addOrReplaceChild("storage_strap_left_2", CubeListBuilder.create()
                .texOffs(73, 99).addBox(-1.0F, 0.0F, -1.0F, 1, 6, 2),
                PartPose.offsetAndRotation(1.4F, 19.0F, 0.01F, 0.0F, 0.0F, 0.40142572795869574F));

        storage_strap_left_2.addOrReplaceChild("storage_strap_left_3", CubeListBuilder.create()
                .texOffs(58, 121).addBox(-1.0F, 0.0F, -1.0F, 1, 6, 2),
                PartPose.offsetAndRotation(0.0F, 6.0F, 0.01F, 0.0F, 0.0F, 1.1693705988362009F));

        storage_strap_left_1.addOrReplaceChild("storage_strap_left_holder", CubeListBuilder.create()
                .texOffs(96, 64).addBox(0.0F, -2.0F, -2.0F, 2, 4, 4),
                PartPose.offset(0.0F, 6.0F, 0.0F));

        PartDefinition storage_strap_right_1 = storage_holder_mid_right.addOrReplaceChild("storage_strap_right_1", CubeListBuilder.create()
                .texOffs(66, 99).addBox(-1.4F, 0.0F, -1.0F, 1, 19, 2),
                PartPose.offset(0.0F, 1.5F, 0.0F));

        PartDefinition storage_strap_right_2 = storage_strap_right_1.addOrReplaceChild("storage_strap_right_2", CubeListBuilder.create()
                .texOffs(73, 108).addBox(0.0F, 0.0F, -1.0F, 1, 6, 2),
                PartPose.offsetAndRotation(-1.4F, 19.0F, 0.01F, 0.0F, 0.0F, -0.40142572795869574F));

        storage_strap_right_2.addOrReplaceChild("storage_strap_right_3", CubeListBuilder.create()
                .texOffs(65, 121).addBox(0.0F, 0.0F, -1.0F, 1, 6, 2),
                PartPose.offsetAndRotation(0.0F, 6.0F, 0.01F, 0.0F, 0.0F, -1.1693705988362009F));

        storage_strap_right_1.addOrReplaceChild("storage_strap_right_holder", CubeListBuilder.create()
                .texOffs(109, 64).addBox(-2.0F, -2.0F, -2.0F, 2, 4, 4),
                PartPose.offset(0.0F, 6.0F, 0.0F));

        // Seats base
        PartDefinition seats_base = storage_base.addOrReplaceChild("seats_base", CubeListBuilder.create()
                .texOffs(0, 144).addBox(-8.5F, -1.0F, -23.5F, 17, 1, 23),
                PartPose.offset(0.0F, -0.3F, 0.0F));

        PartDefinition seats_metal_plate = seats_base.addOrReplaceChild("seats_metal_plate", CubeListBuilder.create()
                .texOffs(0, 169).addBox(-5.0F, -1.0F, -23.0F, 10, 1, 23),
                PartPose.offset(0.0F, -1.0F, -0.5F));

        seats_metal_plate.addOrReplaceChild("seats_main_part", CubeListBuilder.create()
                .texOffs(0, 194).addBox(-4.0F, -1.0F, -22.5F, 8, 1, 22),
                PartPose.offset(0.0F, -1.0F, 0.0F));

        PartDefinition seats_mid_metal = seats_base.addOrReplaceChild("seats_mid_metal", CubeListBuilder.create()
                .texOffs(0, 138).addBox(-4.5F, -5.0F, -1.0F, 9, 5, 1),
                PartPose.offset(0.0F, -2.0F, -12.0F));

        PartDefinition seats_mid_metal_back = seats_base.addOrReplaceChild("seats_mid_metal_back", CubeListBuilder.create()
                .texOffs(0, 138).addBox(-4.5F, -5.0F, -1.0F, 9, 5, 1),
                PartPose.offset(0.0F, -2.0F, -0.8F));

        seats_mid_metal.addOrReplaceChild("seats_backpart_front", CubeListBuilder.create()
                .texOffs(0, 145).addBox(-3.5F, 0.0F, 0.0F, 7, 4, 1),
                PartPose.offsetAndRotation(0.0F, -4.5F, -1.3F, -0.20943951023931953F, 0.0F, 0.0F));

        seats_mid_metal.addOrReplaceChild("seats_backpart_back", CubeListBuilder.create()
                .texOffs(0, 151).addBox(-3.5F, 0.0F, -1.0F, 7, 4, 1),
                PartPose.offsetAndRotation(0.0F, -4.5F, 0.3F, 0.20943951023931953F, 0.0F, 0.0F));

        seats_mid_metal_back.addOrReplaceChild("seats_backpart_back_front", CubeListBuilder.create()
                .texOffs(0, 145).addBox(-3.5F, 0.0F, 0.0F, 7, 4, 1),
                PartPose.offsetAndRotation(0.0F, -4.5F, -1.3F, -0.20943951023931953F, 0.0F, 0.0F));

        // Small storage base
        PartDefinition smallstorage_base = storage_base.addOrReplaceChild("smallstorage_base", CubeListBuilder.create()
                .texOffs(0, 144).addBox(-8.5F, -1.0F, -23.5F, 17, 1, 23),
                PartPose.offsetAndRotation(0.0F, -0.3F, -24.0F, 0.0F, 3.141592653589793F, 0.0F));

        smallstorage_base.addOrReplaceChild("smallstorage_metal_front_left", CubeListBuilder.create()
                .texOffs(107, 99).addBox(-1.5F, -2.0F, -1.0F, 3, 2, 2),
                PartPose.offset(4.0F, 0.7F, -23.2F));

        smallstorage_base.addOrReplaceChild("smallstorage_metal_front_right", CubeListBuilder.create()
                .texOffs(107, 104).addBox(-1.5F, -2.0F, -1.0F, 3, 2, 2),
                PartPose.offset(-4.0F, 0.7F, -23.2F));

        PartDefinition smallstorage_seat_metal = smallstorage_base.addOrReplaceChild("smallstorage_seat_metal", CubeListBuilder.create()
                .texOffs(115, 107).addBox(-5.0F, -1.0F, -11.0F, 10, 1, 11),
                PartPose.offset(0.0F, -1.0F, -1.0F));

        smallstorage_seat_metal.addOrReplaceChild("smallstorage_seat_main", CubeListBuilder.create()
                .texOffs(80, 99).addBox(-4.0F, -1.0F, -10.0F, 8, 1, 10),
                PartPose.offset(0.0F, -1.0F, -0.5F));

        PartDefinition smallstorage_back_support = smallstorage_base.addOrReplaceChild("smallstorage_back_support", CubeListBuilder.create()
                .texOffs(80, 111).addBox(-5.5F, -6.0F, 0.0F, 11, 6, 2),
                PartPose.offset(0.0F, -1.0F, -13.0F));

        smallstorage_back_support.addOrReplaceChild("smallstorage_back_dec", CubeListBuilder.create()
                .texOffs(126, 101).addBox(-3.0F, 0.0F, -1.0F, 6, 4, 1),
                PartPose.offsetAndRotation(0.0F, -5.0F, 2.3F, 0.20943951023931953F, 0.0F, 0.0F));

        // Small storage boxes
        PartDefinition smallstorage_box_1 = smallstorage_base.addOrReplaceChild("smallstorage_box_1", CubeListBuilder.create()
                .texOffs(44, 178).addBox(-2.0F, -5.0F, -4.0F, 4, 5, 8),
                PartPose.offsetAndRotation(-4.5F, -1.0F, -17.5F, 0.0F, -0.18203784098300857F, 0.0F));

        smallstorage_box_1.addOrReplaceChild("smallstorage_box_1_dec", CubeListBuilder.create()
                .texOffs(47, 182).addBox(0.0F, -1.5F, -0.5F, 1, 2, 1),
                PartPose.offset(1.5F, -2.8F, 0.0F));

        PartDefinition smallstorage_box_2 = smallstorage_base.addOrReplaceChild("smallstorage_box_2", CubeListBuilder.create()
                .texOffs(0, 169).addBox(-2.5F, -3.0F, -2.5F, 5, 3, 5),
                PartPose.offsetAndRotation(2.5F, -1.0F, -19.5F, 0.0F, 0.08726646259971647F, 0.0F));

        smallstorage_box_2.addOrReplaceChild("smallstorage_box_2_dec", CubeListBuilder.create()
                .texOffs(0, 178).addBox(-2.5F, -1.0F, -2.0F, 5, 1, 4),
                PartPose.offset(0.01F, -2.3F, 0.0F));

        smallstorage_box_2.addOrReplaceChild("smallstorage_box_2_dec_1", CubeListBuilder.create()
                .texOffs(0, 184).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1),
                PartPose.offset(1.8F, -3.0F, -2.1F));

        smallstorage_box_2.addOrReplaceChild("smallstorage_box_2_dec_2", CubeListBuilder.create()
                .texOffs(5, 184).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1),
                PartPose.offset(-1.8F, -3.0F, -2.1F));

        PartDefinition smallstorage_box_3 = smallstorage_base.addOrReplaceChild("smallstorage_box_3", CubeListBuilder.create()
                .texOffs(140, 86).addBox(-2.0F, -4.0F, -3.0F, 3, 4, 6),
                PartPose.offsetAndRotation(1.9F, -1.0F, -15.3F, 0.0F, 1.6580627893946132F, 0.0F));

        smallstorage_box_3.addOrReplaceChild("smallstorage_box_3_dec", CubeListBuilder.create()
                .texOffs(141, 89).addBox(0.0F, -1.5F, -0.5F, 1, 1, 1),
                PartPose.offset(0.4F, -2.1F, 0.0F));

        // Small storage rods
        PartDefinition smallstorage_rod = smallstorage_base.addOrReplaceChild("smallstorage_rod", CubeListBuilder.create()
                .texOffs(141, 100).addBox(0.0F, -5.0F, -0.5F, 1, 5, 1),
                PartPose.offset(8.0F, -0.5F, -12.0F));

        smallstorage_base.addOrReplaceChild("smallstorage_rod_1", CubeListBuilder.create()
                .texOffs(58, 147).addBox(0.0F, 0.0F, -9.5F, 1, 1, 10),
                PartPose.offsetAndRotation(7.99F, -5.5F, -12.5F, 0.41015237421866746F, 0.0F, 0.0F));

        smallstorage_base.addOrReplaceChild("smallstorage_rod_2", CubeListBuilder.create()
                .texOffs(81, 147).addBox(-1.0F, 0.0F, -9.5F, 1, 1, 10),
                PartPose.offsetAndRotation(-7.99F, -5.5F, -12.5F, 0.41015237421866746F, 0.0F, 0.0F));

        PartDefinition smallstorage_rod_3 = smallstorage_rod.addOrReplaceChild("smallstorage_rod_3", CubeListBuilder.create()
                .texOffs(151, 100).addBox(0.0F, -5.0F, -0.5F, 1, 5, 1),
                PartPose.offset(-17.0F, 0.0F, 0.0F));

        PartDefinition smallstorage_rod_4 = smallstorage_rod.addOrReplaceChild("smallstorage_rod_4", CubeListBuilder.create()
                .texOffs(117, 111).addBox(0.0F, -5.0F, -0.5F, 1, 5, 1),
                PartPose.offset(0.0F, 0.0F, -9.0F));

        PartDefinition smallstorage_rod_5 = smallstorage_rod_3.addOrReplaceChild("smallstorage_rod_5", CubeListBuilder.create()
                .texOffs(73, 120).addBox(0.0F, -1.0F, -0.5F, 18, 1, 1),
                PartPose.offset(0.0F, -5.0F, 0.0F));

        PartDefinition smallstorage_rod_6 = smallstorage_rod_5.addOrReplaceChild("smallstorage_rod_6", CubeListBuilder.create()
                .texOffs(112, 111).addBox(0.0F, 0.0F, -0.5F, 1, 5, 1),
                PartPose.offset(4.5F, 0.0F, -11.0F));

        smallstorage_rod_6.addOrReplaceChild("smallstorage_rod_7", CubeListBuilder.create()
                .texOffs(107, 111).addBox(0.0F, 0.0F, 0.0F, 1, 5, 1),
                PartPose.offset(8.0F, 0.0F, -0.5F));

        PartDefinition smallstorage_rod_8 = smallstorage_rod_6.addOrReplaceChild("smallstorage_rod_8", CubeListBuilder.create()
                .texOffs(91, 130).addBox(0.0F, 0.0F, -0.5F, 1, 1, 10),
                PartPose.offset(-4.5F, -1.0F, 1.0F));

        smallstorage_rod_8.addOrReplaceChild("smallstorage_rod_9", CubeListBuilder.create()
                .texOffs(78, 132).addBox(0.0F, 0.0F, 0.0F, 1, 1, 10),
                PartPose.offset(17.0F, 0.0F, -0.5F));

        PartDefinition smallstorage_rod_10 = smallstorage_rod_4.addOrReplaceChild("smallstorage_rod_10", CubeListBuilder.create()
                .texOffs(146, 100).addBox(0.0F, -5.0F, -0.5F, 1, 5, 1),
                PartPose.offset(-17.0F, 0.0F, 0.0F));

        smallstorage_rod_10.addOrReplaceChild("smallstorage_rod_11", CubeListBuilder.create()
                .texOffs(73, 123).addBox(0.0F, -1.0F, -0.5F, 18, 1, 1),
                PartPose.offset(0.0F, -5.0F, -2.0F));

        // Large storage base
        PartDefinition largestorage_base = storage_base.addOrReplaceChild("largestorage_base", CubeListBuilder.create()
                .texOffs(0, 144).addBox(-8.5F, -1.0F, -23.5F, 17, 1, 23),
                PartPose.offset(0.0F, -0.3F, 0.0F));

        largestorage_base.addOrReplaceChild("storage_metal_front_left", CubeListBuilder.create()
                .texOffs(0, 194).addBox(-1.5F, -2.0F, -1.0F, 3, 2, 2),
                PartPose.offset(4.0F, 0.7F, -23.2F));

        largestorage_base.addOrReplaceChild("storage_metal_front_right", CubeListBuilder.create()
                .texOffs(0, 199).addBox(-1.5F, -2.0F, -1.0F, 3, 2, 2),
                PartPose.offset(-4.0F, 0.7F, -23.2F));

        largestorage_base.addOrReplaceChild("storage_metal_back_left", CubeListBuilder.create()
                .texOffs(0, 204).addBox(-1.5F, -2.0F, -1.0F, 3, 2, 2),
                PartPose.offset(4.0F, 0.7F, -0.8F));

        largestorage_base.addOrReplaceChild("storage_metal_back_right", CubeListBuilder.create()
                .texOffs(0, 209).addBox(-1.5F, -2.0F, -1.0F, 3, 2, 2),
                PartPose.offset(-4.0F, 0.7F, -0.8F));

        // Large storage boxes
        PartDefinition storage_box_1 = largestorage_base.addOrReplaceChild("storage_box_1", CubeListBuilder.create()
                .texOffs(0, 218).addBox(-2.0F, -5.0F, -4.0F, 4, 5, 8),
                PartPose.offsetAndRotation(-4.5F, -1.0F, -17.5F, 0.0F, -0.18203784098300857F, 0.0F));

        storage_box_1.addOrReplaceChild("storage_box_1_dec", CubeListBuilder.create()
                .texOffs(0, 222).addBox(0.0F, -1.5F, -0.5F, 1, 2, 1),
                PartPose.offset(1.5F, -2.8F, 0.0F));

        PartDefinition storage_box_2 = largestorage_base.addOrReplaceChild("storage_box_2", CubeListBuilder.create()
                .texOffs(0, 238).addBox(-2.5F, -3.0F, -2.5F, 5, 3, 5),
                PartPose.offsetAndRotation(2.5F, -1.0F, -19.5F, 0.0F, 0.08726646259971647F, 0.0F));

        storage_box_2.addOrReplaceChild("storage_box_2_dec", CubeListBuilder.create()
                .texOffs(0, 232).addBox(-2.5F, -1.0F, -2.0F, 5, 1, 4),
                PartPose.offset(0.01F, -2.3F, 0.0F));

        storage_box_2.addOrReplaceChild("storage_box_2_dec_1", CubeListBuilder.create()
                .texOffs(16, 240).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1),
                PartPose.offset(1.8F, -3.0F, -2.1F));

        storage_box_2.addOrReplaceChild("storage_box_2_dec_2", CubeListBuilder.create()
                .texOffs(15, 233).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1),
                PartPose.offset(-1.8F, -3.0F, -2.1F));

        PartDefinition storage_box_3 = largestorage_base.addOrReplaceChild("storage_box_3", CubeListBuilder.create()
                .texOffs(0, 246).addBox(-2.0F, -4.0F, -3.0F, 3, 4, 6),
                PartPose.offsetAndRotation(1.9F, -1.0F, -14.1F, 0.0F, -1.6580627893946132F, 0.0F));

        storage_box_3.addOrReplaceChild("storage_box_3_dec", CubeListBuilder.create()
                .texOffs(0, 249).addBox(0.0F, -1.5F, -0.5F, 1, 1, 1),
                PartPose.offset(0.4F, -2.1F, 0.0F));

        PartDefinition storage_box_4 = largestorage_base.addOrReplaceChild("storage_box_4", CubeListBuilder.create()
                .texOffs(25, 218).addBox(-2.0F, -5.0F, -4.0F, 4, 5, 8),
                PartPose.offsetAndRotation(4.4F, -1.0F, -8.0F, 0.0F, 3.050486466635689F, 0.0F));

        storage_box_4.addOrReplaceChild("storage_box_4_dec", CubeListBuilder.create()
                .texOffs(25, 222).addBox(0.0F, -1.5F, -0.5F, 1, 2, 1),
                PartPose.offset(1.5F, -2.8F, 0.0F));

        PartDefinition storage_box_5 = largestorage_base.addOrReplaceChild("storage_box_5", CubeListBuilder.create()
                .texOffs(25, 232).addBox(-2.5F, -3.0F, -2.5F, 5, 3, 5),
                PartPose.offsetAndRotation(-4.0F, -1.0F, -10.0F, 0.0F, 1.7756979809790308F, 0.0F));

        storage_box_5.addOrReplaceChild("storage_box_5_dec", CubeListBuilder.create()
                .texOffs(25, 241).addBox(-2.5F, -1.0F, -2.0F, 5, 1, 4),
                PartPose.offset(0.01F, -2.3F, 0.0F));

        storage_box_5.addOrReplaceChild("storage_box_5_dec_1", CubeListBuilder.create()
                .texOffs(40, 242).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1),
                PartPose.offset(1.8F, -3.0F, -2.1F));

        storage_box_5.addOrReplaceChild("storage_box_5_dec_2", CubeListBuilder.create()
                .texOffs(41, 234).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1),
                PartPose.offset(-1.8F, -3.0F, -2.1F));

        PartDefinition storage_box_6 = largestorage_base.addOrReplaceChild("storage_box_6", CubeListBuilder.create()
                .texOffs(19, 246).addBox(-1.5F, -4.0F, -3.0F, 3, 4, 6),
                PartPose.offsetAndRotation(-2.0F, -1.0F, -4.0F, 0.0F, 1.3658946726107624F, 0.0F));

        storage_box_6.addOrReplaceChild("storage_box_6_dec", CubeListBuilder.create()
                .texOffs(32, 249).addBox(0.0F, -1.5F, -0.5F, 1, 1, 1),
                PartPose.offset(0.9F, -2.1F, 0.0F));

        // Large storage rods
        PartDefinition storage_rod = largestorage_base.addOrReplaceChild("storage_rod", CubeListBuilder.create()
                .texOffs(49, 194).addBox(0.0F, -5.0F, -0.5F, 1, 5, 1),
                PartPose.offset(8.0F, -0.5F, -12.0F));

        largestorage_base.addOrReplaceChild("storage_rod_1", CubeListBuilder.create()
                .texOffs(38, 245).addBox(0.0F, 0.0F, -9.5F, 1, 1, 10),
                PartPose.offsetAndRotation(7.99F, -5.5F, -12.5F, 0.41015237421866746F, 0.0F, 0.0F));

        largestorage_base.addOrReplaceChild("storage_rod_2", CubeListBuilder.create()
                .texOffs(51, 243).addBox(-1.0F, 0.0F, -9.5F, 1, 1, 10),
                PartPose.offsetAndRotation(-7.99F, -5.5F, -12.5F, 0.41015237421866746F, 0.0F, 0.0F));

        PartDefinition storage_rod_3 = storage_rod.addOrReplaceChild("storage_rod_3", CubeListBuilder.create()
                .texOffs(49, 201).addBox(0.0F, -5.0F, -0.5F, 1, 5, 1),
                PartPose.offset(-17.0F, 0.0F, 0.0F));

        PartDefinition storage_rod_4 = storage_rod.addOrReplaceChild("storage_rod_4", CubeListBuilder.create()
                .texOffs(44, 194).addBox(0.0F, -5.0F, -0.5F, 1, 5, 1),
                PartPose.offset(0.0F, 0.0F, -9.0F));

        PartDefinition storage_rod_5 = storage_rod.addOrReplaceChild("storage_rod_5", CubeListBuilder.create()
                .texOffs(50, 218).addBox(0.0F, 0.0F, 0.0F, 1, 1, 10),
                PartPose.offset(0.0F, -6.0F, -10.5F));

        PartDefinition storage_rod_6 = storage_rod_3.addOrReplaceChild("storage_rod_6", CubeListBuilder.create()
                .texOffs(46, 230).addBox(0.0F, -1.0F, -0.5F, 1, 1, 11),
                PartPose.offset(0.0F, -5.0F, 0.0F));

        PartDefinition storage_rod_7 = storage_rod_6.addOrReplaceChild("storage_rod_7", CubeListBuilder.create()
                .texOffs(39, 201).addBox(0.0F, 0.0F, -0.5F, 1, 5, 1),
                PartPose.offset(4.5F, 0.0F, -11.0F));

        PartDefinition storage_rod_8 = storage_rod_7.addOrReplaceChild("storage_rod_8", CubeListBuilder.create()
                .texOffs(39, 194).addBox(0.0F, 0.0F, 0.0F, 1, 5, 1),
                PartPose.offset(8.0F, 0.0F, -0.5F));

        storage_rod_7.addOrReplaceChild("storage_rod_9", CubeListBuilder.create()
                .texOffs(73, 218).addBox(0.0F, 0.0F, -0.5F, 1, 1, 10),
                PartPose.offset(-4.5F, -1.0F, 1.0F));

        PartDefinition storage_rod_10 = storage_rod_4.addOrReplaceChild("storage_rod_10", CubeListBuilder.create()
                .texOffs(44, 201).addBox(0.0F, -5.0F, -0.5F, 1, 5, 1),
                PartPose.offset(-17.0F, 0.0F, 0.0F));

        storage_rod_10.addOrReplaceChild("storage_rod_11", CubeListBuilder.create()
                .texOffs(44, 169).addBox(0.0F, -1.0F, -0.5F, 18, 1, 1),
                PartPose.offset(0.0F, -5.0F, -2.0F));

        PartDefinition storage_rod_12 = storage_rod_5.addOrReplaceChild("storage_rod_12", CubeListBuilder.create()
                .texOffs(71, 230).addBox(0.0F, 0.0F, 0.0F, 1, 1, 11),
                PartPose.offset(0.0F, 0.0F, 10.0F));

        PartDefinition storage_rod_13 = storage_rod_5.addOrReplaceChild("storage_rod_13", CubeListBuilder.create()
                .texOffs(44, 172).addBox(-12.5F, -1.0F, 0.0F, 18, 1, 1),
                PartPose.offsetAndRotation(-11.5F, 1.0F, 22.0F, 0.0F, 3.141592653589793F, 0.0F));

        PartDefinition storage_rod_14 = storage_rod_12.addOrReplaceChild("storage_rod_14", CubeListBuilder.create()
                .texOffs(59, 194).addBox(0.0F, 0.0F, -0.5F, 1, 5, 1),
                PartPose.offsetAndRotation(-3.5F, 1.0F, 11.5F, 0.0F, 3.141592653589793F, 0.0F));

        PartDefinition storage_rod_15 = storage_rod_14.addOrReplaceChild("storage_rod_15", CubeListBuilder.create()
                .texOffs(59, 201).addBox(0.0F, 0.0F, 0.0F, 1, 5, 1),
                PartPose.offset(8.0F, 0.0F, -0.5F));

        PartDefinition storage_rod_16 = storage_rod_13.addOrReplaceChild("storage_rod_16", CubeListBuilder.create()
                .texOffs(54, 201).addBox(0.0F, 0.0F, 0.0F, 1, 5, 1),
                PartPose.offset(4.5F, 0.0F, 2.0F));

        PartDefinition storage_rod_17 = storage_rod_16.addOrReplaceChild("storage_rod_17", CubeListBuilder.create()
                .texOffs(54, 194).addBox(0.0F, 0.0F, 0.0F, 1, 5, 1),
                PartPose.offset(-17.0F, 0.0F, 0.0F));

        PartDefinition storage_rod_18 = storage_rod_17.addOrReplaceChild("storage_rod_18", CubeListBuilder.create()
                .texOffs(64, 245).addBox(0.0F, 0.0F, -9.5F, 1, 1, 10),
                PartPose.offsetAndRotation(0.02F, 0.0F, 9.0F, 0.41015237421866746F, 0.0F, 0.0F));

        storage_rod_18.addOrReplaceChild("storage_rod_19", CubeListBuilder.create()
                .texOffs(77, 243).addBox(0.0F, 0.0F, -9.3F, 1, 1, 10),
                PartPose.offset(16.94F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        // Process model part visibility
        this.front_seat_base.visible = entity.isSaddled();
        this.storage_base.visible = entity.hasBackAttachment();

        // Set all back attachment parts invisible before selecting which one to show
        this.seats_base.visible = false;
        this.smallstorage_base.visible = false;
        this.largestorage_base.visible = false;

        // Initially hide all storage boxes
        this.smallstorage_box_1.visible = false;
        this.smallstorage_box_2.visible = false;
        this.smallstorage_box_3.visible = false;
        this.storage_box_1.visible = false;
        this.storage_box_2.visible = false;
        this.storage_box_3.visible = false;
        this.storage_box_4.visible = false;
        this.storage_box_5.visible = false;
        this.storage_box_6.visible = false;

        switch (entity.getBackAttachment()) {
            case PLAYER_SEATS -> this.seats_base.visible = true;
            case SMALL_STORAGE -> {
                this.smallstorage_base.visible = true;
                processSmallStorageBoxVisibility(entity);
            }
            case LARGE_STORAGE -> {
                this.largestorage_base.visible = true;
                processLargeStorageBoxVisibility(entity);
            }
            default -> {
                // Nothing as all model parts are hidden
            }
        }

        if (entity.isSitting() && !entity.isVehicle()) {
            animateSitting(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        } else if (!entity.isMoving()) {
            animateIdle(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        } else {
            animateWalking(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        }
    }

    private void processSmallStorageBoxVisibility(T entity) {
        int visibleBoxes = BufflonStorageVisuals.getVisibleBoxCount(entity.getBackAttachment(), entity.getOccupiedStorageSlotCount());
        this.smallstorage_box_1.visible = visibleBoxes >= 1;
        this.smallstorage_box_2.visible = visibleBoxes >= 2;
        this.smallstorage_box_3.visible = visibleBoxes >= 3;
    }

    private void processLargeStorageBoxVisibility(T entity) {
        int visibleBoxes = BufflonStorageVisuals.getVisibleBoxCount(entity.getBackAttachment(), entity.getOccupiedStorageSlotCount());
        this.storage_box_1.visible = visibleBoxes >= 1;
        this.storage_box_2.visible = visibleBoxes >= 2;
        this.storage_box_3.visible = visibleBoxes >= 3;
        this.storage_box_4.visible = visibleBoxes >= 4;
        this.storage_box_5.visible = visibleBoxes >= 5;
        this.storage_box_6.visible = visibleBoxes >= 6;
    }

    private void animateSitting(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        body.y += 1.7F;
        body.xRot += 0.05F + (float) Math.toRadians(-8);
        body_back.xRot += (float) Math.toRadians(-25);

        leg_back_right_1.xRot += (float) Math.toRadians(-50);
        leg_back_right_2.xRot += (float) Math.toRadians(50);
        foot_back_right_base.xRot += (float) Math.toRadians(28);
        leg_back_left_1.xRot += (float) Math.toRadians(-50);
        leg_back_left_2.xRot += (float) Math.toRadians(50);
        foot_back_left_base.xRot += (float) Math.toRadians(28);

        tail_1.xRot += (float) Math.toRadians(10);
        tail_2.xRot += (float) Math.toRadians(10);
        tail_3.xRot += (float) Math.toRadians(10);

        leg_front_left_1.xRot += (float) Math.toRadians(5);
        leg_front_right_1.xRot += (float) Math.toRadians(5);
        foot_front_left_base.xRot += (float) Math.toRadians(3);
        foot_front_right_base.xRot += (float) Math.toRadians(3);

        // Head rotation
        neck.yRot = normalizeHeadYaw(netHeadYaw) * ((float) Math.PI / 180) / 2;

        float globalSpeed = 1.0F;
        float globalDegree = 1.0F;
        float tick = entity.tickCount;

        bounce(body, globalSpeed * 0.3F, 0.3F, false, tick, 1);
        swing(body_back, globalSpeed * 0.3F, globalDegree * 0.005F, true, 1.0F, 0.0F, tick, 1);

        swing(neck, globalSpeed * 0.3F, globalDegree * 0.01F, true, 0.0F, 0.0F, tick, 1);
        swing(head_base, globalSpeed * 0.3F, globalDegree * 0.01F, true, 0.0F, 0.0F, tick, 1);

        swing(tail_1, globalSpeed * 0.3F, globalDegree * 0.02F, true, 0.0F, 0.0F, tick, 1);
        swing(tail_2, globalSpeed * 0.3F, globalDegree * 0.02F, true, 0.0F, 0.0F, tick, 1);
        swing(tail_3, globalSpeed * 0.3F, globalDegree * 0.02F, true, 0.0F, 0.0F, tick, 1);
        swing(tail_4, globalSpeed * 0.3F, globalDegree * 0.02F, true, 0.0F, 0.0F, tick, 1);
    }

    private void animateIdle(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        neck.yRot += normalizeHeadYaw(netHeadYaw) * ((float) Math.PI / 180) / 4;

        float globalSpeed = 0.8F;
        float globalDegree = 1.0F;
        float tick = entity.tickCount;

        bounce(body, globalSpeed * 0.3F, 0.5F, false, tick, 1);

        swing(neck, globalSpeed * 0.3F, globalDegree * 0.03F, true, -0.5F, 0.0F, tick, 1);
        swing(body_back, globalSpeed * 0.3F, globalDegree * 0.01F, false, 0.0F, 0.0F, tick, 1);

        bounce(leg_front_left_1, globalSpeed * 0.3F, -0.3F, false, tick, 1);
        swing(leg_front_left_1, globalSpeed * 0.3F, globalDegree * 0.01F, false, 1.0F, 0.0F, tick, 1);
        swing(leg_front_left_2, globalSpeed * 0.3F, globalDegree * 0.02F, false, 1.0F, 0.0F, tick, 1);
        swing(foot_front_left_base, globalSpeed * 0.3F, globalDegree * 0.04F, true, 1.0F, 0.0F, tick, 1);

        bounce(leg_front_right_1, globalSpeed * 0.3F, -0.3F, false, tick, 1);
        swing(leg_front_right_1, globalSpeed * 0.3F, globalDegree * 0.01F, false, 1.0F, 0.0F, tick, 1);
        swing(leg_front_right_2, globalSpeed * 0.3F, globalDegree * 0.02F, false, 1.0F, 0.0F, tick, 1);
        swing(foot_front_right_base, globalSpeed * 0.3F, globalDegree * 0.04F, true, 1.0F, 0.0F, tick, 1);

        bounce(leg_back_left_1, globalSpeed * 0.3F, -0.4F, false, tick, 1);
        swing(leg_back_left_1, globalSpeed * 0.3F, globalDegree * 0.01F, true, 1.0F, 0.0F, tick, 1);
        swing(leg_back_left_2, globalSpeed * 0.3F, globalDegree * 0.02F, true, 1.0F, 0.0F, tick, 1);
        swing(foot_back_left_base, globalSpeed * 0.3F, globalDegree * 0.04F, true, 0.0F, 0.0F, tick, 1);

        bounce(leg_back_right_1, globalSpeed * 0.3F, -0.4F, false, tick, 1);
        swing(leg_back_right_1, globalSpeed * 0.3F, globalDegree * 0.01F, true, 1.0F, 0.0F, tick, 1);
        swing(leg_back_right_2, globalSpeed * 0.3F, globalDegree * 0.02F, true, 1.0F, 0.0F, tick, 1);
        swing(foot_back_right_base, globalSpeed * 0.3F, globalDegree * 0.04F, true, 0.0F, 0.0F, tick, 1);

        swing(tail_1, globalSpeed * 0.3F, globalDegree * 0.02F, false, 0.0F, 0.0F, tick, 1);
        swing(tail_2, globalSpeed * 0.3F, globalDegree * 0.02F, false, 0.0F, 0.0F, tick, 1);
        swing(tail_3, globalSpeed * 0.3F, globalDegree * 0.02F, false, 0.0F, 0.0F, tick, 1);
        swing(tail_4, globalSpeed * 0.3F, globalDegree * 0.02F, false, 0.0F, 0.0F, tick, 1);
    }

    private void animateWalking(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float globalSpeed = 3.6F;
        float globalDegree = 1.0F;

        bounce(body, globalSpeed * 0.3F, 0.5F, false, limbSwing, limbSwingAmount);
        swing(body, globalSpeed * 0.3F, globalDegree * 0.01F, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(body_back, globalSpeed * 0.3F, globalDegree * 0.01F, true, -1.0F, 0.0F, limbSwing, limbSwingAmount);

        swing(neck, globalSpeed * 0.3F, globalDegree * 0.03F, true, 2.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(head_base, globalSpeed * 0.3F, globalDegree * 0.02F, false, 2.0F, 0.0F, limbSwing, limbSwingAmount);

        swing(leg_front_left_1, globalSpeed * 0.15F, globalDegree * 0.35F, true, 1.7F, 0.05F, limbSwing, limbSwingAmount);
        swing(leg_front_left_2, globalSpeed * 0.15F, globalDegree * 0.3F, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(foot_front_left_base, globalSpeed * 0.15F, globalDegree * 0.25F, false, 1.5F, -0.25F, limbSwing, limbSwingAmount);

        swing(leg_front_right_1, globalSpeed * 0.15F, globalDegree * 0.35F, false, 1.7F, 0.05F, limbSwing, limbSwingAmount);
        swing(leg_front_right_2, globalSpeed * 0.15F, globalDegree * 0.3F, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(foot_front_right_base, globalSpeed * 0.15F, globalDegree * 0.25F, true, 1.5F, -0.25F, limbSwing, limbSwingAmount);

        swing(leg_back_right_1, globalSpeed * 0.15F, globalDegree * 0.3F, false, -3.0F, -0.1F, limbSwing, limbSwingAmount);
        swing(leg_back_right_2, globalSpeed * 0.15F, globalDegree * 0.3F, true, -2.0F, 0.2F, limbSwing, limbSwingAmount);
        swing(foot_back_right_base, globalSpeed * 0.15F, globalDegree * 0.35F, false, 0.0F, 0.1F, limbSwing, limbSwingAmount);

        swing(leg_back_left_1, globalSpeed * 0.15F, globalDegree * 0.3F, true, -3.0F, -0.1F, limbSwing, limbSwingAmount);
        swing(leg_back_left_2, globalSpeed * 0.15F, globalDegree * 0.3F, false, -2.0F, 0.2F, limbSwing, limbSwingAmount);
        swing(foot_back_left_base, globalSpeed * 0.15F, globalDegree * 0.35F, true, 0.0F, 0.1F, limbSwing, limbSwingAmount);

        swing(tail_1, globalSpeed * 0.3F, globalDegree * 0.03F, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_2, globalSpeed * 0.3F, globalDegree * 0.03F, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_3, globalSpeed * 0.3F, globalDegree * 0.03F, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(tail_4, globalSpeed * 0.3F, globalDegree * 0.03F, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
    }

    private float normalizeHeadYaw(float netHeadYaw) {
        if (netHeadYaw < -180) {
            return netHeadYaw + 360;
        } else if (netHeadYaw > 180) {
            return netHeadYaw - 360;
        }
        return netHeadYaw;
    }
}
