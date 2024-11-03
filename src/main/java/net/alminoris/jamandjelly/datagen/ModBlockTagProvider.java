package net.alminoris.jamandjelly.datagen;

import net.alminoris.jamandjelly.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

import static net.alminoris.jamandjelly.util.helper.BlockSetsHelper.PLASTIC_BLOCK_NAMES;


public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider
{
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture)
    {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {
        for(String name: PLASTIC_BLOCK_NAMES)
        {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                    .add(ModBlocks.PLASTIC_BLOCKS.get(name));
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                    .add(ModBlocks.SLABS.get(name));
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                    .add(ModBlocks.STAIRS.get(name));
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                    .add(ModBlocks.BUTTONS.get(name));
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                    .add(ModBlocks.PRESSURE_PLATES.get(name));
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                    .add(ModBlocks.WALLS.get(name));
            getOrCreateTagBuilder(BlockTags.WALLS)
                    .add(ModBlocks.WALLS.get(name));
            getOrCreateTagBuilder(BlockTags.BUTTONS)
                    .add(ModBlocks.BUTTONS.get(name));
            getOrCreateTagBuilder(BlockTags.SLABS)
                    .add(ModBlocks.SLABS.get(name));
            getOrCreateTagBuilder(BlockTags.STAIRS)
                    .add(ModBlocks.STAIRS.get(name));
            getOrCreateTagBuilder(BlockTags.PRESSURE_PLATES)
                    .add(ModBlocks.PRESSURE_PLATES.get(name));
        }
    }
}
