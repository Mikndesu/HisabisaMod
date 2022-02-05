package com.github.MitsukiGoto.hisabisamod.init;

import com.github.MitsukiGoto.hisabisamod.HisabisaMod;
import com.github.MitsukiGoto.hisabisamod.block.ModdedCryingObsidian;
import com.github.MitsukiGoto.hisabisamod.block.ModdedObsidian;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            HisabisaMod.MODID);
    public static final RegistryObject<Block> MODDED_OBSIDIAN = BLOCKS.register("modded_obsidian", ModdedObsidian::new);
    public static final RegistryObject<Block> MODDED_CRYING_OBSIDIAN = BLOCKS.register("modded_crying_obsidian", ModdedCryingObsidian::new);
}
