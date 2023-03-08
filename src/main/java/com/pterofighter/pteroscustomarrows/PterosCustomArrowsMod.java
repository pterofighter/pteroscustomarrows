package com.pterofighter.pteroscustomarrows;

import com.pterofighter.pteroscustomarrows.config.Config;
import com.pterofighter.pteroscustomarrows.entity.ModEntityTypes;
import com.pterofighter.pteroscustomarrows.entity.render.*;
import com.pterofighter.pteroscustomarrows.item.ModCreativeModeTab;
import com.pterofighter.pteroscustomarrows.item.ModItems;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(PterosCustomArrowsMod.MOD_ID)
public class PterosCustomArrowsMod
{
    public static final String MOD_ID = "pteroscustomarrows";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();


    public PterosCustomArrowsMod()
    {

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModEntityTypes.register(eventBus);
        eventBus.addListener(this::addCreative);
        eventBus.addListener(this::setupClient);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.config, "pteroscustomarrows-common.toml");

//        Config.loadConfig(Config.config, FMLPaths.CONFIGDIR.get().resolve("pteroscustomarrows-common.toml").toString());

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == ModCreativeModeTab.PTEROS_CUSTOM_ARROWS_TAB) {
            event.accept(ModItems.EXPLOSIVE_ARROW);
            event.accept(ModItems.ARROW_RAIN_ARROW);
            event.accept(ModItems.LIGHTNING_ARROW);
            event.accept(ModItems.INSTAKILL_ARROW);
            event.accept(ModItems.BLACK_HOLE_ARROW);
            event.accept(ModItems.SEEKING_ARROW);
            event.accept(ModItems.TREE_ARROW);
        }
    }

    private void setupClient(final FMLClientSetupEvent event)
    {
        EntityRenderers.register(ModEntityTypes.EXPLOSIVE_ARROW.get(), ExplosiveArrowRenderer::new);
        EntityRenderers.register(ModEntityTypes.LIGHTNING_ARROW.get(), LightningArrowRenderer::new);
        EntityRenderers.register(ModEntityTypes.INSTAKILL_ARROW.get(), InstaKillArrowRenderer::new);
        EntityRenderers.register(ModEntityTypes.ARROW_RAIN_ARROW.get(), ArrowRainArrowRenderer::new);
        EntityRenderers.register(ModEntityTypes.SHORT_LIVED_ARROW.get(), ShortLivedArrowRenderer::new);
        EntityRenderers.register(ModEntityTypes.BLACK_HOLE_ARROW.get(), BlackHoleArrowRenderer::new);
        EntityRenderers.register(ModEntityTypes.SEEKING_ARROW.get(), SeekingArrowRenderer::new);
        EntityRenderers.register(ModEntityTypes.TREE_ARROW.get(), TreeArrowRenderer::new);
//        EntityRenderers.register(ModEntityTypes.SCATTER_ARROW.get(), ScatterArrowRenderer::new);
//        EntityRenderers.register(ModEntityTypes.POISON_GAS_ARROW.get(), PoisonGasArrowRenderer::new);
//        EntityRenderers.register(ModEntityTypes.HEAVY_ARROW.get(), HeavyArrowRenderer::new);
//        EntityRenderers.register(ModEntityTypes.DECOY_ARROW.get(), DecoyArrowRenderer::new);
//        EntityRenderers.register(ModEntityTypes.ENDER_PEARL_ARROW.get(), EnderPearlArrowRenderer::new);


    }



}