package io.naustudio.lostfaith.item.mission;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class StoryMission implements Mission {

    protected Component Text;

    public StoryMission(String textKey) {
        Text = Component.translatable(textKey);
    }

    @Override
    public Component getText() {
        return Text;
    }

    @Override
    public boolean canContinue(Player player) {
        return true;
    }
}
