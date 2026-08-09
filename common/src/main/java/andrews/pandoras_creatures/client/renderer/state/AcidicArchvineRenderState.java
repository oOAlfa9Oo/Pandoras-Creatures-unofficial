package andrews.pandoras_creatures.client.renderer.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.phys.Vec3;

public class AcidicArchvineRenderState extends LivingEntityRenderState {
    public int archvineType;
    public int tickCount;
    public float gameTimeWithPartialTick;
    public boolean hasTarget;
    public Vec3 targetPosition = Vec3.ZERO;
    public boolean hasTargetedEntity;
    public andrews.pandoras_creatures.entities.acidic_archvine.AcidicArchvineAttackState attackState;
    public float entityRotation;
}
