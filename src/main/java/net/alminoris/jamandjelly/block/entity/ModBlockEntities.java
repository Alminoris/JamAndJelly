package net.alminoris.jamandjelly.block.entity;

import net.alminoris.jamandjelly.JamJelly;
import net.alminoris.jamandjelly.block.ModBlocks;
import net.alminoris.jamandjelly.integration.arborealnature.block.ANIntegrationBlocks;
import net.alminoris.jamandjelly.integration.wildfields.block.WFIntegrationBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

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
                            ANIntegrationBlocks.AN_CHOPPING_BOARDS.get("hazelnut"),
                            ANIntegrationBlocks.AN_CHOPPING_BOARDS.get("hornbeam"),
                            ANIntegrationBlocks.AN_CHOPPING_BOARDS.get("hawthorn"),
                            ANIntegrationBlocks.AN_CHOPPING_BOARDS.get("quince"),
                            ANIntegrationBlocks.AN_CHOPPING_BOARDS.get("plum"),
                            ANIntegrationBlocks.AN_CHOPPING_BOARDS.get("mango"),
                            ANIntegrationBlocks.AN_CHOPPING_BOARDS.get("fig"),
                            ANIntegrationBlocks.AN_CHOPPING_BOARDS.get("viburnum"),
                            ANIntegrationBlocks.AN_CHOPPING_BOARDS.get("white_mulberry"),
                            ANIntegrationBlocks.AN_CHOPPING_BOARDS.get("wild_cherry"),
                            ANIntegrationBlocks.AN_CHOPPING_BOARDS.get("bauhinia"),
                            ANIntegrationBlocks.AN_CHOPPING_BOARDS.get("pine"),
                            WFIntegrationBlocks.WF_CHOPPING_BOARDS.get("olive"),
                            WFIntegrationBlocks.WF_CHOPPING_BOARDS.get("tamarisk"),
                            ANIntegrationBlocks.AN_CHOPPING_BOARDS.get("fir"),
                            ANIntegrationBlocks.AN_CHOPPING_BOARDS.get("cedar")).build());

    public static void registerBlockEntities()
    {

    }
}
