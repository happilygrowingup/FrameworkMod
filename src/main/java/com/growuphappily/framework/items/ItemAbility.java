package com.growuphappily.framework.items;

import com.growuphappily.framework.threads.WallThread;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemAbility extends Item {
    public static int height = 3;
    public static int width = 5;
    public static int under = 2;
    public Vec3d getPerpendicularVecXZ(Vec3d v){
        double k = (double)-1 / (v.z / v.x);
        Vec3d vec = new Vec3d(1, 0,k);
        return new Vec3d(vec.x / vec.length(), 0,vec.z / vec.length());
    }
    public Vec3d vecmul(Vec3d v, double x){
        return new Vec3d(v.x*x,v.y*x,v.z*x);
    }
    public ArrayList<BlockPos> getWall(World world, EntityPlayer player){
        Vec3d lookVec = player.getLookVec();
        Vec3d posVec = player.getPositionVector();
        lookVec = new Vec3d(lookVec.x, 0, lookVec.z);
        lookVec = new Vec3d(lookVec.x / lookVec.length(),0,lookVec.z / lookVec.length());
        lookVec = vecmul(lookVec,3);
        Vec3d wallVec = getPerpendicularVecXZ(lookVec);
        ArrayList<BlockPos> Pos = new ArrayList<BlockPos>();
        BlockPos lastPos = null;
        for(float i = 0; i < (float)width /2.0; i = (float) (i+0.5)){
            Vec3d block = posVec.add(lookVec).add(vecmul(wallVec,i));
            BlockPos currentPos = new BlockPos(Math.floor(block.x),Math.floor(block.y),Math.floor(block.z));
            if(currentPos.equals(lastPos))
                continue;
            Pos.add(currentPos);
            lastPos = currentPos;
        }
        for(float i = 0; i < (float)width /2.0; i = (float) (i+0.5)){
            Vec3d block = posVec.add(lookVec).add(vecmul(wallVec,-i));
            BlockPos currentPos = new BlockPos(Math.floor(block.x),Math.floor(block.y),Math.floor(block.z));
            if(currentPos.equals(lastPos))
                continue;
            Pos.add(currentPos);
            lastPos = currentPos;
        }
        return Pos;
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ArrayList<BlockPos> wall = getWall(worldIn,playerIn);
        WallThread thread = new WallThread(worldIn, wall);
        thread.start();
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
