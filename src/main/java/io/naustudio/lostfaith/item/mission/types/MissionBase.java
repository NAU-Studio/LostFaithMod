package io.naustudio.lostfaith.item.mission.types;

import io.naustudio.lostfaith.item.mission.Mission;
import net.minecraft.network.chat.Component;

public abstract class MissionBase implements Mission {

    public Component Text;

    public MissionBase(String textKey) {
        Text = Component.translatable(textKey);
    }

    @Override
    public Component getText() {
        return Text;
    }
}
