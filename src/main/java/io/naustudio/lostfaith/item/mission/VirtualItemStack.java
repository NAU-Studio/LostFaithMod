package io.naustudio.lostfaith.item.mission;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.TypedDataComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class VirtualItemStack {

    public Item Type;

    public int Count;

    public DataComponentMap Datas;

    public VirtualItemStack(Item type, int count, DataComponentMap map) {
        Type = type;
        Count = count;
        Datas = map;
    }

    public boolean Equals(ItemStack item) {
        return item.getCount() >= Count && EqualsNoCountDetection(item);
    }

    public boolean Equals(ItemStack[] items) {
        int matchedCount = 0;
        for (ItemStack item : items) {
            if (EqualsNoCountDetection(item))
                matchedCount += item.getCount();
        }
        return matchedCount >= Count;
    }

    protected boolean EqualsNoCountDetection(ItemStack item) {
        return item.is(Type) && DataEquals(item.getComponents());
    }

    protected boolean DataEquals(DataComponentMap map) {
        if (map.size() != Datas.size())
            return false;
        if (Datas.isEmpty())
            return true;
        for (TypedDataComponent<?> i : map) {
            Object a = map.get(i.type());
            if (a != i.value())
                return false;
        }
        return true;
    }
}
