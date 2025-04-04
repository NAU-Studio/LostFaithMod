package io.naustudio.lostland.mixin;

import io.naustudio.lostland.render.PathRenderer;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
@OnlyIn(Dist.CLIENT)
public class EntityRendererMixin {

    @Inject(method = "renderLevel", at = @At("TAIL"))
    private void renderWorldPass(DeltaTracker deltaTracker,
                                 boolean renderBlockOutline,
                                 Camera camera,
                                 GameRenderer gameRenderer,
                                 LightTexture lightTexture,
                                 Matrix4f frustumMatrix,
                                 Matrix4f projectionMatrix,
                                 CallbackInfo ci)
    {
        PathRenderer.Render();
    }
}
