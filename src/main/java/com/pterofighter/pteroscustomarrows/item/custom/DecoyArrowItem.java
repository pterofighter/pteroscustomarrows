//package com.pterofighter.pteroscustomarrows.item.custom;
//
//import com.pterofighter.pteroscustomarrows.entity.ModEntityTypes;
//import com.pterofighter.pteroscustomarrows.entity.custom.DecoyArrowEntity;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.projectile.AbstractArrow;
//import net.minecraft.world.item.ArrowItem;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.Level;
//
//public class DecoyArrowItem extends ArrowItem {
//    public DecoyArrowItem(Item.Properties p_40512_) {
//        super(p_40512_);
//    }
//
//    @Override
//    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
//        return new DecoyArrowEntity(ModEntityTypes.DECOY_ARROW.get(), pShooter, pLevel);
//    }
//}
