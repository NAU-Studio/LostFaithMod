package io.naustudio.lostfaith.entity.turtle.judas;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.naustudio.lostfaith.LostFaithMod;
import io.naustudio.lostfaith.util.MathUtils;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("unused")
public class ModelJudas extends EntityModel<EntityJudas> {

    public static final ModelLayerLocation LayerLocation
            = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(LostFaithMod.MODID, "judas"), "main");

    private final ModelPart Head;
    private final ModelPart Body;
    private final ModelPart LeftLeg;
    private final ModelPart RightLeg;
    private final ModelPart LeftArm;
    private final ModelPart RightArm;
    private final ModelPart Hammer;
    private final ModelPart Shell;

    public ModelJudas(ModelPart root) {
        Body = root.getChild("body");
        LeftLeg = root.getChild("left_leg");
        RightLeg = root.getChild("right_leg");
        LeftArm = root.getChild("left_arm");
        RightArm = root.getChild("right_arm");
        Hammer = RightArm.getChild("hammer");
        Head = root.getChild("head");
        Shell = Head.getChild("shell");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(32, 91)
                        .addBox(-3, -13, -2, 8, 13, 4, new CubeDeformation(0.0F)),
                PartPose.offset(0, 12, 0));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg",
                CubeListBuilder.create()
                        .texOffs(16, 93)
                        .addBox(-2, 0, -2, 4, 12, 4, new CubeDeformation(0)),
                PartPose.offset(-1, 12, 0));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg",
                CubeListBuilder.create()
                        .texOffs(0, 93)
                        .addBox(-2, 0, -2, 4, 12, 4, new CubeDeformation(0)),
                PartPose.offset(3, 12, 0));

        PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm",
                CubeListBuilder.create()
                        .texOffs(22, 109).mirror()
                        .addBox(0, -2, -2, 4, 13, 4, new CubeDeformation(0))
                        .mirror(false),
                PartPose.offset(5.0F, 1.0F, 0.0F));

        PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm",
                CubeListBuilder.create()
                        .texOffs(22, 109)
                        .addBox(-4, -2, -2, 4, 13, 4, new CubeDeformation(0)),
                PartPose.offset(-3, 1, 0));

        PartDefinition hammer = right_arm.addOrReplaceChild("hammer",
                CubeListBuilder.create()
                        .texOffs(22, 108)
                        .addBox(-1, -1, -15, 2, 2, 18, new CubeDeformation(0))
                        .texOffs(0, 111)
                        .addBox(-3, -6, -16, 6, 12, 5, new CubeDeformation(0)),
                PartPose.offset(-2, 9.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head",
                CubeListBuilder.create()
                        .texOffs(0, 20)
                        .addBox(-3.5f, -9, -5, 9, 9, 9, new CubeDeformation(0)),
                PartPose.offset(0, -1, 0));

        PartDefinition shell = head.addOrReplaceChild("shell",
                CubeListBuilder.create()
                        .texOffs(26, 78)
                        .addBox(-4, -2, 2, 9, 10, 2, new CubeDeformation(0))
                        .texOffs(0, 79)
                        .addBox(-5, -3, 0, 11, 12, 2, new CubeDeformation(0)),
                PartPose.offset(0, -13.5f, 6));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(EntityJudas entity, float swing, float swingAmount, float age, float yaw, float pitch) {
        Head.xRot = MathUtils.ToRad(pitch);
        Head.yRot = MathUtils.ToRad(yaw);

        float scale = (float)entity.getDeltaMovement().lengthSqr() / 0.2f;
        scale *= scale * scale;
        if (scale < 1)
            scale = 1;

        float swingA = Mth.sin(swing / 1.1f) * swingAmount * scale;
        float swingB = Mth.sin(swing / 1.1f + MathUtils.Pi) * swingAmount * scale;
        float swingShell = Mth.cos(age / 8);

        LeftArm.xRot = swingB;
        RightLeg.xRot = swingB * 1.25f;
        RightArm.xRot = swingA;
        LeftLeg.xRot = swingA * 1.25f;

        Shell.y = swingShell - 12;

        PerformAttackAnimation(entity, age);
    }

    public void PerformAttackAnimation(EntityJudas entity, float age) {
        if (attackTime > 0) {
            RightArm.xRot = -MathUtils.ToRad(Mth.cos(attackTime * MathUtils.Pi / 2) * 150);
        }
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack matrix, @NotNull VertexConsumer vc, int light, int overlay, int color) {
        Body.render(matrix, vc, light, overlay, color);
        RightLeg.render(matrix, vc, light, overlay, color);
        LeftLeg.render(matrix, vc, light, overlay, color);
        LeftArm.render(matrix, vc, light, overlay, color);
        RightArm.render(matrix, vc, light, overlay, color);
        Head.render(matrix, vc, light, overlay, color);
    }
}
