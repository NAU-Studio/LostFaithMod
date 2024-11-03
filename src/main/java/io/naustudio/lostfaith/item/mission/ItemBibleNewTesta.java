package io.naustudio.lostfaith.item.mission;

import io.naustudio.lostfaith.component.LFComponents;
import io.naustudio.lostfaith.component.mission.BibleMetaData;
import io.naustudio.lostfaith.gui.book.BibleNewTestaScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
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

        BibleMetaData.Content data = new BibleMetaData.Content(item.get(LFComponents.BibleMetaDataType));

        if (data.Finished)
            content.add(Component.translatable("item.lostfaith.bible_new_testa.description.line1_done"));
        else
            content.add(Component.translatable("item.lostfaith.bible_new_testa.description.line1_tobecontinued"));
        content.add(Component.translatable("item.lostfaith.bible_new_testa.description.line2"));
        Component line3 = data.Owner == null
                ? Component.translatable("item.lostfaith.bible_new_testa.description.line3_unsigned")
                : GetAuthorText(item);
                content.add(line3);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack item = player.getItemInHand(usedHand);
        if (!item.has(LFComponents.BibleMetaDataType.get()))
            item.set(LFComponents.BibleMetaDataType.get(), new BibleMetaData(player.getName(), false));
        if (!item.has(LFComponents.BibleProgressType))
            item.set(LFComponents.BibleProgressType.get(), 0);
        if (BibleNewTestaScreen.Current == null)
            Minecraft.getInstance().setScreen(new BibleNewTestaScreen(item));
        return InteractionResultHolder.success(item);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        if (!stack.has(LFComponents.BibleProgressType))
            return;

        int progress = stack.get(LFComponents.BibleProgressType);
        if (Missions.GetCurrentEventMission(progress) instanceof StoryMission)
            stack.set(LFComponents.BibleProgressType, progress + 1);
    }

    public Component GetAuthorText(ItemStack item) {
        return Component.translatable("item.lostfaith.bible_new_testa.description.line3",
                item.get(LFComponents.BibleMetaDataType).owner());
    }
}
