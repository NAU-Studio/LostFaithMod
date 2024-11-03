package io.naustudio.lostfaith.item.mission.types;

import io.naustudio.lostfaith.item.mission.VirtualItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class CollectItemMission extends MissionBase {

    protected VirtualItemStack RequiredItem;

    public CollectItemMission(String textKey, VirtualItemStack requiredItem) {
        super(textKey);
        RequiredItem = requiredItem;
    }

    @Override
    public Component getProgressText(Player player) {
        return Component.translatable("gui.lostfaith.mission.collect.progress",
                RequiredItem.Count(player.getInventory()), RequiredItem.Count);
    }

    @Override
    public boolean canContinue(Player player) {
        return RequiredItem.Equals(player.getInventory());
    }
}
