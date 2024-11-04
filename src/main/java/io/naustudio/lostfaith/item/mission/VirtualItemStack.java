package io.naustudio.lostfaith.item.mission;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.TypedDataComponent;
import net.minecraft.world.entity.player.Inventory;
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

    public boolean Equals(Inventory items) {
        return Count(items) >= Count;
    }

    public int Count(Inventory items) {
        int matchedCount = 0;
        for (int i = 0; i < items.getContainerSize(); i++) {
            ItemStack item = items.getItem(i);
            if (EqualsNoCountDetection(item))
                matchedCount += item.getCount();
        }
        return matchedCount;
    }

    protected boolean EqualsNoCountDetection(ItemStack item) {
        return item.is(Type) && DataEquals(item.getComponents());
    }

    protected boolean DataEquals(DataComponentMap map) {
        if (Datas == null)
            return true;
        if (map.size() != Datas.size())
            return false;
        for (TypedDataComponent<?> i : map) {
            Object a = map.get(i.type());
            if (a != i.value())
                return false;
        }
        return true;
    }
}
