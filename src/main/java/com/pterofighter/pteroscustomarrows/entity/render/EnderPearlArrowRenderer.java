package com.pterofighter.pteroscustomarrows.entity.render;

import com.pterofighter.pteroscustomarrows.PterosCustomArrowsMod;
import com.pterofighter.pteroscustomarrows.entity.custom.EnderPearlArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class EnderPearlArrowRenderer extends ArrowRenderer<EnderPearlArrowEntity>
{
    public static final ResourceLocation TEXTURE = new ResourceLocation(PterosCustomArrowsMod.MOD_ID,
            "textures/entity/ender_pearl_arrow.png");
    public EnderPearlArrowRenderer(EntityRendererProvider.Context manager)
    {
        super(manager);
    }
    @Override
    public ResourceLocation getTextureLocation(EnderPearlArrowEntity arrow)
    {
        return TEXTURE;
    }
}
