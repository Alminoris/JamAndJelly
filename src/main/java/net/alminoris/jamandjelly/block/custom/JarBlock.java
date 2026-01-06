package net.alminoris.jamandjelly.block.custom;

import net.alminoris.jamandjelly.integration.arborealnature.item.ANIntegrationItems;
import net.alminoris.jamandjelly.integration.wildfields.item.WFIntegrationItems;
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
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import static net.alminoris.jamandjelly.integration.arborealnature.item.ANIntegrationItems.AN_JAM_NAMES;
import static net.alminoris.jamandjelly.integration.wildfields.item.WFIntegrationItems.WF_JAM_NAMES;

public class JarBlock extends TransparentBlock
{
    public enum Inside implements StringIdentifiable
    {
        APPLE("apple"),
        HONEY("honey"),
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
        PINK_CURRANT("pink_currant"),
        BLUEBERRY("blueberry");

        private final String name;

        Inside(String name) { this.name = name; }

        @Override
        public String asString() { return this.name; }

        public static JarBlock.Inside fromString(String name)
        {
            for (JarBlock.Inside inside : JarBlock.Inside.values())
            {
                if (inside.name.equalsIgnoreCase(name))
                    return inside;
            }
            throw new IllegalArgumentException("No enum constant for name: " + name);
        }
    }

    public static final EnumProperty<Inside> INSIDE = EnumProperty.of("inside", Inside.class);

    public static final IntProperty VARIANT = IntProperty.of("variant", 0, 3);

    public static final BooleanProperty OPEN = BooleanProperty.of("open");

    private static final VoxelShape SHAPE = JarBlock.createCuboidShape(4,0,4,12,12,12);

    public JarBlock()
    {
        super(AbstractBlock.Settings.create()
                .instrument(NoteBlockInstrument.HAT)
                .strength(0.3F)
                .sounds(BlockSoundGroup.GLASS)
                .nonOpaque()
                .allowsSpawning(Blocks::never)
                .solidBlock(Blocks::never)
                .suffocates(Blocks::never)
                .blockVision(Blocks::never));
        this.setDefaultState(this.getStateManager().getDefaultState().with(OPEN, false).with(VARIANT, 0).with(INSIDE, Inside.APPLE));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        builder.add(OPEN, VARIANT, INSIDE);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
    {
        if (!world.isClient)
        {
            Item item = player.getInventory().getMainHandStack().getItem();
            String itemName = Registries.ITEM.getId(item).getPath();
            boolean currentOpen = state.get(OPEN);
            int currentVariant = state.get(VARIANT);
            Inside currentInside = state.get(INSIDE);

            if (player.getInventory().getMainHandStack().isEmpty())
            {
                if(currentOpen)
                    world.playSound(null, pos, ModSounds.SOUND_JAR_CLOSE, SoundCategory.NEUTRAL);
                else
                    world.playSound(null, pos, ModSounds.SOUND_JAR_OPEN, SoundCategory.NEUTRAL);
                world.setBlockState(pos, state.with(OPEN, !currentOpen).with(VARIANT, currentVariant).with(INSIDE, currentInside));
            }

            if (player.getInventory().getMainHandStack().isIn(ModTags.Items.JAM_BOTTLES) && (currentVariant < 3) && currentOpen)
            {
                if (isItemMatchesContent(itemName, currentInside) || (currentVariant == 0))
                {
                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.NEUTRAL);
                    Inside nextInside = switch (itemName)
                    {
                        case "apple_jam_bottle" -> Inside.APPLE;
                        case "sweetberry_jam_bottle" -> Inside.SWEETBERRY;
                        case "melon_jam_bottle" -> Inside.MELON;
                        default -> Inside.HONEY;
                    };

                    if (FabricLoader.getInstance().isModLoaded("arborealnature"))
                    {
                        for (String name : AN_JAM_NAMES)
                        {
                            if (itemName.equals(name+"_jam_bottle"))
                                nextInside = Inside.fromString(name);
                        }
                    }

                    if (FabricLoader.getInstance().isModLoaded("wildfields"))
                    {
                        for (String name : WF_JAM_NAMES)
                        {
                            if (itemName.equals(name+"_jam_bottle"))
                                nextInside = Inside.fromString(name);
                        }
                    }

                    player.getInventory().getMainHandStack().decrement(1);

                    world.setBlockState(pos, state.with(OPEN, currentOpen).with(VARIANT, currentVariant+1).with(INSIDE, nextInside));

                    if (!player.getInventory().insertStack(new ItemStack(Items.GLASS_BOTTLE)))
                        player.dropItem(new ItemStack(Items.GLASS_BOTTLE), false);
                }
            }

            if (player.getInventory().getMainHandStack().isOf(Items.GLASS_BOTTLE) && (currentVariant > 0) && currentOpen)
            {
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL);
                player.getInventory().getMainHandStack().decrement(1);

                world.setBlockState(pos, state.with(OPEN, currentOpen).with(VARIANT, currentVariant-1).with(INSIDE, currentInside));

                Item newItem = switch (currentInside)
                {
                    case APPLE -> ModItems.APPLE_JAM_BOTTLE;
                    case SWEETBERRY -> ModItems.SWEETBERRY_JAM_BOTTLE;
                    case MELON -> ModItems.MELON_JAM_BOTTLE;
                    default -> Items.HONEY_BOTTLE;
                };

                if (FabricLoader.getInstance().isModLoaded("arborealnature"))
                {
                    for (String name : AN_JAM_NAMES)
                    {
                        if (currentInside.asString().equals(name))
                            newItem = ANIntegrationItems.AN_JAM_BOTTLES.get(name);
                    }
                }

                if (FabricLoader.getInstance().isModLoaded("wildfields"))
                {
                    for (String name : WF_JAM_NAMES)
                    {
                        if (currentInside.asString().equals(name))
                            newItem = WFIntegrationItems.WF_JAM_BOTTLES.get(name);
                    }
                }

                if (!player.getInventory().insertStack(new ItemStack(newItem)))
                    player.dropItem(new ItemStack(newItem), false);
            }
        }
        return ActionResult.SUCCESS;
    }

    private boolean isItemMatchesContent(String fullName, Inside content)
    {
        String contentName = content.asString();
        String name;
        if (fullName.split("_").length > 3)
            name = fullName.split("_")[0]+"_"+fullName.split("_")[1];
        else
            name = fullName.split("_")[0];
        return name.equals(contentName);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        return SHAPE;
    }
}