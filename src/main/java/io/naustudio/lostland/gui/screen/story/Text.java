package io.naustudio.lostland.gui.screen.story;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;

public abstract class Text extends AbstractWidget {

    public Text(int x, int y, int width, int height, Component message) {
        super(x, y, width, height, message);
    }
}
