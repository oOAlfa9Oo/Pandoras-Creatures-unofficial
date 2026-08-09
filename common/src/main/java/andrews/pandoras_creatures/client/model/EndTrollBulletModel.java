package andrews.pandoras_creatures.client.model;

import andrews.pandoras_creatures.client.renderer.state.EndTrollBulletRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class EndTrollBulletModel<T extends EndTrollBulletRenderState> extends EntityModel<T> {
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
    public void setupAnim(T state) {
        this.renderer.yRot = state.rotYaw * ((float) Math.PI / 180F);
        this.renderer.xRot = state.rotPitch * ((float) Math.PI / 180F);
    }
}
