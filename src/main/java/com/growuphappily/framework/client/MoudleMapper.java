package com.growuphappily.framework.client;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

//@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid="framework")
public class MoudleMapper {
    @SubscribeEvent
    public static void onModelReg(ModelRegistryEvent event) {
        try {
            ModelLoader.setCustomModelResourceLocation(ItemInit.ability, 0, new ModelResourceLocation(ItemInit.ability.getRegistryName(), "inventory"));
        } catch (Exception ignored) {
        }
    }
}
