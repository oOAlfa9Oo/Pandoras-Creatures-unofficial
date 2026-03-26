package andrews.pandoras_creatures.entities.bases;

import andrews.pandoras_creatures.util.animation.Animation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IAnimatedEntityTest {

    @Test
    void animateTickStartsAndResetsFinishedAnimation() {
        TestAnimatedEntity entity = new TestAnimatedEntity();
        Animation animation = new Animation(2);

        entity.setPlayingAnimation(animation);

        entity.animateTick();
        assertEquals(1, entity.getAnimationTick());
        assertSame(animation, entity.lastStartedAnimation);

        entity.animateTick();
        assertEquals(2, entity.getAnimationTickCalls());
        assertSame(animation, entity.lastEndedAnimation);
        assertSame(IAnimatedEntity.BLANK_ANIMATION, entity.getPlayingAnimation());
        assertEquals(2, entity.getAnimationTick());
    }

    @Test
    void resetAnimationUsesBlankAnimation() {
        TestAnimatedEntity entity = new TestAnimatedEntity();

        entity.resetAnimation();

        assertTrue(entity.isNoAnimationPlaying());
        assertSame(IAnimatedEntity.BLANK_ANIMATION, entity.getPlayingAnimation());
    }

    private static final class TestAnimatedEntity implements IAnimatedEntity {
        private Animation playingAnimation = BLANK_ANIMATION;
        private int animationTick;
        private int animationTickCalls;
        private Animation lastStartedAnimation;
        private Animation lastEndedAnimation;

        @Override
        public Animation[] getAnimations() {
            return new Animation[0];
        }

        @Override
        public Animation getPlayingAnimation() {
            return this.playingAnimation;
        }

        @Override
        public int getAnimationTick() {
            return this.animationTick;
        }

        @Override
        public void setAnimationTick(int animationTick) {
            this.animationTick = animationTick;
            this.animationTickCalls++;
        }

        @Override
        public void setPlayingAnimation(Animation animationToPlay) {
            this.playingAnimation = animationToPlay;
        }

        @Override
        public void onAnimationStart(Animation animation) {
            this.lastStartedAnimation = animation;
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            this.lastEndedAnimation = animation;
        }

        int getAnimationTickCalls() {
            return this.animationTickCalls;
        }
    }
}
