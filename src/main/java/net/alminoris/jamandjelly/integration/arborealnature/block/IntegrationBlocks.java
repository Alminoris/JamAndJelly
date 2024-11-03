package net.alminoris.jamandjelly.integration.arborealnature.block;

import net.alminoris.jamandjelly.JamJelly;
import net.alminoris.jamandjelly.block.custom.ChoppingBoardBlock;
import net.alminoris.jamandjelly.integration.arborealnature.item.IntegrationFoodComponents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.HoneyBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Dictionary;
import java.util.Hashtable;

import static net.alminoris.jamandjelly.integration.arborealnature.item.IntegrationItems.JAM_NAMES;

public class IntegrationBlocks
{
    public static final String[] WOOD_NAMES = { "hazelnut", "hornbeam", "hawthorn", "quince", "plum", "mango" };
    public static final Dictionary<String, Block> CHOPPING_BOARDS = new Hashtable<>();

    public static final Dictionary<String, Block> JAM_BLOCKS = new Hashtable<>();

    static
    {
        for(String name : WOOD_NAMES)
        {
            CHOPPING_BOARDS.put(name, registerBlock("chopping_board_"+name, new ChoppingBoardBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD).nonOpaque())));
        }

        for(String name : JAM_NAMES)
        {
            JAM_BLOCKS.put(name, registerBlock(name+"_jam_block", new HoneyBlock(AbstractBlock.Settings.copy(Blocks.HONEY_BLOCK))));
        }
    }

    private static Block registerBlock(String name, Block block)
    {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(JamJelly.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block)
    {
        Registry.register(Registries.ITEM, Identifier.of(JamJelly.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerBlocks()
    {
        JamJelly.LOGGER.info("Arboreal Nature is present");
    }
}
