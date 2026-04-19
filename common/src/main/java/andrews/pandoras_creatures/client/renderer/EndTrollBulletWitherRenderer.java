package andrews.pandoras_creatures.client.renderer;

import andrews.pandoras_creatures.entities.projectiles.EndTrollBulletWitherEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.resources.Identifier;

public class EndTrollBulletWitherRenderer extends EndTrollBulletRenderer<EndTrollBulletWitherEntity> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Reference.MODID, "textures/entity/end_troll/bullets/end_troll_bullet_2.png");

    public EndTrollBulletWitherRenderer(EntityRendererProvider.Context context) {
        super(context, TEXTURE);
    }
}
