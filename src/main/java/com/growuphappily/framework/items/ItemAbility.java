package com.growuphappily.framework.items;

import com.growuphappily.framework.client.ItemInit;
import com.growuphappily.framework.threads.WallThread;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import scala.tools.nsc.symtab.classfile.Pickler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ItemAbility extends Item {
    public static int height = 3;
    public static int width = 5;
    public static int under = 2;
    public ItemAbility(){
        setCreativeTab(ItemInit.tab);
        setTranslationKey("framework.ability");
        setRegistryName("framework:ability");
    }
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
            for (int j = 1; j < height + 1; j++) {
                Pos.add(new BlockPos(currentPos.getX(),currentPos.getY()+j,currentPos.getZ()));
            }
            for (int k = 0; k < under; k++) {
                Pos.add(new BlockPos(currentPos.getX(),currentPos.getY()-k,currentPos.getZ()));
            }
            lastPos = currentPos;
        }
        for(float i = 0; i < (float)width /2.0; i = (float) (i+0.5)){
            Vec3d block = posVec.add(lookVec).add(vecmul(wallVec,-i));
            BlockPos currentPos = new BlockPos(Math.floor(block.x),Math.floor(block.y),Math.floor(block.z));
            if(currentPos.equals(lastPos))
                continue;
            Pos.add(currentPos);
            for (int j = 1; j < height + 1; j++) {
                Pos.add(new BlockPos(currentPos.getX(),currentPos.getY()+j,currentPos.getZ()));
            }
            for (int k = 0; k < under; k++) {
                Pos.add(new BlockPos(currentPos.getX(),currentPos.getY()-k,currentPos.getZ()));
            }
            lastPos = currentPos;
        }
        ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        for (BlockPos p :
                Pos) {
            if (!list.contains(p)){
                list.add(p);
            }
        }
        return list;
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ArrayList<BlockPos> wall = getWall(worldIn,playerIn);
        WallThread thread = new WallThread(worldIn, wall);
        thread.start();
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
