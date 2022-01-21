package com.github.MitsukiGoto.hisabisamod.asm.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

import static net.minecraft.block.Block.dropResources;
import static net.minecraft.block.LeavesBlock.DISTANCE;
import static net.minecraft.block.LeavesBlock.PERSISTENT;

@Mixin(LeavesBlock.class)
public class Sample3 {
    @Inject(at=@At("RETURN"), method= "randomTick(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/server/ServerWorld;Lnet/minecraft/util/math/BlockPos;Ljava/util/Random;)V")
    private void injection(BlockState p_225542_1_, ServerWorld p_225542_2_, BlockPos p_225542_3_, Random p_225542_4_, CallbackInfo ci){
        if (!p_225542_1_.getValue(PERSISTENT) && p_225542_1_.getValue(DISTANCE) == 7) {
            p_225542_2_.setBlock(p_225542_3_, Blocks.SEA_LANTERN.defaultBlockState(), 1);
        }
    }
}
