package net.alminoris.jamandjelly.screen;

import net.alminoris.jamandjelly.block.entity.JammingPotBlockEntity;
import net.alminoris.jamandjelly.item.ModItems;
import net.alminoris.jamandjelly.network.BlockPosPayload;
import net.alminoris.jamandjelly.util.ModTags;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class JammingPotScreenHandler extends ScreenHandler
{
    private final Inventory INVENTORY;
    private final PropertyDelegate propertyDelegate;
    public final JammingPotBlockEntity blockEntity;

    //Client
    public JammingPotScreenHandler(int syncId, PlayerInventory inventory, BlockPosPayload payload)
    {
        this(syncId, inventory, (JammingPotBlockEntity) inventory.player.getWorld().getBlockEntity(payload.pos()),
                new ArrayPropertyDelegate(3));
    }


    //Server
    public JammingPotScreenHandler(int syncId, PlayerInventory playerInventory,
                                     JammingPotBlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate)
    {
        super(ModScreenHandlers.JAMMING_POT_SCREEN_HANDLER, syncId);
        checkSize(blockEntity, 6);
        this.INVENTORY = blockEntity;
        INVENTORY.onOpen(playerInventory.player);
        this.propertyDelegate = arrayPropertyDelegate;
        this.blockEntity = blockEntity;

        this.addSlot(new Slot(INVENTORY, 0, 54, 37));
        this.addSlot(new Slot(INVENTORY, 1, 34, 37));
        this.addSlot(new Slot(INVENTORY, 2, 44, 17));
        this.addSlot(new Slot(INVENTORY, 3, 94, 16));
        this.addSlot(new Slot(INVENTORY, 4, 94, 46));
        this.addSlot(new Slot(INVENTORY, 5, 140, 31));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(arrayPropertyDelegate);
    }

    public boolean isCrafting()
    {
        return propertyDelegate.get(0) > 0 && (propertyDelegate.get(2) == 1);
    }

    public boolean isFlaming()
    {
        return (propertyDelegate.get(2) == 1);
    }

    public int getScaledProgress()
    {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);  // Max Progress
        int progressArrowSize = 48; // This is the width in pixels of your arrow

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
                if (invSlot == 5)
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
                if (originalStack.isIn(ModTags.Items.JAM_INGREDIENTS))
                {
                    if (!this.insertItem(originalStack, 0, 1, false)) return ItemStack.EMPTY;
                }
                else if (originalStack.isOf(Items.SUGAR))
                {
                    if (!this.insertItem(originalStack, 1, 2, false)) return ItemStack.EMPTY;
                }
                else if (originalStack.isOf(ModItems.GELATIN))
                {
                    if (!this.insertItem(originalStack, 2, 3, false)) return ItemStack.EMPTY;
                }
                else if (originalStack.isOf(Items.GLASS_BOTTLE))
                {
                    if (!this.insertItem(originalStack, 3, 4, false)) return ItemStack.EMPTY;
                }
                else if (originalStack.isOf(ModItems.LADLE))
                {
                    if (!this.insertItem(originalStack, 4, 5, false)) return ItemStack.EMPTY;
                }
                else
                    return ItemStack.EMPTY;
            }

            // Update slot stack state
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
