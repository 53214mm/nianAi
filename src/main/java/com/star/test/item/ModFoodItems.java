package com.star.test.item;

import com.star.test.registry.ItemRegistrar;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

/**
 * 食物类物品。
 */
public class ModFoodItems {

    // ── 食物属性 ──
    public static final Consumable POISON_FOOD_CONSUMABLE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(
                    new MobEffectInstance(MobEffects.POISON, 6 * 20, 1), 1.0f
            ))
            .onConsume(new ApplyStatusEffectsConsumeEffect(
                    new MobEffectInstance(MobEffects.SPEED, 6 * 20, 3), 1.0f
            ))
            .build();

    public static final FoodProperties POISON_FOOD_PROPERTIES = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.3f)
            .alwaysEdible()
            .build();

    // ── 物品 ──
    public static final Item SUSPICIOUS_FOOD = ItemRegistrar.register(
            "suspicious_food",
            Item::new,
            new Item.Properties().food(POISON_FOOD_PROPERTIES, POISON_FOOD_CONSUMABLE)
    );

    // ── 初始化 ──
    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
                .register(output -> output.accept(
                        new ItemStack(SUSPICIOUS_FOOD),
                        CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
                ));
    }
}
