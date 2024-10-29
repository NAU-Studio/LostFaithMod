package io.naustudio.lostfaith.entity.turtle_guard.lost;

import io.naustudio.lostfaith.LostFaithMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RendererLostTurtleGuard extends MobRenderer<EntityLostTurtleGuard, ModelLostTurtleGuard> {

    public static final ResourceLocation Texture = ResourceLocation.fromNamespaceAndPath(LostFaithMod.MODID, "textures/entity/lost_turtle_guard.png");

    public RendererLostTurtleGuard(EntityRendererProvider.Context context) {
        super(context, new ModelLostTurtleGuard(context.bakeLayer(ModelLostTurtleGuard.LayerLocation)), 0.625f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityLostTurtleGuard e) {
        return Texture;
    }
}