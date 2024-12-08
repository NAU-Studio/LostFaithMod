package io.naustudio.lostfaith.entity.turtle.royal;

import io.naustudio.lostfaith.LostFaithMod;
import io.naustudio.lostfaith.entity.turtle.ZombieBasedTurtle;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.NotNull;

public class EntityQueenTurtle extends ZombieBasedTurtle {

    private static final ResourceLocation texture
            = ResourceLocation.fromNamespaceAndPath(LostFaithMod.MODID, "textures/entity/king_turtle.png");

    private EntityKingTurtle King;

    public EntityQueenTurtle(EntityType<EntityQueenTurtle> type, Level level, EntityKingTurtle king) {
        super(type, level);
        King = king;
    }

    @Override
    public ResourceLocation Texture() {
        return texture;
    }

    @Override
    public void startSeenByPlayer(@NotNull ServerPlayer p) {
        super.startSeenByPlayer(p);
    }

    @Override
    public void stopSeenByPlayer(@NotNull ServerPlayer p) {
        super.stopSeenByPlayer(p);
    }

    @Override
    public void checkDespawn() {
        if (!EventHooks.checkMobDespawn(this)) {
            if (level().getDifficulty() == Difficulty.PEACEFUL)
                discard();
            else
                noActionTime = 0;
        }
    }

    @Override
    public void tick() {
        super.tick();
    }

    public static AttributeSupplier.Builder CreateAttributes() {
        return ZombieBasedTurtle.CreateAttributes()
                .add(Attributes.MAX_HEALTH, 200)
                .add(Attributes.FOLLOW_RANGE, 12.0)
                .add(Attributes.ATTACK_DAMAGE, 4.0);
    }
}
