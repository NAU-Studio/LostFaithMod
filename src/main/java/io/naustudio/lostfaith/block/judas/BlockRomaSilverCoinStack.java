package io.naustudio.lostfaith.block.judas;

import io.naustudio.lostfaith.item.LFItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BlockRomaSilverCoinStack extends Block {

    public static final IntegerProperty Count = IntegerProperty.create("count", 1, 4);

    public BlockRomaSilverCoinStack(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any()
                .setValue(Count, 1));
    }

    public boolean IncreaseCoin(Level level, BlockPos pos, BlockState state) {
        int v = state.getValue(Count);
        if (v < 4)
            level.setBlock(pos, state.setValue(Count, v + 1), 2);
        return v < 4;
    }

    public void DecreaseCoin(Level level, BlockPos pos, BlockState state) {
        int v = state.getValue(Count);
        if (v > 1) {
            level.setBlock(pos, state.setValue(Count, v - 1), 2);
            level.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(state));
            level.levelEvent(2001, pos, Block.getId(state));
        }
        else
            level.destroyBlock(pos, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(Count);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.getItem() == LFItems.RomaSilverCoin.get() && IncreaseCoin(level, pos, state))
        {
            level.playSound(player, pos, getSoundType(state, level, pos, player).getPlaceSound(), SoundSource.BLOCKS, 1, 1);
            if (!player.isCreative())
                stack.setCount(stack.getCount() - 1);
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.FAIL;
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.playerDestroy(level, player, pos, state, blockEntity, tool);
        DecreaseCoin(level, pos, state);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Block.box(2, 0, 2, 14, 1, 14);
    }

    @Override
    protected VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return getShape(state, level, pos, null);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return canSupportCenter(level, pos.below(), Direction.UP);
    }
}
