package com.pterofighter.pteroscustomarrows.entity.custom;

import com.pterofighter.pteroscustomarrows.PterosCustomArrowsMod;
import com.pterofighter.pteroscustomarrows.entity.ModEntityTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;


public class ArrowRainArrowEntity extends AbstractArrow
{

    private int life;
    private LivingEntity shooter;
    //we want the pierceLevel to be at least 1 so the arrow won't dissapear when hitting an entity a
    private int pierceLevel = 3;
    //how long the arrow rain will last in ticks
    private int duration = 600;
    //how many arrows will be fired per tick during arrow rain
    private int arrowsPerTick = 7;
    //how many blocks above where the arrow land, where the arrow rain will start
    private int arrowRainHeight = 69;
    //whether the arrows spawned by arrow rain will scale power enchantment
    private boolean scaleWithPowerEnchantment = true;
    //whether the arrows spawned by arrow rain will scale with flame enchantment
    private boolean scaleWithFlameEnchantment = true;
    private boolean startArrowRain = false;
    private boolean isFireArrows = false;



    public ArrowRainArrowEntity(EntityType<ArrowRainArrowEntity> entityType, Level world)
    {
        super(entityType, world);
    }

    public ArrowRainArrowEntity(EntityType<ArrowRainArrowEntity> entityType, double x, double y, double z, Level world)
    {
        super(entityType, x, y, z, world);
    }

    public ArrowRainArrowEntity(EntityType<ArrowRainArrowEntity> entityType, LivingEntity shooter, Level world)
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
            Entity entity = pResult.getEntity();
            this.teleportTo(entity.getX(), entity.getY() + arrowRainHeight, entity.getZ());
            this.setDeltaMovement(Vec3.ZERO);
            this.startArrowRain = true;
            this.life = 0;
        }
    }


    @Override
    public void tick()
    {
        super.tick();
        if(!this.level.isClientSide)
        {
            if (startArrowRain)
            {
                this.doArrowRain();
            }
            ++this.life;
            if (this.life > duration)
            {
                this.discard();
            }
        }
    }

    private void doArrowRain()
    {
        for (int i = 0; i < arrowsPerTick; i++)
        {
            ShortLivedArrowEntity arrow = new ShortLivedArrowEntity(ModEntityTypes.SHORT_LIVED_ARROW.get(), this.shooter,this.level);
            arrow.shoot(0, 0, 0, 3.9f, 5);
            arrow.setPos(this.position());
            //make the short live arrow despawn almost isntantly when it hits the ground
            arrow.setArrowLifeTime(1);

            //sets the base damage of the spawned arrow to the same as this one
            if(scaleWithPowerEnchantment)
            {
                arrow.setBaseDamage(this.getBaseDamage());
            }

            //sets the arrows spawned in the rain on fire if shooter has flame enchantment
            if(scaleWithFlameEnchantment && isFireArrows)
            {
                arrow.setSecondsOnFire(100);
            }
            this.level.addFreshEntity(arrow);
        }

    }

    @Override
    public void shoot(double pX, double pY, double pZ, float pVelocity, float pInaccuracy)
    {
        super.shoot(pX, pY, pZ, pVelocity, pInaccuracy);
        if(EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAMING_ARROWS, this.shooter) > 0)
        {
            this.isFireArrows = true;
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult p_36755_)
    {
        super.onHitBlock(p_36755_);
        if(!this.level.isClientSide)
        {
            this.setNoGravity(true);
            this.teleportTo(this.getX(), this.getY() + arrowRainHeight, this.getZ());
            this.setDeltaMovement(Vec3.ZERO);
            this.startArrowRain = true;
            this.life = 0;
        }
    }

    @Override
    public byte getPierceLevel()
    {
        return (byte)this.pierceLevel ;
    }

    @Override
    public void setPierceLevel(byte pLevel)
    {
        this.pierceLevel = pLevel;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public void setArrowsPerTick(int arrowsPerTick)
    {
        this.arrowsPerTick = arrowsPerTick;
    }

    public void setArrowRainHeight(int height)
    {
        this.arrowRainHeight = height;
    }

    public void setScaleWithPowerEnchantment(boolean bool)
    {
        this.scaleWithPowerEnchantment = bool;
    }

    public void setScaleWithFlameEnchantment(boolean bool) {
        this.scaleWithFlameEnchantment = bool;
    }

    @Override
    public Packet<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
