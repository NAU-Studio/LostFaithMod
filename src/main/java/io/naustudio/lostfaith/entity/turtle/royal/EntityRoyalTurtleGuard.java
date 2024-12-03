package io.naustudio.lostfaith.entity.turtle.royal;

import io.naustudio.lostfaith.LostFaithMod;
import io.naustudio.lostfaith.entity.turtle.ZombieBasedTurtle;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class EntityRoyalTurtleGuard extends ZombieBasedTurtle {

    private static final ResourceLocation texture
             = ResourceLocation.fromNamespaceAndPath(LostFaithMod.MODID, "textures/entity/royal_turtle_guard.png");

    public EntityRoyalTurtleGuard(EntityType<EntityRoyalTurtleGuard> type, Level level) {
        super(type, level);
    }

    @Override
    public ResourceLocation Texture() {
        return texture;
    }
}
