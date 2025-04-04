package io.naustudio.lostland.entity.turtle.royal;

import io.naustudio.lostland.LostLand;
import io.naustudio.lostland.entity.LFEntities;
import io.naustudio.lostland.entity.turtle.ZombieBasedTurtle;
import io.naustudio.lostland.util.MathUtils;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class EntityKingTurtle extends ZombieBasedTurtle {

    private final ServerBossEvent BossInfo
            = (ServerBossEvent) new ServerBossEvent(Component.translatable("entity.lostland.bossbar.turtle_monarch_couple"),
            BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.PROGRESS).setDarkenScreen(true);

    private static final ResourceLocation texture
            = ResourceLocation.fromNamespaceAndPath(LostLand.MODID, "textures/entity/king_turtle.png");

    public EntityQueenTurtle Queen;
    public UUID QueenUUID;

    public boolean ShouldSpawnQueen = true;

    public EntityKingTurtle(EntityType<EntityKingTurtle> type, Level level) {
        super(type, level);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("ShouldSpawnQueen", ShouldSpawnQueen);
        compound.putUUID("Queen", Queen.getUUID());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("ShouldSpawnQueen"))
            ShouldSpawnQueen = compound.getBoolean("ShouldSpawnQueen");
        if (compound.contains("Queen"))
            QueenUUID = compound.getUUID("Queen");
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

        if (ShouldSpawnQueen) {
            Queen = new EntityQueenTurtle(LFEntities.QueenTurtle.get(), level());
            Queen.setPos(MathUtils.Add(position(), new Vec3(1, 0, 0)));
            level().addFreshEntity(Queen);
            ShouldSpawnQueen = false;
        }

        BossInfo.setProgress(getHealth() / getMaxHealth());
    }

    public static AttributeSupplier.Builder CreateAttributes() {
        return ZombieBasedTurtle.CreateAttributes()
                .add(Attributes.MAX_HEALTH, 300)
                .add(Attributes.FOLLOW_RANGE, 12.0)
                .add(Attributes.ATTACK_DAMAGE, 5.0);
    }
}
