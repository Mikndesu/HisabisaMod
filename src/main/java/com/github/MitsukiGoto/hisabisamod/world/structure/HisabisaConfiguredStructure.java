package com.github.MitsukiGoto.hisabisamod.world.structure;

import com.github.MitsukiGoto.hisabisamod.HisabisaMod;
import com.github.MitsukiGoto.hisabisamod.init.StructureInit;
import com.github.MitsukiGoto.hisabisamod.world.structure.structures.HisabisaStructure;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class HisabisaConfiguredStructure {
    public static StructureFeature<?, ?> CONFIGURED_HISABISA_STRUCTURE = StructureInit.HISABISA_STRUCTURE.get().configured(IFeatureConfig.NONE);

    public static void registerConfiguredStructures() {
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(HisabisaMod.MODID, "hisabisa_structure"), CONFIGURED_HISABISA_STRUCTURE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureInit.HISABISA_STRUCTURE.get(), CONFIGURED_HISABISA_STRUCTURE);
    }
}
