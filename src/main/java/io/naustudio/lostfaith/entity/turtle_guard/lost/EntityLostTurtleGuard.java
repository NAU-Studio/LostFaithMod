package io.naustudio.lostfaith.entity.turtle_guard.lost;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

public class EntityLostTurtleGuard extends Zombie {

    public EntityLostTurtleGuard(EntityType<EntityLostTurtleGuard> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder CreateAttributes() {
        return createAttributes().add(Attributes.MOVEMENT_SPEED, 0.3f);
    }

    @Override
    protected boolean isSunSensitive() {
        return false;
    }

    @Override
    public void checkDespawn() {
        super.checkDespawn();
    }
}
