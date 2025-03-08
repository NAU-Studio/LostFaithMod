package io.naustudio.lostfaith.gui.control;

import net.minecraft.client.gui.GuiGraphics;
import org.joml.*;

import java.lang.Math;

public abstract class Control {

    // x: left, y: top, z: right, w: bottom
    public Vector4f Transform = new Vector4f();
    // x: left, y: top, z: right, w: bottom
    public Vector4f Anchor = new Vector4f();
    public Vector2f Pivot = new Vector2f();

    private Vector4i Bound;
    private Vector2f Size;

    public Control() { }

    public abstract void Render(GuiGraphics g);

    public void CalculateRect(GuiGraphics g) {
        float width = g.guiWidth();
        float height = g.guiHeight();

        float left = width * Anchor.x + Transform.x;
        float right = width * Anchor.z - Transform.z;
        float top = height * Anchor.y + Transform.y;
        float bottom = height * Anchor.w - Transform.w;

        float pWidth = right - left;
        float pHeight = bottom - top;

        Size = new Vector2f(pWidth, pHeight);

        left -= pWidth * Pivot.x;
        right -= pWidth * Pivot.x;
        top -= pHeight * Pivot.x;
        bottom -= pHeight * Pivot.x;

        Bound = new Vector4i(Math.round(left), Math.round(top), Math.round(right), Math.round(bottom));
    }

    public Vector2f Size() {
        return Size;
    }

    public Vector4i Bound() {
        return Bound;
    }
}
