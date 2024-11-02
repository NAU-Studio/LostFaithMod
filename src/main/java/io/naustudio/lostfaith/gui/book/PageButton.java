package io.naustudio.lostfaith.gui.book;

import io.naustudio.lostfaith.LostFaithMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;

public class PageButton extends Button {

    public static final ResourceLocation LeftArrow
            = ResourceLocation.fromNamespaceAndPath(LostFaithMod.MODID, "book/left");
    public static final ResourceLocation LeftArrowHover
            = ResourceLocation.fromNamespaceAndPath(LostFaithMod.MODID, "book/left_hover");
    public static final ResourceLocation RightArrow
            = ResourceLocation.fromNamespaceAndPath(LostFaithMod.MODID, "book/right");
    public static final ResourceLocation RightArrowHover
            = ResourceLocation.fromNamespaceAndPath(LostFaithMod.MODID, "book/right_hover");

    private final PageButtonDirection Direction;

    public PageButton(int x, int y, PageButtonDirection direction, Button.OnPress onPress) {
        super(x, y, 23, 13, CommonComponents.EMPTY, onPress, DEFAULT_NARRATION);
        Direction = direction;
    }

    public void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        ResourceLocation img = Direction == PageButtonDirection.Left
                ? (isHovered ? LeftArrowHover : LeftArrow)
                : (isHovered ? RightArrowHover : RightArrow);

        graphics.blitSprite(img, this.getX(), this.getY(), 24, 16);
    }

    public void playDownSound(SoundManager handler) {
        handler.play(SimpleSoundInstance.forUI(SoundEvents.BOOK_PAGE_TURN, 1.0F));
    }

    public enum PageButtonDirection {
        Left,
        Right
    }
}
