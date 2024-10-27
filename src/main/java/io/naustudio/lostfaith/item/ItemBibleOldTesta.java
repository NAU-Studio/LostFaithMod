package io.naustudio.lostfaith.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.TooltipFlag;
import java.util.List;

public class ItemBibleOldTesta extends ShieldItem {

    public ItemBibleOldTesta(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack item, TooltipContext context, List<Component> content, TooltipFlag flags) {
        super.appendHoverText(item, context, content, flags);
        content.add(Component.translatable("item.lostfaith.bible_old_testa.description.line1"));
        content.add(Component.translatable("item.lostfaith.bible_old_testa.description.line2"));
    }
}
