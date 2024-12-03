package io.naustudio.lostfaith.item.mission.types;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.naustudio.lostfaith.block.structure.MultiBlockStructureCore;
import io.naustudio.lostfaith.item.mission.Mission;
import io.naustudio.lostfaith.item.mission.trigger.MultiBlockStructureEventHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class MultiBlockStructureMission extends Mission {

    public static final com.mojang.serialization.Codec<MultiBlockStructureMission> Codec = RecordCodecBuilder.create(x -> x.group(
            ComponentSerialization.CODEC.fieldOf("text").forGetter(Mission::GetText),
            com.mojang.serialization.Codec.STRING.listOf().fieldOf("next").forGetter(Mission::GetNext),
            BlockState.CODEC.fieldOf("block").forGetter(MultiBlockStructureMission::GetCoreType)
    ).apply(x, MultiBlockStructureMission::new));

    private final MultiBlockStructureCore CoreType;
    private boolean Succeed;

    public MultiBlockStructureMission(Component text, List<String> next, BlockState coreType) {
        super(text, next);
        if (!(coreType.getBlock() instanceof MultiBlockStructureCore))
            throw new RuntimeException("Mission activation block is not a multi block structure core.");
        CoreType = (MultiBlockStructureCore) coreType.getBlock();
        MultiBlockStructureEventHandler.Add(x -> {
            if (x.CoreType == CoreType)
                Succeed = true;
        });
    }

    public BlockState GetCoreType() {
        return CoreType.defaultBlockState();
    }

    @Override
    public boolean Finished(Player player) {
        return Succeed;
    }
}
