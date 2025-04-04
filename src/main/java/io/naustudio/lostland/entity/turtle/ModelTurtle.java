package io.naustudio.lostland.entity.turtle;

import io.naustudio.lostland.LostLand;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

@SuppressWarnings("unused")
public class ModelTurtle extends PlayerModel<ZombieBasedTurtle> {

    public static final ModelLayerLocation LayerLocation
            = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(LostLand.MODID, "turtle_model"), "main");

    public static final ModelLayerLocation SlimLayerLocation
            = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(LostLand.MODID, "slim_turtle_model"), "main");

    public ModelTurtle(ModelPart root, boolean slim) {
        super(root, slim);
    }

    public static LayerDefinition createBodyLayer(boolean slim) {
        MeshDefinition meshdefinition = PlayerModel.createMesh(CubeDeformation.NONE, slim);
        PartDefinition partdefinition = meshdefinition.getRoot();
        CreateShell(partdefinition);
        return LayerDefinition.create(meshdefinition, 128, 64);
    }

    protected static void CreateShell(PartDefinition def) {
        PartDefinition shell = def.getChild("body").addOrReplaceChild("shell",
                CubeListBuilder.create()
                        .texOffs(64, 14)
                        .addBox(-4, -2, 2, 9, 10, 2, new CubeDeformation(0))
                        .texOffs(64, 0)
                        .addBox(-5, -3, 0, 11, 12, 2, new CubeDeformation(0)),
                PartPose.offset(0, 3, 2));
    }
}
