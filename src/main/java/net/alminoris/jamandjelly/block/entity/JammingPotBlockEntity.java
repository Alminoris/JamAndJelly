package net.alminoris.jamandjelly.block.entity;

import net.alminoris.jamandjelly.block.custom.JammingPotBlock;
import net.alminoris.jamandjelly.integration.arborealnature.item.IntegrationItems;
import net.alminoris.jamandjelly.item.ModItems;
import net.alminoris.jamandjelly.network.BlockPosPayload;
import net.alminoris.jamandjelly.screen.JammingPotScreenHandler;
import net.alminoris.jamandjelly.sound.ModSounds;
import net.alminoris.jamandjelly.util.ModTags;
import net.alminoris.jamandjelly.util.helper.RecipeHelper;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static net.alminoris.jamandjelly.integration.arborealnature.item.IntegrationItems.JAM_NAMES;

public class JammingPotBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPosPayload>, ImplementedInventory
{
    private final DefaultedList<ItemStack> INVENTORY = DefaultedList.ofSize(6, ItemStack.EMPTY);

    private static final int INPUT_SLOT_0 = 0;
    private static final int INPUT_SLOT_1 = 1;
    private static final int INPUT_SLOT_2 = 2;
    private static final int INPUT_SLOT_3 = 3;
    private static final int INPUT_SLOT_4 = 4;
    private static final int OUTPUT_SLOT = 5;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 240;
    private int isFlameOn = 0;

    private ItemStack result = null;
    private JammingPotBlock.Inside newInside = JammingPotBlock.Inside.APPLE;

    private final int soundMaxTicks = 80;
    private int soundTickCounter = 80;

    public JammingPotBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.JAMMING_POT_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate()
        {
            @Override
            public int get(int index)
            {
                return switch (index)
                {
                    case 0 -> JammingPotBlockEntity.this.progress;
                    case 1 -> JammingPotBlockEntity.this.maxProgress;
                    case 2 -> JammingPotBlockEntity.this.isFlameOn;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value)
            {
                switch (index)
                {
                    case 0 -> JammingPotBlockEntity.this.progress = value;
                    case 1 -> JammingPotBlockEntity.this.maxProgress = value;
                    case 2 -> JammingPotBlockEntity.this.isFlameOn = value;
                };
            }

            @Override
            public int size()
            {
                return 3;
            }
        };
    }


    @Override
    public DefaultedList<ItemStack> getItems()
    {
        return INVENTORY;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup)
    {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, INVENTORY, registryLookup);
        nbt.putInt("jamming_pot.progress", progress);
        nbt.putInt("jamming_pot.isFlameOn", isFlameOn);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup)
    {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, INVENTORY, registryLookup);
        nbt.getInt("jamming_pot.progress");
        nbt.getInt("jamming_pot.isFlameOn");
    }

    @Override
    public BlockPosPayload getScreenOpeningData(ServerPlayerEntity serverPlayerEntity)
    {
        return new BlockPosPayload(this.pos);
    }

    @Override
    public Text getDisplayName()
    {
        return Text.translatable("block.jamandjelly.jamming_pot");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player)
    {
        return new JammingPotScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state)
    {
        if (world.isClient)
            return;

        JammingPotBlock.Variant currentVariant;
        boolean currentSupport;

        if (this.hasLadle())
            currentVariant = JammingPotBlock.Variant.NORMAL;
        else
            currentVariant = JammingPotBlock.Variant.CLOSED;

        if (this.hasFlame(world, pos))
        {
            currentSupport = true;
            this.isFlameOn = 1;
        }
        else
        {
            currentSupport = false;
            this.isFlameOn = 0;
        }

        if (isOutputSlotEmptyOrReceivable())
        {
            final RecipeHelper recipeHelper = new RecipeHelper("jamming_pot",
                    getStack(INPUT_SLOT_0),
                    getStack(INPUT_SLOT_1),
                    getStack(INPUT_SLOT_2),
                    getStack(INPUT_SLOT_3),
                    getStack(INPUT_SLOT_4));

            if (this.hasRecipe(recipeHelper) && (this.isFlameOn == 1) && this.hasLadle())
            {
                currentVariant = JammingPotBlock.Variant.INSIDED;
                if (soundTickCounter == soundMaxTicks)
                {
                    world.playSound(null, pos, ModSounds.SOUND_JAM_BOILING, SoundCategory.NEUTRAL);
                    soundTickCounter = 0;
                } else soundTickCounter++;
                this.increaseCraftProgress();
                markDirty(world, pos, state);

                if (hasCraftingFinished())
                {
                    currentVariant = JammingPotBlock.Variant.NORMAL;
                    this.craftItem();
                    this.resetProgress();
                }
            }
            else this.resetProgress();
        }
        else
        {
            this.resetProgress();
            markDirty(world, pos, state);
        }

        world.setBlockState(pos, state
                .with(JammingPotBlock.VARIANT, currentVariant)
                .with(JammingPotBlock.SUPPORT, currentSupport)
                .with(JammingPotBlock.INSIDE, newInside));
    }

    private boolean hasLadle()
    {
        return getStack(INPUT_SLOT_4).getItem() == ModItems.LADLE;
    }

    private boolean hasFlame(World world, BlockPos pos)
    {
        BlockState belowState = world.getBlockState(pos.down());

        return belowState.isOf(Blocks.CAMPFIRE);
    }

    private void resetProgress()
    {
        this.progress = 0;
    }

    private void craftItem()
    {

        this.removeStack(INPUT_SLOT_1, 1);
        if (this.getStack(INPUT_SLOT_0).isIn(ModTags.Items.JUICE_BOTTLES))
            this.removeStack(INPUT_SLOT_2, 3);
        else
            this.removeStack(INPUT_SLOT_2, 1);
        this.removeStack(INPUT_SLOT_0, 1);
        this.removeStack(INPUT_SLOT_3, 1);

        ItemStack ladleItem = this.getStack(INPUT_SLOT_4);
        if (ladleItem.getDamage() < ladleItem.getMaxDamage() - 1)
        {
            ladleItem.setDamage(ladleItem.getDamage() + 1);
            this.setStack(INPUT_SLOT_4, ladleItem);
        }
        else this.removeStack(INPUT_SLOT_4, 1);

        this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(),
                getStack(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private boolean hasCraftingFinished()
    {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress()
    {
        progress++;
    }

    private boolean hasRecipe(RecipeHelper recipeHelper)
    {
        if (!recipeHelper.hasInput()) return false;
        result = recipeHelper.getResult();

        boolean hasInputRes = recipeHelper.hasInput() && canInsertAmountIntoOutputSlot(result)
                && canInsertItemIntoOutputSlot(result.getItem());

        if (hasInputRes)
        {
            if (result.getItem() == ModItems.APPLE_JAM_BOTTLE || result.getItem() == ModItems.APPLE_JELLY)
                newInside = JammingPotBlock.Inside.APPLE;
            else if (result.getItem() == ModItems.SWEETBERRY_JAM_BOTTLE || result.getItem() == ModItems.SWEETBERRY_JELLY)
                newInside = JammingPotBlock.Inside.SWEETBERRY;
            else if (result.getItem() == ModItems.MELON_JAM_BOTTLE || result.getItem() == ModItems.MELON_JELLY)
                newInside = JammingPotBlock.Inside.MELON;
            else
            {
                if (FabricLoader.getInstance().isModLoaded("arborealnature"))
                {
                    for (String name : JAM_NAMES)
                    {
                        if (result.getItem() == IntegrationItems.JAM_BOTTLES.get(name) || result.getItem() == IntegrationItems.JELLY.get(name))
                        {
                            newInside = JammingPotBlock.Inside.fromString(name);
                            break;
                        }
                    }
                }
            }
        }

        return hasInputRes;
    }

    private boolean canInsertItemIntoOutputSlot(Item item)
    {
        return this.getStack(OUTPUT_SLOT).getItem() == item || getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result)
    {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean isOutputSlotEmptyOrReceivable()
    {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }
}