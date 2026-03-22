package andrews.pandoras_creatures.util.animation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnimationTest {

    @Test
    void storesConfiguredTickDuration() {
        Animation animation = new Animation(12);

        assertEquals(12, animation.getAnimationTickDuration());
    }

    @Test
    void defaultConstructorCreatesZeroTickAnimation() {
        Animation animation = new Animation();

        assertEquals(0, animation.getAnimationTickDuration());
    }
}
