package com.darkness.client;

import com.darkness.client.manager.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DarknessClient implements ClientModInitializer {
    public static final String MOD_ID = "darkness";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    private static KeyBinding guiKeyBinding;
    private static ModuleManager moduleManager;

    @Override
    public void onInitializeClient() {
        LOGGER.info("[Darkness] Initializing zero-allocation high-performance build for 1.21.1.");

        // Changed key to GRAVE_ACCENT (the ~ key, right below ESC / next to 1)
        guiKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.darkness.gui",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "category.darkness.main"
        ));

        moduleManager = new ModuleManager();
        moduleManager.initializeModules();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null || client.world == null) return;

            while (guiKeyBinding.wasPressed()) {
                client.setScreen(new com.darkness.client.gui.DarknessClickGuiScreen());
            }

            var modList = moduleManager.getModules();
            for (int i = 0, size = modList.size(); i < size; i++) {
                com.darkness.client.module.Module module = modList.get(i);
                if (module.isEnabled()) {
                    module.onTick(client);
                }
            }
        });
    }

    public static ModuleManager getModuleManager() {
        return moduleManager;
    }
}
