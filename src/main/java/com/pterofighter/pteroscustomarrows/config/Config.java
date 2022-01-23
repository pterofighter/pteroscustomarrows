package com.pterofighter.pteroscustomarrows.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.pterofighter.pteroscustomarrows.PterosCustomArrowsMod;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.io.File;

@Mod.EventBusSubscriber
public class Config
{
    private static final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec config;

    static
    {
        ArrowsConfig.init(builder);
        config = builder.build();
    }

    public static void loadConfig(ForgeConfigSpec config, String path )
    {
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().
                autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig(file);
    }
}
