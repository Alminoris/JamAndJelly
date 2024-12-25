package net.alminoris.jamandjelly.item;

import net.alminoris.jamandjelly.JamJelly;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.item.*;
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

    public static final Item APPLE_JELLY = registerItem("apple_jelly", new Item(new Item.Settings().food(ModFoodComponents.JELLY)));
    public static final Item SWEETBERRY_JELLY = registerItem("sweetberry_jelly", new Item(new Item.Settings().food(ModFoodComponents.JELLY)));
    public static final Item MELON_JELLY = registerItem("melon_jelly", new Item(new Item.Settings().food(ModFoodComponents.JELLY)));

    public static final Item GLASS_JUICE_BOTTLE = registerItem("glass_juice_bottle", new Item(new Item.Settings()));
    public static final Item GLASS_APPLE_JUICE_BOTTLE = registerItem("glass_apple_juice_bottle", new HoneyBottleItem(new Item.Settings().maxCount(16).food(ModFoodComponents.APPLE_JUICE)));
    public static final Item GLASS_SWEETBERRY_JUICE_BOTTLE = registerItem("glass_sweetberry_juice_bottle", new HoneyBottleItem(new Item.Settings().maxCount(16).food(ModFoodComponents.SWEETBERRY_JUICE)));
    public static final Item GLASS_MELON_JUICE_BOTTLE = registerItem("glass_melon_juice_bottle", new HoneyBottleItem(new Item.Settings().maxCount(16).food(ModFoodComponents.MELON_JUICE)));

    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registries.ITEM, Identifier.of(JamJelly.MOD_ID, name), item);
    }

    public static void registerItems()
    {

    }
}
