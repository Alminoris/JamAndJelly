package net.alminoris.jamandjelly.block.entity;

import net.alminoris.jamandjelly.JamJelly;
import net.alminoris.jamandjelly.block.ModBlocks;
import net.alminoris.jamandjelly.block.custom.ChoppingBoardBlock;
import net.alminoris.jamandjelly.block.custom.JammingPotBlock;
import net.alminoris.jamandjelly.item.ModItems;
import net.alminoris.jamandjelly.network.BlockPosPayload;
import net.alminoris.jamandjelly.screen.ChoppingBoardScreenHandler;
import net.alminoris.jamandjelly.screen.JammingPotScreenHandler;
import net.alminoris.jamandjelly.sound.ModSounds;
import net.alminoris.jamandjelly.util.helper.RecipeHelper;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.Registries;
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

public class ChoppingBoardBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPosPayload>, ImplementedInventory
{
    private final DefaultedList<ItemStack> INVENTORY = DefaultedList.ofSize(3, ItemStack.EMPTY);

    private static final int INPUT_SLOT_0 = 0;
    private static final int INPUT_SLOT_1 = 1;
    private static final int OUTPUT_SLOT = 2;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 110;

    private String name;

    private ItemStack result = null;

    private final int soundMaxTicks = 4;
    private int soundTickCounter = 4;

    public ChoppingBoardBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.CHOPPING_BOARD_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate()
        {
            @Override
            public int get(int index)
            {
                return switch (index)
                {
                    case 0 -> ChoppingBoardBlockEntity.this.progress;
                    case 1 -> ChoppingBoardBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value)
            {
                switch (index)
                {
                    case 0 -> ChoppingBoardBlockEntity.this.progress = value;
                    case 1 -> ChoppingBoardBlockEntity.this.maxProgress = value;
                };
            }

            @Override
            public int size()
            {
                return 2;
            }
        };
    }

    public ItemStack getRenderStack()
    {
        if (this.getStack(OUTPUT_SLOT).isEmpty())
        {
            return this.getStack(INPUT_SLOT_1);
        }
        else return this.getStack(OUTPUT_SLOT);
    }

    @Override
    public void markDirty()
    {
        world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        super.markDirty();
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
        nbt.putInt("chopping_board.progress", progress);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup)
    {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, INVENTORY, registryLookup);
        nbt.getInt("chopping_board.progress");
    }

    @Override
    public BlockPosPayload getScreenOpeningData(ServerPlayerEntity serverPlayerEntity)
    {
        return new BlockPosPayload(this.pos);
    }

    @Override
    public Text getDisplayName()
    {
        return Text.translatable("block.jamandjelly." + name);
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player)
    {
        return new ChoppingBoardScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state)
    {
        if (world.isClient)
            return;

        boolean currentKnife;

        if (isOutputSlotEmptyOrReceivable())
        {
            final RecipeHelper recipeHelper = new RecipeHelper("chopping_board", getStack(INPUT_SLOT_1));
            if (this.hasRecipe(recipeHelper) && hasKnife())
            {
                if (soundTickCounter == soundMaxTicks)
                {
                    world.playSound(null, pos, ModSounds.SOUND_CHOP_ON_BOARD, SoundCategory.NEUTRAL);
                    soundTickCounter = 0;
                } else soundTickCounter++;

                this.increaseCraftProgress();
                markDirty(world, pos, state);

                if (hasCraftingFinished())
                {
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

        name = Registries.BLOCK.getId(state.getBlock()).getPath();

        if (hasKnife()) currentKnife = true;
        else currentKnife = false;

        world.setBlockState(pos, state.with(ChoppingBoardBlock.KNIFE, currentKnife));
    }

    private boolean hasKnife()
    {
        return getStack(INPUT_SLOT_0).getItem() == ModItems.KITCHEN_KNIFE;
    }

    private void resetProgress()
    {
        this.progress = 0;
    }

    private void craftItem()
    {
        this.removeStack(INPUT_SLOT_1, 1);

        ItemStack knife = this.getStack(INPUT_SLOT_0);
        if (knife.getDamage() < knife.getMaxDamage() - 1)
        {
            knife.setDamage(knife.getDamage() + 1);
            this.setStack(INPUT_SLOT_0, knife);
        }
        else this.removeStack(INPUT_SLOT_0, 1);

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
        return recipeHelper.hasInput() && canInsertAmountIntoOutputSlot(result)
                && canInsertItemIntoOutputSlot(result.getItem());
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

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket()
    {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup)
    {
        return createNbt(registryLookup);
    }
}
