package com.growuphappily.framework.threads;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class WallThread extends Thread{
    public ArrayList<BlockPos> wall;
    public World world;
    public WallThread(World worldIn, ArrayList<BlockPos> arr){
        wall = arr;
        world = worldIn;
    }
    @Override
    public void run() {
        ArrayList<IBlockState> last = new ArrayList<>();
        for(BlockPos block:wall) {
            last.add(world.getBlockState(block));
            world.setBlockState(block,Blocks.GLASS.getDefaultState());
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i = 0;i<wall.size();i++){
            world.setBlockState(wall.get(i), last.get(i));
        }
    }
}
