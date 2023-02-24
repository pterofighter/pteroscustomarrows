package com.pterofighter.pteroscustomarrows.entity.custom;

import com.pterofighter.pteroscustomarrows.config.ArrowsConfig;
import com.pterofighter.pteroscustomarrows.item.ModItems;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;


public class ExplosiveArrowEntity extends AbstractArrow
{
    private LivingEntity shooter;

    private double ExplosionRadiusDouble = ArrowsConfig.explosionArrowRadius.get();
    private float explosionRadius = (float) ExplosionRadiusDouble;
    private int groundExplodeTimeDelay = ArrowsConfig.explosionArrowGroundExplodeDelay.get();
    private Level.ExplosionInteraction explosionInteraction = Level.ExplosionInteraction.BLOCK;
    private boolean breakBlocks = ArrowsConfig.explosionArrowBreakBlocks.get();
    private boolean scaleWithFlame = ArrowsConfig.explosionArrowScaleWithFlame.get();
    private boolean isFireArrows = false;

    public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> entityType, Level world)
    {
        super(entityType, world);
        if(!breakBlocks)
        {
            explosionInteraction = Level.ExplosionInteraction.NONE;
        }
    }

    public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> entityType, double x, double y, double z, Level world)
    {
        super(entityType, x, y, z, world);
        if(!breakBlocks)
        {
            explosionInteraction = Level.ExplosionInteraction.NONE;
        }
    }

    public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> entityType, LivingEntity shooter, Level world)
    {
        super(entityType, shooter, world);
        this.shooter = shooter;
        if(!breakBlocks)
        {
            explosionInteraction = Level.ExplosionInteraction.NONE;
        }
    }

    @Override
    protected ItemStack getPickupItem()
    {
        return new ItemStack(ModItems.EXPLOSIVE_ARROW.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult)
    {
        super.onHitEntity(pResult);
        if(!pResult.getEntity().level.isClientSide)
        {
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), explosionRadius, isFireArrows,
                    explosionInteraction);
            this.discard();
        }
    }

    @Override
    protected void tickDespawn()
    {
        if(!this.level.isClientSide)
        {
            if (this.inGroundTime > groundExplodeTimeDelay)
            {
                this.level.explode(this, this.getX(), this.getY(), this.getZ(), explosionRadius, isFireArrows,
                        explosionInteraction);
                this.discard();
            }
        }
    }

    @Override
    public void shoot(double pX, double pY, double pZ, float pVelocity, float pInaccuracy)
    {
        super.shoot(pX, pY, pZ, pVelocity, pInaccuracy);
        if(scaleWithFlame && EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAMING_ARROWS, this.shooter) > 0)
        {
            this.isFireArrows = true;
        }
    }



    //this is for helping debug render issues
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
