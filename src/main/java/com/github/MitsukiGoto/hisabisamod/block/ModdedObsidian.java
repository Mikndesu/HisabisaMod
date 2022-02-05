package com.github.MitsukiGoto.hisabisamod.block;

import com.github.MitsukiGoto.hisabisamod.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

import static net.minecraft.block.FrostedIceBlock.AGE;

public class ModdedObsidian extends Block {

    public static final IntegerProperty AGE_OBSIDIAN = IntegerProperty.create("age", 0, 3);

    public ModdedObsidian() {
        super(Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(50.0F, 1200.0F).lightLevel((p_235435_0_) -> 10));
        this.defaultBlockState = this.stateDefinition.any().setValue(AGE_OBSIDIAN, Integer.valueOf(0));
    }

    public void tick(BlockState p_225534_1_, ServerWorld p_225534_2_, BlockPos p_225534_3_, Random p_225534_4_) {
        if ((p_225534_4_.nextInt(3) == 0 || this.fewerNeigboursThan(p_225534_2_, p_225534_3_, 4)) && p_225534_2_.getMaxLocalRawBrightness(p_225534_3_) > 11 - p_225534_1_.getValue(AGE_OBSIDIAN) && this.slightlyMelt(p_225534_1_, p_225534_2_, p_225534_3_)) {
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

            for (Direction direction : Direction.values()) {
                blockpos$mutable.setWithOffset(p_225534_3_, direction);
                BlockState blockstate = p_225534_2_.getBlockState(blockpos$mutable);
                if (blockstate.is(this) && !this.slightlyMelt(blockstate, p_225534_2_, blockpos$mutable)) {
                    p_225534_2_.getBlockTicks().scheduleTick(blockpos$mutable, this, MathHelper.nextInt(p_225534_4_, 20, 40));
                }
            }

        } else {
            p_225534_2_.getBlockTicks().scheduleTick(p_225534_3_, this, MathHelper.nextInt(p_225534_4_, 20, 40));
        }
    }

    private boolean slightlyMelt(BlockState p_196455_1_, World p_196455_2_, BlockPos p_196455_3_) {
        int i = p_196455_1_.getValue(AGE_OBSIDIAN);
        if (i < 3) {
            p_196455_2_.setBlock(p_196455_3_, p_196455_1_.setValue(AGE_OBSIDIAN, i + 1), 2);
            return false;
        } else {
            this.melt(p_196455_1_, p_196455_2_, p_196455_3_);
            return true;
        }
    }

    protected void melt(BlockState p_196454_1_, World p_196454_2_, BlockPos p_196454_3_) {
        p_196454_2_.setBlockAndUpdate(p_196454_3_, BlockInit.MODDED_CRYING_OBSIDIAN.get().defaultBlockState());
    }

    private boolean fewerNeigboursThan(IBlockReader p_196456_1_, BlockPos p_196456_2_, int p_196456_3_) {
        int i = 0;
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for (Direction direction : Direction.values()) {
            blockpos$mutable.setWithOffset(p_196456_2_, direction);
            if (p_196456_1_.getBlockState(blockpos$mutable).is(this)) {
                ++i;
                if (i >= p_196456_3_) {
                    return false;
                }
            }
        }

        return true;
    }

}
