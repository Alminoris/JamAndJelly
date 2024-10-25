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
        public static final TagKey<Item> EXAMPLE_ITEM_TAG = createTag("example_item_tag");

        private static TagKey<Item> createTag(String name)
        {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(JamJelly.MOD_ID, name));
        }
    }
}
