package net.alminoris.jamandjelly.datagen;

import com.google.common.collect.ImmutableList;
import net.alminoris.jamandjelly.block.ModBlocks;
import net.alminoris.jamandjelly.integration.arborealnature.block.ANIntegrationBlocks;
import net.alminoris.jamandjelly.integration.arborealnature.item.ANIntegrationItems;
import net.alminoris.jamandjelly.integration.wildfields.block.WFIntegrationBlocks;
import net.alminoris.jamandjelly.integration.wildfields.item.WFIntegrationItems;
import net.alminoris.jamandjelly.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.alminoris.jamandjelly.integration.arborealnature.item.ANIntegrationItems.AN_JAM_NAMES;
import static net.alminoris.jamandjelly.integration.wildfields.item.WFIntegrationItems.WF_JAM_NAMES;
import static net.alminoris.jamandjelly.util.helper.BlockSetsHelper.PLASTIC_BLOCK_NAMES;

public class ModRecipeProvider extends FabricRecipeProvider
{
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture)
    {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter)
    {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GELATIN, 1)
                .input(Items.SUGAR).input(Items.SLIME_BALL).input(Items.WATER_BUCKET)
                .criterion(hasItem(Items.SUGAR), conditionsFromItem(Items.SUGAR))
                .criterion(hasItem(Items.SLIME_BALL), conditionsFromItem(Items.SLIME_BALL))
                .criterion(hasItem(Items.WATER_BUCKET), conditionsFromItem(Items.WATER_BUCKET))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.KITCHEN_KNIFE, 1)
                .pattern("  #")
                .pattern(" # ")
                .pattern("/  ")
                .input('#', Items.IRON_INGOT)
                .input('/', Items.STICK)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.LADLE, 1)
                .pattern("  #")
                .pattern("// ")
                .pattern("/  ")
                .input('#', ModBlocks.PLASTIC_BLOCKS.get("plastic_black"))
                .input('/', Items.IRON_INGOT)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(recipeExporter);

//        registerChoppingBoardRecipe(recipeExporter, ModBlocks.CHOPPING_BOARD_OAK, Blocks.OAK_SLAB);
//        registerChoppingBoardRecipe(recipeExporter, ModBlocks.CHOPPING_BOARD_BIRCH, Blocks.BIRCH_SLAB);
//        registerChoppingBoardRecipe(recipeExporter, ModBlocks.CHOPPING_BOARD_SPRUCE, Blocks.SPRUCE_SLAB);
//        registerChoppingBoardRecipe(recipeExporter, ModBlocks.CHOPPING_BOARD_JUNGLE, Blocks.JUNGLE_SLAB);
//        registerChoppingBoardRecipe(recipeExporter, ModBlocks.CHOPPING_BOARD_ACACIA, Blocks.ACACIA_SLAB);
//        registerChoppingBoardRecipe(recipeExporter, ModBlocks.CHOPPING_BOARD_DARK_OAK, Blocks.DARK_OAK_SLAB);
//        registerChoppingBoardRecipe(recipeExporter, ModBlocks.CHOPPING_BOARD_CRIMSON, Blocks.CRIMSON_SLAB);
//        registerChoppingBoardRecipe(recipeExporter, ModBlocks.CHOPPING_BOARD_WARPED, Blocks.WARPED_SLAB);
//        registerChoppingBoardRecipe(recipeExporter, ModBlocks.CHOPPING_BOARD_MANGROVE, Blocks.MANGROVE_SLAB);
//        registerChoppingBoardRecipe(recipeExporter, ModBlocks.CHOPPING_BOARD_CHERRY, Blocks.CHERRY_SLAB);
//        registerChoppingBoardRecipe(recipeExporter, ModBlocks.CHOPPING_BOARD_BAMBOO, Blocks.BAMBOO_SLAB);

        registerJarBlockRecipe(recipeExporter, "plastic_black", ModBlocks.JAR_BLACK);
        registerJarBlockRecipe(recipeExporter, "plastic_brown", ModBlocks.JAR_BROWN);
        registerJarBlockRecipe(recipeExporter, "plastic_gray", ModBlocks.JAR_GRAY);
        registerJarBlockRecipe(recipeExporter, "plastic_light_gray", ModBlocks.JAR_LIGHT_GRAY);
        registerJarBlockRecipe(recipeExporter, "plastic_white", ModBlocks.JAR_WHITE);
        registerJarBlockRecipe(recipeExporter, "plastic_red", ModBlocks.JAR_RED);
        registerJarBlockRecipe(recipeExporter, "plastic_orange", ModBlocks.JAR_ORANGE);
        registerJarBlockRecipe(recipeExporter, "plastic_yellow", ModBlocks.JAR_YELLOW);
        registerJarBlockRecipe(recipeExporter, "plastic_purple", ModBlocks.JAR_PURPLE);
        registerJarBlockRecipe(recipeExporter, "plastic_magenta", ModBlocks.JAR_MAGENTA);
        registerJarBlockRecipe(recipeExporter, "plastic_pink", ModBlocks.JAR_PINK);
        registerJarBlockRecipe(recipeExporter, "plastic_blue", ModBlocks.JAR_BLUE);
        registerJarBlockRecipe(recipeExporter, "plastic_cyan", ModBlocks.JAR_CYAN);
        registerJarBlockRecipe(recipeExporter, "plastic_light_blue", ModBlocks.JAR_LIGHT_BLUE);
        registerJarBlockRecipe(recipeExporter, "plastic_green", ModBlocks.JAR_GREEN);
        registerJarBlockRecipe(recipeExporter, "plastic_lime", ModBlocks.JAR_LIME);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.APPLE_JAM_BLOCK, 1)
                .pattern("##")
                .pattern("##")
                .input('#', ModItems.APPLE_JAM_BOTTLE)
                .criterion(hasItem(ModItems.APPLE_JAM_BOTTLE), conditionsFromItem(ModItems.APPLE_JAM_BOTTLE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.SWEETBERRY_JAM_BLOCK, 1)
                .pattern("##")
                .pattern("##")
                .input('#', ModItems.SWEETBERRY_JAM_BOTTLE)
                .criterion(hasItem(ModItems.SWEETBERRY_JAM_BOTTLE), conditionsFromItem(ModItems.SWEETBERRY_JAM_BOTTLE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.MELON_JAM_BLOCK, 1)
                .pattern("##")
                .pattern("##")
                .input('#', ModItems.MELON_JAM_BOTTLE)
                .criterion(hasItem(ModItems.MELON_JAM_BOTTLE), conditionsFromItem(ModItems.MELON_JAM_BOTTLE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.APPLE_JELLY_BLOCK, 1)
                .pattern("##")
                .pattern("##")
                .input('#', ModItems.APPLE_JELLY)
                .criterion(hasItem(ModItems.APPLE_JELLY), conditionsFromItem(ModItems.APPLE_JELLY))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.SWEETBERRY_JELLY_BLOCK, 1)
                .pattern("##")
                .pattern("##")
                .input('#', ModItems.SWEETBERRY_JELLY)
                .criterion(hasItem(ModItems.SWEETBERRY_JELLY), conditionsFromItem(ModItems.SWEETBERRY_JELLY))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.MELON_JELLY_BLOCK, 1)
                .pattern("##")
                .pattern("##")
                .input('#', ModItems.MELON_JELLY)
                .criterion(hasItem(ModItems.MELON_JELLY), conditionsFromItem(ModItems.MELON_JELLY))
                .offerTo(recipeExporter);

        for(String name : AN_JAM_NAMES)
        {
            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ANIntegrationBlocks.AN_JAM_BLOCKS.get(name), 1)
                    .pattern("##")
                    .pattern("##")
                    .input('#', ANIntegrationItems.AN_JAM_BOTTLES.get(name))
                    .criterion(hasItem(ANIntegrationItems.AN_JAM_BOTTLES.get(name)), conditionsFromItem(ANIntegrationItems.AN_JAM_BOTTLES.get(name)))
                    .offerTo(recipeExporter);

            ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ANIntegrationItems.AN_JAM_BOTTLES.get(name), 4)
                    .input(ANIntegrationBlocks.AN_JAM_BLOCKS.get(name))
                    .input(Items.GLASS_BOTTLE, 4)
                    .criterion(hasItem(ANIntegrationBlocks.AN_JAM_BLOCKS.get(name)), conditionsFromItem(ANIntegrationBlocks.AN_JAM_BLOCKS.get(name)))
                    .criterion(hasItem(Items.GLASS_BOTTLE), conditionsFromItem(Items.GLASS_BOTTLE))
                    .offerTo(recipeExporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ANIntegrationBlocks.AN_JELLY_BLOCKS.get(name), 1)
                    .pattern("##")
                    .pattern("##")
                    .input('#', ANIntegrationItems.AN_JELLY.get(name))
                    .criterion(hasItem(ANIntegrationItems.AN_JELLY.get(name)), conditionsFromItem(ANIntegrationItems.AN_JELLY.get(name)))
                    .offerTo(recipeExporter);

            ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ANIntegrationItems.AN_JELLY.get(name), 4)
                    .input(ANIntegrationBlocks.AN_JELLY_BLOCKS.get(name))
                    .input(Items.BOWL, 4)
                    .criterion(hasItem(ANIntegrationBlocks.AN_JELLY_BLOCKS.get(name)), conditionsFromItem(ANIntegrationBlocks.AN_JELLY_BLOCKS.get(name)))
                    .criterion(hasItem(Items.BOWL), conditionsFromItem(Items.BOWL))
                    .offerTo(recipeExporter);
        }

        for(String name : WF_JAM_NAMES)
        {
            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, WFIntegrationBlocks.WF_JAM_BLOCKS.get(name), 1)
                    .pattern("##")
                    .pattern("##")
                    .input('#', ANIntegrationItems.AN_JAM_BOTTLES.get(name))
                    .criterion(hasItem(ANIntegrationItems.AN_JAM_BOTTLES.get(name)), conditionsFromItem(WFIntegrationItems.WF_JAM_BOTTLES.get(name)))
                    .offerTo(recipeExporter);

            ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, WFIntegrationItems.WF_JAM_BOTTLES.get(name), 4)
                    .input(WFIntegrationBlocks.WF_JAM_BLOCKS.get(name))
                    .input(Items.GLASS_BOTTLE, 4)
                    .criterion(hasItem(WFIntegrationBlocks.WF_JAM_BLOCKS.get(name)), conditionsFromItem(WFIntegrationBlocks.WF_JAM_BLOCKS.get(name)))
                    .criterion(hasItem(Items.GLASS_BOTTLE), conditionsFromItem(Items.GLASS_BOTTLE))
                    .offerTo(recipeExporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, WFIntegrationBlocks.WF_JELLY_BLOCKS.get(name), 1)
                    .pattern("##")
                    .pattern("##")
                    .input('#', WFIntegrationItems.WF_JELLY.get(name))
                    .criterion(hasItem(WFIntegrationItems.WF_JELLY.get(name)), conditionsFromItem(WFIntegrationItems.WF_JELLY.get(name)))
                    .offerTo(recipeExporter);

            ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, WFIntegrationItems.WF_JELLY.get(name), 4)
                    .input(WFIntegrationBlocks.WF_JELLY_BLOCKS.get(name))
                    .input(Items.BOWL, 4)
                    .criterion(hasItem(WFIntegrationBlocks.WF_JELLY_BLOCKS.get(name)), conditionsFromItem(WFIntegrationBlocks.WF_JELLY_BLOCKS.get(name)))
                    .criterion(hasItem(Items.BOWL), conditionsFromItem(Items.BOWL))
                    .offerTo(recipeExporter);
        }

        offerSmelting(recipeExporter, ImmutableList.of(ModBlocks.KELP_BLOCK), RecipeCategory.FOOD, Blocks.DRIED_KELP_BLOCK, 0.1f, 200, "kelp");
        offerBlasting(recipeExporter, ImmutableList.of(Items.DRIED_KELP_BLOCK), RecipeCategory.BUILDING_BLOCKS, ModBlocks.PLASTIC_BLOCKS.get("plastic_white"), 0.1f, 100, "plastic");

        List<Item> list = List.of(
                Items.BLACK_DYE,
                Items.BLUE_DYE,
                Items.BROWN_DYE,
                Items.CYAN_DYE,
                Items.GRAY_DYE,
                Items.GREEN_DYE,
                Items.LIGHT_BLUE_DYE,
                Items.LIGHT_GRAY_DYE,
                Items.LIME_DYE,
                Items.MAGENTA_DYE,
                Items.ORANGE_DYE,
                Items.PINK_DYE,
                Items.PURPLE_DYE,
                Items.RED_DYE,
                Items.YELLOW_DYE,
                Items.WHITE_DYE
        );

        List<Item> list2 = new ArrayList<>();

        for (String name: PLASTIC_BLOCK_NAMES)
            list2.add(ModBlocks.PLASTIC_BLOCKS.get(name).asItem());

        offerDyeableRecipes(recipeExporter, list, list2, "plastic");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.KELP_BLOCK, 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .input('#', Blocks.KELP)
                .criterion(hasItem(Blocks.KELP), conditionsFromItem(Blocks.KELP))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.APPLE_JAM_BOTTLE, 4)
                .input(ModBlocks.APPLE_JAM_BLOCK)
                .input(Items.GLASS_BOTTLE, 4)
                .criterion(hasItem(ModBlocks.APPLE_JAM_BLOCK), conditionsFromItem(ModBlocks.APPLE_JAM_BLOCK))
                .criterion(hasItem(Items.GLASS_BOTTLE), conditionsFromItem(Items.GLASS_BOTTLE))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.SWEETBERRY_JAM_BOTTLE, 4)
                .input(ModBlocks.SWEETBERRY_JAM_BLOCK)
                .input(Items.GLASS_BOTTLE, 4)
                .criterion(hasItem(ModBlocks.SWEETBERRY_JAM_BLOCK), conditionsFromItem(ModBlocks.SWEETBERRY_JAM_BLOCK))
                .criterion(hasItem(Items.GLASS_BOTTLE), conditionsFromItem(Items.GLASS_BOTTLE))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.MELON_JAM_BOTTLE, 4)
                .input(ModBlocks.MELON_JAM_BLOCK)
                .input(Items.GLASS_BOTTLE, 4)
                .criterion(hasItem(ModBlocks.MELON_JAM_BLOCK), conditionsFromItem(ModBlocks.MELON_JAM_BLOCK))
                .criterion(hasItem(Items.GLASS_BOTTLE), conditionsFromItem(Items.GLASS_BOTTLE))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.APPLE_JELLY, 4)
                .input(ModBlocks.APPLE_JELLY_BLOCK)
                .input(Items.BOWL, 4)
                .criterion(hasItem(ModBlocks.APPLE_JELLY_BLOCK), conditionsFromItem(ModBlocks.APPLE_JELLY_BLOCK))
                .criterion(hasItem(Items.BOWL), conditionsFromItem(Items.BOWL))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.SWEETBERRY_JELLY, 4)
                .input(ModBlocks.SWEETBERRY_JELLY_BLOCK)
                .input(Items.BOWL, 4)
                .criterion(hasItem(ModBlocks.SWEETBERRY_JELLY_BLOCK), conditionsFromItem(ModBlocks.SWEETBERRY_JELLY_BLOCK))
                .criterion(hasItem(Items.BOWL), conditionsFromItem(Items.BOWL))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.MELON_JELLY, 4)
                .input(ModBlocks.MELON_JELLY_BLOCK)
                .input(Items.BOWL, 4)
                .criterion(hasItem(ModBlocks.MELON_JELLY_BLOCK), conditionsFromItem(ModBlocks.MELON_JELLY_BLOCK))
                .criterion(hasItem(Items.BOWL), conditionsFromItem(Items.BOWL))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BREWING, ModItems.GLASS_JUICE_BOTTLE, 2)
                .pattern("# #")
                .pattern(" # ")
                .input('#', Blocks.GLASS_PANE)
                .criterion(hasItem(Blocks.GLASS_PANE), conditionsFromItem(Blocks.GLASS_PANE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BREWING, ModBlocks.JAMMING_POT, 1)
                .pattern("#/#")
                .pattern(" # ")
                .input('#', Items.IRON_INGOT)
                .input('/', Blocks.IRON_BLOCK)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Blocks.IRON_BLOCK), conditionsFromItem(Blocks.IRON_BLOCK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BREWING, ModBlocks.JUICER, 1)
                .pattern(" / ")
                .pattern("*/*")
                .pattern("###")
                .input('#', Blocks.SMOOTH_STONE_SLAB)
                .input('*', Blocks.GRAY_CONCRETE)
                .input('/', Items.IRON_INGOT)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Blocks.GRAY_CONCRETE), conditionsFromItem(Blocks.GRAY_CONCRETE))
                .criterion(hasItem(Blocks.SMOOTH_STONE_SLAB), conditionsFromItem(Blocks.SMOOTH_STONE_SLAB))
                .offerTo(recipeExporter);
    }

    private static void registerChoppingBoardRecipe(RecipeExporter recipeExporter, Block block, Block slab)
    {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, block, 4)
                .pattern("###")
                .pattern("###")
                .input('#', slab)
                .criterion(hasItem(slab), conditionsFromItem(slab))
                .offerTo(recipeExporter);
    }

    private static void registerJarBlockRecipe(RecipeExporter recipeExporter, String name, Block block)
    {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BREWING, block, 4)
                .pattern("#/#")
                .pattern("# #")
                .pattern("###")
                .input('#', Blocks.GLASS)
                .input('/', ModBlocks.PLASTIC_BLOCKS.get(name))
                .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                .criterion(hasItem(ModBlocks.PLASTIC_BLOCKS.get(name)), conditionsFromItem(ModBlocks.PLASTIC_BLOCKS.get(name)))
                .offerTo(recipeExporter);
    }
}
