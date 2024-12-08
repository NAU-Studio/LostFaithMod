package io.naustudio.lostfaith.entity.turtle;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class RendererTurtle extends MobRenderer<ZombieBasedTurtle, ModelTurtle> {

    public RendererTurtle(EntityRendererProvider.Context context) { // Most turtle are not slim!!
        super(context, new ModelTurtle(context.bakeLayer(ModelTurtle.LayerLocation), false), 0.625f);
    }

    public RendererTurtle(EntityRendererProvider.Context context, boolean slim) {
        super(context, new ModelTurtle(context.bakeLayer(slim ? ModelTurtle.SlimLayerLocation : ModelTurtle.LayerLocation), slim), 0.625f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ZombieBasedTurtle e) {
        return e.Texture();
    }
}