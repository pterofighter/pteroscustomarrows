package com.pterofighter.pteroscustomarrows.entity.custom;

import com.pterofighter.pteroscustomarrows.config.ArrowsConfig;
import com.pterofighter.pteroscustomarrows.entity.ModEntityTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class ScatterArrowEntity extends AbstractArrow
{

    private LivingEntity shooter;
    private int despawnTime = 60;
    private int arrowsFired = ArrowsConfig.scatterArrowArrowsFired.get();
    private int inaccuracy = ArrowsConfig.scatterArrowInaccuracy.get();
    private double baseDamage = ArrowsConfig.scatterArrowBaseDamage.get();
    private boolean scaleWithPower = ArrowsConfig.scatterArrowScaleWithPower.get();
    private boolean scaleWithFlame = ArrowsConfig.scatterArrowScaleWithFlame.get();
    private boolean scaleWithKnockBack = ArrowsConfig.scatterArrowScaleWithPunch.get();


    public ScatterArrowEntity(EntityType<ScatterArrowEntity> entityType, Level world)
    {
        super(entityType, world);
        setBaseDamage(baseDamage);
    }

    public ScatterArrowEntity(EntityType<ScatterArrowEntity> entityType, double x, double y, double z, Level world)
    {
        super(entityType, x, y, z, world);
        setBaseDamage(baseDamage);
    }

    public ScatterArrowEntity(EntityType<ScatterArrowEntity> entityType, LivingEntity shooter, Level world)
    {
        super(entityType, shooter, world);
        this.shooter = shooter;
        setBaseDamage(baseDamage);
    }

    @Override
    protected ItemStack getPickupItem()
    {
        return ItemStack.EMPTY;
    }

    //disables the particles from a critArrow
    @Override
    public boolean isCritArrow()
    {
        return false;
    }

    @Override
    protected void tickDespawn()
    {
        if(!this.level.isClientSide)
        {
            if (this.inGroundTime > despawnTime)
            {
                this.discard();
            }
        }
    }

    @Override
    public void shoot(double pX, double pY, double pZ, float pVelocity, float pInaccuracy)
    {
        super.shoot(pX, pY, pZ, pVelocity, pInaccuracy);
        if(!this.level.isClientSide)
        {
            //-1 so it fires the correct amount of arrows including the base arrow
            for (int i = 0; i < arrowsFired-1; i++)
            {
                ShortLivedArrowEntity arrow = new ShortLivedArrowEntity(ModEntityTypes.SHORT_LIVED_ARROW.get(), this.shooter,
                        this.level);
                arrow.setArrowLifeTime(despawnTime);
                if(scaleWithPower)
                {
                    arrow.setBaseDamage(this.getBaseDamage());
                }
                if(scaleWithFlame && EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAMING_ARROWS, this.shooter) > 0)
                {
                    arrow.setSecondsOnFire(100);
                }

                if(scaleWithKnockBack)
                {
                    arrow.setKnockback(this.getKnockback());
                }
                arrow.shoot(pX, pY, pZ, pVelocity, inaccuracy);
                this.level.addFreshEntity(arrow);
            }
        }
    }

    //this is for helping debug render issues
    @Override
    public Packet<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
