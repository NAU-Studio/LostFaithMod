package io.naustudio.lostfaith.item.mission;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.naustudio.lostfaith.block.LFBlocks;
import io.naustudio.lostfaith.item.LFItems;
import io.naustudio.lostfaith.item.mission.types.CollectItemMission;
import io.naustudio.lostfaith.item.mission.types.MultiBlockStructureMission;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.*;

public class Missions {

    public static Map<String, Mission> Missions = new HashMap<>();

    public static List<String> BeginMissions = new ArrayList<>();

    public static final ResourceLocation MissionsLocation
            = ResourceLocation.fromNamespaceAndPath("lostfaith", "mission/mission.json");

    public static void ReloadMissions() {
        if (Minecraft.getInstance().player == null)
            return;
        ResourceManager res = Minecraft.getInstance().getSingleplayerServer().getResourceManager();

        try {
            BeginMissions.clear();
            Missions.clear();

            JsonObject root = JsonParser.parseReader(res.getResource(MissionsLocation).get().openAsReader()).getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : root.entrySet()) {
                if (Objects.equals(entry.getKey(), "begin")) {
                    for (JsonElement e : entry.getValue().getAsJsonArray().asList())
                        BeginMissions.add(e.getAsString());
                    continue;
                }
                String index = entry.getKey();
                Mission mission = StoryMission.Parse(entry.getValue());
                Missions.put(index, mission);
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Mission loading error.", e);
        }
    }

    public static List<StoryMission> GetAchievedStories(List<String> finished) {
        CheckLoadState();

        List<StoryMission> m = new ArrayList<>();
        for (String s : finished) {
            if (Missions.get(s) instanceof StoryMission story)
                m.add(story);
        }
        return m;
    }

    public static List<String> GetCurrentMission(List<String> finished) {
        CheckLoadState();

        List<String> m = new ArrayList<>();
        for (String s : finished) {
            Mission mx = Missions.get(s);
            List<String> next = mx.GetNext();
            for (String x : next)
                if (!finished.contains(x))
                    m.add(x);
        }
        for (String s : BeginMissions) {
            if (!finished.contains(s))
                m.add(s);
        }
        return m;
    }

    public static List<String> CheckFinish(List<String> f, Player p) {
        CheckLoadState();

        List<String> current = GetCurrentMission(f);
        List<String> finished = new ArrayList<>();
        for (String s : current) {
            if (Missions.get(s).Finished(p))
                finished.add(s);
        }
        return finished;
    }

    public static void CheckLoadState() {
        if (Missions.isEmpty())
            ReloadMissions();
    }
}
