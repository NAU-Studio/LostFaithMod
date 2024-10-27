package io.naustudio.lostfaith.item.mission;

import io.naustudio.lostfaith.LostFaithMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class ItemBibleNewTesta extends Item {

    public ItemBibleNewTesta(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack item, TooltipContext context, List<Component> content, TooltipFlag flags) {
        super.appendHoverText(item, context, content, flags);

        MetaData data = new MetaData(item.get(LostFaithMod.LFComponents.BibleMetadata));

        if (data.Finished)
            content.add(Component.translatable("item.lostfaith.bible_new_testa.description.line1_done"));
        else
            content.add(Component.translatable("item.lostfaith.bible_new_testa.description.line1_tobecontinued"));
        content.add(Component.translatable("item.lostfaith.bible_new_testa.description.line2"));
        Component line3 = data.Owner == null
                ? Component.translatable("item.lostfaith.bible_new_testa.description.line3_unsigned")
                : Component.translatable("item.lostfaith.bible_new_testa.description.line3", data.Owner);
                content.add(line3);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack item = player.getItemInHand(usedHand);
        if (item.get(LostFaithMod.LFComponents.BibleMetadata.get()) == null)
            item.set(LostFaithMod.LFComponents.BibleMetadata.get(), new MetaRecord(player.getName(), false));
        return InteractionResultHolder.success(item);
    }
}
