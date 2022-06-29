package com.pterofighter.pteroscustomarrows.entity.custom;

import com.pterofighter.pteroscustomarrows.config.ArrowsConfig;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import java.util.List;


public class BlackHoleArrowEntity extends AbstractArrow
{

    private LivingEntity shooter;
    private int pierceLevel = 3;
    private int life = 0;
    private int duration = ArrowsConfig.blackHoleDuration.get();
    private boolean startSuck = false;
    //the radius in which entities will be pulled into the arrow
    private double suckRange = ArrowsConfig.blackHoleSuckRange.get();
    //how strong the pull strength of the arrow will be;
    private double suckStrength = ArrowsConfig.blackHoleSuckStrength.get();
    //how many ticks the arrow will pull entities in
    private int suckInterval = ArrowsConfig.blackHoleSuckInterval.get();

    public BlackHoleArrowEntity(EntityType<BlackHoleArrowEntity> entityType, Level world)
    {
        super(entityType, world);
    }

    public BlackHoleArrowEntity(EntityType<BlackHoleArrowEntity> entityType, double x, double y, double z, Level world)
    {
        super(entityType, x, y, z, world);
    }

    public BlackHoleArrowEntity(EntityType<BlackHoleArrowEntity> entityType, LivingEntity shooter, Level world)
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
    protected void onHitEntity(EntityHitResult pResult)
    {
        super.onHitEntity(pResult);
        if(!pResult.getEntity().level.isClientSide)
        {
            this.setNoGravity(true);
            this.setDeltaMovement(Vec3.ZERO);
            this.startSuck = true;
        }
    }

    @Override
    public void tick()
    {
        super.tick();
        if (!this.level.isClientSide)
        {
            if (startSuck && this.tickCount % suckInterval == 0)
            {
                TargetingConditions targetingConditions = TargetingConditions.forCombat().range(suckRange);
                List<LivingEntity> list = this.level.getNearbyEntities(LivingEntity.class, targetingConditions, null,
                        this.getBoundingBox().inflate(suckRange, suckRange, suckRange));
                for (LivingEntity entity : list)
                {
                    Vec3 vec3 = new Vec3(this.getX() - entity.getX(), this.getY() - entity.getY(),
                            this.getZ() - entity.getZ());
                    entity.setDeltaMovement(entity.getDeltaMovement().add(vec3.normalize().scale(suckStrength)));
                    if (entity instanceof Player)
                    {
                        System.out.println("BOOOOOOO!");
//                        entity.setDeltaMovement(entity.getDeltaMovement().add(0, 10, 0));
                        Player bob = (Player) entity;
                    }
                }
            }
            this.life++;
            if (this.life > this.duration)
            {
                this.discard();
            }
        }

    }

    @Override
    protected void onHitBlock(BlockHitResult p_36755_)
    {
        super.onHitBlock(p_36755_);
        if(!this.level.isClientSide)
        {
            this.setNoGravity(true);
            this.startSuck = true;
        }
    }

    @Override
    public byte getPierceLevel()
    {
        return (byte)this.pierceLevel ;
    }

    //this is for helping debug render issues
    @Override
    public Packet<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    /*
    Getter and Setter methods
     */
    public void setSuckInterval(int suckInterval)
    {
        this.suckInterval = suckInterval;
    }

    public void setSuckRange(double suckRange)
    {
        this.suckRange = suckRange;
    }

    public void setSuckStrength(double suckStrength)
    {
        this.suckStrength = suckStrength;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public double getSuckRange()
    {
        return suckRange;
    }

    public double getSuckStrength()
    {
        return suckStrength;
    }

    public int getSuckInterval()
    {
        return suckInterval;
    }

    public int getDuration()
    {
        return duration;
    }
}
