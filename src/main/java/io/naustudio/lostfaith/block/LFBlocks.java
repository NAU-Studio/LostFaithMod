package io.naustudio.lostfaith.block;

import io.naustudio.lostfaith.LostFaithMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LFBlocks {

    public static final DeferredRegister.Blocks Registry = DeferredRegister.createBlocks(LostFaithMod.MODID);

    public static final DeferredBlock<Block> SilverFramedGoldBlock
            = Registry.register("silver_framed_gold_block", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.GOLD)
                    .instrument(NoteBlockInstrument.BELL)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 6.0F)
                    .sound(SoundType.METAL)));
}
