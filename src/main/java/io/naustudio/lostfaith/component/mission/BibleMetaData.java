package io.naustudio.lostfaith.component.mission;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record BibleMetaData(Component owner, boolean finished) {

    public static final Codec<BibleMetaData> CODEC = RecordCodecBuilder.create(
            x -> x.group(
                    ComponentSerialization.CODEC.fieldOf("Owner").forGetter(BibleMetaData::owner),
                    Codec.BOOL.fieldOf("Finished").forGetter(BibleMetaData::finished)
            ).apply(x, BibleMetaData::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, BibleMetaData> STREAM_CODEC = StreamCodec.composite(
            ComponentSerialization.STREAM_CODEC, BibleMetaData::owner,
            ByteBufCodecs.BOOL, BibleMetaData::finished,
            BibleMetaData::new
    );

    public static class Content {

        public final Component Owner;
        public final boolean Finished;

        public Content(BibleMetaData data) {
            if (data == null) {
                Owner = null;
                Finished = false;
            }
            else
            {
                Owner = data.owner();
                Finished = data.finished();
            }
        }
    }
}
