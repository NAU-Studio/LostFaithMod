package io.naustudio.lostfaith.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

// I didn't register this entity, but I can spawn it by instantiate and then only my mod can spawn this entity!!!
public class DivineFlameball extends Fireball {

    private int ExplosionPower;

    public DivineFlameball(Level level, LivingEntity owner, int power) {
        super(EntityType.FIREBALL, owner, Vec3.ZERO, level);
        ExplosionPower = power;
    }

    protected void onHit(@NotNull HitResult result) {
        super.onHit(result);
        if (!level().isClientSide) {
            level().explode(this, this.getX(), this.getY(), this.getZ(), (float)this.ExplosionPower, Level.ExplosionInteraction.NONE);
            discard();
        }
    }

    protected void onHitEntity(@NotNull EntityHitResult result) {
        super.onHitEntity(result);
        if (level() instanceof ServerLevel level) {
            Entity target = result.getEntity();
            Entity owner = getOwner();
            DamageSource dmg = damageSources().fireball(this, owner);
            target.hurt(dmg, 6);
            EnchantmentHelper.doPostAttackEffects(level, target, dmg);
        }
    }

    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putByte("ExplosionPower", (byte)ExplosionPower);
    }

    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("ExplosionPower", 99))
            ExplosionPower = tag.getByte("ExplosionPower");
    }

    protected float getInertia() {
        return 1;
    }
}
