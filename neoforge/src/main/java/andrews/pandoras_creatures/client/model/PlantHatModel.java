package andrews.pandoras_creatures.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

/**
 * PlantHatModel - Migrated from 1.16.5 (Tabula 7.1.0) to NeoForge 1.21.1
 * Custom 3D armor model for the Plant Hat helmet item.
 */
@OnlyIn(Dist.CLIENT)
public class PlantHatModel extends HumanoidModel<LivingEntity> {

    private final ModelPart hat_base;
    private final ModelPart hanging_plants;
    private final ModelPart hanging_plants_1;
    private final ModelPart hanging_plants_2;

    // Initial rotations for hanging plants (from PartPose definition) used for reset each frame
    private static final float HP_BASE_Y  = 0.7853981633974483F;  // 45 deg
    private static final float HP2_BASE_Y = 2.356194490192345F;   // 135 deg

    private boolean isArmorStand = false;

    public PlantHatModel(ModelPart root) {
        super(root);
        this.hat_base        = root.getChild("hat_base");
        this.hanging_plants  = hat_base.getChild("hanging_plants");
        this.hanging_plants_1 = hat_base.getChild("hanging_plants_1");
        this.hanging_plants_2 = hat_base.getChild("hanging_plants_2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // Required parts by HumanoidModel — left empty (not rendered by this model)
        root.addOrReplaceChild("head",      CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        root.addOrReplaceChild("hat",       CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        root.addOrReplaceChild("body",      CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        root.addOrReplaceChild("left_arm",  CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        root.addOrReplaceChild("left_leg",  CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        // ── Hat base (the main woven-straw cylinder) ───────────────────────────
        PartDefinition hat_base = root.addOrReplaceChild("hat_base",
                CubeListBuilder.create().texOffs(104, 15)
                        .addBox(-3.0F, -13.0F, -3.0F, 6, 5, 6, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.012740903539558604F));

        // ── Top plants (4 flat leaf panels on top of the hat) ─────────────────
        hat_base.addOrReplaceChild("top_plants",
                CubeListBuilder.create().texOffs(52, 0)
                        .addBox(-6.0F, 0.0F, -10.0F, 12, 0, 12, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -13.0F, -3.0F, 0.35779249665883756F, 0.0F, 0.0F));
        hat_base.addOrReplaceChild("top_plants_1",
                CubeListBuilder.create().texOffs(52, 14)
                        .addBox(-6.0F, 0.0F, -2.0F, 12, 0, 12, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -13.0F, 3.0F, -0.619591884457987F, 0.0F, 0.0F));
        hat_base.addOrReplaceChild("top_plants_2",
                CubeListBuilder.create().texOffs(68, 26)
                        .addBox(-2.0F, 0.0F, -6.0F, 12, 0, 12, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(3.0F, -13.0F, 0.0F, 0.0F, 0.0F, 0.6283185307179586F));
        hat_base.addOrReplaceChild("top_plants_3",
                CubeListBuilder.create().texOffs(92, 26)
                        .addBox(-10.0F, 0.0F, -6.0F, 12, 0, 12, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-3.0F, -13.0F, 0.0F, 0.0F, 0.0F, -0.6283185307179586F));

        // ── Wood beams (structural supports radiating outward) ─────────────────
        hat_base.addOrReplaceChild("wood_beam",
                CubeListBuilder.create().texOffs(94, 2)
                        .addBox(-0.5F, 0.0F, -10.0F, 1, 1, 10, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -9.0F, -2.8F, -0.148352986419518F, 0.0F, 0.0F));
        hat_base.addOrReplaceChild("wood_beam_1",
                CubeListBuilder.create().texOffs(82, 3)
                        .addBox(-0.5F, 0.0F, 0.0F, 1, 1, 10, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -9.0F, 3.0F, -0.2181661564992912F, 0.0F, 0.0F));
        hat_base.addOrReplaceChild("wood_beam_2",
                CubeListBuilder.create().texOffs(106, 2)
                        .addBox(0.0F, 0.0F, -0.5F, 10, 1, 1, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(3.0F, -9.0F, 0.0F, 0.0F, 0.0F, 0.2181661564992912F));
        hat_base.addOrReplaceChild("wood_beam_3",
                CubeListBuilder.create().texOffs(106, 8)
                        .addBox(-10.0F, 0.0F, -0.5F, 10, 1, 1, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-3.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.2181661564992912F));
        hat_base.addOrReplaceChild("wood_beam_4",
                CubeListBuilder.create().texOffs(108, 0)
                        .addBox(-1.0F, 0.0F, -0.5F, 9, 1, 1, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(3.0F, -9.01F, -3.0F, 0.0F, 0.7853981633974483F, 0.0F));
        hat_base.addOrReplaceChild("wood_beam_5",
                CubeListBuilder.create().texOffs(108, 10)
                        .addBox(-1.0F, 0.0F, -0.5F, 9, 1, 1, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-3.0F, -9.01F, -3.0F, 0.0F, 2.356194490192345F, 0.0F));
        hat_base.addOrReplaceChild("wood_beam_6",
                CubeListBuilder.create().texOffs(108, 4)
                        .addBox(-1.0F, 0.0F, -0.5F, 9, 1, 1, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(3.0F, -9.01F, 3.0F, 0.0F, -0.7853981633974483F, 0.08726646259971647F));
        hat_base.addOrReplaceChild("wood_beam_7",
                CubeListBuilder.create().texOffs(108, 6)
                        .addBox(-1.0F, 0.0F, -0.5F, 9, 1, 1, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-3.0F, -9.01F, 3.0F, 0.0F, -2.356194490192345F, -0.08726646259971647F));

        // ── Brim leaf panels (flat planes fanning outward from base) ───────────
        hat_base.addOrReplaceChild("plants",
                CubeListBuilder.create().texOffs(54, 54)
                        .addBox(0.0F, 0.0F, -10.0F, 8, 0, 10, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.2F, -8.8F, -3.0F, -0.1361356816555577F, 0.0F, 0.1361356816555577F));
        hat_base.addOrReplaceChild("plants_1",
                CubeListBuilder.create().texOffs(54, 32)
                        .addBox(-8.0F, 0.0F, -10.0F, 8, 0, 10, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-0.2F, -8.8F, -3.0F, -0.1361356816555577F, 0.0F, -0.1361356816555577F));
        hat_base.addOrReplaceChild("plants_2",
                CubeListBuilder.create().texOffs(54, 43)
                        .addBox(0.0F, 0.0F, 0.0F, 8, 0, 10, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.2F, -8.7F, 3.0F, -0.22689280275926282F, 0.0F, -0.08726646259971647F));
        hat_base.addOrReplaceChild("plants_3",
                CubeListBuilder.create().texOffs(71, 45)
                        .addBox(-8.0F, 0.0F, 0.0F, 8, 0, 10, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-0.2F, -8.7F, 3.0F, -0.22689280275926282F, 0.0F, 0.08726646259971647F));
        hat_base.addOrReplaceChild("plants_4",
                CubeListBuilder.create().texOffs(94, 56)
                        .addBox(0.0F, 0.0F, 0.0F, 10, 0, 8, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(3.0F, -8.8F, 0.3F, 0.06981317007977318F, 0.0F, 0.22689280275926282F));
        hat_base.addOrReplaceChild("plants_5",
                CubeListBuilder.create().texOffs(73, 56)
                        .addBox(0.0F, 0.0F, -8.0F, 10, 0, 8, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(3.0F, -8.7F, -0.3F, -0.10471975511965977F, 0.0F, 0.22689280275926282F));
        hat_base.addOrReplaceChild("plants_6",
                CubeListBuilder.create().texOffs(90, 47)
                        .addBox(-10.0F, 0.0F, 0.0F, 10, 0, 8, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-3.0F, -8.8F, 0.3F, 0.06981317007977318F, 0.0F, -0.22689280275926282F));
        hat_base.addOrReplaceChild("plants_7",
                CubeListBuilder.create().texOffs(90, 38)
                        .addBox(-10.0F, 0.0F, -8.0F, 10, 0, 8, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-3.0F, -8.7F, -0.3F, -0.10471975511965977F, 0.0F, -0.22689280275926282F));

        // ── Hanging plants (sway when the entity walks) ────────────────────────
        hat_base.addOrReplaceChild("hanging_plants",
                CubeListBuilder.create().texOffs(94, -5)
                        .addBox(0.0F, 0.0F, 0.0F, 0, 6, 5, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-8.0F, -8.4F, -8.0F, 0.0F, HP_BASE_Y, 0.0F));
        hat_base.addOrReplaceChild("hanging_plants_1",
                CubeListBuilder.create().texOffs(94, 1)
                        .addBox(0.0F, 0.0F, 0.0F, 0, 6, 5, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -7.3F, 5.3F));
        hat_base.addOrReplaceChild("hanging_plants_2",
                CubeListBuilder.create().texOffs(89, 9)
                        .addBox(0.0F, 0.0F, 0.0F, 0, 6, 6, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(4.0F, -8.0F, -4.0F, 0.0F, HP2_BASE_Y, 0.0F));

        // ── Chin strap ────────────────────────────────────────────────────────
        PartDefinition strap = hat_base.addOrReplaceChild("strap",
                CubeListBuilder.create().texOffs(108, 13)
                        .addBox(-4.5F, 0.0F, 0.0F, 9, 1, 1, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -0.5F, -3.5F, -0.3700098014227978F, 0.0F, 0.0F));
        strap.addOrReplaceChild("strap_1",
                CubeListBuilder.create().texOffs(119, 39)
                        .addBox(-1.0F, -8.0F, 0.0F, 1, 8, 1, new CubeDeformation(0.0F)),
                PartPose.offset(4.5F, 0.0F, 0.0F));
        strap.addOrReplaceChild("strap_2",
                CubeListBuilder.create().texOffs(124, 39)
                        .addBox(0.0F, -8.0F, 0.0F, 1, 8, 1, new CubeDeformation(0.0F)),
                PartPose.offset(-4.5F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 64);
    }

    /**
     * Called from the IClientItemExtensions before each render frame.
     * Copies head rotation from the already-animated original model and applies sway animation.
     */
    public void prepareForRender(LivingEntity entity, HumanoidModel<?> original) {
        this.isArmorStand = entity instanceof ArmorStand;

        // Copy head rotation from the pipeline's animated model so the hat follows the head
        this.head.xRot = original.head.xRot;
        this.head.yRot = original.head.yRot;
        this.head.zRot = original.head.zRot;

        // Reset hanging plants to their initial pose (avoids infinite accumulation)
        this.hanging_plants.yRot  = HP_BASE_Y;
        this.hanging_plants.zRot  = 0.0F;
        this.hanging_plants_1.yRot = 0.0F;
        this.hanging_plants_1.zRot = 0.0F;
        this.hanging_plants_2.yRot = HP2_BASE_Y;
        this.hanging_plants_2.zRot = 0.0F;

        // Sway the hanging plants when the entity is walking
        if (entity.getX() != entity.xOld || entity.getZ() != entity.zOld) {
            float limbSwing       = entity.walkAnimation.position();
            float limbSwingAmount = entity.walkAnimation.speed();

            this.hanging_plants.zRot  += calcRot(0.4F, 0.12F, false, 0.0F, 0.0F,   limbSwing, limbSwingAmount);
            this.hanging_plants.yRot  += calcRot(0.4F, 0.10F, false, 0.0F, 0.0F,   limbSwing, limbSwingAmount);
            this.hanging_plants_1.zRot += calcRot(0.4F, 0.12F, false, 0.0F, 0.05F, limbSwing, limbSwingAmount);
            this.hanging_plants_1.yRot += calcRot(0.4F, 0.10F, false, 0.0F, 0.0F,  limbSwing, limbSwingAmount);
            this.hanging_plants_2.zRot += calcRot(0.4F, 0.02F, false, 0.0F, -0.1F, limbSwing, limbSwingAmount);
            this.hanging_plants_2.yRot += calcRot(0.4F, 0.04F, false, 0.0F, 0.0F,  limbSwing, limbSwingAmount);
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        // Make hat_base follow the head direction
        hat_base.xRot = this.head.xRot;
        hat_base.yRot = this.head.yRot;
        hat_base.zRot = this.head.zRot;

        poseStack.pushPose();
        // Slight downward offset on armor stands so the hat sits correctly
        if (isArmorStand) {
            poseStack.translate(0.0F, 0.12F, 0.0F);
        }
        hat_base.render(poseStack, buffer, packedLight, packedOverlay, color);
        poseStack.popPose();
    }

    private float calcRot(float speed, float degree, boolean invert, float delay, float weight,
                          float limbSwing, float limbSwingAmount) {
        float rotation = (Mth.cos(limbSwing * speed + delay) * degree * limbSwingAmount) + (weight * limbSwingAmount);
        return invert ? -rotation : rotation;
    }
}
