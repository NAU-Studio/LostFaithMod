package io.naustudio.lostfaith.gui.mission;

import io.naustudio.lostfaith.component.LFComponents;
import io.naustudio.lostfaith.item.LFItems;
import io.naustudio.lostfaith.item.mission.Missions;
import io.naustudio.lostfaith.item.mission.types.MissionBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

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

        MissionBase mission = Missions.GetCurrentMission(progress);
        if (mission == null)
            return;

        int tcenter = graphics.guiHeight() / 2;
        graphics.drawString(font, MissionsText.getVisualOrderText(), 4, tcenter - font.lineHeight, 0xffffffff);
        Component c = Component.translatable("gui.lostfaith.mission", mission.getText());
        graphics.drawString(font, c, 4, tcenter, 0xffffffff);
    }
}
