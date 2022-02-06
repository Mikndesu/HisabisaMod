package com.github.MitsukiGoto.hisabisamod.block;

import com.github.MitsukiGoto.hisabisamod.HisabisaMod;
import com.github.MitsukiGoto.hisabisamod.init.BlockInit;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

import static net.minecraft.block.FrostedIceBlock.AGE;
import static net.minecraft.state.properties.BlockStateProperties.AGE_1;

public class ModdedCryingObsidian extends Block {
    public ModdedCryingObsidian() {
        super(Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(50.0F, 1200.0F).lightLevel((p_235435_0_) -> 10));
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE_1, Integer.valueOf(0)));
    }

    @Override
    public void setPlacedBy(World world, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity p_180633_4_, ItemStack itemStack) {
    }

    @Override
    public void tick(BlockState p_225534_1_, ServerWorld p_225534_2_, BlockPos p_225534_3_, Random p_225534_4_) {
        if ((p_225534_4_.nextInt(3) == 0 && this.slightlyMelt(p_225534_1_, p_225534_2_, p_225534_3_))) {
            HisabisaMod.LOGGER.debug("Modded Crying Obsidian get aged");
        } else {
            p_225534_2_.getBlockTicks().scheduleTick(p_225534_3_, this, MathHelper.nextInt(p_225534_4_, 20, 40));
        }
    }

    private boolean slightlyMelt(BlockState p_196455_1_, World p_196455_2_, BlockPos p_196455_3_) {
        int i = p_196455_1_.getValue(AGE_1);
        if (i < 1) {
            p_196455_2_.setBlock(p_196455_3_, p_196455_1_.setValue(AGE_1, Integer.valueOf(i + 1)), 2);
            return false;
        } else {
            this.melt(p_196455_1_, p_196455_2_, p_196455_3_);
            return true;
        }
    }

    protected void melt(BlockState p_196454_1_, World p_196454_2_, BlockPos p_196454_3_) {
        p_196454_2_.setBlockAndUpdate(p_196454_3_, Blocks.LAVA.defaultBlockState());
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE_1);
    }
}
