package andrews.pandoras_creatures.client.renderer.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class SeahorseRenderState extends LivingEntityRenderState {
    public int seahorseType;
    public int seahorseSize;
    public boolean isSpecialNamed;
    public boolean isEntityMovingHorizontally;
    public int tickCount;
    public boolean isAlive;
    public String customNameString = "";
}
