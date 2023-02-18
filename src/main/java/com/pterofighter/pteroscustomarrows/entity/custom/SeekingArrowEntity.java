//package com.pterofighter.pteroscustomarrows.entity.custom;
//
//import com.pterofighter.pteroscustomarrows.config.ArrowsConfig;
//import net.minecraft.network.protocol.Packet;
//import net.minecraft.world.entity.*;
//import net.minecraft.world.entity.ai.targeting.TargetingConditions;
//import net.minecraft.world.entity.monster.Monster;
//import net.minecraft.world.entity.monster.Slime;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.entity.projectile.AbstractArrow;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.phys.BlockHitResult;
//import net.minecraft.world.phys.EntityHitResult;
//import net.minecraft.world.phys.Vec3;
//import net.minecraftforge.network.NetworkHooks;
//
//import java.util.List;
//
//public class SeekingArrowEntity extends AbstractArrow
//{
//
//    private LivingEntity shooter;
//    private int despawnTime = ArrowsConfig.seekerArrowDespawnTime.get();
//
//    private double targetingRange = ArrowsConfig.seekerArrowTargetingRange.get();
//    private int targetingWaitTime = ArrowsConfig.seekerArrowTargetingWaitTime.get();
//    //how much damage to add once the arrow starts targeting to make up for reduced damage of lost velocity from firing
//    private double targetingDamageBonus = ArrowsConfig.seekerArrowTargetingDamageBonus.get();
//    private boolean addedTargetingDamageBonus = false;
//    //determines if arrows bounces off blocks or shields
//    private boolean arrowsBounces = ArrowsConfig.seekerArrowArrowsBounce.get();
//
//    public SeekingArrowEntity(EntityType<SeekingArrowEntity> entityType, Level world)
//    {
//        super(entityType, world);
//    }
//
//    public SeekingArrowEntity(EntityType<SeekingArrowEntity> entityType, double x, double y, double z, Level world)
//    {
//        super(entityType, x, y, z, world);
//    }
//
//    public SeekingArrowEntity(EntityType<SeekingArrowEntity> entityType, LivingEntity shooter, Level world)
//    {
//        super(entityType, shooter, world);
//        this.shooter = shooter;
//    }
//
//    @Override
//    protected ItemStack getPickupItem()
//    {
//        return ItemStack.EMPTY;
//    }
//
//    @Override
//    protected void onHitEntity(EntityHitResult pResult)
//    {
//        super.onHitEntity(pResult);
//    }
//
//
//    @Override
//    public void tick() {
//        super.tick();
//        if (this.tickCount > targetingWaitTime)
//        {
//            if (!this.addedTargetingDamageBonus)
//            {
//                this.addedTargetingDamageBonus = true;
//                this.setBaseDamage(this.getBaseDamage() + this.targetingDamageBonus);
//            }
//
//            TargetingConditions targetingConditions = TargetingConditions.forCombat().range(targetingRange);
//
//            Entity nearestEntity = this.level.getNearestEntity(LivingEntity.class, targetingConditions, null,
//                 this.getX(), this.getY(), this.getZ(), this.getBoundingBox().inflate(targetingRange,targetingRange,
//                            targetingRange));
//
//
//            if(nearestEntity != null && !nearestEntity.equals(this.shooter) &&(nearestEntity instanceof FlyingMob ||
//                    nearestEntity instanceof Monster || nearestEntity instanceof Slime || nearestEntity instanceof Player))
//            {
//                this.setDeltaMovement(Vec3.ZERO);
//                Vec3 vec3 = new Vec3(nearestEntity.getX() - this.getX(), nearestEntity.getY() - this.getY(),
//                        nearestEntity.getZ() - this.getZ());
//
//                this.setDeltaMovement(this.getDeltaMovement().add(vec3.normalize()));
//            }
//
//            if(this.tickCount > despawnTime)
//            {
//                this.discard();
//            }
//        }
//    }
//
//    @Override
//    protected void onHitBlock(BlockHitResult blockHitResult)
//    {
//        super.onHitBlock(blockHitResult);
//        if(arrowsBounces)
//        {
//            this.shoot(90, 90, 90, 3, 1);
//        }
//    }
//
////    @Override
////    public void shoot(double pX, double pY, double pZ, float pVelocity, float pInaccuracy) {
////        super.shoot(pX, pY, pZ, pVelocity, pInaccuracy);
////    }
//
//
//    @Override
//    public boolean isNoGravity() {
//        return true;
//    }
//
//    //this is for helping debug render issues
//    @Override
//    public Packet<?> getAddEntityPacket()
//    {
//        return NetworkHooks.getEntitySpawningPacket(this);
//    }
//
//
//}
