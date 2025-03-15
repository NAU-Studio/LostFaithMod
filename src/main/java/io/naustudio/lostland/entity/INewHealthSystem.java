package io.naustudio.lostland.entity;

import io.naustudio.lostland.util.Color32;

public interface INewHealthSystem {

    float GetHealth();
    float GetMaxHealth();
    float GetShield();
    float GetStageCount();

    default float GetHealthRatio() {
        return GetHealth() / GetMaxHealth();
    }

    default float GetShieldRatio() {
        return GetShield() / GetMaxHealth();
    }

    default Color32 GetHealthBgColor() {
        return new Color32(127, 24, 15, 255);
    }
    default Color32 GetHealthFgColor() {
        return new Color32(255, 79, 63, 255);
    }
    default Color32 GetHealthTwColor() {
        return new Color32(188, 46, 35, 255);
    }
    default Color32 GetShieldBgColor() {
        return new Color32(31, 95, 127, 255);
    }
    default Color32 GetShieldFgColor() {
        return new Color32(127, 212, 255, 255);
    }
    default Color32 GetShieldTwColor() {
        return new Color32(72, 151, 191, 255);
    }
}
