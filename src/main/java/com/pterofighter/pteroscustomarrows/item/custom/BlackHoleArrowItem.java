package com.pterofighter.pteroscustomarrows.item.custom;

import com.pterofighter.pteroscustomarrows.entity.ModEntityTypes;
import com.pterofighter.pteroscustomarrows.entity.custom.BlackHoleArrowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BlackHoleArrowItem extends ArrowItem
{

    public BlackHoleArrowItem(Properties p_40512_) {
        super(p_40512_);
    }

    @Override
    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        return new BlackHoleArrowEntity(ModEntityTypes.BLACK_HOLE_ARROW.get(), pShooter, pLevel);
    }

}
