package io.naustudio.lostfaith.block.judas;

import io.naustudio.lostfaith.block.LFBlocks;
import io.naustudio.lostfaith.entity.LFEntities;
import io.naustudio.lostfaith.util.MathUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class BlockSilverFramedGoldBlock extends Block implements EntityBlock {

    public BlockSilverFramedGoldBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return LFEntities.SilverFramedGoldBlock.get().create(blockPos, blockState);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return type == LFEntities.SilverFramedGoldBlock.get() ? SilverFramedGoldBlockEntity::Tick : null;
    }

    private static final BlockPos[] intersections = {
            new BlockPos(2, 0, 2),
            new BlockPos(2, 0, -2),
            new BlockPos(-2, 0, 2),
            new BlockPos(-2, 0, -2)
    };
    private static final BlockPos[] xPositions = {
            new BlockPos(0, 0, 2),
            new BlockPos(0, 0, -2)
    };
    private static final BlockPos[] zPositions = {
            new BlockPos(2, 0, 0),
            new BlockPos(-2, 0, 0)
    };

    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        SilverFramedGoldBlockEntity e = level.getBlockEntity(pos, LFEntities.SilverFramedGoldBlock.get()).get();
        if (e.Progress >= 0)
            return InteractionResult.FAIL;

        boolean interSuccess = true, xSuccess = true, zSuccess = true;
        List<BlockPos> coins = new ArrayList<>();
        for (BlockPos p : intersections) {
            BlockPos ap = MathUtils.Add(pos, p);
            BlockState s = level.getBlockState(ap);
            if (s.getBlock() != LFBlocks.SilverCoinStack.get()) {
                interSuccess = false;
                continue;
            }
            else if (s.getValue(BlockRomaSilverCoinStack.Count) != 4) {
                interSuccess = false;
            }
            coins.add(ap);
        }

        for (BlockPos p : xPositions) {
            BlockPos ap = MathUtils.Add(pos, p);
            BlockState s = level.getBlockState(ap);
            if (s.getBlock() != LFBlocks.SilverCoinStack.get()) {
                xSuccess = false;
                continue;
            }
            else if (s.getValue(BlockRomaSilverCoinStack.Count) != 4) {
                xSuccess = false;
            }
            coins.add(ap);
        }
        for (BlockPos p : zPositions) {
            BlockPos ap = MathUtils.Add(pos, p);
            BlockState s = level.getBlockState(ap);
            if (s.getBlock() != LFBlocks.SilverCoinStack.get()) {
                zSuccess = false;
                continue;
            }
            else if (s.getValue(BlockRomaSilverCoinStack.Count) != 4) {
                zSuccess = false;
            }
            coins.add(ap);
        }

        e.Coins = coins;
        e.Succeed = interSuccess && (xSuccess || zSuccess);
        e.Progress = 0;
        return InteractionResult.SUCCESS;
    }
}
