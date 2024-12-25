package net.alminoris.jamandjelly.block;

import net.alminoris.jamandjelly.JamJelly;
import net.alminoris.jamandjelly.block.custom.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.HoneyBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Dictionary;
import java.util.Hashtable;

import static net.alminoris.jamandjelly.util.helper.BlockSetsHelper.*;

public class ModBlocks
{
    public static final Block JAR_RED =  registerBlock("jar_red", new JarBlock());
    public static final Block JAR_ORANGE =  registerBlock("jar_orange", new JarBlock());
    public static final Block JAR_YELLOW =  registerBlock("jar_yellow", new JarBlock());
    public static final Block JAR_BLUE =  registerBlock("jar_blue", new JarBlock());
    public static final Block JAR_LIGHT_BLUE =  registerBlock("jar_light_blue", new JarBlock());
    public static final Block JAR_GREEN =  registerBlock("jar_green", new JarBlock());
    public static final Block JAR_LIME =  registerBlock("jar_lime", new JarBlock());
    public static final Block JAR_BLACK =  registerBlock("jar_black", new JarBlock());
    public static final Block JAR_GRAY =  registerBlock("jar_gray", new JarBlock());
    public static final Block JAR_LIGHT_GRAY =  registerBlock("jar_light_gray", new JarBlock());
    public static final Block JAR_WHITE =  registerBlock("jar_white", new JarBlock());
    public static final Block JAR_CYAN =  registerBlock("jar_cyan", new JarBlock());
    public static final Block JAR_BROWN =  registerBlock("jar_brown", new JarBlock());
    public static final Block JAR_PURPLE =  registerBlock("jar_purple", new JarBlock());
    public static final Block JAR_MAGENTA =  registerBlock("jar_magenta", new JarBlock());
    public static final Block JAR_PINK =  registerBlock("jar_pink", new JarBlock());

    public static final Block APPLE_JAM_BLOCK = registerBlock("apple_jam_block", new JamBlock());
    public static final Block SWEETBERRY_JAM_BLOCK = registerBlock("sweetberry_jam_block", new JamBlock());
    public static final Block MELON_JAM_BLOCK = registerBlock("melon_jam_block", new JamBlock());

    public static final Block APPLE_JELLY_BLOCK = registerBlock("apple_jelly_block", new JellyBlock(AbstractBlock.Settings.copy(Blocks.SLIME_BLOCK)));
    public static final Block SWEETBERRY_JELLY_BLOCK = registerBlock("sweetberry_jelly_block", new JellyBlock(AbstractBlock.Settings.copy(Blocks.SLIME_BLOCK)));
    public static final Block MELON_JELLY_BLOCK = registerBlock("melon_jelly_block", new JellyBlock(AbstractBlock.Settings.copy(Blocks.SLIME_BLOCK)));

    public static final Block KELP_BLOCK = registerBlock("kelp_block", new Block(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));

    public static final Block JAMMING_POT = registerBlock("jamming_pot", new JammingPotBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).nonOpaque()));

    public static final Block JUICER = registerBlock("juicer", new JuicerBlock());

    public static final Block CHOPPING_BOARD_OAK = registerBlock("chopping_board_oak", new ChoppingBoardBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD).nonOpaque()));
    public static final Block CHOPPING_BOARD_BIRCH = registerBlock("chopping_board_birch", new ChoppingBoardBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD).nonOpaque()));
    public static final Block CHOPPING_BOARD_SPRUCE = registerBlock("chopping_board_spruce", new ChoppingBoardBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD).nonOpaque()));
    public static final Block CHOPPING_BOARD_JUNGLE = registerBlock("chopping_board_jungle", new ChoppingBoardBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD).nonOpaque()));
    public static final Block CHOPPING_BOARD_ACACIA = registerBlock("chopping_board_acacia", new ChoppingBoardBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD).nonOpaque()));
    public static final Block CHOPPING_BOARD_DARK_OAK = registerBlock("chopping_board_dark_oak", new ChoppingBoardBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD).nonOpaque()));
    public static final Block CHOPPING_BOARD_CRIMSON = registerBlock("chopping_board_crimson", new ChoppingBoardBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD).nonOpaque()));
    public static final Block CHOPPING_BOARD_WARPED = registerBlock("chopping_board_warped", new ChoppingBoardBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD).nonOpaque()));
    public static final Block CHOPPING_BOARD_MANGROVE = registerBlock("chopping_board_mangrove", new ChoppingBoardBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD).nonOpaque()));
    public static final Block CHOPPING_BOARD_CHERRY = registerBlock("chopping_board_cherry", new ChoppingBoardBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD).nonOpaque()));
    public static final Block CHOPPING_BOARD_BAMBOO = registerBlock("chopping_board_bamboo", new ChoppingBoardBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD).nonOpaque()));

    public static final Dictionary<String, Block> PLASTIC_BLOCKS = new Hashtable<>()
    {{
        for(String name : PLASTIC_BLOCK_NAMES)
            put(name, plastic_block(name));
    }};

    public static final Dictionary<String, Block> SLABS = new Hashtable<>()
    {{
        for(String name : PLASTIC_BLOCK_NAMES)
            put(name, slab(name));
    }};

    public static final Dictionary<String, Block> STAIRS = new Hashtable<>()
    {{
        for(String name : PLASTIC_BLOCK_NAMES)
            put(name, stairs(name));
    }};

    public static final Dictionary<String, Block> BUTTONS = new Hashtable<>()
    {{
        for(String name : PLASTIC_BLOCK_NAMES)
            put(name, button(name));
    }};

    public static final Dictionary<String, Block> PRESSURE_PLATES = new Hashtable<>()
    {{
        for(String name : PLASTIC_BLOCK_NAMES)
            put(name, pressurePlate(name));
    }};

    public static final Dictionary<String, Block> WALLS = new Hashtable<>()
    {{
        for(String name : PLASTIC_BLOCK_NAMES)
            put(name, wall(name));
    }};

    public static Block registerBlock(String name, Block block)
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

    }
}
