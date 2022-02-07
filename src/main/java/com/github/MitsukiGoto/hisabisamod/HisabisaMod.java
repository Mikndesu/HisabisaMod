package com.github.MitsukiGoto.hisabisamod;

import com.github.MitsukiGoto.hisabisamod.config.HisabisaConfig;
import com.github.MitsukiGoto.hisabisamod.enchant.LavaWalkerEnchantment;
import com.github.MitsukiGoto.hisabisamod.init.*;
import com.github.MitsukiGoto.hisabisamod.world.structure.HisabisaConfiguredStructure;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(HisabisaMod.MODID)
public class HisabisaMod {

    public static final String MODID = "hisabisamod";
    public static final String HISABISA_STRUCTURE_NAME = "hisabisa_structure";
    public static final String HISABISA_BIOME_NAME = "hisabisa_biome";
    public static final Logger LOGGER = LogManager.getLogger("Hisabisa Mod/Main");

    public HisabisaMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, HisabisaConfig.SPEC, "hisabisamod-common.toml");
        BlockInit.BLOCKS.register(bus);
        StructureInit.STRUCTURES.register(bus);
        BiomeInit.BIOMES.register(bus);
        ItemInit.ITEMS.register(bus);
        EnchantmentInit.ENCHANTMENTS.register(bus);
        bus.addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(this::biomeModification);
    }

    @SubscribeEvent
    public void onCreeperSpawns(LivingSpawnEvent.SpecialSpawn evt) {
        if (evt.getEntity() instanceof CreeperEntity && HisabisaConfig.isSpawnedCreeper_should_be_charged.get()) {
            CreeperEntity creeperEntity = (CreeperEntity) evt.getEntity();
            creeperEntity.getEntityData().set(CreeperEntity.DATA_IS_POWERED, true);
        }
    }

    @SubscribeEvent
    public void onEntityMoved(LivingEvent.LivingUpdateEvent evt) {
        LivingEntity entityLiving = evt.getEntityLiving();
        if (!(entityLiving instanceof PlayerEntity)) {
            return;
        }
        ItemStack boots = entityLiving.getItemBySlot(EquipmentSlotType.FEET);
        int isEnchantedFrostWalker = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FROST_WALKER, boots);
        if(isEnchantedFrostWalker==0&&HisabisaConfig.isAlways_frosted_walk.get()) {
            FrostWalkerEnchantment.onEntityMoved(entityLiving, entityLiving.getCommandSenderWorld(), entityLiving.blockPosition(),2);
        }
        int isEnchantedLavaWalker = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentInit.LAVA_WALKER.get(), boots);
        if(isEnchantedLavaWalker==0&&HisabisaConfig.isAlways_lava_walk.get()) {
            LavaWalkerEnchantment.onEntityMoved(entityLiving, entityLiving.getCommandSenderWorld(), entityLiving.blockPosition(),2);
        }
    }

    @SubscribeEvent
    public void setup(final FMLCommonSetupEvent evt) {
        evt.enqueueWork(() -> {
            StructureInit.setupStructures();
            HisabisaConfiguredStructure.registerConfiguredStructures();
            BiomeInit.setupBiome();
        });
    }

    public void biomeModification(final BiomeLoadingEvent evt) {
        evt.getGeneration().getStructures().add(() -> HisabisaConfiguredStructure.CONFIGURED_HISABISA_STRUCTURE);
    }

}
