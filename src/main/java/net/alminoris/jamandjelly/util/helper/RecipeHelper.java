package net.alminoris.jamandjelly.util.helper;

import net.alminoris.jamandjelly.JamJelly;
import net.alminoris.jamandjelly.block.custom.JammingPotBlock;
import net.alminoris.jamandjelly.integration.arborealnature.item.IntegrationItems;
import net.alminoris.jamandjelly.item.ModItems;
import net.alminoris.jamandjelly.util.ModTags;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.List;

import static net.alminoris.jamandjelly.integration.arborealnature.item.IntegrationItems.JAM_NAMES;

public class RecipeHelper
{
    private final List<ItemStack> slots;
    private final String recipeType;
    public RecipeHelper(String recipeType, ItemStack... slots)
    {
        this.recipeType = recipeType;
        this.slots = List.of(slots);
    }

    public boolean hasInput()
    {
        switch (this.recipeType)
        {
            case "jamming_pot":
                return slots.get(0).isIn(ModTags.Items.JAM_INGREDIENTS) &&
                        slots.get(1).getItem() == Items.SUGAR &&
                        slots.get(2).getItem() == ModItems.GELATIN &&
                        slots.get(3).getItem() == Items.GLASS_BOTTLE;
            case "chopping_board":
                return slots.get(0).isIn(ModTags.Items.JAM_CHOPPING_INGREDIENTS);
            default: return false;
        }
    }

    public ItemStack getResult()
    {
        if ("jamming_pot".equals(this.recipeType))
        {
            ItemStack inputItem = slots.get(0);
            if (inputItem.getItem() == ModItems.APPLE_CHOPPED)
                return new ItemStack(ModItems.APPLE_JAM_BOTTLE);
            if (inputItem.getItem() == ModItems.SWEETBERRY_CHOPPED)
                return new ItemStack(ModItems.SWEETBERRY_JAM_BOTTLE);
            if (inputItem.getItem() == ModItems.MELON_CHOPPED)
                return new ItemStack(ModItems.MELON_JAM_BOTTLE);
            if (FabricLoader.getInstance().isModLoaded("arborealnature"))
            {
                for (String name : JAM_NAMES)
                {
                    if (inputItem.getItem() == IntegrationItems.JAM_CHOPPED.get(name))
                        return new ItemStack(IntegrationItems.JAM_BOTTLES.get(name));
                }
            }
        }
        else if ("chopping_board".equals(this.recipeType))
        {
            ItemStack inputItem = slots.get(0);
            if (inputItem.getItem() == Items.APPLE)
                return new ItemStack(ModItems.APPLE_CHOPPED);
            if (inputItem.getItem() == Items.SWEET_BERRIES)
                return new ItemStack(ModItems.SWEETBERRY_CHOPPED);
            if (inputItem.getItem() == Items.MELON_SLICE)
                return new ItemStack(ModItems.MELON_CHOPPED);
            if (FabricLoader.getInstance().isModLoaded("arborealnature"))
            {
                for (String name : JAM_NAMES)
                {
                    if (inputItem.getItem() == Registries.ITEM.get(Identifier.of("arborealnature", name)))
                        return new ItemStack(IntegrationItems.JAM_CHOPPED.get(name));
                }
            }
        }

        return null;
    }
}
