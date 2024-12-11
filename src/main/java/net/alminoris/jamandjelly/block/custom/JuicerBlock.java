package net.alminoris.jamandjelly.block.custom;

import net.alminoris.jamandjelly.integration.arborealnature.item.IntegrationItems;
import net.alminoris.jamandjelly.item.ModItems;
import net.alminoris.jamandjelly.sound.ModSounds;
import net.alminoris.jamandjelly.util.ModTags;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import static net.alminoris.jamandjelly.integration.arborealnature.item.IntegrationItems.JAM_NAMES;

public class JuicerBlock extends Block
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
        PINK_CURRANT("pink_currant");

        private final String name;

        Inside(String name) { this.name = name; }

        @Override
        public String asString() { return this.name; }

        public static JuicerBlock.Inside fromString(String name)
        {
            for (JuicerBlock.Inside inside : JuicerBlock.Inside.values())
            {
                if (inside.name.equalsIgnoreCase(name))
                    return inside;
            }
            throw new IllegalArgumentException("No enum constant for name: " + name);
        }
    }

    public static final EnumProperty<Inside> INSIDE = EnumProperty.of("inside", Inside.class);

    public static final IntProperty VARIANT = IntProperty.of("variant", 0, 6);

    private static final VoxelShape SHAPE = JuicerBlock.createCuboidShape(3,0,8,13,12,15);

    private static final VoxelShape SHAPE1 = JuicerBlock.createCuboidShape(2,0,1,14,4,8);

    public JuicerBlock()
    {
        super(Settings.create()
                .instrument(NoteBlockInstrument.BASS)
                .strength(3.0F)
                .sounds(BlockSoundGroup.STONE)
                .nonOpaque()
                .allowsSpawning(Blocks::never)
                .solidBlock(Blocks::never)
                .suffocates(Blocks::never)
                .blockVision(Blocks::never));
        this.setDefaultState(this.getStateManager().getDefaultState().with(VARIANT, 0).with(INSIDE, Inside.APPLE));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        builder.add(VARIANT, INSIDE);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
    {
        if (!world.isClient)
        {
            Item item = player.getInventory().getMainHandStack().getItem();
            String itemName = Registries.ITEM.getId(item).getPath();
            int currentVariant = state.get(VARIANT);
            Inside currentInside = state.get(INSIDE);

            if (player.getInventory().getMainHandStack().isEmpty())
            {
                world.setBlockState(pos, state.with(VARIANT, currentVariant).with(INSIDE, currentInside));
            }

            if (player.getInventory().getMainHandStack().isIn(ModTags.Items.JAM_CHOPPING_INGREDIENTS) && (currentVariant < 6))
            {
                if (currentInside.asString().equals(itemName) || itemName.equals("sweet_berries") || itemName.equals("melon_slice") || (currentVariant == 0))
                {
                    world.playSound(null, pos, ModSounds.SOUND_JUICER, SoundCategory.NEUTRAL);
                    Inside nextInside = switch (itemName)
                    {
                        case "apple" -> Inside.APPLE;
                        case "sweet_berries" -> Inside.SWEETBERRY;
                        default -> Inside.MELON;
                    };

                    if (FabricLoader.getInstance().isModLoaded("arborealnature"))
                    {
                        for (String name : JAM_NAMES)
                        {
                            if (itemName.equals(name))
                                nextInside = Inside.fromString(name);
                        }
                    }

                    player.getInventory().getMainHandStack().decrement(1);

                    world.setBlockState(pos, state.with(VARIANT, currentVariant+1).with(INSIDE, nextInside));
                }
            }

            if (player.getInventory().getMainHandStack().isOf(ModItems.GLASS_JUICE_BOTTLE) && (currentVariant > 0))
            {
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL);
                player.getInventory().getMainHandStack().decrement(1);

                world.setBlockState(pos, state.with(VARIANT, currentVariant-1).with(INSIDE, currentInside));

                Item newItem = switch (currentInside)
                {
                    case APPLE -> ModItems.GLASS_APPLE_JUICE_BOTTLE;
                    case SWEETBERRY -> ModItems.GLASS_SWEETBERRY_JUICE_BOTTLE;
                    default -> ModItems.GLASS_MELON_JUICE_BOTTLE;
                };

                if (FabricLoader.getInstance().isModLoaded("arborealnature"))
                {
                    for (String name : JAM_NAMES)
                    {
                        if (currentInside.asString().equals(name))
                            newItem = IntegrationItems.JUICE_BOTTLES.get(name);
                    }
                }

                if (!player.getInventory().insertStack(new ItemStack(newItem)))
                    player.dropItem(new ItemStack(newItem), false);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        return VoxelShapes.union(SHAPE, SHAPE1);
    }
}