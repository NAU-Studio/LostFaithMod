package io.naustudio.lostland.item;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum LFTiers implements Tier {

    CRUCIFIX(null, 3200, 0, 9, 4, () -> Ingredient.of(Items.TOTEM_OF_UNDYING));

    private final TagKey<Block> IncorrectBlocks;
    private final int Durability;
    private final float Speed;
    private final float Damage;
    private final int EnchantmentValue;
    private final Ingredient RepairIngredient;

    LFTiers(TagKey<Block> incorrectBlocks, int durability, float speed, float damage, int enchValue, Supplier<Ingredient> repairIngred) {
        IncorrectBlocks = incorrectBlocks;
        Durability = durability;
        Speed = speed;
        Damage = damage;
        EnchantmentValue = enchValue;
        RepairIngredient = repairIngred.get();
    }

    public int getUses() {
        return Durability;
    }

    public float getSpeed() {
        return Speed;
    }

    public float getAttackDamageBonus() {
        return Damage;
    }

    public @NotNull TagKey<Block> getIncorrectBlocksForDrops() {
        return IncorrectBlocks;
    }

    public int getEnchantmentValue() {
        return EnchantmentValue;
    }

    public @NotNull Ingredient getRepairIngredient() {
        return RepairIngredient;
    }
}
