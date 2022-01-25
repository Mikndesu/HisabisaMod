package com.github.MitsukiGoto.hisabisamod.asm.mixin;

import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BiomeMaker.class)
public class BiomeGenerationSettingsMixin {
    @ModifyVariable(method = "plainsBiome(Z)Lnet/minecraft/world/biome/Biome;",at=@At(value="STORE"), ordinal = 0)
    private static BiomeGenerationSettings.Builder injection(BiomeGenerationSettings.Builder builder) {
        builder.addStructureStart(StructureFeatures.END_CITY);
        return builder;
    }

}
