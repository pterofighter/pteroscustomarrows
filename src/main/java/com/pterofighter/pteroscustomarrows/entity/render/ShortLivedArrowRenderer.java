package com.pterofighter.pteroscustomarrows.entity.render;

import com.pterofighter.pteroscustomarrows.PterosCustomArrowsMod;
import com.pterofighter.pteroscustomarrows.entity.custom.ShortLivedArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ShortLivedArrowRenderer extends ArrowRenderer<ShortLivedArrowEntity>
{

    public static final ResourceLocation TEXTURE = new ResourceLocation(PterosCustomArrowsMod.MOD_ID,
            "textures/entity/short_lived_arrow.png");

    public ShortLivedArrowRenderer(EntityRendererProvider.Context manager) {
        super(manager);
    }

    @Override
    public ResourceLocation getTextureLocation(ShortLivedArrowEntity arrow) {
        return TEXTURE;
    }
}
