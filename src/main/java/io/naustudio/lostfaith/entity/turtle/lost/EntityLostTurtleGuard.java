package io.naustudio.lostfaith.entity.turtle.lost;

import io.naustudio.lostfaith.LostFaithMod;
import io.naustudio.lostfaith.entity.turtle.ZombieBasedTurtle;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class EntityLostTurtleGuard extends ZombieBasedTurtle {

    private static final ResourceLocation texture
            = ResourceLocation.fromNamespaceAndPath(LostFaithMod.MODID, "textures/entity/lost_turtle_guard.png");

    public EntityLostTurtleGuard(EntityType<EntityLostTurtleGuard> type, Level level) {
        super(type, level);
    }

    @Override
    public ResourceLocation Texture() {
        return texture;
    }
}
