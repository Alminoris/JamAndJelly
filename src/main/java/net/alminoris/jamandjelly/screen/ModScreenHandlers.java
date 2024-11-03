package net.alminoris.jamandjelly.screen;

import net.alminoris.jamandjelly.JamJelly;
import net.alminoris.jamandjelly.network.BlockPosPayload;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers
{
    public static final ScreenHandlerType<JammingPotScreenHandler> JAMMING_POT_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(JamJelly.MOD_ID, "jamming_pot"),
                    new ExtendedScreenHandlerType<>(JammingPotScreenHandler::new, BlockPosPayload.PACKET_CODEC));

    public static final ScreenHandlerType<ChoppingBoardScreenHandler> CHOPPING_BOARD_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(JamJelly.MOD_ID, "chopping_board"),
                    new ExtendedScreenHandlerType<>(ChoppingBoardScreenHandler::new, BlockPosPayload.PACKET_CODEC));

    public static void registerScreenHandlers()
    {

    }
}
