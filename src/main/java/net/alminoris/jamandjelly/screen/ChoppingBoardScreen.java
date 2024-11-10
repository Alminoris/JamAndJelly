package net.alminoris.jamandjelly.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.alminoris.jamandjelly.JamJelly;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Objects;

public class ChoppingBoardScreen extends HandledScreen<ChoppingBoardScreenHandler>
{
    private final String NAME = Registries.BLOCK.getId(Objects.requireNonNull(handler.blockEntity.getWorld())
            .getBlockState(handler.blockEntity.getPos()).getBlock()).getPath();

    private final Identifier TEXTURE = Identifier.of(JamJelly.MOD_ID, "textures/gui/"+ NAME +".png");;

    private final Dictionary<String, Integer> WOOD_COLORS = new Hashtable<>()
    {{
        put("chopping_board_oak", 0x836b3f);
        put("chopping_board_birch", 0xbdab77);
        put("chopping_board_spruce", 0x694f30);
        put("chopping_board_jungle", 0x937143);
        put("chopping_board_acacia", 0x954727);
        put("chopping_board_dark_oak", 0x40321f);
        put("chopping_board_crimson", 0x712f4a);
        put("chopping_board_warped", 0x408d8b);
        put("chopping_board_mangrove", 0x662b2b);
        put("chopping_board_cherry", 0xcb7075);
        put("chopping_board_bamboo", 0xccb038);
        put("chopping_board_hazelnut", 0x856b42);
        put("chopping_board_hawthorn", 0x81421f);
        put("chopping_board_hornbeam", 0xb7b59a);
        put("chopping_board_quince", 0xc78955);
        put("chopping_board_plum", 0x8e656c);
        put("chopping_board_mango", 0xb4733c);
        put("chopping_board_fig", 0xbc9e80);
    }};

    public ChoppingBoardScreen(ChoppingBoardScreenHandler handler, PlayerInventory inventory, Text title)
    {
        super(handler, inventory, title);

    }

    @Override
    protected void init()
    {
        super.init();
        titleY = 10;
        titleX = 10;
        playerInventoryTitleX = 10;
        playerInventoryTitleY = 68;
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY)
    {
        context.drawText(this.textRenderer, this.title, this.titleX, this.titleY, WOOD_COLORS.get(NAME), false);
        context.drawText(this.textRenderer, this.playerInventoryTitle, this.playerInventoryTitleX, this.playerInventoryTitleY, WOOD_COLORS.get(NAME), false);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY)
    {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderArrowProgress(context, x, y);
    }

    private void renderArrowProgress(DrawContext context, int x, int y)
    {
        if (handler.isCrafting())
            context.drawTexture(TEXTURE, x + 96, y + 35, 176, 35, handler.getScaledProgress(), 16);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta)
    {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
