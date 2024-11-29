package io.naustudio.lostfaith.item;

import io.naustudio.lostfaith.entity.DivineFireball;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemSilverCrucifix extends Item {

    public ItemSilverCrucifix(Item.Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack item = player.getItemInHand(hand);

        Vec3 pos = player.getEyePosition();
        DivineFireball fireball = new DivineFireball(level, player, 3);
        fireball.setPos(pos);
        fireball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 2, 0);
        level.addFreshEntity(fireball);

        player.playSound(SoundEvents.FIRECHARGE_USE);

        item.hurtAndBreak(1, player, player.getEquipmentSlotForItem(item));

        return InteractionResultHolder.success(item);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack item, @NotNull TooltipContext context, @NotNull List<Component> content, @NotNull TooltipFlag flags) {
        super.appendHoverText(item, context, content, flags);
        content.add(Component.translatable("item.lostfaith.silver_crucifix.description.line1"));
        content.add(Component.translatable("item.lostfaith.silver_crucifix.description.line2"));
    }
}
