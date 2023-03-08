package com.pterofighter.pteroscustomarrows.item;

import com.pterofighter.pteroscustomarrows.PterosCustomArrowsMod;
import com.pterofighter.pteroscustomarrows.item.custom.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    //a list of what forge keeps track of items we add
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PterosCustomArrowsMod.MOD_ID);


    public static final RegistryObject<Item> EXPLOSIVE_ARROW = ITEMS.register( "explosive_arrow",
            () -> new ExplosiveArrowItem(new Item.Properties()));

    public static final RegistryObject<Item> LIGHTNING_ARROW = ITEMS.register( "lightning_arrow",
            () -> new LightningArrowItem(new Item.Properties()));

    public static final RegistryObject<Item> INSTAKILL_ARROW = ITEMS.register( "instakill_arrow",
            () -> new InstaKillArrowItem(new Item.Properties()));

    public static final RegistryObject<Item> ARROW_RAIN_ARROW = ITEMS.register( "arrow_rain_arrow",
            () -> new ArrowRainArrowItem(new Item.Properties()));

    public static final RegistryObject<Item> BLACK_HOLE_ARROW = ITEMS.register( "black_hole_arrow",
            () -> new BlackHoleArrowItem(new Item.Properties()));

    public static final RegistryObject<Item> SEEKING_ARROW = ITEMS.register( "seeking_arrow",
            () -> new SeekingArrowItem(new Item.Properties()));

    public static final RegistryObject<Item> TREE_ARROW = ITEMS.register( "tree_arrow",
            () -> new TreeArrowItem(new Item.Properties()));

    public static final RegistryObject<Item> SCATTER_ARROW = ITEMS.register( "scatter_arrow",
            () -> new ScatterArrowItem(new Item.Properties()));

    public static final RegistryObject<Item> POISON_GAS_ARROW = ITEMS.register( "poison_gas_arrow",
            () -> new PoisonGasArrowItem(new Item.Properties()));

    public static final RegistryObject<Item> HEAVY_ARROW = ITEMS.register( "heavy_arrow",
            () -> new HeavyArrowItem(new Item.Properties()));

//    public static final RegistryObject<Item> DECOY_ARROW = ITEMS.register( "decoy_arrow",
//            () -> new DecoyArrowItem(new Item.Properties().tab(ModCreativeModeTab.RANDOM_STUFF_TAB)));
//
//    public static final RegistryObject<Item> ENDER_PEARL_ARROW = ITEMS.register( "ender_pearl_arrow",
//            () -> new EnderPearlArrowItem(new Item.Properties().tab(ModCreativeModeTab.RANDOM_STUFF_TAB)));



    //this tells forge the list of items we will register
    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
