package io.naustudio.lostfaith.component;

import com.mojang.serialization.Codec;
import io.naustudio.lostfaith.LostFaithMod;
import io.naustudio.lostfaith.component.mission.BibleMetaData;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LFComponents {

    public static final DeferredRegister.DataComponents Registry = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, LostFaithMod.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BibleMetaData>> BibleMetaDataType
            = Registry.registerComponentType("bible_meta_data",
                    x -> x.persistent(BibleMetaData.CODEC).networkSynchronized(BibleMetaData.STREAM_CODEC));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> BibleProgressType
            = Registry.registerComponentType("bible_progress",
                    x -> x.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT));
}
