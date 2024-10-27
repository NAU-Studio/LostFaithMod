package io.naustudio.lostfaith;

import com.mojang.logging.LogUtils;
import io.naustudio.lostfaith.component.LFComponents;
import io.naustudio.lostfaith.entity.LFEntities;
import io.naustudio.lostfaith.entity.judas.EntityJudas;
import io.naustudio.lostfaith.entity.judas.ModelJudas;
import io.naustudio.lostfaith.entity.judas.RendererJudas;
import io.naustudio.lostfaith.item.LFItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
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
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.UUID;

@Mod(LostFaithMod.MODID)
public class LostFaithMod {

    public static final String MODID = "lostfaith";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, MODID);
    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MODID);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final LFItems LFItems = new LFItems();
    public static final LFComponents LFComponents = new LFComponents();
    public static final LFEntities LFEntities = new LFEntities();

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB = TABS.register("lostfaith_main", () ->
            CreativeModeTab.builder()
                    .icon(() -> new ItemStack(LFItems.CRUCIFIX.get()))
                    .title(Component.translatable("item_group.lostfaith_main")).build());

    public LostFaithMod(IEventBus modEventBus, ModContainer container) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        TABS.register(modEventBus);
        DATA_COMPONENTS.register(modEventBus);

        modEventBus.register(this);
    }

    @SubscribeEvent
    public void addCreative(@NotNull BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == MAIN_TAB.getKey()) {
            event.accept(LFItems.CRUCIFIX);
            event.accept(LFItems.SILVER_CRUCIFIX);
            event.accept(LFItems.BIBLE_OLD_TESTA);
            event.accept(LFItems.BIBLE_NEW_TESTA);
        }
    }

    static ResourceLocation res = ResourceLocation.fromNamespaceAndPath(MODID, "blocking");

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            event.enqueueWork(
                    () -> ItemProperties.register(LFItems.BIBLE_OLD_TESTA.get(), res,
                            (i, l, e, s) -> e != null && e.isUsingItem() && e.getUseItem() == i ? 1 : 0));
        }

        @SubscribeEvent
        public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(ModelJudas.LayerLocation, ModelJudas::createBodyLayer);
        }

        @SubscribeEvent
        public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(LFEntities.Judas.get(), RendererJudas::new);
        }

        @SubscribeEvent
        public static void onAttributeCreate(EntityAttributeCreationEvent event) {
            event.put(LFEntities.Judas.get(), EntityJudas.CreateAttributes().build());
        }
    }
}
