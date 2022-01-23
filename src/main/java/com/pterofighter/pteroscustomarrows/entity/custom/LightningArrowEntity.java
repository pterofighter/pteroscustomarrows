package com.pterofighter.pteroscustomarrows.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class LightningArrowEntity extends AbstractArrow
{


    public LightningArrowEntity(EntityType<LightningArrowEntity> entityType, Level world)
    {
        super(entityType, world);
    }

    public LightningArrowEntity(EntityType<LightningArrowEntity> entityType, double x, double y, double z, Level world)
    {
        super(entityType, x, y, z, world);
    }

    public LightningArrowEntity(EntityType<LightningArrowEntity> entityType, LivingEntity shooter, Level world)
    {
        super(entityType, shooter, world);
    }

    @Override
    protected ItemStack getPickupItem()
    {
        return ItemStack.EMPTY;
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult)
    {
        super.onHitEntity(pResult);
        if (!pResult.getEntity().level.isClientSide())
        {
            ServerLevel world = (ServerLevel) this.level;
            BlockPos pos = pResult.getEntity().blockPosition();
            EntityType.LIGHTNING_BOLT.spawn(world, null, null, pos,
                    MobSpawnType.TRIGGERED, true, true);
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult)
    {
        super.onHitBlock(blockHitResult);
        if(!this.level.isClientSide)
        {
            ServerLevel world = (ServerLevel) this.level;
            BlockPos pos = blockHitResult.getBlockPos();
            EntityType.LIGHTNING_BOLT.spawn(world, null, null, pos,
                    MobSpawnType.TRIGGERED, true, true);
            this.discard();
        }
    }

    //this is for helping debug render issues
    @Override
    public Packet<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
