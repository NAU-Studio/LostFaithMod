package io.naustudio.lostland.entity;

import io.naustudio.lostland.LostLand;
import io.naustudio.lostland.block.LFBlocks;
import io.naustudio.lostland.block.judas.SilverFramedGoldBlockEntity;
import io.naustudio.lostland.entity.turtle.judas.EntityJudas;
import io.naustudio.lostland.entity.turtle.lost.EntityLostTurtleGuard;
import io.naustudio.lostland.entity.turtle.royal.EntityKingTurtle;
import io.naustudio.lostland.entity.turtle.royal.EntityQueenTurtle;
import io.naustudio.lostland.entity.turtle.royal.EntityRoyalTurtleGuard;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class LFEntities {

    public static final DeferredRegister<EntityType<?>> Registry = DeferredRegister.create(Registries.ENTITY_TYPE, LostLand.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<EntityJudas>> Judas
            = Registry.register("judas", () -> EntityType.Builder.of(EntityJudas::new, MobCategory.MONSTER)
            .sized(1, 2.5f).setTrackingRange(36)
            .eyeHeight(2)
            .build(ResourceLocation.fromNamespaceAndPath(LostLand.MODID, "judas").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityLostTurtleGuard>> LostTurtleGuard
            = Registry.register("lost_turtle_guard", () -> EntityType.Builder.of(EntityLostTurtleGuard::new, MobCategory.MONSTER)
            .sized(0.6F, 1.95F)
            .eyeHeight(1.75F)
            .clientTrackingRange(8)
            .build(ResourceLocation.fromNamespaceAndPath(LostLand.MODID, "lost_turtle_guard").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityRoyalTurtleGuard>> RoyalTurtleGuard
            = Registry.register("royal_turtle_guard", () -> EntityType.Builder.of(EntityRoyalTurtleGuard::new, MobCategory.MONSTER)
            .sized(0.6F, 1.95F)
            .eyeHeight(1.75F)
            .build(ResourceLocation.fromNamespaceAndPath(LostLand.MODID, "royal_turtle_guard").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityKingTurtle>> KingTurtle
            = Registry.register("king_turtle", () -> EntityType.Builder.of(EntityKingTurtle::new, MobCategory.MONSTER)
            .sized(0.6F, 1.95F)
            .eyeHeight(1.75F)
            .build(ResourceLocation.fromNamespaceAndPath(LostLand.MODID, "king_turtle").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityQueenTurtle>> QueenTurtle
            = Registry.register("queen_turtle", () -> EntityType.Builder.of(EntityQueenTurtle::new, MobCategory.MONSTER)
            .sized(0.6F, 1.95F)
            .eyeHeight(1.75F)
            .build(ResourceLocation.fromNamespaceAndPath(LostLand.MODID, "queen_turtle").toString()));

    public static final DeferredRegister<BlockEntityType<?>> BlockEntityRegistry
            = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, LostLand.MODID);

    public static final Supplier<BlockEntityType<SilverFramedGoldBlockEntity>> SilverFramedGoldBlock
            = BlockEntityRegistry.register("silver_framed_gold_block",
            () -> BlockEntityType.Builder
                    .of(SilverFramedGoldBlockEntity::new, LFBlocks.SilverFramedGoldBlock.get())
                    .build(null));
}
