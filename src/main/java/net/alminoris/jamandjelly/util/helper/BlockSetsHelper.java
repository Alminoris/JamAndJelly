package net.alminoris.jamandjelly.util.helper;

import net.alminoris.jamandjelly.JamJelly;
import net.alminoris.jamandjelly.block.custom.PlasticBlock;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;

import static net.alminoris.jamandjelly.block.ModBlocks.registerBlock;

public class BlockSetsHelper
{
    public static final Dictionary<String, Integer> WOOD_COLORS = new Hashtable<>()
    {{
        put("acacia", 0x542d19);
        put("alder", 0x56241a);
        put("apple", 0x583e2a);
        put("araucaria", 0x4b330a);
        put("aspen_nss", 0x6c5726);
        put("aspen", 0x6a6354);
        put("azalea", 0x4b372c);
        put("bald_cypress", 0x39392c);
        put("bamboo", 0x605628);
        put("bauhinia", 0x372a20);
        put("birch", 0x60573c);
        put("cedar_nss", 0x4f2f2c);
        put("cedar", 0x463126);
        put("cherry", 0x715956);
        put("coconut_nss", 0x674336);
        put("cottonwood", 0x443d32);
        put("crimson", 0x321823);
        put("cryptomeria", 0x57452e);
        put("cypress_nss", 0x433a2c);
        put("dark_oak", 0x21150a);
        put("fig", 0x5f5041);
        put("fir_nss", 0x2e221e);
        put("fir", 0x452e1a);
        put("ghaf_nss", 0x46301d);
        put("hawthorn", 0x442210);
        put("hazelnut", 0x4b3f2d);
        put("hornbeam", 0x5d5c51);
        put("jungle", 0x503928);
        put("juniper", 0x5c3624);
        put("larch_nss", 0x282b2e);
        put("larch", 0x54473a);
        put("mahogany_nss", 0x371c14);
        put("mango", 0x593c23);
        put("mangrove", 0x3a1b18);
        put("maple_nss", 0x5b3f23);
        put("mountain_hemlock", 0x584531);
        put("oak", 0x514127);
        put("olive_nss", 0x2e2917);
        put("olive", 0x393428);
        put("palo_verde_nss", 0x444117);
        put("pine", 0x574a35);
        put("plum", 0x483337);
        put("poplar", 0x6e6142);
        put("quince", 0x664c35);
        put("redwood_nss", 0x27100e);
        put("saxaul_nss", 0x332b23);
        put("scots_pine", 0x645043);
        put("sequoia", 0x4f2f26);
        put("silverberry", 0x5d5845);
        put("silver_maple", 0x6d5b4c);
        put("spruce", 0x392a18);
        put("staghorn_sumac", 0x603931);
        put("sugi_nss", 0x2f1c15);
        put("swamp_oak", 0x3f3223);
        put("tamarisk", 0x271917);
        put("thuja", 0x3b2722);
        put("trembling_aspen", 0x4a473b);
        put("viburnum", 0x4e3428);
        put("walnut", 0x3e2f22);
        put("warped", 0x153431);
        put("western_serviceberry", 0x473325);
        put("white_mulberry", 0x594c18);
        put("wild_cherry", 0x6f512f);
        put("willow_nss", 0x18120a);
        put("willow", 0x5b5438);
        put("wisteria_nss", 0x564c43);
        put("yew", 0x483218);
    }};

    public static String getKeyByValue(Hashtable<String, Block> table, Block value)
    {
        for (Map.Entry<String, Block> entry : table.entrySet())
        {
            if (entry.getValue().equals(value))
            {
                return entry.getKey();
            }
        }
        return null;
    }

    public static String getWoodName(Dictionary<String, Block> blocks, BlockEntity blockEntity)
    {
        Block block = Objects.requireNonNull(blockEntity.getWorld()).getBlockState(blockEntity.getPos()).getBlock();

        if (getKeyByValue((Hashtable<String, Block>) blocks, block) != null)
            return getKeyByValue((Hashtable<String, Block>) blocks, block);

        return "";
    }

    public static final String[] PLASTIC_BLOCK_NAMES = new String[]
            {
                    "plastic_black", "plastic_brown", "plastic_gray", "plastic_light_gray",
                    "plastic_white", "plastic_red", "plastic_orange", "plastic_yellow",
                    "plastic_purple", "plastic_magenta", "plastic_pink", "plastic_blue",
                    "plastic_cyan", "plastic_light_blue", "plastic_green", "plastic_lime"
            };

    public static Block plastic_block(String name)
    {
        return registerBlock(name, new PlasticBlock());
    }

    public static Block slab(String name)
    {
        Block block = Registries.BLOCK.get(Identifier.of(JamJelly.MOD_ID, name));
        return registerBlock(name+"_slab", new SlabBlock(AbstractBlock.Settings.copy(block)));
    }

    public static Block stairs(String name)
    {
        Block block = Registries.BLOCK.get(Identifier.of(JamJelly.MOD_ID, name));
        return registerBlock(name+"_stairs", new StairsBlock(block.getDefaultState(), AbstractBlock.Settings.copy(block)));
    }

    public static Block button(String name)
    {
        return registerBlock(name+"_button", new ButtonBlock(BlockSetType.OAK, 30, AbstractBlock.Settings.copy(Blocks.OAK_BUTTON)));
    }

    public static Block pressurePlate(String name)
    {
        return registerBlock(name+"_pressure_plate", new PressurePlateBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE)));
    }

    public static Block wall(String name)
    {
        Block block = Registries.BLOCK.get(Identifier.of(JamJelly.MOD_ID, name));
        return registerBlock(name+"_wall", new WallBlock(AbstractBlock.Settings.copy(block)));
    }

    public static final String[] COLORS =
            {
                    "black", "brown", "gray", "light_gray",
                    "white", "red", "orange", "yellow",
                    "purple", "magenta", "pink", "blue",
                    "cyan", "light_blue", "green", "lime"
            };

    public static final String[] STONES =
            {
                    "stone", "tuff", "blackstone", "andesite", "diorite", "granite",  "deepslate", "basalt_side",
                    "quartz_block_bottom", "stone_bricks", "bricks", "mud_bricks", "sandstone"
            };

    public static final String[] EXTRA_STONES_WF =
            {
                    "dolomite_block", "saltmarsh_block", "loessic_marl_block", "loamy_marl_block", "fossil_marlstone_block", "limestone_block"
            };

    public static final String[] WOODS =
            {
                    "oak", "birch", "spruce", "jungle", "acacia", "dark_oak",
                    "crimson", "warped", "mangrove", "cherry", "bamboo"
            };

    public static final String[] EXTRA_WOODS_AN =
            {
                    "hazelnut", "hornbeam", "hawthorn", "quince", "plum", "mango", "fig", "viburnum", "white_mulberry", "wild_cherry",
                    "bauhinia", "pine", "fir", "cedar", "araucaria", "juniper",
                    "bald_cypress", "thuja", "sequoia", "mountain_hemlock", "cryptomeria", "yew", "larch"
            };

    public static final String[] EXTRA_WOODS_WF =
            {
                    "olive", "tamarisk", "western_serviceberry", "trembling_aspen", "cottonwood"
            };

    public static final String[] ST_WOOD_NAMES = new String[] { "walnut", "silver_maple", "staghorn_sumac", "silverberry" };

    public static final String[] WT_WOOD_NAMES = new String[] { "willow", "poplar", "alder", "aspen" };

    public static final String[] MT_WOOD_NAMES = new String[] { "azalea", "apple", "scots_pine", "swamp_oak" };

    public static final String[] NSS_WOOD_NAMES = new String[] { "aspen_nss", "cedar_nss", "coconut_nss", "cypress_nss", "fir_nss", "ghaf_nss",
            "larch_nss", "mahogany_nss", "maple_nss", "olive_nss", "palo_verde_nss", "redwood_nss", "saxaul_nss", "sugi_nss", "willow_nss", "wisteria_nss" };

    public static String[] getWoods()
    {
        String[] combinedWoods = new String[WOODS.length + EXTRA_WOODS_AN.length + EXTRA_WOODS_WF.length+
                ST_WOOD_NAMES.length+ WT_WOOD_NAMES.length+ MT_WOOD_NAMES.length+ NSS_WOOD_NAMES.length];
        System.arraycopy(WOODS, 0, combinedWoods, 0, WOODS.length);
        System.arraycopy(EXTRA_WOODS_AN, 0, combinedWoods, WOODS.length, EXTRA_WOODS_AN.length);
        System.arraycopy(EXTRA_WOODS_WF, 0, combinedWoods, WOODS.length + EXTRA_WOODS_AN.length, EXTRA_WOODS_WF.length);
        System.arraycopy(ST_WOOD_NAMES, 0, combinedWoods, WOODS.length + EXTRA_WOODS_AN.length+EXTRA_WOODS_WF.length, ST_WOOD_NAMES.length);
        System.arraycopy(WT_WOOD_NAMES, 0, combinedWoods, WOODS.length + EXTRA_WOODS_AN.length+EXTRA_WOODS_WF.length
                +ST_WOOD_NAMES.length, WT_WOOD_NAMES.length);
        System.arraycopy(MT_WOOD_NAMES, 0, combinedWoods, WOODS.length + EXTRA_WOODS_AN.length+EXTRA_WOODS_WF.length
                +ST_WOOD_NAMES.length+WT_WOOD_NAMES.length, MT_WOOD_NAMES.length);
        System.arraycopy(NSS_WOOD_NAMES, 0, combinedWoods, WOODS.length + EXTRA_WOODS_AN.length+EXTRA_WOODS_WF.length
                +ST_WOOD_NAMES.length+WT_WOOD_NAMES.length+MT_WOOD_NAMES.length, NSS_WOOD_NAMES.length);
        return combinedWoods;
    }

    public static String[] getStones()
    {
        String[] combinedStones = new String[STONES.length + EXTRA_STONES_WF.length];
        System.arraycopy(STONES, 0, combinedStones, 0, STONES.length);
        System.arraycopy(EXTRA_STONES_WF, 0, combinedStones, STONES.length, EXTRA_STONES_WF.length);
        return combinedStones;
    }

    public static String[] getWoodsNStones()
    {
        String[] combined = new String[getWoods().length + getStones().length];
        System.arraycopy(getWoods(), 0, combined, 0, getWoods().length);
        System.arraycopy(getStones(), 0, combined, getWoods().length, getStones().length);
        return combined;
    }
}
