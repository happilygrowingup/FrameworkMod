package com.growuphappily.framework;

import com.growuphappily.framework.commands.SettingsCommand;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid=Framework.MOD_ID)
public class Framework {
    public static final String MOD_ID = "framework";
    @Mod.EventHandler
    public static void onServerStarting(FMLServerStartingEvent event){
        event.registerServerCommand(new SettingsCommand());
    }
}
