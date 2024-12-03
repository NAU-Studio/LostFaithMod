package io.naustudio.lostfaith.entity.turtle.royal;

import io.naustudio.lostfaith.LostFaithMod;
import io.naustudio.lostfaith.entity.turtle.ZombieBasedTurtle;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class EntityKingTurtle extends ZombieBasedTurtle {

    private static final ResourceLocation texture
            = ResourceLocation.fromNamespaceAndPath(LostFaithMod.MODID, "textures/entity/king_turtle.png");

    public EntityKingTurtle(EntityType<EntityKingTurtle> type, Level level) {
        super(type, level);
    }

    @Override
    public ResourceLocation Texture() {
        return texture;
    }

    public static AttributeSupplier.Builder CreateAttributes() {
        return ZombieBasedTurtle.CreateAttributes()
                .add(Attributes.MAX_HEALTH, 300)
                .add(Attributes.FOLLOW_RANGE, 12.0)
                .add(Attributes.ATTACK_DAMAGE, 5.0);
    }
}
