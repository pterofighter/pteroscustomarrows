//package com.pterofighter.pteroscustomarrows.entity.custom;
//
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.Direction;
//import net.minecraft.network.protocol.Packet;
//import net.minecraft.server.level.ServerLevel;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.projectile.AbstractArrow;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.block.SaplingBlock;
//import net.minecraft.world.phys.BlockHitResult;
//import net.minecraft.world.phys.EntityHitResult;
//import net.minecraftforge.network.NetworkHooks;
//
//public class TreeArrowEntity extends AbstractArrow
//{
//
//    private Block tree = Blocks.OAK_SAPLING;
//    //how many attempts it will make to grow the sapling
//    private int treeAdvanceAttempts = 5;
//
//    public TreeArrowEntity(EntityType<TreeArrowEntity> entityType, Level world)
//    {
//        super(entityType, world);
//    }
//
//    public TreeArrowEntity(EntityType<TreeArrowEntity> entityType, double x, double y, double z, Level world)
//    {
//        super(entityType, x, y, z, world);
//    }
//
//    public TreeArrowEntity(EntityType<TreeArrowEntity> entityType, LivingEntity shooter, Level world)
//    {
//        super(entityType, shooter, world);
//    }
//
//    @Override
//    protected ItemStack getPickupItem()
//    {
//        return ItemStack.EMPTY;
//    }
//
//
//    @Override
//    protected void onHitEntity(EntityHitResult result)
//    {
//        super.onHitEntity(result);
//        if (!this.level.isClientSide()) {
//            BlockPos pos = result.getEntity().getOnPos().relative(Direction.UP);
//            spawnTree(pos);
//        }
//    }
//
//    @Override
//    protected void onHitBlock(BlockHitResult result)
//    {
//        super.onHitBlock(result);
//        if (!this.level.isClientSide())
//        {
//            BlockPos pos = result.getBlockPos().relative(result.getDirection());
//            spawnTree(pos);
//            this.discard();
//        }
//
//    }
//
//    private void spawnTree(BlockPos pos)
//    {
//        if(this.level.setBlockAndUpdate(pos,tree.getStateForPlacement(null)))
//        {
//            Block sapling = this.level.getBlockState(pos).getBlock();
//            int i = 0;
//            try
//            {
//                while (sapling instanceof SaplingBlock && i < treeAdvanceAttempts)
//                {
//                    sapling = this.level.getBlockState(pos).getBlock();
//                    ((SaplingBlock) sapling).advanceTree((ServerLevel) this.level, pos, this.level.getBlockState(pos),
//                            this.random);
//                    i++;
//                }
//            }
//            catch (Exception e)
//            {
//                this.discard();
//            }
//        }
//    }
//
//    @Override
//    public int getKnockback()
//    {
//        return 0;
//    }
//
//    @Override
//    public Packet<?> getAddEntityPacket()
//    {
//        return NetworkHooks.getEntitySpawningPacket(this);
//    }
//}
