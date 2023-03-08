
package com.pterofighter.pteroscustomarrows.item.custom;

import com.pterofighter.pteroscustomarrows.entity.ModEntityTypes;
import com.pterofighter.pteroscustomarrows.entity.custom.TreeArrowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TreeArrowItem extends ArrowItem
{

    public TreeArrowItem(Properties p_40512_) {
        super(p_40512_);
    }

    @Override
    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        return new TreeArrowEntity(ModEntityTypes.TREE_ARROW.get(), pShooter, pLevel);
    }

}
