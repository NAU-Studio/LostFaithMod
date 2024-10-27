package io.naustudio.lostfaith.entity.judas;

import io.naustudio.lostfaith.LostFaithMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RendererJudas extends MobRenderer<EntityJudas, ModelJudas> {

    public static final ResourceLocation Texture = ResourceLocation.fromNamespaceAndPath(LostFaithMod.MODID, "textures/entity/judas.png");

    public RendererJudas(EntityRendererProvider.Context context) {
        super(context, new ModelJudas(context.bakeLayer(ModelJudas.LayerLocation)), 0.625f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityJudas entityJudas) {
        return Texture;
    }
}
