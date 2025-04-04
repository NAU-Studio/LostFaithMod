package io.naustudio.lostland.entity.turtle.royal;

import io.naustudio.lostland.LostLand;
import io.naustudio.lostland.entity.turtle.ZombieBasedTurtle;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class EntityRoyalTurtleGuard extends ZombieBasedTurtle {

    private static final ResourceLocation texture
             = ResourceLocation.fromNamespaceAndPath(LostLand.MODID, "textures/entity/royal_turtle_guard.png");

    public EntityRoyalTurtleGuard(EntityType<EntityRoyalTurtleGuard> type, Level level) {
        super(type, level);
    }

    @Override
    public ResourceLocation Texture() {
        return texture;
    }
}
