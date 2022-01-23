package com.pterofighter.pteroscustomarrows.entity.custom;

import com.pterofighter.pteroscustomarrows.config.ArrowsConfig;
import com.pterofighter.pteroscustomarrows.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class InstaKillArrowEntity extends AbstractArrow
{

    private float arrowVelocity = ArrowsConfig.instakillArrowVelocity.get();
    private boolean arrowBreakBlocks = ArrowsConfig.instakillArrowBreakBlocks.get();
    private int arrowLifeTime = 400;
    private int pierceLevel = ArrowsConfig.instakillArrowPierceLevel.get();
    private double baseDamage = ArrowsConfig.instakillArrowBaseDamage.get();
    private boolean isNoGravity = ArrowsConfig.instakillArrowIsNoGravity.get();

    public InstaKillArrowEntity(EntityType<InstaKillArrowEntity> entityType, Level world)
    {
        super(entityType, world);
        setBaseDamage(baseDamage);
    }

    public InstaKillArrowEntity(EntityType<InstaKillArrowEntity> entityType, double x, double y, double z, Level world)
    {
        super(entityType, x, y, z, world);
        setBaseDamage(baseDamage);
    }

    public InstaKillArrowEntity(EntityType<InstaKillArrowEntity> entityType, LivingEntity shooter, Level world)
    {
        super(entityType, shooter, world);
        setBaseDamage(baseDamage);
    }


    @Override
    protected void onHitBlock(BlockHitResult result)
    {
        super.onHitBlock(result);
        if (!this.level.isClientSide())
        {
            if (arrowBreakBlocks)
            {
                BlockPos pos = result.getBlockPos();
                if (this.level.getBlockState(pos).getBlock() != Blocks.BEDROCK)
                {
                    this.level.destroyBlock(pos, true, this);
                }
                this.discard();
            }
        }

    }

    @Override
    public void tick()
    {
        super.tick();
        if(!this.level.isClientSide)
        {
            if(this.tickCount > arrowLifeTime)
            {
                this.discard();
            }
        }
    }

    @Override
    protected ItemStack getPickupItem()
    {
        return new ItemStack(ModItems.INSTAKILL_ARROW.get());
    }

    @Override
    public byte getPierceLevel()
    {
        return (byte) this.pierceLevel;
    }

    @Override
    public boolean isNoGravity()
    {
        return this.isNoGravity;
    }

//    @Override
//    public void shootFromRotation(Entity pProjectile, float pX, float pY, float pZ, float pVelocity, float pInaccuracy)
//    {
//        super.shootFromRotation(pProjectile, pX, pY, pZ, arrowVelocity, pInaccuracy);
//    }

    @Override
    public void shoot(double pX, double pY, double pZ, float pVelocity, float pInaccuracy)
    {
        super.shoot(pX, pY, pZ, arrowVelocity, pInaccuracy);
    }

    @Override
    public Packet<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
