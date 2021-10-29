package com.growuphappily.framework.client;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid="framework")
public class MoudleMapper {
    @SubscribeEvent
    public static void onModelReg(ModelRegistryEvent event){
        ModelLoader.setCustomModelResourceLocation(ItemInit.ability,0,new ModelResourceLocation(ItemInit.ability.getRegistryName(),"inventory"));
    }
}
