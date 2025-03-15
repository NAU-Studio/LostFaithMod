package io.naustudio.lostland.gui;

import io.naustudio.lostland.gui.control.Control;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ControlContainer {

    private List<Control> Controls = new ArrayList<>();

    public void Update(GuiGraphics graphics, DeltaTracker delta) { }

    public void Render(GuiGraphics graphics, DeltaTracker delta) {
        Recalculate(graphics);
        Update(graphics, delta);
        for (var c : Controls) {
            c.Render(graphics);
        }
    }

    public void Recalculate(GuiGraphics graphics) {
        for (var c : Controls) {
            c.CalculateRect(graphics);
        }
    }

    public void Add(Control control) {
        Controls.add(control);
    }

    public void Remove(Control control) {
        Controls.remove(control);
    }

    public void Clear() {
        Controls.clear();
    }
}
