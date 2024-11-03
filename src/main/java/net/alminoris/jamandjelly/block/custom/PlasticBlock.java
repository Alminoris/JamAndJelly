package net.alminoris.jamandjelly.block.custom;

import net.alminoris.jamandjelly.sound.ModSounds;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

public class PlasticBlock extends Block
{
    public PlasticBlock()
    {
        super(AbstractBlock.Settings.create().strength(2.0f, 6.0f).sounds(ModSounds.PLASTIC_SOUND_GROUP).requiresTool());
    }
}
