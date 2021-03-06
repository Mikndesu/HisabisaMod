package com.github.MitsukiGoto.hisabisamod.asm.mixin.block;

import com.github.MitsukiGoto.hisabisamod.HisabisaMod;
import com.github.MitsukiGoto.hisabisamod.config.HisabisaConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

import static net.minecraft.block.LeavesBlock.DISTANCE;
import static net.minecraft.block.LeavesBlock.PERSISTENT;

@Mixin(LeavesBlock.class)
public class LeavesBlockMixin {
    @Inject(at=@At("RETURN"), method= "randomTick(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/server/ServerWorld;Lnet/minecraft/util/math/BlockPos;Ljava/util/Random;)V")
    private void injection(BlockState blockState, ServerWorld serverWorld, BlockPos blockPos, Random random, CallbackInfo ci){
        if (!blockState.getValue(PERSISTENT) && blockState.getValue(DISTANCE) == 7 && HisabisaConfig.isDisappearLeavesExplode.get()) {
            float leavesExplosionLevel = HisabisaConfig.leavesExplosionLevel.get();
            if(HisabisaConfig.leavesExplosionLevel.get() <= 0) {
                HisabisaMod.LOGGER.error("Invalid value was set to leavesExplosionLevel");
                leavesExplosionLevel = 100;
            }
//            serverWorld.setBlock(blockPos, Blocks.SEA_LANTERN.defaultBlockState(), 1);
//            EntityType.CREEPER.spawn(serverWorld, null,null, blockPos, SpawnReason.EVENT, true, false);
            serverWorld.explode(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), leavesExplosionLevel, Explosion.Mode.DESTROY);
        }
    }
}
