package com.star.test.registry;

import com.star.NianAi;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.function.Function;

/**
 * 物品注册工具 —— 整个 Mod 共用这一份。
 * <p>
 * 用法：
 * <pre>
 *   public static final Item XXX = ItemRegistrar.register("xxx", Item::new, new Item.Properties());
 * </pre>
 */
public class ItemRegistrar {

    public static <T extends Item> T register(
            String name,
            Function<Item.Properties, T> itemFactory,
            Item.Properties settings
    ) {
        ResourceKey<Item> itemKey = ResourceKey.create(
                Registries.ITEM,
                Identifier.fromNamespaceAndPath(NianAi.MOD_ID, name)
        );
        T item = itemFactory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }
}
