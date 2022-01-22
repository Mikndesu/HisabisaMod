package com.github.MitsukiGoto.hisabisamod;

import java.util.logging.LogManager;

import com.github.MitsukiGoto.hisabisamod.init.BiomeInit;
import com.github.MitsukiGoto.hisabisamod.init.ItemInit;

import com.github.MitsukiGoto.hisabisamod.init.StructureInit;
import com.github.MitsukiGoto.hisabisamod.world.structure.HisabisaConfiguredStructure;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(HisabisaMod.MODID)
public class HisabisaMod {

    public static final String MODID = "hisabisamod";
    public static final LogManager LOGGER = LogManager.getLogManager();

    public HisabisaMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        ItemInit.ITEMS.register(bus);
        BiomeInit.BIOMES.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent evt) {
        BiomeInit.setupBiome();
        StructureInit.setupStructures();
        HisabisaConfiguredStructure.registerConfiguredStructures();
    }

}
