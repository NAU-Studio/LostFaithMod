package io.naustudio.lostfaith.util;

import net.minecraft.core.BlockPos;

public class MathUtils {

    public static final float Pi = 3.14159265f;

    public static float ToRad(float deg) {
        return deg / 180 * Pi;
    }

    public static BlockPos Add(BlockPos left, BlockPos right) {
        return new BlockPos(left.getX() + right.getX(), left.getY() + right.getY(), left.getZ() + right.getZ());
    }
}
