package com.star.test.item;

import com.star.test.registry.ItemRegistrar;
import net.minecraft.world.item.Item;

/**
 * 物品注册调度中心。
 * <p>
 * 只管两件事：
 * 1. 持有公用的 {@link ItemRegistrar}（需要时暴露给外部）
 * 2. 在 initialize() 中调度所有子类的初始化
 */
public class ModItems {

    /**
     * 总入口 —— NianAi.onInitialize() 调这里。
     * 以后每加一个物品类别，就在这加一行。
     */
    public static void initialize() {
        ModBasicItems.initialize();
        ModFoodItems.initialize();
    }
}
