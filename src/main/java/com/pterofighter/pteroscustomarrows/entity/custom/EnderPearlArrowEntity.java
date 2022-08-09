package com.pterofighter.pteroscustomarrows.entity.custom;

import com.pterofighter.pteroscustomarrows.item.ModItems;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class EnderPearlArrowEntity extends AbstractArrow {
    private LivingEntity shooter;


    public EnderPearlArrowEntity(EntityType<EnderPearlArrowEntity> entityType, Level world)
    {
        super(entityType, world);
    }

    public EnderPearlArrowEntity(EntityType<EnderPearlArrowEntity> entityType, double x, double y, double z, Level world)
    {
        super(entityType, x, y, z, world);

    }

    public EnderPearlArrowEntity(EntityType<EnderPearlArrowEntity> entityType, LivingEntity shooter, Level world)
    {
        super(entityType, shooter, world);
        this.shooter = shooter;
    }


    @Override
    protected void onHitEntity(EntityHitResult pResult)
    {

        super.onHitEntity(pResult);
        if (!this.level.isClientSide)
        {
            Entity entity = this.getOwner();
            if (entity instanceof ServerPlayer)
            {
                ServerPlayer serverplayer = (ServerPlayer)entity;
                entity.teleportTo(this.getX(), this.getY(), this.getZ());
                entity.resetFallDistance();
            }
            else if (entity != null)
            {
                entity.teleportTo(this.getX(), this.getY(), this.getZ());
                entity.resetFallDistance();
            }
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult p_36755_) {
        super.onHitBlock(p_36755_);
        if (!this.level.isClientSide && !this.isRemoved())
        {
            Entity entity = this.getOwner();
            if (entity instanceof ServerPlayer)
            {
                ServerPlayer serverplayer = (ServerPlayer)entity;
                entity.teleportTo(this.getX(), this.getY(), this.getZ());
                entity.resetFallDistance();
            }
            else if (entity != null)
            {
                entity.teleportTo(this.getX(), this.getY(), this.getZ());
                entity.resetFallDistance();
            }

            this.discard();
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.ENDER_PEARL_ARROW.get());
    }


    //this is for helping debug render issues
    @Override
    public Packet<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
