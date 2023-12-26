package com.qwe88788.dbqcreator.item;

import com.qwe88788.dbqcreator.DBQCreator;
import com.qwe88788.dbqcreator.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ModItemGroups {
    /**
     * 注册物品组DBQ_GROUP
     */
    public static final ItemGroup DBQ_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(DBQCreator.MOD_ID,"dbqcreator"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.dbqcreator"))
                    .icon(() -> new ItemStack(ModItems.RAW_MITHRIL)).entries((displayContext, entries) -> {
                        entries.add(ModItems.MITHRIL);
                        entries.add(ModItems.RAW_MITHRIL);
                        entries.add(ModItems.CAGE_ITEM);

                        entries.add(ModBlocks.MITHRIL_BLOCK);
                        entries.add(ModBlocks.MITHRIL_ORE );

                    }).build());

    /**
     * 注册物品组方法
     */
    public static void registerItemGroups() {
        DBQCreator.LOGGER.info("Registering Item Groups for " + DBQCreator.MOD_ID);
    }

}
