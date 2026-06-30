package com.star.test.item;

import com.star.NianAi;
import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;

/**
 * 自定义药水。
 * <p>
 * 药水本身只是一个"效果配方"（什么效果 + 什么颜色），
 * 真正喝下去的是药水瓶、喷溅药水、滞留药水这些 vanilla 物品。
 */
public class ModPotions {


    public static final Holder<Potion> POISON_SPEED =
            Registry.registerForHolder(
                    BuiltInRegistries.POTION,
                    Identifier.fromNamespaceAndPath(NianAi.MOD_ID, "poison_speed"),
                    new Potion("poison_speed",
                            new MobEffectInstance(MobEffects.POISON,  6 * 20, 0),  // 中毒 I, 6秒
                            new MobEffectInstance(MobEffects.SPEED, 6 * 20, 1)  // 速度 II, 6秒
                    )
            );

    // ── 酿造配方 ──
    // PotionBrewing.addMix(原料药水, 添加物, 产物药水)
    public static void initialize() {
        // 粗制药水 + 糖 → 毒速药水
        FabricPotionBrewingBuilder.BUILD.register(builder -> {
            builder.addMix(
                    Potions.AWKWARD,    // 原料：粗制药水
                    net.minecraft.world.item.Items.SUGAR,  // 添加物：糖
                    POISON_SPEED        // 产物：我们的自定义药水
            );
        });
    }
}
