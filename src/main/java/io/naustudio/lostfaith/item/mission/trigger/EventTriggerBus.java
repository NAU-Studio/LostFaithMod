package io.naustudio.lostfaith.item.mission.trigger;

import io.naustudio.lostfaith.block.structure.MultiBlockStructureCore;

import java.util.function.Consumer;

public class EventTriggerBus {

    protected static Consumer<MultiBlockStructureCore> OnMultiBlockStructureSucceedSubscribed = x -> { };

    public static void OnMultiBlockStructureSucceed(MultiBlockStructureCore coreType) {
        OnMultiBlockStructureSucceedSubscribed.accept(coreType);
    }

    public static void SubscribeOnMultiBlockStructureSucceed(Consumer<MultiBlockStructureCore> consumer) {
        OnMultiBlockStructureSucceedSubscribed = OnMultiBlockStructureSucceedSubscribed.andThen(consumer);
    }
}
