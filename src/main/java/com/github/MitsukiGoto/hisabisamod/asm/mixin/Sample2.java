package com.github.MitsukiGoto.hisabisamod.asm.mixin;

import net.minecraft.world.biome.BiomeMaker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BiomeMaker.class)
public class Sample2 {
    @ModifyVariable(at=@At("STORE"))
}
