package com.pterofighter.pteroscustomarrows.entity.custom;

import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class ShortLivedArrowEntity extends AbstractArrow
{

    private int arrowLifeTime = 60;
    private LivingEntity shooter;



    public ShortLivedArrowEntity(EntityType<ShortLivedArrowEntity> entityType, Level world)
    {
        super(entityType, world);
    }

    public ShortLivedArrowEntity(EntityType<ShortLivedArrowEntity> entityType, double x, double y, double z, Level world)
    {
        super(entityType, x, y, z, world);
    }

    public ShortLivedArrowEntity(EntityType<ShortLivedArrowEntity> entityType, LivingEntity shooter, Level world)
    {
        super(entityType, shooter, world);
        this.shooter = shooter;
    }

    @Override
    protected ItemStack getPickupItem()
    {
        return ItemStack.EMPTY;
    }


    @Override
    protected void tickDespawn()
    {
        if(this.tickCount > arrowLifeTime)
        {
            this.discard();
        }
    }


    public void setArrowLifeTime(int time)
    {
        this.arrowLifeTime = time;
    }


    //this is for helping debug render issues
    @Override
    public Packet<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
