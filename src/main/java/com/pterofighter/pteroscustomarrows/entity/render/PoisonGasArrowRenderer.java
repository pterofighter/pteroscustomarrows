package com.pterofighter.pteroscustomarrows.entity.render;

import com.pterofighter.pteroscustomarrows.PterosCustomArrowsMod;
import com.pterofighter.pteroscustomarrows.entity.custom.PoisonGasArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class PoisonGasArrowRenderer extends ArrowRenderer<PoisonGasArrowEntity>
{

    public static final ResourceLocation TEXTURE = new ResourceLocation(PterosCustomArrowsMod.MOD_ID,
            "textures/entity/poison_gas_arrow.png");

    public PoisonGasArrowRenderer(EntityRendererProvider.Context manager) {
        super(manager);
    }

    @Override
    public ResourceLocation getTextureLocation(PoisonGasArrowEntity arrow) {
        return TEXTURE;
    }
}
