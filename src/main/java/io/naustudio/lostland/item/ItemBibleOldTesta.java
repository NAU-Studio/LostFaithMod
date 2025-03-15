package io.naustudio.lostland.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemBibleOldTesta extends ShieldItem {

    public ItemBibleOldTesta(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack item, @NotNull TooltipContext context,
                                @NotNull List<Component> content, @NotNull TooltipFlag flags) {
        super.appendHoverText(item, context, content, flags);
        content.add(Component.translatable("item.lostland.bible_old_testa.description.line1"));
        content.add(Component.translatable("item.lostland.bible_old_testa.description.line2"));
    }
}
