package io.naustudio.lostfaith.item.mission;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public abstract class Mission {

    public int Index;
    public Component Text;
    public boolean Optional;

    public Mission(int index, Component text, boolean optional) {
        Index = index;
        Text = text;
        Optional = optional;
    }

    public Component GetText() {
        return Text;
    }

    public Component GetProgressText(Player player) {
        return Component.empty();
    }

    public abstract boolean Finished(Player player);
    public boolean IsOptional() {
        return Optional;
    }
}
