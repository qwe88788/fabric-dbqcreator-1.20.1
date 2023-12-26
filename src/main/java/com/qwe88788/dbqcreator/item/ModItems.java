package com.qwe88788.dbqcreator.item;

import com.qwe88788.dbqcreator.DBQCreator;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.impl.itemgroup.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {

    /**
     * 注册秘银和粗秘银
     */
    public static final Item MITHRIL = registerItem("mithril",new Item(new FabricItemSettings()));
    public static final Item RAW_MITHRIL = registerItem("raw_mithril",new Item(new FabricItemSettings()));
    public static final Item CAGE_ITEM = registerItem("cage_item",new Item(new FabricItemSettings()));
    /**
     * 将秘银和粗秘银添加到选项卡
     */
    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(MITHRIL);
        entries.add(RAW_MITHRIL);
    }

    /**
     * 注册物品
     */
    private static Item registerItem(String name,Item item){
        return Registry.register(Registries.ITEM,new Identifier(DBQCreator.MOD_ID,name),item);
    }

    /**
     * 注册mod物品
     */
    public static void registerModItems() {
        DBQCreator.LOGGER.info("Registering Mod Items for " + DBQCreator.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }

}
