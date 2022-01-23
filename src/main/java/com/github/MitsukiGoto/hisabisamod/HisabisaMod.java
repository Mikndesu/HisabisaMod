package com.github.MitsukiGoto.hisabisamod;



import com.github.MitsukiGoto.hisabisamod.init.BiomeInit;
import com.github.MitsukiGoto.hisabisamod.init.ItemInit;

import com.github.MitsukiGoto.hisabisamod.init.StructureInit;
import com.github.MitsukiGoto.hisabisamod.world.structure.HisabisaConfiguredStructure;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(HisabisaMod.MODID)
public class HisabisaMod {

    public static final String MODID = "hisabisamod";
    public static final Logger LOGGER = LogManager.getLogger();

    public HisabisaMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemInit.ITEMS.register(bus);
        StructureInit.STRUCTURES.register(bus);
        BiomeInit.BIOMES.register(bus);
        bus.addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(this::biomeModification);
    }

    private void setup(final FMLCommonSetupEvent evt) {
        evt.enqueueWork(()->{
            StructureInit.setupStructures();
            HisabisaConfiguredStructure.registerConfiguredStructures();
            BiomeInit.setupBiome();
        });
    }

    public void biomeModification(final BiomeLoadingEvent evt) {
        System.out.println(evt.getName());
        evt.getGeneration().getStructures().add(() -> HisabisaConfiguredStructure.CONFIGURED_HISABISA_STRUCTURE);
    }

}
