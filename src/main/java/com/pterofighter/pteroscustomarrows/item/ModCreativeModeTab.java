package com.pterofighter.pteroscustomarrows.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    //this is used to make a creative mode tab for our mod items
    public static final CreativeModeTab RANDOM_STUFF_TAB = new CreativeModeTab("pteros_custom_arrows_tab")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ModItems.EXPLOSIVE_ARROW.get());
        }
    };

}
