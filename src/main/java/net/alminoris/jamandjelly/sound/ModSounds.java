package net.alminoris.jamandjelly.sound;

import net.alminoris.jamandjelly.JamJelly;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds
{
    //public static final SoundEvent SOUND_SQUIRREL_DEATH = registerSoundEvent("sound_squirrel_death");

    private static SoundEvent registerSoundEvent(String name)
    {
        Identifier id = Identifier.of(JamJelly.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerModSounds()
    {

    }
}
