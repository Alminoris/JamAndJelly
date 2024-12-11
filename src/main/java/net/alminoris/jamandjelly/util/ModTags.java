package net.alminoris.jamandjelly.util;

import net.alminoris.jamandjelly.JamJelly;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags
{
    public static class Blocks
    {
        public static final TagKey<Block> EXAMPLE_BLOCK_TAG = createTag("example_block_tag");

        private static TagKey<Block> createTag(String name)
        {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(JamJelly.MOD_ID, name));
        }
    }

    public static class Items
    {
        public static final TagKey<Item> JAM_BOTTLES = createTag("jam_bottles");

        public static final TagKey<Item> JUICE_BOTTLES = createTag("juice_bottles");

        public static final TagKey<Item> JAM_INGREDIENTS = createTag("jam_ingredients");

        public static final TagKey<Item> JAM_CHOPPING_INGREDIENTS = createTag("jam_chopping_ingredients");

        private static TagKey<Item> createTag(String name)
        {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(JamJelly.MOD_ID, name));
        }
    }
}
