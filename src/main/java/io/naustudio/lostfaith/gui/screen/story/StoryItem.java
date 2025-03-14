package io.naustudio.lostfaith.gui.screen.story;

public abstract class StoryItem {

    public float Tick;

    public StoryItem(float tick) {
        Tick = tick;
    }

    public abstract void Invoke(StoryScreen screen);
}
