package io.naustudio.lostfaith.item.mission.types;

import io.naustudio.lostfaith.block.structure.MultiBlockStructureCore;
import io.naustudio.lostfaith.item.mission.Mission;
import io.naustudio.lostfaith.item.mission.trigger.MultiBlockStructureEventHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class MultiBlockStructureMission extends Mission {

    protected boolean Succeed;

    public MultiBlockStructureMission(int index, Component text, boolean optional, MultiBlockStructureCore coreType) {
        super(index, text, optional);
        MultiBlockStructureEventHandler.Add(x -> {
            if (x.CoreType == coreType)
                Succeed = true;
        });
    }

    @Override
    public boolean Finished(Player player) {
        if (Succeed) {
            Succeed = false;
            return true;
        }
        return false;
    }
}
