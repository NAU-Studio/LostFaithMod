package io.naustudio.lostland.gui.screen.story;

import net.minecraft.network.chat.Component;
import org.joml.Vector2f;

public class TypeWriterTextItem extends StoryTextItem {

    public TypeWriterTextItem(float tick, float duration, float outTransition,
                              Component text, Vector2f anchor, Vector2f posiiton, Vector2f pivot, int r, int g, int b) {
        super(tick, duration, 0, outTransition, text, anchor, posiiton, pivot, r, g, b);
    }

    @Override
    public void Invoke(StoryScreen screen) {
        screen.CreateTypeWriterText(this);
    }
}
