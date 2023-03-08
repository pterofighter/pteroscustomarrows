
package com.pterofighter.pteroscustomarrows.entity.custom;


import com.pterofighter.pteroscustomarrows.config.ArrowsConfig;
import com.pterofighter.pteroscustomarrows.item.ModItems;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class HeavyArrowEntity extends AbstractArrow
{
    private double baseDamage = ArrowsConfig.heavyArrowBaseDamage.get();

    public HeavyArrowEntity(EntityType<HeavyArrowEntity> entityType, Level world)
    {
        super(entityType, world);
        setBaseDamage(baseDamage);
    }

    public HeavyArrowEntity(EntityType<HeavyArrowEntity> entityType, double x, double y, double z, Level world)
    {
        super(entityType, x, y, z, world);
        setBaseDamage(baseDamage);
    }

    public HeavyArrowEntity(EntityType<HeavyArrowEntity> entityType, LivingEntity shooter, Level world)
    {
        super(entityType, shooter, world);
        setBaseDamage(baseDamage);
    }

    @Override
    protected ItemStack getPickupItem()
    {
        return new ItemStack(ModItems.HEAVY_ARROW.get());
    }


    @Override
    public double getBaseDamage()
    {
        return this.baseDamage;
    }

    //this is for helping debug render issues
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
