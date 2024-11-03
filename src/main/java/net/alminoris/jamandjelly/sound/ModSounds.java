package net.alminoris.jamandjelly.sound;

import net.alminoris.jamandjelly.JamJelly;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds
{
    public static final SoundEvent BLOCK_PLASTIC_BREAK = registerSoundEvent("block_plastic_break");
    public static final SoundEvent BLOCK_PLASTIC_STEP = registerSoundEvent("block_plastic_step");
    public static final SoundEvent BLOCK_PLASTIC_PLACE = registerSoundEvent("block_plastic_place");
    public static final SoundEvent BLOCK_PLASTIC_HIT = registerSoundEvent("block_plastic_hit");
    public static final SoundEvent BLOCK_PLASTIC_FALL = registerSoundEvent("block_plastic_fall");
    public static final SoundEvent SOUND_CHOP_ON_BOARD = registerSoundEvent("sound_chop_on_board");
    public static final SoundEvent SOUND_JAM_BOILING = registerSoundEvent("sound_jam_boiling");

    public static final BlockSoundGroup PLASTIC_SOUND_GROUP = new BlockSoundGroup(1.0f, 1.0f,
            BLOCK_PLASTIC_BREAK,
            BLOCK_PLASTIC_STEP,
            BLOCK_PLASTIC_PLACE,
            BLOCK_PLASTIC_HIT,
            BLOCK_PLASTIC_FALL
    );

    private static SoundEvent registerSoundEvent(String name)
    {
        Identifier id = Identifier.of(JamJelly.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds()
    {

    }
}
