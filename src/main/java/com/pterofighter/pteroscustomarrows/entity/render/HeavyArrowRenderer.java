package com.pterofighter.pteroscustomarrows.entity.render;

import com.pterofighter.pteroscustomarrows.PterosCustomArrowsMod;
import com.pterofighter.pteroscustomarrows.entity.custom.HeavyArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class HeavyArrowRenderer extends ArrowRenderer<HeavyArrowEntity>
{

    public static final ResourceLocation TEXTURE = new ResourceLocation(PterosCustomArrowsMod.MOD_ID,
            "textures/entity/heavy_arrow.png");

    public HeavyArrowRenderer(EntityRendererProvider.Context manager) {
        super(manager);
    }

    @Override
    public ResourceLocation getTextureLocation(HeavyArrowEntity arrow) {
        return TEXTURE;
    }
}
