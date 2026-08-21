package com.darkness.client.manager;

import com.darkness.client.module.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>(32);

    public void initializeModules() {
        modules.add(new AutoCrystalModule());
        modules.add(new AutoHitCrystalModule());
        modules.add(new AnchorMacroModule());
        modules.add(new HoverTotemModule());
        modules.add(new TriggerbotModule());
        modules.add(new AutoShieldBreakerModule());
        modules.add(new SilentAimModule());
        modules.add(new AimAssistModule());
        modules.add(new HitBoxesModule());
        modules.add(new AutoMaceModule());
        modules.add(new AutoPearlCatchModule());
        modules.add(new AutoJumpResetModule());
        modules.add(new FastExpModule());
        modules.add(new AutoSprintModule());
        modules.add(new LungeMacroModule());
        modules.add(new DTapSpearModule()); // Newly added D-Tap Spear module
        modules.add(new autoswordswap ()); // newly added autoswordswap

    }

    public List<Module> getModules() {
        return modules;
    }
}
