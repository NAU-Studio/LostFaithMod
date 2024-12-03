package io.naustudio.lostfaith.component;

import io.naustudio.lostfaith.LostFaithMod;
import io.naustudio.lostfaith.component.mission.BibleData;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LFComponents {

    public static final DeferredRegister.DataComponents Registry = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, LostFaithMod.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BibleData>> BibleMetaDataType
            = Registry.registerComponentType("bible_meta_data",
                    x -> x.persistent(BibleData.CODEC).networkSynchronized(BibleData.STREAM_CODEC));
}
