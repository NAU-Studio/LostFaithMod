package io.naustudio.lostland.component;

import io.naustudio.lostland.LostLand;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("unused")
public class LFComponents {

    public static final DeferredRegister.DataComponents Registry = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, LostLand.MODID);
}
