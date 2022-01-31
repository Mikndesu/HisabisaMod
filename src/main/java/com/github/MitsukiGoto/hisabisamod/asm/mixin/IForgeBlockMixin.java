package com.github.MitsukiGoto.hisabisamod.asm.mixin;

import com.github.MitsukiGoto.hisabisamod.HisabisaMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeBlock;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Block.class)
public abstract class IForgeBlockMixin implements IForgeBlock {
    boolean shouldBeBroken = true;
    @Override
    public void onBlockExploded(BlockState state, World world, BlockPos pos, Explosion explosion) {
        if(explosion.getExploder() instanceof CreeperEntity && state == Blocks.SPAWNER.defaultBlockState()) {
            HisabisaMod.LOGGER.log(Level.DEBUG, "Creeper is now exploding");
            HisabisaMod.LOGGER.log(Level.ERROR, state.getBlock());
            shouldBeBroken = false;
        } else if(explosion.getExploder() == null && state == Blocks.OAK_LEAVES.defaultBlockState()) {
            HisabisaMod.LOGGER.log(Level.DEBUG, "A Leave is now exploding");
            HisabisaMod.LOGGER.log(Level.ERROR, state.getBlock());
            shouldBeBroken = false;
        } else if(shouldBeBroken) {
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            getBlock().wasExploded(world, pos, explosion);
            HisabisaMod.LOGGER.log(Level.DEBUG, "Block is now destroyed");
        }
    }
}
