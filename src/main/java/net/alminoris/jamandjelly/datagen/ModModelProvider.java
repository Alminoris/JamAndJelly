package net.alminoris.jamandjelly.datagen;

import net.alminoris.jamandjelly.JamJelly;
import net.alminoris.jamandjelly.block.ModBlocks;
import net.alminoris.jamandjelly.integration.arborealnature.block.ANIntegrationBlocks;
import net.alminoris.jamandjelly.integration.arborealnature.item.ANIntegrationItems;
import net.alminoris.jamandjelly.integration.wildfields.block.WFIntegrationBlocks;
import net.alminoris.jamandjelly.integration.wildfields.item.WFIntegrationItems;
import net.alminoris.jamandjelly.item.ModItems;
import net.alminoris.jamandjelly.util.helper.JsonHelper;
import net.alminoris.jamandjelly.util.helper.ModJsonTemplates;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

import java.util.Dictionary;
import java.util.Hashtable;

import static net.alminoris.jamandjelly.block.ModBlocks.*;
import static net.alminoris.jamandjelly.integration.arborealnature.item.ANIntegrationItems.AN_JAM_NAMES;
import static net.alminoris.jamandjelly.integration.wildfields.item.WFIntegrationItems.WF_JAM_NAMES;
import static net.alminoris.jamandjelly.util.helper.BlockSetsHelper.PLASTIC_BLOCK_NAMES;
import static net.alminoris.jamandjelly.util.helper.BlockSetsHelper.getWoods;

public class ModModelProvider extends FabricModelProvider
{
    public ModModelProvider(FabricDataOutput output)
    {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator)
    {
        String[] woods = { "oak", "birch", "spruce", "jungle",
                "acacia", "dark_oak", "crimson", "warped",
                "mangrove", "cherry", "bamboo" };
        String[] colors = { "black", "brown", "gray", "light_gray",
                "white", "red", "orange", "yellow",
                "purple", "magenta", "pink", "blue",
                "cyan", "light_blue", "green", "lime" };
        String[] jams = { "apple", "sweetberry", "melon", "honey" };

        for(String color : colors)
            for(String jam : jams)
                registerJar(color, jam);

        blockStateModelGenerator.registerSimpleState(ModBlocks.APPLE_JAM_BLOCK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.SWEETBERRY_JAM_BLOCK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.MELON_JAM_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.APPLE_JELLY_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SWEETBERRY_JELLY_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MELON_JELLY_BLOCK);

        for(String jam : jams)
        {
            if (jam.equals("honey")) break;
            JsonHelper.createJamBlockModel(ModJsonTemplates.JAM_BLOCK_TEMPLATE, jam);
            registerJammingPot(jam);
            registerJuicer(jam);
        }

        blockStateModelGenerator.registerParentedItemModel(JUICER, Identifier.of(JamJelly.MOD_ID, "block/apple/juicer_0"));

        for(String color : colors)
        {
            JsonHelper.createJarBlockState(ModJsonTemplates.JAR_BLOCKSTATE_TEMPLATE, color);
        }

        blockStateModelGenerator.registerParentedItemModel(ModBlocks.JAR_BLACK, Identifier.of(JamJelly.MOD_ID, "block/apple/jar_black_0"));
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.JAR_BROWN, Identifier.of(JamJelly.MOD_ID, "block/apple/jar_brown_0"));
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.JAR_GRAY, Identifier.of(JamJelly.MOD_ID, "block/apple/jar_gray_0"));
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.JAR_LIGHT_GRAY, Identifier.of(JamJelly.MOD_ID, "block/apple/jar_light_gray_0"));
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.JAR_WHITE, Identifier.of(JamJelly.MOD_ID, "block/apple/jar_white_0"));
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.JAR_RED, Identifier.of(JamJelly.MOD_ID, "block/apple/jar_red_0"));
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.JAR_ORANGE, Identifier.of(JamJelly.MOD_ID, "block/apple/jar_orange_0"));
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.JAR_YELLOW, Identifier.of(JamJelly.MOD_ID, "block/apple/jar_yellow_0"));
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.JAR_PURPLE, Identifier.of(JamJelly.MOD_ID, "block/apple/jar_purple_0"));
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.JAR_MAGENTA, Identifier.of(JamJelly.MOD_ID, "block/apple/jar_magenta_0"));
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.JAR_PINK, Identifier.of(JamJelly.MOD_ID, "block/apple/jar_pink_0"));
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.JAR_BLUE, Identifier.of(JamJelly.MOD_ID, "block/apple/jar_blue_0"));
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.JAR_CYAN, Identifier.of(JamJelly.MOD_ID, "block/apple/jar_cyan_0"));
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.JAR_LIGHT_BLUE, Identifier.of(JamJelly.MOD_ID, "block/apple/jar_light_blue_0"));
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.JAR_GREEN, Identifier.of(JamJelly.MOD_ID, "block/apple/jar_green_0"));
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.JAR_LIME, Identifier.of(JamJelly.MOD_ID, "block/apple/jar_lime_0"));

        blockStateModelGenerator.registerParentedItemModel(ModBlocks.APPLE_JAM_BLOCK, Identifier.of(JamJelly.MOD_ID, "block/apple_jam_block"));
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.SWEETBERRY_JAM_BLOCK, Identifier.of(JamJelly.MOD_ID, "block/sweetberry_jam_block"));
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.MELON_JAM_BLOCK, Identifier.of(JamJelly.MOD_ID, "block/melon_jam_block"));

        Dictionary<String, BlockStateModelGenerator.BlockTexturePool> plasticBlocksPool = new Hashtable<>()
        {{
            for(String name : PLASTIC_BLOCK_NAMES)
                put(name, blockStateModelGenerator.registerCubeAllModelTexturePool(PLASTIC_BLOCKS.get(name)));
        }};

        for(String name : PLASTIC_BLOCK_NAMES)
        {
            plasticBlocksPool.get(name).slab(SLABS.get(name));
            plasticBlocksPool.get(name).stairs(STAIRS.get(name));
            plasticBlocksPool.get(name).button(BUTTONS.get(name));
            plasticBlocksPool.get(name).pressurePlate(PRESSURE_PLATES.get(name));
            plasticBlocksPool.get(name).wall(WALLS.get(name));
        }

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.KELP_BLOCK);

        for(String wood : woods)
        {
            JsonHelper.createChoppingBoardBlockState(ModJsonTemplates.CHOPPING_BOARD_BS_TEMPLATE, wood);
            JsonHelper.createChoppingBoardModel(ModJsonTemplates.CHOPPING_BOARD_KNIFE_TEMPLATE, wood, true);
            JsonHelper.createChoppingBoardModel(ModJsonTemplates.CHOPPING_BOARD_TEMPLATE, wood, false);
        }

        for(String name : AN_JAM_NAMES)
        {
            blockStateModelGenerator.registerSimpleCubeAll(ANIntegrationBlocks.AN_JELLY_BLOCKS.get(name));
            blockStateModelGenerator.registerSimpleState(ANIntegrationBlocks.AN_JAM_BLOCKS.get(name));
            blockStateModelGenerator.registerParentedItemModel(ANIntegrationBlocks.AN_JAM_BLOCKS.get(name), Identifier.of(JamJelly.MOD_ID, "block/"+name+"_jam_block"));
            JsonHelper.createJamBlockModel(ModJsonTemplates.JAM_BLOCK_TEMPLATE, name);
            registerJammingPot(name);
            registerJuicer(name);
            for(String color : colors)
                registerJar(color, name);
        }

        for(String name : WF_JAM_NAMES)
        {
            blockStateModelGenerator.registerSimpleCubeAll(WFIntegrationBlocks.WF_JELLY_BLOCKS.get(name));
            blockStateModelGenerator.registerSimpleState(WFIntegrationBlocks.WF_JAM_BLOCKS.get(name));
            blockStateModelGenerator.registerParentedItemModel(WFIntegrationBlocks.WF_JAM_BLOCKS.get(name), Identifier.of(JamJelly.MOD_ID, "block/"+name+"_jam_block"));
            JsonHelper.createJamBlockModel(ModJsonTemplates.JAM_BLOCK_TEMPLATE, name);
            registerJammingPot(name);
            registerJuicer(name);
            for(String color : colors)
                registerJar(color, name);
        }

        for(String name : getWoods())
        {
            JsonHelper.createChoppingBoardBlockState(ModJsonTemplates.CHOPPING_BOARD_BS_TEMPLATE, name);
            JsonHelper.createChoppingBoardModel(ModJsonTemplates.CHOPPING_BOARD_KNIFE_TEMPLATE, name, true);
            JsonHelper.createChoppingBoardModel(ModJsonTemplates.CHOPPING_BOARD_TEMPLATE, name, false);
            blockStateModelGenerator.registerParentedItemModel(CHOPPING_BOARDS.get(name),
                    Identifier.of(JamJelly.MOD_ID, "block/chopping_board_"+name));
        }


        blockStateModelGenerator.registerParentedItemModel(ModBlocks.JAMMING_POT, Identifier.of(JamJelly.MOD_ID, "block/apple/jamming_pot_closed"));
    }

    private void registerJammingPot(String insideName)
    {
        JsonHelper.createJammingPotBlockModel(ModJsonTemplates.JAMMING_POT_TEMPLATE, "", insideName, false);
        JsonHelper.createJammingPotBlockModel(ModJsonTemplates.JAMMING_POT_CLOSED_TEMPLATE, "closed", insideName, false);
        JsonHelper.createJammingPotBlockModel(ModJsonTemplates.JAMMING_POT_CLOSED_SUPPORT_TEMPLATE, "closed", insideName, true);
        JsonHelper.createJammingPotBlockModel(ModJsonTemplates.JAMMING_POT_INSIDE_TEMPLATE, "inside", insideName, false);
        JsonHelper.createJammingPotBlockModel(ModJsonTemplates.JAMMING_POT_INSIDE_SUPPORT_TEMPLATE, "inside", insideName, true);
        JsonHelper.createJammingPotBlockModel(ModJsonTemplates.JAMMING_POT_SUPPORT_TEMPLATE, "", insideName, true);
    }

    private void registerJar(String colorName, String insideName)
    {
        JsonHelper.createJarBlockModel(ModJsonTemplates.JAR_0_TEMPLATE, colorName, insideName, false, 0);
        JsonHelper.createJarBlockModel(ModJsonTemplates.JAR_1_TEMPLATE, colorName, insideName, false, 1);
        JsonHelper.createJarBlockModel(ModJsonTemplates.JAR_2_TEMPLATE, colorName, insideName, false, 2);
        JsonHelper.createJarBlockModel(ModJsonTemplates.JAR_3_TEMPLATE, colorName, insideName, false, 3);
        JsonHelper.createJarBlockModel(ModJsonTemplates.JAR_0_OPEN_TEMPLATE, colorName, insideName, true, 0);
        JsonHelper.createJarBlockModel(ModJsonTemplates.JAR_1_OPEN_TEMPLATE, colorName, insideName, true, 1);
        JsonHelper.createJarBlockModel(ModJsonTemplates.JAR_2_OPEN_TEMPLATE, colorName, insideName, true, 2);
        JsonHelper.createJarBlockModel(ModJsonTemplates.JAR_3_OPEN_TEMPLATE, colorName, insideName, true, 3);
    }

    private void registerJuicer(String insideName)
    {
        JsonHelper.createJuicerBlockModel(ModJsonTemplates.JUICER_0, insideName, 0);
        JsonHelper.createJuicerBlockModel(ModJsonTemplates.JUICER_1, insideName, 1);
        JsonHelper.createJuicerBlockModel(ModJsonTemplates.JUICER_2, insideName, 2);
        JsonHelper.createJuicerBlockModel(ModJsonTemplates.JUICER_3, insideName, 3);
        JsonHelper.createJuicerBlockModel(ModJsonTemplates.JUICER_4, insideName, 4);
        JsonHelper.createJuicerBlockModel(ModJsonTemplates.JUICER_5, insideName, 5);
        JsonHelper.createJuicerBlockModel(ModJsonTemplates.JUICER_6, insideName, 6);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator)
    {
        itemModelGenerator.register(ModItems.KITCHEN_KNIFE, Models.GENERATED);
        itemModelGenerator.register(ModItems.LADLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GELATIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.APPLE_JAM_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SWEETBERRY_JAM_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MELON_JAM_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.APPLE_CHOPPED, Models.GENERATED);
        itemModelGenerator.register(ModItems.SWEETBERRY_CHOPPED, Models.GENERATED);
        itemModelGenerator.register(ModItems.MELON_CHOPPED, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLASS_JUICE_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLASS_APPLE_JUICE_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLASS_SWEETBERRY_JUICE_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLASS_MELON_JUICE_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.APPLE_JELLY, Models.GENERATED);
        itemModelGenerator.register(ModItems.SWEETBERRY_JELLY, Models.GENERATED);
        itemModelGenerator.register(ModItems.MELON_JELLY, Models.GENERATED);

        for(String name : AN_JAM_NAMES)
        {
            itemModelGenerator.register(ANIntegrationItems.AN_JAM_BOTTLES.get(name), Models.GENERATED);
            itemModelGenerator.register(ANIntegrationItems.AN_JUICE_BOTTLES.get(name), Models.GENERATED);
            itemModelGenerator.register(ANIntegrationItems.AN_JELLY.get(name), Models.GENERATED);
            itemModelGenerator.register(ANIntegrationItems.AN_JAM_CHOPPED.get(name), Models.GENERATED);
        }

        for(String name : WF_JAM_NAMES)
        {
            itemModelGenerator.register(WFIntegrationItems.WF_JAM_BOTTLES.get(name), Models.GENERATED);
            itemModelGenerator.register(WFIntegrationItems.WF_JUICE_BOTTLES.get(name), Models.GENERATED);
            itemModelGenerator.register(WFIntegrationItems.WF_JELLY.get(name), Models.GENERATED);
            itemModelGenerator.register(WFIntegrationItems.WF_JAM_CHOPPED.get(name), Models.GENERATED);
        }
    }
}