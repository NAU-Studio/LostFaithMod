package io.naustudio.lostfaith.util;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class InventoryUtils {

    public static int Count(Inventory inv, ItemStack item) {
        int count = 0;
        for (ItemStack i : inv.items) {
            if (i.is(item.getItem()))
                count += i.getCount();
        }
        return count;
    }
}
