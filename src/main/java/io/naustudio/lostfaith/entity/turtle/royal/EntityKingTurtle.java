package io.naustudio.lostfaith.entity.turtle.royal;

import io.naustudio.lostfaith.LostFaithMod;
import io.naustudio.lostfaith.entity.turtle.ZombieBasedTurtle;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.NotNull;

public class EntityKingTurtle extends ZombieBasedTurtle {

    private final ServerBossEvent BossInfo
            = (ServerBossEvent) new ServerBossEvent(Component.translatable("entity.lostfaith.bossbar.turtle_monarch_couple"),
            BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.PROGRESS).setDarkenScreen(true);

    private static final ResourceLocation texture
            = ResourceLocation.fromNamespaceAndPath(LostFaithMod.MODID, "textures/entity/king_turtle.png");

    public EntityKingTurtle(EntityType<EntityKingTurtle> type, Level level) {
        super(type, level);
    }

    @Override
    public ResourceLocation Texture() {
        return texture;
    }

    @Override
    public void startSeenByPlayer(@NotNull ServerPlayer p) {
        super.startSeenByPlayer(p);
        BossInfo.addPlayer(p);
    }

    @Override
    public void stopSeenByPlayer(@NotNull ServerPlayer p) {
        super.stopSeenByPlayer(p);
        BossInfo.removePlayer(p);
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
        BossInfo.setProgress(getHealth() / getMaxHealth());
    }

    public static AttributeSupplier.Builder CreateAttributes() {
        return ZombieBasedTurtle.CreateAttributes()
                .add(Attributes.MAX_HEALTH, 300)
                .add(Attributes.FOLLOW_RANGE, 12.0)
                .add(Attributes.ATTACK_DAMAGE, 5.0);
    }
}
