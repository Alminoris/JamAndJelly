package net.alminoris.jamandjelly;

import net.alminoris.jamandjelly.block.ModBlocks;
import net.alminoris.jamandjelly.block.entity.ModBlockEntities;
import net.alminoris.jamandjelly.integration.arborealnature.block.ANIntegrationBlocks;
import net.alminoris.jamandjelly.integration.arborealnature.item.ANIntegrationItems;
import net.alminoris.jamandjelly.item.ModItemGroups;
import net.alminoris.jamandjelly.item.ModItems;
import net.alminoris.jamandjelly.screen.ModScreenHandlers;
import net.alminoris.jamandjelly.sound.ModSounds;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JamJelly implements ModInitializer
{
	public static final String MOD_ID = "jamandjelly";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize()
	{
		ModItems.registerItems();
		ModBlocks.registerBlocks();
		if (FabricLoader.getInstance().isModLoaded("arborealnature"))
		{
			ANIntegrationItems.registerItems();
			ANIntegrationBlocks.registerBlocks();
		}
		ModItemGroups.registerItemGroups();
		ModSounds.registerSounds();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
	}
}