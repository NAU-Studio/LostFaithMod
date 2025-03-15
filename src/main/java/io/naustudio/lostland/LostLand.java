package io.naustudio.lostland;

import com.mojang.logging.LogUtils;
import io.naustudio.lostland.block.LFBlocks;
import io.naustudio.lostland.component.LFComponents;
import io.naustudio.lostland.entity.LFEntities;
import io.naustudio.lostland.entity.turtle.judas.EntityJudas;
import io.naustudio.lostland.entity.turtle.judas.ModelJudas;
import io.naustudio.lostland.entity.turtle.judas.RendererJudas;
import io.naustudio.lostland.entity.turtle.lost.EntityLostTurtleGuard;
import io.naustudio.lostland.entity.turtle.ModelTurtle;
import io.naustudio.lostland.entity.turtle.RendererTurtle;
import io.naustudio.lostland.entity.turtle.royal.EntityKingTurtle;
import io.naustudio.lostland.entity.turtle.royal.EntityQueenTurtle;
import io.naustudio.lostland.entity.turtle.royal.EntityRoyalTurtleGuard;
import io.naustudio.lostland.gui.BetterBossBar;
import io.naustudio.lostland.item.LFItems;
import io.naustudio.lostland.worldgen.structure_types.LFStructures;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.*;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@Mod(LostLand.MODID)
public class LostLand {

    public static final String MODID = "lostland";

    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB = TABS.register("lostland_main", () ->
            CreativeModeTab.builder()
                    .icon(() -> new ItemStack(LFItems.Crucifix.get()))
                    .title(Component.translatable("item_group.lostland.main")).build());

    public static final BetterBossBar BossBarOverlay = new BetterBossBar();

    public LostLand(IEventBus modEventBus) {
        LFBlocks.Registry.register(modEventBus);
        LFItems.Registry.register(modEventBus);
        LFEntities.Registry.register(modEventBus);
        LFEntities.BlockEntityRegistry.register(modEventBus);
        TABS.register(modEventBus);
        LFComponents.Registry.register(modEventBus);
        LFStructures.Registry.register(modEventBus);

        modEventBus.register(this);
    }

    @SubscribeEvent
    public void addCreative(@NotNull BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == MAIN_TAB.getKey()) {
            Field[] fields = LFItems.class.getFields();
            for (Field field : fields) {
                if (field.getType() == DeferredItem.class && Modifier.isPublic(field.getModifiers())) {
                    try {
                        event.accept((DeferredItem<? extends Item>) field.get(null));
                    } catch (IllegalAccessException ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onRegisterSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(LFEntities.LostTurtleGuard.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkAnyLightMonsterSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    static ResourceLocation res = ResourceLocation.fromNamespaceAndPath(MODID, "blocking");

    @EventBusSubscriber(value = Dist.CLIENT)
    public static class ClientEvents {

        @SubscribeEvent
        public static void onRenderGuiOverlay(RenderGuiEvent.Post e) {
            BossBarOverlay.Render(e.getGuiGraphics(), e.getPartialTick());
        }
    }

    @EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)  {
            event.enqueueWork(
                    () -> ItemProperties.register(LFItems.BibleOldTesta.get(), res,
                            (i, l, e, s) -> e != null && e.isUsingItem() && e.getUseItem() == i ? 1 : 0));
        }

        @SubscribeEvent
        public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(ModelJudas.LayerLocation, ModelJudas::createBodyLayer);
            event.registerLayerDefinition(ModelTurtle.LayerLocation, () -> ModelTurtle.createBodyLayer(false));
            event.registerLayerDefinition(ModelTurtle.SlimLayerLocation, () -> ModelTurtle.createBodyLayer(true));
        }

        @SubscribeEvent
        public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(LFEntities.Judas.get(), RendererJudas::new);
            event.registerEntityRenderer(LFEntities.LostTurtleGuard.get(), RendererTurtle::new);
            event.registerEntityRenderer(LFEntities.RoyalTurtleGuard.get(), RendererTurtle::new);
            event.registerEntityRenderer(LFEntities.KingTurtle.get(), RendererTurtle::new);
            event.registerEntityRenderer(LFEntities.QueenTurtle.get(), x -> new RendererTurtle(x, true));
        }

        @SubscribeEvent
        public static void onAttributeCreate(EntityAttributeCreationEvent event) {
            event.put(LFEntities.Judas.get(), EntityJudas.CreateAttributes().build());
            event.put(LFEntities.LostTurtleGuard.get(), EntityLostTurtleGuard.CreateAttributes().build());
            event.put(LFEntities.RoyalTurtleGuard.get(), EntityRoyalTurtleGuard.CreateAttributes().build());
            event.put(LFEntities.KingTurtle.get(), EntityKingTurtle.CreateAttributes().build());
            event.put(LFEntities.QueenTurtle.get(), EntityQueenTurtle.CreateAttributes().build());
        }
    }
}
