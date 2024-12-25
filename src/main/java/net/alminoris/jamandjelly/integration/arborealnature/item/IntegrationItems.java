package net.alminoris.jamandjelly.integration.arborealnature.item;

import net.alminoris.jamandjelly.JamJelly;
import net.alminoris.jamandjelly.item.ModFoodComponents;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Dictionary;
import java.util.Hashtable;

public class IntegrationItems
{
    public static final String[] JAM_NAMES = { "quince", "plum", "mango", "figs", "viburnum", "white_mulberry",
            "wild_cherry", "bilberry", "blackberry", "pink_currant", "blueberry" };
    public static final Dictionary<String, Item> JAM_BOTTLES = new Hashtable<>();
    public static final Dictionary<String, Item> JUICE_BOTTLES = new Hashtable<>();
    public static final Dictionary<String, Item> JAM_CHOPPED = new Hashtable<>();
    public static final Dictionary<String, Item> JELLY = new Hashtable<>();

    static
    {
        for (String name : JAM_NAMES)
        {
            JAM_BOTTLES.put(name, registerItem(name + "_jam_bottle", new HoneyBottleItem(
                    new Item.Settings().maxCount(16).food(IntegrationFoodComponents.JAM_FOOD_COMPONENTS.get(name)))));

            JUICE_BOTTLES.put(name, registerItem("glass_" + name + "_juice_bottle", new HoneyBottleItem(
                    new Item.Settings().maxCount(16).food(IntegrationFoodComponents.JUICE_FOOD_COMPONENTS.get(name)))));

            JAM_CHOPPED.put(name, registerItem(name + "_chopped",
                    new Item(new Item.Settings().food(FoodComponents.SWEET_BERRIES))));

            JELLY.put(name, registerItem(name + "_jelly",
                    new Item(new Item.Settings().food(ModFoodComponents.JELLY))));
        }
    }

    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registries.ITEM, Identifier.of(JamJelly.MOD_ID, name), item);
    }

    public static void registerItems()
    {

    }
}
