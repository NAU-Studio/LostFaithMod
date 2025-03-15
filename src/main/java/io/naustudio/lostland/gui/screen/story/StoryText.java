package io.naustudio.lostland.gui.screen.story;

import io.naustudio.lostland.gui.tween.LinearFloatTween;
import io.naustudio.lostland.gui.tween.Tween;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.util.FastColor;

public class StoryText extends AbstractWidget {

    public int R, G, B, A = 0;

    private final float _duration;
    private final float _transition;
        private LinearFloatTween _tween;
    private StoryScreen _parent;

    private boolean _removed = false;
    private float _time;

    private static final Font Font = Minecraft.getInstance().font;

    public StoryText(StoryTextItem data, StoryScreen parent) {
        super(0, 0, 0, 0, data.Text);
        _parent = parent;
        setX(Math.round(_parent.width * data.Anchor.x + data.Position.x - Font.width(getMessage()) * data.Pivot.x));
        setY(Math.round(_parent.height * data.Anchor.y + data.Position.y - Font.lineHeight * data.Pivot.y));
        R = data.R;
        G = data.G;
        B = data.B;
        _duration = data.Duration;
        _transition = data.Transition;
        _tween = new LinearFloatTween(() -> (float)A, v -> A = Math.round(v), 255f, _transition);
        _parent = parent;
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        _time += delta;
        _tween.Update(delta);

        if (_time > _duration && !_removed) {
            _tween = new LinearFloatTween(() -> (float) A, v -> A = Math.round(v), 0f, _transition);
            _removed = true;
        }
        if (_removed && Tween.IsDead(_tween))
            _parent.FinalizeText(this);
        if (A < 4) return; // text that alpha is less than 4 will display as opaque
        graphics.drawString(Font, getMessage(), getX(), getY(), FastColor.ARGB32.color(A, R, G, B));
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) { }
}
