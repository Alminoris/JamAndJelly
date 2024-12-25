package net.alminoris.jamandjelly.block.entity;

import net.alminoris.jamandjelly.JamJelly;
import net.alminoris.jamandjelly.block.ModBlocks;
import net.alminoris.jamandjelly.integration.arborealnature.block.IntegrationBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModBlockEntities
{
    public static final BlockEntityType<JammingPotBlockEntity> JAMMING_POT_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(JamJelly.MOD_ID, "jamming_pot_be"),
                    FabricBlockEntityTypeBuilder.create(JammingPotBlockEntity::new, ModBlocks.JAMMING_POT).build());

    public static final BlockEntityType<ChoppingBoardBlockEntity> CHOPPING_BOARD_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(JamJelly.MOD_ID, "chopping_board_be"),
                    FabricBlockEntityTypeBuilder.create(ChoppingBoardBlockEntity::new,
                            ModBlocks.CHOPPING_BOARD_OAK,
                            ModBlocks.CHOPPING_BOARD_BIRCH,
                            ModBlocks.CHOPPING_BOARD_SPRUCE,
                            ModBlocks.CHOPPING_BOARD_JUNGLE,
                            ModBlocks.CHOPPING_BOARD_ACACIA,
                            ModBlocks.CHOPPING_BOARD_DARK_OAK,
                            ModBlocks.CHOPPING_BOARD_CRIMSON,
                            ModBlocks.CHOPPING_BOARD_WARPED,
                            ModBlocks.CHOPPING_BOARD_MANGROVE,
                            ModBlocks.CHOPPING_BOARD_CHERRY,
                            ModBlocks.CHOPPING_BOARD_BAMBOO,
                            IntegrationBlocks.CHOPPING_BOARDS.get("hazelnut"),
                            IntegrationBlocks.CHOPPING_BOARDS.get("hornbeam"),
                            IntegrationBlocks.CHOPPING_BOARDS.get("hawthorn"),
                            IntegrationBlocks.CHOPPING_BOARDS.get("quince"),
                            IntegrationBlocks.CHOPPING_BOARDS.get("plum"),
                            IntegrationBlocks.CHOPPING_BOARDS.get("mango"),
                            IntegrationBlocks.CHOPPING_BOARDS.get("fig"),
                            IntegrationBlocks.CHOPPING_BOARDS.get("viburnum"),
                            IntegrationBlocks.CHOPPING_BOARDS.get("white_mulberry"),
                            IntegrationBlocks.CHOPPING_BOARDS.get("wild_cherry"),
                            IntegrationBlocks.CHOPPING_BOARDS.get("pine")).build());

    public static void registerBlockEntities()
    {

    }
}
