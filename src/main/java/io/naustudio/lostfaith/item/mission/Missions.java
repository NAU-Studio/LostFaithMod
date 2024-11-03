package io.naustudio.lostfaith.item.mission;

import io.naustudio.lostfaith.item.LFItems;
import io.naustudio.lostfaith.item.mission.types.CollectItemMission;
import io.naustudio.lostfaith.item.mission.types.MissionBase;
import net.minecraft.core.component.DataComponentMap;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Missions {

    public static final Mission[] Missions = new Mission[] {
            new StoryMission("text.lostfaith.story.0"),
            new CollectItemMission("text.lostfaith.story.1", new VirtualItemStack(
                    LFItems.RomaSilverCoin.get(),
                    30,
                    DataComponentMap.EMPTY
            )),
    };


    public static List<StoryMission> GetAchievedStories(int progress) {
        List<StoryMission> ret = new ArrayList<>();
        for (int i = 0; i < progress; i++) {
            if (Missions[i] instanceof StoryMission m)
                ret.add(m);
        }
        return ret;
    }

    public static @Nullable MissionBase GetCurrentMission(int progress) {
        if (Missions[progress] instanceof MissionBase m)
            return m;
        else
            return null;
    }

    public static Mission GetCurrentEventMission(int progress) {
        return Missions[progress];
    }
}
