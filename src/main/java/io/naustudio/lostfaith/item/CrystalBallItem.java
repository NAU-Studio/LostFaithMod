package io.naustudio.lostfaith.item;

import io.naustudio.lostfaith.gui.screen.story.ChangeEscapeItem;
import io.naustudio.lostfaith.gui.screen.story.StoryItem;
import io.naustudio.lostfaith.gui.screen.story.StoryTextItem;
import io.naustudio.lostfaith.gui.screen.story.StoryScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.fml.loading.FMLEnvironment;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class CrystalBallItem extends Item {

    private List<StoryItem> BeginningStory = new ArrayList<>();

    public CrystalBallItem(Properties properties) {
        super(properties);

        BeginningStory.add(new ChangeEscapeItem(0, true));

        BeginningStory.add(new StoryTextItem(
                10,
                80,
                10,
                Component.translatable("scn.lostfaith.beginning.0"),
                new Vector2f(0.5f, 0.5f),
                new Vector2f(0, 0),
                new Vector2f(0.5f, 0.5f),
                255,
                255,
                255));

        BeginningStory.add(new StoryTextItem(
                30,
                20,
                0,
                Component.translatable("scn.lostfaith.beginning.1"),
                new Vector2f(0.5f, 0.5f),
                new Vector2f(0, 12),
                new Vector2f(0.5f, 0.5f),
                255,
                255,
                255));

        BeginningStory.add(new StoryTextItem(
                50,
                20,
                0,
                Component.translatable("scn.lostfaith.beginning.2"),
                new Vector2f(0.5f, 0.5f),
                new Vector2f(0, 12),
                new Vector2f(0.5f, 0.5f),
                255,
                255,
                255));

        BeginningStory.add(new StoryTextItem(
                70,
                20,
                0,
                Component.translatable("scn.lostfaith.beginning.3"),
                new Vector2f(0.5f, 0.5f),
                new Vector2f(0, 12),
                new Vector2f(0.5f, 0.5f),
                255,
                255,
                255));

        BeginningStory.add(new ChangeEscapeItem(10, false));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack item = player.getItemInHand(usedHand);

        if (level.isClientSide)
            if (FMLEnvironment.dist.isClient()) {
                StoryScreen screen = new StoryScreen(BeginningStory, 5);
                Minecraft.getInstance().setScreen(screen);
            }

        return InteractionResultHolder.success(item);
    }
}
