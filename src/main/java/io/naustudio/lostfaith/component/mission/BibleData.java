package io.naustudio.lostfaith.component.mission;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.List;

public record BibleData(Component owner, List<String> progress, List<String> cache) {

    public static final Codec<BibleData> CODEC = RecordCodecBuilder.create(
            x -> x.group(
                    ComponentSerialization.CODEC.fieldOf("owner").forGetter(BibleData::owner),
                    Codec.STRING.listOf().fieldOf("progress").forGetter(BibleData::progress),
                    Codec.STRING.listOf().fieldOf("cache").forGetter(BibleData::cache)
            ).apply(x, BibleData::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, BibleData> STREAM_CODEC = StreamCodec.composite(
            ComponentSerialization.STREAM_CODEC, BibleData::owner,
            ByteBufCodecs.fromCodec(Codec.STRING.listOf()), BibleData::progress,
            ByteBufCodecs.fromCodec(Codec.STRING.listOf()), BibleData::cache,
            BibleData::new
    );
}
