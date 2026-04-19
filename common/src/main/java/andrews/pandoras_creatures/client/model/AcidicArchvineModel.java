package andrews.pandoras_creatures.client.model;

import andrews.pandoras_creatures.client.model.base.PCEntityModel;
import andrews.pandoras_creatures.entities.AcidicArchvineEntity;
import andrews.pandoras_creatures.entities.acidic_archvine.AcidicArchvineAttackState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class AcidicArchvineModel<T extends AcidicArchvineEntity> extends PCEntityModel<T> {
    // Base and roots
    private final ModelPart base;
    private final ModelPart base_top;
    private final ModelPart root_front;
    private final ModelPart root_back;
    private final ModelPart root_right;
    private final ModelPart root_left;

    // Rotation points and corner roots
    private final ModelPart rotation_point_front_right;
    private final ModelPart rotation_point_front_left;
    private final ModelPart rotation_point_back_right;
    private final ModelPart rotation_point_back_left;
    private final ModelPart root_front_right;
    private final ModelPart root_front_left;
    private final ModelPart root_back_right;
    private final ModelPart root_back_left;

    // Main leaves
    private final ModelPart leaf_right;
    private final ModelPart leaf_left;
    private final ModelPart leaf_front;
    private final ModelPart leaf_back;
    private final ModelPart leaf_right_top;
    private final ModelPart leaf_left_top;
    private final ModelPart leaf_front_top;
    private final ModelPart leaf_back_top;

    // Corner leaves
    private final ModelPart leaf_front_right;
    private final ModelPart leaf_front_right_top;
    private final ModelPart leaf_front_left;
    private final ModelPart leaf_front_left_top;
    private final ModelPart leaf_back_right;
    private final ModelPart leaf_back_right_top;
    private final ModelPart leaft_back_left;
    private final ModelPart leaft_back_left_top;

    // Top bases
    private final ModelPart top_front_base;
    private final ModelPart top_back_base;
    private final ModelPart top_left_base;
    private final ModelPart top_right_base;

    // Front head
    private final ModelPart top_front_1;
    private final ModelPart top_front_2;
    private final ModelPart top_front_3;
    private final ModelPart top_front_4;
    private final ModelPart top_front_1_right;
    private final ModelPart top_front_1_left;
    private final ModelPart top_front_3_right;
    private final ModelPart top_front_3_left;
    private final ModelPart top_front_4_left;
    private final ModelPart top_front_4_right;
    private final ModelPart top_front_4_top;
    private final ModelPart tooth_front_1;
    private final ModelPart tooth_front_2;
    private final ModelPart tooth_front_3;

    // Back head
    private final ModelPart top_back_1;
    private final ModelPart top_back_2;
    private final ModelPart top_back_3;
    private final ModelPart top_back_4;
    private final ModelPart top_back_1_right;
    private final ModelPart top_back_1_left;
    private final ModelPart top_back_3_right;
    private final ModelPart top_back_3_left;
    private final ModelPart top_back_4_left;
    private final ModelPart top_back_4_right;
    private final ModelPart top_back_4_top;
    private final ModelPart tooth_back_1;
    private final ModelPart tooth_back_2;
    private final ModelPart tooth_back_3;

    // Left head
    private final ModelPart top_left_1;
    private final ModelPart top_left_2;
    private final ModelPart top_left_3;
    private final ModelPart top_left_4;
    private final ModelPart top_left_1_right;
    private final ModelPart top_left_1_left;
    private final ModelPart top_left_3_right;
    private final ModelPart top_left_3_left;
    private final ModelPart top_left_4_left;
    private final ModelPart top_left_4_right;
    private final ModelPart top_left_4_top;
    private final ModelPart tooth_left_1;
    private final ModelPart tooth_left_2;
    private final ModelPart tooth_left_3;

    // Right head
    private final ModelPart top_right_1;
    private final ModelPart top_right_2;
    private final ModelPart top_right_3;
    private final ModelPart top_right_4;
    private final ModelPart top_right_1_right;
    private final ModelPart top_right_1_left;
    private final ModelPart top_right_3_right;
    private final ModelPart top_right_3_left;
    private final ModelPart top_right_4_left;
    private final ModelPart top_right_4_right;
    private final ModelPart top_right_4_top;
    private final ModelPart tooth_right_1;
    private final ModelPart tooth_right_2;
    private final ModelPart tooth_right_3;

    // Tongue
    private final ModelPart tongue_1;
    private final ModelPart tongue_2;
    private final ModelPart tongue_3;
    private final ModelPart tongue_4;

    // Acid blob
    private final ModelPart acid_blob_holder;
    private final ModelPart acid_blob;

    private float partialTicks;

    public AcidicArchvineModel(ModelPart root) {
        super(root);

        // Base and roots
        this.base = root.getChild("base");
        this.base_top = this.base.getChild("base_top");
        this.root_front = this.base.getChild("root_front");
        this.root_back = this.base.getChild("root_back");
        this.root_right = this.base.getChild("root_right");
        this.root_left = this.base.getChild("root_left");

        // Rotation points and corner roots
        this.rotation_point_front_right = this.base.getChild("rotation_point_front_right");
        this.rotation_point_front_left = this.base.getChild("rotation_point_front_left");
        this.rotation_point_back_right = this.base.getChild("rotation_point_back_right");
        this.rotation_point_back_left = this.base.getChild("rotation_point_back_left");
        this.root_front_right = this.rotation_point_front_right.getChild("root_front_right");
        this.root_front_left = this.rotation_point_front_left.getChild("root_front_left");
        this.root_back_right = this.rotation_point_back_right.getChild("root_back_right");
        this.root_back_left = this.rotation_point_back_left.getChild("root_back_left");

        // Main leaves
        this.leaf_right = this.base.getChild("leaf_right");
        this.leaf_left = this.base.getChild("leaf_left");
        this.leaf_front = this.base.getChild("leaf_front");
        this.leaf_back = this.base.getChild("leaf_back");
        this.leaf_right_top = this.leaf_right.getChild("leaf_right_top");
        this.leaf_left_top = this.leaf_left.getChild("leaf_left_top");
        this.leaf_front_top = this.leaf_front.getChild("leaf_front_top");
        this.leaf_back_top = this.leaf_back.getChild("leaf_back_top");

        // Corner leaves
        this.leaf_front_right = this.rotation_point_front_right.getChild("leaf_front_right");
        this.leaf_front_right_top = this.leaf_front_right.getChild("leaf_front_right_top");
        this.leaf_front_left = this.rotation_point_front_left.getChild("leaf_front_left");
        this.leaf_front_left_top = this.leaf_front_left.getChild("leaf_front_left_top");
        this.leaf_back_right = this.rotation_point_back_right.getChild("leaf_back_right");
        this.leaf_back_right_top = this.leaf_back_right.getChild("leaf_back_right_top");
        this.leaft_back_left = this.rotation_point_back_left.getChild("leaft_back_left");
        this.leaft_back_left_top = this.leaft_back_left.getChild("leaft_back_left_top");

        // Top bases
        this.top_front_base = this.base_top.getChild("top_front_base");
        this.top_back_base = this.base_top.getChild("top_back_base");
        this.top_left_base = this.base_top.getChild("top_left_base");
        this.top_right_base = this.base_top.getChild("top_right_base");

        // Front head
        this.top_front_1 = this.top_front_base.getChild("top_front_1");
        this.top_front_2 = this.top_front_1.getChild("top_front_2");
        this.top_front_3 = this.top_front_2.getChild("top_front_3");
        this.top_front_4 = this.top_front_3.getChild("top_front_4");
        this.top_front_1_right = this.top_front_1.getChild("top_front_1_right");
        this.top_front_1_left = this.top_front_1.getChild("top_front_1_left");
        this.top_front_3_right = this.top_front_3.getChild("top_front_3_right");
        this.top_front_3_left = this.top_front_3.getChild("top_front_3_left");
        this.top_front_4_left = this.top_front_4.getChild("top_front_4_left");
        this.top_front_4_right = this.top_front_4.getChild("top_front_4_right");
        this.top_front_4_top = this.top_front_4.getChild("top_front_4_top");
        this.tooth_front_1 = this.top_front_1.getChild("tooth_front_1");
        this.tooth_front_2 = this.top_front_2.getChild("tooth_front_2");
        this.tooth_front_3 = this.top_front_3.getChild("tooth_front_3");

        // Back head
        this.top_back_1 = this.top_back_base.getChild("top_back_1");
        this.top_back_2 = this.top_back_1.getChild("top_back_2");
        this.top_back_3 = this.top_back_2.getChild("top_back_3");
        this.top_back_4 = this.top_back_3.getChild("top_back_4");
        this.top_back_1_right = this.top_back_1.getChild("top_back_1_right");
        this.top_back_1_left = this.top_back_1.getChild("top_back_1_left");
        this.top_back_3_right = this.top_back_3.getChild("top_back_3_right");
        this.top_back_3_left = this.top_back_3.getChild("top_back_3_left");
        this.top_back_4_left = this.top_back_4.getChild("top_back_4_left");
        this.top_back_4_right = this.top_back_4.getChild("top_back_4_right");
        this.top_back_4_top = this.top_back_4.getChild("top_back_4_top");
        this.tooth_back_1 = this.top_back_1.getChild("tooth_back_1");
        this.tooth_back_2 = this.top_back_2.getChild("tooth_back_2");
        this.tooth_back_3 = this.top_back_3.getChild("tooth_back_3");

        // Left head
        this.top_left_1 = this.top_left_base.getChild("top_left_1");
        this.top_left_2 = this.top_left_1.getChild("top_left_2");
        this.top_left_3 = this.top_left_2.getChild("top_left_3");
        this.top_left_4 = this.top_left_3.getChild("top_left_4");
        this.top_left_1_right = this.top_left_1.getChild("top_left_1_right");
        this.top_left_1_left = this.top_left_1.getChild("top_left_1_left");
        this.top_left_3_right = this.top_left_3.getChild("top_left_3_right");
        this.top_left_3_left = this.top_left_3.getChild("top_left_3_left");
        this.top_left_4_left = this.top_left_4.getChild("top_left_4_left");
        this.top_left_4_right = this.top_left_4.getChild("top_left_4_right");
        this.top_left_4_top = this.top_left_4.getChild("top_left_4_top");
        this.tooth_left_1 = this.top_left_1.getChild("tooth_left_1");
        this.tooth_left_2 = this.top_left_2.getChild("tooth_left_2");
        this.tooth_left_3 = this.top_left_3.getChild("tooth_left_3");

        // Right head
        this.top_right_1 = this.top_right_base.getChild("top_right_1");
        this.top_right_2 = this.top_right_1.getChild("top_right_2");
        this.top_right_3 = this.top_right_2.getChild("top_right_3");
        this.top_right_4 = this.top_right_3.getChild("top_right_4");
        this.top_right_1_right = this.top_right_1.getChild("top_right_1_right");
        this.top_right_1_left = this.top_right_1.getChild("top_right_1_left");
        this.top_right_3_right = this.top_right_3.getChild("top_right_3_right");
        this.top_right_3_left = this.top_right_3.getChild("top_right_3_left");
        this.top_right_4_left = this.top_right_4.getChild("top_right_4_left");
        this.top_right_4_right = this.top_right_4.getChild("top_right_4_right");
        this.top_right_4_top = this.top_right_4.getChild("top_right_4_top");
        this.tooth_right_1 = this.top_right_1.getChild("tooth_right_1");
        this.tooth_right_2 = this.top_right_2.getChild("tooth_right_2");
        this.tooth_right_3 = this.top_right_3.getChild("tooth_right_3");

        // Tongue
        this.tongue_1 = root.getChild("tongue_1");
        this.tongue_2 = this.tongue_1.getChild("tongue_2");
        this.tongue_3 = this.tongue_2.getChild("tongue_3");
        this.tongue_4 = this.tongue_3.getChild("tongue_4");

        // Acid blob
        this.acid_blob_holder = root.getChild("acid_blob_holder");
        this.acid_blob = this.acid_blob_holder.getChild("acid_blob");
        this.acid_blob_holder.visible = false;

        // Register animated parts
        registerAllAnimatedParts();
    }

    private void registerAllAnimatedParts() {
        registerAnimatedPart(base);
        registerAnimatedPart(leaf_front);
        registerAnimatedPart(leaf_front_top);
        registerAnimatedPart(leaf_back);
        registerAnimatedPart(leaf_back_top);
        registerAnimatedPart(leaf_left);
        registerAnimatedPart(leaf_left_top);
        registerAnimatedPart(leaf_right);
        registerAnimatedPart(leaf_right_top);
        registerAnimatedPart(leaf_front_left);
        registerAnimatedPart(leaf_front_left_top);
        registerAnimatedPart(leaf_front_right);
        registerAnimatedPart(leaf_front_right_top);
        registerAnimatedPart(leaft_back_left);
        registerAnimatedPart(leaft_back_left_top);
        registerAnimatedPart(leaf_back_right);
        registerAnimatedPart(leaf_back_right_top);
        registerAnimatedPart(tongue_1);
        registerAnimatedPart(tongue_2);
        registerAnimatedPart(tongue_3);
        registerAnimatedPart(tongue_4);
        registerAnimatedPart(top_front_1);
        registerAnimatedPart(top_front_2);
        registerAnimatedPart(top_front_3);
        registerAnimatedPart(top_front_4);
        registerAnimatedPart(top_back_1);
        registerAnimatedPart(top_back_2);
        registerAnimatedPart(top_back_3);
        registerAnimatedPart(top_back_4);
        registerAnimatedPart(top_left_1);
        registerAnimatedPart(top_left_2);
        registerAnimatedPart(top_left_3);
        registerAnimatedPart(top_left_4);
        registerAnimatedPart(top_right_1);
        registerAnimatedPart(top_right_2);
        registerAnimatedPart(top_right_3);
        registerAnimatedPart(top_right_4);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        // Base
        PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create()
                .texOffs(224, 0).addBox(-4.0F, 6.0F, -4.0F, 8, 2, 8),
                PartPose.offset(0.0F, 16.0F, 0.0F));

        // Base top
        PartDefinition base_top = base.addOrReplaceChild("base_top", CubeListBuilder.create()
                .texOffs(199, 0).addBox(-3.0F, 4.5F, -3.0F, 6, 2, 6),
                PartPose.ZERO);

        // Roots
        base.addOrReplaceChild("root_front", CubeListBuilder.create()
                .texOffs(244, 11).addBox(-1.0F, 0.0F, -4.0F, 2, 1, 4),
                PartPose.offsetAndRotation(0.0F, 6.2F, -4.0F, 0.47123889803846897F, 0.0F, 0.0F));
        base.addOrReplaceChild("root_back", CubeListBuilder.create()
                .texOffs(231, 11).addBox(-1.0F, 0.0F, 0.0F, 2, 1, 4),
                PartPose.offsetAndRotation(0.0F, 6.2F, 4.0F, -0.47123889803846897F, 0.0F, 0.0F));
        base.addOrReplaceChild("root_right", CubeListBuilder.create()
                .texOffs(244, 17).addBox(-4.0F, 0.0F, -1.0F, 4, 1, 2),
                PartPose.offsetAndRotation(-4.0F, 6.2F, 0.0F, 0.0F, 0.0F, -0.47123889803846897F));
        base.addOrReplaceChild("root_left", CubeListBuilder.create()
                .texOffs(231, 17).addBox(0.0F, 0.0F, -1.0F, 4, 1, 2),
                PartPose.offsetAndRotation(4.0F, 6.2F, 0.0F, 0.0F, 0.0F, 0.47123889803846897F));

        // Rotation points
        PartDefinition rotation_point_front_right = base.addOrReplaceChild("rotation_point_front_right", CubeListBuilder.create()
                .texOffs(252, 21).addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1),
                PartPose.offsetAndRotation(-3.0F, 6.2F, -3.0F, 0.0F, -0.7853981633974483F, 0.0F));
        PartDefinition rotation_point_front_left = base.addOrReplaceChild("rotation_point_front_left", CubeListBuilder.create()
                .texOffs(247, 21).addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1),
                PartPose.offsetAndRotation(3.0F, 6.2F, -3.0F, 0.0F, 0.7853981633974483F, 0.0F));
        PartDefinition rotation_point_back_right = base.addOrReplaceChild("rotation_point_back_right", CubeListBuilder.create()
                .texOffs(242, 21).addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1),
                PartPose.offsetAndRotation(-3.0F, 6.2F, 3.0F, 0.0F, 0.7853981633974483F, 0.0F));
        PartDefinition rotation_point_back_left = base.addOrReplaceChild("rotation_point_back_left", CubeListBuilder.create()
                .texOffs(237, 21).addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1),
                PartPose.offsetAndRotation(3.0F, 6.2F, 3.0F, 0.0F, -0.7853981633974483F, 0.0F));

        // Corner roots
        rotation_point_front_right.addOrReplaceChild("root_front_right", CubeListBuilder.create()
                .texOffs(244, 24).addBox(-4.0F, 0.0F, -1.0F, 4, 1, 2),
                PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.0F, 0.0F, -0.47123889803846897F));
        rotation_point_front_left.addOrReplaceChild("root_front_left", CubeListBuilder.create()
                .texOffs(231, 24).addBox(0.0F, 0.0F, -1.0F, 4, 1, 2),
                PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.47123889803846897F));
        rotation_point_back_right.addOrReplaceChild("root_back_right", CubeListBuilder.create()
                .texOffs(244, 28).addBox(-4.0F, 0.0F, -1.0F, 4, 1, 2),
                PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.0F, 0.0F, -0.47123889803846897F));
        rotation_point_back_left.addOrReplaceChild("root_back_left", CubeListBuilder.create()
                .texOffs(231, 28).addBox(0.0F, 0.0F, -1.0F, 4, 1, 2),
                PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.47123889803846897F));

        // Main leaves
        PartDefinition leaf_front = base.addOrReplaceChild("leaf_front", CubeListBuilder.create()
                .texOffs(232, 46).addBox(-2.5F, -0.5F, -7.0F, 5, 1, 7),
                PartPose.offsetAndRotation(0.0F, 5.7F, -3.0F, 0.17453292519943295F, -0.13962634015954636F, 0.0F));
        leaf_front.addOrReplaceChild("leaf_front_top", CubeListBuilder.create()
                .texOffs(182, 46).addBox(-2.5F, -1.1F, -7.0F, 5, 1, 7),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.17453292519943295F, 0.2792526803190927F, 0.0F));

        PartDefinition leaf_back = base.addOrReplaceChild("leaf_back", CubeListBuilder.create()
                .texOffs(207, 46).addBox(-2.5F, -0.5F, 0.0F, 5, 1, 7),
                PartPose.offsetAndRotation(0.0F, 5.7F, 3.0F, -0.17453292519943295F, -0.13962634015954636F, 0.0F));
        leaf_back.addOrReplaceChild("leaf_back_top", CubeListBuilder.create()
                .texOffs(157, 46).addBox(-2.5F, -1.1F, 0.0F, 5, 1, 7),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.17453292519943295F, 0.2792526803190927F, 0.0F));

        PartDefinition leaf_left = base.addOrReplaceChild("leaf_left", CubeListBuilder.create()
                .texOffs(207, 55).addBox(-7.0F, -0.5F, -2.5F, 7, 1, 5),
                PartPose.offsetAndRotation(-3.0F, 5.7F, 0.0F, 0.0F, -0.13962634015954636F, -0.17453292519943295F));
        leaf_left.addOrReplaceChild("leaf_left_top", CubeListBuilder.create()
                .texOffs(157, 55).addBox(-7.0F, -1.1F, -2.5F, 7, 1, 5),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2792526803190927F, 0.17453292519943295F));

        PartDefinition leaf_right = base.addOrReplaceChild("leaf_right", CubeListBuilder.create()
                .texOffs(232, 55).addBox(0.0F, -0.5F, -2.5F, 7, 1, 5),
                PartPose.offsetAndRotation(3.0F, 5.7F, 0.0F, 0.0F, -0.13962634015954636F, 0.17453292519943295F));
        leaf_right.addOrReplaceChild("leaf_right_top", CubeListBuilder.create()
                .texOffs(182, 55).addBox(0.0F, -1.1F, -2.5F, 7, 1, 5),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2792526803190927F, -0.17453292519943295F));

        // Corner leaves
        PartDefinition leaf_front_right = rotation_point_front_right.addOrReplaceChild("leaf_front_right", CubeListBuilder.create()
                .texOffs(232, 32).addBox(-7.0F, 0.0F, -2.5F, 7, 1, 5),
                PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, -0.13962634015954636F, -0.17453292519943295F));
        leaf_front_right.addOrReplaceChild("leaf_front_right_top", CubeListBuilder.create()
                .texOffs(182, 32).addBox(-7.0F, 0.0F, -2.5F, 7, 1, 5),
                PartPose.offsetAndRotation(1.0F, -0.8F, 0.0F, 0.0F, 0.2792526803190927F, 0.08726646259971647F));

        PartDefinition leaf_front_left = rotation_point_front_left.addOrReplaceChild("leaf_front_left", CubeListBuilder.create()
                .texOffs(207, 32).addBox(0.0F, 0.0F, -2.5F, 7, 1, 5),
                PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, -0.13962634015954636F, 0.17453292519943295F));
        leaf_front_left.addOrReplaceChild("leaf_front_left_top", CubeListBuilder.create()
                .texOffs(157, 32).addBox(0.0F, 0.0F, -2.5F, 7, 1, 5),
                PartPose.offsetAndRotation(-1.0F, -0.8F, 0.0F, 0.0F, 0.2792526803190927F, -0.08726646259971647F));

        PartDefinition leaf_back_right = rotation_point_back_right.addOrReplaceChild("leaf_back_right", CubeListBuilder.create()
                .texOffs(232, 39).addBox(-7.0F, 0.0F, -2.5F, 7, 1, 5),
                PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, -0.13962634015954636F, -0.17453292519943295F));
        leaf_back_right.addOrReplaceChild("leaf_back_right_top", CubeListBuilder.create()
                .texOffs(182, 39).addBox(-7.0F, 0.0F, -2.5F, 7, 1, 5),
                PartPose.offsetAndRotation(1.0F, -0.8F, 0.0F, 0.0F, 0.2792526803190927F, 0.08726646259971647F));

        PartDefinition leaft_back_left = rotation_point_back_left.addOrReplaceChild("leaft_back_left", CubeListBuilder.create()
                .texOffs(207, 39).addBox(0.0F, 0.0F, -2.5F, 7, 1, 5),
                PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, -0.13962634015954636F, 0.17453292519943295F));
        leaft_back_left.addOrReplaceChild("leaft_back_left_top", CubeListBuilder.create()
                .texOffs(157, 39).addBox(0.0F, 0.0F, -2.5F, 7, 1, 5),
                PartPose.offsetAndRotation(-1.0F, -0.8F, 0.0F, 0.0F, 0.2792526803190927F, -0.08726646259971647F));

        // Top bases
        PartDefinition top_front_base = base_top.addOrReplaceChild("top_front_base", CubeListBuilder.create()
                .texOffs(125, 126).addBox(-2.0F, -1.0F, -3.0F, 4, 1, 1),
                PartPose.offset(0.0F, 5.0F, 0.0F));
        PartDefinition top_back_base = base_top.addOrReplaceChild("top_back_base", CubeListBuilder.create()
                .texOffs(91, 126).addBox(-2.0F, -1.0F, -3.0F, 4, 1, 1),
                PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, 0.0F, 3.141592653589793F, 0.0F));
        PartDefinition top_left_base = base_top.addOrReplaceChild("top_left_base", CubeListBuilder.create()
                .texOffs(57, 126).addBox(-2.0F, -1.0F, -3.0F, 4, 1, 1),
                PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, 0.0F, -1.5707963267948966F, 0.0F));
        PartDefinition top_right_base = base_top.addOrReplaceChild("top_right_base", CubeListBuilder.create()
                .texOffs(23, 126).addBox(-2.0F, -1.0F, -3.0F, 4, 1, 1),
                PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, 0.0F, 1.5707963267948966F, 0.0F));

        // Front head
        PartDefinition top_front_1 = top_front_base.addOrReplaceChild("top_front_1", CubeListBuilder.create()
                .texOffs(124, 119).addBox(-2.0F, -4.0F, -2.0F, 4, 4, 2),
                PartPose.offsetAndRotation(0.0F, -1.0F, -2.0F, 0.7853981633974483F, 0.0F, 0.0F));
        top_front_1.addOrReplaceChild("top_front_1_right", CubeListBuilder.create()
                .texOffs(137, 119).addBox(0.0F, -4.0F, -2.0F, 2, 4, 2),
                PartPose.offsetAndRotation(-2.0F, 0.0F, 0.01F, 0.0F, 0.0F, -0.40142572795869574F));
        top_front_1.addOrReplaceChild("top_front_1_left", CubeListBuilder.create()
                .texOffs(115, 119).addBox(-2.0F, -4.0F, -2.0F, 2, 4, 2),
                PartPose.offsetAndRotation(2.0F, 0.0F, 0.01F, 0.0F, 0.0F, 0.40142572795869574F));
        top_front_1.addOrReplaceChild("tooth_front_1", CubeListBuilder.create()
                .texOffs(15, 0).addBox(-0.5F, -1.4F, -1.0F, 1, 2, 1),
                PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, -1.1344640137963142F, 0.0F, 0.0F));

        PartDefinition top_front_2 = top_front_1.addOrReplaceChild("top_front_2", CubeListBuilder.create()
                .texOffs(121, 110).addBox(-3.5F, -6.0F, 0.0F, 7, 6, 2),
                PartPose.offsetAndRotation(0.0F, -4.0F, -2.0F, -0.5235987755982988F, 0.0F, 0.0F));
        top_front_2.addOrReplaceChild("tooth_front_2", CubeListBuilder.create()
                .texOffs(15, 5).addBox(-0.5F, -2.2F, -1.0F, 1, 3, 1),
                PartPose.offsetAndRotation(0.0F, -2.0F, 2.0F, -0.9599310885968813F, 0.0F, 0.0F));

        PartDefinition top_front_3 = top_front_2.addOrReplaceChild("top_front_3", CubeListBuilder.create()
                .texOffs(124, 102).addBox(-2.0F, -5.0F, 0.0F, 4, 5, 2),
                PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, -0.6108652381980153F, 0.0F, 0.0F));
        top_front_3.addOrReplaceChild("top_front_3_right", CubeListBuilder.create()
                .texOffs(137, 102).addBox(-2.0F, 0.0F, 0.0F, 2, 5, 2),
                PartPose.offsetAndRotation(2.0F, -5.0F, 0.01F, 0.0F, 0.0F, -0.296705972839036F));
        top_front_3.addOrReplaceChild("top_front_3_left", CubeListBuilder.create()
                .texOffs(115, 102).addBox(0.0F, 0.0F, 0.0F, 2, 5, 2),
                PartPose.offsetAndRotation(-2.0F, -5.0F, 0.01F, 0.0F, 0.0F, 0.296705972839036F));
        top_front_3.addOrReplaceChild("tooth_front_3", CubeListBuilder.create()
                .texOffs(15, 10).addBox(-0.5F, -2.6F, -1.0F, 1, 3, 1),
                PartPose.offsetAndRotation(0.0F, -1.0F, 2.0F, -0.5235987755982988F, 0.0F, 0.0F));

        PartDefinition top_front_4 = top_front_3.addOrReplaceChild("top_front_4", CubeListBuilder.create()
                .texOffs(126, 99).addBox(-1.5F, -1.0F, -0.5F, 3, 1, 1),
                PartPose.offsetAndRotation(0.0F, -4.7F, 1.0F, -0.3141592653589793F, 0.0F, 0.0F));
        top_front_4.addOrReplaceChild("top_front_4_left", CubeListBuilder.create()
                .texOffs(121, 97).addBox(-1.0F, -3.0F, -0.5F, 1, 3, 1),
                PartPose.offsetAndRotation(1.5F, -1.0F, 0.02F, 0.0F, 0.0F, -0.3490658503988659F));
        top_front_4.addOrReplaceChild("top_front_4_right", CubeListBuilder.create()
                .texOffs(135, 97).addBox(0.0F, -3.0F, -0.5F, 1, 3, 1),
                PartPose.offsetAndRotation(-1.5F, -1.0F, 0.01F, 0.0F, 0.0F, 0.3490658503988659F));
        top_front_4.addOrReplaceChild("top_front_4_top", CubeListBuilder.create()
                .texOffs(127, 93).addBox(-0.5F, -3.0F, -1.0F, 1, 3, 2),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.17453292519943295F, 0.0F, 0.0F));

        // Back head (similar structure)
        PartDefinition top_back_1 = top_back_base.addOrReplaceChild("top_back_1", CubeListBuilder.create()
                .texOffs(90, 119).addBox(-2.0F, -4.0F, -2.0F, 4, 4, 2),
                PartPose.offsetAndRotation(0.0F, -1.0F, -2.0F, 0.7853981633974483F, 0.0F, 0.0F));
        top_back_1.addOrReplaceChild("top_back_1_right", CubeListBuilder.create()
                .texOffs(103, 119).addBox(0.0F, -4.0F, -2.0F, 2, 4, 2),
                PartPose.offsetAndRotation(-2.0F, 0.0F, 0.01F, 0.0F, 0.0F, -0.40142572795869574F));
        top_back_1.addOrReplaceChild("top_back_1_left", CubeListBuilder.create()
                .texOffs(81, 119).addBox(-2.0F, -4.0F, -2.0F, 2, 4, 2),
                PartPose.offsetAndRotation(2.0F, 0.0F, 0.01F, 0.0F, 0.0F, 0.40142572795869574F));
        top_back_1.addOrReplaceChild("tooth_back_1", CubeListBuilder.create()
                .texOffs(10, 0).addBox(-0.5F, -1.4F, -1.0F, 1, 2, 1),
                PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, -1.1344640137963142F, 0.0F, 0.0F));

        PartDefinition top_back_2 = top_back_1.addOrReplaceChild("top_back_2", CubeListBuilder.create()
                .texOffs(87, 110).addBox(-3.5F, -6.0F, 0.0F, 7, 6, 2),
                PartPose.offsetAndRotation(0.0F, -4.0F, -2.0F, -0.5235987755982988F, 0.0F, 0.0F));
        top_back_2.addOrReplaceChild("tooth_back_2", CubeListBuilder.create()
                .texOffs(10, 5).addBox(-0.5F, -2.2F, -1.0F, 1, 3, 1),
                PartPose.offsetAndRotation(0.0F, -2.0F, 2.0F, -0.9599310885968813F, 0.0F, 0.0F));

        PartDefinition top_back_3 = top_back_2.addOrReplaceChild("top_back_3", CubeListBuilder.create()
                .texOffs(90, 102).addBox(-2.0F, -5.0F, 0.0F, 4, 5, 2),
                PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, -0.6108652381980153F, 0.0F, 0.0F));
        top_back_3.addOrReplaceChild("top_back_3_right", CubeListBuilder.create()
                .texOffs(103, 102).addBox(-2.0F, 0.0F, 0.0F, 2, 5, 2),
                PartPose.offsetAndRotation(2.0F, -5.0F, 0.01F, 0.0F, 0.0F, -0.296705972839036F));
        top_back_3.addOrReplaceChild("top_back_3_left", CubeListBuilder.create()
                .texOffs(81, 102).addBox(0.0F, 0.0F, 0.0F, 2, 5, 2),
                PartPose.offsetAndRotation(-2.0F, -5.0F, 0.01F, 0.0F, 0.0F, 0.296705972839036F));
        top_back_3.addOrReplaceChild("tooth_back_3", CubeListBuilder.create()
                .texOffs(10, 10).addBox(-0.5F, -2.6F, -1.0F, 1, 3, 1),
                PartPose.offsetAndRotation(0.0F, -1.0F, 2.0F, -0.5235987755982988F, 0.0F, 0.0F));

        PartDefinition top_back_4 = top_back_3.addOrReplaceChild("top_back_4", CubeListBuilder.create()
                .texOffs(92, 99).addBox(-1.5F, -1.0F, -0.5F, 3, 1, 1),
                PartPose.offsetAndRotation(0.0F, -4.7F, 1.0F, -0.3141592653589793F, 0.0F, 0.0F));
        top_back_4.addOrReplaceChild("top_back_4_left", CubeListBuilder.create()
                .texOffs(87, 97).addBox(-1.0F, -3.0F, -0.5F, 1, 3, 1),
                PartPose.offsetAndRotation(1.5F, -1.0F, 0.02F, 0.0F, 0.0F, -0.3490658503988659F));
        top_back_4.addOrReplaceChild("top_back_4_right", CubeListBuilder.create()
                .texOffs(101, 97).addBox(0.0F, -3.0F, -0.5F, 1, 3, 1),
                PartPose.offsetAndRotation(-1.5F, -1.0F, 0.01F, 0.0F, 0.0F, 0.3490658503988659F));
        top_back_4.addOrReplaceChild("top_back_4_top", CubeListBuilder.create()
                .texOffs(93, 93).addBox(-0.5F, -3.0F, -1.0F, 1, 3, 2),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.17453292519943295F, 0.0F, 0.0F));

        // Left head
        PartDefinition top_left_1 = top_left_base.addOrReplaceChild("top_left_1", CubeListBuilder.create()
                .texOffs(56, 119).addBox(-2.0F, -4.0F, -2.0F, 4, 4, 2),
                PartPose.offsetAndRotation(0.0F, -1.0F, -2.0F, 0.7853981633974483F, 0.0F, 0.0F));
        top_left_1.addOrReplaceChild("top_left_1_right", CubeListBuilder.create()
                .texOffs(69, 119).addBox(0.0F, -4.0F, -2.0F, 2, 4, 2),
                PartPose.offsetAndRotation(-2.0F, 0.0F, 0.01F, 0.0F, 0.0F, -0.40142572795869574F));
        top_left_1.addOrReplaceChild("top_left_1_left", CubeListBuilder.create()
                .texOffs(47, 119).addBox(-2.0F, -4.0F, -2.0F, 2, 4, 2),
                PartPose.offsetAndRotation(2.0F, 0.0F, 0.01F, 0.0F, 0.0F, 0.40142572795869574F));
        top_left_1.addOrReplaceChild("tooth_left_1", CubeListBuilder.create()
                .texOffs(5, 0).addBox(-0.5F, -1.4F, -1.0F, 1, 2, 1),
                PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, -1.1344640137963142F, 0.0F, 0.0F));

        PartDefinition top_left_2 = top_left_1.addOrReplaceChild("top_left_2", CubeListBuilder.create()
                .texOffs(53, 110).addBox(-3.5F, -6.0F, 0.0F, 7, 6, 2),
                PartPose.offsetAndRotation(0.0F, -4.0F, -2.0F, -0.5235987755982988F, 0.0F, 0.0F));
        top_left_2.addOrReplaceChild("tooth_left_2", CubeListBuilder.create()
                .texOffs(5, 5).addBox(-0.5F, -2.2F, -1.0F, 1, 3, 1),
                PartPose.offsetAndRotation(0.0F, -2.0F, 2.0F, -0.9599310885968813F, 0.0F, 0.0F));

        PartDefinition top_left_3 = top_left_2.addOrReplaceChild("top_left_3", CubeListBuilder.create()
                .texOffs(56, 102).addBox(-2.0F, -5.0F, 0.0F, 4, 5, 2),
                PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, -0.6108652381980153F, 0.0F, 0.0F));
        top_left_3.addOrReplaceChild("top_left_3_right", CubeListBuilder.create()
                .texOffs(69, 102).addBox(-2.0F, 0.0F, 0.0F, 2, 5, 2),
                PartPose.offsetAndRotation(2.0F, -5.0F, 0.01F, 0.0F, 0.0F, -0.296705972839036F));
        top_left_3.addOrReplaceChild("top_left_3_left", CubeListBuilder.create()
                .texOffs(47, 102).addBox(0.0F, 0.0F, 0.0F, 2, 5, 2),
                PartPose.offsetAndRotation(-2.0F, -5.0F, 0.01F, 0.0F, 0.0F, 0.296705972839036F));
        top_left_3.addOrReplaceChild("tooth_left_3", CubeListBuilder.create()
                .texOffs(5, 10).addBox(-0.5F, -2.6F, -1.0F, 1, 3, 1),
                PartPose.offsetAndRotation(0.0F, -1.0F, 2.0F, -0.5235987755982988F, 0.0F, 0.0F));

        PartDefinition top_left_4 = top_left_3.addOrReplaceChild("top_left_4", CubeListBuilder.create()
                .texOffs(58, 99).addBox(-1.5F, -1.0F, -0.5F, 3, 1, 1),
                PartPose.offsetAndRotation(0.0F, -4.7F, 1.0F, -0.3141592653589793F, 0.0F, 0.0F));
        top_left_4.addOrReplaceChild("top_left_4_left", CubeListBuilder.create()
                .texOffs(53, 97).addBox(-1.0F, -3.0F, -0.5F, 1, 3, 1),
                PartPose.offsetAndRotation(1.5F, -1.0F, 0.02F, 0.0F, 0.0F, -0.3490658503988659F));
        top_left_4.addOrReplaceChild("top_left_4_right", CubeListBuilder.create()
                .texOffs(67, 97).addBox(0.0F, -3.0F, -0.5F, 1, 3, 1),
                PartPose.offsetAndRotation(-1.5F, -1.0F, 0.01F, 0.0F, 0.0F, 0.3490658503988659F));
        top_left_4.addOrReplaceChild("top_left_4_top", CubeListBuilder.create()
                .texOffs(59, 93).addBox(-0.5F, -3.0F, -1.0F, 1, 3, 2),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.17453292519943295F, 0.0F, 0.0F));

        // Right head
        PartDefinition top_right_1 = top_right_base.addOrReplaceChild("top_right_1", CubeListBuilder.create()
                .texOffs(22, 119).addBox(-2.0F, -4.0F, -2.0F, 4, 4, 2),
                PartPose.offsetAndRotation(0.0F, -1.0F, -2.0F, 0.7853981633974483F, 0.0F, 0.0F));
        top_right_1.addOrReplaceChild("top_right_1_right", CubeListBuilder.create()
                .texOffs(35, 119).addBox(0.0F, -4.0F, -2.0F, 2, 4, 2),
                PartPose.offsetAndRotation(-2.0F, 0.0F, 0.01F, 0.0F, 0.0F, -0.40142572795869574F));
        top_right_1.addOrReplaceChild("top_right_1_left", CubeListBuilder.create()
                .texOffs(13, 119).addBox(-2.0F, -4.0F, -2.0F, 2, 4, 2),
                PartPose.offsetAndRotation(2.0F, 0.0F, 0.01F, 0.0F, 0.0F, 0.40142572795869574F));
        top_right_1.addOrReplaceChild("tooth_right_1", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-0.5F, -1.4F, -1.0F, 1, 2, 1),
                PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, -1.1344640137963142F, 0.0F, 0.0F));

        PartDefinition top_right_2 = top_right_1.addOrReplaceChild("top_right_2", CubeListBuilder.create()
                .texOffs(19, 110).addBox(-3.5F, -6.0F, 0.0F, 7, 6, 2),
                PartPose.offsetAndRotation(0.0F, -4.0F, -2.0F, -0.5235987755982988F, 0.0F, 0.0F));
        top_right_2.addOrReplaceChild("tooth_right_2", CubeListBuilder.create()
                .texOffs(0, 5).addBox(-0.5F, -2.2F, -1.0F, 1, 3, 1),
                PartPose.offsetAndRotation(0.0F, -2.0F, 2.0F, -0.9599310885968813F, 0.0F, 0.0F));

        PartDefinition top_right_3 = top_right_2.addOrReplaceChild("top_right_3", CubeListBuilder.create()
                .texOffs(22, 102).addBox(-2.0F, -5.0F, 0.0F, 4, 5, 2),
                PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, -0.6108652381980153F, 0.0F, 0.0F));
        top_right_3.addOrReplaceChild("top_right_3_right", CubeListBuilder.create()
                .texOffs(35, 102).addBox(-2.0F, 0.0F, 0.0F, 2, 5, 2),
                PartPose.offsetAndRotation(2.0F, -5.0F, 0.01F, 0.0F, 0.0F, -0.296705972839036F));
        top_right_3.addOrReplaceChild("top_right_3_left", CubeListBuilder.create()
                .texOffs(13, 102).addBox(0.0F, 0.0F, 0.0F, 2, 5, 2),
                PartPose.offsetAndRotation(-2.0F, -5.0F, 0.01F, 0.0F, 0.0F, 0.296705972839036F));
        top_right_3.addOrReplaceChild("tooth_right_3", CubeListBuilder.create()
                .texOffs(0, 10).addBox(-0.5F, -2.6F, -1.0F, 1, 3, 1),
                PartPose.offsetAndRotation(0.0F, -1.0F, 2.0F, -0.5235987755982988F, 0.0F, 0.0F));

        PartDefinition top_right_4 = top_right_3.addOrReplaceChild("top_right_4", CubeListBuilder.create()
                .texOffs(24, 99).addBox(-1.5F, -1.0F, -0.5F, 3, 1, 1),
                PartPose.offsetAndRotation(0.0F, -4.7F, 1.0F, -0.3141592653589793F, 0.0F, 0.0F));
        top_right_4.addOrReplaceChild("top_right_4_left", CubeListBuilder.create()
                .texOffs(19, 97).addBox(-1.0F, -3.0F, -0.5F, 1, 3, 1),
                PartPose.offsetAndRotation(1.5F, -1.0F, 0.02F, 0.0F, 0.0F, -0.3490658503988659F));
        top_right_4.addOrReplaceChild("top_right_4_right", CubeListBuilder.create()
                .texOffs(33, 97).addBox(0.0F, -3.0F, -0.5F, 1, 3, 1),
                PartPose.offsetAndRotation(-1.5F, -1.0F, 0.01F, 0.0F, 0.0F, 0.3490658503988659F));
        top_right_4.addOrReplaceChild("top_right_4_top", CubeListBuilder.create()
                .texOffs(25, 93).addBox(-0.5F, -3.0F, -1.0F, 1, 3, 2),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.17453292519943295F, 0.0F, 0.0F));

        // Tongue (renders separately)
        PartDefinition tongue_1 = partdefinition.addOrReplaceChild("tongue_1", CubeListBuilder.create()
                .texOffs(187, 119).addBox(-0.5F, -8.0F, -0.5F, 1, 8, 1),
                PartPose.offset(0.0F, 21.0F, 0.0F));
        PartDefinition tongue_2 = tongue_1.addOrReplaceChild("tongue_2", CubeListBuilder.create()
                .texOffs(187, 109).addBox(-0.5F, -8.0F, -0.5F, 1, 8, 1),
                PartPose.offset(0.0F, -8.0F, 0.0F));
        PartDefinition tongue_3 = tongue_2.addOrReplaceChild("tongue_3", CubeListBuilder.create()
                .texOffs(187, 99).addBox(-0.5F, -8.0F, -0.5F, 1, 8, 1),
                PartPose.offset(0.0F, -8.0F, 0.0F));
        tongue_3.addOrReplaceChild("tongue_4", CubeListBuilder.create()
                .texOffs(187, 89).addBox(-0.5F, -8.0F, -0.5F, 1, 8, 1),
                PartPose.offset(0.0F, -8.0F, 0.0F));

        // Acid blob holder (not used in current implementation but kept for completeness)
        PartDefinition acid_blob_holder = partdefinition.addOrReplaceChild("acid_blob_holder", CubeListBuilder.create()
                .texOffs(232, 86).addBox(-3.0F, 1.0F, -3.0F, 6, 3, 6),
                PartPose.offset(0.0F, 16.0F, 0.0F));
        acid_blob_holder.addOrReplaceChild("acid_blob", CubeListBuilder.create()
                .texOffs(192, 96).addBox(-8.0F, -15.0F, -8.0F, 16, 16, 16),
                PartPose.offset(0.0F, 0.01F, 0.0F));

        return LayerDefinition.create(meshdefinition, 256, 128);
    }

    public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTick) {
        this.partialTicks = partialTick;
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        this.tongue_1.visible = !entity.hasTargetedEntity();

        if (entity.getAttackState() == AcidicArchvineAttackState.IDLE) {
            animateIdle(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        } else if (entity.getAttackState() == AcidicArchvineAttackState.GRABBING) {
            animateGrabbing(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        } else if (entity.getAttackState() == AcidicArchvineAttackState.CHEWING) {
            animateChewing(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        }
    }

    private void animateIdle(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float globalSpeed = 0.8F;
        float globalDegree = 0.8F;

        limbSwing = entity.tickCount;
        limbSwingAmount = 1;

        revertToDefaultBoxValues();

        float entityRotation = (float) Math.toRadians(netHeadYaw) - (float) Math.toRadians(Mth.rotLerp(this.partialTicks, entity.yBodyRotO, entity.yBodyRot));
        base.yRot = entityRotation;

        // Leaf animations
        swing(leaf_front, 0.2F * globalSpeed, 0.05F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_front, 0.2F * globalSpeed, 0.05F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaf_front_top, 0.2F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_front_top, 0.2F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaf_back, 0.2F * globalSpeed, 0.05F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_back, 0.2F * globalSpeed, 0.05F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaf_back_top, 0.2F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_back_top, 0.2F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaf_left, 0.2F * globalSpeed, 0.05F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_left, 0.2F * globalSpeed, 0.05F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaf_left_top, 0.2F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_left_top, 0.2F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaf_right, 0.2F * globalSpeed, 0.05F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_right, 0.2F * globalSpeed, 0.05F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaf_right_top, 0.2F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_right_top, 0.2F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);

        // Corner leaf animations
        swing(leaf_front_left, 0.2F * globalSpeed, 0.05F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_front_left, 0.2F * globalSpeed, 0.05F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaf_front_left_top, 0.2F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_front_left_top, 0.2F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaf_front_right, 0.2F * globalSpeed, 0.05F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_front_right, 0.2F * globalSpeed, 0.05F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaf_front_right_top, 0.2F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_front_right_top, 0.2F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaft_back_left, 0.2F * globalSpeed, 0.05F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaft_back_left, 0.2F * globalSpeed, 0.05F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaft_back_left_top, 0.2F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaft_back_left_top, 0.2F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaf_back_right, 0.2F * globalSpeed, 0.05F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_back_right, 0.2F * globalSpeed, 0.05F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaf_back_right_top, 0.2F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_back_right_top, 0.2F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);

        // Tongue animations
        swing(tongue_1, 0.1F * globalSpeed, 0.1F * globalDegree, false, -1.2F, 0.0F, limbSwing, limbSwingAmount);
        flap(tongue_1, 0.1F * globalSpeed, 0.1F * globalDegree, false, 1.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(tongue_2, 0.1F * globalSpeed, 0.1F * globalDegree, true, -1.2F, 0.0F, limbSwing, limbSwingAmount);
        flap(tongue_2, 0.1F * globalSpeed, 0.1F * globalDegree, true, 1.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(tongue_3, 0.1F * globalSpeed, 0.1F * globalDegree, true, -1.2F, 0.0F, limbSwing, limbSwingAmount);
        flap(tongue_3, 0.1F * globalSpeed, 0.1F * globalDegree, true, 1.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(tongue_4, 0.1F * globalSpeed, 0.1F * globalDegree, true, -1.2F, 0.0F, limbSwing, limbSwingAmount);
        flap(tongue_4, 0.1F * globalSpeed, 0.1F * globalDegree, true, 1.2F, 0.0F, limbSwing, limbSwingAmount);

        // Head animations
        swing(top_front_1, 0.2F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(top_front_2, 0.2F * globalSpeed, 0.1F * globalDegree, true, 1.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(top_front_3, 0.2F * globalSpeed, 0.1F * globalDegree, false, 1.0F, 0.1F, limbSwing, limbSwingAmount);
        swing(top_front_4, 0.2F * globalSpeed, 0.1F * globalDegree, true, -1.0F, 0.0F, limbSwing, limbSwingAmount);

        swing(top_back_1, 0.2F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(top_back_2, 0.2F * globalSpeed, 0.1F * globalDegree, true, 1.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(top_back_3, 0.2F * globalSpeed, 0.1F * globalDegree, false, 1.0F, 0.1F, limbSwing, limbSwingAmount);
        swing(top_back_4, 0.2F * globalSpeed, 0.1F * globalDegree, true, -1.0F, 0.0F, limbSwing, limbSwingAmount);

        swing(top_left_1, 0.2F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(top_left_2, 0.2F * globalSpeed, 0.1F * globalDegree, true, 1.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(top_left_3, 0.2F * globalSpeed, 0.1F * globalDegree, false, 1.0F, 0.1F, limbSwing, limbSwingAmount);
        swing(top_left_4, 0.2F * globalSpeed, 0.1F * globalDegree, true, -1.0F, 0.0F, limbSwing, limbSwingAmount);

        swing(top_right_1, 0.2F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(top_right_2, 0.2F * globalSpeed, 0.1F * globalDegree, true, 1.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(top_right_3, 0.2F * globalSpeed, 0.1F * globalDegree, false, 1.0F, 0.1F, limbSwing, limbSwingAmount);
        swing(top_right_4, 0.2F * globalSpeed, 0.1F * globalDegree, true, -1.0F, 0.0F, limbSwing, limbSwingAmount);
    }

    private void animateGrabbing(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float globalSpeed = 0.8F;
        float globalDegree = 0.8F;

        limbSwing = entity.tickCount;
        limbSwingAmount = 1;

        revertToDefaultBoxValues();

        float entityRotation = (float) Math.toRadians(netHeadYaw) - (float) Math.toRadians(Mth.rotLerp(this.partialTicks, entity.yBodyRotO, entity.yBodyRot));
        base.yRot = entityRotation;

        // Open heads wide
        this.top_front_1.xRot = (float) Math.toRadians(96);
        this.top_front_2.xRot = (float) Math.toRadians(-20);
        this.top_front_3.xRot = (float) Math.toRadians(-30);
        this.top_back_1.xRot = (float) Math.toRadians(96);
        this.top_back_2.xRot = (float) Math.toRadians(-20);
        this.top_back_3.xRot = (float) Math.toRadians(-30);
        this.top_left_1.xRot = (float) Math.toRadians(96);
        this.top_left_2.xRot = (float) Math.toRadians(-20);
        this.top_left_3.xRot = (float) Math.toRadians(-30);
        this.top_right_1.xRot = (float) Math.toRadians(96);
        this.top_right_2.xRot = (float) Math.toRadians(-20);
        this.top_right_3.xRot = (float) Math.toRadians(-30);

        // Head animations
        swing(top_front_1, 0.2F * globalSpeed, 0.1F * globalDegree, false, 0.0F, -0.1F, limbSwing, limbSwingAmount);
        swing(top_front_2, 0.2F * globalSpeed, 0.1F * globalDegree, true, 1.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(top_front_3, 0.2F * globalSpeed, 0.1F * globalDegree, false, 1.0F, 0.1F, limbSwing, limbSwingAmount);
        swing(top_front_4, 0.2F * globalSpeed, 0.1F * globalDegree, true, -1.0F, 0.0F, limbSwing, limbSwingAmount);

        swing(top_back_1, 0.2F * globalSpeed, 0.1F * globalDegree, false, 0.0F, -0.1F, limbSwing, limbSwingAmount);
        swing(top_back_2, 0.2F * globalSpeed, 0.1F * globalDegree, true, 1.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(top_back_3, 0.2F * globalSpeed, 0.1F * globalDegree, false, 1.0F, 0.1F, limbSwing, limbSwingAmount);
        swing(top_back_4, 0.2F * globalSpeed, 0.1F * globalDegree, true, -1.0F, 0.0F, limbSwing, limbSwingAmount);

        swing(top_left_1, 0.2F * globalSpeed, 0.1F * globalDegree, false, 0.0F, -0.1F, limbSwing, limbSwingAmount);
        swing(top_left_2, 0.2F * globalSpeed, 0.1F * globalDegree, true, 1.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(top_left_3, 0.2F * globalSpeed, 0.1F * globalDegree, false, 1.0F, 0.1F, limbSwing, limbSwingAmount);
        swing(top_left_4, 0.2F * globalSpeed, 0.1F * globalDegree, true, -1.0F, 0.0F, limbSwing, limbSwingAmount);

        swing(top_right_1, 0.2F * globalSpeed, 0.1F * globalDegree, false, 0.0F, -0.1F, limbSwing, limbSwingAmount);
        swing(top_right_2, 0.2F * globalSpeed, 0.1F * globalDegree, true, 1.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(top_right_3, 0.2F * globalSpeed, 0.1F * globalDegree, false, 1.0F, 0.1F, limbSwing, limbSwingAmount);
        swing(top_right_4, 0.2F * globalSpeed, 0.1F * globalDegree, true, -1.0F, 0.0F, limbSwing, limbSwingAmount);

        // Leaf animations (same as idle)
        animateLeaves(globalSpeed, globalDegree, limbSwing, limbSwingAmount);

        // Tongue animations
        swing(tongue_1, 0.1F * globalSpeed, 0.1F * globalDegree, false, -1.2F, 0.0F, limbSwing, limbSwingAmount);
        flap(tongue_1, 0.1F * globalSpeed, 0.1F * globalDegree, false, 1.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(tongue_2, 0.1F * globalSpeed, 0.1F * globalDegree, true, -1.2F, 0.0F, limbSwing, limbSwingAmount);
        flap(tongue_2, 0.1F * globalSpeed, 0.1F * globalDegree, true, 1.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(tongue_3, 0.1F * globalSpeed, 0.1F * globalDegree, true, -1.2F, 0.0F, limbSwing, limbSwingAmount);
        flap(tongue_3, 0.1F * globalSpeed, 0.1F * globalDegree, true, 1.2F, 0.0F, limbSwing, limbSwingAmount);
        swing(tongue_4, 0.1F * globalSpeed, 0.1F * globalDegree, true, -1.2F, 0.0F, limbSwing, limbSwingAmount);
        flap(tongue_4, 0.1F * globalSpeed, 0.1F * globalDegree, true, 1.2F, 0.0F, limbSwing, limbSwingAmount);
    }

    private void animateChewing(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float globalSpeed = 1.6F;
        float globalDegree = 0.8F;

        limbSwing = entity.tickCount;
        limbSwingAmount = 1;

        revertToDefaultBoxValues();

        // Head chewing animations
        swing(top_front_1, 0.2F * globalSpeed, 0.5F * globalDegree, false, 0.0F, 0.1F, limbSwing, limbSwingAmount);
        swing(top_front_2, 0.2F * globalSpeed, 0.3F * globalDegree, false, -1.0F, 0.1F, limbSwing, limbSwingAmount);
        swing(top_front_3, 0.2F * globalSpeed, 0.3F * globalDegree, false, -1.0F, 0.1F, limbSwing, limbSwingAmount);
        swing(top_front_4, 0.2F * globalSpeed, 0.3F * globalDegree, false, 1.0F, 0.1F, limbSwing, limbSwingAmount);

        swing(top_back_1, 0.2F * globalSpeed, 0.5F * globalDegree, false, 0.0F, 0.1F, limbSwing, limbSwingAmount);
        swing(top_back_2, 0.2F * globalSpeed, 0.3F * globalDegree, false, -1.0F, 0.1F, limbSwing, limbSwingAmount);
        swing(top_back_3, 0.2F * globalSpeed, 0.3F * globalDegree, false, -1.0F, 0.1F, limbSwing, limbSwingAmount);
        swing(top_back_4, 0.2F * globalSpeed, 0.3F * globalDegree, false, 1.0F, 0.1F, limbSwing, limbSwingAmount);

        swing(top_left_1, 0.2F * globalSpeed, 0.5F * globalDegree, false, 0.0F, 0.1F, limbSwing, limbSwingAmount);
        swing(top_left_2, 0.2F * globalSpeed, 0.3F * globalDegree, false, -1.0F, 0.1F, limbSwing, limbSwingAmount);
        swing(top_left_3, 0.2F * globalSpeed, 0.3F * globalDegree, false, -1.0F, 0.1F, limbSwing, limbSwingAmount);
        swing(top_left_4, 0.2F * globalSpeed, 0.3F * globalDegree, false, 1.0F, 0.1F, limbSwing, limbSwingAmount);

        swing(top_right_1, 0.2F * globalSpeed, 0.5F * globalDegree, false, 0.0F, 0.1F, limbSwing, limbSwingAmount);
        swing(top_right_2, 0.2F * globalSpeed, 0.3F * globalDegree, false, -1.0F, 0.1F, limbSwing, limbSwingAmount);
        swing(top_right_3, 0.2F * globalSpeed, 0.3F * globalDegree, false, -1.0F, 0.1F, limbSwing, limbSwingAmount);
        swing(top_right_4, 0.2F * globalSpeed, 0.3F * globalDegree, false, 1.0F, 0.1F, limbSwing, limbSwingAmount);

        // Leaf animations (same as idle but faster)
        animateLeaves(globalSpeed, globalDegree, limbSwing, limbSwingAmount);
    }

    private void animateLeaves(float globalSpeed, float globalDegree, float limbSwing, float limbSwingAmount) {
        swing(leaf_front, 0.2F * globalSpeed, 0.05F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_front, 0.2F * globalSpeed, 0.05F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaf_front_top, 0.2F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_front_top, 0.2F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaf_back, 0.2F * globalSpeed, 0.05F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_back, 0.2F * globalSpeed, 0.05F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaf_back_top, 0.2F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_back_top, 0.2F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaf_left, 0.2F * globalSpeed, 0.05F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_left, 0.2F * globalSpeed, 0.05F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaf_left_top, 0.2F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_left_top, 0.2F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaf_right, 0.2F * globalSpeed, 0.05F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_right, 0.2F * globalSpeed, 0.05F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaf_right_top, 0.2F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_right_top, 0.2F * globalSpeed, 0.1F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);

        swing(leaf_front_left, 0.2F * globalSpeed, 0.05F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_front_left, 0.2F * globalSpeed, 0.05F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaf_front_left_top, 0.2F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_front_left_top, 0.2F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaf_front_right, 0.2F * globalSpeed, 0.05F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_front_right, 0.2F * globalSpeed, 0.05F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaf_front_right_top, 0.2F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_front_right_top, 0.2F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaft_back_left, 0.2F * globalSpeed, 0.05F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaft_back_left, 0.2F * globalSpeed, 0.05F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaft_back_left_top, 0.2F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaft_back_left_top, 0.2F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaf_back_right, 0.2F * globalSpeed, 0.05F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_back_right, 0.2F * globalSpeed, 0.05F * globalDegree, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        swing(leaf_back_right_top, 0.2F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        shake(leaf_back_right_top, 0.2F * globalSpeed, 0.1F * globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
    }
}
