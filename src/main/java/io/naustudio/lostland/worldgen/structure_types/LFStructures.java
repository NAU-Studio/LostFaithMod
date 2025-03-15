package io.naustudio.lostland.worldgen.structure_types;

import io.naustudio.lostland.LostLand;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("unused")
public class LFStructures {

    public static final DeferredRegister<StructureType<?>> Registry
            = DeferredRegister.create(Registries.STRUCTURE_TYPE, LostLand.MODID);

    public static final DeferredHolder<StructureType<?>, StructureType<JigsawStructure>> Town
            = Registry.register("town", () -> () -> JigsawStructure.CODEC);
}
