package io.naustudio.lostland.gui.screen.story;

import net.minecraft.network.chat.Component;
import org.joml.Vector2f;

public class StoryData {

    public Component Text;
    public Vector2f Anchor;
    public Vector2f Position, Pivot;
    public int R, G, B;
    public float Transition;
    public float ShowTick, Duration;

    public StoryData(Component text, Vector2f anchor, Vector2f posiiton, Vector2f pivot, int r, int g, int b,
                     float showTick, float duration, float transition) {
        Text = text;
        Anchor = anchor;
        Position = posiiton;
        Pivot = pivot;
        R = r;
        G = g;
        B = b;
        ShowTick = showTick;
        Duration = duration;
        Transition = transition;
    }
}
