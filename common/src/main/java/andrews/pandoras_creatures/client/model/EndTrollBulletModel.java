package andrews.pandoras_creatures.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.EntityRenderState;

public class EndTrollBulletModel extends EntityModel<EntityRenderState> {
    private final ModelPart renderer;

    public EndTrollBulletModel(ModelPart root) {
        super(root);
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
    public void setupAnim(EntityRenderState state) {
        this.renderer.yRot = state.ageInTicks * 0.25F;
        this.renderer.xRot = state.ageInTicks * 0.15F;
    }
}
