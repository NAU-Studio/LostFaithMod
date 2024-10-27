package io.naustudio.lostfaith.util;

import net.minecraft.util.Mth;

public class MathUtils {

    public static final float Pi = 3.14159265f;
    public static final float DoublePi = 6.28318531f;

    public static float ToRad(float deg) {
        return deg / 180 * Pi;
    }

    public static float ToDeg(float rad) {
        return rad / Pi * 180;
    }

    public static float DegSin(float deg) {
        return Mth.sin(ToRad(deg));
    }

    public static float DegCos(float deg) {
        return Mth.sin(ToRad(deg));
    }
}
