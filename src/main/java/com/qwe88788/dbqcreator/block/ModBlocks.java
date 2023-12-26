package com.qwe88788.dbqcreator.block;

import com.qwe88788.dbqcreator.DBQCreator;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {


    /**
     * 注册秘银块
     */
    public static final Block MITHRIL_BLOCK = registerBlock("mithril_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    /**
     * 注册秘银矿石
     */
    public static final Block MITHRIL_ORE = registerBlock("mithril_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE).strength(2f), UniformIntProvider.create(2,5)));


    //public static final Block DEEPSLATE_MITHRIL_ORE = registerBlock("deepslate_mithril_ore,
            //new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE).strength(3f), UniformIntProvider.create(2,5)));
    //public static final Block NETHER_MITHRIL_ORE = registerBlock("nether_mithril_ore",
            //new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.NETHERRACK).strength(1.5f), UniformIntProvider.create(2,5)));
    //public static final Block END_MITHRIL_ORE = registerBlock("end_mithril_ore",
            //new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.END_STONE).strength(3f), UniformIntProvider.create(2,5)));

    /**
     * 用于注册方块
     * @param name 方块名称
     * @param block 方块对象
     * @return 注册后的方块对象
     */
    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK,new Identifier(DBQCreator.MOD_ID,name), block);
    }


    /**
     * 为方块注册一个方块物品
     *
     * @param name 方块物品名称
     * @param block 方块
     * @return 注册的方块物品
     */
    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM,new Identifier(DBQCreator.MOD_ID,name),
                new BlockItem(block,new FabricItemSettings()));
    }


    /**
     * 注册模组方块
     */
    public static void registerModBlocks() {
        DBQCreator.LOGGER.info("Registering Mod Blocks for " + DBQCreator.MOD_ID);
    }

}