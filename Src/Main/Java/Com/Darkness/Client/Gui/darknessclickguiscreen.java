package com.darkness.client.gui;

import com.darkness.client.DarknessClient;
import com.darkness.client.module.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class DarknessClickGuiScreen extends Screen {
    private static final Text TITLE_TEXT = Text.literal("Darkness Client v1.21.1");

    public DarknessClickGuiScreen() {
        super(TITLE_TEXT);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Background container box
        context.fill(50, 50, 300, 400, 0xFF120A2A); 
        // Title header box
        context.fill(50, 50, 300, 75, 0xFF2A104E);  
        context.drawText(this.textRenderer, TITLE_TEXT, 60, 60, 0xFFD8B4FE, true);

        var modules = DarknessClient.getModuleManager().getModules();
        int yOffset = 90;
        
        for (int i = 0, size = modules.size(); i < size; i++) {
            Module module = modules.get(i);
            int color = module.isEnabled() ? 0xFF9333EA : 0xFF4C1D95;
            context.fill(60, yOffset, 290, yOffset + 20, color);
            context.drawText(this.textRenderer, Text.literal(module.getName()), 70, yOffset + 6, 0xFFFFFFFF, false);
            yOffset += 25;
            if (yOffset > 380) break;
        }

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
