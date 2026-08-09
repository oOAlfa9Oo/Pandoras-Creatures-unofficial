package andrews.pandoras_creatures.client.renderer.state;

import andrews.pandoras_creatures.util.animation.Animation;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class EndTrollRenderState extends LivingEntityRenderState {
    public Animation playingAnimation;
    public int animationTick;
    public boolean isEntityStanding;
    public boolean isEntityMovingHorizontally;
    public int tickCount;

    public boolean isAnimationPlaying(Animation animation) {
        return this.playingAnimation == animation;
    }
}
