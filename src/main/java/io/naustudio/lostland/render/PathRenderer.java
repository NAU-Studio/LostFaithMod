package io.naustudio.lostland.render;

import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class PathRenderer {

    /* TODO: 存储寻路数据，并每一秒：
     *       判断玩家是否移动
     *       是：
     *           重新计算路径
     *       否：
     *           返回
     */

    public static void Render()
    {
        /* TODO: 渲染路径
         *       每一格正上方渲染一个类似箭头的平面，指向下一块目标路径
         *       可以添加：脉冲效果
         *                 HUD图标（位于目标点）
         */
    }

    private static int _ticks = 0;
    private static Vec3 _recentPos = new Vec3(0, 0, 0);

    @SubscribeEvent
    public static void ClientTick(ClientTickEvent e)
    {
        _ticks++;
        if (_ticks >= 20)
            _ticks -= 20;

        if (Minecraft.getInstance().player == null)
            return;
        Vec3 pos = Minecraft.getInstance().player.position();
        if (_recentPos != pos)
        {
            // TODO: 重新计算路径
        }
    }
}
