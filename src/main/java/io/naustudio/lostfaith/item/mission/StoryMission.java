package io.naustudio.lostfaith.item.mission;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class StoryMission extends Mission {

    public static final com.mojang.serialization.Codec<Mission> Codec = RecordCodecBuilder.create(x -> x.group(
            ComponentSerialization.CODEC.fieldOf("text").forGetter(Mission::GetText),
            com.mojang.serialization.Codec.STRING.listOf().fieldOf("next").forGetter(Mission::GetNext)
    ).apply(x, StoryMission::new));

    public StoryMission(Component text, List<String> next) {
        super(text, next);
    }

    @Override
    public boolean Finished(Player player) {
        return true;
    }
}
