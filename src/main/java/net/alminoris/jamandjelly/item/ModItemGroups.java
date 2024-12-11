package net.alminoris.jamandjelly.item;

import net.alminoris.jamandjelly.JamJelly;
import net.alminoris.jamandjelly.block.ModBlocks;
import net.alminoris.jamandjelly.integration.arborealnature.block.IntegrationBlocks;
import net.alminoris.jamandjelly.integration.arborealnature.item.IntegrationItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.alminoris.jamandjelly.integration.arborealnature.block.IntegrationBlocks.WOOD_NAMES;
import static net.alminoris.jamandjelly.integration.arborealnature.item.IntegrationItems.JAM_NAMES;
import static net.alminoris.jamandjelly.util.helper.BlockSetsHelper.PLASTIC_BLOCK_NAMES;

public class ModItemGroups
{
    public static final ItemGroup JAMANDJELLYTAB = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(JamJelly.MOD_ID, "jajtab"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.jajtab"))
                    .icon(() -> new ItemStack(Items.SWEET_BERRIES)).entries((displayContext, entries) ->
                    {
                        entries.add(ModBlocks.JAMMING_POT);
                        entries.add(ModBlocks.JUICER);
                        entries.add(ModBlocks.CHOPPING_BOARD_OAK);
                        entries.add(ModBlocks.CHOPPING_BOARD_BIRCH);
                        entries.add(ModBlocks.CHOPPING_BOARD_SPRUCE);
                        entries.add(ModBlocks.CHOPPING_BOARD_JUNGLE);
                        entries.add(ModBlocks.CHOPPING_BOARD_ACACIA);
                        entries.add(ModBlocks.CHOPPING_BOARD_DARK_OAK);
                        entries.add(ModBlocks.CHOPPING_BOARD_CRIMSON);
                        entries.add(ModBlocks.CHOPPING_BOARD_WARPED);
                        entries.add(ModBlocks.CHOPPING_BOARD_MANGROVE);
                        entries.add(ModBlocks.CHOPPING_BOARD_CHERRY);
                        entries.add(ModBlocks.CHOPPING_BOARD_BAMBOO);
                        if (FabricLoader.getInstance().isModLoaded("arborealnature"))
                        {
                            for(String name : WOOD_NAMES)
                                entries.add(IntegrationBlocks.CHOPPING_BOARDS.get(name));
                        }
                        entries.add(ModItems.LADLE);
                        entries.add(ModItems.KITCHEN_KNIFE);
                        entries.add(ModItems.APPLE_JAM_BOTTLE);
                        entries.add(ModItems.SWEETBERRY_JAM_BOTTLE);
                        entries.add(ModItems.MELON_JAM_BOTTLE);
                        if (FabricLoader.getInstance().isModLoaded("arborealnature"))
                        {
                            for(String name : JAM_NAMES)
                                entries.add(IntegrationItems.JAM_BOTTLES.get(name));
                        }
                        entries.add(ModItems.GLASS_APPLE_JUICE_BOTTLE);
                        entries.add(ModItems.GLASS_SWEETBERRY_JUICE_BOTTLE);
                        entries.add(ModItems.GLASS_MELON_JUICE_BOTTLE);
                        if (FabricLoader.getInstance().isModLoaded("arborealnature"))
                        {
                            for(String name : JAM_NAMES)
                                entries.add(IntegrationItems.JUICE_BOTTLES.get(name));
                        }
                        entries.add(ModBlocks.APPLE_JAM_BLOCK);
                        entries.add(ModBlocks.SWEETBERRY_JAM_BLOCK);
                        entries.add(ModBlocks.MELON_JAM_BLOCK);
                        if (FabricLoader.getInstance().isModLoaded("arborealnature"))
                        {
                            for(String name : JAM_NAMES)
                                entries.add(IntegrationBlocks.JAM_BLOCKS.get(name));
                        }
                        entries.add(ModItems.APPLE_CHOPPED);
                        entries.add(ModItems.SWEETBERRY_CHOPPED);
                        entries.add(ModItems.MELON_CHOPPED);
                        if (FabricLoader.getInstance().isModLoaded("arborealnature"))
                        {
                            for(String name : JAM_NAMES)
                                entries.add(IntegrationItems.JAM_CHOPPED.get(name));
                        }
                        entries.add(ModItems.GELATIN);
                        entries.add(ModBlocks.KELP_BLOCK);
                        entries.add(ModItems.GLASS_JUICE_BOTTLE);
                        entries.add(ModBlocks.JAR_BLACK);
                        entries.add(ModBlocks.JAR_BROWN);
                        entries.add(ModBlocks.JAR_GRAY);
                        entries.add(ModBlocks.JAR_LIGHT_GRAY);
                        entries.add(ModBlocks.JAR_WHITE);
                        entries.add(ModBlocks.JAR_RED);
                        entries.add(ModBlocks.JAR_ORANGE);
                        entries.add(ModBlocks.JAR_YELLOW);
                        entries.add(ModBlocks.JAR_PURPLE);
                        entries.add(ModBlocks.JAR_MAGENTA);
                        entries.add(ModBlocks.JAR_PINK);
                        entries.add(ModBlocks.JAR_BLUE);
                        entries.add(ModBlocks.JAR_CYAN);
                        entries.add(ModBlocks.JAR_LIGHT_BLUE);
                        entries.add(ModBlocks.JAR_GREEN);
                        entries.add(ModBlocks.JAR_LIME);
                        for(String name : PLASTIC_BLOCK_NAMES)
                        {
                            entries.add(ModBlocks.PLASTIC_BLOCKS.get(name));
                            entries.add(ModBlocks.SLABS.get(name));
                            entries.add(ModBlocks.STAIRS.get(name));
                            entries.add(ModBlocks.BUTTONS.get(name));
                            entries.add(ModBlocks.PRESSURE_PLATES.get(name));
                            entries.add(ModBlocks.WALLS.get(name));
                        }
                    }).build());

    public static void registerItemGroups()
    {

    }
}
