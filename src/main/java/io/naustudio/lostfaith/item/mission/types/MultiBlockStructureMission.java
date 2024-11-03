package io.naustudio.lostfaith.item.mission.types;

import io.naustudio.lostfaith.block.structure.MultiBlockStructureCore;
import io.naustudio.lostfaith.item.mission.trigger.EventTriggerBus;
import net.minecraft.world.entity.player.Player;

public class MultiBlockStructureMission extends MissionBase {

    protected boolean Succeed;

    public MultiBlockStructureMission(String textKey, MultiBlockStructureCore coreType) {
        super(textKey);
        EventTriggerBus.SubscribeOnMultiBlockStructureSucceed(x -> {
            if (x == coreType)
                Succeed = true;
        });
    }

    @Override
    public boolean canContinue(Player player) {
        if (Succeed) {
            Succeed = false;
            return true;
        }
        return false;
    }
}
