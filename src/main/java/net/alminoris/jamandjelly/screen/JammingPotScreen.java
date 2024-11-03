package net.alminoris.jamandjelly.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.alminoris.jamandjelly.JamJelly;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class JammingPotScreen extends HandledScreen<JammingPotScreenHandler>
{
    private static final Identifier TEXTURE = Identifier.of(JamJelly.MOD_ID, "textures/gui/jamming_pot.png");

    public JammingPotScreen(JammingPotScreenHandler handler, PlayerInventory inventory, Text title)
    {
        super(handler, inventory, title);
    }

    @Override
    protected void init()
    {
        super.init();
        titleY = 5;
        titleX = 105;
        playerInventoryTitleX = 120;
        playerInventoryTitleY = 68;
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY)
    {
        context.drawText(this.textRenderer, this.title, this.titleX, this.titleY, 0x8b8b8b, false);
        context.drawText(this.textRenderer, this.playerInventoryTitle, this.playerInventoryTitleX, this.playerInventoryTitleY, 0x8b8b8b, false);
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

        renderFlame(context, x, y);
        renderArrowProgress(context, x, y);
    }

    private void renderArrowProgress(DrawContext context, int x, int y)
    {
        if (handler.isCrafting())
            context.drawTexture(TEXTURE, x + 83, y + 29, 176, 43, handler.getScaledProgress(),20);
    }

    private void renderFlame(DrawContext context, int x, int y)
    {
        if (handler.isFlaming())
        {
            context.drawTexture(TEXTURE, x + 44, y + 61, 176, 63, 14,14);
            context.drawTexture(TEXTURE, x + 21, y + 8, 176, 84, 61,54);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta)
    {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
