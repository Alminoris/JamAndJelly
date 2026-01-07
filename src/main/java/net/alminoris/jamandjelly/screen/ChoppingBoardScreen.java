package net.alminoris.jamandjelly.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.alminoris.jamandjelly.JamJelly;
import net.alminoris.jamandjelly.block.ModBlocks;
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

import static net.alminoris.jamandjelly.util.helper.BlockSetsHelper.WOOD_COLORS;
import static net.alminoris.jamandjelly.util.helper.BlockSetsHelper.getWoodName;

public class ChoppingBoardScreen extends HandledScreen<ChoppingBoardScreenHandler>
{
    private final Identifier TEXTURE = Identifier.of(JamJelly.MOD_ID, "textures/gui/"+ getWoodName(ModBlocks.CHOPPING_BOARDS, handler.blockEntity) +".png");;

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
        context.drawText(this.textRenderer, this.title, this.titleX, this.titleY, WOOD_COLORS.get(getWoodName(ModBlocks.CHOPPING_BOARDS, handler.blockEntity)), false);
        context.drawText(this.textRenderer, this.playerInventoryTitle, this.playerInventoryTitleX, this.playerInventoryTitleY, WOOD_COLORS.get(getWoodName(ModBlocks.CHOPPING_BOARDS, handler.blockEntity)), false);
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
