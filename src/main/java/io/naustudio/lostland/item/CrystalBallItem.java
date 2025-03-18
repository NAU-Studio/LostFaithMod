package io.naustudio.lostland.item;

import io.naustudio.lostland.gui.screen.story.*;
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
                10,
                Component.translatable("scn.lostland.beginning.0"),
                new Vector2f(0.5f, 0.5f),
                new Vector2f(0, -6),
                new Vector2f(0.5f, 0.5f),
                255,
                255,
                255));

        BeginningStory.add(new StoryTextItem(
                30,
                20,
                10,
                0,
                Component.translatable("scn.lostland.beginning.1"),
                new Vector2f(0.5f, 0.5f),
                new Vector2f(0, 6),
                new Vector2f(0.5f, 0.5f),
                255,
                255,
                255));

        BeginningStory.add(new StoryTextItem(
                50,
                20,
                0,
                0,
                Component.translatable("scn.lostland.beginning.2"),
                new Vector2f(0.5f, 0.5f),
                new Vector2f(0, 6),
                new Vector2f(0.5f, 0.5f),
                255,
                255,
                255));

        BeginningStory.add(new StoryTextItem(
                70,
                20,
                0,
                10,
                Component.translatable("scn.lostland.beginning.3"),
                new Vector2f(0.5f, 0.5f),
                new Vector2f(0, 6),
                new Vector2f(0.5f, 0.5f),
                255,
                255,
                255));

        BeginningStory.add(new ChangeEscapeItem(90, false));

        BeginningStory.add(new TypeWriterTextItem(
                120,
                830,
                10,
                Component.translatable("scn.lostland.format.date", 1815, 6, 1),
                new Vector2f(0, 0),
                new Vector2f(6, 6),
                new Vector2f(0, 0),
                255,
                255,
                255));

        BeginningStory.add(new TypeWriterTextItem(
                140,
                810,
                10,
                Component.translatable("scn.lostland.format.weather.clear"),
                new Vector2f(0, 0),
                new Vector2f(102, 6),
                new Vector2f(0, 0),
                255,
                255,
                255));

        BeginningStory.add(new TypeWriterTextItem(
                170,
                60,
                10,
                Component.translatable("scn.lostland.beginning.4"),
                new Vector2f(0.5f, 0.5f),
                new Vector2f(0, 0),
                new Vector2f(0.5f, 0.5f),
                255,
                255,
                255));

        BeginningStory.add(new TypeWriterTextItem(
                250,
                160,
                10,
                Component.translatable("scn.lostland.beginning.5"),
                new Vector2f(0.5f, 0.5f),
                new Vector2f(0, -6),
                new Vector2f(0.5f, 0.5f),
                255,
                255,
                255));

        BeginningStory.add(new TypeWriterTextItem(
                330,
                80,
                10,
                Component.translatable("scn.lostland.beginning.6"),
                new Vector2f(0.5f, 0.5f),
                new Vector2f(0, 6),
                new Vector2f(0.5f, 0.5f),
                255,
                255,
                255));

        BeginningStory.add(new TypeWriterTextItem(
                430,
                160,
                10,
                Component.translatable("scn.lostland.beginning.7"),
                new Vector2f(0.5f, 0.5f),
                new Vector2f(0, -6),
                new Vector2f(0.5f, 0.5f),
                255,
                255,
                255));

        BeginningStory.add(new TypeWriterTextItem(
                510,
                80,
                10,
                Component.translatable("scn.lostland.beginning.8"),
                new Vector2f(0.5f, 0.5f),
                new Vector2f(0, 6),
                new Vector2f(0.5f, 0.5f),
                255,
                255,
                255));

        BeginningStory.add(new TypeWriterTextItem(
                610,
                160,
                10,
                Component.translatable("scn.lostland.beginning.9"),
                new Vector2f(0.5f, 0.5f),
                new Vector2f(0, -6),
                new Vector2f(0.5f, 0.5f),
                255,
                255,
                255));

        BeginningStory.add(new TypeWriterTextItem(
                690,
                80,
                10,
                Component.translatable("scn.lostland.beginning.10"),
                new Vector2f(0.5f, 0.5f),
                new Vector2f(0, 6),
                new Vector2f(0.5f, 0.5f),
                255,
                255,
                255));

        BeginningStory.add(new TypeWriterTextItem(
                790,
                160,
                10,
                Component.translatable("scn.lostland.beginning.11"),
                new Vector2f(0.5f, 0.5f),
                new Vector2f(0, -6),
                new Vector2f(0.5f, 0.5f),
                255,
                255,
                255));

        BeginningStory.add(new TypeWriterTextItem(
                870,
                80,
                10,
                Component.translatable("scn.lostland.beginning.12"),
                new Vector2f(0.5f, 0.5f),
                new Vector2f(0, 6),
                new Vector2f(0.5f, 0.5f),
                255,
                255,
                255));
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
