package io.naustudio.lostfaith.item.mission.types;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.naustudio.lostfaith.item.mission.Mission;
import io.naustudio.lostfaith.item.mission.StoryMission;
import io.naustudio.lostfaith.item.mission.VirtualItemStack;
import io.naustudio.lostfaith.util.InventoryUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class CollectItemMission extends Mission {

    public static final com.mojang.serialization.Codec<CollectItemMission> Codec = RecordCodecBuilder.create(x -> x.group(
            ComponentSerialization.CODEC.fieldOf("text").forGetter(Mission::GetText),
            com.mojang.serialization.Codec.STRING.listOf().fieldOf("next").forGetter(Mission::GetNext),
            ItemStack.CODEC.fieldOf("item").forGetter(CollectItemMission::GetRequiredItem)
    ).apply(x, CollectItemMission::new));

    private final ItemStack RequiredItem;

    public CollectItemMission(Component text, List<String> next, ItemStack requiredItem) {
        super(text, next);
        RequiredItem = requiredItem;
    }

    public ItemStack GetRequiredItem() {
        return RequiredItem;
    }

    @Override
    public Component GetProgressText(Player player) {
        return Component.translatable("gui.lostfaith.mission.collect.progress",
                InventoryUtils.Count(player.getInventory(), RequiredItem),
                RequiredItem.getCount());
    }

    @Override
    public boolean Finished(Player player) {
        return InventoryUtils.Count(player.getInventory(), RequiredItem) >= RequiredItem.getCount();
    }
}
