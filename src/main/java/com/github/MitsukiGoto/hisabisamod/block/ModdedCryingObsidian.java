package com.github.MitsukiGoto.hisabisamod.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class ModdedCryingObsidian extends Block {
    public ModdedCryingObsidian() {
        super(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(50.0F, 1200.0F).lightLevel((p_235435_0_) -> 10));
    }
}
