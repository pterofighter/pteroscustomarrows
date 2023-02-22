package com.pterofighter.pteroscustomarrows.item;

import com.pterofighter.pteroscustomarrows.PterosCustomArrowsMod;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PterosCustomArrowsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTab {
    //this is used to make a creative mode tab for our mod items
    public static CreativeModeTab PTEROS_CUSTOM_ARROWS_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTab(CreativeModeTabEvent.Register event) {
        PTEROS_CUSTOM_ARROWS_TAB = event.registerCreativeModeTab(new ResourceLocation(PterosCustomArrowsMod.MOD_ID, "pteros_custom_arrows_tab"),
                builder -> builder.icon(() -> new ItemStack(ModItems.EXPLOSIVE_ARROW.get()))
                        .title(Component.translatable("creativemodetab.pteros_custom_arrows_tab")));
    }

}
