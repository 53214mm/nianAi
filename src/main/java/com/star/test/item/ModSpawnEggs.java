package com.star.test.item;

import com.star.test.registry.ItemRegistrar;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.TypedEntityData;

/**
 * 自定义刷怪蛋。
 */
public class ModSpawnEggs {

    // ── 物品 ──
    // MC 1.21.2+: 实体类型通过 DataComponents.ENTITY_DATA 组件绑定，
    // 而不是 SpawnEggItem 构造函数参数
    public static final Item MINI_GOLEM_SPAWN_EGG = ItemRegistrar.register(
            "mini_golem_spawn_egg",
            SpawnEggItem::new,
            new Item.Properties()
                    .component(DataComponents.ENTITY_DATA,
                            TypedEntityData.of(EntityTypes.IRON_GOLEM, new CompoundTag()))
    );

    // ── 初始化 ──
    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.SPAWN_EGGS)
                .register(output -> output.accept(
                        new ItemStack(MINI_GOLEM_SPAWN_EGG),
                        CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
                ));
    }
}
