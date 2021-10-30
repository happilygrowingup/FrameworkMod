package com.growuphappily.framework.client;

import com.growuphappily.framework.items.ItemAbility;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid="framework")
public class ItemInit {
    public static final CreativeTabs tab = new CreativeTabs("Ability") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.DIAMOND);
        }
    };
    public static Item ability;
    @SubscribeEvent
    public static void onItemReg(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(
                ability = new ItemAbility()
        );
    }
}
