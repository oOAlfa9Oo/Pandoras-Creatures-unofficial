package andrews.pandoras_creatures.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EndTrollBulletModel<T extends Entity> extends EntityModel<T> {
    private final ModelPart renderer;

    public EndTrollBulletModel(ModelPart root) {
        this.renderer = root.getChild("renderer");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("renderer", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-4.0F, -4.0F, -1.0F, 8, 8, 2)
                .texOffs(0, 10).addBox(-1.0F, -4.0F, -4.0F, 2, 8, 8)
                .texOffs(20, 0).addBox(-4.0F, -1.0F, -4.0F, 8, 2, 8),
                PartPose.ZERO);

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.renderer.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.renderer.xRot = headPitch * ((float) Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        this.renderer.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
