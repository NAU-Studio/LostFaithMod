package io.naustudio.lostfaith.gui.tween;

import net.minecraft.util.Mth;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class FloatTween extends Tween<Float> {

    public FloatTween(Supplier<Float> getter, Consumer<Float> setter, Float end, float duration) {
        super(getter, setter, end, duration);
    }

    @Override
    protected Float Interpolate(Float a, Float b, float t) {
        return Mth.lerp(t, a, b);
    }
}
