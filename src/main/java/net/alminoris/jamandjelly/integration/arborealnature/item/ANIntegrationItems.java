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

public class ANIntegrationItems
{
    public static final String[] AN_JAM_NAMES = { "quince", "plum", "mango", "figs", "viburnum", "white_mulberry",
            "wild_cherry", "bilberry", "blackberry", "pink_currant", "blueberry" };
    public static final Dictionary<String, Item> AN_JAM_BOTTLES = new Hashtable<>();
    public static final Dictionary<String, Item> AN_JUICE_BOTTLES = new Hashtable<>();
    public static final Dictionary<String, Item> AN_JAM_CHOPPED = new Hashtable<>();
    public static final Dictionary<String, Item> AN_JELLY = new Hashtable<>();

    static
    {
        for (String name : AN_JAM_NAMES)
        {
            AN_JAM_BOTTLES.put(name, registerItem(name + "_jam_bottle", new HoneyBottleItem(
                    new Item.Settings().maxCount(16).food(ANIntegrationFoodComponents.AN_JAM_FOOD_COMPONENTS.get(name)))));

            AN_JUICE_BOTTLES.put(name, registerItem("glass_" + name + "_juice_bottle", new HoneyBottleItem(
                    new Item.Settings().maxCount(16).food(ANIntegrationFoodComponents.AN_JUICE_FOOD_COMPONENTS.get(name)))));

            AN_JAM_CHOPPED.put(name, registerItem(name + "_chopped",
                    new Item(new Item.Settings().food(FoodComponents.SWEET_BERRIES))));

            AN_JELLY.put(name, registerItem(name + "_jelly",
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
