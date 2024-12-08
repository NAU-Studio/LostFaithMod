package io.naustudio.lostfaith.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class MathUtils {

    public static final float Pi = 3.14159265f;

    public static float ToRad(float deg) {
        return deg / 180 * Pi;
    }

    public static BlockPos Add(BlockPos left, BlockPos right) { // Thank you, Minecraft.
        return new BlockPos(left.getX() + right.getX(), left.getY() + right.getY(), left.getZ() + right.getZ());
    }

    public static Vec3 Add(Vec3 left, Vec3 right) {
        return new Vec3(left.x + right.x, left.y + right.y, left.z + right.z);
    }

    public static Vec3 Sub(Vec3 left, Vec3 right) { // Why?
        return new Vec3(left.x - right.x, left.y - right.y, left.z - right.z);
    }

    public static BlockHitResult RaycastBlock(Level level, Entity entity, float length) {
        Vec3 eyePos = entity.getEyePosition();
        Vec3 e = eyePos.add(entity.calculateViewVector(entity.getXRot(), entity.getYRot()).scale(length));
        return level.clip(new ClipContext(eyePos, e, ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, entity));
    }
}
