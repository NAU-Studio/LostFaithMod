package io.naustudio.lostland.gui.screen.story;

import net.minecraft.network.chat.Component;
import org.joml.Vector2f;

public class StoryTextItem extends io.naustudio.lostland.gui.screen.story.StoryItem {

    public Component Text;
    public Vector2f Anchor;
    public Vector2f Position, Pivot;
    public int R, G, B;
    public float Duration, InTransition, OutTransition;

    public StoryTextItem(float tick, float duration, float inTransition, float outTransition, Component text,
                         Vector2f anchor, Vector2f posiiton, Vector2f pivot,
                         int r, int g, int b) {
        super(tick);
        Text = text;
        Anchor = anchor;
        Position = posiiton;
        Pivot = pivot;
        R = r;
        G = g;
        B = b;
        Duration = duration;
        InTransition = inTransition;
        OutTransition = outTransition;
    }

    @Override
    public void Invoke(StoryScreen screen) {
        screen.CreateText(this);
    }
}
