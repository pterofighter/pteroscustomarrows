package com.pterofighter.pteroscustomarrows.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class PoisonGasArrowEntity extends AbstractArrow
{

    private int duration = 600;
    private float radius = 10;
    //how many ticks before the gas will take effect
    private int waitTime = 30;
    //how long the poison will after leaving the poison cloud
    private int poisonDuration = 300;
    private int poisonLevel = 0;
    private int harmLevel = 0;


    public PoisonGasArrowEntity(EntityType<PoisonGasArrowEntity> entityType, Level world)
    {
        super(entityType, world);
    }

    public PoisonGasArrowEntity(EntityType<PoisonGasArrowEntity> entityType, double x, double y, double z, Level world)
    {
        super(entityType, x, y, z, world);
    }

    public PoisonGasArrowEntity(EntityType<PoisonGasArrowEntity> entityType, LivingEntity shooter, Level world)
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
        Entity entity = pResult.getEntity();
        if(!entity.level.isClientSide)
        {
            BlockPos pos = entity.getOnPos();
            BlockPos pos2 = new BlockPos(pos.getX(), pos.getY()+1, pos.getZ());
            spawnGas(pos, entity.level);
            spawnGas(pos2, entity.level);
            this.discard();
        }
    }



    @Override
    protected void onHitBlock(BlockHitResult blockHitResult)
    {
        super.onHitBlock(blockHitResult);
        if(!this.level.isClientSide)
        {
            BlockPos pos = blockHitResult.getBlockPos();
            BlockPos pos2 = new BlockPos(pos.getX(), pos.getY()+1, pos.getZ());
            spawnGas(pos,this.level);
            spawnGas(pos2, this.level);
            this.discard();
        }
    }

    private void spawnGas(BlockPos pos, Level level)
    {
        AreaEffectCloud areaeffectcloud = new AreaEffectCloud(level, pos.getX(), pos.getY(), pos.getZ());
        areaeffectcloud.setRadius(radius);
        areaeffectcloud.setWaitTime(waitTime);
        areaeffectcloud.setDuration(duration);
        areaeffectcloud.addEffect(new MobEffectInstance(MobEffects.POISON, poisonDuration, poisonLevel));
        areaeffectcloud.addEffect(new MobEffectInstance(MobEffects.HARM, 1, harmLevel));
        this.level.addFreshEntity(areaeffectcloud);
    }

    //this is for helping debug render issues
    @Override
    public Packet<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
