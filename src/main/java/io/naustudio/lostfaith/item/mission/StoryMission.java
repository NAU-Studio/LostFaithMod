package io.naustudio.lostfaith.item.mission;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class StoryMission extends Mission {

    public StoryMission(int index, Component text) {
        super(index, text, false);
    }

    @Override
    public boolean Finished(Player player) {
        return true;
    }
}
