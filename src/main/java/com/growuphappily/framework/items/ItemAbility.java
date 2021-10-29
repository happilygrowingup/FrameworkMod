package com.growuphappily.framework.items;

import com.growuphappily.framework.client.ItemInit;
import net.minecraft.item.Item;

public class ItemAbility extends Item {
    public ItemAbility() {
        setCreativeTab(ItemInit.tab);
        setTranslationKey("framework.ability");
        setRegistryName("framework:ability");
    }
}
