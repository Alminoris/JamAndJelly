package net.alminoris.jamandjelly.item;

import net.alminoris.jamandjelly.JamJelly;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups
{
    public static final ItemGroup JAMANDJELLYTAB = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(JamJelly.MOD_ID, "jajtab"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.jajtab"))
                    .icon(() -> new ItemStack(Items.SWEET_BERRIES)).entries((displayContext, entries) ->
                    {
                        entries.add(ModItems.KITCHEN_KNIFE);
                    }).build());

    public static void registerModItemGroups()
    {

    }
}
