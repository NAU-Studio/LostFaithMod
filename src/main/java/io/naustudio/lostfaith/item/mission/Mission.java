package io.naustudio.lostfaith.item.mission;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.PrimitiveCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.naustudio.lostfaith.item.mission.types.CollectItemMission;
import io.naustudio.lostfaith.item.mission.types.MultiBlockStructureMission;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public abstract class Mission {

    public Component Text;
    public List<String> Next;

    public Mission(Component text, List<String> next) {
        Text = text;
        Next = next;
    }

    public Component GetText() {
        return Text;
    }

    public List<String> GetNext() {
        return Next;
    }

    public Component GetProgressText(Player player) {
        return Component.empty();
    }

    public abstract boolean Finished(Player player);

    public static Mission Parse(JsonElement element)
    {
        String type = element.getAsJsonObject().get("type").getAsString();
        switch (type) {
            case "story": return StoryMission.Codec.parse(JsonOps.INSTANCE, element).getOrThrow();
            case "collect_item": return CollectItemMission.Codec.parse(JsonOps.INSTANCE, element).getOrThrow();
            case "activate": return MultiBlockStructureMission.Codec.parse(JsonOps.INSTANCE, element).getOrThrow();

            default: throw new RuntimeException("Mission type must be included in: [story, collect_item, activate].");
        }
    }
}
