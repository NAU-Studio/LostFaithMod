package io.naustudio.lostfaith.gui.screen.story;

import com.mojang.blaze3d.systems.RenderSystem;
import io.naustudio.lostfaith.gui.tween.LinearFloatTween;
import io.naustudio.lostfaith.gui.tween.Tween;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FastColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.common.NeoForge;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class StoryScreen extends Screen {

    public List<StoryData> Data;

    private float ShowTransition;

    public StoryScreen(List<StoryData> data, float showTransition) {
        super(Component.empty());
        ShowTransition = showTransition;
        _currentTween = new LinearFloatTween(() -> (float)_backgroundAlpha, x -> _backgroundAlpha = Math.round(x),
                255f, ShowTransition);
        Data = new ArrayList<>(data); // Copy
    }

    private int _backgroundAlpha;

    private Tween<?> _currentTween;

    private List<StoryText> _texts = new ArrayList<>();

    protected void FinalizeText(StoryText text) {
        _removingText.add(text);
        _texts.remove(text);
    }

    @SuppressWarnings({"removal", "UnstableApiUsage"})
    @Override
    public void renderBackground(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        if (_backgroundAlpha <= 0) return;

        RenderSystem.enableBlend();
        graphics.fill(0, 0, graphics.guiWidth(), graphics.guiHeight(),
                FastColor.ARGB32.color(_backgroundAlpha, 0, 0 ,0));
        RenderSystem.disableBlend();

        NeoForge.EVENT_BUS.post(new ScreenEvent.BackgroundRendered(this, graphics));
    }

    private float _time;
    private boolean _closing;

    private List<StoryData> _removingData = new ArrayList<>();
    private List<StoryText> _removingText = new ArrayList<>();

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        _currentTween.Update(partialTick);
        if (Tween.IsDead(_currentTween)) {
            if (_closing) {
                minecraft.popGuiLayer();
                return;
            }
            else
                _time += partialTick;
        }

        for (var i : Data) {
            if (_time >= i.ShowTick) {
                StoryText text = new StoryText(i, this);
                addRenderableOnly(text);
                _texts.add(text);
                _removingData.add(i);
            }
        }
        if (!_removingData.isEmpty()) {
            for (var i : _removingData)
                Data.remove(i);
            _removingData.clear();
        }
        if (!_removingText.isEmpty()) {
            for (var i : _removingText)
                removeWidget(i);
            _removingText.clear();
        }
        if (Data.isEmpty() && _texts.isEmpty()) {
            _currentTween = new LinearFloatTween(() -> (float)_backgroundAlpha, x -> _backgroundAlpha = Math.round(x),
                    0f, ShowTransition);
            _closing = true;
        }

        for (var i : renderables)
            i.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public void onClose() {
        // You can't close the screen!
    }
}
