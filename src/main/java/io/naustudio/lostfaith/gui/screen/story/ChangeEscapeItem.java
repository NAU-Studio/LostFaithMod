package io.naustudio.lostfaith.gui.screen.story;

public class ChangeEscapeItem extends StoryItem {

    public boolean Escape;

    public ChangeEscapeItem(float showTick, boolean escape) {
        super(showTick);
        Escape = escape;
    }

    @Override
    public void Invoke(StoryScreen screen) {
        screen.ChangeEscape(Escape);
    }
}
