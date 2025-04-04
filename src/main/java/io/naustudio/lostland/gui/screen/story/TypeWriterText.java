package io.naustudio.lostland.gui.screen.story;

import io.naustudio.lostland.gui.tween.LinearFloatTween;
import io.naustudio.lostland.gui.tween.Tween;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FastColor;
import org.joml.Vector2f;

public class TypeWriterText extends Text {

    public int R, G, B, A = 0;

    private final float _duration;
    private final float _outTransition;
    private LinearFloatTween _tween;
    private StoryScreen _parent;

    private boolean _removed = false;
    private float _time;

    private Vector2f _anchor, _pos, _pivot;

    private static final net.minecraft.client.gui.Font Font = Minecraft.getInstance().font;

    public TypeWriterText(StoryTextItem data, StoryScreen parent) {
        super(0, 0, 0, 0, data.Text);
        _parent = parent;
        setX(Math.round(_parent.width * data.Anchor.x + data.Position.x - Font.width(getMessage()) * data.Pivot.x));
        setY(Math.round(_parent.height * data.Anchor.y + data.Position.y - Font.lineHeight * data.Pivot.y));
        _anchor = data.Anchor;
        _pos = data.Position;
        _pivot = data.Pivot;
        R = data.R;
        G = data.G;
        B = data.B;
        _duration = data.Duration;
        _outTransition = data.OutTransition;
        _tween = new LinearFloatTween(() -> (float)A, v -> A = Math.round(v), 255f, data.InTransition);
        _strLen = data.Text.getString().length();
    }

    private final int _strLen;
    private Component _cache;
    private int _cachedLen = -1;

    private static final float TickEachChar = 1.5f;

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        _time += delta;
        _tween.Update(delta);

        if (_time > _duration && !_removed) {
            _tween = new LinearFloatTween(() -> (float) A, v -> A = Math.round(v), 0f, _outTransition);
            _removed = true;
        }
        if (_removed && Tween.IsDead(_tween))
            _parent.FinalizeText(this);
        if (A < 4) return; // text that alpha is less than 4 will display as opaque

        int len = Math.min(_strLen, (int)Math.floor(_time / TickEachChar));

        if (len != _cachedLen) {
            _cachedLen = len;
            _cache = Component.empty().append(getMessage().getString(len)).withStyle(getMessage().getStyle());
            setX(Math.round(_parent.width * _anchor.x + _pos.x - Font.width(_cache) * _pivot.x));
        }

        graphics.drawString(Font, _cache
                , getX(), getY(), FastColor.ARGB32.color(A, R, G, B));
    }

    protected String Cut(Component c, int l) {
        String s = c.getString();
        return s.substring(0, Math.min(s.length(), l));
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) { }
}
