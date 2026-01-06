package net.alminoris.jamandjelly;

import net.alminoris.jamandjelly.block.ModBlocks;
import net.alminoris.jamandjelly.block.entity.ModBlockEntities;
import net.alminoris.jamandjelly.block.entity.renderer.ChoppingBoardBlockEntityRenderer;
import net.alminoris.jamandjelly.integration.arborealnature.block.ANIntegrationBlocks;
import net.alminoris.jamandjelly.screen.ChoppingBoardScreen;
import net.alminoris.jamandjelly.screen.JammingPotScreen;
import net.alminoris.jamandjelly.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

import static net.alminoris.jamandjelly.integration.arborealnature.item.ANIntegrationItems.AN_JAM_NAMES;

public class JamJellyClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JAR_BLACK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JAR_BROWN, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JAR_GRAY, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JAR_LIGHT_GRAY, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JAR_WHITE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JAR_RED, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JAR_ORANGE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JAR_YELLOW, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JAR_PURPLE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JAR_MAGENTA, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JAR_PINK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JAR_BLUE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JAR_CYAN, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JAR_LIGHT_BLUE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JAR_GREEN, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JAR_LIME, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.APPLE_JAM_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SWEETBERRY_JAM_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MELON_JAM_BLOCK, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.APPLE_JELLY_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SWEETBERRY_JELLY_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MELON_JELLY_BLOCK, RenderLayer.getTranslucent());

        if (FabricLoader.getInstance().isModLoaded("arborealnature"))
        {
            for(String name : AN_JAM_NAMES)
                BlockRenderLayerMap.INSTANCE.putBlock(ANIntegrationBlocks.AN_JAM_BLOCKS.get(name), RenderLayer.getTranslucent());

            for(String name : AN_JAM_NAMES)
                BlockRenderLayerMap.INSTANCE.putBlock(ANIntegrationBlocks.AN_JELLY_BLOCKS.get(name), RenderLayer.getTranslucent());
        }

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JAMMING_POT, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JUICER, RenderLayer.getTranslucent());

        HandledScreens.register(ModScreenHandlers.JAMMING_POT_SCREEN_HANDLER, JammingPotScreen::new);
        HandledScreens.register(ModScreenHandlers.CHOPPING_BOARD_SCREEN_HANDLER, ChoppingBoardScreen::new);

        BlockEntityRendererFactories.register(ModBlockEntities.CHOPPING_BOARD_BLOCK_ENTITY, ChoppingBoardBlockEntityRenderer::new);
    }
}
