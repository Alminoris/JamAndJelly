package net.alminoris.jamandjelly.screen;

import net.alminoris.jamandjelly.block.entity.ChoppingBoardBlockEntity;
import net.alminoris.jamandjelly.block.entity.JammingPotBlockEntity;
import net.alminoris.jamandjelly.item.ModItems;
import net.alminoris.jamandjelly.network.BlockPosPayload;
import net.alminoris.jamandjelly.util.ModTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class ChoppingBoardScreenHandler extends ScreenHandler
{
    private final Inventory INVENTORY;
    private final PropertyDelegate propertyDelegate;
    public final ChoppingBoardBlockEntity blockEntity;

    //Client
    public ChoppingBoardScreenHandler(int syncId, PlayerInventory inventory, BlockPosPayload payload)
    {
        this(syncId, inventory, (ChoppingBoardBlockEntity) inventory.player.getWorld().getBlockEntity(payload.pos()),
                new ArrayPropertyDelegate(2));
    }


    //Server
    public ChoppingBoardScreenHandler(int syncId, PlayerInventory playerInventory,
                                      ChoppingBoardBlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate)
    {
        super(ModScreenHandlers.CHOPPING_BOARD_SCREEN_HANDLER, syncId);
        checkSize(blockEntity, 3);
        this.INVENTORY = blockEntity;
        INVENTORY.onOpen(playerInventory.player);
        this.propertyDelegate = arrayPropertyDelegate;
        this.blockEntity = blockEntity;

        this.addSlot(new Slot(INVENTORY, 0, 26, 35));
        this.addSlot(new Slot(INVENTORY, 1, 71, 35));
        this.addSlot(new Slot(INVENTORY, 2, 130, 35));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(arrayPropertyDelegate);
    }

    public boolean isCrafting()
    {
        return propertyDelegate.get(0) > 0;
    }

    public int getScaledProgress()
    {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);
        int progressArrowSize = 22;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot)
    {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);

        if (slot != null && slot.hasStack())
        {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            if (invSlot < this.INVENTORY.size())
            {
                if (invSlot == 2)
                {
                    if (!this.insertItem(originalStack, this.INVENTORY.size(), this.slots.size(), true))
                        return ItemStack.EMPTY;
                }
                else if (!this.insertItem(originalStack, this.INVENTORY.size(), this.slots.size(), false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else
            {
                if (originalStack.isOf(ModItems.KITCHEN_KNIFE))
                {
                    if (!this.insertItem(originalStack, 0, 1, false)) return ItemStack.EMPTY;
                }
                else if (originalStack.isIn(ModTags.Items.JAM_CHOPPING_INGREDIENTS))
                {
                    if (!this.insertItem(originalStack, 1, 2, false)) return ItemStack.EMPTY;
                }
                else
                    return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty())
            {
                slot.setStack(ItemStack.EMPTY);
            } else
            {
                slot.markDirty();
            }
        }

        return newStack;
    }


    @Override
    public boolean canUse(PlayerEntity player)
    {
        return this.INVENTORY.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory)
    {
        for (int i = 0; i < 3; ++i)
        {
            for (int l = 0; l < 9; ++l)
            {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory)
    {
        for (int i = 0; i < 9; ++i)
        {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
