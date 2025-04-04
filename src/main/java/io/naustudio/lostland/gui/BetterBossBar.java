package io.naustudio.lostland.gui;

import io.naustudio.lostland.entity.INewHealthSystem;
import io.naustudio.lostland.gui.control.Fill;
import io.naustudio.lostland.gui.tween.OutCubicFloatTween;
import io.naustudio.lostland.gui.tween.Tween;
import io.naustudio.lostland.util.Color32;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Vector4f;

@OnlyIn(Dist.CLIENT)
public class BetterBossBar extends ControlContainer {

    private final Minecraft game;

    private INewHealthSystem Target;
    private float TweenHp, RecentHp, TweenShield, RecentShield;

    public BetterBossBar() {
        game = Minecraft.getInstance();

        HpBackground = new Fill(new Color32(0, 0, 0, 0));
        HpBackground.Transform = new Vector4f(0, 24, 0, -16);
        HpBackground.Anchor = new Vector4f(0.25f, 0, 0.75f, 0);

        HpTweening = new Fill(new Color32(0, 0, 0, 0));
        HpTweening.Transform = new Vector4f(0, 24, 0, -16);
        HpTweening.Anchor = new Vector4f(0.25f, 0, 0.75f, 0);

        HpForeground = new Fill(new Color32(0, 0, 0, 0));
        HpForeground.Transform = new Vector4f(0, 24, 0, -16);
        HpForeground.Anchor = new Vector4f(0.25f, 0, 0.75f, 0);
    }

    public void TrackEntity(INewHealthSystem entity) {
        InitializeBar(entity);
    }

    private Fill HpBackground, HpTweening, HpForeground, ShieldBackground, ShieldTweening, ShieldForeground;

    public void InitializeBar(INewHealthSystem entity) {
        Target = entity;

        TweenHp = RecentHp = entity.GetHealthRatio();
        TweenShield = RecentShield = entity.GetShieldRatio();

        Clear();

        HpBackground.Color = entity.GetHealthBgColor();
        HpTweening.Color = entity.GetHealthTwColor();
        HpForeground.Color = entity.GetHealthFgColor();

        Add(HpBackground);
        Add(HpTweening);
        Add(HpForeground);
    }

    public void Untrack() {
        Target = null;
        Clear();
    }

    private Tween<?> HpTween;
    private Tween<?> ShieldTween;

    @Override
    public void Update(GuiGraphics graphics, DeltaTracker delta) {
        if (Target == null)
            return;

        if (!Tween.IsDead(HpTween))
            HpTween.Update(delta.getGameTimeDeltaTicks());

        float hr = Target.GetHealthRatio();

        if (hr != RecentHp) {
            RecentHp = hr;

            if (!Tween.IsDead(HpTween))
                HpTween.Kill();

            float hpBarValue = (1 - hr) * HpBackground.Size().x;

            HpTween = new OutCubicFloatTween(() -> HpForeground.Transform.z, (x) -> HpForeground.Transform.z = x, hpBarValue, 4);
        }

        float health = Target.GetHealth();

        if (TweenHp > health) {
            TweenHp -= delta.getGameTimeDeltaTicks() * 2;
            if (TweenHp < health)
                TweenHp = health;
            HpTweening.Transform.z = (1 - TweenHp / Target.GetMaxHealth()) * HpBackground.Size().x;
        }
        if (TweenHp < health) {
            TweenHp += delta.getGameTimeDeltaTicks() * 2;
            if (TweenHp > health)
                TweenHp = health;
            HpTweening.Transform.z = (1 - TweenHp / Target.GetMaxHealth()) * HpBackground.Size().x;
        }

        Recalculate(graphics);
    }
}
