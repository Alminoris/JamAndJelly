package net.alminoris.jamandjelly.item;

import net.alminoris.jamandjelly.JamJelly;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems
{
    public static final Item KITCHEN_KNIFE = registerItem("kitchen_knife", new ToolItem(ToolMaterials.IRON, new Item.Settings().maxCount(1)));
    public static final Item LADLE = registerItem("ladle", new ToolItem(ToolMaterials.IRON, new Item.Settings().maxCount(1)));

    public static final Item GELATIN = registerItem("gelatin", new Item(new Item.Settings()));
    public static final Item APPLE_JAM_BOTTLE = registerItem("apple_jam_bottle", new HoneyBottleItem(new Item.Settings().maxCount(16).food(ModFoodComponents.APPLE_JAM)));
    public static final Item SWEETBERRY_JAM_BOTTLE = registerItem("sweetberry_jam_bottle", new HoneyBottleItem(new Item.Settings().maxCount(16).food(ModFoodComponents.SWEETBERRY_JAM)));
    public static final Item MELON_JAM_BOTTLE = registerItem("melon_jam_bottle", new HoneyBottleItem(new Item.Settings().maxCount(16).food(ModFoodComponents.MELON_JAM)));

    public static final Item APPLE_CHOPPED = registerItem("apple_chopped", new Item(new Item.Settings().food(FoodComponents.SWEET_BERRIES)));
    public static final Item SWEETBERRY_CHOPPED = registerItem("sweetberry_chopped", new Item(new Item.Settings().food(FoodComponents.SWEET_BERRIES)));
    public static final Item MELON_CHOPPED = registerItem("melon_chopped", new Item(new Item.Settings().food(FoodComponents.SWEET_BERRIES)));

    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registries.ITEM, Identifier.of(JamJelly.MOD_ID, name), item);
    }

    public static void registerItems()
    {

    }
}
