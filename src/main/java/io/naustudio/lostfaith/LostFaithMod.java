package io.naustudio.lostfaith;

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

@Mod(LostFaithMod.MODID)
public class LostFaithMod {

    public static final String MODID = "lostfaith";

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB = TABS.register("lostfaith_main", () ->
            CreativeModeTab.builder()
                    .icon(() -> new ItemStack(LFItems.Crucifix.get()))
                    .title(Component.translatable("item_group.lostfaith_main")).build());

    public LostFaithMod(IEventBus modEventBus, ModContainer container) {
        LFItems.Registry.register(modEventBus);
        LFEntities.Registry.register(modEventBus);
        TABS.register(modEventBus);
        LFComponents.Registry.register(modEventBus);

        modEventBus.register(this);
    }

    @SubscribeEvent
    public void addCreative(@NotNull BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == MAIN_TAB.getKey()) {
            event.accept(LFItems.Crucifix);
            event.accept(LFItems.SilverCrucifix);
            event.accept(LFItems.BibleOldTesta);
            event.accept(LFItems.BibleNewTesta);
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
                    () -> ItemProperties.register(LFItems.BibleOldTesta.get(), res,
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
