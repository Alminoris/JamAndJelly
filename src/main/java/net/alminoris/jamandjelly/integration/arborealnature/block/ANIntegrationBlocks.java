package net.alminoris.jamandjelly.integration.arborealnature.block;

import net.alminoris.jamandjelly.JamJelly;
import net.alminoris.jamandjelly.block.custom.ChoppingBoardBlock;
import net.alminoris.jamandjelly.block.custom.JamBlock;
import net.alminoris.jamandjelly.block.custom.JellyBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Dictionary;
import java.util.Hashtable;

import static net.alminoris.jamandjelly.integration.arborealnature.item.ANIntegrationItems.AN_JAM_NAMES;

public class ANIntegrationBlocks
{
    public static final Dictionary<String, Block> AN_JAM_BLOCKS = new Hashtable<>();
    public static final Dictionary<String, Block> AN_JELLY_BLOCKS = new Hashtable<>();

    static
    {
        for(String name : AN_JAM_NAMES)
        {
            AN_JAM_BLOCKS.put(name, registerBlock(name+"_jam_block", new JamBlock()));
            AN_JELLY_BLOCKS.put(name, registerBlock(name+"_jelly_block", new JellyBlock(AbstractBlock.Settings.copy(Blocks.SLIME_BLOCK))));
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
