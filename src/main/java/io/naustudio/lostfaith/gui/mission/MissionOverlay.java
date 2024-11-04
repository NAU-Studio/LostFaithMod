package io.naustudio.lostfaith.gui.mission;

import io.naustudio.lostfaith.component.LFComponents;
import io.naustudio.lostfaith.item.LFItems;
import io.naustudio.lostfaith.item.mission.Mission;
import io.naustudio.lostfaith.item.mission.Missions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

import java.util.List;

@EventBusSubscriber({Dist.CLIENT})
public class MissionOverlay {

    protected static final Component MissionsText = Component.translatable("gui.lostfaith.missions");

    @SubscribeEvent
    public static void eventHandler(RenderGuiEvent.Pre event) {
        GuiGraphics graphics = event.getGuiGraphics();

        Player p = Minecraft.getInstance().player;
        Inventory inv = p.getInventory();
        ItemStack item = null;
        Font font = Minecraft.getInstance().font;

        for (int i = 0; i < inv.getContainerSize(); i++) {
            if (inv.getItem(i).is(LFItems.BibleNewTesta)) {
                item = inv.getItem(i);
                break;
            }
        }

        if (item == null || !item.has(LFComponents.BibleProgressType))
            return;

        int progress = item.get(LFComponents.BibleProgressType);

        List<Mission> missions = Missions.GetCurrentMission(progress);

        int tcenter = graphics.guiHeight() / 2;
        graphics.drawString(font, MissionsText.getVisualOrderText(), 4, tcenter - font.lineHeight, 0xffffffff);

        StringBuilder sb = new StringBuilder();

        for (Mission m : missions) {
            Component c = Component.translatable("gui.lostfaith.mission",
                    m.GetText().getString() + " " + m.GetProgressText(p).getString());
            sb.append(c.getString());
            sb.append('\n');
        }

        graphics.drawWordWrap(font, FormattedText.of(sb.toString()), 8, tcenter, (graphics.guiWidth() - 16) / 2, 0xffffffff);
    }
}
