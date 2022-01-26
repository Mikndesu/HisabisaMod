package com.github.MitsukiGoto.hisabisamod;



import com.github.MitsukiGoto.hisabisamod.asm.mixin.DungeonsFeatureMixin;
import com.github.MitsukiGoto.hisabisamod.init.BiomeInit;
import com.github.MitsukiGoto.hisabisamod.init.ItemInit;

import com.github.MitsukiGoto.hisabisamod.init.StructureInit;
import com.github.MitsukiGoto.hisabisamod.world.structure.HisabisaConfiguredStructure;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(HisabisaMod.MODID)
public class HisabisaMod {

    public static final String MODID = "hisabisamod";
    public static final String HISABISA_STRUCTURE_NAME = "hisabisa_structure";
    public static final String HISABISA_BIOME_NAME = "hisabisa_biome";
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

    @SubscribeEvent
    public void onCreeperSpawns(LivingSpawnEvent.SpecialSpawn evt) {
        if(evt.getEntity() instanceof CreeperEntity) {
            SpawnReason reason = evt.getSpawnReason();
            LOGGER.log(Level.ERROR, reason.toString());
        }
    }

    @SubscribeEvent
    public void setup(final FMLCommonSetupEvent evt) {
        evt.enqueueWork(()->{
            StructureInit.setupStructures();
            HisabisaConfiguredStructure.registerConfiguredStructures();
            BiomeInit.setupBiome();
        });
    }

    public void biomeModification(final BiomeLoadingEvent evt) {
        evt.getGeneration().getStructures().add(() -> HisabisaConfiguredStructure.CONFIGURED_HISABISA_STRUCTURE);
    }

}
