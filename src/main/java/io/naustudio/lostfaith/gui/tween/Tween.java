package io.naustudio.lostfaith.gui.tween;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class Tween<T> {

    protected final Supplier<T> Getter;
    protected final Consumer<T> Setter;
    protected final T Start;
    protected final T End;
    protected final float Duration;

    protected float Elapsed = 0;
    protected boolean Died;

    public Tween(Supplier<T> getter, Consumer<T> setter, T end, float duration) {
        Getter = getter;
        Setter = setter;
        Start = Getter.get();
        End = end;
        Duration = duration;

        if (Duration == 0) {
            setter.accept(end);
            Died = true;
        }
    }

    public void Update(float delta) {
        if (Died) return;
        Elapsed += delta;
        Setter.accept(Interpolate(Start, End, Get(Elapsed / Duration)));
        if (Elapsed >= Duration)
            Died = true;
    }

    public void Kill() {
        Died = true;
    }

    public static boolean IsDead(Tween<?> tween) {
        return tween == null || tween.Died;
    }

    protected abstract float Get(float t);

    protected abstract T Interpolate(T a, T b, float t);
}
