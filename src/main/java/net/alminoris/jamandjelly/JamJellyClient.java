package net.alminoris.jamandjelly;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class JamJellyClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        //BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WHITE_MUSHROOM, RenderLayer.getCutout());
    }
}
