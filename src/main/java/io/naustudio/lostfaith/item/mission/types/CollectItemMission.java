package io.naustudio.lostfaith.item.mission.types;

import io.naustudio.lostfaith.item.mission.VirtualItemStack;

public class CollectItemMission extends MissionBase {

    protected VirtualItemStack RequiredItem;

    public CollectItemMission(String textKey, VirtualItemStack requiredItem) {
        super(textKey);
        RequiredItem = requiredItem;
    }
}
