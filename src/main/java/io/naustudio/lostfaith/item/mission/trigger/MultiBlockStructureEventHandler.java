package io.naustudio.lostfaith.item.mission.trigger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MultiBlockStructureEventHandler {

    public static final List<Consumer<MultiBlockStructureEvent>> Listeners = new ArrayList<>();

    public static void Add(Consumer<MultiBlockStructureEvent> l) {
        Listeners.add(l);
    }

    public static void Remove(Consumer<MultiBlockStructureEvent> l) {
        Listeners.remove(l);
    }

    public static void FireEvent(MultiBlockStructureEvent event) {
        for (Consumer<MultiBlockStructureEvent> listener : Listeners) {
            listener.accept(event);
        }
    }
}
