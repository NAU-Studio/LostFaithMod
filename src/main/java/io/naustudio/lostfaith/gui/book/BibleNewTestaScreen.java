package io.naustudio.lostfaith.gui.book;


import com.mojang.blaze3d.vertex.PoseStack;
import io.naustudio.lostfaith.LostFaithMod;
import io.naustudio.lostfaith.component.LFComponents;
import io.naustudio.lostfaith.item.LFItems;
import io.naustudio.lostfaith.item.mission.Missions;
import io.naustudio.lostfaith.item.mission.StoryMission;
import net.minecraft.client.ComponentCollector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.StringSplitter;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.StringDecomposer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.WrittenBookItem;
import net.minecraft.world.item.component.WrittenBookContent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

@OnlyIn(Dist.CLIENT)
public class BibleNewTestaScreen extends Screen {

    public static final ResourceLocation Background
            = ResourceLocation.fromNamespaceAndPath(LostFaithMod.MODID, "textures/gui/bible_new_testa.png");
    public static final ResourceLocation Title
            = ResourceLocation.fromNamespaceAndPath(LostFaithMod.MODID, "textures/gui/bible_new_testa_title.png");
    public static final Component TitleText
            = Component.translatable("item.lostfaith.bible_new_testa.raw");

    public static BibleNewTestaScreen Current;

    public PageButton LeftArrow, RightArrow;

    public int PageNumber = -1;

    public ItemStack Item;

    public BibleNewTestaScreen(ItemStack item) {
        super(TitleText);
        if (!item.is(LFItems.BibleNewTesta)) {
            throw new RuntimeException("Opening a bible new testament screen without using bible new testament item!");
        }
        Item = item;
        Current = this;
    }

    protected List<StoryMission> Paragraphs;

    @Override
    protected void init() {
        CreateControls();
        Paragraphs = Missions.GetAchievedStories(Objects.requireNonNull(Item.get(LFComponents.BibleMetaDataType)).progress());

        for (StoryMission p : Paragraphs) {
            Lines.addAll(font.getSplitter().splitLines(p.Text, 156, p.Text.getStyle()));
            Lines.add(FormattedText.EMPTY); // Paragraph spacing
        }
    }

    @Override
    public void renderBackground(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        renderTransparentBackground(graphics);
        ResourceLocation img = PageNumber == -1 ? Title : Background;
        graphics.blit(img, (width - 192) / 2, 16, 0, 0, 192, 256, 192, 256);
    }

    private List<FormattedText> Lines = new ArrayList<>();

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        if (PageNumber == 0) {
            int titleWidth = font.width(TitleText);
            PoseStack pose = graphics.pose();
            pose.pushPose();
            pose.scale(2, 2, 2);
            graphics.drawWordWrap(font, TitleText,
                    h(96 - titleWidth) / 2, v(64) / 2, 96, 0xff333333);
            pose.popPose();
            Component author = LFItems.BibleNewTesta.get().GetAuthorText(Item);
            graphics.drawWordWrap(font, author,
                    h(96 - titleWidth), v(128), 96, 0xff333333);
        }
        if (PageNumber > 0) {
            int zbPageNumber = PageNumber - 1; // Zero based page number

            // Page lines: 225(Page Height) / 9(Line Height) = 25
            int lineStartIndex = 25 * zbPageNumber;

            for (int i = lineStartIndex, j = 0; // i: line start index. j: line vertical offset
                // Page lines - 1(To zero based) = 24
                 i < Math.min(lineStartIndex + 25, Lines.stream().count() - 1);
                 ++i, j += font.lineHeight) {
                graphics.drawWordWrap(font, Lines.get(i), h(24), v(16 + j),
                        156, 0xff333333);
            }

            graphics.drawCenteredString(font, Integer.toString(PageNumber), h(96), 276, 0xffffffff);
        }
    }

    @Override
    public void onClose() {
        Minecraft.getInstance().setScreen(null);
        Current = null;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    protected void CreateControls() {
        LeftArrow =
                addRenderableWidget(new PageButton(h(0), v(256), PageButton.PageButtonDirection.Left, x -> PrevPage()));
        RightArrow =
                addRenderableWidget(new PageButton(h(168), v(256), PageButton.PageButtonDirection.Right, x -> NextPage()));
    }

    public void NextPage() {
        PageNumber++;
    }

    public void PrevPage() {
        if (PageNumber > -1) {
            PageNumber--;
        }
    }

    // Horizontal position with offset
    protected int h(int x) {
        return x + (width - 192) / 2;
    }

    // Vertical position with offset
    protected int v(int x) {
        return x + 16;
    }
}
