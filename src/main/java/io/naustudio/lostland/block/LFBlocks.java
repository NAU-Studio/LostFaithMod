package io.naustudio.lostland.block;

import io.naustudio.lostland.LostLand;
import io.naustudio.lostland.block.judas.BlockRomaSilverCoinStack;
import io.naustudio.lostland.block.judas.BlockSilverFramedGoldBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("unused")
public class LFBlocks {

    public static final DeferredRegister.Blocks Registry = DeferredRegister.createBlocks(LostLand.MODID);

    public static final DeferredBlock<BlockRomaSilverCoinStack> SilverCoinStack
            = Registry.register("roma_silver_coin_stack", () -> new BlockRomaSilverCoinStack(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_LIGHT_GRAY)
            .strength(0, 0)
            .sound(SoundType.CHAIN)));

    public static final DeferredBlock<BlockSilverFramedGoldBlock> SilverFramedGoldBlock
            = Registry.register("silver_framed_gold_block", () -> new BlockSilverFramedGoldBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.GOLD)
                    .instrument(NoteBlockInstrument.BELL)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 6.0F)
                    .sound(SoundType.METAL)));
}
