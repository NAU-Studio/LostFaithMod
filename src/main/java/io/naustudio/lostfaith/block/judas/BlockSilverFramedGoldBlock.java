package io.naustudio.lostfaith.block.judas;

import io.naustudio.lostfaith.block.LFBlocks;
import io.naustudio.lostfaith.block.structure.MultiBlockStructureCore;
import io.naustudio.lostfaith.entity.LFEntities;
import io.naustudio.lostfaith.entity.turtle.judas.EntityJudas;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class BlockSilverFramedGoldBlock extends MultiBlockStructureCore {

    public BlockSilverFramedGoldBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void initStructure() {
        Ingredients.put('@', LFBlocks.SilverCoinStack.get());
        Blocks.put(new BlockPos(-2, 0, 2), '@');
        Blocks.put(new BlockPos(-2, 0, 0), '@');
        Blocks.put(new BlockPos(-2, 0, -2), '@');
        Blocks.put(new BlockPos(2, 0, 2), '@');
        Blocks.put(new BlockPos(2, 0, 0), '@');
        Blocks.put(new BlockPos(2, 0, -2), '@');
    }

    @Override
    protected void onTestFail(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        super.onTestFail(state, level, pos, player, hitResult);
    }

    @Override
    protected void onTestSuccess(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        EntityJudas judas = LFEntities.Judas.get().create(level);
        if (judas != null)
            judas.setPos(pos.below(-1).getBottomCenter());
        level.addFreshEntity(judas);
    }

    @Override
    protected boolean Is(Block block, Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        return super.Is(block, level, pos) ||
                (state.getBlock() == LFBlocks.SilverCoinStack.get() && level.getBlockState(pos).getValue(BlockRomaSilverCoinStack.Count) == 4);
    }

    @Override
    protected Component getTestFailedMessage() {
        return Component.translatable("message.lostfaith.silver_framed_gold_block.interaction_failed");
    }
}
