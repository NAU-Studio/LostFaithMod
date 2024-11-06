package io.naustudio.lostfaith.entity.turtle;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RendererTurtle extends MobRenderer<ZombieBasedTurtle, ModelTurtle> {

    public RendererTurtle(EntityRendererProvider.Context context) {
        super(context, new ModelTurtle(context.bakeLayer(ModelTurtle.LayerLocation)), 0.625f);
    }

    @Override
    public ResourceLocation getTextureLocation(ZombieBasedTurtle e) {
        return e.Texture();
    }
}