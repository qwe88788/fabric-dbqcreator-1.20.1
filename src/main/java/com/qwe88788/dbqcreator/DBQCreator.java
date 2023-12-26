package com.qwe88788.dbqcreator;

import com.qwe88788.dbqcreator.block.ModBlocks;
import com.qwe88788.dbqcreator.item.ModItemGroups;
import com.qwe88788.dbqcreator.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBQCreator implements ModInitializer {
    /**
     * MOD的唯一标识符
     */
    public static final String MOD_ID = "dbqcreator";
    /**
     * LOGGER用于记录MOD的日志信息
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	/**
	 * 初始化方法，用于注册物品和方块
	 */
	@Override
	public void onInitialize() {
	    ModItemGroups.registerItemGroups();
	    ModItems.registerModItems();
	    ModBlocks.registerModBlocks();
	}

}