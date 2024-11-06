package io.naustudio.lostfaith;

import com.mojang.logging.LogUtils;
import io.naustudio.lostfaith.block.LFBlocks;
import io.naustudio.lostfaith.component.LFComponents;
import io.naustudio.lostfaith.entity.LFEntities;
import io.naustudio.lostfaith.entity.turtle.judas.EntityJudas;
import io.naustudio.lostfaith.entity.turtle.judas.ModelJudas;
import io.naustudio.lostfaith.entity.turtle.judas.RendererJudas;
import io.naustudio.lostfaith.entity.turtle.lost.EntityLostTurtleGuard;
import io.naustudio.lostfaith.entity.turtle.ModelTurtle;
import io.naustudio.lostfaith.entity.turtle.RendererTurtle;
import io.naustudio.lostfaith.item.LFItems;
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
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
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

@Mod(LostFaithMod.MODID)
public class LostFaithMod {

    public static final String MODID = "lostfaith";

    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB = TABS.register("lostfaith_main", () ->
            CreativeModeTab.builder()
                    .icon(() -> new ItemStack(LFItems.Crucifix.get()))
                    .title(Component.translatable("item_group.lostfaith_main")).build());

    public LostFaithMod(IEventBus modEventBus, ModContainer container) {
        LFBlocks.Registry.register(modEventBus);
        LFItems.Registry.register(modEventBus);
        LFEntities.Registry.register(modEventBus);
        TABS.register(modEventBus);
        LFComponents.Registry.register(modEventBus);

        modEventBus.register(this);
    }

    @SubscribeEvent
    public void addCreative(@NotNull BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == MAIN_TAB.getKey()) {
            Field[] fields = LFItems.class.getFields();
            for (Field field : fields) {
                if (field.getType() == DeferredItem.class && Modifier.isPublic(field.getModifiers())) {
                    try {
                        event.accept((DeferredItem<? extends Item>)field.get(null));
                        LOGGER.debug("Registered {}", field.getName());
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

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)  {
            event.enqueueWork(
                    () -> ItemProperties.register(LFItems.BibleOldTesta.get(), res,
                            (i, l, e, s) -> e != null && e.isUsingItem() && e.getUseItem() == i ? 1 : 0));
        }

        @SubscribeEvent
        public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(ModelJudas.LayerLocation, ModelJudas::createBodyLayer);
            event.registerLayerDefinition(ModelTurtle.LayerLocation, ModelTurtle::createBodyLayer);
        }

        @SubscribeEvent
        public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(LFEntities.Judas.get(), RendererJudas::new);
            event.registerEntityRenderer(LFEntities.LostTurtleGuard.get(), RendererTurtle::new);
        }

        @SubscribeEvent
        public static void onAttributeCreate(EntityAttributeCreationEvent event) {
            event.put(LFEntities.Judas.get(), EntityJudas.CreateAttributes().build());
            event.put(LFEntities.LostTurtleGuard.get(), EntityLostTurtleGuard.CreateAttributes().build());
        }
    }
}
