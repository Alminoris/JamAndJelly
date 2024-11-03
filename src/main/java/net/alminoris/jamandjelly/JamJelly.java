package net.alminoris.jamandjelly;

import net.alminoris.jamandjelly.block.ModBlocks;
import net.alminoris.jamandjelly.block.entity.ModBlockEntities;
import net.alminoris.jamandjelly.integration.arborealnature.block.IntegrationBlocks;
import net.alminoris.jamandjelly.integration.arborealnature.item.IntegrationItems;
import net.alminoris.jamandjelly.item.ModItemGroups;
import net.alminoris.jamandjelly.item.ModItems;
import net.alminoris.jamandjelly.screen.ModScreenHandlers;
import net.alminoris.jamandjelly.sound.ModSounds;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
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
			IntegrationItems.registerItems();
			IntegrationBlocks.registerBlocks();
		}
		ModItemGroups.registerItemGroups();
		ModSounds.registerSounds();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
	}
}