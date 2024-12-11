package net.alminoris.jamandjelly.block.custom;

import com.mojang.serialization.MapCodec;
import net.alminoris.jamandjelly.block.entity.JammingPotBlockEntity;
import net.alminoris.jamandjelly.block.entity.ModBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class JammingPotBlock extends BlockWithEntity implements BlockEntityProvider
{
    public enum Inside implements StringIdentifiable
    {
        APPLE("apple"),
        SWEETBERRY("sweetberry"),
        MELON("melon"),
        QUINCE("quince"),
        PLUM("plum"),
        MANGO("mango"),
        FIGS("figs"),
        VIBURNUM("viburnum"),
        WHITE_MULBERRY("white_mulberry"),
        WILD_CHERRY("wild_cherry"),
        BILBERRY("bilberry"),
        BLACKBERRY("blackberry"),
        PINK_CURRANT("pink_currant");;

        private final String name;

        Inside(String name) { this.name = name; }

        @Override
        public String asString() { return this.name; }

        public static Inside fromString(String name)
        {
            for (Inside inside : Inside.values())
            {
                if (inside.name.equalsIgnoreCase(name))
                    return inside;
            }
            throw new IllegalArgumentException("No enum constant for name: " + name);
        }
    }

    public enum Variant implements StringIdentifiable
    {
        NORMAL("normal"),
        CLOSED("closed"),
        INSIDED("insided");

        private final String name;

        Variant(String name) { this.name = name; }

        @Override
        public String asString() { return this.name; }
    }

    public static final EnumProperty<JammingPotBlock.Inside> INSIDE = EnumProperty.of("inside", JammingPotBlock.Inside.class);

    public static final EnumProperty<JammingPotBlock.Variant> VARIANT = EnumProperty.of("variant", JammingPotBlock.Variant.class);

    public static final BooleanProperty SUPPORT = BooleanProperty.of("support");

    private static final VoxelShape SHAPE = Block.createCuboidShape(3, 0, 3, 13, 8, 13);

    public static final MapCodec<JammingPotBlock> CODEC = JammingPotBlock.createCodec(JammingPotBlock::new);

    public JammingPotBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(VARIANT, Variant.CLOSED).with(SUPPORT, false).with(INSIDE, Inside.APPLE));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        builder.add(VARIANT, SUPPORT, INSIDE);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec()
    {
        return CODEC;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state)
    {
        return BlockRenderType.MODEL;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state)
    {
        return new JammingPotBlockEntity(pos, state);
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved)
    {
        if (state.getBlock() != newState.getBlock())
        {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof JammingPotBlockEntity)
            {
                ItemScatterer.spawn(world, pos, (JammingPotBlockEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
    {
        if (!world.isClient)
        {
            NamedScreenHandlerFactory screenHandlerFactory = ((JammingPotBlockEntity) world.getBlockEntity(pos));

            if (screenHandlerFactory != null)
                player.openHandledScreen(screenHandlerFactory);
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type)
    {
        return validateTicker(type, ModBlockEntities.JAMMING_POT_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }
}
