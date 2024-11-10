package io.naustudio.lostfaith.entity.turtle.royal;

import io.naustudio.lostfaith.LostFaithMod;
import io.naustudio.lostfaith.entity.turtle.ZombieBasedTurtle;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EntityRoyalTurtleGuard extends ZombieBasedTurtle {

    private static final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(LostFaithMod.MODID, "textures/entity/royal_turtle_guard.png");

    private static final ItemStack Weapon = new ItemStack(Items.IRON_SWORD);

    public EntityRoyalTurtleGuard(EntityType<EntityRoyalTurtleGuard> type, Level level) {
        super(type, level);
        setItemSlot(EquipmentSlot.MAINHAND, Weapon);
    }

    @Override
    public ResourceLocation Texture() {
        return texture;
    }
}
