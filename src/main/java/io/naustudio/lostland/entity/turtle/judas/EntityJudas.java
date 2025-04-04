package io.naustudio.lostland.entity.turtle.judas;

import io.naustudio.lostland.LostLand;
import io.naustudio.lostland.entity.DivineFireball;
import io.naustudio.lostland.entity.INewHealthSystem;
import io.naustudio.lostland.util.MathUtils;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.NotNull;

public class EntityJudas extends Monster implements INewHealthSystem {

    public EntityJudas(EntityType<EntityJudas> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
    }

    public static AttributeSupplier.Builder CreateAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.ATTACK_DAMAGE, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.FOLLOW_RANGE, 40)
                .add(Attributes.MAX_HEALTH, 400)
                .add(Attributes.ARMOR, 15)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5);
    }

    private boolean flameEnabled = false;

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public boolean attackable() {
        return started;
    }

    private int storyboardTick = 0;

    @Override
    public void tick() {
        super.tick();

        if (level() instanceof ClientLevel)
            return;

        storyboardTick++;

        switch (storyboardTick) {
            case 40: Say("message.lostland.judas.judas.0", "message.lostland.sender.judas"); break;
            case 100: Say("message.lostland.judas.player.0", "message.lostland.sender.player"); break;
            case 160: Say("message.lostland.judas.judas.1", "message.lostland.sender.judas"); break;
            case 200: StartBattle(); break;
        }

        if (getHealth() < 200 && !flameEnabled) {
            flameEnabled = true;
            goalSelector.addGoal(7, new ShootFlameGoal(this));
        }
    }

    private boolean started = false;

    private void StartBattle() {
        started = true;
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(2, new MeleeAttackGoal(this, 1, false));
        goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1, false, 4, () -> true));
        goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1));
        goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Cat.class, true));
    }

    private void Say(String msgTranslatable, String senderTranslatable) {
        for (var p : level().players())
            p.sendSystemMessage(Component.translatable("message.lostland.format", Component.translatable(senderTranslatable), Component.translatable(msgTranslatable)));
    }

    @Override
    public void startSeenByPlayer(@NotNull ServerPlayer p) {
        super.startSeenByPlayer(p);
        LostLand.BossBarOverlay.TrackEntity(this);
    }

    @Override
    public void stopSeenByPlayer(@NotNull ServerPlayer p) {
        super.stopSeenByPlayer(p);
        LostLand.BossBarOverlay.Untrack();
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
    public float GetHealth() {
        return getHealth();
    }

    @Override
    public float GetMaxHealth() {
        return getMaxHealth();
    }

    @Override
    public float GetShield() {
        return 0;
    }

    @Override
    public float GetStageCount() {
        return 1;
    }

    static class ShootFlameGoal extends Goal {
        private final EntityJudas Entity;
        public int Delay;

        public ShootFlameGoal(EntityJudas mob) {
            Entity = mob;
        }

        public boolean canUse() {
            return Entity.getTarget() != null;
        }

        public void start() {
            Delay = 0;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            LivingEntity target = Entity.getTarget();
            Delay++;
            if (target != null && Delay >= 0
                    && MathUtils.RaycastBlock(Entity.level(), Entity, (float)MathUtils.Sub(target.position(), Entity.position()).length()).getType()
                        != HitResult.Type.BLOCK
                    && target.distanceToSqr(Entity) > 12 && Entity.hasLineOfSight(target)) {
                Delay = -40;
                Level level = Entity.level();

                // Copied from ghast
                Vec3 vec3 = Entity.getViewVector(1.0F);
                double d2 = target.getX() - (Entity.getX() + vec3.x * 4.0);
                double d3 = target.getY(0.5) - (0.5 + Entity.getY(0.5));
                double d4 = target.getZ() - (Entity.getZ() + vec3.z * 4.0);
                Vec3 vec31 = new Vec3(d2, d3, d4);
                // end

                DivineFireball flame = new DivineFireball(level, Entity, 2);
                flame.setPos(Entity.getX(), Entity.getY() + 2.25, Entity.getZ());
                flame.shoot(vec31.x, vec31.y, vec31.z, 2, 0);
                level.addFreshEntity(flame);
            }
        }
    }
}
