package io.naustudio.lostfaith.item.mission;

import net.minecraft.network.chat.Component;
import java.util.Objects;

public class MetaData {

    public Component Owner;
    public boolean Finished;

    public MetaData(Component owner, boolean finished) {
        Owner = owner;
        Finished = finished;
    }

    public MetaData(MetaRecord rec) {
        if (rec != null)
        {
            Owner = rec.owner();
            Finished = rec.finished();
        }
        else
        {
            Owner = null;
            Finished = false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(Owner, Finished);
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || (obj instanceof MetaData && obj.hashCode() == hashCode());
    }
}
