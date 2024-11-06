package io.naustudio.lostfaith.entity.turtle;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class ZombieBasedTurtle extends Zombie {

    public ZombieBasedTurtle(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder CreateAttributes() {
        return createAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.3f);
    }

    @Override
    protected boolean isSunSensitive() {
        return false;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return null;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.ZOMBIE_STEP;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.TURTLE_SWIM;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }

    protected static final Fallsounds FallSound = new Fallsounds(SoundEvents.SLIME_JUMP_SMALL, SoundEvents.SLIME_JUMP);

    @Override
    public Fallsounds getFallSounds() {
        return FallSound;
    }

    @Override
    protected ItemStack getSkull() {
        return ItemStack.EMPTY;
    }

    public abstract ResourceLocation Texture();
}
