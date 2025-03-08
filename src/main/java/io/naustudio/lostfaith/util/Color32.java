package io.naustudio.lostfaith.util;

import net.minecraft.util.FastColor;

public record Color32(int r, int g, int b, int a) {
    public int GetValue() {
        return FastColor.ARGB32.color(a, r, g, b);
    }
}
