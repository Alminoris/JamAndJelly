package net.alminoris.jamandjelly.block.custom;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HoneyBlock;
import net.minecraft.entity.Entity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;

public class JamBlock extends HoneyBlock
{
    public JamBlock()
    {
        super(AbstractBlock.Settings.copy(Blocks.HONEY_BLOCK));
    }
}
