package io.naustudio.lostfaith.item;

import io.naustudio.lostfaith.LostFaithMod;
import io.naustudio.lostfaith.item.mission.ItemBibleNewTesta;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LFItems {

    public final DeferredItem<SwordItem> CRUCIFIX;
    public final DeferredItem<ItemSilverCrucifix> SILVER_CRUCIFIX;
    public final DeferredItem<ItemBibleOldTesta> BIBLE_OLD_TESTA;
    public final DeferredItem<ItemBibleNewTesta> BIBLE_NEW_TESTA;

    public LFItems() {
        DeferredRegister.Items reg = LostFaithMod.ITEMS;
        CRUCIFIX = reg.register("crucifix", () -> new SwordItem(LFTiers.CRUCIFIX, new Item.Properties()
                .attributes(SwordItem.createAttributes(LFTiers.CRUCIFIX, 4, -2.4F))));
        SILVER_CRUCIFIX = reg.register("silver_crucifix", () -> new ItemSilverCrucifix(new Item.Properties()
                .durability(1200)));
        BIBLE_OLD_TESTA = reg.register("bible_old_testa", () -> new ItemBibleOldTesta(new Item.Properties()));
        BIBLE_NEW_TESTA = reg.register("bible_new_testa", () -> new ItemBibleNewTesta(new Item.Properties()));
    }
}
