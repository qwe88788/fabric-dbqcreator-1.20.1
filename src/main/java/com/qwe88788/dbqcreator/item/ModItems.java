package com.qwe88788.dbqcreator.item;

import com.qwe88788.dbqcreator.DBQCreator;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    private static Item registerItem(String name,Item item){
        return Registry.register(Registries.ITEM,new Identifier(DBQCreator.MOD_ID,name),item);
    }

    public static void registerModItems() {
        DBQCreator.LOGGER.info("Registering Mod Items for " + DBQCreator.MOD_ID);
    }
}
