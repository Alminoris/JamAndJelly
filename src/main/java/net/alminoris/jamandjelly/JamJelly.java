package net.alminoris.jamandjelly;

import net.alminoris.jamandjelly.block.ModBlocks;
import net.alminoris.jamandjelly.item.ModItemGroups;
import net.alminoris.jamandjelly.item.ModItems;
import net.alminoris.jamandjelly.sound.ModSounds;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JamJelly implements ModInitializer
{
	public static final String MOD_ID = "jamandjelly";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize()
	{
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerModItemGroups();
		ModSounds.registerModSounds();
	}
}