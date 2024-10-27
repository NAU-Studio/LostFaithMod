package io.naustudio.lostfaith.item.mission;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record MetaRecord(Component owner, boolean finished) {

    public static final Codec<MetaRecord> CODEC = RecordCodecBuilder.create(
            x -> x.group(
                    ComponentSerialization.CODEC.fieldOf("Owner").forGetter(MetaRecord::owner),
                    Codec.BOOL.fieldOf("Finished").forGetter(MetaRecord::finished)
            ).apply(x, MetaRecord::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, MetaRecord> STREAM_CODEC = StreamCodec.composite(
            ComponentSerialization.STREAM_CODEC, MetaRecord::owner,
            ByteBufCodecs.BOOL, MetaRecord::finished,
            MetaRecord::new
    );
}
