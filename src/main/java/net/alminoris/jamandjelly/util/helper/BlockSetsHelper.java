package net.alminoris.jamandjelly.util.helper;

import net.alminoris.jamandjelly.JamJelly;
import net.alminoris.jamandjelly.block.custom.PlasticBlock;
import net.minecraft.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import static net.alminoris.jamandjelly.block.ModBlocks.registerBlock;

public class BlockSetsHelper
{
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
}
