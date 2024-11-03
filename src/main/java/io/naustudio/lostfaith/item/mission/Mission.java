package io.naustudio.lostfaith.item.mission;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public interface Mission {

    Component getText();

    default Component getProgressText(Player player) {
        return Component.empty();
    }

    boolean canContinue(Player player);
}
