package io.naustudio.lostfaith.item.mission.types;

import io.naustudio.lostfaith.item.mission.Mission;
import io.naustudio.lostfaith.item.mission.VirtualItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class CollectItemMission extends Mission {

    protected VirtualItemStack RequiredItem;

    public CollectItemMission(int index, Component text, boolean optional, VirtualItemStack requiredItem) {
        super(index, text, optional);
        RequiredItem = requiredItem;
    }

    @Override
    public Component GetProgressText(Player player) {
        return Component.translatable("gui.lostfaith.mission.collect.progress",
                RequiredItem.Count(player.getInventory()), RequiredItem.Count);
    }

    @Override
    public boolean Finished(Player player) {
        return RequiredItem.Equals(player.getInventory());
    }
}
