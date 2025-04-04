package io.naustudio.lostland.block.judas;

import io.naustudio.lostland.entity.LFEntities;
import io.naustudio.lostland.entity.turtle.judas.EntityJudas;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class SilverFramedGoldBlockEntity extends BlockEntity {

    public int Progress = -1;
    public boolean Succeed = false;
    public List<BlockPos> Coins = new ArrayList<>();

    public SilverFramedGoldBlockEntity(BlockPos pos, BlockState blockState) {
        super(LFEntities.SilverFramedGoldBlock.get(), pos, blockState);
    }

    private static final String KProgress = "progress", KSucceed = "succeed", KCoins = "coins";

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        Progress = tag.getInt(KProgress);
        Succeed = tag.getBoolean(KSucceed);
        int[] cpa = tag.getIntArray(KCoins);
        if (cpa.length % 3 != 0)
            throw new RuntimeException("Coins is not position array.");
        for (int i = 0; i < cpa.length; i += 3) {
            Coins.add(new BlockPos(cpa[i], cpa[i + 1], cpa[i + 2]));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt(KProgress, Progress);
        tag.putBoolean(KSucceed, Succeed);
        int[] cpa = new int[Coins.size() * 3];
        for (int i = 0; i < Coins.size(); i++) {
            int t = i * 3;
            BlockPos p = Coins.get(i);
            cpa[t] = p.getX();
            cpa[t + 1] = p.getY();
            cpa[t + 2] = p.getZ();
        }
        tag.putIntArray(KCoins, cpa);
    }

    private static final int Delay = 10;

    private void Tick(Level level, BlockPos pos)
    {
        if (Progress < 0)
            return;

        int pcv = Progress / Delay;

        if (Progress % Delay == 0) {
            if (pcv < Coins.size()) {
                BlockPos cp = Coins.get(pcv);
                LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(level);
                bolt.moveTo(cp.getBottomCenter());
                level.addFreshEntity(bolt);
                level.addDestroyBlockEffect(cp, level.getBlockState(cp));
                level.destroyBlock(cp, false);
            }
            else {
                LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(level);
                bolt.moveTo(pos.getBottomCenter());
                level.addFreshEntity(bolt);
                level.addDestroyBlockEffect(pos, level.getBlockState(pos));
                level.setBlock(pos, Blocks.GOLD_BLOCK.defaultBlockState(), 0b10);
                if (!Succeed)
                    return;
                EntityJudas judas = LFEntities.Judas.get().create(level);
                judas.setPos(pos.above().getBottomCenter());
                level.addFreshEntity(judas);
                Progress = -1;
                return;
            }
        }
        Progress++;
    }

    public static <T extends BlockEntity> void Tick(Level level, BlockPos pos, BlockState state, T e) {
        if (e instanceof SilverFramedGoldBlockEntity ec)
            ec.Tick(level, pos);
    }
}
