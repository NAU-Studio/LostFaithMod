package io.naustudio.lostfaith.item.mission.trigger;

import net.minecraft.world.level.block.Block;

import java.util.EventObject;

public class MultiBlockStructureEvent extends EventObject {

    public Block CoreType;

    public MultiBlockStructureEvent(Object source, Block coreType) {
        super(source);
        CoreType = coreType;
    }
}
