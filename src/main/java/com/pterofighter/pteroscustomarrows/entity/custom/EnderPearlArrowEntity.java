package com.pterofighter.pteroscustomarrows.entity.custom;

import com.pterofighter.pteroscustomarrows.item.ModItems;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
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
        if (!this.level.isClientSide && !this.isRemoved()) {
            Entity entity = this.getOwner();
            if (entity instanceof ServerPlayer) {
                ServerPlayer serverplayer = (ServerPlayer)entity;
                if (serverplayer.connection.getConnection().isConnected() && serverplayer.level == this.level && !serverplayer.isSleeping()) {
                    net.minecraftforge.event.entity.EntityTeleportEvent.EnderPearl event = net.minecraftforge.event.ForgeEventFactory.onEnderPearlLand(serverplayer, this.getX(), this.getY(), this.getZ(), this, 5.0F);
                    if (!event.isCanceled()) { // Don't indent to lower patch size
                        if (this.random.nextFloat() < 0.05F && this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING)) {
                            Endermite endermite = EntityType.ENDERMITE.create(this.level);
                            endermite.moveTo(entity.getX(), entity.getY(), entity.getZ(), entity.getYRot(), entity.getXRot());
                            this.level.addFreshEntity(endermite);
                        }

                        if (entity.isPassenger()) {
                            serverplayer.dismountTo(this.getX(), this.getY(), this.getZ());
                        } else {
                            entity.teleportTo(this.getX(), this.getY(), this.getZ());
                        }

                        entity.teleportTo(event.getTargetX(), event.getTargetY(), event.getTargetZ());
                        entity.resetFallDistance();
                        entity.hurt(DamageSource.FALL, event.getAttackDamage());
                    } //Forge: End
                }
            } else if (entity != null) {
                entity.teleportTo(this.getX(), this.getY(), this.getZ());
                entity.resetFallDistance();
            }

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
