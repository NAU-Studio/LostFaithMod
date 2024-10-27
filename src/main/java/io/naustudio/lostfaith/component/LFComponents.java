package io.naustudio.lostfaith.component;

import com.mojang.serialization.Codec;
import io.naustudio.lostfaith.LostFaithMod;
import io.naustudio.lostfaith.item.mission.MetaRecord;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LFComponents {

    public final DeferredHolder<DataComponentType<?>, DataComponentType<MetaRecord>> BibleMetadata;

    public LFComponents() {
        DeferredRegister.DataComponents registry = LostFaithMod.DATA_COMPONENTS;
        BibleMetadata = BasicComponent(registry, "bible_metadata", MetaRecord.CODEC, MetaRecord.STREAM_CODEC);
    }

    private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> BasicComponent
            (DeferredRegister.DataComponents registry, String name, Codec<T> codec, StreamCodec<RegistryFriendlyByteBuf, T> scodec) {

        return registry.registerComponentType(name, x -> x.persistent(codec).networkSynchronized(scodec));
    }
}
