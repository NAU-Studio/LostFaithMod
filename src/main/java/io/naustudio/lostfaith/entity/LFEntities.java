package io.naustudio.lostfaith.entity;

import io.naustudio.lostfaith.LostFaithMod;
import io.naustudio.lostfaith.entity.judas.EntityJudas;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LFEntities {

    public static DeferredRegister<EntityType<?>> Registry = DeferredRegister.create(Registries.ENTITY_TYPE, LostFaithMod.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<EntityJudas>> Judas
            = Registry.register("judas", () -> EntityType.Builder.of(EntityJudas::new, MobCategory.MONSTER)
            .sized(1, 2.5f).setTrackingRange(36)
            .eyeHeight(2).build(ResourceLocation.fromNamespaceAndPath(LostFaithMod.MODID, "judas").toString()));
}
