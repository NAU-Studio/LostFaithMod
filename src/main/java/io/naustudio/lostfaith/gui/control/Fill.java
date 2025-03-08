package io.naustudio.lostfaith.gui.control;

import io.naustudio.lostfaith.util.Color32;
import net.minecraft.client.gui.GuiGraphics;
import org.joml.Vector4i;

public class Fill extends Control {

    public Color32 Color;

    public Fill(Color32 color) {
        Color = color;
    }

    @SuppressWarnings("SuspiciousNameCombination")
    @Override
    public void Render(GuiGraphics g) {
        g.fill(Bound().x, Bound().y, Bound().z, Bound().w, Color.GetValue());
    }
}
