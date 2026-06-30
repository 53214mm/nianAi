package com.star.test.item;

import com.star.test.registry.ItemRegistrar;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.registry.CompostableRegistry;
import net.fabricmc.fabric.api.registry.FuelValueEvents;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * 普通物品 —— 不能吃、没特效的杂物。
 */
public class ModBasicItems {

    // ── 物品 ──
    public static final Item SUSPICIOUS_SUBSTANCE = ItemRegistrar.register(
            "suspicious_substance",
            Item::new,
            new Item.Properties()
    );

    // ── 初始化 ──
    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
                .register(output -> output.accept(
                        new ItemStack(SUSPICIOUS_SUBSTANCE),
                        CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
                ));
        CompostableRegistry.INSTANCE.add(SUSPICIOUS_SUBSTANCE, 0.3f);
        FuelValueEvents.BUILD.register((builder, context) -> {
            builder.add(SUSPICIOUS_SUBSTANCE, 30 * 20);
        });
    }
}
