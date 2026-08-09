package andrews.pandoras_creatures.client.renderer.state;

import andrews.pandoras_creatures.entities.bufflon.BufflonBackAttachmentType;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class BufflonRenderState extends LivingEntityRenderState {
    public boolean isSaddled;
    public boolean hasBackAttachment;
    public BufflonBackAttachmentType backAttachment = BufflonBackAttachmentType.NONE;
    public int occupiedStorageSlotCount;
    public boolean isSitting;
    public boolean isVehicle;
    public boolean isMoving;
    public int tickCount;
    public int bufflonType;
}
