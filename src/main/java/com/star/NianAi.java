package com.star;

import com.star.test.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NianAi implements ModInitializer {
	//组件ID
	public static final String MOD_ID = "nianai";
	//日志工具
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// ★ 触发 ModItems 类的静态初始化 →
		//    所有 static 字段（即物品）开始注册
		ModItems.initialize();

		LOGGER.info("NianAi mod initialized!");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
