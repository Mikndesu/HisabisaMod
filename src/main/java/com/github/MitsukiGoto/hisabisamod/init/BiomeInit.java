package com.github.MitsukiGoto.hisabisamod.init;

import java.util.Objects;

import com.github.MitsukiGoto.hisabisamod.HisabisaMod;
import com.github.MitsukiGoto.hisabisamod.world.biome.BiomeHisabisa;

import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.github.MitsukiGoto.hisabisamod.HisabisaMod.HISABISA_BIOME_NAME;

public class BiomeInit {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES,
            HisabisaMod.MODID);
    public static final RegistryObject<Biome> HISABISA_BIOME = BIOMES.register(HISABISA_BIOME_NAME,
            BiomeHisabisa::hisabisaBiome);

    public static void setupBiome() {
        RegistryKey<Biome> key = RegistryKey.create(ForgeRegistries.Keys.BIOMES, Objects
                .requireNonNull(ForgeRegistries.BIOMES.getKey(HISABISA_BIOME.get()), "Biome registry name was null"));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(key, 100));
        BiomeDictionary.addTypes(key, Type.PLAINS, Type.OVERWORLD);
    }
}
