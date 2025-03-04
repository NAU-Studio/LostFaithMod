package io.naustudio.lostfaith.component;

import io.naustudio.lostfaith.LostFaithMod;
import io.naustudio.lostfaith.item.mission.MetaRecord;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("unused")
public class LFComponents {

    public static final DeferredRegister.DataComponents Registry = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, LostFaithMod.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<MetaRecord>> BibleMetadata
            = Registry.registerComponentType("bible_metadata",
                    x -> x.persistent(MetaRecord.CODEC).networkSynchronized(MetaRecord.STREAM_CODEC)) ;
}
