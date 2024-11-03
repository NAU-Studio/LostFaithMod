package io.naustudio.lostfaith.item.mission;

import net.minecraft.network.chat.Component;

public class StoryMission implements Mission {

    protected Component Text;

    public StoryMission(String textKey) {
        Text = Component.translatable(textKey);
    }

    @Override
    public Component getText() {
        return Text;
    }
}
