package io.naustudio.lostfaith.item;

import io.naustudio.lostfaith.LostFaithMod;
import io.naustudio.lostfaith.block.LFBlocks;
import io.naustudio.lostfaith.item.mission.ItemBibleNewTesta;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LFItems {

    public static final DeferredRegister.Items Registry = DeferredRegister.createItems(LostFaithMod.MODID);

    public static final DeferredItem<SwordItem> Crucifix
            = Registry.register("crucifix", () -> new SwordItem(LFTiers.CRUCIFIX, new Item.Properties()
                    .attributes(SwordItem.createAttributes(LFTiers.CRUCIFIX, 4, -2.4F))));

    public static final DeferredItem<ItemSilverCrucifix> SilverCrucifix
            = Registry.register("silver_crucifix", () -> new ItemSilverCrucifix(new Item.Properties()
                    .durability(1200)));

    public static final DeferredItem<ItemBibleOldTesta> BibleOldTesta
            = Registry.register("bible_old_testa", () -> new ItemBibleOldTesta(new Item.Properties()));

    public static final DeferredItem<ItemBibleNewTesta> BibleNewTesta
            = Registry.register("bible_new_testa", () -> new ItemBibleNewTesta(new Item.Properties()));

    public static final DeferredItem<Item> RomaSilverCoin
            = Registry.register("roma_silver_coin", () -> new BlockItem(LFBlocks.SilverCoinStack.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> SilverFramedGoldBlock
            = Registry.register("silver_framed_gold_block", () -> new BlockItem(LFBlocks.SilverFramedGoldBlock.get(), new Item.Properties()));
}
