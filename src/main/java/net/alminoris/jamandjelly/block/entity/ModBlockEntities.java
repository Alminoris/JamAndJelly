package net.alminoris.jamandjelly.block.entity;

import net.alminoris.jamandjelly.JamJelly;
import net.alminoris.jamandjelly.block.ModBlocks;
import net.alminoris.jamandjelly.integration.arborealnature.block.ANIntegrationBlocks;
import net.alminoris.jamandjelly.integration.wildfields.block.WFIntegrationBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ModBlockEntities
{
    public static final BlockEntityType<JammingPotBlockEntity> JAMMING_POT_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(JamJelly.MOD_ID, "jamming_pot_be"),
                    FabricBlockEntityTypeBuilder.create(JammingPotBlockEntity::new, ModBlocks.JAMMING_POT).build());

    public static final BlockEntityType<ChoppingBoardBlockEntity> CHOPPING_BOARD_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(JamJelly.MOD_ID, "chopping_board_be"),
                    FabricBlockEntityTypeBuilder.create(ChoppingBoardBlockEntity::new, toBlockArray(ModBlocks.CHOPPING_BOARDS.elements())).build());

    public static void registerBlockEntities()
    {

    }

    private static Block[] toBlockArray(Enumeration<Block> enumeration)
    {
        List<Block> blocks = new ArrayList<>();
        while (enumeration.hasMoreElements())
        {
            blocks.add(enumeration.nextElement());
        }
        return blocks.toArray(new Block[0]);
    }
}