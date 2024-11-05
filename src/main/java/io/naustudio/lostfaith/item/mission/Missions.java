package io.naustudio.lostfaith.item.mission;

import io.naustudio.lostfaith.block.LFBlocks;
import io.naustudio.lostfaith.item.LFItems;
import io.naustudio.lostfaith.item.mission.types.CollectItemMission;
import io.naustudio.lostfaith.item.mission.types.MultiBlockStructureMission;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Missions {

    public static final Mission[] Missions = new Mission[] {
            new StoryMission(0, Component.translatable("text.lostfaith.story.0")),
            new CollectItemMission(1, Component.translatable("text.lostfaith.story.1"), false,
                    new VirtualItemStack(LFItems.RomaSilverCoin.get(), 30, null)),
            new CollectItemMission(1, Component.translatable("text.lostfaith.story.2"), true,
                    new VirtualItemStack(LFItems.SilverCrucifix.get(), 1, null)),
            new StoryMission(2, Component.translatable("text.lostfaith.story.3")),
            new MultiBlockStructureMission(3, Component.translatable("text.lostfaith.story.4"), false,
                    LFBlocks.SilverFramedGoldBlock.get())
    };

    public static final List<Mission> FinishedMissions = new ArrayList<>();

    public static List<StoryMission> GetAchievedStories(int progress) {
        List<StoryMission> ret = new ArrayList<>();
        for (int i = 0; i < progress; i++) {
            if (Missions[i] instanceof StoryMission m)
                ret.add(m);
        }
        return ret;
    }

    public static @Nullable List<Mission> GetCurrentMission(int progress) {
        List<Mission> ret = new ArrayList<>();
        
        for (int i = 0; i < Missions.length; i++) {
            Mission m = Missions[i];
            if (m.Index <= progress && !FinishedMissions.contains(m))
                ret.add(m);
        }
        
        return ret;
    }

    public static Mission GetCurrentEventMission(int progress) {
        return Missions[progress];
    }
    
    public static boolean CheckFinish(int progress, Player player) {
        boolean nextProgress = true;
        
        List<Mission> ms = GetCurrentMission(progress);
        for (int i = 0; i < ms.size(); i++) {
            Mission m = ms.get(i);
            if (m.Finished(player)) {
                FinishedMissions.add(m);
            }
            else {
                if (!m.IsOptional())
                    nextProgress = false;
            }
        }
        return nextProgress;
    }
}
