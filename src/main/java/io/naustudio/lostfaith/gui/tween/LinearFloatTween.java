package io.naustudio.lostfaith.gui.tween;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class LinearFloatTween extends FloatTween {

    public LinearFloatTween(Supplier<Float> getter, Consumer<Float> setter, Float end, float duration) {
        super(getter, setter, end, duration);
    }

    @Override
    protected float Get(float t) {
        return t;
    }
}
