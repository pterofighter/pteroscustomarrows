package com.pterofighter.pteroscustomarrows.entity.custom;

import com.pterofighter.pteroscustomarrows.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;


public class DecoyArrowEntity extends AbstractArrow
{
    private LivingEntity shooter;

    public DecoyArrowEntity(EntityType<DecoyArrowEntity> entityType, Level world)
    {
        super(entityType, world);
    }

    public DecoyArrowEntity(EntityType<DecoyArrowEntity> entityType, double x, double y, double z, Level world)
    {
        super(entityType, x, y, z, world);
    }
    public DecoyArrowEntity(EntityType<DecoyArrowEntity> entityType, LivingEntity shooter, Level world)
    {
        super(entityType, shooter, world);
        this.shooter = shooter;
    }


    @Override
    protected ItemStack getPickupItem()
    {
        return new ItemStack(ModItems.DECOY_ARROW.get());
    }



    //this is for helping debug render issues
    @Override
    public Packet<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}

