package io.naustudio.lostland.item;

import io.naustudio.lostland.gui.screen.story.StoryData;
import io.naustudio.lostland.gui.screen.story.StoryScreen;
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

    private List<StoryData> BeginningStory = new ArrayList<>();

    public CrystalBallItem(Properties properties) {
        super(properties);

        BeginningStory.add(new StoryData(
                Component.translatable("scn.lostland.beginning.0"),
                new Vector2f(0.5f, 0.5f),
                new Vector2f(0, 0),
                new Vector2f(0.5f, 0.5f),
                255,
                255,
                255,
                0,
                80,
                20));

        BeginningStory.add(new StoryData(
                Component.translatable("scn.lostland.beginning.1"),
                new Vector2f(0.5f, 0.5f),
                new Vector2f(64, 16),
                new Vector2f(0.5f, 0.5f),
                0,
                255,
                255,
                50,
                80,
                40));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack item = player.getItemInHand(usedHand);

        if (level.isClientSide)
            if (FMLEnvironment.dist.isClient()) {
                StoryScreen screen = new StoryScreen(BeginningStory, 20);
                Minecraft.getInstance().setScreen(screen);
            }

        return InteractionResultHolder.success(item);
    }
}
