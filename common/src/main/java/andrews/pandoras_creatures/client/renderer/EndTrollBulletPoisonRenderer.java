package andrews.pandoras_creatures.client.renderer;

import andrews.pandoras_creatures.entities.projectiles.EndTrollBulletPoisonEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.resources.Identifier;

public class EndTrollBulletPoisonRenderer extends EndTrollBulletRenderer<EndTrollBulletPoisonEntity> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Reference.MODID, "textures/entity/end_troll/bullets/end_troll_bullet_1.png");

    public EndTrollBulletPoisonRenderer(EntityRendererProvider.Context context) {
        super(context, TEXTURE);
    }
}
