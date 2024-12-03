package io.naustudio.lostfaith.gui.mission;

import io.naustudio.lostfaith.component.LFComponents;
import io.naustudio.lostfaith.component.mission.BibleData;
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

import java.util.ArrayList;
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

        if (item == null || !item.has(LFComponents.BibleMetaDataType))
            return;

        BibleData data = item.get(LFComponents.BibleMetaDataType);

        if (data != null) {
            List<String> missions = Missions.GetCurrentMission(data.progress());
            int tcenter = graphics.guiHeight() / 2;
            graphics.drawString(font, MissionsText.getVisualOrderText(), 4, tcenter - font.lineHeight, 0xffffffff);

            StringBuilder sb = new StringBuilder();

            for (String s : missions) {
                Mission m = Missions.Missions.get(s);
                Component c = Component.translatable("gui.lostfaith.mission",
                        m.GetText().getString() + " " + m.GetProgressText(p).getString());
                sb.append(c.getString());
                sb.append('\n');
            }

            List<String> progress = new ArrayList<>(data.progress());

            for (String m : Missions.CheckFinish(data.progress(), p))
                progress.add(m);

            item.set(LFComponents.BibleMetaDataType, new BibleData(data.owner(), progress, data.cache()));

            graphics.drawWordWrap(font, FormattedText.of(sb.toString()), 8, tcenter, (graphics.guiWidth() - 16) / 2, 0xffffffff);
        }
    }
}
