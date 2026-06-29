package com.star.test;

import com.star.NianAi;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.registry.CompostableRegistry;
import net.fabricmc.fabric.api.registry.FuelValueEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Function;

/**
 * 我们的物品注册中心 —— 相当于游戏里所有自定义物品的"户口本"。
 */
public class ModItems {

    /**
     * 万能注册函数：传入名字 → 返回一个注册好的物品。
     *
     * @param name         物品的 ID（如 "suspicious_substance"），
     *                     最终会变成 "nianai:suspicious_substance"
     * @param itemFactory  怎么创建这个物品（通常是 Item::new）
     * @param settings     物品的属性（堆叠数量、能否吃、耐久度等）
     * @param <T>          泛型 —— 不限于 Item，也可以是它的子类
     *                     比如 SwordItem、BlockItem 等
     * @return 注册好的物品实例
     */
    public static <T extends Item> T register(
            String name,
            Function<Item.Properties, T> itemFactory,
            Item.Properties settings
    ) {
        // ① 创建"资源的钥匙" —— 格式是 "nianai:物品名"
        ResourceKey<Item> itemKey = ResourceKey.create(
                Registries.ITEM,
                Identifier.fromNamespaceAndPath(NianAi.MOD_ID, name)
        );

        // ② 把钥匙塞进属性里，然后用工厂函数创建物品实例
        T item = itemFactory.apply(settings.setId(itemKey));

        // ③ 在游戏注册表里"登记上户口"
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }

    // ──────────────── 注册具体物品 ────────────────

    /**
     * SUSPICIOUS_SUBSTANCE = 我们的第一个自定义物品！
     * <p>
     * new Item.Properties() —— 空属性，意味着：
     *   - 堆叠上限 64 个（默认）
     *   - 不能吃 / 没有耐久度
     *   - 就是最普通的物品
     */
    public static final Item SUSPICIOUS_SUBSTANCE = register(
            "suspicious_substance",
            Item::new,
            new Item.Properties()
    );

    //自定义食物
    public static final Item SUSPICIOUS_FOOD = register(
            "suspicious_food",       // ← ID 改为和文件名一致
            Item::new,
            new Item.Properties().food(new FoodProperties.Builder().build())
    );




    // ──────────────── 静态初始化触发器 ────────────────

    /**
     * 第一次访问这个类时触发所有 static 字段初始化（即物品注册）。
     * 这个方法在 NianAi.onInitialize() 中被调用。
     */
    public static void initialize() {
        initializeSuspiciousSubstance();
        initializeSuspiciousFood();
    }

    //SUSPICIOUS_SUBSTANCE初始化
    public static void initializeSuspiciousSubstance(){
        // 把物品添加到创造模式的"材料"标签页
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
                .register(output -> output.accept(
                        new ItemStack(SUSPICIOUS_SUBSTANCE),
                        CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
                ));
        // 让 SUSPICIOUS_SUBSTANCE 可以被堆肥机处理，堆肥机的成功率是 30%
        CompostableRegistry.INSTANCE.add(SUSPICIOUS_SUBSTANCE, 0.3f);
        // 让 SUSPICIOUS_SUBSTANCE 可以作为燃料，燃烧时间是 30 秒（30 * 20 tick）
        FuelValueEvents.BUILD.register((builder, context) -> {
            builder.add(SUSPICIOUS_SUBSTANCE, 30 * 20);
        });
    }

    //SUSPICIOUS_FOOD初始化
    public static void initializeSuspiciousFood(){
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
                .register(output -> output.accept(
                        new ItemStack(SUSPICIOUS_FOOD),
                        CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
                ));
    }
}
