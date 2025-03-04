package io.naustudio.lostfaith.block.structure;

import com.mojang.logging.LogUtils;
import io.naustudio.lostfaith.util.MathUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.slf4j.Logger;

import java.util.*;

public abstract class MultiBlockStructureCore extends Block {

    protected Logger logger = LogUtils.getLogger();

    public MultiBlockStructureCore(Properties properties) {
        super(properties);
        initStructure();
        if (Blocks.isEmpty())
            logger.error("initStructure didn't initialize blocks!");
    }

    public Dictionary<Character, Block> Ingredients = new Hashtable<>();

    public Dictionary<BlockPos, Character> Blocks = new Hashtable<>();

    protected abstract void initStructure();

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (Blocks.isEmpty())
            return InteractionResult.FAIL;

        Enumeration<BlockPos> e = Blocks.keys();
        boolean Ok = true;
        while (e.hasMoreElements()) {
            BlockPos p = e.nextElement();
            BlockPos r = p.rotate(Rotation.CLOCKWISE_90);
            Block ing = Ingredients.get(Blocks.get(p));
            Ok &= Is(ing, level, MathUtils.Add(pos, p)) ||
                    Is(ing, level, MathUtils.Add(pos, r));
        }
        if (Ok)
        {
            onTestSuccess(state, level, pos, player, hitResult);
            e = Blocks.keys();
            while (e.hasMoreElements()) {
                BlockPos p = e.nextElement();
                BlockPos r = p.rotate(Rotation.CLOCKWISE_90);
                level.destroyBlock(MathUtils.Add(pos, p), false);
                level.destroyBlock(MathUtils.Add(pos, r), false);
            }
            level.destroyBlock(pos, false);
            return InteractionResult.SUCCESS;
        }
        onTestFail(state, level, pos, player, hitResult);

        return InteractionResult.FAIL;
    }

    protected void onTestFail(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult)
    {
        Component testFailedMsg = getTestFailedMessage();
        if (testFailedMsg != null)
            player.sendSystemMessage(testFailedMsg);
    }

    protected Component getTestFailedMessage() { return null; }

    protected void onTestSuccess(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) { }

    protected boolean Is(Block block, Level level, BlockPos pos) {
        return level.getBlockState(pos).getBlock() == block;
    }
}
