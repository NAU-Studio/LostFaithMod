package io.naustudio.lostland.gui.control;

import io.naustudio.lostland.util.Color32;
import net.minecraft.client.gui.GuiGraphics;

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
